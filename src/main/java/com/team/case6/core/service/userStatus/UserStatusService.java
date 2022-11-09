package com.team.case6.core.service.userStatus;


import com.team.case6.core.model.entity.UserStatus;
import com.team.case6.core.repository.IUserStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStatusService implements IUserStatusService {
    @Autowired
    IUserStatusRepo userStatusRepository;

    @Override
    public List<UserStatus> findAll() {
        return userStatusRepository.findAll();
    }

    @Override
    public UserStatus save(UserStatus userStatus) {
        return userStatusRepository.save(userStatus);
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Optional<UserStatus> findById(Long id) {
        return userStatusRepository.findById(id);
    }

}
