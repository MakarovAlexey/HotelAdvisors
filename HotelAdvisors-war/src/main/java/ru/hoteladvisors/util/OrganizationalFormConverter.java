/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import ru.hoteladvisors.OrganizationalForm;

/**
 *
 * @author makarov
 */
@Converter
public class OrganizationalFormConverter implements AttributeConverter<OrganizationalForm, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrganizationalForm x) {
        return x.getId();
    }

    @Override
    public OrganizationalForm convertToEntityAttribute(Integer y) {
        return OrganizationalForm.get(y);
    }
}
