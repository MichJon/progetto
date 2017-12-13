package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IPagamentoDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Pagamento;

import java.util.ArrayList;
import java.util.Iterator;

public class PagamentoDAO implements IPagamentoDAO {

    //singleton design pattern
    private static PagamentoDAO instance;

    public static PagamentoDAO getInstance(){
        if (instance==null)
            instance = new PagamentoDAO();
        return instance;
    }


    @Override
    public Pagamento findByNumcarta(int numCarta) {
        ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM pagamento WHERE num_carta="+numCarta);
        if (risultato.size()==0) return null;

        Pagamento p=new Pagamento();

        String[] riga= risultato.get(0);
        p.setNumCarta(Integer.parseInt(riga[0]));
        p.setCircuito(riga[1]);
        p.setCodSicurezza(Integer.parseInt(riga[2]));
        p.setDataScadenza(riga[3]);

        return p;
    }

    @Override
    public ArrayList<Pagamento> findAll() {
            ArrayList<String []> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM pagamento");
        if (risultato.size()==0) return null;
            ArrayList<Pagamento> listaPagamenti = new ArrayList<Pagamento>();

            Iterator<String[]> i = risultato.iterator();

            while(i.hasNext()) {
                String[] riga = i.next();
                Pagamento p = new Pagamento();
                p.setNumCarta(Integer.parseInt(riga[0]));
                p.setCircuito(riga[1]);
                p.setCodSicurezza(Integer.parseInt(riga[2]));
                p.setDataScadenza(riga[3]);
                listaPagamenti.add(p);
            }
            return listaPagamenti;
    }
}
