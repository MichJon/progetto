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
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
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
//        RichiestaOrdine trovata = new RichiestaOrdine();
//            Iterator i = listarichieste.iterator();
//            while (i.hasNext()) {
//                RichiestaOrdine richiesta = (RichiestaOrdine) i.next();
//                if (idordine == richiesta.getIdRichiesta())
//                    trovata = richiesta;
//            }
        RichiestaOrdine trovata = RichiestaOrdineBusiness.getInstance().trovaRichiesta(idordine);
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

            PDImageXObject pdImage = PDImageXObject.createFromFile("./images/LOGO2.jpg", document);


            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.drawImage(pdImage, 25, 650, 500, 100);
           // contentStream.drawIm

            //Begin the Content stream
            contentStream.beginText();

            //Setting the font to the Content stream
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            //Setting the position for the line
            contentStream.newLineAtOffset(25, 600);

            //Setting text Leading
            contentStream.setLeading(14.5f);

            String intro = "Gentile utente " + u.getNome() + " " + u.getCognome() + ", grazie per aver acquistato da noi!";

            //Adding text in the form of string
            contentStream.showText(intro);

            //For each line add "newline"
            contentStream.newLine();
//
            contentStream.showText("Nome");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Quantità");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Prezzo");
            contentStream.newLineAtOffset(-200, 0);
            contentStream.newLine();
            contentStream.newLine();


            Iterator j = prodotticontenuti.iterator();
            float tot=0;
            while (j.hasNext()) {
                Prodotto p = (Prodotto) j.next();
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                float prezzoScont = p.getPrezzo()-p.getPrezzo()*p.getSconto()/100;

                String prezzoScontato = df.format(prezzoScont);

//                contentStream.showText(p.getNome() + "   " + p.getPrezzo() + "    " + ProdottoBusiness.getInstance().getQuantita(c, p));
//                contentStream.newLineAtOffset();
                contentStream.showText(p.getNome());
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(Integer.toString(ProdottoBusiness.getInstance().getQuantita(c, p)));
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("€"+prezzoScontato);
                contentStream.newLineAtOffset(-200, 0);
                contentStream.newLine();
                tot+=prezzoScont*ProdottoBusiness.getInstance().getQuantita(c, p);
            }

            contentStream.showText("----------------------------------------------------------------");
            contentStream.newLine();
            contentStream.newLineAtOffset(160,0);
            contentStream.showText("Totale:  €"+tot);
            //Ending the content stream
            contentStream.endText();
            contentStream.close();
            document.save("./PDF's/" + u.getEmailUtente() + "#" + trovata.getIdRichiesta() + ".pdf");
            document.close();
        }
}
