package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.entity.UserEntity;
import org.example.entity.converter.BirthdayConverter;
import org.example.entity.converter.DepartmentConverter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class MyHibernateConfiguration {
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();
        // we indicated which class hibernate can mapping
        configuration.addAnnotatedClass(UserEntity.class);
        // convert Birthday class -> SQL Format
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.addAttributeConverter(new DepartmentConverter());
        configuration.configure();
//        // convert JSONB from SQL to String
//        configuration.registerTypeOverride(new JsonBinaryType());
        return configuration.buildSessionFactory();
    }
}
