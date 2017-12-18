package it.progettojorick.view;

import it.progettojorick.model.Pagamento;
import it.progettojorick.model.PreferenzeConsegna;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PreferenzeConsegnaTableModel extends AbstractTableModel {
    private ArrayList<PreferenzeConsegna> preferenzeconsegna;
    private String columnNames[] = {"Nominativo", "Indirizzo"};

    public PreferenzeConsegnaTableModel(ArrayList<PreferenzeConsegna> preferenzeconsegna){
        this.preferenzeconsegna=preferenzeconsegna;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    @Override
    public int getColumnCount() {
        return 2;
    }
    @Override
    public int getRowCount() {

        try {
            return preferenzeconsegna.size();
        }catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PreferenzeConsegna pref = preferenzeconsegna.get(rowIndex);



        switch(columnIndex) {
            case 0: return pref.getNominativo();
            case 1: return pref.getIndirizzoConsegna();
            default: return null;
        }
    }
}
