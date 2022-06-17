package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class CatalogoDAO<TIPO> extends DAOGenerico<Catalogo> implements Serializable{
    public CatalogoDAO(){
        super();
        classePersistente = Catalogo.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
}
