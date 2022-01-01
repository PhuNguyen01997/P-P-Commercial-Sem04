package com.apt.p2p.common;

import com.apt.p2p.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SeedData {
    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
//        seedUser(10);
        boolean set = true;
        if(set){
            seedLocation();
        }
    }

//    private void seedUser(int number){
//    }

    private void seedLocation(){

    }
}
