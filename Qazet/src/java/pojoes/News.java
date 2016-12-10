/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Farid
 */
@Entity
public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="name",unique=true,nullable=false)
    private String name;
    @ManyToOne
    @JoinColumn(name="topic_id",unique=false,nullable=false)
    private Topic topic;
    @Column(name="ViewCount")
    private int viewCount;
    @Column(name="ShareCount")
    private int shareCount;
    @Column(name="Weightiness",unique=false)
    private int weightiness;
    @Column(name="Date",unique=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    @Column(name="header",nullable=false)
    private String header;
    @Column(name="headerimage")
    private String headerImage;
    @Column(name="explaintext",nullable=true,unique=false,columnDefinition="TEXT")
    private String explainText;
    @OneToOne
    @JoinColumn(name="link_id")
    private Link link;
    @OneToOne
    @JoinColumn(name="article_id")
    private Article article;
    @OneToOne
    @JoinColumn(name="video_id")
    private Video video;
    @OneToOne
    @JoinColumn(name="slide_id")
    private Slide slide;
    @OneToOne
    @JoinColumn(name="poll_id")
    private Poll poll;
    @Column(name="keywords",nullable=true,unique=false,columnDefinition="TEXT")
    private String keyWords;
    

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
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojoes.News[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getWeightiness() {
        return weightiness;
    }

    public void setWeightiness(int weightiness) {
        this.weightiness = weightiness;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getExplainText() {
        return explainText;
    }

    public void setExplainText(String explainText) {
        this.explainText = explainText;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
    
    
    
}
