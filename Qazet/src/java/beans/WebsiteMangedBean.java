/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.UploadedFile;
import pojoes.News;
import pojoes.Topic;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "WMB")
@RequestScoped
public class WebsiteMangedBean implements Serializable {

    private GeneralSessionBean gsb = new GeneralSessionBean();
    private WebSite ws = gsb.findWebSite();
    private boolean voip = true;
    private boolean voep = false;
    private UploadedFile file;
    private UploadedFile file2;
    private UploadedFile file3;
    private CommonFunctions cf=new CommonFunctions();
    private String news1;
    private String news2;
    private String news3;
    private String news4;
    private String news5;
    private String news6;
    private String news7;
    private String news8;
    private String news9;
    private String news10;
    private List<SelectItem> newss = new ArrayList<SelectItem>();
    private List<SelectItem> newss2 = new ArrayList<SelectItem>();
    private List<SelectItem> newss3 = new ArrayList<SelectItem>();
    private List<SelectItem> newss4 = new ArrayList<SelectItem>();
    private List<SelectItem> newss5 = new ArrayList<SelectItem>();
    private SelectItem topArticles1;
    private SelectItem topArticles2;
    private SelectItem topArticles3;
    private SelectItem topArticles4;
    private SelectItem topArticles5;
    private SelectItem topArticles6;
    private SelectItem topArticles7;
    private SelectItem topArticles8;
    private SelectItem topArticles9;
    private SelectItem topArticles10;
    private SelectItem topTopic1;
    private SelectItem topTopic2;
    private SelectItem topTopic3;
    private SelectItem topTopic4;
    private SelectItem topTopic5;
    private SelectItem topTopic6;
    private SelectItem topTopic7;
    private SelectItem topTopic8;
    private SelectItem topTopic9;
    private SelectItem topTopic10;
    private SelectItem topVideos1;
    private SelectItem topVideos2;
    private SelectItem topVideos3;
    private SelectItem topVideos4;
    private SelectItem topVideos5;
    private SelectItem topVideos6;
    private SelectItem topVideos7;
    private SelectItem topVideos8;
    private SelectItem topVideos9;
    private SelectItem topVideos10;
    private SelectItem topSlides1;
    private SelectItem topSlides2;
    private SelectItem topSlides3;
    private SelectItem topSlides4;
    private SelectItem topSlides5;
    private SelectItem topSlides6;
    private SelectItem topSlides7;
    private SelectItem topSlides8;
    private SelectItem topSlides9;
    private SelectItem topSlides10;
    private SelectItem topPolles1;
    private SelectItem topPolles2;
    private SelectItem topPolles3;
    private SelectItem topPolles4;
    private SelectItem topPolles5;
    private SelectItem topPolles6;
    private SelectItem topPolles7;
    private SelectItem topPolles8;
    private SelectItem topPolles9;
    private SelectItem topPolles10;
    

    public WebsiteMangedBean() {
        showTopArticles();
        showTopSlides();
        showTopVideos();
        showTopPolles();
        showTopTopics();
        
    }
    
    
   public void editWebsite() throws NonexistentEntityException, Exception{
   FacesContext context = FacesContext.getCurrentInstance();
   if (file == null) {
                ws.setBanner(ws.getBanner());
            } else {

                cf.deletFile(ws.getBanner());
                ws.setBanner(cf.saveFileToFolder(file));
            }
   
   if (file2 == null) {
                ws.setLogo(ws.getLogo());
            } else {

                cf.deletFile(ws.getLogo());
                ws.setLogo(cf.saveFileToFolder(file2));
            }
   
   if (file3 == null) {
                ws.setWhiteLogo(ws.getWhiteLogo());
            } else {

                cf.deletFile(ws.getWhiteLogo());
                ws.setWhiteLogo(cf.saveFileToFolder(file3));
            }
   
   gsb.editWebsite(ws); 
   voip=true;
   voep=false;
   context.addMessage(null, new FacesMessage("Succes", "Edition is successful"));
   }
   
   public void showEditPanel(){
   voip=false;
   voep=true;
   }

