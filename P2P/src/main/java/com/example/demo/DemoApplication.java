package com.example.demo;

import com.example.demo.Utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();

            // Insert user

            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
        }
    }


}
