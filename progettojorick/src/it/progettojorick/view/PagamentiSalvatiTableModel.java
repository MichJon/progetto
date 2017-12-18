package it.progettojorick.view;

import it.progettojorick.model.Pagamento;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PagamentiSalvatiTableModel extends AbstractTableModel {
    private ArrayList<Pagamento> pagamenti;
    private String columnNames[] = {"Numero carta", "Circuito", "CVV" , "Data di scadenza"};

    public PagamentiSalvatiTableModel(ArrayList<Pagamento> pagamenti){
        this.pagamenti=pagamenti;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    @Override
    public int getColumnCount() {
        return 4;
    }
    @Override
    public int getRowCount() {

        try {
            return pagamenti.size();
        }catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pagamento p = pagamenti.get(rowIndex);



        switch(columnIndex) {
            case 0: return p.getNumCarta();
            case 1: return p.getCircuito();
            case 2: return p.getCodSicurezza();
            case 3: return p.getDataScadenza();
            default: return null;
        }
    }
}
