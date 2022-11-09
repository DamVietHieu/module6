package com.team.case6.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String avatar;
    @Lob
    private String about;

    private String birthday;

    private String registerDate;

    @OneToOne
    private UserStatus userStatus;
    @OneToOne
    private User user;

    public UserInfo(String name, String email, String avatar, String about, String birthday, String registerDate, UserStatus userStatus, User user) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.about = about;
        this.birthday = birthday;
        this.registerDate = registerDate;
        this.userStatus = userStatus;
        this.user = user;
    }
}
