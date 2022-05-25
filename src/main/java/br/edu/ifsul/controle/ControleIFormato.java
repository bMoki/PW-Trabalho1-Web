package br.edu.ifsul.controle;

import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleFormato")
@ViewScoped
public class ControleIFormato implements Serializable{
    
    @EJB
    private FormatoDAO<Formato> dao;
    private Formato objeto;
    
    public ControleIFormato(){
        
    }
    
    public String listar(){
        return "/privado/formato/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Formato();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.getObjectByID(id);        
        }catch(Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: "+ Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try{
            objeto = dao.getObjectByID(id); 
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
        }catch(Exception e){
            Util.mensagemInformacao("Erro ao remover objeto: "+ Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        }catch(Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: "+ Util.getMensagemErro(e));
        }
    }
    
    public Formato getObjeto() {
        return objeto;
    }

    public void setObjeto(Formato objeto) {
        this.objeto = objeto;
    }

    public FormatoDAO<Formato> getDao() {
        return dao;
    }

    public void setDao(FormatoDAO<Formato> dao) {
        this.dao = dao;
    }
}
