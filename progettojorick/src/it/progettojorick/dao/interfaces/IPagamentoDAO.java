package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Pagamento;

public interface IPagamentoDAO extends IBaseDAO<Pagamento> {

    Pagamento findByNumcarta (long numCarta);
    void insertPagamento (long numCarta, String circuito, int codSicurezza, String dataScadenza);
    void insertPagamentoUtente(String email, long numCarta);

}
