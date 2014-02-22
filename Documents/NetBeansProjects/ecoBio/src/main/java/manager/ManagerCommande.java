/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoCommande;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Commande;
import modele.metier.CommandeHasProduit;
import modele.metier.Etat;
import modele.metier.Produit;
import modele.metier.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author Virginie
 */
public class ManagerCommande implements IManager<Commande>, Serializable {

    private Commande maCommande;
    private User userCommande;

    @Override
    public List<Commande> findAll() {
        IDao daoCommande = FactoryDao.getDAO("Commande");
        return daoCommande.selectAll();
    }

    public List<Commande> findAllCommandeByUser() {
        DaoCommande daoCommande = (DaoCommande) FactoryDao.getDAO("Commande");
        return daoCommande.selectAllMyCommande(userCommande);
    }

    @Override
    public String create() {
        return null;
    }

    public void genererFacture() {

        //TODO C : 2 type de facture => une pour le client et l'autre pour le vendeur
        maCommande = new Commande();
        maCommande.setId(120);
        maCommande.setDateCreation(new Date());
        User user = new User("SEVERIEN", "Christiana", "login", "c.severien@insta.fr", "25 rue de Bellevue", 93800, "Epinay-Sur-seine", "password");
        maCommande.setUser(user);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("students");
        sheet.protectSheet("secret");
        //style pour verrouiller les cellules
        CellStyle lockedCellStyle = workbook.createCellStyle();
        lockedCellStyle.setLocked(true);

        //Titre
        HSSFRow rowTitle = sheet.createRow(1);
        HSSFCell cellTitle = rowTitle.createCell(9);
        cellTitle.setCellValue(new HSSFRichTextString("FACTURE"));
//        cellTitle.setCellStyle(lockedCellStyle);

        //Numéro de la commande
        HSSFRow rowNumeroCommande = sheet.createRow(3);
        HSSFCell cellNumeroCommande = rowNumeroCommande.createCell(16);
        cellNumeroCommande.setCellValue(new HSSFRichTextString("Numéro commande : " + maCommande.getId()));

        //Date de la commande
        HSSFRow rowDateCommande = sheet.createRow(4);
        HSSFCell cellDateCommande = rowDateCommande.createCell(16);
        cellDateCommande.setCellValue(new HSSFRichTextString("Date commande : " + maCommande.getDateCreation()));

        //Identifiant vendeur
        //Adresse vendeur
        //CP et ville vendeur
        //Numéro client
        HSSFRow rowNumeroClient = sheet.createRow(11);
        HSSFCell cellNumeroClient = rowNumeroClient.createCell(12);
        cellNumeroClient.setCellValue(new HSSFRichTextString("Numéro client : " + maCommande.getUser().getId()));

        //Identifiant client
        HSSFRow rowIdentifiantClient = sheet.createRow(12);
        HSSFCell cellIdentifiantClient = rowIdentifiantClient.createCell(12);
        cellIdentifiantClient.setCellValue(new HSSFRichTextString(maCommande.getUser().getNom() + " " + maCommande.getUser().getPrenom()));

        //Adresse client
        HSSFRow rowAdresseClient = sheet.createRow(13);
        HSSFCell cellAdresseClient = rowAdresseClient.createCell(12);
        cellAdresseClient.setCellValue(new HSSFRichTextString(maCommande.getUser().getRue()));

        //CP et ville client
        HSSFRow rowCpVilleClient = sheet.createRow(14);
        HSSFCell cellCpVilleClient = rowCpVilleClient.createCell(12);
        cellCpVilleClient.setCellValue(new HSSFRichTextString(maCommande.getUser().getCp() + " " + maCommande.getUser().getVille()));

        //Titre colonne du tablea (Quantité,Désignation,Prix unitaire,Prix total)
        HSSFRow rowTitreColonne = sheet.createRow(20);
        HSSFCell cellQuantité = rowTitreColonne.createCell(1);
        cellQuantité.setCellValue(new HSSFRichTextString("Quantité"));
        HSSFCell cellDesignation = rowTitreColonne.createCell(2);
        cellDesignation.setCellValue(new HSSFRichTextString("Désignation"));
        HSSFCell cellPrixU = rowTitreColonne.createCell(3);
        cellPrixU.setCellValue(new HSSFRichTextString("Prix unitaire"));
        HSSFCell cellPrixTotal = rowTitreColonne.createCell(4);
        cellPrixTotal.setCellValue(new HSSFRichTextString("Prix Total"));

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File("C:/Users/Chac/Desktop/myExcelWorkBook.xls"));
            workbook.write(fos);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

    public Commande getMaCommande() {
        return maCommande;
    }

    public void setMaCommande(Commande maCommande) {
        this.maCommande = maCommande;
    }

    public User getUserCommande() {
        return userCommande;
    }

    public void setUserCommande(User userCommande) {
        this.userCommande = userCommande;
    }

    ////////////////////////////  TEST ///////////////////////////////
    public void delete() {

        DaoCommande dao = (DaoCommande) FactoryDao.getDAO("Commande");
        maCommande = (Commande) dao.selectById(2); // modifier l'id
        dao.delete(maCommande);
    }

    public void update() {
        
        //appel Dao
        DaoCommande dao = (DaoCommande) FactoryDao.getDAO("Commande");
        IDao daoP = FactoryDao.getDAO("Produit");
        //Recuperation des objets
        Produit produit = (Produit) daoP.selectById(5);
        maCommande = dao.selectById(2);
        // creation objet
        CommandeHasProduit commandehasProduit = new CommandeHasProduit(produit, maCommande, 2);
        // creation d'une liste de type commandeHasProduit
        Set<CommandeHasProduit> commandeHasProduit = new HashSet<CommandeHasProduit>();
        commandeHasProduit.add(commandehasProduit);
        //mise a jour de la commande
        maCommande.setCommandeHasProduits(commandeHasProduit);
        dao.update(maCommande);

    }

}
