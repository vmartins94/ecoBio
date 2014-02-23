/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

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
    
    public CellStyle creationTexteGrasMoyen(){
        
        CellStyle cellStyle = workbook.createCellStyle();

        Font font = workbook.createFont();
//        font.setFontHeightInPoints((short)14);
        font.setFontName("Arial");
        font.setBoldweight((short)14);
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
    
    
}
