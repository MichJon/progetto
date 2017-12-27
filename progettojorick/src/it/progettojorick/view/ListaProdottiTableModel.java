package it.progettojorick.view;

import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.model.Prodotto;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListaProdottiTableModel extends AbstractTableModel {

    private ArrayList<Prodotto> listaprodotti = ProdottoBusiness.getInstance().prodottiPresenti();
    private String columnNames[] = {"Nome", "Descrizione", "Prezzo", "Disponibilità", "Categoria", "Id Produttore", "Id Distributore"};

  //  public ListaProdottiTableModel(ArrayList<Prodotto> listaprodotti) {
//        this.listaprodotti = listaprodotti;
//    }


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
    public boolean isCellEditable(int row, int col) {
        return col==2||col==3;
    }
    @Override
    public void setValueAt(Object value, int row, int col){

        Prodotto p = listaprodotti.get(row);

        if(col==2) {
            String prez = (String) value;
            float prezzo = Float.parseFloat(prez);
            // int disponibilita=p.getDisponibilita();
            // int intQuantita=Integer.parseInt(quantita);

                ProdottoBusiness.getInstance().setPrezzo(p, prezzo);

        }else if(col==3){
            String disp=(String) value;
            int disponibilita = Integer.parseInt(disp);
            ProdottoBusiness.getInstance().setDisponibilita(p,disponibilita);

        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        listaprodotti = ProdottoBusiness.getInstance().prodottiPresenti();
        Prodotto p = listaprodotti.get(rowIndex);


        switch (columnIndex) {
            case 0:
                return p.getNome();
            case 1:
                return p.getDescrizione();
            case 2:
                return p.getPrezzo();
            case 3:
                return p.getDisponibilita();
            case 4:
                return p.getCategoria().getNomecategoria();
            case 5:
                return p.getProduttore().getId();
            case 6:
                return p.getDistributore().getId();
            default:
                return null;
        }
    }
}
