/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import modele.metier.Produit;



/**
 *
 * @author Chac
 */
public class ManagerArticle {
    
    private Produit produit;
    private int quantite;

    public ManagerArticle() {}
        
    public ManagerArticle(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public void supprimerUnArticle(){
        
    }
    
    public void ajouterUnArticle(){
        
    }
    
}
