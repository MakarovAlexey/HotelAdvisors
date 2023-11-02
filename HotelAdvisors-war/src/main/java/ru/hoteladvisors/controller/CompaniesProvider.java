/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.hoteladvisors.controller;

import java.util.List;
import java.util.Optional;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import ru.hoteladvisors.CompanyView;
import ru.hoteladvisors.Database;
import ru.hoteladvisors.DatabaseProvider;

/**
 *
 * @author makarov
 */
public class CompaniesProvider extends SelectorComposer<Component> {
    
    @Wire
    private Listbox companyListbox;
    
    private ListModelList<CompanyView> companyTableModel;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
      super.doAfterCompose(comp); //wire variables and event listners
      
      Database database = DatabaseProvider.current();
      List<CompanyView> companies = database.getCompanies();
      
      this.companyTableModel = new ListModelList<>(companies);
      this.companyListbox.setModel(companyTableModel);
    }
    
    @Listen("onClick = #companyListbox")
    public void onClick(Object event) {
        Optional.of(companyListbox)
                .map(Listbox::getSelectedItem)
                .<CompanyView>map(Listitem::getValue)
                .ifPresent(companyView -> {
                    Executions.sendRedirect("/company.zul?id=" + companyView.getId());
                });
    }
}
