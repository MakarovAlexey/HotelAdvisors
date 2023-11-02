/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors;

import javax.enterprise.inject.spi.CDI;

/**
 *
 * @author makarov
 */
public class DatabaseProvider {

    public static Database current() {
        return CDI.current()
                .select(Database.class)
                .get();
    }
}