    public void showTopArticles() {
        
            news1 = null;
            news2 = null;
            news3 = null;
            news4 = null;
            news5 = null;
            news6 = null;
            news7 = null;
            news8 = null;
            news9 = null;
            news10 = null;
            topArticles1 = null;
            topArticles2 = null;
            topArticles3 = null;
            topArticles4 = null;
            topArticles5 = null;
            topArticles6 = null;
            topArticles7 = null;
            topArticles8 = null;
            topArticles9 = null;
            topArticles10 = null;
            

            newss = new ArrayList<SelectItem>();
            showArticles("root");
            String hn[];
            this.newss.add(new SelectItem("",""));
            
                hn = ws.getTopArticles().split("splitcode");
            
            if (hn != null) {

                for (int i = 0; i < hn.length; i++) {
                    if (i == 0) {
                        topArticles1 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 1) {
                        topArticles2 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 2) {
                        topArticles3 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 3) {
                        topArticles4 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 4) {
                        topArticles5 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 5) {
                        topArticles6 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 6) {
                        topArticles7 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 7) {
                        topArticles8 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 8) {
                        topArticles9 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 9) {
                        topArticles10 = new SelectItem(hn[i], hn[i]);
                    }
                }
            }
        
    }

    public void showTopVideos() {
        
            news1 = null;
            news2 = null;
            news3 = null;
            news4 = null;
            news5 = null;
            news6 = null;
            news7 = null;
            news8 = null;
            news9 = null;
            news10 = null;
            topVideos1 = null;
            topVideos2 = null;
            topVideos3 = null;
            topVideos4 = null;
            topVideos5 = null;
            topVideos6 = null;
            topVideos7 = null;
            topVideos8 = null;
            topVideos9 = null;
            topVideos10 = null;
           



            newss2 = new ArrayList<SelectItem>();
            showVideos("root");
            String hn[];
            this.newss2.add(new SelectItem("",""));
            
                hn = ws.getTopVidoes().split("splitcode");
            
            if (hn != null) {

                for (int i = 0; i < hn.length; i++) {
                    if (i == 0) {
                        topVideos1 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 1) {
                        topVideos2 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 2) {
                        topVideos3 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 3) {
                        topVideos4 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 4) {
                        topVideos5 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 5) {
                        topVideos6 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 6) {
                        topVideos7 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 7) {
                        topVideos8 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 8) {
                        topVideos9 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 9) {
                        topVideos10 = new SelectItem(hn[i], hn[i]);
                    }
                }
            }
        
    }

    public void showTopSlides() {
        
            news1 = null;
            news2 = null;
            news3 = null;
            news4 = null;
            news5 = null;
            news6 = null;
            news7 = null;
            news8 = null;
            news9 = null;
            news10 = null;
            topSlides1 = null;
            topSlides2 = null;
            topSlides3 = null;
            topSlides4 = null;
            topSlides5 = null;
            topSlides6 = null;
            topSlides7 = null;
            topSlides8 = null;
            topSlides9 = null;
            topSlides10 = null;
            



            newss3 = new ArrayList<SelectItem>();
            showSlides("root");
            this.newss3.add(new SelectItem("",""));
            String hn[];
            
                hn = ws.getTopSlides().split("splitcode");
            
            if (hn != null) {

                for (int i = 0; i < hn.length; i++) {
                    if (i == 0) {
                        topSlides1 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 1) {
                        topSlides2 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 2) {
                        topSlides3 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 3) {
                        topSlides4 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 4) {
                        topSlides5 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 5) {
                        topSlides6 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 6) {
                        topSlides7 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 7) {
                        topSlides8 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 8) {
                        topSlides9 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 9) {
                        topSlides10 = new SelectItem(hn[i], hn[i]);
                    }
                }
            }
        
    }

