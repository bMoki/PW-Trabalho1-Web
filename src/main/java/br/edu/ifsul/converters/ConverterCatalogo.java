package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Catalogo;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Named(value = "converterCatalogo")
@RequestScoped
public class ConverterCatalogo implements Serializable, Converter{

    @PersistenceContext(unitName = "Livraria-WebPU")
    private EntityManager em;
   
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um catalogo")){
            return null;
        }
        return em.find(Catalogo.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Catalogo obj = (Catalogo) t;
        return obj.getId().toString();
    }

}