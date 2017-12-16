package it.progettojorick.actionListeners;

import it.progettojorick.business.CategoriaBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.GestoreCatalogo;
import it.progettojorick.view.CategoriaFrame;
import it.progettojorick.view.GestoreFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        if(e.getSource() instanceof JButton){

            Categoria c = CategoriaBusiness.getInstance().creaCategoria(nome,g);
            CategoriaBusiness.getInstance().inserisciCategoria(c);
            JOptionPane.showMessageDialog(null,"Categoria inserita.");
            finestra.setVisible(false);
            GestoreFrame finestraGestore = new GestoreFrame();
            SessionManager.getInstance().getSession().put("finestra_gestore", finestraGestore);

        }

    }
}
