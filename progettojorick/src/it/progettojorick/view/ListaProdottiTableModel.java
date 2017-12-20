package it.progettojorick.view;

import it.progettojorick.model.Prodotto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListaProdottiTableModel extends AbstractTableModel {

    private ArrayList<Prodotto> listaprodotti;
    private String columnNames[] = {"Nome", "Descrizione", "Prezzo", "Quantità", "Categoria", "Id Produttore", "Id Distributore"};

    public ListaProdottiTableModel(ArrayList<Prodotto> listaprodotti) {
        this.listaprodotti = listaprodotti;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        try {
            return listaprodotti.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto p = listaprodotti.get(rowIndex);


        switch (columnIndex) {
            case 0:
                return p.getNome();
            case 1:
                return p.getDescrizione();
            case 2:
                return p.getPrezzo();
            case 3:
                return p.getQuantita();
            case 4:
                return p.getCategoria();
            case 5:
                return p.getProduttore().getId();
            case 6:
                return p.getDistributore().getId();
            default:
                return null;
        }
    }
}
