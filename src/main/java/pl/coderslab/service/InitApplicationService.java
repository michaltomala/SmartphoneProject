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

    public void initAdmin(){
        System.out.println("INIT APP");
        List<User> users = userRepository.findAll();
        if( users.size() == 0) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword("123456");
            user.setEmail("admin@admin.pl");
            user.setAdmin(true);
//     todo przeniesienie do serwicu
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
        }
    }

}
