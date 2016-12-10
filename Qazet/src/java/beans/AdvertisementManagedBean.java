/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.UploadedFile;
import pojoes.Advertisement;

/**
 *
 * @author Farid
 */

@ManagedBean(name = "AdvMB")
@RequestScoped
public class AdvertisementManagedBean {
    
    private CommonFunctions cf = new CommonFunctions();
    private GeneralSessionBean gsb = new GeneralSessionBean();
    private boolean voap = false;
    private boolean voep = false;
    private boolean vosp = false;
    private Advertisement newAdv=new Advertisement();
    private Advertisement selectedAdv=new Advertisement();
    private String newAdvType;
    private String newAdvFormat;
    private SelectItem selectedTypeSI;
    private SelectItem selectedAdvFormatSI;
    private UploadedFile file;
    private UploadedFile file2;
    private Date date1;
    private List<Advertisement> advertisements =gsb.findAdvertisements();
    
    
     public void showAddAdvertisement() {
        setVoap(true);

    }

     public void showEditAdvertisementForm() {
        if (selectedAdv!=null) {
            setVoep(true);
        } else {
            setVoep(false);
        }
    }
     
     

     public void showAdvertisement() {
        if (selectedAdv!=null) {
            setVosp(true);
            selectedAdv.setHeight(height(selectedAdv.getType()));
            selectedAdv.setWidth(width(selectedAdv.getType()));
            selectedAdv.setIsSwf(isSwf(selectedAdv.getIndex()));
            selectedAdv.setIsnSwf(isnSwf(selectedAdv.getIndex()));
        } else {
            setVosp(false);
        }
    }
     
     public void addAdvertisement(){
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;
       
        if (file == null) {
               context.addMessage(null, new FacesMessage("File", "Advertisement is not choosen"));
               check = false;
            } else {
                newAdv.setIndex(cf.saveFlashFileToFolder(file));
            }
       
        if (newAdv.getEndDate() == null) {
               context.addMessage(null, new FacesMessage("End Date", "End Date is not choosen"));
               check = false;
            } 
       
        if (newAdv.getUrl() == null) {
               context.addMessage(null, new FacesMessage("Url", "Url Date is not choosen"));
               check = false;
            }
        
     if (check) {
            
            gsb.creatAdvertisement(newAdv);
            advertisements =gsb.findAdvertisements();
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
        }
     
     }
     
     public String color(Advertisement a){
         Date r=new Date();
     if(a.getEndDate().before(r)){
     return "red";
     }
     else{return "black";}
     }

    public void deletAdvertisement() throws NonexistentEntityException, Exception {
        
        FacesContext context = FacesContext.getCurrentInstance();
        if (selectedAdv!=null) {
            cf.deletFlashFile(selectedAdv.getIndex());
            gsb.destroyAdvertisement(selectedAdv.getId());
            advertisements =gsb.findAdvertisements();
            context.addMessage(null, new FacesMessage("Succes", "Deleting is successful"));
           
           
            
        }
        else{
         context.addMessage(null, new FacesMessage("File", "Advertisement is not choosen"));
        }
    }

