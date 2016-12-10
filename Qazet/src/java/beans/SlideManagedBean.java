/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javassist.bytecode.stackmap.BasicBlock;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import pojoes.News;
import pojoes.Slide;
import pojoes.Topic;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "SMB")
@SessionScoped
public class SlideManagedBean implements Serializable {

    private CommonFunctions cf = new CommonFunctions();
    private GeneralSessionBean gsb = new GeneralSessionBean();
    private TreeNode root = new DefaultTreeNode("Root", null);
    private Topic topic = new Topic();
    private Topic selectedTopic = new Topic();
    private TreeNode selectedNode;
    private UploadedFile photo1;
    private UploadedFile photo2;
    private UploadedFile photo3;
    private String photoName1;
    private String photoName2;
    private String photoName3;
    private TreeFunctions tf = new TreeFunctions();
    private News slide = new News();
    private Slide slide2 = new Slide();
    private News selectedSlide = new News();
    private TreeNode root2 = new DefaultTreeNode("Root", null);
    private TreeNode root3 = new DefaultTreeNode("Root", null);
    private UploadedFile file2;
    private String fileName;
    private TreeNode selectedNode2;
    private TreeNode selectedNode3;
    private boolean voep = false;
    private boolean aoep = false;
    private boolean vopap = false;
    private boolean voap = false;
    private List<String> photos = new ArrayList<String>();

    public boolean isVoap() {
        return voap;
    }

    public void setVoap(boolean voap) {
        this.voap = voap;
    }

    public void sifirla() {
        voep = false;
        aoep = false;
        vopap = false;
        voap = false;

        photos = new ArrayList<String>();
        slide=new News();
        slide.setSlide(new Slide());
        selectedSlide = new News();

    }

    public boolean isVopap() {
        return vopap;
    }

