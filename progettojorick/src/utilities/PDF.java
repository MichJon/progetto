package utilities;

import it.progettojorick.business.SessionManager;
import it.progettojorick.model.Utente;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDF {

    //singleton design pattern
    private static PDF instance;

    public static PDF getInstance(){
        if (instance==null)
            instance = new PDF();
        return instance;
    }

    Utente u = (Utente) SessionManager.getInstance().getSession().get("utente");

    public void creaPDF()throws IOException {

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
        contentStream.newLineAtOffset(150, 700);

        String text = "Grazie per aver acquistato da noi.";

        //Adding text in the form of string
        contentStream.showText(text);

        //Ending the content stream
        contentStream.endText();



        contentStream.close();




        document.save("./PDF's/"+u.getEmailUtente()+".pdf");
        document.close();
    }
}
