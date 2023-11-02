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
public class CompanyView {
    
    private UUID id;
    
    private String title;
    
    private OrganizationalForm formOrNull;
    
    private String address;
    
    protected CompanyView() { }
    
    public CompanyView(UUID id, String title, OrganizationalForm formOrNull, String address) {
        this.id = id;
        this.title = title;
        this.formOrNull = formOrNull;
        this.address = address;
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
        if (!(obj instanceof CompanyView)) {
            return false;
        }
        final CompanyView other = (CompanyView) obj;
        if (!other.getId().equals(this.id)) {
            return false;
        }
        return true;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OrganizationalForm getFormOrNull() {
        return formOrNull;
    }

    public void setFormOrNull(OrganizationalForm formOrNull) {
        this.formOrNull = formOrNull;
    }

    public String getAddress() {
        return address;
    }
}
