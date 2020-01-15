package com.tim04.school.trivia.service.user;

import com.tim04.school.trivia.persistence.user.UserEntity;
import com.tim04.school.trivia.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Transactional
    public void save(String username, String password, int age, String role){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setRole("ADMIN");
        userEntity.setAge(age);
        repository.save(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByUsername(username);
        return new MyUser(userEntity.getUsername(),userEntity.getPassword(),userEntity.getRole(),userEntity.getAge());
    }
}
