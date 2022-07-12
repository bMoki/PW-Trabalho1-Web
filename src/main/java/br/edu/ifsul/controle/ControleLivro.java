package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AutorDAO;
import br.edu.ifsul.dao.CatalogoDAO;
import br.edu.ifsul.dao.FormatoDAO;
import br.edu.ifsul.dao.IdiomaDAO;
import br.edu.ifsul.dao.LivroDAO;
import br.edu.ifsul.modelo.Autor;
import br.edu.ifsul.modelo.Catalogo;
import br.edu.ifsul.modelo.Formato;
import br.edu.ifsul.modelo.Idioma;
import br.edu.ifsul.modelo.Livro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value="controleLivro")
@ViewScoped
public class ControleLivro implements Serializable{
    
    @EJB
    private LivroDAO<Livro> dao;
    private Livro objeto;
    
    @EJB
    private FormatoDAO<Formato> daoFormato;
    
    @EJB
    private IdiomaDAO<Idioma> daoIdioma;
    
    @EJB
    private CatalogoDAO<Catalogo> daoCatalogo;
    
    @EJB
    private AutorDAO<Autor> daoAutor;
    private Autor autor;
    private int abaAtiva;

    public ControleLivro() {
    }
    
    public void removerAutor(Autor obj) {
        objeto.getAutores().remove(obj);
        Util.mensagemInformacao("Autor removido com sucesso!");
    }
    
    public void adicionarAutor() {
        System.out.println("Chamou metodo");
        if (!objeto.getAutores().contains(autor)) {
            objeto.getAutores().add(autor);
            Util.mensagemInformacao("Autor adicionado com sucesso!");
        } else {
            Util.mensagemErro("Livro já possui este autor");
        }
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
            //objeto = dao.getObjectByID(id); 
            dao.remover(id.toString());
            Util.mensagemInformacao("Objeto removido com sucesso");
        }catch(Exception e){
            Util.mensagemInformacao("Erro ao remover objeto: "+ Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try{
            if(objeto.getDataCadastro() == null){
                Calendar today = Calendar.getInstance();
                objeto.setDataCadastro(today);
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        }catch(Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: "+ Util.getMensagemErro(e));
        }
    }

    public IdiomaDAO<Idioma> getDaoIdioma() {
        return daoIdioma;
    }

    public void setDaoIdioma(IdiomaDAO<Idioma> daoIdioma) {
        this.daoIdioma = daoIdioma;
    }

    public CatalogoDAO<Catalogo> getDaoCatalogo() {
        return daoCatalogo;
    }

    public void setDaoCatalogo(CatalogoDAO<Catalogo> daoCatalogo) {
        this.daoCatalogo = daoCatalogo;
    }
    
    public FormatoDAO<Formato> getDaoFormato() {
        return daoFormato;
    }

    public void setDaoFormato(FormatoDAO<Formato> daoFormato) {
        this.daoFormato = daoFormato;
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

    public AutorDAO<Autor> getDaoAutor() {
        return daoAutor;
    }

    public void setDaoAutor(AutorDAO<Autor> daoAutor) {
        this.daoAutor = daoAutor;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    
    
}
