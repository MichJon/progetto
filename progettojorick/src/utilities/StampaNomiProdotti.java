package utilities;

import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;

import java.util.ArrayList;
import java.util.Iterator;

public class StampaNomiProdotti {
    private static StampaNomiProdotti instance;

    public static StampaNomiProdotti getInstance(){
        if (instance==null)
            instance = new StampaNomiProdotti();
        return instance;
    }

    ArrayList<Prodotto> prod = new ArrayList<Prodotto>();
    Carrello c;

    public String Stampa (ArrayList<Prodotto> prod,Carrello c){

        this.prod=prod;
        this.c=c;

        String stringa = new String();

        Iterator i = prod.iterator();



        while (i.hasNext()){

             Prodotto p = (Prodotto) i.next();
//             int quantita = ProdottoBusiness.getInstance().getQuantita(c,p);

            stringa = stringa+p.getNome()+", ";//+" (quantità: "+quantita+") , ";

        }
        return stringa;

    }


}
