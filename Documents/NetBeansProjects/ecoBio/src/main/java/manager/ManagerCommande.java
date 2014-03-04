/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.dao.DaoCommande;
import modele.dao.DaoProduit;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Commande;
import modele.metier.CommandeHasProduit;
import modele.metier.Enchere;
import modele.metier.Etat;
import modele.metier.Produit;
import modele.metier.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import utils.Constantes;
import utils.GenerateExcelFile;

/**
 *
 * @author Virginie
 */
public class ManagerCommande implements IManager<Commande>, Serializable {

    private Commande maCommande;
    private User userCommande;  
    private Commande selectedCommande;

    public Commande getSelectedCommande() {
        return selectedCommande;
    }

    public void setSelectedCommande(Commande selectedCommande) {
        this.selectedCommande = selectedCommande;
    }
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

        //appel Dao
        DaoCommande dao = (DaoCommande) FactoryDao.getDAO("Commande");
        IDao daoP = FactoryDao.getDAO("Produit");
        //Recuperation des objets
        Produit produit = (Produit) daoP.selectById(5);
        maCommande = dao.selectById(2);
        // creation objet et insertion dans la base  ok 
        CommandeHasProduit commandehasProduit = new CommandeHasProduit(produit, maCommande, 2);
        dao.insertTableAsso(commandehasProduit);

        // creation d'une liste de type commandeHasProduit
        Set<CommandeHasProduit> commandeHasProduit = new HashSet<CommandeHasProduit>();
        commandeHasProduit.add(commandehasProduit);
        //mise a jour de la commande
        maCommande.setPrixTotal(Float.valueOf(25));
        maCommande.setCommandeHasProduits(commandeHasProduit);
        dao.update(maCommande);

