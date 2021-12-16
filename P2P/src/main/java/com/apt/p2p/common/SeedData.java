package com.apt.p2p.common;

import com.apt.p2p.entity.User;
import com.apt.p2p.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SeedData {
    @Autowired
    private EasyRandom easyRandom;
    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        seedUser(10);
    }

    private void seedUser(int number){
//        for (int i = 0; i < number; i++) {
//            easyRandom.nextObject(User.class);
//        }
//        new Exception().printStackTrace();
    }
}
