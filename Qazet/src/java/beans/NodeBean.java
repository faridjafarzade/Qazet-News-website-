/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.xml.sax.SAXException;
import pojoes.Topic;

/**
 *
 * @author Farid
 */
@ManagedBean(name = "nodeBean")
@RequestScoped
public class NodeBean   implements Serializable {

    GeneralSessionBean gsb = new GeneralSessionBean();
    private TreeNode root;
    private Topic topic = new Topic();
    private Topic selectedTopic = new Topic();
    private TreeNode selectedNode;
    private TreeNode[] selectedNodes;
    private List<TreeNode> treeNodes2;

    public List<TreeNode> getTreeNodes2() {
        return treeNodes2;
    }

    public void setTreeNodes2(List<TreeNode> treeNodes2) {
        this.treeNodes2 = treeNodes2;
    }
   

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public NodeBean() {
        organiseTree();

    }

    public List<TreeNode> turnToTree(List<Topic> topics) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (Topic t : topics) {
            trees.add(new DefaultTreeNode(t.getName(), null));
        }
        return trees;

    }

    public void findTree(List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            List<TreeNode> trees2 = turnToTree(gsb.findTopics(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            findTree(trees2);
        }
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

    public void addTopics() {

        if (selectedNode == null) {
            topic.setUpperTopic("root");
        } else {
            topic.setUpperTopic(selectedNode.getData().toString());
        }

        gsb.creatTopic(topic);
        organiseTree();

    }

    public void organiseTree() {
        root = new DefaultTreeNode("Root", null);
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTree(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findTree(trees);
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void displaySelectedSingle(ActionEvent event) {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(message);
        }
    }

    public void wt() throws JAXBException, SAXException {
        List<Topic> liste2 = new ArrayList<Topic>();
        liste2 = gsb.findTopics("root");


        for (Topic li : liste2) {
            String stringt = li.getName() + " " + li.getUpperTopic();
            System.out.print(stringt);
        }


    }
}
