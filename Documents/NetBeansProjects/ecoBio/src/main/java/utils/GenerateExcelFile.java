/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import modele.metier.Produit;
import modele.metier.Type;
import modele.metier.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Chac
 */
public class GenerateExcelFile {
    
    private HSSFWorkbook workbook;

    public GenerateExcelFile() {
        workbook = new HSSFWorkbook();
    }

    public CellStyle creationFontTitre(){
        
        CellStyle cellStyle = workbook.createCellStyle();
        
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short)20);
        font.setFontName("Arial");
        font.setItalic(true);
        font.setColor((short)HSSFColor.GREEN.index);
        
        cellStyle.setFont(font); 
        
        return cellStyle;
    }
    
    public CellStyle creationFontTitreColonne(){
        
        CellStyle cellStyle = workbook.createCellStyle();
        
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short)14);
        font.setFontName("Arial");
        font.setItalic(true);
        font.setColor((short)HSSFColor.GREEN.index);
        
        cellStyle.setFont(font); 
        
        return cellStyle;
    }
    
    public CellStyle creationTexteGrasMoyen(){
        
        CellStyle cellStyle = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short)14);
        font.setFontName("Arial");
//        font.setBoldweight((short)14);
        font.setColor((short)HSSFColor.BLACK.index);
        
        cellStyle.setFont(font);
        
        return cellStyle;
    }
    
    public void creationTextePetit(){
        
        CellStyle cellStyle = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("Arial");
        font.setColor((short)HSSFColor.BLACK.index);
        
        cellStyle.setFont(font);
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(HSSFWorkbook workbook) {
        this.workbook = workbook;
    }
    
    /**
     * Lecture d'un fichier excel contenant la liste des produits à insérer dans la base de données
     * @param fileName
     * @return 
     */
    public static List<Produit> importListProduits(String fileName){
        
        List<Produit> listeProduits = new ArrayList();
        Produit theProduit = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);

            XSSFWorkbook workbook = new XSSFWorkbook(fileName);
            XSSFSheet worksheet = workbook.getSheet("Feuil1");
            
            Iterator<Row> rowIterator = worksheet.iterator();
            Row currentRow = null;
            while(rowIterator.hasNext()){
                currentRow = rowIterator.next();
                //TODO C : a terme, regarder plutôt en fonction du nombrede cellule par ligne
                if(currentRow.getRowNum() > 7){
                    theProduit = new Produit();
                    
                    //Récupération du nom 
                    Cell cellNom = currentRow.getCell((short) 1);
                    String nom = cellNom.getStringCellValue();
                    
                    //Récupération du prix
                    Cell cellPrix = currentRow.getCell((short) 2);
                    double doublePrix = cellPrix.getNumericCellValue();
                    int prix = (int)doublePrix;
                    
                    //Récupération du mode de vente
                    Cell cellModeVente = currentRow.getCell((short) 3);
                    double doubleModeVente = cellModeVente.getNumericCellValue();
                    boolean modeVente = false;
                    if((int)doubleModeVente == 1){
                        modeVente = true;
                    }
                    
                    //Récupération le type de vente
                    Cell cellTypeVente = currentRow.getCell((short) 4);
                    double doubleTypeVente = cellTypeVente.getNumericCellValue();
                    boolean typeVente = false;
                    if((int)doubleTypeVente == 1){
                        modeVente = true;
                    }
                    
                    //Récupération de la quantité
                    Cell cellQuantite = currentRow.getCell((short) 5);
                    double doubleQuantite = cellQuantite.getNumericCellValue();
                    int quantite = (int)doubleQuantite;
                    
                    //Récuparation
                    Cell cellDescription = currentRow.getCell((short) 6);
                    String description = cellDescription.getStringCellValue();
     
                    theProduit.setNom(nom);
                    theProduit.setPrix(prix);
                    theProduit.setAvecEnchere(modeVente);
                    theProduit.setTypeVente(typeVente);
                    theProduit.setQuantiteInitiale(quantite);
                    theProduit.setQuantiteFinale(quantite);
                    theProduit.setDescription(description);
                     
                    listeProduits.add(theProduit);
                }
            }

            fileInputStream.close();
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return listeProduits;
    }
    
    public void generateFacture(){
        
    }
    
}
