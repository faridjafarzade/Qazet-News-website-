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
import pojoes.Link;
import pojoes.News;
import pojoes.Topic;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "LMB")
@RequestScoped
public class LinkManagedBean  implements Serializable {

    private CommonFunctions cf = new CommonFunctions();
    private GeneralSessionBean gsb = new GeneralSessionBean();
    private TreeNode root = new DefaultTreeNode("Root", null);
    private Topic topic = new Topic();
    private Topic selectedTopic = new Topic();
    private TreeNode selectedNode;
    private UploadedFile file;
    private News link = new News();
    private TreeFunctions tf = new TreeFunctions();
    private News selectedLink = new News();
    private Link link2=new Link();
    private TreeNode root2 = new DefaultTreeNode("Root", null);
    private TreeNode root3 = new DefaultTreeNode("Root", null);
    private UploadedFile file2;
    private TreeNode selectedNode2;
    private TreeNode selectedNode3;
    private boolean voep = false;
    private boolean voap = false;

    public boolean isVoap() {
        return voap;
    }

    public void setVoap(boolean voap) {
        this.voap = voap;
    }

    public News getLink() {
        return link;
    }

    public void setLink(News link) {
        this.link = link;
    }

    public News getSelectedLink() {
        return selectedLink;
    }

    public void setSelectedLink(News selectedLink) {
        this.selectedLink = selectedLink;
    }

    public Link getLink2() {
        return link2;
    }

    public void setLink2(Link link2) {
        this.link2 = link2;
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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile upfile) {
        this.file = upfile;
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

    public LinkManagedBean() {

        tf.organiseLinkTree(gsb, root2);
        tf.organiseTree(gsb, root);
        tf.organiseTree(gsb, root3);
        link.setLink(new Link()); 

    }
    
    public void showAddLinkForm() {
      setVoap(true);
        
    }

    public void addLink() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;
        if (selectedNode == null) {
            context.addMessage(null, new FacesMessage("Topic", "Topic isn't chossen"));
            check = false;
        } else {
            link.setTopic(gsb.findTopicWithName(selectedNode.getData().toString()));
        }
        if (link.getName().trim().equals("") || link.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (gsb.getNewsCount(link.getName().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Name", "This name is used"));
            check = false;
        }
        if (link.getLink().getLink().trim().equals("") || link.getLink() == null) {
            context.addMessage(null, new FacesMessage("Link", "There isn't Link"));
            check = false;
        }
        if (link.getHeader().trim().equals("") || link.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }
        if (check) {
            link.setHeaderImage(cf.saveFileToFolder(file));
            link.setDate(new Date());
            Link nl=link.getLink();
            link.setLink(gsb.creatLink(nl)); 
            gsb.creatNews(link);
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseLinkTree(gsb, root2);
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

    public void showLink() {
        if (checkSelectedNode2()) {
        }
    }

    public void showEditLinkForm() {
        if (checkSelectedNode2()) {
            selectedLink = gsb.findNewsWithName(selectedNode2.getData().toString());
            link2=selectedLink.getLink();
            setVoep(true);
        } else {
            setVoep(false);
        }
    }

    public void deletLink() throws NonexistentEntityException, Exception {
      FacesContext context = FacesContext.getCurrentInstance();
        if (checkSelectedNode2()) {
            selectedLink = gsb.findNewsWithName(selectedNode2.getData().toString());
            cf.deletFile(selectedLink.getHeaderImage()); 
            Link l=selectedLink.getLink();
            gsb.destroyNews(selectedLink.getId()); 
            gsb.destroyLink(l.getId());
            deletFromTopArticles(selectedLink.getName(),selectedLink.getTopic(),new Topic(),new Topic());
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseLinkTree(gsb, root2);
            setVoep(false);
            context.addMessage(null, new FacesMessage("Succes", "Deleting is successful"));
        }
    }

    public void edidLink() throws NonexistentEntityException, Exception {
        News l = gsb.findNews(selectedLink.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode3 != null) {
            Topic top=gsb.findTopicWithName(selectedNode3.getData().toString());
            deletFromTopArticles(l.getName(),l.getTopic(),top,l.getTopic());
            selectedLink.setTopic(top);
        } else {
            selectedLink.setTopic(l.getTopic());
        }

        if (selectedLink.getName().trim().equals("") || selectedLink.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (!selectedLink.getName().equals(l.getName())) {
            if (gsb.getNewsCount(selectedLink.getName().trim()) > 0) {
                context.addMessage(null, new FacesMessage("Name", "This name is used"));
                check = false;
            }
        }
        if (selectedLink.getHeader().trim().equals("") || selectedLink.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }
        if (link2.getLink().trim().equals("") || link2.getLink() == null) {
            context.addMessage(null, new FacesMessage("Link", "There isn't Link"));
            check = false;
        }

        if (check) {
            
            if (selectedLink.getName()!= l.getName()){
            renameInTopArticles(selectedLink.getName(),l.getName(), selectedLink.getTopic());
            }

            if (file2 == null) {
                selectedLink.setHeaderImage(l.getHeaderImage());
            } else {
                cf.deletFile(l.getHeaderImage()); 
                selectedLink.setHeaderImage(cf.saveFileToFolder(file2));
            }
            selectedLink.setDate(new Date());
            gsb.editLink(link2);
            selectedLink.setLink(link2); 
            gsb.editNews(selectedLink); 
            context.addMessage(null, new FacesMessage("Succes", "Edititing is successful"));
            setVoep(false);
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseLinkTree(gsb, root2);
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
}
