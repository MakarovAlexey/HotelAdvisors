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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import ru.hoteladvisors.AddressView;
import ru.hoteladvisors.Database;
import ru.hoteladvisors.DatabaseProvider;

/**
 *
 * @author makarov
 */
public class CompanyOfficeNewController extends SelectorComposer<Component> {

    private UUID companyId;

    private Database database;

    @Wire
    private Textbox nameTextbox;

    @Wire
    private Combobox addressCombobox;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp); //wire variables and event listners

        String valueOrNull = Executions.getCurrent().getParameter("id");
        if (valueOrNull == null) {
            throw new NoSuchElementException("Отсутствует параметр 'id', идентификатор компании");
        }

        this.companyId = UUID.fromString(valueOrNull);

        this.database = DatabaseProvider.current();

        List<AddressView> addresses = database.getAddresses();
        ListModelList addressTableModel = new ListModelList<>(addresses);

        this.addressCombobox.setModel(addressTableModel);
    }

    @Listen("onClick = #addCompanyOfficeButton")
    public void addCompanyOffice() {
        String title = nameTextbox.getValue();

        AddressView address = addressCombobox.getSelectedItem()
                .getValue();

        UUID addressId = address.getId();

        database.addCompanyOffice(companyId, title, addressId);

        Executions.sendRedirect("/company.zul?id=" + companyId);
    }

    @Listen("onClick = #toCompanyButton")
    public void goToCompany() {
        Executions.sendRedirect("/company.zul?id=" + companyId);
    }
}
