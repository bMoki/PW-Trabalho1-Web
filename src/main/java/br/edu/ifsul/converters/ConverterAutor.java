package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Autor;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "converterAutor")
@RequestScoped
public class ConverterAutor implements Serializable, Converter {

    @PersistenceContext(unitName = "Livraria-WebPU")
    protected EntityManager em;    
    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um autor")){
            return null;
        }
        return em.find(Autor.class, Integer.parseInt(string));
    }

   
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Autor obj = (Autor) t;
        return obj.getId().toString();
    }

}