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
import modele.dao.DaoProduit;
import modele.dao.FactoryDao;
import modele.metier.Produit;

/**
 *
 * @author Virginie
 */
public class ManagerProduit implements IManager<Produit>, Serializable {

    private Produit produit;
    private Map<Integer, Integer> mapProduit;

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
    

    @Override
    public List<Produit> findAll() {

        DaoProduit daoProduit = (DaoProduit) FactoryDao.getDAO("Produit");
        List<Produit> listeProduit = daoProduit.selectAll();
        return listeProduit;
    }

    @Override
    public String create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

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

}
