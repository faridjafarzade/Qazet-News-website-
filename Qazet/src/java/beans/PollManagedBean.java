/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import pojoes.News;
import pojoes.Poll;
import pojoes.Topic;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "PMB")
@SessionScoped
public class PollManagedBean implements Serializable {

    private CommonFunctions cf = new CommonFunctions();
    private GeneralSessionBean gsb = new GeneralSessionBean();
    private TreeNode root = new DefaultTreeNode("Root", null);
    private Topic topic = new Topic();
    private Topic selectedTopic = new Topic();
    private TreeNode selectedNode;
    private UploadedFile file;
    private TreeFunctions tf = new TreeFunctions();
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private News poll = new News();
    private Poll poll2 = new Poll();
    private News selectedPoll = new News();
    private TreeNode root2 = new DefaultTreeNode("Root", null);
    private TreeNode root3 = new DefaultTreeNode("Root", null);
    private UploadedFile file2;
    private TreeNode selectedNode2;
    private TreeNode selectedNode3;
    private boolean voep = false;
    private boolean aoep = false;
    private boolean vopap = false;
    private boolean voap = false;
    private List<String> items = new ArrayList<String>();
    private String itemName;

    public void sifirla() {
        voep = false;
        aoep = false;
        vopap = false;
        voap = false;
        items = new ArrayList<String>();
        poll = new News();
        poll.setPoll(new Poll());
        selectedPoll = new News();
        item1 = new String();
        item2 = new String();
        item3 = new String();
        item4 = new String();
        item5 = new String();
    }

    public boolean isAoep() {
        return aoep;
    }

    public void setAoep(boolean aoep) {
        this.aoep = aoep;
    }

    public boolean isVopap() {
        return vopap;
    }

    public void setVopap(boolean vopap) {
        this.vopap = vopap;
    }

    public boolean isVoap() {
        return voap;
    }

