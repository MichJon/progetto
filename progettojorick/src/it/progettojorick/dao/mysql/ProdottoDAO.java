package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IProdottoDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Categoria;
import it.progettojorick.model.Prodotto;

import java.util.ArrayList;
import java.util.Iterator;

public class ProdottoDAO implements IProdottoDAO {

    //singleton design pattern
    private static ProdottoDAO instance;

    public static ProdottoDAO getInstance(){
        if (instance==null)
            instance = new ProdottoDAO();
        return instance;
    }


    @Override
    public Prodotto findByName(String nome){
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM prodotto WHERE nome_prodotto = '"+nome+"';");
        if (risultato.size()==0) return null;

        Prodotto p=new Prodotto();

        String[] riga= risultato.get(0);
        p.setNome(riga[0]);
        p.setDescrizione(riga[1]);
        p.setPrezzo(Float.parseFloat(riga[2]));
        p.setDisponibilita(Integer.parseInt(riga[3]));
        p.setCategoria(CategoriaDAO.getInstance().findByName(riga[4]));
        p.setProduttore(ProduttoreDAO.getInstance().findById(Integer.parseInt(riga[5])));
        p.setDistributore(DistributoreDAO.getInstance().findById(Integer.parseInt(riga[6])));
        p.setProdottiContenuti(ProdottoDAO.getInstance().findProdottiContenuti(riga[0]));
        p.setImgUrl(riga[7]);
        p.setSconto(Integer.parseInt(riga[8]));

        return p;


    }

    @Override
    public void insertProdotto(Prodotto p) {

        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO prodotto (nome_prodotto,descrizione," +
                        "prezzo,disponibilita,categoria_nome_categoria, produttore_idproduttore," +
                        "distributore_iddistributore, url_immagine, sconto)" +
                        "VALUES ('"+p.getNome()+"','"+p.getDescrizione()+"','"+p.getPrezzo()+"','"+p.getDisponibilita()+"','"+
                                    p.getCategoria().getNomecategoria()+"','"+p.getProduttore().getId()+"','"+
                                    p.getDistributore().getId()+"','"+p.getImgUrl()+"','"+p.getSconto()+"');");


    }