    public void showTopPolles() {
        
            
            news1 = null;
            news2 = null;
            news3 = null;
            news4 = null;
            news5 = null;
            news6 = null;
            news7 = null;
            news8 = null;
            news9 = null;
            news10 = null;
            topPolles1 = null;
            topPolles2 = null;
            topPolles3 = null;
            topPolles4 = null;
            topPolles5 = null;
            topPolles6 = null;
            topPolles7 = null;
            topPolles8 = null;
            topPolles9 = null;
            topPolles10 = null;
            



            newss4 = new ArrayList<SelectItem>();
            showPolles("root");
            this.newss4.add(new SelectItem("",""));
            String hn[];
            
                hn = ws.getTopPolles().split("splitcode");
            
            if (hn != null) {

                for (int i = 0; i < hn.length; i++) {
                    if (i == 0) {
                        topPolles1 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 1) {
                        topPolles2 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 2) {
                        topPolles3 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 3) {
                        topPolles4 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 4) {
                        topPolles5 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 5) {
                        topPolles6 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 6) {
                        topPolles7 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 7) {
                        topPolles8 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 8) {
                        topPolles9 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 9) {
                        topPolles10 = new SelectItem(hn[i], hn[i]);
                    }
                }
            }
        
    }

    public void showTopTopics() {
        
            
            news1 = null;
            news2 = null;
            news3 = null;
            news4 = null;
            news5 = null;
            news6 = null;
            news7 = null;
            news8 = null;
            news9 = null;
            news10 = null;
            topTopic1 = null;
            topTopic2 = null;
        topTopic3 = null;
        topTopic4 = null;
        topTopic5 = null;
        topTopic6 = null;
        topTopic7 = null;
        topTopic8 = null;
        topTopic9 = null;
        topTopic10 = null;
            



            newss5 = new ArrayList<SelectItem>();
            showTopics("root");
            this.newss5.add(new SelectItem("",""));
            
            String hn[];
            
                hn = ws.getTopTopics().split("splitcode");
            
            if (hn != null) {

                for (int i = 0; i < hn.length; i++) {
                    if (i == 0) {
                        topTopic1 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 1) {
                        topTopic2 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 2) {
                        topTopic3 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 3) {
                        topTopic4 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 4) {
                        topTopic5 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 5) {
                        topTopic6 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 6) {
                        topTopic7 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 7) {
                        topTopic8 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 8) {
                        topTopic9 = new SelectItem(hn[i], hn[i]);
                    } else if (i == 9) {
                        topTopic10 = new SelectItem(hn[i], hn[i]);
                    }
                }
            }
        
    }

    public void editTopNews() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        String s = "";
        if (news1 != null && !news1.trim().equals("")) {
            s = s + news1 + "splitcode";
        }
        if (news2 != null && !news2.trim().equals("")) {
            s = s + news2 + "splitcode";
        }
        if (news3 != null && !news3.trim().equals("")) {
            s = s + news3 + "splitcode";
        }
        if (news4 != null && !news4.trim().equals("")) {
            s = s + news4 + "splitcode";
        }
        if (news5 != null && !news5.trim().equals("")) {
            s = s + news5 + "splitcode";
        }
        if (news6 != null && !news6.trim().equals("")) {
            s = s + news6 + "splitcode";
        }
        if (news7 != null && !news7.trim().equals("")) {
            s = s + news7 + "splitcode";
        }
        if (news8 != null && !news8.trim().equals("")) {
            s = s + news8 + "splitcode";
        }
        if (news9 != null && !news9.trim().equals("")) {
            s = s + news9 + "splitcode";
        }
        if (news10 != null && !news10.trim().equals("")) {
            s = s + news10 + "splitcode";
        }

      
            ws.setTopArticles(s);
            gsb.editWebSite(ws);
        

