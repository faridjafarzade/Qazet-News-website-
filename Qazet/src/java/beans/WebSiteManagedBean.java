/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name="WSMB")
@RequestScoped
public class WebSiteManagedBean {

    private GeneralSessionBean gsb=new GeneralSessionBean();
    public WebSiteManagedBean() {
    }
    
     public void createWebSite(){
    WebSite ws=new WebSite();
    ws.setTopTopics(" "); 
    ws.setTopArticles(" ");
    ws.setTopVidoes(" ");
    ws.setTopSlides(" ");
    ws.setTopPolles(" ");
    gsb.creatWebSite(ws); 
    
    }
}
