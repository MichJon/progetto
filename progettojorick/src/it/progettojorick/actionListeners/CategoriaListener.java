package it.progettojorick.actionListeners;


import it.progettojorick.business.CategoriaBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.dao.mysql.CategoriaDAO;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.GestoreCatalogo;
import it.progettojorick.view.CategoriaFrame;
import it.progettojorick.view.GestoreFrame;
import it.progettojorick.view.ListaCategorieFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class CategoriaListener implements ActionListener{
    private CategoriaFrame finestra;

    public CategoriaListener(CategoriaFrame finestra){
        this.finestra=finestra;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        GestoreCatalogo g = (GestoreCatalogo)SessionManager.getInstance().getSession().get("gestore");

        System.out.println("Evento catturato!");

        String nome= finestra.getTxtNome().getText();

        if(e.getSource() instanceof JButton) {
            //ArrayList<Categoria> categorie = CategoriaBusiness.getInstance().categoriePresenti();
            //Iterator i = categorie.iterator();
            //while (i.hasNext()) {
                //Categoria cat = (Categoria) i.next();


                if (!nome.equals("") && CategoriaDAO.getInstance().findByName(nome)==null) {

                    Categoria c = CategoriaBusiness.getInstance().creaCategoria(nome, g);
                    CategoriaBusiness.getInstance().inserisciCategoria(c);
                    JOptionPane.showMessageDialog(null, "Categoria inserita.");
                    finestra.setVisible(false);
//            GestoreFrame finestraGestore = new GestoreFrame();
//            SessionManager.getInstance().getSession().put("finestra_gestore", finestraGestore);
                    new ListaCategorieFrame();
                    }
                else if (CategoriaDAO.getInstance().findByName(nome)!=null){
                JOptionPane.showMessageDialog(null, "categoria già presente.");
                }
            else if (nome.equals("")) {
                JOptionPane.showMessageDialog(null, "Inserire il nome della categoria.");
                }
            }


        //}
    }
}
