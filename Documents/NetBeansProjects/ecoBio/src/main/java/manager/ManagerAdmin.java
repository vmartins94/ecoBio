/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modele.dao.DaoSaison;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Etat;
import modele.metier.Saison;
import modele.metier.Type;
import modele.metier.User;
import utils.Constantes;

/**
 *
 * @author Chac
 */
public class ManagerAdmin implements Serializable{
    
    private Etat etat;
    private Saison saison;
    private Type type;
    private List<Etat> listE;
    private List<Saison> listS;
    private List<Type> listT;
    private List<User> listU;
    private List<SelectItem> listEtat;
    private List<SelectItem> listSaison;
    private List<SelectItem> listType;
    private List<SelectItem> listCategorie;
    
    //Objets selectionnés dans la liste
    private List<String> selectedSaisonForType;
    private List<Saison> selectedSaison;
    private List<Etat> selectedEtat;
    private List<Type> selectedType = new ArrayList<Type>();
    private List<String> selectedCategorie;
 
    public String createEtat(Etat newEtat) {
        etat = newEtat;
        IDao etatDao = FactoryDao.getDAO("Etat");
        etatDao.insert(etat);
        return "";
    }
        
    public String createSaison(Saison newSaison) {
        saison = newSaison;
        IDao saisonDao = FactoryDao.getDAO("Saison");
        saisonDao.insert(saison);
        return "";
    }
            
    public String createTypeProduit(Type newType) {
        type = newType;
        IDao typeDao = FactoryDao.getDAO("Type");
        DaoSaison saisonDao = (DaoSaison) FactoryDao.getDAO("Saison");
        
        Set<Saison> setSaison = new HashSet<Saison>();
        
        String nameSaison = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFormType:idSaison");
        setSaison.add(saisonDao.selectByName(nameSaison));
        type.setSaisons(setSaison);

        String categorie = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFormType:idCategorie");
        type.setCategorie(categorie);
        
        typeDao.insert(type);
        return "";
    }
                
    public List<Etat> findAllEtat() {
        IDao daoEtat = FactoryDao.getDAO("Etat");
        listE = daoEtat.selectAll();
        return listE;
    }

    public List<SelectItem> selectedItemFindAllEtat() {
        
        findAllEtat();
        
        listEtat = new ArrayList<SelectItem>();
        
        for (Etat lEtat : listE) {
            listEtat.add(new SelectItem(lEtat.getNom()));
        }

        return listEtat;
    }
        
    public List<Saison> findAllSaison() {
        IDao daoSaison = FactoryDao.getDAO("Saison");
        listS = daoSaison.selectAll();
        return listS;
    }
      
    public List<SelectItem> selectedItemFindAllSaison() {
        
        findAllSaison();
        listSaison = new ArrayList<SelectItem>();
        
        for (Saison laSaison : listS) {
            listSaison.add(new SelectItem(laSaison.getNom()));
        }

        return listSaison;
    }
        
    public List<Type> findAllTypeProduit() {
        IDao daoTypeProduit = FactoryDao.getDAO("Type");
        listT =  daoTypeProduit.selectAll();
        return listT;
    }

    public List<SelectItem> selectedItemFindAllType() {
        
        findAllTypeProduit();
        listType = new ArrayList<SelectItem>();
        
        for (Type leType : listT) {
            listType.add(new SelectItem(leType.getNom()));
        }

        return listType;
    }

    public List<SelectItem> selectedItemFindAllCategorie() {

        listCategorie = new ArrayList<SelectItem>();
        listCategorie.add(new SelectItem(Constantes.CATEGORIE_FRUITS));
        listCategorie.add(new SelectItem(Constantes.CATEGORIE_LEGUMES));
        listCategorie.add(new SelectItem(Constantes.CATEGORIE_PRODUITS_DEFECTUEUX));

        return listCategorie;
    }
    
    public List<User> findAllUser() {
        IDao daoUser = FactoryDao.getDAO("User");
        listU =  daoUser.selectAll();
        return listU;
    }
    
    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<SelectItem> getListEtat() {
        return listEtat;
    }

    public void setListEtat(List<SelectItem> listEtat) {
        this.listEtat = listEtat;
    }

    public List<SelectItem> getListSaison() {
        return listSaison;
    }

    public void setListSaison(List<SelectItem> listSaison) {
        this.listSaison = listSaison;
    }

    public List<SelectItem> getListType() {
        return listType;
    }

    public void setListType(List<SelectItem> listType) {
        this.listType = listType;
    }

    public List<Etat> getSelectedEtat() {
        return selectedEtat;
    }

    public void setSelectedEtat(List<Etat> selectedEtat) {
        this.selectedEtat = selectedEtat;
    }

    public List<String> getSelectedSaisonForType() {
        return selectedSaisonForType;
    }

    public void setSelectedSaisonForType(List<String> selectedSaisonForType) {
        this.selectedSaisonForType = selectedSaisonForType;
    }

    public List<Saison> getSelectedSaison() {
        return selectedSaison;
    }

    public void setSelectedSaison(List<Saison> selectedSaison) {
        this.selectedSaison = selectedSaison;
    }

    public List<Type> getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(List<Type> selectedType) {
        this.selectedType = selectedType;
    }

    public List<SelectItem> getListCategorie() {
        return listCategorie;
    }

    public void setListCategorie(List<SelectItem> listCategorie) {
        this.listCategorie = listCategorie;
    }

    public List<String> getSelectedCategorie() {
        return selectedCategorie;
    }

    public void setSelectedCategorie(List<String> selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
    }
 
}
