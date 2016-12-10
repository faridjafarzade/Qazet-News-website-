/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojoes.Article;
import pojoes.News;
import pojoes.Topic;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "PaMB")
@SessionScoped
public class PageManagedBean implements Serializable {

    private GeneralSessionBean gsb = new GeneralSessionBean();
    private WebSite ws = gsb.findWebSite();
    private String searchKey = "Search...";
    private String searchTopic;
    private int searchPN;
    private Date d1;
    private Date d2;

    public List<Topic> giveTopTopic(String t, int start, int max) throws NonexistentEntityException, Exception {

        List<Topic> topics = new ArrayList<Topic>();
        if (t != null && !t.trim().equals("")) {
            String word = gsb.findTopicWithName(t).getTopTopics();
            if (word != null && !word.trim().equals("")) {
                System.out.println(word);
                String[] topics2 = word.split("splitcode");
                int l = topics2.length;
                System.out.println(start + "," + max + "," + l);

                if (topics2.length < max) {
                    max = topics2.length;
                }


                if (l < start) {
                    start = 0;
                    max = 0;

                }



                for (int i = start; i < max; i++) {
                    System.out.println(i);
                    if (topics2[i] != null && !topics2[i].trim().equals("")) {
                        System.out.println(start + "," + max + "," + l);
                        if (gsb.findTopicWithName(topics2[i]) != null) {
                            topics.add(gsb.findTopicWithName(topics2[i]));
                        }
                    }
                }
            }
        }
        return topics;

    }

    public List<Topic> giveWebsiteTopTopic(int start, int max) throws NonexistentEntityException, Exception {
        List<Topic> topics = new ArrayList<Topic>();
        String[] topics2 = ws.getTopTopics().split("splitcode");
        int l = topics2.length;


        if (topics2.length < max) {
            max = topics2.length;
        }

        for (int i = start; i < max; i = i + 1) {
            if (topics2[i] != null && topics2[i].trim() != "") {
                if (gsb.findTopicWithName(topics2[i]) != null) {
                    topics.add(gsb.findTopicWithName(topics2[i]));
                }
            }
        }
        return topics;

    }

