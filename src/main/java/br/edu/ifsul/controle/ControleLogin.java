package br.edu.ifsul.controle;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value="controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
    private Usuario usuarioAutenticado;
    @EJB
    private UsuarioDAO<Usuario> dao;
    private String usuario;
    private String senha;
    
    public ControleLogin(){

    }
    
    public String irTelaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public Boolean hasPermissao(String string){
        Permissao p = new Permissao();
        p.setNome(string);
        return usuarioAutenticado.getPermissoes().contains(p);
    }
   
    public String efetuarLogin(){
        try{
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(usuario, senha);
            
            if(request.getUserPrincipal() != null){
                usuarioAutenticado = dao.getObjectByID(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Usuario autenticado com sucesso");
                usuario = "";
                senha = "";
            }
            
            return "/index?faces-redirect=true";
        }catch(Exception e){
            Util.mensagemErro("Erro no login: " +Util.getMensagemErro(e));
            return "/login?faces-redirect=true";
        }
    }
    
     public String efetuarLogout(){
        try{
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest)
                    FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
             Util.mensagemInformacao("Logout realizado com sucesso");
            return "/index?faces-redirect=true";
        }catch(Exception e){
            Util.mensagemErro("Erro no logout: " +Util.getMensagemErro(e));
            return "/index?faces-redirect=true";
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public UsuarioDAO<Usuario> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuario> dao) {
        this.dao = dao;
    }

 
    
    
    
}


