/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Etat;
import modele.metier.Saison;
import modele.metier.Type;

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
    private List<SelectItem> listEtat;
    private List<SelectItem> listSaison;
    private List<SelectItem> listType;

    public String createEtat() {
        IDao etatDao = FactoryDao.getDAO("Etat");
        etatDao.insert(etat);
        return "";
    }
        
    public String createSaison() {
        IDao saisonDao = FactoryDao.getDAO("Saison");
        saisonDao.insert(saison);
        return "";
    }
            
    public String createTypeProduit() {
        IDao typeDao = FactoryDao.getDAO("Type");
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
    
}
