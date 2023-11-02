/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors;

import java.util.NoSuchElementException;

/**
 *
 * @author makarov
 */
public class OrganizationalForm {
    
    public static final OrganizationalForm FORM_LIMITED = new OrganizationalForm(1, "ООО");
    
    public static final OrganizationalForm FORM_INDUVIDUAL = new OrganizationalForm(2, "ИП");
    
    public static final OrganizationalForm FORM_JOINT_COMPANY = new OrganizationalForm(3, "АО");

    public static OrganizationalForm get(int id) {
        switch (id) {
            case 1:
                return FORM_LIMITED;
            case 2:
                return FORM_INDUVIDUAL;
            case 3:
                return FORM_JOINT_COMPANY;
            default:
                throw new NoSuchElementException("Organisation form for id=`" + id + "' not found");
        }
    }
    
    private final int id;
    
    private final String title;

    private OrganizationalForm(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrganizationalForm)) {
            return false;
        }
        final OrganizationalForm other = (OrganizationalForm) obj;
        if (other.getId() != this.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
