/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import pojoes.News;
import pojoes.Topic;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "NMB")
@RequestScoped
public class NewsManagedBean implements Serializable {

    private GeneralSessionBean gsb = new GeneralSessionBean();
    private List<Topic> HeaderTopic = gsb.findTopics("root");
    private List<Topic> topic0fTopic;
    private Topic topic;
    private String search="Search...";
    private Date nowDate = new Date();

    public List<News> getNewsOfTopic(int max) {

          return gsb.findLastArticleNewsEntitiesWithTopicName(max, 0, "root");
    }

    public List<News> getVideoesOfTopic(int max) {

       
        return gsb.findLastVideoNewsEntitiesWithTopicName(max, 0, "root");
    }

    public List<News> getSelectedNewsOfTopic(int max) {

        String newsString = gsb.findWebSite().getTopArticles();
        String newsString2[] = newsString.split("splitcode");
        List<News> newestNews = new ArrayList<News>();
        if (newsString2.length < max) {
            max = newsString2.length;
        }
        for (int i = 0; i < max; i++) {
            if(!newsString2[i].trim().equals("")){
                System.out.println(newsString2[i]);
            newestNews.add(gsb.findNewsWithName(newsString2[i]));}
        }
        return newestNews;
    }

    public String getNewsType(News a) {
        if (a.getVideo() != null) {
            return "video";
        } else if (a.getSlide() != null) {
            return "slide";
        } else if (a.getLink() != null) {
            return a.getLink().getLink();
        } else if (a.getPoll() != null) {
            return "poll";
        } else {
            return "article";
        }
    }
    
    public String minimalizeNewsText(News news,int size){
    String miniText="";
    char[] ca;
    
    if(news.getArticle()!=null){
     ca=cleanHtml(news.getArticle().getText()).toCharArray();}
     else{
     ca=cleanHtml(news.getExplainText()).toCharArray();}
        if(ca.length<size){ 
            size=ca.length;}
       for(int i=0;i<size;i++){
       miniText=miniText+ca[i];
       if(i%50==0&&i>50){
       //miniText=miniText+"<br/>";
       }}
       return miniText;
     }
   
    public String cleanHtml(String htmlString){
         // Remove HTML tag from java String    
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");

        // Remove Carriage return from java String
        noHTMLString = noHTMLString.replaceAll("\r", "<br/>");

        // Remove New line from java string and replace html break
        noHTMLString = noHTMLString.replaceAll("\n", " ");
        noHTMLString = noHTMLString.replaceAll("\'", "&#39;");
        noHTMLString = noHTMLString.replaceAll("\"", "&quot;");
        return noHTMLString;
    }
    
    
    public void focusInSearch(AjaxBehaviorEvent event){
        System.out.println("sfafaf");
    }
    
    public void focusSearch(){
    
    if(search.trim().equals("")){
    search="Search...";
    }
    
    }
    
    
    public List<Topic> getTopic0fTopic() {
        return topic0fTopic;
    }

    public void setTopic0fTopic(List<Topic> topic0fTopic) {
        this.topic0fTopic = topic0fTopic;
    }

    public Topic getTopic() {
      Map<String, String> params =FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       String top= params.get("topic");
       topic=gsb.findTopicWithName(top);
       System.out.println(top);
      return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    
    public List<Topic> getHeaderTopic() {
        return HeaderTopic;
    }

    public void setHeaderTopic(List<Topic> HeaderTopic) {
        this.HeaderTopic = HeaderTopic;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }
    
    
    
    
}
