package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Livro;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class LivroDAO<TIPO> extends DAOGenerico<Livro> implements Serializable {
    public LivroDAO(){
        super();
        classePersistente = Livro.class;
        listaOrdem.add(new Ordem("isbn", "ISBN", "like"));
        listaOrdem.add(new Ordem("titulo", "Titulo", "like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }

    @Override
    public Livro getObjectByID(Object id) throws Exception {
        Livro livro = em.find(Livro.class, id);
        livro.getAutores().size();
        return livro;
    }
    
    public void remover(String id) throws Exception {
        Livro obj = (Livro) em.find(classePersistente, id);
        em.remove(obj);
    }
    
    
    
}
