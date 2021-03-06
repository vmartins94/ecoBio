package modele.metier;




/**
 * CommandeHasProduit generated by hbm2java
 */
public class CommandeHasProduit  implements java.io.Serializable {


     private Integer id;
     private Produit produit;
     private Commande commande;
     private Integer quantite;

    public CommandeHasProduit() {
    }

	
    public CommandeHasProduit(Produit produit, Commande commande) {
        this.produit = produit;
        this.commande = commande;
    }
    public CommandeHasProduit(Produit produit, Commande commande, Integer quantite) {
       this.produit = produit;
       this.commande = commande;
       this.quantite = quantite;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Produit getProduit() {
        return this.produit;
    }
    
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public Commande getCommande() {
        return this.commande;
    }
    
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    public Integer getQuantite() {
        return this.quantite;
    }
    
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }




}


