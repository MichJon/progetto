package it.progettojorick.view;

import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.RichiestaRegistrazione;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class RichiesteRegistrazioneTableModel extends AbstractTableModel {

    private ArrayList<RichiestaRegistrazione> richiesteRegistrazioni;
    private String columnNames[] = {"Id richiesta","Amministratore","Email dell'utente","Stato"};

    public RichiesteRegistrazioneTableModel(ArrayList<RichiestaRegistrazione> richiesteRegistrazioni) {
        this.richiesteRegistrazioni = richiesteRegistrazioni;
    }

//    @Override
//    public Class getColumnClass(int columnIndex) {
//        if(columnIndex == 2) return Boolean.class;
//        return String.class;
//    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        try{
            return richiesteRegistrazioni.size();
        }catch(NullPointerException ex){
            return 0;
        }

    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RichiestaRegistrazione r = richiesteRegistrazioni.get(rowIndex);


        String email = null;
        if (r.getAmministratore()!=null)
            email=r.getAmministratore().getEmailAmministratore();

        switch(columnIndex) {
            case 0: return r.getIdRegistrazione();
            case 1: return email;
            case 2: return r.getPersona().getEmail();
            case 3: return r.getStato();
            default: return null;
        }
    }
}
