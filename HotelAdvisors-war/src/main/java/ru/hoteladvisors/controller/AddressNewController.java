/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors.controller;

import java.util.Optional;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import ru.hoteladvisors.Database;
import ru.hoteladvisors.DatabaseProvider;

/**
 *
 * @author makarov
 */
public class AddressNewController extends SelectorComposer<Component> {
    
    private Database database;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
      super.doAfterCompose(comp); //wire variables and event listners
      
      this.database = DatabaseProvider.current();
    }
    
    @Wire
    private Textbox postalCodeTextBox;
    
    @Wire
    private Textbox cityTextBox;
    
    @Wire
    private Textbox streetTextBox;
    
    @Wire
    private Textbox houseTextBox;
    
    @Wire
    private Textbox flatTextbox;
    
    @Listen("onClick = #addAddress")
    public void addAddress() {
        String postalCode = Optional.of(postalCodeTextBox)
                .map(Textbox::getValue)
                .filter(x -> !x.isBlank())
                .orElse(null);
        
        String city = cityTextBox.getValue();
        String street = streetTextBox.getValue();
        String house = houseTextBox.getValue();
        
        String flat = Optional.of(flatTextbox)
                .map(Textbox::getValue)
                .filter(x -> !x.isBlank())
                .orElse(null);
        
        database.addAddress(postalCode, city, street, house, flat);
        
        Executions.sendRedirect("/addresses.zul");
    }
}
