/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import ru.hoteladvisors.AddressView;
import ru.hoteladvisors.Database;
import ru.hoteladvisors.DatabaseProvider;
import ru.hoteladvisors.OrganizationalForm;

/**
 *
 * @author makarov
 */
public class CompanyNewController extends SelectorComposer<Component> {
    
    private Database database;
    
    @Wire
    private Textbox companyNameTextbox;
    
    @Wire
    private Radiogroup fromRaidoGroup;
    
    @Wire
    private Combobox addressCombobox;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
      super.doAfterCompose(comp); //wire variables and event listners
      
      this.database = DatabaseProvider.current();
      
      fromRaidoGroup.getItemAtIndex(0)
              .setValue(OrganizationalForm.FORM_LIMITED);
      
      fromRaidoGroup.getItemAtIndex(1)
              .setValue(OrganizationalForm.FORM_INDUVIDUAL);
      
      fromRaidoGroup.getItemAtIndex(2)
              .setValue(OrganizationalForm.FORM_JOINT_COMPANY);
      
      List<AddressView> addresses = database.getAddresses();
      ListModelList addressTableModel = new ListModelList<>(addresses);
      
      this.addressCombobox.setModel(addressTableModel);
    }
    
    @Listen("onClick = #addCompanyButton")
    public void addCompany() {
        String title = companyNameTextbox.getValue();
        
        OrganizationalForm formOrNull = (OrganizationalForm) Optional.of(fromRaidoGroup)
                .map(Radiogroup::getSelectedItem)
                .map(Radio::getValue)
                .orElse(null);
        
        AddressView address = addressCombobox.getSelectedItem()
                .getValue();
        
        UUID addressId = address.getId();
        
        database.addCompany(title, formOrNull, addressId);
        
        Executions.sendRedirect("/companies.zul");
    }
}
