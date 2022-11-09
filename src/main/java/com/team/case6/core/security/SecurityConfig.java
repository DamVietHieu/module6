package com.team.case6.core.security;


import com.team.case6.core.helper.CustomAccessDeniedHandler;
import com.team.case6.core.filter.JwtAuthenticationFilter;
import com.team.case6.core.helper.RestAuthenticationEntryPoint;
import com.team.case6.core.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IUserService userService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //bean mã hóa pass
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //lấy user từ DB
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());//Tùy chỉnh lại thông báo 401 thông qua class restEntryPoint
        http.authorizeRequests()
                .antMatchers("/login",
                        "/register","/image/**","/logout/**","/userView/**","/api/**").permitAll()
                .antMatchers(
                        "/changePassword/**",
                        "/username/**",
                         "/userInfo/**",
                        "/**",
                        "/blog/**",
                        "/like/**","/comment/**").access("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
                .antMatchers("/admin/**","/category/**").access( "hasAuthority('ROLE_ADMIN')")
                .anyRequest().authenticated()
//                .and().formLogin().loginPage("http://localhost:63342/case4-FE/login.html")
//                .successHandler(new CustomSuccessHandler())
//                .and().exceptionHandling().accessDeniedPage("/accessDenied")
                .and().csrf().disable()
                ;
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }
}
