package com.example.demo.util;

import com.example.demo.security.AppUser;
import com.example.demo.security.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInit implements CommandLineRunner {

    @Autowired
    private UserService userService;
    private final Log logger = LogFactory.getLog(FirstTimeInit.class);
    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()){
            logger.info("No user accounts found.Creating some users");

            AppUser user = new AppUser("rayen","password","rayen");
            userService.save(user);
        }
    }
}
