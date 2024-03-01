package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userentity", schema = "public")
public class UserEntity {
    @Id
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Role role;
}
