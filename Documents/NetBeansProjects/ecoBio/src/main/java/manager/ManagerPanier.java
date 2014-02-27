/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Produit;
import org.primefaces.examples.view.CounterBean;

/**
 *
 * @author Chac
 */
public class ManagerPanier implements Serializable {

    private List<ManagerArticle> listArticle = new ArrayList<ManagerArticle>();

    public ManagerPanier() {
    }

    public List<ManagerArticle> getListArticle() {
        return listArticle;
    }

    public void setListArticle(List<ManagerArticle> listArticle) {
        this.listArticle = listArticle;
    }

    public void addProduit(Produit monProduit, CounterBean count) {
        
        ManagerArticle monManagerArticle = new ManagerArticle(monProduit, count.getCount());
        listArticle.add(monManagerArticle);
        count.resetBean();
        
    }
}
