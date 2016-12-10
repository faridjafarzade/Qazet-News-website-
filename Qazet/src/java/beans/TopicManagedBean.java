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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pojoes.Article;
import pojoes.Link;
import pojoes.News;
import pojoes.Poll;
import pojoes.Slide;
import pojoes.Topic;
import pojoes.Video;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "TMB")
@SessionScoped
public class TopicManagedBean implements Serializable {

    GeneralSessionBean gsb = new GeneralSessionBean();
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    private String readTopic = (String) request.getParameter("page");
    private TreeNode root = new DefaultTreeNode("Root", null);
    private TreeNode root2 = new DefaultTreeNode("Root", null);
    private TreeNode root3 = new DefaultTreeNode("Root", null);
    private TreeNode root4 = new DefaultTreeNode("Root", null);
    private TreeNode root5 = new DefaultTreeNode("Root", null);
    private TreeNode root6 = new DefaultTreeNode("Root", null);
    private TreeNode root7 = new DefaultTreeNode("Root", null);
    private TreeNode root8 = new DefaultTreeNode("Root", null);
    private TreeNode root9 = new DefaultTreeNode("Root", null);
    private TreeNode root10 = new DefaultTreeNode("Root", null);
    private Topic topic = new Topic();
    private Topic selectedTopic = new Topic();
    private TreeNode selectedNode;
    private TreeNode selectedNode2;
    private TreeNode selectedNode3;
    private TreeNode selectedNode4;
    private TreeNode selectedNode5;
    private TreeNode selectedNode6;
    private TreeNode selectedNode7;
    private TreeNode selectedNode8;
    private TreeNode[] selectedNodes;
    private TreeNode[] selectedNodes2;
    private TreeNode[] selectedNodes3;
    private TreeFunctions tf = new TreeFunctions();
    private List<String> articles = new ArrayList<String>();
    private List<Topic> topics = new ArrayList<Topic>();
    private List<SelectItem> newss = new ArrayList<SelectItem>();
    private List<SelectItem> newss2 = new ArrayList<SelectItem>();
    private List<SelectItem> newss3 = new ArrayList<SelectItem>();
    private List<SelectItem> newss4 = new ArrayList<SelectItem>();
    private List<SelectItem> newss5 = new ArrayList<SelectItem>();
    private CommonFunctions cf = new CommonFunctions();
    private String dg;
    private SelectItem si;
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
    private WebSite ws = gsb.findWebSite();
    private boolean voep = false;
    private boolean votap = false;
    private boolean votvp = false;
    private boolean votsp = false;
    private boolean votpp = false;
    private boolean vottp = false;

    public TopicManagedBean() {
        cleanTrees();
        voep = false;
        votap = false;
        votvp = false;
        votsp = false;
        votpp = false;
        vottp = false;
    }

    public void start() {
        gsb = new GeneralSessionBean();
        topic = new Topic();
        selectedTopic = new Topic();
        tf = new TreeFunctions();
        articles = new ArrayList<String>();
        newss = new ArrayList<SelectItem>();
        newss2 = new ArrayList<SelectItem>();
        newss3 = new ArrayList<SelectItem>();
        newss4 = new ArrayList<SelectItem>();
        ws = new WebSite();
        voep = false;
        votap = false;
        votvp = false;
        votsp = false;
        votpp = false;
        vottp = false;
        cleanTrees();
    }

