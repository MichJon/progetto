package it.progettojorick.dao.interfaces;

import it.progettojorick.model.Pagamento;

public interface IPagamentoDAO extends IBaseDAO<Pagamento> {

    Pagamento findByNumcarta (int numCarta);

}
