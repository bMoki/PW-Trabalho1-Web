package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "converterUsuario")
@RequestScoped
public class ConverterUsuario implements Serializable, Converter {

    @PersistenceContext(unitName = "Livraria-WebPU")
    protected EntityManager em;    
    
   
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um usuario")){
            return null;
        }
        return em.find(Usuario.class, string);
    }

   
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Usuario obj = (Usuario) t;
        return obj.getNomeUsuario();
    }

}