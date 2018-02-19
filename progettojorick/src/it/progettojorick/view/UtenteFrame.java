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


       // this.setBackground(Color.white);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2)-x/2, (dim.height/2)-y/2);

//        JPanel immagine = new JPanel(new BorderLayout());
//        ImageIcon img = new ImageIcon("./images/LOGO2.jpg");
//        Image image = img.getImage().getScaledInstance(500,150,0);
//        ImageIcon newImg = new ImageIcon(image);
//        immagine.add(new JLabel(newImg), BorderLayout.CENTER);
//


        c.setLayout(new BorderLayout());
        JPanel nord = new JPanel(new FlowLayout());
        nord.setBackground(Color.white);
        JPanel centro = new JPanel(new FlowLayout());
        centro.setBackground(Color.white);
        JPanel sud = new JPanel();
        sud.setBackground(Color.white);
//        Color col=new Color(1f,0f,0f,0f );
//        centro.setBackground(col);
//        c.add(immagine, BorderLayout.CENTER);

        c.add(nord,BorderLayout.NORTH);
        c.add(centro,BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);


        JButton panieri = new JButton("Panieri");
        panieri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (u!=null){
                _this.setVisible(false);
                new ElencoPanieriView();}
                else {
                    JOptionPane.showMessageDialog(null, "Effettua il login prima di continuare!");
                    _this.setVisible(false);
                    new LoginFrame();
                }
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
               if (u!=null) {
                   _this.setVisible(false);
                   new OrdiniUtenteFrame();
               }
               else {
                   JOptionPane.showMessageDialog(null, "Effettua il login prima di continuare!");
                   _this.setVisible(false);
                   new LoginFrame();
               }
            }
        });
        sud.add(logout);
        sud.add(panieri);
        sud.add(carrello);
        sud.add(btnOrdini);
        carrello.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (u!=null) {
                   _this.setVisible(false);
                   try {
                       SessionManager.getInstance().getSession().put("finestra_carrello", new CarrelloFrame());
                   } catch (NullPointerException ex) {
                       JOptionPane.showMessageDialog(null, "Non sono presenti prodotti nel carrello");
                       _this.setVisible(true);
                   }
               }
               else {
                   JOptionPane.showMessageDialog(null, "Effettua il login prima di continuare!");
                   _this.setVisible(false);
                   new LoginFrame();
               }
            }
        });
        JMenuBar bar = new JMenuBar();
        JMenu mostraTutti = new JMenu("Mostra tutti");
        JMenu categorie = new JMenu("Categorie");
        JMenu fdp = new JMenu("Fasce di prezzo");
        bar.add(mostraTutti);

        bar.add(categorie);
        bar.add(fdp);
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

        JMenuItem fascia1 = new JMenuItem("€0 - €10");
        JMenuItem fascia2 = new JMenuItem("€10 - €20");
        JMenuItem fascia3 = new JMenuItem("€20 - €30");
        JMenuItem fascia4 = new JMenuItem("€30 +");

        fdp.add(fascia1);
        fdp.add(fascia2);
        fdp.add(fascia3);
        fdp.add(fascia4);

        fascia1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().trovaPerFasciaDiPrezzo(0,10));
                SessionManager.getInstance().getSession().put("finestra_utente",fr);
            }
        });

        fascia2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().trovaPerFasciaDiPrezzo(10,20));
                SessionManager.getInstance().getSession().put("finestra_utente",fr);
            }
        });

        fascia3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().trovaPerFasciaDiPrezzo(20,30));
                SessionManager.getInstance().getSession().put("finestra_utente",fr);
            }
        });

        fascia4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _this.dispose();
                UtenteFrame fr = new UtenteFrame(ProdottoBusiness.getInstance().trovaPerFasciaDiPrezzo(30));
                SessionManager.getInstance().getSession().put("finestra_utente",fr);
            }
        });

        ArrayList<Categoria> categorieList = new ArrayList<Categoria>();
        if (CategoriaBusiness.getInstance().categoriePresenti()!=null)
        categorieList = CategoriaBusiness.getInstance().categoriePresenti();


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
            centro.add(new JLabel("Non sono presenti prodotti."));
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
