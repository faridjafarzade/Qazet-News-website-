/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Farid
 */
@Entity
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="name",unique=true,nullable=false)
    private String name;
    private String Title;
    private String keyWords;
    @Column(columnDefinition="TEXT")
    private String color;
    @Column(name="upperTopic",nullable=false)
    private String upperTopic;
    @OneToMany(mappedBy = "topic")
    private List<News> news=new ArrayList<News>();
    @Column(name="toparticles",nullable=true,unique=false,columnDefinition="TEXT")
    private String topArticles;
    @Column(name="topvidoes",nullable=true,unique=false,columnDefinition="TEXT")
    private String topVidoes;
    @Column(name="topslides",nullable=true,unique=false,columnDefinition="TEXT")
    private String topSlides;
    @Column(name="toppolles",nullable=true,unique=false,columnDefinition="TEXT")
    private String topPolles;
    @Column(name="toptopics",nullable=true,unique=false,columnDefinition="TEXT")
    private String topTopics;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Topic[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    public String getTopVidoes() {
        return topVidoes;
    }

    public void setTopVidoes(String topVidoes) {
        this.topVidoes = topVidoes;
    }

    public String getTopSlides() {
        return topSlides;
    }

    public void setTopSlides(String topSlides) {
        this.topSlides = topSlides;
    }

    public String getTopPolles() {
        return topPolles;
    }

    public void setTopPolles(String topPolles) {
        this.topPolles = topPolles;
    }


    
   
    /**
     * @return the upperTopicId
     */
    public String getUpperTopic() {
        return upperTopic;
    }

    /**
     * @param upperTopicId the upperTopicId to set
     */
    public void setUpperTopic(String upperTopic) {
        this.upperTopic = upperTopic;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public String getTopNews() {
        return topArticles;
    }

    public void setTopNews(String topNews) {
        this.topArticles = topNews;
    }

    public String getTopArticles() {
        return topArticles;
    }

    public void setTopArticles(String topArticles) {
        this.topArticles = topArticles;
    }

    public String getTopTopics() {
        return topTopics;
    }

    public void setTopTopics(String topTopics) {
        this.topTopics = topTopics;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
    
    
}