    public List<Topic> giveTopTopic(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return giveTopTopic(t, start, max);
        } else {
            return giveWebsiteTopTopic(start, max);
        }
    }

    public List<News> giveTopArticle(String t, int start, int max) throws NonexistentEntityException, Exception {

        List<News> news = new ArrayList<News>();
        if (t != null && !t.trim().equals("")) {
            String word = gsb.findTopicWithName(t).getTopNews();
            if (word != null && !word.trim().equals("")) {
                String[] news2 = word.split("splitcode");
                int l = news2.length;


                if (news2.length < max) {
                    max = news2.length;
                }

                if (l < start) {
                    start = 1;
                    max = 2;

                }



                for (int i = start; i < max; i++) {
                    if (news2[i] != null && !news2[i].trim().equals("")) {
                        news.add(gsb.findNewsWithName(news2[i]));
                    }
                }
            }
        }
        return news;

    }

    public List<News> giveWebsiteTopArticle(int start, int max) throws NonexistentEntityException, Exception {
        List<News> news = new ArrayList<News>();
        String[] news2 = ws.getTopArticles().split("splitcode");
        int l = news2.length;


        if (news2.length < max) {
            max = news2.length;
        }

        for (int i = start; i < max; i = i + 1) {
            if (news2[i] != null && news2[i].trim() != "") {
                if (gsb.findNewsWithName(news2[i]) != null) {
                    news.add(gsb.findNewsWithName(news2[i]));
                }
            }
        }
        return news;

    }

    public List<News> giveTopArticle(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return giveTopArticle(t, start, max);
        } else {
            return giveWebsiteTopArticle(start, max);
        }
    }

    public List<News> giveNewestArticle(String t, int start, int max) throws NonexistentEntityException, Exception {

        return gsb.findLastArticleNewsEntitiesWithTopicName(max, start, t);

    }

    public List<News> giveNewestArticle(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return gsb.findLastArticleNewsEntitiesWithTopicName(max, start, t);
        } else {
            return gsb.findLastArticleNewsEntities(max, start);
        }
    }

    public List<News> giveTopVideo(String t, int start, int max) throws NonexistentEntityException, Exception {

        List<News> vs = new ArrayList<News>();
        if (t != null && !t.trim().equals("")) {
            String word = gsb.findTopicWithName(t).getTopVidoes();
            if (word != null && !word.trim().equals("")) {
                String[] vs2 = word.split("splitcode");
                int l = vs2.length;


                if (vs2.length < max) {
                    max = vs2.length;
                }

                if (l < start) {
                    start = 1;
                    max = 2;

                }



                for (int i = start; i < max; i++) {
                    if (vs2[i] != null && !vs2[i].trim().equals("")) {
                        vs.add(gsb.findNewsWithName(vs2[i]));
                    }
                }
            }
        }
        return vs;

    }

    public List<News> giveWebsiteTopVideo(int start, int max) throws NonexistentEntityException, Exception {
        List<News> vs = new ArrayList<News>();
        String[] vs2 = ws.getTopVidoes().split("splitcode");
        int l = vs2.length;


        if (vs2.length < max) {
            max = vs2.length;
        }

        for (int i = start; i < max; i = i + 1) {
            if (vs2[i] != null && vs2[i].trim() != "") {
                if (gsb.findNewsWithName(vs2[i]) != null) {
                    vs.add(gsb.findNewsWithName(vs2[i]));
                }
            }
        }
        return vs;

    }

    public List<News> giveTopVideo(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return giveTopVideo(t, start, max);
        } else {
            return giveWebsiteTopVideo(start, max);
        }
    }

    public List<News> giveNewestVideo(String t, int start, int max) throws NonexistentEntityException, Exception {

        return gsb.findLastVideoNewsEntitiesWithTopicName(max, start, t);

    }

    public List<News> giveNewestVideo(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return gsb.findLastVideoNewsEntitiesWithTopicName(max, start, t);
        } else {
            return gsb.findLastVideoNewsEntities(max, start);
        }
    }

    public List<News> giveTopSlides(String t, int start, int max) throws NonexistentEntityException, Exception {

        List<News> vs = new ArrayList<News>();
        if (t != null && !t.trim().equals("")) {
            String word = gsb.findTopicWithName(t).getTopSlides();
            if (word != null && !word.trim().equals("")) {
                String[] vs2 = word.split("splitcode");
                int l = vs2.length;


                if (vs2.length < max) {
                    max = vs2.length;
                }

                if (l < start) {
                    start = 1;
                    max = 2;

                }



                for (int i = start; i < max; i++) {
                    if (vs2[i] != null && !vs2[i].trim().equals("")) {
                        vs.add(gsb.findNewsWithName(vs2[i]));
                    }
                }
            }
        }
        return vs;

    }

    public List<News> giveWebsiteTopSlides(int start, int max) throws NonexistentEntityException, Exception {
        List<News> vs = new ArrayList<News>();
        String[] vs2 = ws.getTopSlides().split("splitcode");
        int l = vs2.length;


        if (vs2.length < max) {
            max = vs2.length;
        }

        for (int i = start; i < max; i = i + 1) {
            if (vs2[i] != null && vs2[i].trim() != "") {
                if (gsb.findNewsWithName(vs2[i]) != null) {
                    vs.add(gsb.findNewsWithName(vs2[i]));
                }
            }
        }
        return vs;

    }

    public List<News> giveTopSlides(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return giveTopSlides(t, start, max);
        } else {
            return giveWebsiteTopSlides(start, max);
        }
    }

    public List<News> giveNewestSlides(String t, int start, int max) throws NonexistentEntityException, Exception {

        return gsb.findLastSlideNewsEntitiesWithTopicName(max, start, t);

    }

    public List<News> giveNewestSlides(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return gsb.findLastSlideNewsEntitiesWithTopicName(max, start, t);
        } else {
            return gsb.findLastSlideNewsEntities(max, start);
        }
    }

    public List<News> giveTopPoll(String t, int start, int max) throws NonexistentEntityException, Exception {

        List<News> vs = new ArrayList<News>();
        if (t != null && !t.trim().equals("")) {
            String word = gsb.findTopicWithName(t).getTopPolles();
            if (word != null && !word.trim().equals("")) {
                String[] vs2 = word.split("splitcode");
                int l = vs2.length;


                if (vs2.length < max) {
                    max = vs2.length;
                }

                if (l < start) {
                    start = 1;
                    max = 2;

                }



                for (int i = start; i < max; i++) {
                    if (vs2[i] != null && !vs2[i].trim().equals("")) {
                        vs.add(gsb.findNewsWithName(vs2[i]));
                    }
                }
            }
        }
        return vs;

    }

    public List<News> giveWebsiteTopPoll(int start, int max) throws NonexistentEntityException, Exception {
        List<News> vs = new ArrayList<News>();
        String[] vs2 = ws.getTopPolles().split("splitcode");
        int l = vs2.length;


        if (vs2.length < max) {
            max = vs2.length;
        }

        for (int i = start; i < max; i = i + 1) {
            if (vs2[i] != null && vs2[i].trim() != "") {
                if (gsb.findNewsWithName(vs2[i]) != null) {
                    vs.add(gsb.findNewsWithName(vs2[i]));
                }
            }
        }
        return vs;

    }
    
    

    public List<News> giveTopPoll(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return giveTopPoll(t, start, max);
        } else {
            return giveWebsiteTopPoll(start, max);
        }
    }

    public List<News> giveNewestPoll(String t, int start, int max) throws NonexistentEntityException, Exception {

        return gsb.findLastPollNewsEntitiesWithTopicName(max, start, t);

    }

    public List<News> giveNewestPoll(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("topic");
        if (t != null) {
            return gsb.findLastPollNewsEntitiesWithTopicName(max, start, t);
        } else {
            return gsb.findLastPollNewsEntities(max, start);
        }
    }

    public List<News> giveNews() {
        List<News> news = new ArrayList<News>();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int id = Integer.parseInt((String) request.getParameter("news"));
        news.add(gsb.findNews(id));
        return news;
    }

    public void viewNews() throws NonexistentEntityException, Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String) request.getParameter("news");

        if (t != null) {
            int id = Integer.parseInt(t);
            News s = gsb.findNews(id);
            s.setViewCount(s.getViewCount() + 1);
            gsb.editNews(s);

        }
    }

    public String giveTitle() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            
       
        

        String n = (String) request.getParameter("news");
        if (n != null) {
            return gsb.findNews(Integer.parseInt(n)).getName() + "-" + gsb.findWebSite().getName();
        } else {
            String t = (String) request.getParameter("topic");
            if (t != null) {
                return gsb.findTopicWithName(t).getTitle() + "-" + gsb.findWebSite().getName();
            } else {
                return ws.getTitle();
            }

        }
         } catch (Exception e) {
             HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();;
             response.sendRedirect(request.getContextPath() + "/index.xhtml");
             return null;
        }
    }

    public String giveKeys() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String n = (String) request.getParameter("news");
        if (n != null) {
            return gsb.findNews(Integer.parseInt(n)).getKeyWords();
        } else {
            String t = (String) request.getParameter("topic");
            if (t != null) {
                return gsb.findTopicWithName(t).getKeyWords();
            } else {
                return ws.getKeyWords();
            }

        }
    }

    public String giveFaceUrl() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String url = request.getRequestURI().trim();
        String t = (String) request.getParameter("topic");
        String n = (String) request.getParameter("news");
        url = url.replace("Qazet", "Qazet.com");
        url = url.replace(":", "%3A");
        url = url.replace("/", "%2F");
        return url + "%3Ftopic%3D" + t + "%26news%3D" + n;

    }

    ;
   
   public List<String> giveFaceUrlL() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        List<String> ls = new ArrayList<String>();
        String url = request.getRequestURI().trim();
        String t = (String) request.getParameter("topic");
        String n = (String) request.getParameter("news");
        url = url.replace("Qazet", "Qazet.com");
        url = url.replace(":", "%3A");
        url = url.replace("/", "%2F");
        for (int i = -1000000000; i < 1000000000; i++) {
            ls.add(url + "%3Ftopic%3D" + t + "%26news%3D" + i);
        }
        return ls;

    }

    ;
   

    public String getON(String s) {
        String a[] = s.split("\\.");
        System.out.println(a[0]);
        return a[0];
    }

    public List<String> getOP(News s) {
        List<String> ls = new ArrayList<String>();
        String a[] = s.getSlide().getPhotos().split("photoborder");
        for (int i = 0; i < a.length; i++) {
            ls.add(a[i]);
        }
        return ls;
    }

    public String header(News s) {
        List<String> ls = new ArrayList<String>();
        String a[] = s.getSlide().getPhotos().split("photoborder");
        for (int i = 0; i < a.length; i++) {
            ls.add(a[i]);
        }
        return ls.get(0);
    }

    public WebSite getWs() {
        return ws;
    }

    public void setWs(WebSite ws) {
        this.ws = ws;
    }

    public List<News> giveArticleSearchListwithDate() throws ParseException {
       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String sk = (String) request.getParameter("sk");
        if(sk==null) sk=searchKey;
        System.out.println(sk);
        if (searchTopic == null) {
            return gsb.findLastLikeArticleNewsEntities(11, givePage(), sk, d1, d2);
        } else {
            return gsb.findLastLikeArticleNewsEntitiesWithTopicName(11, givePage(), searchTopic, sk, d1, d2);
        }
    }

    public List<News> giveArticleSearchList() throws ParseException {
       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String sk = (String) request.getParameter("sk");
        System.out.println(sk);
        return gsb.findLastLikeArticleNewsEntities(11,givePage(),sk);
      
    }

 

    public String a() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String sk = (String) request.getParameter("sk");
        System.out.println("search?ak=" + sk + "&amp;d1=" + d1 + "&amp;d2=" + d2 + "asaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return "search";
    } 
    
    public String giveSK(){
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    return request.getParameter("sk");
    }
    
    public int givePage(){
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         return   Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            return 0;
        }
   
    }
    
    public String givePB(){
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         if( Integer.parseInt(request.getParameter("page"))>10){
         return "block";}
         else return "none";
        } catch (Exception e) {
            return "none";
        }
   
    }
    
    public String giveType(News news){
     if (news.getArticle() != null) {
                return "Article";
            } else if (news.getLink()!= null) {
                return "Link";
            } else if (news.getVideo()!= null) {
                return "Video";
            } else if (news.getSlide()!= null) {
                return "Slide";
            } else{
                return "Poll";
            }
    
    }
    
    
    public String giveLink(News news){
     if (news.getArticle() != null) {
                return "http://localhost:8080/Qazet/faces/a/"+news.getTopic().getName()+"/"+news.getId();
            } else if (news.getLink()!= null) {
                return news.getLink().getLink();
            } else if (news.getVideo()!= null) {
               return "http://localhost:8080/Qazet/faces/v/"+news.getTopic().getName()+"/"+news.getId();
            } else if (news.getSlide()!= null) {
                return "http://localhost:8080/Qazet/faces/s/"+news.getTopic().getName()+"/"+news.getId();
            } else{
                return "http://localhost:8080/Qazet/faces/p/"+news.getTopic().getName()+"/"+news.getId();
            }
    
    }
     public String goSearchPage(){
     return "article.xhtml";
     }
    
  


    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchTopic() {
        return searchTopic;
    }

    public void setSearchTopic(String searchTopic) {
        this.searchTopic = searchTopic;
    }

    public int getSearchPN() {
        return searchPN;
    }

    public void setSearchPN(int searchPN) {
        this.searchPN = searchPN;
    }

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }
}
