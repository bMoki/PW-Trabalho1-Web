package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Livraria;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class LivrariaDAO<TIPO> extends DAOGenerico<Livraria> implements Serializable{
    public LivrariaDAO(){
        super();
        classePersistente = Livraria.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
     @Override
    public Livraria getObjectByID(Object id) throws Exception {
        Livraria obj = em.find(Livraria.class, id);
        obj.getCatalogos().size();
        return obj;
    }
}
