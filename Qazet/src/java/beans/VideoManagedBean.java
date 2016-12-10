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
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import pojoes.News;
import pojoes.Topic;
import pojoes.Video;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "VMB")
@RequestScoped
public class VideoManagedBean implements Serializable {

    private CommonFunctions cf = new CommonFunctions();
    private GeneralSessionBean gsb = new GeneralSessionBean();
    private TreeNode root = new DefaultTreeNode("Root", null);
    private Topic topic = new Topic();
    private Topic selectedTopic = new Topic();
    private TreeNode selectedNode;
    private UploadedFile file;
    private News video = new News();
    private Video video2 = new Video();
    private News selectedVideo = new News();
    private TreeFunctions tf = new TreeFunctions();
    private TreeNode root2 = new DefaultTreeNode("Root", null);
    private TreeNode root3 = new DefaultTreeNode("Root", null);
    private UploadedFile file2;
    private TreeNode selectedNode2;
    private TreeNode selectedNode3;
    private boolean voep = false;
    private String videoLink;
    private boolean voap = false;

    public boolean isVoap() {
        return voap;
    }

    public void setVoap(boolean voap) {
        this.voap = voap;
    }
    
    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public TreeNode getRoot2() {
        return root2;
    }

    public void setRoot2(TreeNode root2) {
        this.root2 = root2;
    }

    public TreeNode getRoot3() {
        return root3;
    }

    public void setRoot3(TreeNode root3) {
        this.root3 = root3;
    }

    public UploadedFile getFile2() {
        return file2;
    }

    public void setFile2(UploadedFile file2) {
        this.file2 = file2;
    }

    public TreeNode getSelectedNode2() {
        return selectedNode2;
    }

    public void setSelectedNode2(TreeNode selectedNode2) {
        this.selectedNode2 = selectedNode2;
    }

    public TreeNode getSelectedNode3() {
        return selectedNode3;
    }

    public void setSelectedNode3(TreeNode selectedNode3) {
        this.selectedNode3 = selectedNode3;
    }

    public boolean isVoep() {
        return voep;
    }

    public void setVoep(boolean voep) {
        this.voep = voep;
    }

    public VideoManagedBean() {
        tf.organiseVideoTree(gsb, root2);
        tf.organiseTree(gsb, root);
        tf.organiseTree(gsb, root3);
        video.setVideo(new Video()); 
    }

    public News getVideo() {
        return video;
    }

    public void setVideo(News video) {
        this.video = video;
    }

    public Video getVideo2() {
        return video2;
    }

    public void setVideo2(Video video2) {
        this.video2 = video2;
    }

    public News getSelectedVideo() {
        return selectedVideo;
    }

    public void setSelectedVideo(News selectedVideo) {
        this.selectedVideo = selectedVideo;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void showAddVideoForm() {
      setVoap(true);
        
    }

    public void addVideo() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode == null) {
            context.addMessage(null, new FacesMessage("Topic", "Topic isn't chossen"));
            check = false;
        } else {
            video.setTopic(gsb.findTopicWithName(selectedNode.getData().toString()));
        }
        if (video.getName().trim().equals("") || video.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (gsb.getNewsCount(video.getName().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Name", "This name is used"));
            check = false;
        }
        if (video.getHeader().trim().equals("") || video.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }
         Video nv=video.getVideo();
        if (nv.getVideo().trim().equals("") || video.getVideo() == null) {
            context.addMessage(null, new FacesMessage("Video", "There isn't video"));
            check = false;
        } else {
            
            try {
                String link = nv.getVideo();
                String pl[] = link.split("watch");
                link = pl[0] + pl[1];
                pl = link.split("\\?");
                link = pl[0] + pl[1];
                pl = link.split("=");
                link = pl[0] + "/" + pl[1];
                nv.setVideo(link);
            } catch (Exception ex) {
                context.addMessage(null, new FacesMessage("Video", "Video link isn't correct"));
                check = false;
            }
        }
        if (check) {
            video.setHeaderImage(cf.saveFileToFolder(file));
            video.setDate(new Date());
            System.out.println(nv.getVideo());
            System.out.println(nv.getId());
            video.setVideo(gsb.creatVideo(nv));
            System.out.println(nv.getVideo());
            System.out.println(nv.getId()); 
            gsb.creatNews(video);
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseVideoTree(gsb, root2);
            setVoep(false);
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
        } else {
        }
    }

    public boolean checkSelectedNode2() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (selectedNode2 == null) {
            context.addMessage(null, new FacesMessage(" ", "Please select a video"));
            return false;
        } else if (!selectedNode2.getType().equals("video")) {
            context.addMessage(null, new FacesMessage(" ", "Please select a video"));
            return false;
        }
        return true;
    }

