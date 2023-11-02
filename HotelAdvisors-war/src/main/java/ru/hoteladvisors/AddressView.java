/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors;

import java.util.UUID;

/**
 *
 * @author makarov
 */
public class AddressView {
    
    private final UUID id;
    
    private final String postalCodeOrNull;
    
    private final String city;
    
    private final String street;
    
    private final String house;
    
    private final String flatOrNull;

    public AddressView(UUID id, String postalCodeOrNull, String city, String street, String house, String flatOrNull) {
        this.id = id;
        this.postalCodeOrNull = postalCodeOrNull;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flatOrNull = flatOrNull;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (postalCodeOrNull != null) {
            sb.append(postalCodeOrNull);
            sb.append(", ");
        }
        
        sb.append("г. ").append(city).append(", ");
        sb.append("ул. ").append(street).append(", ");
        sb.append("д. ").append(house);
        
        if (flatOrNull != null) {
            sb.append(", кв. ").append(flatOrNull);
        }
        
        return sb.toString();
    }

    public UUID getId() {
        return id;
    }

    public String getPostalCodeOrNull() {
        return postalCodeOrNull;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlatOrNull() {
        return flatOrNull;
    }
}
