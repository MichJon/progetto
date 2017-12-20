package it.progettojorick.actionListeners;

import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.RichiestaRegistrazioneBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.dao.mysql.CarrelloDAO;
import it.progettojorick.dao.mysql.CategoriaDAO;
import it.progettojorick.dao.mysql.DistributoreDAO;
import it.progettojorick.dao.mysql.ProduttoreDAO;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.Distributore;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Produttore;
import it.progettojorick.view.GestoreFrame;
import it.progettojorick.view.ListaProdottiFrame;
import it.progettojorick.view.ProdottoFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

public class ProdottoListener implements ActionListener {

    private ProdottoFrame finestra;

    public ProdottoListener(ProdottoFrame finestra){
        this.finestra=finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //CONTROLLI
        System.out.println("Evento catturato!");

        String nome= finestra.getTxtNome().getText();
        String descrizione= finestra.getTxtDescrizone().getText();
        float prezzo= Float.parseFloat(finestra.getTxtPrezzo().getText());
        int quantita = Integer.parseInt(finestra.getTxtQuantita().getText());
        Categoria categoria= CategoriaDAO.getInstance().findByName(finestra.getTxtCategoria().getText());
        Produttore produttore= ProduttoreDAO.getInstance().findById(Integer.parseInt(finestra.getTxtProduttore().getText()));
        Distributore distributore= DistributoreDAO.getInstance().findById(Integer.parseInt(finestra.getTxtDistributore().getText()));
       // Path path= finestra.get;
//        String url=finestra.getLblUrl().getText();
//        String imgUrl= url.replace("\\", "\\\\\\");
            String nomeFile = finestra.getLblNomeFile().getText();



        if(e.getSource() instanceof JButton ){

            Prodotto p = ProdottoBusiness.getInstance().creaProdotto(nome,descrizione,prezzo,quantita,categoria,produttore,distributore,nomeFile);
            ProdottoBusiness.getInstance().inserisciProdotto(p);

            finestra.setVisible(false);
//            GestoreFrame finestraGestore = new GestoreFrame();
//            SessionManager.getInstance().getSession().put("finestra_gestore", finestraGestore);
            new ListaProdottiFrame();

        }


    }
}
