package it.progettojorick.dao.mysql;

import it.progettojorick.dao.interfaces.IPersonaDAO;
import it.progettojorick.dbInterface.DbConnection;
import it.progettojorick.model.Amministratore;
import it.progettojorick.model.GestoreCatalogo;
import it.progettojorick.model.Persona;
import it.progettojorick.model.Utente;

import java.util.ArrayList;

public class PersonaDAO implements IPersonaDAO {

    //singleton design pattern
    private static PersonaDAO instance;

    public static PersonaDAO getInstance(){
        if (instance==null)
            instance = new PersonaDAO();
        return instance;
    }

    @Override
    public Persona findUsersByUsernameAndPassword(String email, byte[] password) {
        Persona p=null;

        String query = "SELECT * FROM persona WHERE email = '"+email+"' AND password = '"+ new String(password)+"';";
        ArrayList<String[]> ris= DbConnection.getInstance().eseguiQuery(query);

        if (ris.size()!=0){
            //esiste un utente!!!
            String[] utente = ris.get(0);

            //vediamo se è un amministratore
            Amministratore a = AmministratoreDAO.getInstance().findByEmail(email);
            if (a==null){
                //allora sarà un gestore?...
                GestoreCatalogo g =  GestoreCatalogoDAO.getInstance().findByEmail(email);
                if (g!=null){
                    p=g;
                }
                else{
                    Utente u= UtenteDAO.getInstance().findByEmail(email);
                    if (u!=null)
                        p=u;
                }
            }
            else {
                p=a;
            }
            if(p!=null){
                p.setNome(utente[1]);
                p.setCognome(utente[2]);
            }
        }

        return p;
    }

    @Override
    public ArrayList<Persona> findAll() {
        return null;
    }
}
