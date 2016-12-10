/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Farid
 */
@Entity
public class Advertisement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue
    private int id;
    @Column(unique=true)
    private String index;
    private String type;
    @Transient
    private String width;
    @Transient
    private String height;
    @Transient
    private String isSwf;
    @Transient
    private String isnSwf;
    private String title;
    private String url;
    private int power;
    @Column(name="Enddate",unique=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endDate;

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }@Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advertisement)) {
            return false;
        }
        Advertisement other = (Advertisement) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Advertisement[ id=" + id + " ]";
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIsSwf() {
        return isSwf;
    }

    public void setIsSwf(String isSwf) {
        this.isSwf = isSwf;
    }

    public String getIsnSwf() {
        return isnSwf;
    }

    public void setIsnSwf(String isnSwf) {
        this.isnSwf = isnSwf;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String link) {
        this.url = link;
    }
    
    
   
   
}
