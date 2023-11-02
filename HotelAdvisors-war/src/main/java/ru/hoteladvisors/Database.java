/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author makarov
 */
@Stateless
public class Database {
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<CompanyView> getCompanies() {
        return entityManager.createQuery("FROM Company", Company.class)
                .getResultList()
                .stream()
                .map(this::companyView)
                .collect(Collectors.toList());
    }

    public List<AddressView> getAddresses() {
        return entityManager.createQuery("FROM Address", Address.class)
                .getResultList()
                .stream()
                .map(x -> {
                    UUID addressId = x.getId();
                    String postalCodeOrNull = x.getPostalCodeOrNull();
                    String city = x.getCity();
                    String street = x.getStreet();
                    String house = x.getHouse();
                    String flatOrNull = x.getFlatOrNull();
                    
                    return new AddressView(addressId, postalCodeOrNull, city, street, house, flatOrNull);
                })
                .collect(Collectors.toList());
    }

    public AddressView addAddress(String postalCode, String city, String street, String house, String flat) {
        Address address = new Address(postalCode, city, street, house, flat);
        entityManager.persist(address);
        
        UUID id = address.getId();
        return new AddressView(id, postalCode, city, street, house, flat);
    }

    public CompanyView addCompany(String title, OrganizationalForm formOrNull, UUID addressId) {
        Address address = findAddress(addressId);
        
        Company company = new Company(title, formOrNull, address);
        entityManager.persist(company);
        
        return companyView(company);
    }

    public void addCompanyOffice(UUID companyId, String title, UUID addressId) {
        Company company = findCompany(companyId);
        Address address = findAddress(addressId);
        
        company.addCompanyOffice(title, address);
    }

    public CompanyView getCompanyView(UUID companyId) {
        Company company = findCompany(companyId);
        
        return companyView(company);
    }

    public List<CompanyOfficeView> getCompanyOffices(UUID companyId) {
        return findCompany(companyId)
                .getCompanyOffices()
                .stream()
                .map(this::companyOfficeView)
                .collect(Collectors.toList());
    }

    private Company findCompany(UUID companyId) {
        Company companyOrNull = entityManager.find(Company.class, companyId);
        if (companyOrNull == null) {
            throw new NoSuchElementException("company with id=`" + companyOrNull + "' not found");
        }
        
        return companyOrNull;
    }

    private Address findAddress(UUID addressId) {
        Address addressOrNull = entityManager.find(Address.class, addressId);
        if (addressOrNull == null) {
            throw new NoSuchElementException("address with id=`" + addressId + "' not found");
        }
        
        return addressOrNull;
    }

    private CompanyView companyView(Company company) {
        UUID companyId = company.getId();
        String title = company.getTitle();
        OrganizationalForm formOrNull = company.getFormOrNull();
        String addressText = company.getAddress().asText();
        
        return new CompanyView(companyId, title, formOrNull, addressText);
    }

    private CompanyOfficeView companyOfficeView(CompanyOffice companyOffice) {
        UUID id = companyOffice.getId();
        UUID companyId = companyOffice.getId();
        String title = companyOffice.getTitle();
        String addressText = companyOffice.getAddress().asText();
        
        return new CompanyOfficeView(id, companyId, title, addressText);
    }
}
