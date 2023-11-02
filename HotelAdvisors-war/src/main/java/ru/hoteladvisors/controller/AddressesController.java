/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors.controller;

import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import ru.hoteladvisors.AddressView;
import ru.hoteladvisors.Database;
import ru.hoteladvisors.DatabaseProvider;

/**
 *
 * @author makarov
 */
public class AddressesController extends SelectorComposer<Component> {
    
    @Wire
    private Listbox addressListbox;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
      super.doAfterCompose(comp); //wire variables and event listners
      
      Database database = DatabaseProvider.current();
      List<AddressView> addresses = database.getAddresses();
      
      ListModelList addressTableModel = new ListModelList<>(addresses);
      this.addressListbox.setModel(addressTableModel);
    }
}
