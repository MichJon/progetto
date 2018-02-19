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
import java.util.ArrayList;

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
        int disponibilita = Integer.parseInt(finestra.getTxtDisponibilita().getText());
       // Categoria categoria= CategoriaDAO.getInstance().findByName(finestra.getTxtCategoria().getText());
        Categoria categoria =  CategoriaDAO.getInstance().findByName((String)finestra.getCbCategoria().getSelectedItem());

        String idProd =finestra.getCbProduttore().getSelectedItem().toString().split(":")[1].split("\\)")[0];

//        Produttore produttore= ProduttoreDAO.getInstance().findById(Integer.parseInt(finestra.getTxtProduttore().getText()));
        Produttore produttore= ProduttoreDAO.getInstance().findById(Integer.parseInt(idProd));

        String idDist =finestra.getCbDistributore().getSelectedItem().toString().split(":")[1].split("\\)")[0];

//        Distributore distributore= DistributoreDAO.getInstance().findById(Integer.parseInt(finestra.getTxtDistributore().getText()));
        Distributore distributore= DistributoreDAO.getInstance().findById(Integer.parseInt(idDist));
        ArrayList<Prodotto> prodottiContenuti = finestra.getProdottiContenuti();
       // Path path= finestra.get;
//        String url=finestra.getLblUrl().getText();
//        String imgUrl= url.replace("\\", "\\\\\\");
        String nomeFile = finestra.getLblNomeFile().getText();
        int sconto = Integer.parseInt(finestra.getTxtSconto().getText());



        if(e.getSource() instanceof JButton ){

            Prodotto p = ProdottoBusiness.getInstance().creaProdotto(nome,descrizione,prezzo,disponibilita,categoria,produttore,distributore,nomeFile,sconto);
            ProdottoBusiness.getInstance().inserisciProdotto(p);
            //inserimento prodotti composti
            if(prodottiContenuti!=null)
                ProdottoBusiness.getInstance().inserisciProdottiContenuti(p, prodottiContenuti);

            finestra.setVisible(false);
//            GestoreFrame finestraGestore = new GestoreFrame();
//            SessionManager.getInstance().getSession().put("finestra_gestore", finestraGestore);
            new ListaProdottiFrame();

        }


    }
}
