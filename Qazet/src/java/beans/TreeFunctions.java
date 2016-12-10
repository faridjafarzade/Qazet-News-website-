/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pojoes.Article;
import pojoes.Link;
import pojoes.News;
import pojoes.Poll;
import pojoes.Slide;
import pojoes.Topic;
import pojoes.Video;

/**
 *
 * @author Farid
 */
public class TreeFunctions {
    
    //functions of turn

    public List<TreeNode> turnToTreeFromTopic(List<Topic> topics) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (Topic t : topics) {
            trees.add(new DefaultTreeNode(t.getName(), null));
        }
        return trees;

    }
    
    
    
    public List<TreeNode> turnToTreeFromNews(List<News> news) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (News a : news) {
            if(a.getVideo()!=null){
            trees.add(new DefaultTreeNode("video",a.getName(), null));
            }
            else if(a.getSlide()!=null){
            trees.add(new DefaultTreeNode("picture",a.getName(), null));
            }
            else{
            trees.add(new DefaultTreeNode("document",a.getName(), null));
            }
         
        }
        return trees;

    }
    
   
    
    public List<TreeNode> turnToPictureTree(List<News> objects) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (News o : objects) { 
            trees.add(new DefaultTreeNode("picture",o.getName(), null));
        }
        return trees;

    }
    
    public List<TreeNode> turnToVideoTree(List<News> objects) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (News o : objects) { 
            trees.add(new DefaultTreeNode("video",o.getName(), null));
        }
        return trees;

    }
    
    public List<TreeNode> turnToDocumentTree(List<News> objects) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (News o : objects) { 
            trees.add(new DefaultTreeNode("document",o.getName(), null));
        }
        return trees;

    }
    
  
    
    //functions of find
    
    
    public void findTree(GeneralSessionBean gsb, List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            List<TreeNode> trees2 = turnToTreeFromTopic(gsb.findTopics(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            findTree(gsb, trees2);


        }
    }
    
    
    public void findAllTree(GeneralSessionBean gsb, List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            List<TreeNode> trees2 = turnToTreeFromTopic(gsb.findTopics(tr.getData().toString()));
            List<TreeNode> treesNews=turnToTreeFromNews(gsb.findNewsEntitiesWithTopicName(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            
            for (TreeNode trArticle : treesNews) {
                trArticle.setParent(tr);
            }
            if(trees2.size()>0){
            findAllTree(gsb, trees2);}


        }
    }

   

    public void findArticleTree(GeneralSessionBean gsb, List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            
            List<TreeNode> trees2 = turnToTreeFromTopic(gsb.findTopics(tr.getData().toString()));
            List<TreeNode> treesArticle=turnToDocumentTree(gsb.findArticleNewsEntitiesWithTopicName(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            
            for (TreeNode trArticle : treesArticle) {
                trArticle.setParent(tr);
            }
            findArticleTree(gsb, trees2);


        }
    }

    public void findLinkTree(GeneralSessionBean gsb, List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            List<TreeNode> trees2 = turnToTreeFromTopic(gsb.findTopics(tr.getData().toString()));
            List<TreeNode> treesLink=turnToDocumentTree(gsb.findLinkNewsEntitiesWithTopicName(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            
            for (TreeNode trLink : treesLink) {
                trLink.setParent(tr);
            }
            findLinkTree(gsb, trees2);


        }
    }

    public void findVideoTree(GeneralSessionBean gsb, List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            
            List<TreeNode> trees2 = turnToTreeFromTopic(gsb.findTopics(tr.getData().toString()));
            List<TreeNode> treesVideo=turnToVideoTree(gsb.findVideoNewsEntitiesWithTopicName(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            
            for (TreeNode trVideo : treesVideo) {
                trVideo.setParent(tr);
            }
            findVideoTree(gsb, trees2);


        }
    }

    public void findPollTree(GeneralSessionBean gsb, List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            
            List<TreeNode> trees2 = turnToTreeFromTopic(gsb.findTopics(tr.getData().toString()));
            List<TreeNode> treesPoll=turnToDocumentTree(gsb.findPollNewsEntitiesWithTopicName(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            
            for (TreeNode trPoll : treesPoll) {
                trPoll.setParent(tr);
            }
            findPollTree(gsb, trees2);


        }
    }

    public void findSlideTree(GeneralSessionBean gsb, List<TreeNode> trees) {
        for (TreeNode tr : trees) {
            
            List<TreeNode> trees2 = turnToTreeFromTopic(gsb.findTopics(tr.getData().toString()));
            List<TreeNode> treesSlide=turnToPictureTree(gsb.findSlideNewsEntitiesWithTopicName(tr.getData().toString()));
            for (TreeNode tr2 : trees2) {
                tr2.setParent(tr);
            }
            
            for (TreeNode trSlide : treesSlide) {
                trSlide.setParent(tr);
            }
            findSlideTree(gsb, trees2);


        }
    }
    
    
    //function of organize
    
    
    public void organiseTree(GeneralSessionBean gsb, TreeNode root) {
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTreeFromTopic(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findTree(gsb, trees);
    }
    
    public void organiseArticleTree(GeneralSessionBean gsb, TreeNode root) {
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTreeFromTopic(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findArticleTree(gsb, trees);
    }
    
    public void organiseLinkTree(GeneralSessionBean gsb, TreeNode root) {
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTreeFromTopic(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findLinkTree(gsb, trees);
    }
    
    public void organisePollTree(GeneralSessionBean gsb, TreeNode root) {
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTreeFromTopic(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findPollTree(gsb, trees);
    }
    
    public void organiseVideoTree(GeneralSessionBean gsb, TreeNode root) {
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTreeFromTopic(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findVideoTree(gsb, trees);
    }
    
    public void organiseSlideTree(GeneralSessionBean gsb, TreeNode root) {
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTreeFromTopic(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findSlideTree(gsb, trees);
    }
    
    public void organiseAllTree(GeneralSessionBean gsb, TreeNode root) {
        List<Topic> topics = gsb.findTopics("root");
        List<TreeNode> trees = turnToTreeFromTopic(topics);
        for (TreeNode tr : trees) {
            tr.setParent(root);

        }
        findAllTree(gsb, trees);
    }
}
