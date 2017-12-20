package it.progettojorick.view;

import it.progettojorick.model.Categoria;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListaCategorieTableModel extends AbstractTableModel {

    private ArrayList<Categoria> listacategorie;
    private String columnNames[] = {"Nome"};

    public ListaCategorieTableModel(ArrayList<Categoria> listacategorie) {
        this.listacategorie = listacategorie;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        try {
            return listacategorie.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria c = listacategorie.get(rowIndex);


        switch (columnIndex) {
            case 0:
                return c.getNomecategoria();
            default:
                return null;
        }
    }

}
