package it.progettojorick.view;

import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Paniere;
import it.progettojorick.model.Prodotto;
import utilities.SpinnerEditor;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;

public class CarrelloTableModel extends AbstractTableModel {

    private ArrayList<Prodotto> prodotti;
    private String nomePaniere;
    private String columnNames[] = {"Nome Prodotto", "Prezzo", "Quantità"};//, "Paniere"};

    Carrello c =(Carrello)SessionManager.getInstance().getSession().get("carrello");

    public CarrelloTableModel(ArrayList<Prodotto> prodotti){//, String nomePaniere){
        this.prodotti=prodotti;
     //   this.nomePaniere=nomePaniere;
    }
//
//    @Override
//    public Class getColumnClass(int columnIndex) {
//        if(columnIndex == 3) return SpinnerModel.class;
//
//        return String.class;
//    }

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

//    public void setCellEditable(int row, int col, boolean value) {
//        this.editable_cells[row][col] = value; // set cell true/false
//        this.fireTableCellUpdated(row, col);
//    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col==2;
    }


    @Override
    public void setValueAt(Object value, int row, int col){
        Prodotto p = prodotti.get(row);
        String quant = (String) value;
        int quantita = Integer.parseInt(quant);
        int disponibilita=p.getDisponibilita();
       // int intQuantita=Integer.parseInt(quantita);
        if (quantita<=disponibilita) {

            ProdottoBusiness.getInstance().setQuantita(quantita, c, p);
        }
        else{
            JOptionPane.showMessageDialog(null,"Quantità richiesta non presente, valore impostato alla disponibilità massima.");
            ProdottoBusiness.getInstance().setQuantita(disponibilita,c,p);
        }

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto p = prodotti.get(rowIndex);



        int quantita = ProdottoBusiness.getInstance().getQuantita(c,p);
//        Paniere pan =(Paniere) SessionManager.getInstance().getSession().get("paniere");
//
//        String nomePan = "";
//        if(pan!=null)
//            nomePan=pan.getNome();

        switch(columnIndex) {
            case 0: return p.getNome();
            case 1: return p.getPrezzo();
            case 2:

                return quantita ;
//            case 3:
//                if(p.isDalPaniere()) return pan.getNome();
//                else return null;
            default: return null;
        }
    }




}
