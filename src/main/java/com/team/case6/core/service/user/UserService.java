package com.team.case6.core.service.user;


import com.team.case6.core.security.UserPrincipal;
import com.team.case6.core.model.entity.Role;
import com.team.case6.core.model.entity.User;
import com.team.case6.core.repository.IUserInfoRepo;
import com.team.case6.core.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepo userRepository;
    private IUserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);//Mã hóa pass của người dùng
        user.setPassword(encodePassword);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(2L, "ROLE_USER"));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserPrincipal.build(user);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
