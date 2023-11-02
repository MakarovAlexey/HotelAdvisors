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
public class CompanyOfficeView {
    
    private final UUID id;
    
    private final UUID companyId;
    
    private final String title;
    
    private final String address;
    
    public CompanyOfficeView(UUID id, UUID companyId, String title, String address) {
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }
}
