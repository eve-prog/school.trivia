package com.tim04.school.trivia.service.user;

import com.tim04.school.trivia.persistence.user.UserEntity;
import com.tim04.school.trivia.persistence.repositories.UserRepository;
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
    public void save(String first_name, String  last_name, String username, String e_mail, String phone, String active, String password, String role){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirst_name(first_name);
        userEntity.setLast_name(last_name);
        userEntity.setUsername(username);
        userEntity.setEmail(e_mail);
        userEntity.setPhone(phone);
        userEntity.setActive(active);
        userEntity.setPassword(password);
        userEntity.setRole("ADMIN");
        repository.save(userEntity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByUsername(username);
        return new MyUser(userEntity.getEmail(), userEntity.getPassword(), userEntity.getRole());
    }
}
