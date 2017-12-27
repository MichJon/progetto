package utilities;

import it.progettojorick.business.CarrelloBusiness;
import it.progettojorick.business.ProdottoBusiness;
import it.progettojorick.business.RichiestaOrdineBusiness;
import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Carrello;
import it.progettojorick.model.Prodotto;
import it.progettojorick.model.RichiestaOrdine;
import it.progettojorick.model.Utente;
import it.progettojorick.view.PROVAframe;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class PDF {

    //singleton design pattern
    private static PDF instance;

    public static PDF getInstance(){
        if (instance==null)
            instance = new PDF();
        return instance;
    }

    Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");
    ArrayList<RichiestaOrdine> listarichieste = RichiestaOrdineBusiness.getInstance().richiesteOrdineUtente(u);

    public void creaPDF(int idordine)throws IOException {
        RichiestaOrdine trovata = new RichiestaOrdine();
        Iterator i = listarichieste.iterator();
        while (i.hasNext()){
            RichiestaOrdine richiesta = (RichiestaOrdine)i.next();
            if(idordine==richiesta.getIdRichiesta())
                trovata = richiesta;
        }
        Carrello c = trovata.getCarrello();
        //ArrayList<String> nomi = new ArrayList<>();
        //ArrayList<Integer> quantita = new ArrayList<>();
        //ArrayList<Float> prezzi = new ArrayList<>();
        ArrayList<Prodotto> prodotticontenuti = new ArrayList<>();
        prodotticontenuti = c.getProdottiContenuti();


        PDDocument document = new PDDocument();
        PDPage my_page = new PDPage();
        document.addPage(my_page);
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        //Setting the position for the line
        contentStream.newLineAtOffset(25, 750);

        //Setting text Leading
        contentStream.setLeading(14.5f);

        String intro = "Gentile utente "+u.getNome()+" "+u.getCognome()+" Grazie per aver acquistato da noi.";

        //Adding text in the form of string
        contentStream.showText(intro);

        //For each line add "newline"
        contentStream.newLine();
        contentStream.showText("Nome               Prezzo    Quantità");
        contentStream.newLine();

        Iterator j = prodotticontenuti.iterator();

        while (j.hasNext()){
            Prodotto p = (Prodotto) j.next();

            contentStream.showText(p.getNome()+"   "+p.getPrezzo()+"    "+ ProdottoBusiness.getInstance().getQuantita(c,p));
            contentStream.newLine();
        }

        //Ending the content stream
        contentStream.endText();
        contentStream.close();
        document.save("./PDF's/"+u.getEmailUtente()+".pdf");
        document.close();
    }
}
