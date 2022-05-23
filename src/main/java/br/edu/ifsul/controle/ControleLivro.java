package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value="controleLivro")
@ViewScoped
public class ControleLivro implements Serializable{
    
    @EJB
    private LivroDAO<Livro> dao;
    private Livro objeto;

    public ControleLivro() {
    }

    public String listar(){
        return "/privado/livro/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Livro();
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
            if(objeto.getIsbn() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        }catch(Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: "+ Util.getMensagemErro(e));
        }
    }
    
    public LivroDAO<Livro> getDao() {
        return dao;
    }

    public void setDao(LivroDAO<Livro> dao) {
        this.dao = dao;
    }

    public Livro getObjeto() {
        return objeto;
    }

    public void setObjeto(Livro objeto) {
        this.objeto = objeto;
    }
    
    
}
