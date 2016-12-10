/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Farid
 */
@Entity
public class WebSite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String shortName;
    private String popLimit;
    private String Title;
    @Column(columnDefinition="TEXT")
    private String keyWords;
    @Column(name="toparticles",nullable=true,unique=false,columnDefinition="TEXT")
    private String topArticles;
    @Column(name="toptopics",nullable=true,unique=false,columnDefinition="TEXT")
    private String topTopics;
    @Column(name="topvidoes",nullable=true,unique=false,columnDefinition="TEXT")
    private String topVidoes;
    @Column(name="topslides",nullable=true,unique=false,columnDefinition="TEXT")
    private String topSlides;
    @Column(name="toppolles",nullable=true,unique=false,columnDefinition="TEXT")
    private String topPolles;
    @Column(name="contacts",nullable=true,unique=false,columnDefinition="TEXT")
    private String contacts;
    @Column(name="information",nullable=true,unique=false,columnDefinition="TEXT")
    private String information;
    private String header;
    private String color;
    private String banner;
    private String logo;
    private String whiteLogo;
  
  
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WebSite)) {
            return false;
        }
        WebSite other = (WebSite) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPopLimit() {
        return popLimit;
    }

    public void setPopLimit(String popLimit) {
        this.popLimit = popLimit;
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWhiteLogo() {
        return whiteLogo;
    }

    public void setWhiteLogo(String whiteLogo) {
        this.whiteLogo = whiteLogo;
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
    
    

  
    
}
