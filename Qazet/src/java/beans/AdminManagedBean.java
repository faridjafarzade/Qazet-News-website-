/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import pojoes.Admin;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "AdMB")
@SessionScoped
public class AdminManagedBean implements Serializable {

    private Admin admin = new Admin();
    private Admin selectedAdmin = new Admin();
    private Admin currentAdmin = new Admin();
    private String password1;
    private String password2;
    private String password3;
    private String password4;
    private String password5;
    private String username;
    private String oldPassword;
    private boolean voep = false;
    private GeneralSessionBean gsb = new GeneralSessionBean();
    private List<Admin> Admins = gsb.findAdminEntities();

    public AdminManagedBean() {
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    public String getPassword5() {
        return password5;
    }

    public void setPassword5(String password5) {
        this.password5 = password5;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getSelectedAdmin() {
        return selectedAdmin;
    }

    public void setSelectedAdmin(Admin selectedAdmin) {
        this.selectedAdmin = selectedAdmin;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword3() {
        return password3;
    }

    public void setPassword3(String password3) {
        this.password3 = password3;
    }

    public String getPassword4() {
        return password4;
    }

    public void setPassword4(String password4) {
        this.password4 = password4;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public boolean isVoep() {
        return voep;
    }

    public void setVoep(boolean voep) {
        this.voep = voep;
    }

    public List<Admin> getAdmins() {
        return Admins;
    }

    public void setAdmins(List<Admin> Admins) {
        this.Admins = Admins;
    }

    public void creatAdmin() {

        boolean checkResult = true;
        FacesContext context = FacesContext.getCurrentInstance();

        if (admin.getName().trim().equals("") || admin.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "Thera is not name"));
            checkResult = false;
        } else if (gsb.getAdminCountWithName(admin.getName().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Name", "This name is used"));
            checkResult = false;
        }

        if (admin.getEmail().trim().equals("") || admin.getEmail() == null) {
            context.addMessage(null, new FacesMessage("Email", "Thera is not email"));
            checkResult = false;
        } else if (gsb.getAdminCountWithEmail(admin.getEmail().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Email", "This email is used"));
            checkResult = false;
        }

        if (password1.trim().equals("") || password1 == null) {
            context.addMessage(null, new FacesMessage("Password", "Thera is not password 1"));
            checkResult = false;
        }
        if (password2.trim().equals("") || password2 == null) {
            context.addMessage(null, new FacesMessage("Password", "Thera is not password 2"));
            checkResult = false;
        } else if (!password2.equals(password1)) {
            context.addMessage(null, new FacesMessage("Password", "password 1 ,  password 2  are not same password "));
            checkResult = false;
        } else {
            admin.setPassword(password1);
        }

        if (checkResult) {
            gsb.creatAdmin(admin);
            Admins = gsb.findAdminEntities();
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
        }

    }

    public String login() {
        currentAdmin = gsb.adminLogin(username, password5);
        if (currentAdmin == null) {

            return (username = password5 = null);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("v1", password5);
            if (gsb.adminLogin(username, password5).isEditor()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("v2", "true");
            }
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("password",admin.isEditor());
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", admin.getId());
            return "index.xhtml?faces-redirect=true";
        }

    }

    public boolean isLoggedIn() {
        return currentAdmin != null;
    }

    public String logout() {
        currentAdmin = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        System.out.println("ferid link isleyir");
        return "login.xhtml?faces-redirect=true";
    }

    public void editAdmin() throws NonexistentEntityException, Exception {

        boolean checkResult = true;
        FacesContext context = FacesContext.getCurrentInstance();

        if (selectedAdmin.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "Thera is not anyname"));
            checkResult = false;
        } else if (selectedAdmin.getName().trim().equals("")) {
            context.addMessage(null, new FacesMessage("Name", "Thera is not anyname"));
            checkResult = false;
        } else if (!selectedAdmin.getName().equals(gsb.findAdmin(selectedAdmin.getId()).getName())) {
            if (gsb.getAdminCountWithName(selectedAdmin.getName().trim()) > 0) {
                context.addMessage(null, new FacesMessage("Name", "This name is used"));
                checkResult = false;
            }
        }

        if (selectedAdmin.getEmail().trim().equals("") || selectedAdmin.getEmail() == null) {
            context.addMessage(null, new FacesMessage("Email", "Thera is not anyemail"));
            checkResult = false;
        } else if (!selectedAdmin.getEmail().equals(gsb.findAdmin(selectedAdmin.getId()).getEmail())) {
            if (gsb.getAdminCountWithEmail(selectedAdmin.getEmail().trim()) > 0) {
                context.addMessage(null, new FacesMessage("Email", "This email is used"));
                checkResult = false;
            }
        }
        if (!oldPassword.equals(gsb.findAdmin(selectedAdmin.getId()).getPassword()) && !currentAdmin.isEditor()) {
            context.addMessage(null, new FacesMessage("Password", "Current password is not correct"));
            checkResult = false;
        }

        if (password3 == null && password3.trim().equals("") && password4 != null && !password4.trim().equals("")) {
            context.addMessage(null, new FacesMessage("Password", "Thera is not new password 1"));
            checkResult = false;
        }
        if (password4 == null && password4.trim().equals("") && password3 != null && !password3.trim().equals("")) {
            context.addMessage(null, new FacesMessage("Password", "Thera is not new password 2"));
            checkResult = false;
        }
        if (!password3.equals(password4)) {
            context.addMessage(null, new FacesMessage("Password", "password 1 ,  password 2  are not same password "));
            checkResult = false;
        } else if (checkResult&&password3 != null && !password3.trim().equals("") && password4 != null && !password4.trim().equals("")) {
            selectedAdmin.setPassword(password3);
        }
        if (checkResult) {
            if (!currentAdmin.isEditor()) {
                selectedAdmin.setEditor(gsb.findAdmin(selectedAdmin.getId()).isEditor());
            }

            gsb.editAdmin(selectedAdmin);
            currentAdmin = gsb.findAdmin(currentAdmin.getId());
            Admins = gsb.findAdminEntities();
            context.addMessage(null, new FacesMessage("Succes", "Edititing is successful"));
        } else {

            Admin ad = gsb.findAdmin(selectedAdmin.getId());
            selectedAdmin.setName(ad.getName());
            selectedAdmin.setPassword(ad.getPassword());
            selectedAdmin.setEmail(ad.getEmail());

        }
        System.out.println(currentAdmin.getName());
    }

    public void deletAdmin() throws NonexistentEntityException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (currentAdmin.isEditor()) {
            gsb.destroyAdmin(selectedAdmin.getId());
            Admins = gsb.findAdminEntities();
            voep = false;
        } else {
            context.addMessage(null, new FacesMessage("Password", "You Havent acces for do it "));
        }

    }

    public void showVoep() {

        System.out.println(currentAdmin.getName());
        voep = true;
    }
    
    public void createReadyAdmin(){
    Admin ad =new Admin();
    ad.setEmail(" ");
    ad.setName("Admin");
    ad.setPassword("1234");
    ad.setEditor(true); 
    gsb.creatAdmin(ad); 
    }
     
}
