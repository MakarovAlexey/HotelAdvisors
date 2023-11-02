/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import ru.hoteladvisors.util.OrganizationalFormConverter;

/**
 *
 * @author makarov
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {
    
    @Id
    @Column(name = "company_id", columnDefinition = "char(36)")
    @Type(type = "uuid-char")
    private UUID id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "form")
    @Convert(converter = OrganizationalFormConverter.class)
    private OrganizationalForm formOrNull;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    private Set<CompanyOffice> companyOffices;
    
    protected Company() { }
    
    public Company(String title, OrganizationalForm formOrNull, Address address) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.formOrNull = formOrNull;
        this.address = address;
        this.companyOffices = new HashSet<>();
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
        if (!(obj instanceof Company)) {
            return false;
        }
        final Company other = (Company) obj;
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

    public Address getAddress() {
        return address;
    }

    public Set<CompanyOffice> getCompanyOffices() {
        return companyOffices;
    }

    public CompanyOffice addCompanyOffice(String title, Address address) {
        CompanyOffice companyOffice = new CompanyOffice(this, title, address);
        companyOffices.add(companyOffice);
        
        return companyOffice;
    }
}
