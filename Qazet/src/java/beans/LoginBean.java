/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import pojoes.Admin;

/**
 *
 * @author Farid
 */
@ManagedBean(name="LB")
@SessionScoped
public class LoginBean implements Serializable{
 private Admin admin =new Admin();
 private GeneralSessionBean gsb=new GeneralSessionBean();
 private boolean checkLogin=false;
 private String password;
 private String username;
    public LoginBean() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public boolean isCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    
    public String login(){
    admin=gsb.adminLogin(username, password);
     if (admin == null) {
            
            return (username = password = null);
        } else {
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("v1",password);
           if(admin.isEditor()){
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("v2","true");
           }
           //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("password",admin.isEditor());
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", admin.getId());
       return "index.xhtml?faces-redirect=true";
        }
   
    }
    
   
    
    
    
    
    
    public void loginn(){
    admin=gsb.adminLogin(admin.getName(), admin.getPassword());
    if(gsb.adminLogin(admin.getName(), admin.getPassword()).equals(null)){
    checkLogin=false;
    }
    else {
    checkLogin=true;
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpSession session = request.getSession();
    }
    
 
    }
    
      public String logout() {
          FacesContext facesContext = FacesContext.getCurrentInstance();
  HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
  httpSession.invalidate();
        System.out.println("ferid link isleyir");
        return "login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return admin != null;
    }
    
    
    
}
