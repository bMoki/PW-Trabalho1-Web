package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleAutor")
@ViewScoped
public class ControleAutor implements Serializable{
    
    @EJB
    private AutorDAO<Autor> dao;
    private Autor objeto;
    
    public ControleAutor(){
        
    }
    
    public void imprimeAutores(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioAutores", parametros, dao.getListaTodos());
    }
    
    public String listar(){
        return "/privado/autor/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Autor();
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
    
    public Autor getObjeto() {
        return objeto;
    }

    public void setObjeto(Autor objeto) {
        this.objeto = objeto;
    }

    public AutorDAO<Autor> getDao() {
        return dao;
    }

    public void setDao(AutorDAO<Autor> dao) {
        this.dao = dao;
    }
}
