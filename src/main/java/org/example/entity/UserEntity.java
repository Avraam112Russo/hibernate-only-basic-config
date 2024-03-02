package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userentity", schema = "public")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "someNameForCustomSequence")
    @SequenceGenerator(name = "someNameForCustomSequence", sequenceName = "userentity_user_id_seq", allocationSize = 1)
    private Long userId;
    @Column(name = "user_name", unique = true)
    private String userName;
    @Embedded
    @AttributeOverride(name = "firstName", column = @Column(name = "first_name_column_name"))
    @AttributeOverride(name = "lastName", column = @Column(name = "last_name_column_name"))
    @AttributeOverride(name = "birthDay", column = @Column(name = "birthday_column_name"))
    @AttributeOverride(name = "department", column = @Column(name = "department_column_name"))
    private InfoUser infoUser;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;



}