    public void showVideo() {
        if (checkSelectedNode2()) {
        }
    }

    public void showEditVideoForm() {
        if (checkSelectedNode2()) {
            selectedVideo = gsb.findNewsWithName(selectedNode2.getData().toString());
            video2=video.getVideo();
            setVoep(true);
        } else {
            setVoep(false);
        }
    }

    public void deletVideo() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        if (checkSelectedNode2()) {
            selectedVideo = gsb.findNewsWithName(selectedNode2.getData().toString());
            Video v= selectedVideo.getVideo();
            cf.deletFile(selectedVideo.getHeaderImage()); 
            gsb.destroyNews(selectedVideo.getId());
            gsb.destroyVideo(v.getId());
            deletFromTopVidoes(selectedVideo.getName(),selectedVideo.getTopic(),new Topic(),new Topic());
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseVideoTree(gsb, root2);
            setVoep(false);
            context.addMessage(null, new FacesMessage("Succes", "Deleting is successful"));
        }
    }

    public void editVideo() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        News l = gsb.findNews(selectedVideo.getId());

        boolean check = true;

        if (selectedNode3 != null) {
            Topic top=gsb.findTopicWithName(selectedNode3.getData().toString());
            deletFromTopVidoes(l.getName(),l.getTopic(),top,l.getTopic());
            selectedVideo.setTopic(top);
        } else {
            selectedVideo.setTopic(l.getTopic());
        }
        if (selectedVideo.getName().trim().equals("") || selectedVideo.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (!selectedVideo.getName().equals(l.getName())) {
            if (gsb.getNewsCount(selectedVideo.getName().trim()) > 0) {
                context.addMessage(null, new FacesMessage("Name", "This name is used"));
                check = false;
            }
        }
        if (selectedVideo.getHeader().trim().equals("") || selectedVideo.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }
        if (videoLink.trim().equals("") || videoLink == null) {
            video2.setVideo(l.getVideo().getVideo());
        } else {
            try {
                String link = videoLink;
                String pl[] = link.split("watch");
                link = pl[0] + pl[1];
                pl = link.split("\\?");
                link = pl[0] + pl[1];
                pl = link.split("=");
                link = pl[0] + "/" + pl[1];
                video2.setVideo(link);
            } catch (Exception ex) {
                context.addMessage(null, new FacesMessage("Video", "Video link isn't correct"));
                check = false;
            }
        }
        if (check) {
            
            if (selectedVideo.getName()!= l.getName()){
            renameInTopVidoes(selectedVideo.getName(),l.getName(), selectedVideo.getTopic());
            }

            if (file2 == null) {
                selectedVideo.setHeaderImage(l.getHeaderImage());
            } else {
                cf.deletFile(l.getHeaderImage());
                selectedVideo.setHeaderImage(cf.saveFileToFolder(file2));
            }
            selectedVideo.setDate(new Date());
            video2.setId(l.getVideo().getId());
            gsb.editVideo(video2);
            selectedVideo.setVideo(video2); 
            gsb.editNews(selectedVideo);
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseVideoTree(gsb, root2);
            setVoep(false);
            context.addMessage(null, new FacesMessage("Succes", "Editing is successful"));
        } else {
        }

    }

    public void deletFromTopVidoes(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopVidoes(cf.deletNewestNews(t.getTopVidoes(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
           WebSite  ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopVidoes(cf.deletNewestNews(ws.getTopVidoes(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopVidoes(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }
    
    public void renameInTopVidoes(String nn,String n,Topic t) throws NonexistentEntityException, Exception{
        t.setTopVidoes(cf.renameInTop(t.getTopVidoes(), n,nn));
        gsb.editTopic(t);
        
    WebSite ws=gsb.findWebSite();
    if(t.getUpperTopic().equals("root")){
    ws.setTopVidoes(cf.renameInTop(ws.getTopVidoes(), n,nn));
    gsb.editWebsite(ws);
    }
    else {
    renameInTopVidoes(nn,n,gsb.findTopicWithName(t.getUpperTopic())); 
    }
    
    }

    public List<News> giveWebsiteTopVideos(int start, int max) throws NonexistentEntityException, Exception {
        List<News> news = new ArrayList<News>();
        String[] news2 = gsb.findWebSite().getTopVidoes().split("splitcode");
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
}
