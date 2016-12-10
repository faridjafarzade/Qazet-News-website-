/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import javax.faces.context.FacesContext;
import javax.imageio.stream.ImageInputStream;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import pojoes.News;
import pojoes.Topic;
import pojoes.WebSite;

/**
 *
 * @author Farid
 */
public class CommonFunctions {

//    public StreamedContent giveImage(GeneralSessionBean gsb, int fileId) {
//        return new DefaultStreamedContent(new ByteArrayInputStream(gsb.findMedFile(fileId).getContent()), gsb.findMedFile(fileId).getFileType(), gsb.findMedFile(fileId).getName());
//    }
//
//    public void saveFileToDatabase(GeneralSessionBean gsb, FileUploadEvent event, MedFile coverImage) {
//
//        byte[] content = event.getFile().getContents();
//        coverImage.setContent(event.getFile().getContents());
//        coverImage.setFileSize(event.getFile().getSize());
//        coverImage.setName(event.getFile().getFileName());
//        coverImage.setFileType(event.getFile().getContentType());
//       
//
//    }
    public String saveFileToFolder(UploadedFile file) {
        if (file != null) {
            String[] s = file.getFileName().split("\\.");
            String body = "";
            for (int i = 0; i < s.length - 1; i++) {
                body = body + s[i] + ".";
            }
            int ran = (int) (Math.random() * 1000);
            body = body + ran;
            String fileName = body + "." + s[s.length - 1];
            try {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                String filePath = path + "resources\\images\\";
                File targetFolder = new File(filePath);
                InputStream inputStream = file.getInputstream();
                OutputStream out = new FileOutputStream(new File(targetFolder, fileName));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return fileName;
        }
        return null;
    }

    public String saveFlashFileToFolder(UploadedFile file) {
        if (file != null) {
            String[] s = file.getFileName().split("\\.");
            String body = "";
            for (int i = 0; i < s.length - 1; i++) {
                body = body + s[i] + ".";
            }
            int ran = (int) (Math.random() * 1000);
            body = body + ran;
            String fileName = body + "." + s[s.length - 1];
            try {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                String filePath = path + "resources\\advertisements\\";
                File targetFolder = new File(filePath);
                InputStream inputStream = file.getInputstream();
                OutputStream out = new FileOutputStream(new File(targetFolder, fileName));
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return fileName;
        }
        return null;
    }

    public String saveFileToFolder(UploadedFile file, String fileName) {
        if (file != null) {
            String[] s = file.getFileName().split("\\.");
            String image = null;
            try {
                String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
                String filePath = path + "resources\\images\\";
                File targetFolder = new File(filePath);
                //File targetFolder = new File("..\\"); 
                InputStream inputStream = file.getInputstream();
                int ran = (int) (Math.random() * 1000);
                image = fileName + ".sek." + ran + "." + s[s.length - 1];
                OutputStream out = new FileOutputStream(new File(targetFolder, fileName + ".sek." + ran + "." + s[s.length - 1]));
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                inputStream.close();
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return image + "photoborder";
        }
        return null;
    }

    public void deletFile(String fileName) {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String filePath = path + "resources\\images\\" + fileName;
        try {
            File file = new File(filePath);
            file.setWritable(true);
            if (file.delete()) {
            } else {
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void deletFlashFile(String fileName) {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String filePath = path + "resources\\advertisements\\" + fileName;
        try {
            File file = new File(filePath);
            file.setWritable(true);
            if (file.delete()) {
            } else {
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public String deletNewestNews(String nn, String news) {
        if (nn != news + "splitcode" || !nn.equals(news + "splitcode")) {
            String newestNews[] = nn.split(news + "splitcode");
            System.out.println(nn);
            if (newestNews.length > 1) {
                String ns = newestNews[0] + newestNews[1];
                return ns;
            } else if (newestNews.length > 0){ 
                return newestNews[0];
            }
            else return nn;
        } else {
            return " ";
        }
    }

    public String renameInTop(String nn, String news, String nnews) {
        if (nn != null && !nn.equals(deletNewestNews(nn, news))) {
            if (nn != news + "splitcode" || !nn.equals(news + "splitcode")) {
                String newestNews[] = nn.split(news + "splitcode");
                if (newestNews.length > 1) {
                    String ns = newestNews[0] + nnews + "splitcode" + newestNews[1];
                    return ns;
                } else {
                    return newestNews[0] + nnews + "splitcode";
                }
            } else {
                return nnews + "splitcode";
            }
        } else {
            return "";
        }
    }
}
