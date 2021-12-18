package com.apt.p2p.common;

import com.apt.p2p.entity.User;
import com.apt.p2p.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SeedData {
//    @Autowired
//    private EasyRandom easyRandom;
    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event){
        seedUser(10);
    }

    private void seedUser(int number){
        EasyRandom easyRandom = new EasyRandom();
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters()
                .excludeField(FieldPredicates.named("avatar").and(FieldPredicates.ofType(String.class)).and(FieldPredicates.inClass(User.class)))
                .stringLengthRange(3, 10);
        User user = easyRandom.nextObject(User.class);
        userRepository.save(user);
//        for (int i = 0; i < number; i++) {
//            easyRandom.nextObject(User.class);
//        }
//        new Exception().printStackTrace();
    }
}