        news1 = null;
        news2 = null;
        news3 = null;
        news4 = null;
        news5 = null;
        news6 = null;
        news7 = null;
        news8 = null;
        news9 = null;
        news10 = null;
        topArticles1 = null;
        topArticles2 = null;
        topArticles3 = null;
        topArticles4 = null;
        topArticles5 = null;
        topArticles6 = null;
        topArticles7 = null;
        topArticles8 = null;
        topArticles9 = null;
        topArticles10 = null;

       
        context.addMessage(null, new FacesMessage("Succes", "Edition is successful"));
    }

    public void editTopVidoes() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        String s = "";
        if (news1 != null && !news1.trim().equals("")) {
            s = s + news1 + "splitcode";
        }
        if (news2 != null && !news2.trim().equals("")) {
            s = s + news2 + "splitcode";
        }
        if (news3 != null && !news3.trim().equals("")) {
            s = s + news3 + "splitcode";
        }
        if (news4 != null && !news4.trim().equals("")) {
            s = s + news4 + "splitcode";
        }
        if (news5 != null && !news5.trim().equals("")) {
            s = s + news5 + "splitcode";
        }
        if (news6 != null && !news6.trim().equals("")) {
            s = s + news6 + "splitcode";
        }
        if (news7 != null && !news7.trim().equals("")) {
            s = s + news7 + "splitcode";
        }
        if (news8 != null && !news8.trim().equals("")) {
            s = s + news8 + "splitcode";
        }
        if (news9 != null && !news9.trim().equals("")) {
            s = s + news9 + "splitcode";
        }
        if (news10 != null && !news10.trim().equals("")) {
            s = s + news10 + "splitcode";
        }

        ws.setTopVidoes(s);
            gsb.editWebSite(ws);

        news1 = null;
        news2 = null;
        news3 = null;
        news4 = null;
        news5 = null;
        news6 = null;
        news7 = null;
        news8 = null;
        news9 = null;
        news10 = null;
        topVideos1 = null;
        topVideos2 = null;
        topVideos3 = null;
        topVideos4 = null;
        topVideos5 = null;
        topVideos6 = null;
        topVideos7 = null;
        topVideos8 = null;
        topVideos9 = null;
        topVideos10 = null;

        context.addMessage(null, new FacesMessage("Succes", "Edition is successful"));

    }

    public void editTopSlides() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        String s = "";
        if (news1 != null && !news1.trim().equals("")) {
            s = s + news1 + "splitcode";
        }
        if (news2 != null && !news2.trim().equals("")) {
            s = s + news2 + "splitcode";
        }
        if (news3 != null && !news3.trim().equals("")) {
            s = s + news3 + "splitcode";
        }
        if (news4 != null && !news4.trim().equals("")) {
            s = s + news4 + "splitcode";
        }
        if (news5 != null && !news5.trim().equals("")) {
            s = s + news5 + "splitcode";
        }
        if (news6 != null && !news6.trim().equals("")) {
            s = s + news6 + "splitcode";
        }
        if (news7 != null && !news7.trim().equals("")) {
            s = s + news7 + "splitcode";
        }
        if (news8 != null && !news8.trim().equals("")) {
            s = s + news8 + "splitcode";
        }
        if (news9 != null && !news9.trim().equals("")) {
            s = s + news9 + "splitcode";
        }
        if (news10 != null && !news10.trim().equals("")) {
            s = s + news10 + "splitcode";
        }

        ws.setTopSlides(s);
        gsb.editWebSite(ws);

        news1 = null;
        news2 = null;
        news3 = null;
        news4 = null;
        news5 = null;
        news6 = null;
        news7 = null;
        news8 = null;
        news9 = null;
        news10 = null;
        topSlides1 = null;
        topSlides2 = null;
        topSlides3 = null;
        topSlides4 = null;
        topSlides5 = null;
        topSlides6 = null;
        topSlides7 = null;
        topSlides8 = null;
        topSlides9 = null;
        topSlides10 = null;

        context.addMessage(null, new FacesMessage("Succes", "Edition is successful"));

    }

    public void editTopPolles() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        String s = "";
        if (news1 != null && !news1.trim().equals("")) {
            s = s + news1 + "splitcode";
        }
        if (news2 != null && !news2.trim().equals("")) {
            s = s + news2 + "splitcode";
        }
        if (news3 != null && !news3.trim().equals("")) {
            s = s + news3 + "splitcode";
        }
        if (news4 != null && !news4.trim().equals("")) {
            s = s + news4 + "splitcode";
        }
        if (news5 != null && !news5.trim().equals("")) {
            s = s + news5 + "splitcode";
        }
        if (news6 != null && !news6.trim().equals("")) {
            s = s + news6 + "splitcode";
        }
        if (news7 != null && !news7.trim().equals("")) {
            s = s + news7 + "splitcode";
        }
        if (news8 != null && !news8.trim().equals("")) {
            s = s + news8 + "splitcode";
        }
        if (news9 != null && !news9.trim().equals("")) {
            s = s + news9 + "splitcode";
        }
        if (news10 != null && !news10.trim().equals("")) {
            s = s + news10 + "splitcode";
        }

        ws.setTopPolles(s);
            gsb.editWebSite(ws);

        news1 = null;
        news2 = null;
        news3 = null;
        news4 = null;
        news5 = null;
        news6 = null;
        news7 = null;
        news8 = null;
        news9 = null;
        news10 = null;
        topPolles1 = null;
        topPolles2 = null;
        topPolles3 = null;
        topPolles4 = null;
        topPolles5 = null;
        topPolles6 = null;
        topPolles7 = null;
        topPolles8 = null;
        topPolles9 = null;
        topPolles10 = null;

       
        context.addMessage(null, new FacesMessage("Succes", "Edition is successful"));
    }

    public void editTopTopics() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        String s = "";
        if (news1 != null && !news1.trim().equals("")) {
            s = s + news1 + "splitcode";
        }
        if (news2 != null && !news2.trim().equals("")) {
            s = s + news2 + "splitcode";
        }
        if (news3 != null && !news3.trim().equals("")) {
            s = s + news3 + "splitcode";
        }
        if (news4 != null && !news4.trim().equals("")) {
            s = s + news4 + "splitcode";
        }
        if (news5 != null && !news5.trim().equals("")) {
            s = s + news5 + "splitcode";
        }
        if (news6 != null && !news6.trim().equals("")) {
            s = s + news6 + "splitcode";
        }
        if (news7 != null && !news7.trim().equals("")) {
            s = s + news7 + "splitcode";
        }
        if (news8 != null && !news8.trim().equals("")) {
            s = s + news8 + "splitcode";
        }
        if (news9 != null && !news9.trim().equals("")) {
            s = s + news9 + "splitcode";
        }
        if (news10 != null && !news10.trim().equals("")) {
            s = s + news10 + "splitcode";
        }

        ws.setTopTopics(s);
            gsb.editWebSite(ws);

        news1 = null;
        news2 = null;
        news3 = null;
        news4 = null;
        news5 = null;
        news6 = null;
        news7 = null;
        news8 = null;
        news9 = null;
        news10 = null;
        topTopic1 = null;
        topTopic2 = null;
        topTopic3 = null;
        topTopic4 = null;
        topTopic5 = null;
        topTopic6 = null;
        topTopic7 = null;
        topTopic8 = null;
        topTopic9 = null;
        topTopic10 = null;

       context.addMessage(null, new FacesMessage("Succes", "Edition is successful"));

    }
    
    public void showArticles(String t) {
        List<News> news = gsb.findArticleNewsEntitiesWithTopicName(t);
        List<News> news2 = gsb.findLinkNewsEntitiesWithTopicName(t);
        List<Topic> topics = gsb.findTopics(t);
        
        for (News o : news) {
            this.newss.add(new SelectItem(o.getName(), o.getName()));
        }

        for (News o : news2) {
            this.newss.add(new SelectItem(o.getName(), o.getName()));
        }

        for (Topic o : topics) {
            showArticles(o.getName());
        }
    }

    public void showVideos(String t) {
        List<News> news = gsb.findVideoNewsEntitiesWithTopicName(t);
        List<Topic> topics = gsb.findTopics(t);
        

        for (News o : news) {
            this.newss2.add(new SelectItem(o.getName(), o.getName()));
        }

        for (Topic o : topics) {
            showVideos(o.getName());
        }
    }

    public void showSlides(String t) {
        List<News> news = gsb.findSlideNewsEntitiesWithTopicName(t);
        List<Topic> topics = gsb.findTopics(t);
       

        for (News o : news) {
            this.newss3.add(new SelectItem(o.getName(), o.getName()));
        }

        for (Topic o : topics) {
            showSlides(o.getName());
        }
    }

    public void showPolles(String t) {
        List<News> news = gsb.findPollNewsEntitiesWithTopicName(t);
        List<Topic> topics = gsb.findTopics(t);
       
        for (News o : news) {
            this.newss4.add(new SelectItem(o.getName(), o.getName()));
        }

        for (Topic o : topics) {
            showPolles(o.getName());
        }
    }

    public void showTopics(String t) {
        
        List<Topic> topics = gsb.findTopics(t);
        for (Topic o : topics) {
            this.newss5.add(new SelectItem(o.getName(), o.getName()));
        }

    }
    
    public void onTabChange(TabChangeEvent event) {  
       
        if(event.getTab().getId().equals("ta")){
        showTopArticles();
        } 
        else if(event.getTab().getId().equals("tv")){
        showTopVideos();
        } 
        else if(event.getTab().getId().equals("ts")){
        showTopSlides();
        } 
        else if(event.getTab().getId().equals("tp")){
        showTopPolles();
        }
       
      
    }  
  
    public void onTabClose(TabCloseEvent event) {  
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    

    public boolean isVoip() {
        return voip;
    }

    public void setVoip(boolean voip) {
        this.voip = voip;
    }

    public boolean isVoep() {
        return voep;
    }

    public void setVoep(boolean voep) {
        this.voep = voep;
    }

    public WebSite getWs() {
        return ws;
    }

    public void setWs(WebSite ws) {
        this.ws = ws;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile2() {
        return file2;
    }

    public void setFile2(UploadedFile file2) {
        this.file2 = file2;
    }

    public UploadedFile getFile3() {
        return file3;
    }

    public void setFile3(UploadedFile file3) {
        this.file3 = file3;
    }

    public String getNews1() {
        return news1;
    }

    public void setNews1(String news1) {
        this.news1 = news1;
    }

    public String getNews2() {
        return news2;
    }

    public void setNews2(String news2) {
        this.news2 = news2;
    }

    public String getNews3() {
        return news3;
    }

    public void setNews3(String news3) {
        this.news3 = news3;
    }

    public String getNews4() {
        return news4;
    }

    public void setNews4(String news4) {
        this.news4 = news4;
    }

    public String getNews5() {
        return news5;
    }

    public void setNews5(String news5) {
        this.news5 = news5;
    }

    public String getNews6() {
        return news6;
    }

    public void setNews6(String news6) {
        this.news6 = news6;
    }

    public String getNews7() {
        return news7;
    }

    public void setNews7(String news7) {
        this.news7 = news7;
    }

    public String getNews8() {
        return news8;
    }

    public void setNews8(String news8) {
        this.news8 = news8;
    }

    public String getNews9() {
        return news9;
    }

    public void setNews9(String news9) {
        this.news9 = news9;
    }

    public String getNews10() {
        return news10;
    }

    public void setNews10(String news10) {
        this.news10 = news10;
    }

    public List<SelectItem> getNewss() {
        return newss;
    }

    public void setNewss(List<SelectItem> newss) {
        this.newss = newss;
    }

    public List<SelectItem> getNewss2() {
        return newss2;
    }

    public void setNewss2(List<SelectItem> newss2) {
        this.newss2 = newss2;
    }

    public List<SelectItem> getNewss3() {
        return newss3;
    }

    public void setNewss3(List<SelectItem> newss3) {
        this.newss3 = newss3;
    }

    public List<SelectItem> getNewss4() {
        return newss4;
    }

    public void setNewss4(List<SelectItem> newss4) {
        this.newss4 = newss4;
    }

    public SelectItem getTopArticles1() {
        return topArticles1;
    }

    public void setTopArticles1(SelectItem topArticles1) {
        this.topArticles1 = topArticles1;
    }

    public SelectItem getTopArticles2() {
        return topArticles2;
    }

    public void setTopArticles2(SelectItem topArticles2) {
        this.topArticles2 = topArticles2;
    }

    public SelectItem getTopArticles3() {
        return topArticles3;
    }

    public void setTopArticles3(SelectItem topArticles3) {
        this.topArticles3 = topArticles3;
    }

    public SelectItem getTopArticles4() {
        return topArticles4;
    }

    public void setTopArticles4(SelectItem topArticles4) {
        this.topArticles4 = topArticles4;
    }

    public SelectItem getTopArticles5() {
        return topArticles5;
    }

    public void setTopArticles5(SelectItem topArticles5) {
        this.topArticles5 = topArticles5;
    }

    public SelectItem getTopArticles6() {
        return topArticles6;
    }

    public void setTopArticles6(SelectItem topArticles6) {
        this.topArticles6 = topArticles6;
    }

    public SelectItem getTopArticles7() {
        return topArticles7;
    }

    public void setTopArticles7(SelectItem topArticles7) {
        this.topArticles7 = topArticles7;
    }

    public SelectItem getTopArticles8() {
        return topArticles8;
    }

    public void setTopArticles8(SelectItem topArticles8) {
        this.topArticles8 = topArticles8;
    }

    public SelectItem getTopArticles9() {
        return topArticles9;
    }

    public void setTopArticles9(SelectItem topArticles9) {
        this.topArticles9 = topArticles9;
    }

    public SelectItem getTopArticles10() {
        return topArticles10;
    }

    public void setTopArticles10(SelectItem topArticles10) {
        this.topArticles10 = topArticles10;
    }

    public SelectItem getTopVideos1() {
        return topVideos1;
    }

    public void setTopVideos1(SelectItem topVideos1) {
        this.topVideos1 = topVideos1;
    }

    public SelectItem getTopVideos2() {
        return topVideos2;
    }

    public void setTopVideos2(SelectItem topVideos2) {
        this.topVideos2 = topVideos2;
    }

    public SelectItem getTopVideos3() {
        return topVideos3;
    }

    public void setTopVideos3(SelectItem topVideos3) {
        this.topVideos3 = topVideos3;
    }

    public SelectItem getTopVideos4() {
        return topVideos4;
    }

    public void setTopVideos4(SelectItem topVideos4) {
        this.topVideos4 = topVideos4;
    }

    public SelectItem getTopVideos5() {
        return topVideos5;
    }

    public void setTopVideos5(SelectItem topVideos5) {
        this.topVideos5 = topVideos5;
    }

    public SelectItem getTopVideos6() {
        return topVideos6;
    }

    public void setTopVideos6(SelectItem topVideos6) {
        this.topVideos6 = topVideos6;
    }

    public SelectItem getTopVideos7() {
        return topVideos7;
    }

    public void setTopVideos7(SelectItem topVideos7) {
        this.topVideos7 = topVideos7;
    }

    public SelectItem getTopVideos8() {
        return topVideos8;
    }

    public void setTopVideos8(SelectItem topVideos8) {
        this.topVideos8 = topVideos8;
    }

    public SelectItem getTopVideos9() {
        return topVideos9;
    }

    public void setTopVideos9(SelectItem topVideos9) {
        this.topVideos9 = topVideos9;
    }

    public SelectItem getTopVideos10() {
        return topVideos10;
    }

    public void setTopVideos10(SelectItem topVideos10) {
        this.topVideos10 = topVideos10;
    }

    public SelectItem getTopSlides1() {
        return topSlides1;
    }

    public void setTopSlides1(SelectItem topSlides1) {
        this.topSlides1 = topSlides1;
    }

    public SelectItem getTopSlides2() {
        return topSlides2;
    }

    public void setTopSlides2(SelectItem topSlides2) {
        this.topSlides2 = topSlides2;
    }

    public SelectItem getTopSlides3() {
        return topSlides3;
    }

    public void setTopSlides3(SelectItem topSlides3) {
        this.topSlides3 = topSlides3;
    }

    public SelectItem getTopSlides4() {
        return topSlides4;
    }

    public void setTopSlides4(SelectItem topSlides4) {
        this.topSlides4 = topSlides4;
    }

    public SelectItem getTopSlides5() {
        return topSlides5;
    }

    public void setTopSlides5(SelectItem topSlides5) {
        this.topSlides5 = topSlides5;
    }

    public SelectItem getTopSlides6() {
        return topSlides6;
    }

    public void setTopSlides6(SelectItem topSlides6) {
        this.topSlides6 = topSlides6;
    }

    public SelectItem getTopSlides7() {
        return topSlides7;
    }

    public void setTopSlides7(SelectItem topSlides7) {
        this.topSlides7 = topSlides7;
    }

    public SelectItem getTopSlides8() {
        return topSlides8;
    }

    public void setTopSlides8(SelectItem topSlides8) {
        this.topSlides8 = topSlides8;
    }

    public SelectItem getTopSlides9() {
        return topSlides9;
    }

    public void setTopSlides9(SelectItem topSlides9) {
        this.topSlides9 = topSlides9;
    }

    public SelectItem getTopSlides10() {
        return topSlides10;
    }

    public void setTopSlides10(SelectItem topSlides10) {
        this.topSlides10 = topSlides10;
    }

    public SelectItem getTopPolles1() {
        return topPolles1;
    }

    public void setTopPolles1(SelectItem topPolles1) {
        this.topPolles1 = topPolles1;
    }

    public SelectItem getTopPolles2() {
        return topPolles2;
    }

    public void setTopPolles2(SelectItem topPolles2) {
        this.topPolles2 = topPolles2;
    }

    public SelectItem getTopPolles3() {
        return topPolles3;
    }

    public void setTopPolles3(SelectItem topPolles3) {
        this.topPolles3 = topPolles3;
    }

    public SelectItem getTopPolles4() {
        return topPolles4;
    }

    public void setTopPolles4(SelectItem topPolles4) {
        this.topPolles4 = topPolles4;
    }

    public SelectItem getTopPolles5() {
        return topPolles5;
    }

    public void setTopPolles5(SelectItem topPolles5) {
        this.topPolles5 = topPolles5;
    }

    public SelectItem getTopPolles6() {
        return topPolles6;
    }

    public void setTopPolles6(SelectItem topPolles6) {
        this.topPolles6 = topPolles6;
    }

    public SelectItem getTopPolles7() {
        return topPolles7;
    }

    public void setTopPolles7(SelectItem topPolles7) {
        this.topPolles7 = topPolles7;
    }

    public SelectItem getTopPolles8() {
        return topPolles8;
    }

    public void setTopPolles8(SelectItem topPolles8) {
        this.topPolles8 = topPolles8;
    }

    public SelectItem getTopPolles9() {
        return topPolles9;
    }

    public void setTopPolles9(SelectItem topPolles9) {
        this.topPolles9 = topPolles9;
    }

    public SelectItem getTopPolles10() {
        return topPolles10;
    }

    public void setTopPolles10(SelectItem topPolles10) {
        this.topPolles10 = topPolles10;
    }

    public List<SelectItem> getNewss5() {
        return newss5;
    }

    public void setNewss5(List<SelectItem> newss5) {
        this.newss5 = newss5;
    }

    public SelectItem getTopTopic1() {
        return topTopic1;
    }

    public void setTopTopic1(SelectItem topTopic1) {
        this.topTopic1 = topTopic1;
    }

    public SelectItem getTopTopic3() {
        return topTopic3;
    }

    public void setTopTopic3(SelectItem topTopic3) {
        this.topTopic3 = topTopic3;
    }

    public SelectItem getTopTopic4() {
        return topTopic4;
    }

    public void setTopTopic4(SelectItem topTopic4) {
        this.topTopic4 = topTopic4;
    }

    public SelectItem getTopTopic5() {
        return topTopic5;
    }

    public void setTopTopic5(SelectItem topTopic5) {
        this.topTopic5 = topTopic5;
    }

    public SelectItem getTopTopic6() {
        return topTopic6;
    }

    public void setTopTopic6(SelectItem topTopic6) {
        this.topTopic6 = topTopic6;
    }

    public SelectItem getTopTopic7() {
        return topTopic7;
    }

    public void setTopTopic7(SelectItem topTopic7) {
        this.topTopic7 = topTopic7;
    }

    public SelectItem getTopTopic8() {
        return topTopic8;
    }

    public void setTopTopic8(SelectItem topTopic8) {
        this.topTopic8 = topTopic8;
    }

    public SelectItem getTopTopic9() {
        return topTopic9;
    }

    public void setTopTopic9(SelectItem topTopic9) {
        this.topTopic9 = topTopic9;
    }

    public SelectItem getTopTopic10() {
        return topTopic10;
    }

    public void setTopTopic10(SelectItem topTopic10) {
        this.topTopic10 = topTopic10;
    }

    public SelectItem getTopTopic2() {
        return topTopic2;
    }

    public void setTopTopic2(SelectItem topTopic2) {
        this.topTopic2 = topTopic2;
    }
    
    
    
    

}
