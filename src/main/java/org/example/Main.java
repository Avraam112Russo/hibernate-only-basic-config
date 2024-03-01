package org.example;

import org.example.entity.Role;
import org.example.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(UserEntity.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            UserEntity user = UserEntity.builder()
                    .userName("Masha01")
                    .firstName("updateName")
                    .lastName("Sokolova")
                    .birthDay(LocalDate.of(2000, 8, 16))
                    .age(23)
                    .role(Role.STAFF)
                    .build();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }

    }
}