    public void addTopics() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode == null) {
            context.addMessage(null, new FacesMessage("Topic", "Topic isn't chossen"));
            check = false;
        } else {
            topic.setUpperTopic(selectedNode.getData().toString());
        }

        if (topic.getName().trim().equals("") || topic.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (gsb.getTopicCount(topic.getName().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Name", "This name is used"));
            check = false;
        }
        if (check) {
            topic.setTopNews(" ");
            topic.setTopVidoes(" ");
            topic.setTopSlides(" ");
            topic.setTopPolles(" ");
            topic.setTopTopics(" ");
            gsb.creatTopic(topic);



            cleanTrees();
        }
    }

    public void moveTopic() throws NonexistentEntityException, Exception {
        if (selectedNodes != null && selectedNodes.length > 0 && selectedNode2 != null) {
            boolean cr = true;
            for (TreeNode node : selectedNodes) {
                if (node.equals(selectedNode2)) {
                    cr = false;
                }
            }
            if (cr) {
                Topic t = new Topic();
                if (!selectedNode2.getData().toString().equals("root")) {
                    t = gsb.findTopicWithName(selectedNode2.getData().toString());
                }

                List<Topic> st = new ArrayList<Topic>();
                for (TreeNode node : selectedNodes) {
                    if ((node.getType().equals("document") || node.getType().equals("picture") || node.getType().equals("video")) && !selectedNode2.getData().toString().equals("root")) {
                        News news = gsb.findNewsWithName(node.getData().toString());
                        if (!news.getArticle().equals(null)) {
                            deletFromTopArticles(news.getName(), news.getTopic(), t, news.getTopic());
                        } else if (!news.getLink().equals(null)) {
                            deletFromTopArticles(news.getName(), news.getTopic(), t, news.getTopic());
                        } else if (!news.getVideo().equals(null)) {
                            deletFromTopVidoes(news.getName(), news.getTopic(), t, news.getTopic());
                        } else if (!news.getSlide().equals(null)) {
                            deletFromTopSlides(news.getName(), news.getTopic(), t, news.getTopic());
                        } else if (!news.getPoll().equals(null)) {
                            deletFromTopPolles(news.getName(), news.getTopic(), t, news.getTopic());
                        }

                        news.setTopic(t);
                        gsb.editNews(news);
                    } else {
                       //Topic null=new Topic();
                        Topic top = gsb.findTopicWithName(node.getData().toString());
                         if(!top.getUpperTopic().equals("root")&&!selectedNode2.getData().toString().equals("root")){
                        Topic topu = gsb.findTopicWithName(top.getUpperTopic());
                        Topic topr = gsb.findTopicWithName(selectedNode2.getData().toString());
                        deletFromTopTopics(top.getName(), topu, topr,topu);
                        deletTopicNews(top, topu, topr);}
                         else if(!top.getUpperTopic().equals("root")&&selectedNode2.getData().toString().equals("root")){
                             Topic topu = gsb.findTopicWithName(top.getUpperTopic());
                             deletFromTopTopics(top.getName(), topu, null,topu);
                             deletTopicNews(top, topu,null);
                         }
                         
                        
                        top.setUpperTopic(selectedNode2.getData().toString());
                        gsb.editTopic(top);

                    }
                }
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", "Moving is successfull");
                FacesContext.getCurrentInstance().addMessage(null, message);
                cleanTrees();

            } else {

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eror", "You can't move a topic itself");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

            if (selectedNode2 == null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eror", "Thera isn't a target topic");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
    
    public void editTopic() throws NonexistentEntityException, Exception{
    gsb.editTopic(selectedTopic);
    cleanTrees();
    }

    public void deletTopic() throws NonexistentEntityException, Exception {
        if (selectedNodes2 != null && selectedNodes2.length > 0) {
            List<Topic> st = new ArrayList<Topic>();
            for (TreeNode node : selectedNodes2) {
                if ((node.getType().equals("document") || node.getType().equals("picture") || node.getType().equals("video")) && !node.getData().toString().equals("root")) {
                    News o = gsb.findNewsWithName(node.getData().toString());
                    gsb.destroyNews(o.getId());
                    if (o.getArticle() != null) {
                        deletFromTopArticles(o.getName(), o.getTopic(), new Topic(),new Topic());
                        gsb.destroyArticle(o.getArticle().getId());
                        cf.deletFile(o.getHeaderImage());
                    } else if (o.getLink() != null) {
                        deletFromTopArticles(o.getName(), o.getTopic(), new Topic(),new Topic());
                        gsb.destroyLink(o.getLink().getId());
                        cf.deletFile(o.getHeaderImage());
                    } else if (o.getVideo() != null) {
                        deletFromTopVidoes(o.getName(), o.getTopic(), new Topic(),new Topic());
                        gsb.destroyVideo(o.getVideo().getId());
                        cf.deletFile(o.getHeaderImage());
                    } else if (o.getSlide() != null) {
                        deletFromTopSlides(o.getName(), o.getTopic(), new Topic(),new Topic());
                        gsb.destroySlide(o.getSlide().getId());
                        String ph[] = o.getSlide().getPhotos().split("photoborder");
                        for (int i = 0; i < ph.length; i++) {
                            cf.deletFile(ph[i]);

                        }

                    } else if (o.getPoll() != null) {
                        deletFromTopPolles(o.getName(), o.getTopic(), new Topic(),new Topic());
                        gsb.destroyPoll(o.getPoll().getId());
                        cf.deletFile(o.getHeaderImage());
                    }
                    //gsb.destroyNews(o.getId());
                } else {
                    Topic top = gsb.findTopicWithName(node.getData().toString());
                    st.add(top);
                }
            }

            for (Topic t : st) {
                if(!t.getUpperTopic().equals("root")){
                    deletFromTopTopics(t.getName(), t, new Topic(), gsb.findTopicWithName(t.getUpperTopic()));
                }
                else{
                deletFromTopTopics(t.getName(), t, new Topic(), new Topic());}
                
                deletTopic(t);
            }


            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", "Deleting is successfull");
            FacesContext.getCurrentInstance().addMessage(null, message);
            cleanTrees();
        }

    }

    public void cleanTrees() {
        root = new DefaultTreeNode("Root", null);
        root3 = new DefaultTreeNode("Root", null);
        root2 = new DefaultTreeNode("Root", null);
        root4 = new DefaultTreeNode("Root", null);
        root5 = new DefaultTreeNode("Root", null);
        root6 = new DefaultTreeNode("Root", null);
        root7 = new DefaultTreeNode("Root", null);
        root8 = new DefaultTreeNode("Root", null);
        root9 = new DefaultTreeNode("Root", null);
        root10 = new DefaultTreeNode("Root", null);
        TreeNode ro = new DefaultTreeNode("root", null);
        TreeNode roo = new DefaultTreeNode("root", null);
        TreeNode r = new DefaultTreeNode("root", null);
        TreeNode r2 = new DefaultTreeNode("root", null);
        TreeNode r3 = new DefaultTreeNode("root", null);
        TreeNode r4 = new DefaultTreeNode("root", null);
        TreeNode r5 = new DefaultTreeNode("root", null);
        TreeNode r6 = new DefaultTreeNode("root", null);
        ro.setParent(root);
        roo.setParent(root3);


        tf.organiseTree(gsb, ro);
        tf.organiseTree(gsb, roo);
        tf.organiseAllTree(gsb, root2);

        roo.setParent(root3);
        tf.organiseAllTree(gsb, root4);

        r.setParent(root5);
        tf.organiseTree(gsb, r);

        r2.setParent(root6);
        tf.organiseTree(gsb, r2);

        r3.setParent(root7);
        tf.organiseTree(gsb, r3);

        r4.setParent(root8);
        tf.organiseTree(gsb, r4);

        r5.setParent(root9);
        tf.organiseTree(gsb, r5);

        r6.setParent(root10);
        tf.organiseTree(gsb, r6);
    }

    public void showEditTopicForm() {
          if (selectedNode8 != null&&!selectedNode8.getData().toString().equals("root")) {
            selectedTopic = gsb.findTopicWithName(selectedNode8.getData().toString());
            setVoep(true);
        } else if (selectedNode8.getData().toString().equals("root")) {
            FacesContext context = FacesContext.getCurrentInstance();
            setVoep(false);
            context.addMessage(null, new FacesMessage("Topic", "Please go homepage to do this operation"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            setVoep(false);
            context.addMessage(null, new FacesMessage("Topic", "Topic is not chossen"));
        }
    }

    public void showTopArticles() {
        if (selectedNode3 != null) {
            ws = null;
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
            setVotap(true);

            newss = new ArrayList<SelectItem>();
            showArticles(selectedNode3.getData().toString());
            this.newss.add(new SelectItem("", ""));
            String hn[];
            if (!selectedNode3.getData().toString().equals("root")) {
                selectedTopic = gsb.findTopicWithName(selectedNode3.getData().toString());
                hn = selectedTopic.getTopNews().split("splitcode");
            } else {
                ws = gsb.findWebSite();
                hn = ws.getTopArticles().split("splitcode");
            }
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
    }

    public void showTopVideos() {
        if (selectedNode4 != null) {
            ws = null;
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
            setVotvp(true);



            newss2 = new ArrayList<SelectItem>();
            showVideos(selectedNode4.getData().toString());
            this.newss2.add(new SelectItem("", ""));
            String hn[];
            if (!selectedNode4.getData().toString().equals("root")) {
                selectedTopic = gsb.findTopicWithName(selectedNode4.getData().toString());
                hn = selectedTopic.getTopVidoes().split("splitcode");
            } else {
                ws = gsb.findWebSite();
                hn = ws.getTopVidoes().split("splitcode");
            }
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
    }

    public void showTopSlides() {
        if (selectedNode5 != null) {
            ws = null;
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
            setVotsp(true);



            newss3 = new ArrayList<SelectItem>();
            showSlides(selectedNode5.getData().toString());
            this.newss3.add(new SelectItem("", ""));
            String hn[];
            if (!selectedNode5.getData().toString().equals("root")) {
                selectedTopic = gsb.findTopicWithName(selectedNode5.getData().toString());
                hn = selectedTopic.getTopSlides().split("splitcode");
            } else {
                ws = gsb.findWebSite();
                hn = ws.getTopSlides().split("splitcode");
            }
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
    }

    public void showTopPolles() {
        if (selectedNode6 != null) {
            ws = null;
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
            setVotpp(true);



            newss4 = new ArrayList<SelectItem>();
            showPolles(selectedNode6.getData().toString());
            this.newss4.add(new SelectItem("", ""));
            String hn[];
            if (!selectedNode6.getData().toString().equals("root")) {
                selectedTopic = gsb.findTopicWithName(selectedNode6.getData().toString());
                hn = selectedTopic.getTopPolles().split("splitcode");
            } else {
                ws = gsb.findWebSite();
                hn = ws.getTopPolles().split("splitcode");
            }
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
    }

    public void showTopTopics() {
        if (selectedNode7 != null) {
            ws = null;
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
            setVottp(true);



            newss5 = new ArrayList<SelectItem>();
            showTopics(selectedNode7.getData().toString());
            this.newss5.add(new SelectItem("", ""));
            String hn[];
            if (!selectedNode7.getData().toString().equals("root")) {
                selectedTopic = gsb.findTopicWithName(selectedNode7.getData().toString());
                hn = selectedTopic.getTopTopics().split("splitcode");
            } else {
                ws = gsb.findWebSite();
                hn = ws.getTopTopics().split("splitcode");
            }
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

        if (ws == null) {
            Topic t = gsb.findTopic(selectedTopic.getId());
            t.setTopNews(s);
            gsb.editTopic(t);
            selectedTopic = gsb.findTopic(selectedTopic.getId());
        } else {
            ws = gsb.findWebSite();
            ws.setTopArticles(s);
            gsb.editWebSite(ws);
        }

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
        cleanTrees();

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

        if (ws == null) {
            Topic t = gsb.findTopic(selectedTopic.getId());
            t.setTopVidoes(s);
            gsb.editTopic(t);
            selectedTopic = gsb.findTopic(selectedTopic.getId());
        } else {
            ws = gsb.findWebSite();
            ws.setTopVidoes(s);
            gsb.editWebSite(ws);
        }

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
        cleanTrees();

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

        if (ws == null) {
            Topic t = gsb.findTopic(selectedTopic.getId());
            t.setTopSlides(s);
            gsb.editTopic(t);
            selectedTopic = gsb.findTopic(selectedTopic.getId());
        } else {
            ws = gsb.findWebSite();
            ws.setTopSlides(s);
            gsb.editWebSite(ws);
        }

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
        cleanTrees();

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

        if (ws == null) {
            Topic t = gsb.findTopic(selectedTopic.getId());
            t.setTopPolles(s);
            gsb.editTopic(t);
            selectedTopic = gsb.findTopic(selectedTopic.getId());
        } else {
            ws = gsb.findWebSite();
            ws.setTopPolles(s);
            gsb.editWebSite(ws);
        }

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
        cleanTrees();

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

        if (ws == null) {
            Topic t = gsb.findTopic(selectedTopic.getId());
            t.setTopTopics(s);
            gsb.editTopic(t);
            selectedTopic = gsb.findTopic(selectedTopic.getId());
        } else {
            ws = gsb.findWebSite();
            ws.setTopTopics(s);
            gsb.editWebSite(ws);
        }

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
        cleanTrees();
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

    public void deletTopic(Topic t) throws NonexistentEntityException, Exception {

        List<News> news = gsb.findNewsEntitiesWithTopicName(t.getName());
        List<Topic> topics = gsb.findTopics(t.getName());
        if (t.getUpperTopic().equals("root")) {
            for (News o : news) {

                if (o.getArticle()!= null) {
                    gsb.destroyNews(o.getId());
                    deletFromTopArticles(o.getName(), o.getTopic(), new Topic(),new Topic());
                    gsb.destroyArticle(o.getArticle().getId());
                    cf.deletFile(o.getHeaderImage());
                } else if (o.getLink()!= null) {
                    gsb.destroyNews(o.getId());
                    deletFromTopArticles(o.getName(), o.getTopic(), new Topic(),new Topic());
                    gsb.destroyLink(o.getLink().getId());
                    cf.deletFile(o.getHeaderImage());
                } else if (o.getVideo()!= null) {
                    gsb.destroyNews(o.getId());
                    deletFromTopVidoes(o.getName(), o.getTopic(), new Topic(),new Topic());
                    gsb.destroyVideo(o.getVideo().getId());
                    cf.deletFile(o.getHeaderImage());

                } else if (o.getSlide()!= null) {
                    gsb.destroyNews(o.getId());
                    gsb.destroySlide(o.getSlide().getId());
                    deletFromTopSlides(o.getName(), o.getTopic(), new Topic(),new Topic());
                    String ph[] = o.getSlide().getPhotos().split("photoborder");
                    for (int i = 0; i < ph.length; i++) {
                        cf.deletFile(ph[i]);
                    }

                } else if (o.getPoll()!= null) {
                    gsb.destroyNews(o.getId());
                    deletFromTopPolles(o.getName(), o.getTopic(), new Topic(),new Topic());
                    cf.deletFile(o.getHeaderImage());
                    gsb.destroyPoll(o.getPoll().getId());
                }
            }
            for (Topic o : topics) {
                deletFromTopTopics(o.getName(), o, new Topic(),gsb.findTopicWithName(o.getUpperTopic()));
                deletTopic(o);
            }
        } else {
            Topic tpc = gsb.findTopicWithName(t.getUpperTopic());
            for (News o : news) {
                o.setTopic(tpc);
                gsb.editNews(o);
            }

            for (Topic o : topics) {
                o.setUpperTopic(tpc.getName());
                gsb.editTopic(o);
            }
        }
        gsb.destroyTopic(t.getId());

    }

    public void deletTopicNews(Topic t, Topic tr, Topic tt) throws NonexistentEntityException, Exception {
        List<News> ln = gsb.findNewsEntitiesWithTopicName(t.getName());
        for (News news : ln) {
            if (news.getArticle() != null) {
                deletFromTopArticles(news.getName(), tr, tt, news.getTopic());
            } else if (news.getLink()!= null) {
                deletFromTopArticles(news.getName(), tr, tt, news.getTopic());
            } else if (news.getVideo()!= null) {
                deletFromTopVidoes(news.getName(), tr, tt, news.getTopic());
            } else if (news.getSlide()!= null) {
                deletFromTopSlides(news.getName(), tr, tt, news.getTopic());
            } else if (news.getPoll()!= null) {
                deletFromTopPolles(news.getName(), tr, tt, news.getTopic());
            }
        }

        List<Topic> lt = gsb.findTopics(tr.getName());
        for (Topic topic : lt) {
            deletFromTopTopics(topic.getName(), tr, tt, tr);
        }
    }

    public void deletFromTopArticles(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopArticles(cf.deletNewestNews(t.getTopArticles(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
            ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopArticles(cf.deletNewestNews(ws.getTopArticles(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopArticles(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }

    public void deletFromTopVidoes(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopVidoes(cf.deletNewestNews(t.getTopVidoes(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
            ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopVidoes(cf.deletNewestNews(ws.getTopVidoes(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopVidoes(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }

    public void deletFromTopSlides(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopSlides(cf.deletNewestNews(t.getTopSlides(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
            ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopSlides(cf.deletNewestNews(ws.getTopSlides(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopSlides(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }

    public void deletFromTopPolles(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopPolles(cf.deletNewestNews(t.getTopPolles(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
            ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopPolles(cf.deletNewestNews(ws.getTopPolles(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopPolles(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }

    public void deletFromTopTopics(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        if(t!=null){
        t.setTopTopics(cf.deletNewestNews(t.getTopTopics(), n));
        gsb.editTopic(t);}
        String ttu;
        if(tt!=null){
        ttu=tt.getUpperTopic();
        }
        else{
        ttu="splitcode";
        }
         if (!t.equals(tt) && !t.getUpperTopic().equals(ttu)&&!t.equals(ot)) {
        if (t.getUpperTopic().equals("root")) {
            ws = gsb.findWebSite();
            ws.setTopTopics(cf.deletNewestNews(ws.getTopTopics(), n));
            gsb.editWebsite(ws);
        } else {
            deletFromTopTopics(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
        }
    }
    }

    public Topic giveTopic(String t) {

        return gsb.findTopicWithName(t);

    }

    public List<Topic> giveTopicWithParent(String t) {

        return gsb.findTopics(t);

    }

    public List<Topic> giveTopicWithParent(String t, int start, int end) {

        List<Topic> l1 = gsb.findTopics(t);
        List<Topic> l2 = new ArrayList<Topic>();

        if (end > l1.size()) {
            end = l1.size();
        }
        if (start > l1.size()) {
            start = l1.size();
        }

        for (int i = start; i < end; i++) {
            l2.add(l1.get(i));
        }
        return l2;
    }

    public List<Topic> giveTopTopic(String t, int start, int max) throws NonexistentEntityException, Exception {
       
        List<Topic> topics = new ArrayList<Topic>();
        if (t!= null &&!t.trim().equals("")) {
        String word = gsb.findTopicWithName(t).getTopTopics();
        if (word!= null &&!word.trim().equals("")) {
        System.out.println(word);
        String[] topics2 = word.split("splitcode");
        int l = topics2.length;
        System.out.println(start+","+max+","+l);

        if (topics2.length < max) {
            max = topics2.length;
        }
        

        if (l < start) {
            start = 0;
            max = 0;

        }



        for (int i = start; i < max; i ++) {
            System.out.println(i);
            if (topics2[i] != null && !topics2[i].trim().equals("")) {
                   System.out.println(start+","+max+","+l);
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
        String[] topics2 = gsb.findWebSite().getTopTopics().split("splitcode");
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
        HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String t = (String)request.getParameter("topic");
        if (t!=null){
        return giveTopTopic(t,start, max);
        }
        else{
         return  giveWebsiteTopTopic(start, max);
       }
    }

    public boolean isVotap() {
        return votap;
    }

    public void setVotap(boolean votap) {
        this.votap = votap;
    }

    public TreeNode getSelectedNode2() {
        return selectedNode2;
    }

    public void setSelectedNode2(TreeNode selectedNode2) {
        this.selectedNode2 = selectedNode2;
    }

    public TreeNode getRoot3() {
        return root3;
    }

    public void setRoot3(TreeNode root3) {
        this.root3 = root3;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot2() {
        return root2;
    }

    public void setRoot2(TreeNode root2) {
        this.root2 = root2;
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

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public TreeNode[] getSelectedNodes2() {
        return selectedNodes2;
    }

    public void setSelectedNodes2(TreeNode[] selectedNodes2) {
        this.selectedNodes2 = selectedNodes2;
    }

    public TreeNode getRoot4() {
        return root4;
    }

    public void setRoot4(TreeNode root4) {
        this.root4 = root4;
    }

    public TreeNode getSelectedNode3() {
        return selectedNode3;
    }

    public void setSelectedNode3(TreeNode selectedNode3) {
        this.selectedNode3 = selectedNode3;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

    public TreeNode getRoot5() {
        return root5;
    }

    public void setRoot5(TreeNode root5) {
        this.root5 = root5;
    }

    public TreeNode getRoot6() {
        return root6;
    }

    public void setRoot6(TreeNode root6) {
        this.root6 = root6;
    }

    public TreeNode getRoot7() {
        return root7;
    }

    public void setRoot7(TreeNode root7) {
        this.root7 = root7;
    }

    public TreeNode getRoot8() {
        return root8;
    }

    public void setRoot8(TreeNode root8) {
        this.root8 = root8;
    }

    public TreeNode[] getSelectedNodes3() {
        return selectedNodes3;
    }

    public void setSelectedNodes3(TreeNode[] selectedNodes3) {
        this.selectedNodes3 = selectedNodes3;
    }

    public String getDg() {
        return dg;
    }

    public void setDg(String dg) {
        this.dg = dg;
    }

    public SelectItem getSi() {
        return si;
    }

    public void setSi(SelectItem si) {
        this.si = si;
    }

    public List<SelectItem> getNewss() {
        return newss;
    }

    public void setNewss(List<SelectItem> newss) {
        this.newss = newss;
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

    public SelectItem getTopNews1() {
        return topArticles1;
    }

    public void setTopNews1(SelectItem topNews1) {
        this.topArticles1 = topNews1;
    }

    public SelectItem getTopNews2() {
        return topArticles2;
    }

    public void setTopNews2(SelectItem topNews2) {
        this.topArticles2 = topNews2;
    }

    public SelectItem getTopNews3() {
        return topArticles3;
    }

    public void setTopNews3(SelectItem topNews3) {
        this.topArticles3 = topNews3;
    }

    public SelectItem getTopNews4() {
        return topArticles4;
    }

    public void setTopNews4(SelectItem topNews4) {
        this.topArticles4 = topNews4;
    }

    public SelectItem getTopNews5() {
        return topArticles5;
    }

    public void setTopNews5(SelectItem topNews5) {
        this.topArticles5 = topNews5;
    }

    public SelectItem getTopNews6() {
        return topArticles6;
    }

    public void setTopNews6(SelectItem topNews6) {
        this.topArticles6 = topNews6;
    }

    public WebSite getWs() {
        return ws;
    }

    public void setWs(WebSite ws) {
        this.ws = ws;
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

    public SelectItem getTopNews7() {
        return topArticles7;
    }

    public void setTopNews7(SelectItem topNews7) {
        this.topArticles7 = topNews7;
    }

    public SelectItem getTopNews8() {
        return topArticles8;
    }

    public void setTopNews8(SelectItem topNews8) {
        this.topArticles8 = topNews8;
    }

    public SelectItem getTopNews9() {
        return topArticles9;
    }

    public void setTopNews9(SelectItem topNews9) {
        this.topArticles9 = topNews9;
    }

    public SelectItem getTopNews10() {
        return topArticles10;
    }

    public void setTopNews10(SelectItem topNews10) {
        this.topArticles10 = topNews10;
    }

    public List<Topic> getTopics() {
        topics = gsb.findTopics("root");
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getReadTopic() {
        return readTopic;
    }

    public void setReadTopic(String readTopic) {
        this.readTopic = readTopic;
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

    public boolean isVotvp() {
        return votvp;
    }

    public void setVotvp(boolean votvp) {
        this.votvp = votvp;
    }

    public boolean isVotsp() {
        return votsp;
    }

    public void setVotsp(boolean votsp) {
        this.votsp = votsp;
    }

    public boolean isVotpp() {
        return votpp;
    }

    public void setVotpp(boolean votpp) {
        this.votpp = votpp;
    }

    public TreeNode getSelectedNode4() {
        return selectedNode4;
    }

    public void setSelectedNode4(TreeNode selectedNode4) {
        this.selectedNode4 = selectedNode4;
    }

    public TreeNode getSelectedNode5() {
        return selectedNode5;
    }

    public void setSelectedNode5(TreeNode selectedNode5) {
        this.selectedNode5 = selectedNode5;
    }

    public TreeNode getSelectedNode6() {
        return selectedNode6;
    }

    public void setSelectedNode6(TreeNode selectedNode6) {
        this.selectedNode6 = selectedNode6;
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

    public TreeNode getRoot9() {
        return root9;
    }

    public void setRoot9(TreeNode root9) {
        this.root9 = root9;
    }

    public TreeNode getSelectedNode7() {
        return selectedNode7;
    }

    public void setSelectedNode7(TreeNode selectedNode7) {
        this.selectedNode7 = selectedNode7;
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

    public SelectItem getTopTopic2() {
        return topTopic2;
    }

    public void setTopTopic2(SelectItem topTopic2) {
        this.topTopic2 = topTopic2;
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

    public boolean isVottp() {
        return vottp;
    }

    public void setVottp(boolean vottp) {
        this.vottp = vottp;
    }

    public TreeNode getRoot10() {
        return root10;
    }

    public void setRoot10(TreeNode root10) {
        this.root10 = root10;
    }

    public TreeNode getSelectedNode8() {
        return selectedNode8;
    }

    public void setSelectedNode8(TreeNode selectedNode8) {
        this.selectedNode8 = selectedNode8;
    }

    public boolean isVoep() {
        return voep;
    }

    public void setVoep(boolean voep) {
        this.voep = voep;
    }
    
    
}
