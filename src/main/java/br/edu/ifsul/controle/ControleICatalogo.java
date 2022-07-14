package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleCatalogo")
@ViewScoped
public class ControleICatalogo implements Serializable{
    
    @EJB
    private CatalogoDAO<Catalogo> dao;
    private Catalogo objeto;
    
    private Livro livro;
    private Boolean novoLivro;
    
    public ControleICatalogo(){
        
    }
    
    public void novoLivro(){
        livro = new Livro();
        novoLivro = true;
    }
    
    public void alterarLivro(int index){
        livro = objeto.getLivros().get(index);
        novoLivro = false;
    }
    
    public void salvarLivro(){
        if(novoLivro){
            objeto.adicionarLivro(livro);
        }
        Util.mensagemInformacao("Livro adicionado ou alterado com sucesso!");
    }
    
    public void removerLivro(int index){
        objeto.removerLivro(index);
        Util.mensagemInformacao("Livro removido com sucesso!");
    }
    
    public String listar(){
        return "/privado/catalogo/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Catalogo();
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
    
    public Catalogo getObjeto() {
        return objeto;
    }

    public void setObjeto(Catalogo objeto) {
        this.objeto = objeto;
    }

    public CatalogoDAO<Catalogo> getDao() {
        return dao;
    }

    public void setDao(CatalogoDAO<Catalogo> dao) {
        this.dao = dao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Boolean getNovoLivro() {
        return novoLivro;
    }

    public void setNovoLivro(Boolean novoLivro) {
        this.novoLivro = novoLivro;
    }
}