        return null;

    }

    public String create(List<ManagerArticle> listManagerArticle, User user) {

        //appel Dao
        Integer quantiteTotal = 0;
        Integer nbArticle = 0;
        Integer prixTotal = 0;
        DaoCommande dao = (DaoCommande) FactoryDao.getDAO("Commande");
        IDao daoEtat = FactoryDao.getDAO("Etat");
        Etat monEtat = (Etat) daoEtat.selectById(1);
        Date maDate = new Date();
        for (ManagerArticle monManagerArticle : listManagerArticle) {

            quantiteTotal = monManagerArticle.getQuantite() + quantiteTotal;
            nbArticle = listManagerArticle.size();
            prixTotal = monManagerArticle.getQuantite() * monManagerArticle.getProduit().getPrix() + prixTotal;

        }
        Float prixFloat = Float.valueOf(prixTotal);
        //Recuperation des objets
        Commande nouvelleCommande = new Commande(monEtat, user, maDate, prixFloat, quantiteTotal);
        dao.insert(nouvelleCommande);
        // creation objet et insertion dans la base  ok 
        for (ManagerArticle monManagerArticle : listManagerArticle) {

            CommandeHasProduit commandehasProduit = new CommandeHasProduit(monManagerArticle.getProduit(), nouvelleCommande, monManagerArticle.getQuantite());
            dao.insertTableAsso(commandehasProduit);

        }

        ManagerProduit monManagerProduit = new ManagerProduit();
        monManagerProduit.updateProduit(listManagerArticle);
        listManagerArticle.clear();
        return null;
    }

    public boolean update(Commande uneCommande) {
        IDao daoCommande = FactoryDao.getDAO("Commande");
        return daoCommande.update(uneCommande);
    }

    public List<Commande> findAllCommandeByUser(User user) {
        DaoCommande daoCommande = (DaoCommande) FactoryDao.getDAO("Commande");
        return daoCommande.selectAllMyCommande(user);
    }

    /**
     * Cette méthode permet de verifier s'il y a une commande pour un produit
     * qu'il est en statut encherer
     *
     * @param uneEnchere
     * @return un Objet commande
     */
    public boolean selectCommandeByProduitEnchere(Enchere uneEnchere) {
        DaoCommande daoCommande = (DaoCommande) FactoryDao.getDAO("Commande");
        Commande uneCommande = (Commande) daoCommande.selectCommandeByProduitEnchere(uneEnchere);
        IDao daoUser = FactoryDao.getDAO("User");
        User unUser = (User) daoUser.selectById(uneEnchere.getIntUserId());
        boolean executer = false;
        Date today = new Date();
        if (uneCommande != null) {
            uneCommande.setUser(unUser);
            uneCommande.setDateCreation(today);
            executer = daoCommande.update(uneCommande);
        } else {
            IDao daoEtat = FactoryDao.getDAO("Etat");
            Etat unEtat = (Etat) daoEtat.selectById(2);
            uneCommande = new Commande(unEtat, unUser, today, uneEnchere.getPrix(), 1);
            executer = createCommande(uneCommande);
            CommandeHasProduit commandehasProduit = new CommandeHasProduit(uneEnchere.getProduit(), uneCommande, 1);
            daoCommande.insertTableAsso(commandehasProduit);

        }
        return executer;
    }

    /**
     * Cette methode permet de create une commande
     *
     * @param uneCommande
     * @return un type boolean true = commande bien créée
     */
    public boolean createCommande(Commande uneCommande) {
        IDao daoCommande = FactoryDao.getDAO("Commande");
        return daoCommande.insert(uneCommande);
    }

    public void genererFacture(Commande commande) {

        maCommande = commande;
        
        GenerateExcelFile generateExcel = new GenerateExcelFile();
        HSSFSheet sheet = generateExcel.getWorkbook().createSheet("Facture");
        sheet.protectSheet("secret");

        //style pour verrouiller les cellules
        CellStyle lockedCellStyle = generateExcel.getWorkbook().createCellStyle();
        lockedCellStyle.setLocked(true);

        //Titre
        HSSFRow rowTitle = sheet.createRow(3);
        HSSFCell cellTitle = rowTitle.createCell(6);
        cellTitle.setCellValue(new HSSFRichTextString("FACTURE"));
        cellTitle.setCellStyle(generateExcel.creationFontTitre());
        
        //Logo

        //add picture data to this workbook.
        InputStream is = null;
        try {
            is = new FileInputStream(Constantes.UPLOAD_DIR + File.separator + "logo_Facture.jpg");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(is);
        } catch (IOException ex) {
            Logger.getLogger(ManagerCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        int pictureIdx = generateExcel.getWorkbook().addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        try {
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(ManagerCommande.class.getName()).log(Level.SEVERE, null, ex);
        }

        CreationHelper helper = generateExcel.getWorkbook().getCreationHelper();

        // Create the drawing patriarch. This is the top level container for all shapes. 
        Drawing drawing = sheet.createDrawingPatriarch();

        //add a picture shape
        ClientAnchor anchor = helper.createClientAnchor();
        //set top-left corner of the picture,
        //subsequent call of Picture#resize() will operate relative to it
        anchor.setCol1(1);
        anchor.setRow1(6);
        Picture pict = drawing.createPicture(anchor, pictureIdx);

        //auto-size picture relative to its top-left corner
        pict.resize();

        //Numéro de la commande
        HSSFRow rowNumeroCommande = sheet.createRow(15);
        HSSFCell cellNumeroCommande = rowNumeroCommande.createCell(1);
        cellNumeroCommande.setCellValue(new HSSFRichTextString("Numéro commande : " + maCommande.getId()));
        cellNumeroCommande.setCellStyle(generateExcel.creationTexteGrasMoyen());
        
        //Date de la commande
        HSSFRow rowDateCommande = sheet.createRow(16);
        HSSFCell cellDateCommande = rowDateCommande.createCell(1);
        SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy"); 
        cellDateCommande.setCellValue(new HSSFRichTextString("Date commande : " + formatter.format(maCommande.getDateCreation())));
        cellDateCommande.setCellStyle(generateExcel.creationTexteGrasMoyen());
        
        //Identifiant vendeur
        //Adresse vendeur
        //CP et ville vendeur
        
        //Numéro client
        HSSFRow rowNumeroClient = sheet.createRow(7);
        HSSFCell cellNumeroClient = rowNumeroClient.createCell(10);
        cellNumeroClient.setCellValue(new HSSFRichTextString("Numéro client : " + maCommande.getUser().getId()));
        cellNumeroClient.setCellStyle(generateExcel.creationTexteGrasMoyen());
        
        //Identifiant client
        HSSFRow rowIdentifiantClient = sheet.createRow(9);
        HSSFCell cellIdentifiantClient = rowIdentifiantClient.createCell(10);
        cellIdentifiantClient.setCellValue(new HSSFRichTextString(maCommande.getUser().getNom() + " " + maCommande.getUser().getPrenom()));
        cellIdentifiantClient.setCellStyle(generateExcel.creationTexteGrasMoyen());
        
        //Adresse client
        HSSFRow rowAdresseClient = sheet.createRow(10);
        HSSFCell cellAdresseClient = rowAdresseClient.createCell(10);
        cellAdresseClient.setCellValue(new HSSFRichTextString(maCommande.getUser().getRue()));
        cellAdresseClient.setCellStyle(generateExcel.creationTexteGrasMoyen());
        
        //CP et ville client
        HSSFRow rowCpVilleClient = sheet.createRow(11);
        HSSFCell cellCpVilleClient = rowCpVilleClient.createCell(10);
        cellCpVilleClient.setCellValue(new HSSFRichTextString(maCommande.getUser().getCp() + " " + maCommande.getUser().getVille()));
        cellCpVilleClient.setCellStyle(generateExcel.creationTexteGrasMoyen());
        
        //Titre colonne du tableau
        HSSFRow rowTitreColonne = sheet.createRow(20);
        
        //Colonne nom
        HSSFCell cellNom = rowTitreColonne.createCell(2);
        cellNom.setCellValue(new HSSFRichTextString("Nom"));
        cellNom.setCellStyle(generateExcel.creationFontTitreColonne());
        //Colonne description
        HSSFCell cellDesignation = rowTitreColonne.createCell(3);
        cellDesignation.setCellValue(new HSSFRichTextString("Description"));
        cellDesignation.setCellStyle(generateExcel.creationFontTitreColonne());
        //Colonne quantité
        HSSFCell cellQuantite = rowTitreColonne.createCell(4);
        cellQuantite.setCellValue(new HSSFRichTextString("Quantité"));
        cellQuantite.setCellStyle(generateExcel.creationFontTitreColonne());
        //Colonne prix unitaire
        HSSFCell cellPrixU = rowTitreColonne.createCell(5);
        cellPrixU.setCellValue(new HSSFRichTextString("Prix unitaire (en €)"));
        cellPrixU.setCellStyle(generateExcel.creationFontTitreColonne());
        //Colonne prix total ligne
        HSSFCell cellPrixTotal = rowTitreColonne.createCell(6);
        cellPrixTotal.setCellValue(new HSSFRichTextString("Prix Total"));
        cellPrixTotal.setCellStyle(generateExcel.creationFontTitreColonne());

        //Taille des colonnes en fonction du contenu
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);

        int indexLine = 21;
        //Liste des produits
        Iterator iteratorCommande = maCommande.getCommandeHasProduits().iterator();
        CommandeHasProduit aCommandeHasProduit;
        
        while(iteratorCommande.hasNext()){
            aCommandeHasProduit = (CommandeHasProduit) iteratorCommande.next();
            
            HSSFRow rowProduit = sheet.createRow(indexLine);
            HSSFCell nom = rowProduit.createCell(2);
            nom.setCellValue(new HSSFRichTextString(aCommandeHasProduit.getProduit().getNom()));
            nom.setCellStyle(generateExcel.creationTexteGrasMoyen());
            HSSFCell description = rowProduit.createCell(3);
            description.setCellValue(new HSSFRichTextString(aCommandeHasProduit.getProduit().getDescription()));
            description.setCellStyle(generateExcel.creationTexteGrasMoyen());
            HSSFCell quantite = rowProduit.createCell(4);
            quantite.setCellValue(new HSSFRichTextString(String.valueOf(aCommandeHasProduit.getQuantite())));
            quantite.setCellStyle(generateExcel.creationTexteGrasMoyen());
            HSSFCell prixU = rowProduit.createCell(5);
            prixU.setCellValue(new HSSFRichTextString(String.valueOf(aCommandeHasProduit.getProduit().getPrix())));
            prixU.setCellStyle(generateExcel.creationTexteGrasMoyen());
            HSSFCell prixT = rowProduit.createCell(6);
            prixT.setCellValue(new HSSFRichTextString(String.valueOf(aCommandeHasProduit.getProduit().getPrix()*aCommandeHasProduit.getQuantite())));
            prixT.setCellStyle(generateExcel.creationTexteGrasMoyen());
            indexLine++;
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(
                    new File(Constantes.UPLOAD_DIR + File.separator + "Facture_" + maCommande.getId() + "_" + maCommande.getUser().getNom() + ".xls"));
            generateExcel.getWorkbook().write(fos);
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

}
