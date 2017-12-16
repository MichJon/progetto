package it.progettojorick.view;

import it.progettojorick.model.Prodotto;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CarrelloTableModel extends AbstractTableModel {

    private ArrayList<Prodotto> prodotti;
    private String columnNames[] = {"Nome Prodotto", "Prezzo", "Quantità"};

    public CarrelloTableModel(ArrayList<Prodotto> prodotti){
        this.prodotti=prodotti;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public int getRowCount() {

            return prodotti.size();

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto p = prodotti.get(rowIndex);



        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getPrezzo();
            case 2: return p.getQuantita();
            default: return null;
        }
    }
}