    public void deletOldAdvertisement() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
            Date d=new Date();
            advertisements =gsb.findAdvertisements();
            for(Advertisement a:advertisements){
            if(a.getEndDate().before(d)){
            gsb.destroyAdvertisement(a.getId());
            cf.deletFlashFile(a.getIndex());
            }
            }
            advertisements =gsb.findAdvertisements();
            context.addMessage(null, new FacesMessage("Succes", "Deleting is successful"));
      }
    
    public void selectAdv(){
        System.out.println(selectedAdv.getId());
        System.out.println(selectedAdv.getType());
    
        
    
    }

    public void editAdvertisement() throws NonexistentEntityException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean check = true;
        Advertisement adv=gsb.findAdvertisement(selectedAdv.getId());
        
       
        if (selectedAdv.getEndDate() == null) {
               context.addMessage(null, new FacesMessage("End Date", "End Date is not choosen"));
               check = false;
            } 
       
        if (selectedAdv.getUrl() == null) {
               context.addMessage(null, new FacesMessage("Url", "Url Date is not choosen"));
               check = false;
            }
        
     if (check) {
         
         if (file2 == null) {
              
                selectedAdv.setIndex(adv.getIndex());
              
            } else {
                cf.deletFlashFile(selectedAdv.getIndex());
                selectedAdv.setIndex(cf.saveFlashFileToFolder(file2));
            }
         
            gsb.editAdvertisement(selectedAdv);
            context.addMessage(null, new FacesMessage("Succes", "Adding is successful"));
            setVoep(false);
            advertisements =gsb.findAdvertisements();}
        }
    
    public List<Advertisement> giveRandomAdd(String type){
    List<Advertisement> adds=new ArrayList<Advertisement>();
    List<Advertisement> adds2=new ArrayList<Advertisement>();
            for (Advertisement advertisement : advertisements) {
                if(advertisement.getType().equals(type))
                    adds.add(advertisement);
            }
            if(adds.size()>0){
       int ran = (int) (Math.random() * (adds.size()-1));
           adds.get(ran).setIsSwf(isSwf(adds.get(ran).getIndex()));
           adds.get(ran).setIsnSwf(isnSwf(adds.get(ran).getIndex()));
       adds2.add(adds.get(ran));
     return adds2;}
            else return null;
    }
    
    public String isSwf(String s){
        System.out.println(s);
    String[] a = s.split("\\.");
    System.out.println("swf"+a[a.length-1]);
    System.out.println(a[a.length-1]+"swf");
     if(a[a.length-1]=="SWF"||a[a.length-1]=="swf"||a[a.length-1].equals("SWF")||a[a.length-1].equals("swf")){
          System.out.println("ddddddddddddddddddddddddddddddddddddddddddddd");
    return "block";
    
    }
    else{
    return "none";
    }
    }
    
    public String isnSwf(String s){
    String[] a = s.split("\\.");
     if(a[a.length-1]=="SWF"||a[a.length-1]=="swf"||a[a.length-1].equals("SWF")||a[a.length-1].equals("swf")){
        System.out.println("ddddddddddddddddddddddddddddddddddddddddddddd");
    return "none";
    }
    else{
    return "block";
    }
    }
    
    public String width(String s){ 
    if(s.equals("Banner")||s=="Banner"){
        System.out.println(s);
    return "468px";
    }
    else if(s.equals("Leaderboard")||s=="Leaderboard"){
         System.out.println(s);
    return "728px";
    }
    else {
    System.out.println(s);
    return "200px";
    }
    }
    
    public String height(String s){
    if(s.trim().equals("Banner")||s=="Banner"){
    return "60px";
    }
    else if(s.trim().equals("Leaderboard")||s=="Leaderboard"){
    return "90px";
    }
    else {
    System.out.println(s);
    return "200px";
    }
    }
     
     
    public boolean isVoap() {
        return voap;
    }

    public void setVoap(boolean voap) {
        this.voap = voap;
    }

    public boolean isVoep() {
        return voep;
    }

    public void setVoep(boolean voep) {
        this.voep = voep;
    }

    public Advertisement getNewAdv() {
        return newAdv;
    }

    public void setNewAdv(Advertisement newAdv) {
        this.newAdv = newAdv;
    }

    public String getNewAdvFormat() {
        return newAdvFormat;
    }

    public void setNewAdvFormat(String newAdvFormat) {
        this.newAdvFormat = newAdvFormat;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile2() {
        return file2;
    }

    public void setFile2(UploadedFile file2) {
        this.file2 = file2;
    }

    public String getNewAdvType() {
        return newAdvType;
    }

    public void setNewAdvType(String newAdvType) {
        this.newAdvType = newAdvType;
    }

    public SelectItem getSelectedTypeSI() {
        return selectedTypeSI;
    }

    public void setSelectedTypeSI(SelectItem selectedTypeSI) {
        this.selectedTypeSI = selectedTypeSI;
    }

    public SelectItem getSelectedAdvFormatSI() {
        return selectedAdvFormatSI;
    }

    public void setSelectedAdvFormatSI(SelectItem selectedAdvFormatSI) {
        this.selectedAdvFormatSI = selectedAdvFormatSI;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Advertisement getSelectedAdv() {
        return selectedAdv;
    }

    public void setSelectedAdv(Advertisement selectedAdv) {
        this.selectedAdv = selectedAdv;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public boolean isVosp() {
        return vosp;
    }

    public void setVosp(boolean vosp) {
        this.vosp = vosp;
    }
     
    
    
    
     
}
