package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.entity.*;
import org.example.util.MyHibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Slf4j
public class HibernateRunner {

//    @Slf4j
//    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);
    public static void main(String[] args) {

        UserEntity user = UserEntity.builder()
                .userName("avraam112russo@")
                .infoUser(InfoUser.builder()
                        .firstName("Russo")
                        .lastName("Sokolov")
                        .department(Department.builder().departmentName("IT").build())
                        .birthDay(new Birthday(LocalDate.of(2000, 6, 3)))
                        .build())
                .role(Role.ADMIN)
                .build();
        log.info("User entity is in transient state: {}", user);
        try (SessionFactory sessionFactory = MyHibernateConfiguration.buildSessionFactory()){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            log.info("Transaction is created: {}", session.getTransaction());
            session.saveOrUpdate(user);
            log.info("User: {} was successfully saved. Session {}", user, session.getTransaction());
            session.getTransaction().commit();
        }catch (Exception exception){
            log.error("Error occurred. ", exception);
            throw exception;
        }

    }
}