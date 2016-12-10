/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import pojoes.Image;

/**
 *
 * @author Farid
 */
@ManagedBean(name="IMB")
@SessionScoped
public class ImageManagedBean  implements Serializable  {

   private Image image=new Image ();
   private GeneralSessionBean gsb=new GeneralSessionBean();
   private UploadedFile file;
   private CommonFunctions cf=new CommonFunctions();
   private List<Image> images =gsb.findListImages();

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
   
   
   
   public ImageManagedBean() {
   }
    
   public void createImage(){
   Image image=new Image ();
   String s=cf.saveFileToFolder(file);
   image.setUrl(s); 
   gsb.creatImage(image); 
   images =gsb.findListImages();
      FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded, url= ../resources/Images/"+s);  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
   }
   
   public void deletImage(String image,int id) throws NonexistentEntityException{
       
   cf.deletFile(image); 
   gsb.destroyImage(id);
   images =gsb.findListImages();
   }
   
   public void handleFileUpload(FileUploadEvent event) { 
       file=event.getFile();
       createImage();
     
    }
}
