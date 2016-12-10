/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import pojoes.Article;
import pojoes.News;
import pojoes.Topic;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "AMB")
@RequestScoped
public class ArticleManagedBean implements Serializable {

    private CommonFunctions cf = new CommonFunctions();
    private GeneralSessionBean gsb = new GeneralSessionBean();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private String readArticle = (String) request.getParameter("page");
    private String fileName;
    private String fileName2;
    private TreeNode root = new DefaultTreeNode("Root", null);
    private TreeNode root2 = new DefaultTreeNode("Root", null);
    private TreeNode root3 = new DefaultTreeNode("Root", null);
    private Topic topic = new Topic();
    private Topic selectedTopic = new Topic();
    private TreeNode selectedNode;
    private TreeNode selectedNode2;
    private TreeNode selectedNode3;
    private UploadedFile file;
    private UploadedFile file2;
    private TreeFunctions tf = new TreeFunctions();
    private boolean voep = false;
    private News article = new News();
    Article article2 = new Article();
    private News selectedArticle = new News();
    private boolean voap = false;

    public void addArticle() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode == null) {
            context.addMessage(null, new FacesMessage("Topic", "Topic isn't chossen"));
            check = false;
        } else {
            article.setTopic(gsb.findTopicWithName(selectedNode.getData().toString()));
        }
        if (article.getName().trim().equals("") || article.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (gsb.getNewsCount(article.getName().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Name", "This name is used"));
            check = false;
        }
        if (article.getHeader().trim().equals("") || article.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }
        if (check) {
            article.setDate(new Date());
            article.setHeaderImage(cf.saveFileToFolder(file));
            Article a = article.getArticle();
            article.setArticle(gsb.creatArticle(a));
            gsb.creatNews(article);
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseArticleTree(gsb, root2);
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
        }

    }

    public boolean checkSelectedNode2() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (selectedNode2 == null) {
            context.addMessage(null, new FacesMessage(" ", "Please select a article"));
            return false;
        } else if (!selectedNode2.getType().equals("document")) {
            context.addMessage(null, new FacesMessage(" ", "Please select a article"));
            return false;
        }
        return true;
    }

    public void showArticle() {
        if (checkSelectedNode2()) {
        }
    }

    public void showEditArticleForm() {
        if (checkSelectedNode2()) {
            selectedArticle = gsb.findNewsWithName(selectedNode2.getData().toString());
            article2 = selectedArticle.getArticle();
            setVoep(true);
        } else {
            setVoep(false);
        }
    }

    public void deletArticle() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        if (checkSelectedNode2()) {
            News s = gsb.findNewsWithName(selectedNode2.getData().toString());
            Article a = s.getArticle();
            cf.deletFile(selectedArticle.getHeaderImage());
            gsb.destroyNews(s.getId());
            gsb.destroyArticle(a.getId());
            deletFromTopArticles(s.getName(),s.getTopic(),new Topic(),new Topic());
            root2 = new DefaultTreeNode("Root", null);
            context.addMessage(null, new FacesMessage("Succes", "Deleting is successful"));
            tf.organiseArticleTree(gsb, root2);
            setVoep(false);
        }
    }
    

    public void deletFromTopArticles(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopArticles(cf.deletNewestNews(t.getTopArticles(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
            WebSite ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopArticles(cf.deletNewestNews(ws.getTopArticles(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopArticles(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }
    
    public void renameInTopArticles(String nn,String n,Topic t) throws NonexistentEntityException, Exception{
        t.setTopArticles(cf.renameInTop(t.getTopArticles(), n,nn));
        gsb.editTopic(t);
        
    WebSite ws=gsb.findWebSite();
    if(t.getUpperTopic().equals("root")){
    ws.setTopArticles(cf.renameInTop(ws.getTopArticles(), n,nn));
    gsb.editWebsite(ws);
    }
    else {
    renameInTopArticles(nn,n,gsb.findTopicWithName(t.getUpperTopic())); 
    }
    
    }

    public void editArticle() throws NonexistentEntityException, Exception {
        News art = gsb.findNews(selectedArticle.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode3 != null) {
            deletFromTopArticles(art.getName(),art.getTopic(),gsb.findTopicWithName(selectedNode3.getData().toString()),art.getTopic());
            selectedArticle.setTopic(gsb.findTopicWithName(selectedNode3.getData().toString()));
        } else {
            selectedArticle.setTopic(art.getTopic());
        }



        if (selectedArticle.getName().trim().equals("") || selectedArticle.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (!selectedArticle.getName().equals(art.getName())) {
            if (gsb.getNewsCount(selectedArticle.getName().trim()) > 0) {
                context.addMessage(null, new FacesMessage("Name", "This name is used"));
                check = false;
            }
        }
        if (selectedArticle.getHeader().trim().equals("") || selectedArticle.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }

        if (check) {
            
            if (selectedArticle.getName()!= art.getName()){
            renameInTopArticles(selectedArticle.getName(),art.getName(), selectedArticle.getTopic());
            }
            
            if (file2 == null) {
                selectedArticle.setHeaderImage(art.getHeaderImage());
            } else {

                cf.deletFile(art.getHeaderImage());
                selectedArticle.setHeaderImage(cf.saveFileToFolder(file2));
            }
            selectedArticle.setDate(new Date());
            // Article a=selectedArticle.getArticle();
            gsb.editArticle(article2);
            selectedArticle.setArticle(article2);
            gsb.editNews(selectedArticle);
            context.addMessage(null, new FacesMessage("Succes", "Edititing is successful"));
            setVoep(false);
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseArticleTree(gsb, root2);
        }

    }

    public List<News> giveTopArticle(String t, int start, int max) throws NonexistentEntityException, Exception {
       
        List<News> news = new ArrayList<News>();
        if (t!= null &&!t.trim().equals("")) {
        String word = gsb.findTopicWithName(t).getTopNews();
        if (word!= null &&!word.trim().equals("")) {
        String[] news2 = word.split("splitcode");
        int l = news2.length;


        if (news2.length < max) {
            max = news2.length;
        }

        if (l < start) {
            start = 1;
            max = 2;

        }



        for (int i = start; i < max; i ++) {
            if (news2[i] != null && !news2[i].trim().equals("")) {
                news.add(gsb.findNewsWithName(news2[i]));
            }
        }
        }}
        return news;

    }

    
    public List<News> giveWebsiteTopArticle(int start, int max) throws NonexistentEntityException, Exception {
        List<News> news = new ArrayList<News>();
        String[] news2 = gsb.findWebSite().getTopArticles().split("splitcode");
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
        HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String)request.getParameter("topic");
        if (t!=null){
        return giveTopArticle(t,start, max);
        }
        else{
         return  giveWebsiteTopArticle(start, max);
       }
    }
    

    public List<News> giveNewestArticle(String t,int start, int max) throws NonexistentEntityException, Exception {
        
        return gsb.findLastArticleNewsEntitiesWithTopicName(max, start, t);
        
    }
    

    public List<News> giveNewestArticle(int start, int max) throws NonexistentEntityException, Exception {
        HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String)request.getParameter("topic");
        if (t!=null){
        return gsb.findLastArticleNewsEntitiesWithTopicName(max, start, t);
        }
        else{
         return  gsb.findLastArticleNewsEntities(max, start);
       }
    }
    
    public String getReadArticle() {
        return readArticle;
    }

    public void setReadArticle(String readArticle) {
        this.readArticle = readArticle;
    }

    public boolean isVoap() {
        return voap;
    }

    public void setVoap(boolean voap) {
        this.voap = voap;
    }

    public TreeNode getRoot3() {
        return root3;
    }

    public void setRoot3(TreeNode root3) {
        this.root3 = root3;
    }

    public TreeNode getSelectedNode3() {
        return selectedNode3;
    }

    public void setSelectedNode3(TreeNode selectedNode3) {
        this.selectedNode3 = selectedNode3;
    }

    public UploadedFile getFile2() {
        return file2;
    }

    public void setFile2(UploadedFile file2) {
        this.file2 = file2;
    }

    public boolean isVoep() {
        return voep;
    }

    public void setVoep(boolean voep) {
        this.voep = voep;
    }

    public TreeNode getSelectedNode2() {
        return selectedNode2;
    }

    public void setSelectedNode2(TreeNode selectedNode2) {
        this.selectedNode2 = selectedNode2;
    }

    public TreeNode getRoot2() {
        return root2;
    }

    public void setRoot2(TreeNode root2) {
        this.root2 = root2;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public News getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(News selectedArticle) {
        this.selectedArticle = selectedArticle;
    }

    public ArticleManagedBean() {
        article.setArticle(new Article());
        tf.organiseArticleTree(gsb, root2);
        tf.organiseTree(gsb, root);
        tf.organiseTree(gsb, root3);

    }

    public News getArticle() {
        return article;
    }

    public void setArticle(News article) {
        this.article = article;
    }

    public Article getArticle2() {
        return article2;
    }

    public void setArticle2(Article article2) {
        this.article2 = article2;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }
    
    

    public void showAddArticleForm() {
        setVoap(true);

    }
    

    
}