    @Override
    public ArrayList<Prodotto> findAll() {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM prodotto");
        if (risultato.size()==0) return null;
        ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Prodotto p = new Prodotto();
            p.setNome(riga[0]);
            p.setDescrizione(riga[1]);
            p.setPrezzo(Float.parseFloat(riga[2]));
            p.setDisponibilita(Integer.parseInt(riga[3]));
            p.setCategoria(CategoriaDAO.getInstance().findByName(riga[4]));
            p.setProduttore(ProduttoreDAO.getInstance().findById(Integer.parseInt(riga[5])));
            p.setDistributore(DistributoreDAO.getInstance().findById(Integer.parseInt(riga[6])));
            p.setProdottiContenuti(ProdottoDAO.getInstance().findProdottiContenuti(riga[0]));
            p.setImgUrl(riga[7]);
            p.setSconto(Integer.parseInt(riga[8]));
            listaProdotti.add(p);
        }
        return listaProdotti;
    }


    public ArrayList<Prodotto> findByProduttore(int idProduttore){

        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM prodotto WHERE produttore_idproduttore="+idProduttore);
        if (risultato.size()==0) return null;
        ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Prodotto p = new Prodotto();
            p.setNome(riga[0]);
            p.setDescrizione(riga[1]);
            p.setPrezzo(Float.parseFloat(riga[2]));
            p.setDisponibilita(Integer.parseInt(riga[3]));
            p.setCategoria(CategoriaDAO.getInstance().findByName(riga[4]));
            p.setProduttore(ProduttoreDAO.getInstance().findById(Integer.parseInt(riga[5])));
            p.setDistributore(DistributoreDAO.getInstance().findById(Integer.parseInt(riga[6])));
            p.setProdottiContenuti(ProdottoDAO.getInstance().findProdottiContenuti(riga[0]));
            p.setImgUrl(riga[7]);
            p.setSconto(Integer.parseInt(riga[8]));
            listaProdotti.add(p);
        }
        return listaProdotti;
    }

  /*  public ArrayList<Prodotto> findByProduttore(int idProduttore){

        ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();

        ArrayList<Prodotto> all= findAll();

        Iterator<Prodotto> i = all.iterator();

        while (i.hasNext()) {
            Prodotto p = i.next();
            if (p.getIdproduttore()==idProduttore)
                listaProdotti.add(p);
        }


    }*/

  public ArrayList<Prodotto> findProdottiContenuti (String nome){

      ArrayList<String []>  risNomiProdotti= DbConnection.getInstance().eseguiQuery("SELECT prodotto_nome_prodotto_contenuto FROM prodotto_has_prodotto WHERE prodotto_nome_prodotto_composto = '" +nome +"';");
      ArrayList<Prodotto> prodottiCont = new ArrayList<Prodotto>();
      if (risNomiProdotti.size()==0) return null;
      Iterator<String[]> i = risNomiProdotti.iterator();

      while (i.hasNext()){
          String[] riga = i.next();
          Prodotto prodottoContenuto = ProdottoDAO.getInstance().findByName(riga[0]);
          prodottiCont.add(prodottoContenuto);
      }
      return prodottiCont;
  }


    public ArrayList<Prodotto> findByCategoria(String categoria){

        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM prodotto WHERE categoria_nome_categoria= '"+categoria+"';");
        if (risultato.size()==0) return null;
        ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Prodotto p = new Prodotto();
            p.setNome(riga[0]);
            p.setDescrizione(riga[1]);
            p.setPrezzo(Float.parseFloat(riga[2]));
            p.setDisponibilita(Integer.parseInt(riga[3]));
            p.setCategoria(CategoriaDAO.getInstance().findByName(riga[4]));
            p.setProduttore(ProduttoreDAO.getInstance().findById(Integer.parseInt(riga[5])));
            p.setDistributore(DistributoreDAO.getInstance().findById(Integer.parseInt(riga[6])));
            p.setProdottiContenuti(ProdottoDAO.getInstance().findProdottiContenuti(riga[0]));
            p.setImgUrl(riga[7]);
            p.setSconto(Integer.parseInt(riga[8]));
            listaProdotti.add(p);
        }
        return listaProdotti;
    }

    public ArrayList<Prodotto> findByCarrello(int id){

        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM prodotto WHERE carrello_idcarrello= "+id);
        if (risultato.size()==0) return null;
        ArrayList<Prodotto> listaProdotti= new ArrayList<Prodotto>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Prodotto p = new Prodotto();
            p.setNome(riga[0]);
            p.setDescrizione(riga[1]);
            p.setPrezzo(Float.parseFloat(riga[2]));
            p.setDisponibilita(Integer.parseInt(riga[3]));
            p.setCategoria(CategoriaDAO.getInstance().findByName(riga[4]));
            p.setProduttore(ProduttoreDAO.getInstance().findById(Integer.parseInt(riga[5])));
            p.setDistributore(DistributoreDAO.getInstance().findById(Integer.parseInt(riga[6])));
            p.setProdottiContenuti(ProdottoDAO.getInstance().findProdottiContenuti(riga[0]));
            p.setImgUrl(riga[7]);
            p.setSconto(Integer.parseInt(riga[8]));
            listaProdotti.add(p);
        }
        return listaProdotti;
    }

   public void deleteProdotto(String nome){

        DbConnection.getInstance().eseguiAggiornamento("DELETE FROM prodotto WHERE nome_prodotto='"+nome+"';");

    }

    public String getQuantita(int idCarrello, String nomeProdotto){

        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT quantita FROM carrello_has_prodotto WHERE prodotto_nome_prodotto = '"+nomeProdotto+"' AND " +
                "carrello_idcarrello = "+idCarrello);
        if (risultato.size()==0) return null;

        return risultato.get(0)[0];

    }

    public void setQuantita(String quantita, int idCarrello, String nomeProdotto){

        DbConnection.getInstance().eseguiAggiornamento("UPDATE carrello_has_prodotto SET quantita = '"+quantita+"' WHERE prodotto_nome_prodotto = '"+nomeProdotto+"' AND "  +
                "carrello_idcarrello = "+idCarrello);

    }

    public void insertProdottiContenuti(String nomeProdComposto, String nomeProdContenuto){

        DbConnection.getInstance().eseguiAggiornamento("INSERT INTO prodotto_has_prodotto (prodotto_nome_prodotto_composto,prodotto_nome_prodotto_contenuto) " +
                "VALUES ('"+nomeProdComposto+"','"+nomeProdContenuto+"');");

    }

    public void setDisponibilita(String nomeProdotto,int disponibilita){

        DbConnection.getInstance().eseguiAggiornamento("UPDATE prodotto SET disponibilita = "+disponibilita+" WHERE nome_prodotto = '"+nomeProdotto+"';");

    }

    public void setPrezzo(String nomeProdotto,float prezzo){

        DbConnection.getInstance().eseguiAggiornamento("UPDATE prodotto SET prezzo = "+prezzo+" WHERE nome_prodotto = '"+nomeProdotto+"';");

    }

    public void setSconto(String nomeProdotto,int sconto){

        DbConnection.getInstance().eseguiAggiornamento("UPDATE prodotto SET sconto = "+sconto+" WHERE nome_prodotto = '"+nomeProdotto+"';");

    }


}
