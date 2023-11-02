/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author makarov
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {
    
    @Id
    @Column(name = "address_id", columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID id;
    
    @Column(name = "postal_code")
    private String postalCodeOrNull;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "house")
    private String house;
    
    @Column(name = "flat")
    private String flatOrNull;
    
    protected Address() { }
    
    public Address(String postalCodeOrNull, String city, String street, String house, String flatOrNull) {
        this.id = UUID.randomUUID();
        this.postalCodeOrNull = postalCodeOrNull;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flatOrNull = flatOrNull;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.id != other.getId()) {
            return false;
        }
        return true;
    }

    public UUID getId() {
        return id;
    }

    public String getPostalCodeOrNull() {
        return postalCodeOrNull;
    }

    public void setPostalCodeOrNull(String postalCodeOrNull) {
        this.postalCodeOrNull = postalCodeOrNull;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlatOrNull() {
        return flatOrNull;
    }

    public void setFlatOrNull(String flatOrNull) {
        this.flatOrNull = flatOrNull;
    }

    public String asText() {
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
}
