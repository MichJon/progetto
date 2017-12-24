package it.progettojorick.view;

import it.progettojorick.business.CategoriaBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.dao.mysql.ProdottoDAO;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.Utente;
import utilities.BottoneConImg;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UtenteFrame extends JFrame {

    private int x=850;
    private int y=700;


    ArrayList<Prodotto> prodottiList;// = ProdottoBusiness.getInstance().prodottiPresenti();

    Utente u =(Utente) SessionManager.getInstance().getSession().get("utente");
//
//    public void setProdottiList(ArrayList<Prodotto> prodottiList) {
//        this.prodottiList = prodottiList;
//    }

    public UtenteFrame(ArrayList<Prodotto> prodottiList){


        super("Catalogo");

        this.prodottiList = prodottiList;
        UtenteFrame _this=this;
        Container c = getContentPane();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

        c.setLayout(new BorderLayout());
        JPanel nord = new JPanel(new FlowLayout());
        JPanel centro = new JPanel(new FlowLayout());
        JPanel sud = new JPanel();
        c.add(nord,BorderLayout.NORTH);
        c.add(centro,BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);

        JButton panieri = new JButton("Panieri");
        panieri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                new ElencoPanieriView();
            }
        });
        JButton carrello = new JButton("Carrello");
        JButton logout = new JButton("LOGOUT");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                new LoginFrame();
            }
        });
        JButton btnOrdini=new JButton("Visualizza Ordini");
        btnOrdini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                new OrdiniUtenteFrame();
            }
        });
        sud.add(logout);
        sud.add(panieri);
        sud.add(carrello);
        sud.add(btnOrdini);
        carrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.setVisible(false);
                try{
                SessionManager.getInstance().getSession().put("finestra_carrello",new CarrelloFrame());
                }catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null,"Non sono presenti prodotti nel carrello");
                    _this.setVisible(true);
                }
            }
        });
        JMenuBar bar = new JMenuBar();
        JMenu mostraTutti = new JMenu("Mostra tutti");
        JMenu categorie = new JMenu("Categorie");
        bar.add(mostraTutti);

        bar.add(categorie);
        mostraTutti.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                _this.setVisible(false);
                UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().prodottiPresenti());

                    SessionManager.getInstance().getSession().put("finestra_utente",fr);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(e.getID()+ MouseEvent.MOUSE_PRESSED);
//               if (e.getID()== MouseEvent.MOUSE_PRESSED){
//                        _this.setVisible(false);
//                        UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().prodottiPresenti());
//                        SessionManager.getInstance().getSession().put("finestra_utente",fr);
//                }
//
//            }
        });


        ArrayList<Categoria> categorieList = CategoriaBusiness.getInstance().categoriePresenti();

        Iterator i = categorieList.iterator();

        while(i.hasNext()){

            Categoria cat = (Categoria)i.next();
            JMenuItem item =new JMenuItem(cat.getNomecategoria());
            categorie.add(item);

            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    _this.setVisible(false);
                   // _this.setProdottiList(ProdottoDAO.getInstance().findByCategoria(cat.getNomecategoria()));
                   // _this.mostraProdotti(centro);
                 //   _this.setVisible(true);

                   UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().prodottiPerCategoria(cat));
                    SessionManager.getInstance().getSession().put("finestra_utente",fr);
                   //fr.setVisible(false);
                  // fr.setProdottiList(ProdottoDAO.getInstance().findByCategoria(cat.getNomecategoria()));

                  // fr.setVisible(true);

                }
            });



        }

        try{
            mostraProdotti(centro);
        } catch (NullPointerException e){
            centro.add(new JLabel("Non sono presenti prodotti in questa categoria."));
        }






        setJMenuBar(bar);

        setSize(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void mostraProdotti(JPanel p){

        Iterator j = prodottiList.iterator();

        while(j.hasNext()){

            Prodotto prod = (Prodotto)j.next();

//            InfoProdottoFrame prfr=new InfoProdottoFrame();
//            prfr.setVisible(false);
            SessionManager.getInstance().getSession().put("prodotto_aperto",prod);
            BottoneConImg b = new BottoneConImg(prod.getImgUrl(),prod.getNome(),Float.toString(prod.getPrezzo()));
//            b.addHierarchyListener(new HierarchyListener() {
//                @Override
//                public void hierarchyChanged(HierarchyEvent e) {
//                    if(e.getChanged() instanceof JButton){
//                    UtenteFrame utfr = (UtenteFrame)SessionManager.getInstance().getSession().get("finestra_utente");
//                    utfr.setVisible(false);
//
//                }
//                }
//
//            });

            p.add(b);


        }

    }
}
