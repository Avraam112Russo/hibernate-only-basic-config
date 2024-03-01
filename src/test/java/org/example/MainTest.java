package org.example;

import org.example.entity.UserEntity;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void checkReflectionApi() throws SQLException, IllegalAccessException {
        UserEntity user = UserEntity.builder()
                .userName("Masha01")
                .firstName("updateName")
                .lastName("Sokolova")
                .birthDay(LocalDate.of(2000, 8, 16))
                .age(23)
                .build();



        String sql = """
                insert
                into %s
                (%s)
                values
                (%s)
                """;

        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());
        Field[] declaredFields = user.getClass().getDeclaredFields();
        String columnNames = Arrays.stream(declaredFields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(column -> column.name()).orElse(field.getName())
                ).collect(Collectors.joining(", "));
        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?").collect(Collectors.joining(", "));
        System.out.println(sql.formatted(tableName, columnNames, columnValues));
        Connection connection = null;
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
        for (Field field:declaredFields){
            field.setAccessible(true);
            preparedStatement.setObject(1, field.get(user));
        }
    }
}