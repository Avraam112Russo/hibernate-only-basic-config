package org.example.entity.converter;

import org.example.entity.Department;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department, String> {

    @Override
    public String convertToDatabaseColumn(Department department) {
        return Optional.ofNullable(department)
                .map(dep-> dep.getDepartmentName())
                .orElse(null);
    }

    @Override
    public Department convertToEntityAttribute(String departmentName) {
        return Optional.ofNullable(departmentName)
                .map(name -> Department.builder().departmentName(name).build())
                .orElse(null);
    }
}
