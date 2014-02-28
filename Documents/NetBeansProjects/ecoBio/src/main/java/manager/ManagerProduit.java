/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modele.dao.DaoProduit;
import modele.dao.DaoType;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Enchere;
import modele.metier.Produit;
import modele.metier.Type;
import modele.metier.User;

/**
 *
 * @author Virginie
 */
public class ManagerProduit implements IManager<Produit>, Serializable {

    private Produit produit;
    private Enchere enchere;
    private Map<Integer, Integer> mapProduit;
    private Boolean selectedTypeVente = false;
    private Boolean selectedModeVente;
    private Type selectedType;

    @Override
    public List<Produit> findAll() {
        DaoProduit daoProduit = (DaoProduit) FactoryDao.getDAO("Produit");
        List<Produit> listeProduit = daoProduit.selectAll();
        return listeProduit;
    }

    public List<Produit> findAllMines(User user) {
        DaoProduit daoProduit = (DaoProduit) FactoryDao.getDAO("Produit");
        List<Produit> listeProduit = daoProduit.selectAllByUser(user);
        return listeProduit;
    }
        
    @Override
    public String create() {
        IDao produitDao = FactoryDao.getDAO("Produit");
        produitDao.insert(produit);
        return "";
    }

    public void createProduit(Produit product, Enchere anEnchere, User user) {
        
        //Si c'est une enchere , qté = 1 et c'est forcément un lot
        produit = product;
        produit.setQuantiteFinale(produit.getQuantiteInitiale());
        produit.setUser(user);

        String type = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFormVente:idType");
        String typeVente = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFormVente:idTypeVente");
        
        //TODO C : Revoir pour le typeVente
        DaoType daoType = (DaoType) FactoryDao.getDAO("Type");
        produit.setType(daoType.selectByName(type));
        produit.setTypeVente(Boolean.valueOf(typeVente));
        produit.setImage("C:\\Users\\Chac\\Desktop\\image1.png");
        
        if(produit.isAvecEnchere()){
            enchere = anEnchere;
            enchere.setPrix(produit.getPrix());
            produit.getEncheres().add(enchere);
            produit.setTypeVente(false);
        }
        
        create();
    }
        
    public List<Produit> listeProduit() {
        DaoProduit daoProduit = (DaoProduit) FactoryDao.getDAO("Produit");
        List<Produit> listeProduit = daoProduit.listeAllProduit();
        
        mapProduit();
        
        if (!listeProduit.isEmpty()) {
            return listeProduit;
        }
        return null;
    }

    public Map<Integer, Integer> mapProduit() {
        DaoProduit daoProduit = (DaoProduit) FactoryDao.getDAO("Produit");
        mapProduit = new HashMap<Integer, Integer>();

        for (Produit monProduit : daoProduit.listeAllProduit()) {
            mapProduit.put(monProduit.getId(), 0);
        }
        return mapProduit;
    }

    public List<Produit> listeProduitByEnchere() {
        DaoProduit daoProduit = (DaoProduit) FactoryDao.getDAO("Produit");
        List<Produit> listeProduitByEnchere = daoProduit.listeAllProduitByEnchere();
        if (!listeProduitByEnchere.isEmpty()) {
            return listeProduitByEnchere;
        }
        return null;
    }
    
    public List<SelectItem> selectedItemFindAllTypeVente() {
        
//        Map<Boolean,String> listeTypeDeVente = new HashMap<Boolean, String>();
//        listeTypeDeVente.put(false, "Par lot");
//        listeTypeDeVente.put(true, "Au kilo");
//        
//        listTypeVente = new ArrayList<SelectItem>();
//        listTypeVente.add(new SelectItem(listeTypeDeVente.get(false)));
//        listTypeVente.add(new SelectItem(listeTypeDeVente.get(true)));
//        
//        return listTypeVente;
        return null;
    }
    
     public void updateProduit(List<ManagerArticle> listManagerArticle) {

        DaoProduit daoProduit = (DaoProduit) FactoryDao.getDAO("Produit");
        for (ManagerArticle monManagerArticle : listManagerArticle) {
            daoProduit.updateProduit(monManagerArticle);
        }
     }
        
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Map<Integer, Integer> getMapProduit() {
        return mapProduit;
    }

    public void setMapProduit(Map<Integer, Integer> mapProduit) {
        this.mapProduit = mapProduit;
    }
    
    public Boolean getSelectedTypeVente() {
        return selectedTypeVente;
    }

    public void setSelectedTypeVente(Boolean selectedTypeVente) {
        this.selectedTypeVente = selectedTypeVente;
    }

    public Boolean getSelectedModeVente() {
        return selectedModeVente;
    }

    public void setSelectedModeVente(Boolean selectedModeVente) {
        this.selectedModeVente = selectedModeVente;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public Type getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(Type selectedType) {
        this.selectedType = selectedType;
    }
    
}
