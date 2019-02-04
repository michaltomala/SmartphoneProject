package pl.coderslab.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import java.util.List;


@Service
public class InitApplicationService {

    @Autowired
    private UserRepository userRepository;

    /**
     * In my app there is only one admin - created during server started
     */
    public void initAdmin(){
        System.out.println("INIT APP");
        User userToCheck = userRepository.findOne((long) 1);
        if( userToCheck == null) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword("123456");
            user.setEmail("admin@admin.pl");
            user.setAdmin(true);
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
        }
    }

}