    public void setVopap(boolean vopap) {
        this.vopap = vopap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public boolean isAoep() {
        return aoep;
    }

    public void setAoep(boolean aoep) {
        this.aoep = aoep;
    }

    public News getSlide() {
        return slide;
    }

    public void setSlide(News slide) {
        this.slide = slide;
    }

    public Slide getSlide2() {
        return slide2;
    }

    public void setSlide2(Slide slide2) {
        this.slide2 = slide2;
    }

    public News getSelectedSlide() {
        return selectedSlide;
    }

    public void setSelectedSlide(News selectedSlide) {
        this.selectedSlide = selectedSlide;
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

    public String getPhotoName1() {
        return photoName1;
    }

    public void setPhotoName1(String photoName1) {
        this.photoName1 = photoName1;
    }

    public String getPhotoName2() {
        return photoName2;
    }

    public void setPhotoName2(String photoName2) {
        this.photoName2 = photoName2;
    }

    public String getPhotoName3() {
        return photoName3;
    }

    public void setPhotoName3(String photoName3) {
        this.photoName3 = photoName3;
    }

    public UploadedFile getPhoto1() {
        return photo1;
    }

    public void setPhoto1(UploadedFile photo1) {
        this.photo1 = photo1;
    }

    public UploadedFile getPhoto2() {
        return photo2;
    }

    public void setPhoto2(UploadedFile photo2) {
        this.photo2 = photo2;
    }

    public UploadedFile getPhoto3() {
        return photo3;
    }

    public void setPhoto3(UploadedFile photo3) {
        this.photo3 = photo3;
    }

    public SlideManagedBean() {
        tf.organiseSlideTree(gsb, root2);
        tf.organiseTree(gsb, root);
        tf.organiseTree(gsb, root3);
        sifirla();
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
    private static final Logger LOG = Logger.getLogger(SlideManagedBean.class.getName());

    public void addSlide() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;
        if (selectedNode == null) {
            context.addMessage(null, new FacesMessage("Topic", "Topic isn't chossen"));
            check = false;
        } else {
            slide.setTopic(gsb.findTopicWithName(selectedNode.getData().toString()));
        }
        if (slide.getName().trim().equals("") || slide.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (gsb.getNewsCount(slide.getName().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Name", "This name is used"));
            check = false;
        }
        if (slide.getHeader().trim().equals("") || slide.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }
        if (check) {
            String photos=new String();
            if (photo1 != null) {
                photos=photos+cf.saveFileToFolder(photo1, photoName1);
            }
            if (photo2 != null) {
                photos=photos+cf.saveFileToFolder(photo2, photoName2);
            }
            if (photo3 != null) {
                photos=photos+cf.saveFileToFolder(photo3, photoName3);
            }
            Slide ns=new Slide();
            ns.setPhotos(photos);
            slide.setDate(new Date());
            slide.setSlide(gsb.creatSlide(ns)); 
            gsb.creatNews(slide);
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
            setVoap(false);
            slide = new News();
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseSlideTree(gsb, root2);
        }

    }

    public boolean checkSelectedNode2() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (selectedNode2 == null) {
            context.addMessage(null, new FacesMessage(" ", "Please select a slide"));
            return false;
        } else if (!selectedNode2.getType().equals("picture")) {
            context.addMessage(null, new FacesMessage(" ", "Please select a slide"));
            return false;
        }
        return true;
    }

    public void showSlide() {
        if (checkSelectedNode2()) {
        }
    }

    public void showEditSlideForm() {
        if (checkSelectedNode2()) {
            selectedSlide = gsb.findNewsWithName(selectedNode2.getData().toString());
            slide2=selectedSlide.getSlide();
            setVoep(true);
        } else {
            setVoep(false);
        }
    }

    public void showAddSlideForm() {
        setVoap(true);

    }

    public void showAddPhotoForm() {

        if (checkSelectedNode2()) {
            selectedSlide = gsb.findNewsWithName(selectedNode2.getData().toString());
            Slide ns=selectedSlide.getSlide();
            String ph[] = ns.getPhotos().split("photoborder");
            photos = new ArrayList<String>();
            for (int i = 0; i < ph.length; i++) {
                photos.add(ph[i]);
            }


            setVopap(true);
        } else {
            setVopap(false);
        }
    }

    public void showPhotoForm() {
        if (checkSelectedNode2()) {
            
            selectedSlide = gsb.findNewsWithName(selectedNode2.getData().toString());
            Slide ns=selectedSlide.getSlide();
            String ph[] = ns.getPhotos().split("photoborder");
            photos = new ArrayList<String>();
            for (int i = 0; i < ph.length; i++) {
                photos.add(ph[i]);
            }

            setAoep(true);
        } else {
            setAoep(false);
        }
    }

    public String getON(String s) {
        String a[] = s.split("\\.");
        System.out.println(a[0]);
        return a[0];
    }

    public List<String> getOP(News s) {
        List<String> ls=new ArrayList<String>();
        String a[] = s.getSlide().getPhotos().split("photoborder");
        for(int i=0;i<a.length;i++){
        ls.add(a[i]);
        }
        return ls;
    }
    
    public String header(News s) {
        List<String> ls=new ArrayList<String>();
        String a[] = s.getSlide().getPhotos().split("photoborder");
        for(int i=0;i<a.length;i++){
        ls.add(a[i]);
        }
        return ls.get(0);
    }

    public void deletPhotoFromSlide(String photo) throws NonexistentEntityException, Exception {
        
            Slide ns=selectedSlide.getSlide();
        Slide art = gsb.findSlide(ns.getId());
        try{
            String photos2[] = art.getPhotos().split(photo + "photoborder");
             
        cf.deletFile(photo); 
        String photos3;

        if (photos2.length == 1) {
            photos3 = photos2[0];
        } else {
            photos3 = photos2[0] + photos2[1];
        }

        art.setPhotos(photos3);
        gsb.editSlide(art);
        
        }
        catch(Exception e){
        cf.deletFile(photo);    
        art.setPhotos("");
        gsb.editSlide(art);
        
        }
        ;
        photos = new ArrayList<String>();
        selectedSlide = gsb.findNewsWithName(selectedNode2.getData().toString());
       
        String ph[] = art.getPhotos().split("photoborder");

        for (int i = 0; i < ph.length; i++) {
            photos.add(ph[i]);

        }
    }

    public void addPhoto() throws NonexistentEntityException, Exception {
        
       Slide ns=selectedSlide.getSlide();
        ns.setPhotos(ns.getPhotos() + cf.saveFileToFolder(file2, fileName));
        gsb.editSlide(ns);
        photos = new ArrayList<String>();
        String[] ph = ns.getPhotos().split("photoborder");

        for (int i = 0; i < ph.length; i++) {
            photos.add(ph[i]);

        }
    }

    public void deletSlide() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        selectedSlide = gsb.findNewsWithName(selectedNode2.getData().toString());
        Slide s=selectedSlide.getSlide();
        String ph[] = s.getPhotos().split("photoborder");

        for (int i = 0; i < ph.length; i++) {
            cf.deletFile(ph[i]); 

        }
        if (checkSelectedNode2()) {
            selectedSlide = gsb.findNews(selectedSlide.getId());
            Slide ns=selectedSlide.getSlide();
            gsb.destroyNews(selectedSlide.getId());
            gsb.destroySlide(ns.getId());
            deletFromTopSlides(selectedSlide.getName(),selectedSlide.getTopic(),new Topic(),new Topic());
            root2 = new DefaultTreeNode("Root", null);
            context.addMessage(null, new FacesMessage("Succes", "Deleting is successful"));
            tf.organiseSlideTree(gsb, root2);
            setVoep(false);
        }
    }

    public void edidSlide() throws NonexistentEntityException, Exception {
        News art = gsb.findNews(selectedSlide.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode3 != null) {
            Topic top=gsb.findTopicWithName(selectedNode3.getData().toString());
            deletFromTopSlides(art.getName(),art.getTopic(),top,art.getTopic());
            selectedSlide.setTopic(top);
        } else {
            selectedSlide.setTopic(art.getTopic());
        }



        if (selectedSlide.getName().trim().equals("") || selectedSlide.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (!selectedSlide.getName().equals(art.getName())) {
            if (gsb.getNewsCount(selectedSlide.getName().trim()) > 0) {
                context.addMessage(null, new FacesMessage("Name", "This name is used"));
                check = false;
            }
        }
        if (selectedSlide.getHeader().trim().equals("") || selectedSlide.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }

        if (check) {
            
            if (selectedSlide.getName()!= art.getName()){
            renameInTopSlides(selectedSlide.getName(),art.getName(), selectedSlide.getTopic());
            }
            
           selectedSlide.setDate(new Date());
            gsb.editSlide(slide2); 
            selectedSlide.setSlide(slide2);
            gsb.editNews(selectedSlide);
            context.addMessage(null, new FacesMessage("Succes", "Edititing is successful"));
            setVoep(false);
            root2 = new DefaultTreeNode("Root", null);
            tf.organiseSlideTree(gsb, root2);
            voep = false;
        }

    }

    public void deletFromTopSlides(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopSlides(cf.deletNewestNews(t.getTopSlides(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
           WebSite ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopSlides(cf.deletNewestNews(ws.getTopSlides(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopSlides(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }
    
    public List<News> giveNewestSlide(String t, int start, int max) throws NonexistentEntityException, Exception {
       
        return  gsb.findLastSlideNewsEntitiesWithTopicName(max, start,t);

    }
    
    public List<News> giveTopSlide(String t, int start, int max) throws NonexistentEntityException, Exception {
       
        List<News> news = new ArrayList<News>();
        String[] news2 = gsb.findTopicWithName(t).getTopSlides().split("splitcode");
        if (news2.length < max) {
            max = news2.length;
        }
        for (int i = start; i < max; i++) {
            news.add(gsb.findNewsWithName(news2[i]));
        }
        return news;

    }
    
    public void renameInTopSlides(String nn,String n,Topic t) throws NonexistentEntityException, Exception{
        t.setTopSlides(cf.renameInTop(t.getTopSlides(), n,nn));
        gsb.editTopic(t);
        
    WebSite ws=gsb.findWebSite();
    if(t.getUpperTopic().equals("root")){
    ws.setTopSlides(cf.renameInTop(ws.getTopSlides(), n,nn));
    gsb.editWebsite(ws);
    }
    else {
    renameInTopSlides(nn,n,gsb.findTopicWithName(t.getUpperTopic())); 
    }
    
    }



}
