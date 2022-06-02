package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class CatalogoDAO<TIPO> extends DAOGenerico<Catalogo> implements Serializable{
    public CatalogoDAO(){
        super();
        classePersistente = Catalogo.class;
    }
    
}