    public void setVoap(boolean voap) {
        this.voap = voap;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public News getPoll() {
        return poll;
    }

    public void setPoll(News poll) {
        this.poll = poll;
    }

    public Poll getPoll2() {
        return poll2;
    }

    public void setPoll2(Poll poll2) {
        this.poll2 = poll2;
    }

    public News getSelectedPoll() {
        return selectedPoll;
    }

    public void setSelectedPoll(News selectedPoll) {
        this.selectedPoll = selectedPoll;
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

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public PollManagedBean() {
        tf.organisePollTree(gsb, root2);
        tf.organiseTree(gsb, root);
        tf.organiseTree(gsb, root3);
        Poll a=new Poll();
        a.setActive(true); 
        poll.setPoll(a); 
        
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

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void addPoll() throws NonexistentEntityException, Exception {

        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode == null) {
            context.addMessage(null, new FacesMessage("Topic", "Topic isn't chossen"));
            check = false;
        } else {
            poll.setTopic(gsb.findTopicWithName(selectedNode.getData().toString()));
        }
        if (poll.getName().trim().equals("") || poll.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (gsb.getNewsCount(poll.getName().trim()) > 0) {
            context.addMessage(null, new FacesMessage("Name", "This name is used"));
            check = false;
        }
        if (poll.getHeader().trim().equals("") || poll.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }
        if (check) {
            String items2 = "";
            List<String> items3 = new ArrayList<String>();
            if (item1.trim() != "" & !item1.trim().equals("")) {
                boolean cr = true;
                if (items3.size() > 0) {
                    for (String s : items3) {
                        if (item1.equals(s)) {
                            cr = false;
                        }
                    }
                    if (cr) {
                        items3.add(item1);
                    }
                } else {
                    items3.add(item1);
                }
            }
            if (item2.trim() != "" & !item2.trim().equals("")) {
                boolean cr = true;
                if (items3.size() > 0) {
                    for (String s : items3) {
                        if (item2.equals(s)) {
                            cr = false;
                        }
                    }
                    if (cr) {
                        items3.add(item2);
                    }
                } else {
                    items3.add(item2);
                }
            }
            if (item3.trim() != "" & !item3.trim().equals("")) {
                boolean cr = true;
                if (items3.size() > 0) {
                    for (String s : items3) {
                        if (item3.equals(s)) {
                            cr = false;
                        }
                    }
                    if (cr) {
                        items3.add(item3);
                    }
                } else {
                    items3.add(item3);
                }
            }
            if (item4.trim() != "" & !item4.trim().equals("")) {
                boolean cr = true;
                if (items3.size() > 0) {
                    for (String s : items3) {
                        if (item4.equals(s)) {
                            cr = false;
                        }
                    }
                    if (cr) {
                        items3.add(item4);
                    }
                } else {
                    items3.add(item4);
                }
            }
            if (item5.trim() != "" & !item5.trim().equals("")) {
                boolean cr = true;
                if (items3.size() > 0) {
                    for (String s : items3) {
                        if (item5.equals(s)) {
                            cr = false;
                        }
                    }
                    if (cr) {
                        items3.add(item5);
                    }
                } else {
                    items3.add(item5);
                }
            }

            for (String s : items3) {
                items2 = items2 + s + "valueborder0itemborder";;
            }
            Poll np=new Poll();
            np.setActive(poll.getPoll().getActive()); 
            np.setItems(items2);
            poll.setHeaderImage(cf.saveFileToFolder(file));
            poll.setDate(new Date());
            poll.setPoll(gsb.creatPoll(np)); 
            gsb.creatNews(poll);
           voap = false;
            poll = new News();
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
            root2 = new DefaultTreeNode("Root", null);
            tf.organisePollTree(gsb, root2);
        }

    }

    public void showAddPollForm() {
        setVoap(true);

    }

    public void showAddItemForm() {

        if (checkSelectedNode2()) {
            selectedPoll = gsb.findNewsWithName(selectedNode2.getData().toString());
            String ph[] = selectedPoll.getPoll().getItems().split("itemborder");
            items = new ArrayList<String>();
            for (int i = 0; i < ph.length; i++) {
                items.add(ph[i]);
            }
            setVopap(true);
        } else {
            setVopap(false);
        }
    }

    public void showItemForm() {
        if (checkSelectedNode2()) {
            selectedPoll = gsb.findNewsWithName(selectedNode2.getData().toString());
            String ph[] = selectedPoll.getPoll().getItems().split("itemborder");
            items = new ArrayList<String>();
            for (int i = 0; i < ph.length; i++) {
                items.add(ph[i]);
            }

            setAoep(true);
        } else {
            setAoep(false);
        }
    }

    public void deletItemFromPoll(String item) throws NonexistentEntityException, Exception {
        Poll art = gsb.findPoll(selectedPoll.getPoll().getId());
        String items2[] = art.getItems().split(item + "itemborder");
        String items3;

        if (items2.length == 1) {
            System.out.println(items2[0]);
            items3 = items2[0];
        } else {
            items3 = items2[0] + items2[1];
            System.out.println(items2[0]);
            System.out.println(items2[1]);
        }
        System.out.println(items3);
        art.setItems(items3);
        gsb.editPoll(art);
        selectedPoll = gsb.findNews(selectedPoll.getId());
        items = new ArrayList<String>();
        String ph[] = selectedPoll.getPoll().getItems().split("itemborder");
        items = new ArrayList<String>();

        for (int i = 0; i < ph.length; i++) {
            items.add(ph[i]);
        }

        setAoep(true);
    }

    public void addItem() throws NonexistentEntityException, Exception {
        boolean cr = true;
        FacesContext context = FacesContext.getCurrentInstance();
        for (String s : items) {
            if (itemName.equals(Value(s))) {
                cr = false;
            }
        }
        if (cr) {
            Poll np=selectedPoll.getPoll();
            np.setItems(np.getItems() + itemName + "valueborder0itemborder");
            gsb.editPoll(np);
            items = new ArrayList<String>();
            String ph[] = np.getItems().split("itemborder");
            items = new ArrayList<String>();
            for (int i = 0; i < ph.length; i++) {
                items.add(ph[i]);
            }
        } else {
            context.addMessage(null, new FacesMessage("Item", "Item is exist , please try another name"));
        }
    }

    public boolean checkSelectedNode2() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (selectedNode2 == null) {
            context.addMessage(null, new FacesMessage(" ", "Please select a poll"));
            return false;
        } else if (!selectedNode2.getType().equals("document")) {
            context.addMessage(null, new FacesMessage(" ", "Please select a poll"));
            return false;
        }
        return true;
    }

    public void showPoll() {
        if (checkSelectedNode2()) {
        }
    }

    public void showEditPollForm() {
        if (checkSelectedNode2()) {
            selectedPoll = gsb.findNewsWithName(selectedNode2.getData().toString());
            poll2=selectedPoll.getPoll();
            setVoep(true);
        } else {
            setVoep(false);
        }
    }

    public void deletPoll() throws NonexistentEntityException, Exception {
        if (checkSelectedNode2()) {
            FacesContext context = FacesContext.getCurrentInstance();
            selectedPoll = gsb.findNewsWithName(selectedNode2.getData().toString());
            Poll p= selectedPoll.getPoll();
            cf.deletFile(selectedPoll.getHeaderImage());
            gsb.destroyNews(selectedPoll.getId()); 
            gsb.destroyPoll(p.getId()); 
            deletFromTopPolles(selectedPoll.getName(),selectedPoll.getTopic(),new Topic(),new Topic());
            root2 = new DefaultTreeNode("Root", null);
            context.addMessage(null, new FacesMessage("Succes", "Deleting is successful"));
            tf.organisePollTree(gsb, root2);
            setVoep(false);
        }
    }

    public String Key(String item) {
        String n[] = item.split("valueborder");
        return n[1];
    }

    public String Value(String item) {
        String n[] = item.split("valueborder");
        return n[0];
    }

    public void editPoll() throws NonexistentEntityException, Exception {
        News art = gsb.findNews(selectedPoll.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;

        if (selectedNode3 != null) {
            Topic top=gsb.findTopicWithName(selectedNode3.getData().toString());
            deletFromTopPolles(art.getName(),art.getTopic(),top,art.getTopic());
            selectedPoll.setTopic(top);
        } else {
            selectedPoll.setTopic(art.getTopic());
        }



        if (selectedPoll.getName().trim().equals("") || selectedPoll.getName() == null) {
            context.addMessage(null, new FacesMessage("Name", "There isn't name"));
            check = false;
        } else if (!selectedPoll.getName().equals(art.getName())) {
            if (gsb.getNewsCount(selectedPoll.getName().trim()) > 0) {
                context.addMessage(null, new FacesMessage("Name", "This name is used"));
                check = false;
            }
        }
        if (selectedPoll.getHeader().trim().equals("") || selectedPoll.getHeader() == null) {
            context.addMessage(null, new FacesMessage("Header", "There isn't header"));
            check = false;
        }

        if (check) {
            
            if (selectedPoll.getName()!= art.getName()){
            renameInTopPolles(selectedPoll.getName(),art.getName(), selectedPoll.getTopic());
            }

            if (file2 == null) {
                selectedPoll.setHeaderImage(art.getHeaderImage());
            } else {
                art.setHeaderImage(cf.saveFileToFolder(file2));
                cf.deletFile(selectedPoll.getHeaderImage());
            }
            selectedPoll.setDate(new Date());
            gsb.editPoll(poll2);
            selectedPoll.setPoll(poll2); 
            gsb.editNews(selectedPoll); 
            context.addMessage(null, new FacesMessage("Succes", "Edititing is successful"));
            setVoep(false);
            voep = false;
            
            selectedPoll = new News();
            root2 = new DefaultTreeNode("Root", null);
            tf.organisePollTree(gsb, root2);
        }

    }

    public void deletFromTopPolles(String n, Topic t, Topic tt, Topic ot) throws NonexistentEntityException, Exception {
        t.setTopPolles(cf.deletNewestNews(t.getTopPolles(), n));
        gsb.editTopic(t);
        if (!t.equals(tt) && !t.getUpperTopic().equals(tt.getUpperTopic())&&!t.equals(ot)) {
          WebSite  ws = gsb.findWebSite();
            if (t.getUpperTopic().equals("root")) {
                ws.setTopPolles(cf.deletNewestNews(ws.getTopPolles(), n));
                gsb.editWebsite(ws);
            } else {
                deletFromTopPolles(n, gsb.findTopicWithName(t.getUpperTopic()), tt,ot);
            }
        }
    }
    
    public void renameInTopPolles(String nn,String n,Topic t) throws NonexistentEntityException, Exception{
        t.setTopPolles(cf.renameInTop(t.getTopPolles(), n,nn));
        gsb.editTopic(t);
        
    WebSite ws=gsb.findWebSite();
    if(t.getUpperTopic().equals("root")){
    ws.setTopPolles(cf.renameInTop(ws.getTopPolles(), n,nn));
    gsb.editWebsite(ws);
    }
    else {
    renameInTopPolles(nn,n,gsb.findTopicWithName(t.getUpperTopic())); 
    }
    
    }
    
}
