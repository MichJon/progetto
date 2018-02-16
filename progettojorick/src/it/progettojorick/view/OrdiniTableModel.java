package it.progettojorick.view;

import it.progettojorick.model.RichiestaOrdine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class OrdiniTableModel extends AbstractTableModel {

    private ArrayList<RichiestaOrdine> richiesteOrdine;
    private String columnNames[] = {"Id Ordine","Email Amministratore","Stato","Email utente","Metodo di Pagamento"};//,"Prodotti"};

    public OrdiniTableModel(ArrayList<RichiestaOrdine> richiesteOrdine) {
        this.richiesteOrdine = richiesteOrdine;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if(columnIndex == 5) return String.class;

        return String.class;
    }
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }


    @Override
    public int getRowCount() {
        try{
            return richiesteOrdine.size();
        }catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RichiestaOrdine r = richiesteOrdine.get(rowIndex);


        String email = null;
        if (r.getAmministratore()!=null)
            email=r.getAmministratore().getEmailAmministratore();

        switch(columnIndex) {
            case 0: return r.getIdRichiesta();
            case 1: return email;//r.getAmministratore().getEmailAmministratore();
            case 2: return r.getStato();
            case 3: return r.getUtente().getEmailUtente();
            case 4: return r.getPagamento().getNumCarta();
          //  case 5: return //r.getCarrello().getProdottiContenuti();
            default: return null;
        }
    }





}
