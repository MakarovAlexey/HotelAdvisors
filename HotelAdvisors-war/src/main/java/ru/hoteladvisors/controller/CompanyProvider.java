/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import ru.hoteladvisors.CompanyOfficeView;
import ru.hoteladvisors.CompanyView;
import ru.hoteladvisors.Database;
import ru.hoteladvisors.DatabaseProvider;
import ru.hoteladvisors.OrganizationalForm;

/**
 *
 * @author makarov
 */
public class CompanyProvider extends SelectorComposer<Component> {
    
    private UUID companyId;
    
    @Wire
    private Label titleLabel;
    
    @Wire
    private Label formLabel;
    
    @Wire
    private Label addressLabel;
    
    @Wire
    private Listbox officeListbox;
    
    private ListModelList<CompanyOfficeView> officeTableModel;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); //wire variables and event listners
        
        String valueOrNull = Executions.getCurrent().getParameter("id");
        if (valueOrNull == null) {
            throw new NoSuchElementException("Отсутствует параметр 'id', идентификатор компании");
        }
        
        Database database = DatabaseProvider.current();
        
        this.companyId = UUID.fromString(valueOrNull);
        
        CompanyView companyView = database.getCompanyView(companyId);
        
        titleLabel.setValue(companyView.getTitle());
        
        {
            OrganizationalForm formOrNull = companyView.getFormOrNull();
            if (formOrNull != null) {
                formLabel.setValue(formOrNull.getTitle());
            }
        }
        
        addressLabel.setValue(companyView.getAddress());
        
      List<CompanyOfficeView> companyOffices = database.getCompanyOffices(companyId);
      
      this.officeTableModel = new ListModelList<>(companyOffices);
      this.officeListbox.setModel(officeTableModel);
    }
    
    @Listen("onClick = #newOfficeButton")
    public void newOfficeButtonClick(Object event) {
        Executions.sendRedirect("/company_office_new.zul?id=" + companyId);
    }
}
