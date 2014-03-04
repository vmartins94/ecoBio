/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;
import modele.metier.Produit;
import modele.metier.Type;
import modele.metier.User;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import utils.Constantes;
import utils.GenerateExcelFile;
/**
 *
 * @author Chac
 */
public class FileUploadController {
   
//private UploadedFile file;  
private MonFile file;

    private List<Produit> listeProduits = new ArrayList<Produit>();

    public FileUploadController() {}
    
//    public UploadedFile getFile() {  
//        return file;  
//    } 
//    public File getFile() {  
//        return file;  
//    }
  
//    public void setFile(UploadedFile file) {  
//        this.file = file;  
//    }  
//    public void setFile(File file) {  
//        this.file = file;  
//    } 
  
    public void upload() {  
        if(file != null) {  
            FacesMessage msg = new FacesMessage("Succesful", file.getName()/*file.getFileName()*/ + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }  
    }  
    
    public void handleFileUploadImage(FileUploadEvent event) {  
        
        try {
            File targetFolder = new File(Constantes.UPLOAD_DIR);
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, event.getFile().getFileName()));
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
    }
    
    public void handleFileUploadExcel(FileUploadEvent event) {  
        
        try {
            File targetFolder = new File(Constantes.UPLOAD_DIR);
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, event.getFile().getFileName()));
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
        
        setListeProduits(GenerateExcelFile.importListProduits(Constantes.UPLOAD_DIR + File.separator + event.getFile().getFileName()));
        
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("dlgUpload.show()");
        
    }
    
    public void handleFileUpload(FileUploadEvent event) {  
        
        try {
            File targetFolder = new File(Constantes.UPLOAD_DIR);
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder, event.getFile().getFileName()));
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
    }
    
    public MonFile getFile() {
        return file;
    }

    public void setFile(MonFile file) {
        this.file = file;
    }

    public List<Produit> getListeProduits() {
//        listeProduits.add(new Produit(new Type("tata", "Fruit"), new User(), 1, "", "", tCrue, 14, 14));
        return listeProduits;
    }

    public void setListeProduits(List<Produit> listeProduits) {
        this.listeProduits = listeProduits;
    }
    
    
}


//public String uploadFile() throws IOException {
//
//		// Extract file name from content-disposition header of file part
//		String fileName = getFileName(part);
//		System.out.println("***** fileName: " + fileName);
//
//		String basePath = "C:" + File.separator + "temp" + File.separator;
//		File outputFilePath = new File(basePath + fileName);
//
//		// Copy uploaded file to destination path
//		InputStream inputStream = null;
//		OutputStream outputStream = null;
//		try {
//			inputStream = part.getInputStream();
//			outputStream = new FileOutputStream(outputFilePath);
//
//			int read = 0;
//			final byte[] bytes = new byte[1024];
//			while ((read = inputStream.read(bytes)) != -1) {
//				outputStream.write(bytes, 0, read);
//			}
//
//			statusMessage = "File upload successfull !!";
//		} catch (IOException e) {
//			e.printStackTrace();
//			statusMessage = "File upload failed !!";
//		} finally {
//			if (outputStream != null) {
//				outputStream.close();
//			}
//			if (inputStream != null) {
//				inputStream.close();
//			}
//		}
//		return null;    // return to same page
//	}
//
//// Extract file name from content-disposition header of file part
//	private String getFileName(Part part) {
//		final String partHeader = part.getHeader("content-disposition");
//		System.out.println("***** partHeader: " + partHeader);
//		for (String content : part.getHeader("content-disposition").split(";")) {
//			if (content.trim().startsWith("filename")) {
//				return content.substring(content.indexOf('=') + 1).trim()
//						.replace("\"", "");
//			}
//		}
//		return null;
//	}