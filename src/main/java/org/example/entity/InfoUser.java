package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoUser {
    private String firstName;
    private String lastName;
    //    @Convert(converter = BirthdayConverter.class) // setup this setting in configuration.addAttributeConverter();
    private Birthday birthDay;
    private Department department;
}
