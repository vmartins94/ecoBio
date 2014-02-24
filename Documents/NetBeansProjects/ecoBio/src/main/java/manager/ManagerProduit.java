/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.Serializable;
import java.util.List;
import modele.dao.DaoProduit;
import modele.dao.FactoryDao;
import modele.metier.Produit;

/**
 *
 * @author Virginie
 */
public class ManagerProduit implements IManager<Produit>, Serializable {

    private Produit produit;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public List<Produit> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

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
