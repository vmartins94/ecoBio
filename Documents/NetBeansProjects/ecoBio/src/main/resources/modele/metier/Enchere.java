package modele.metier;
// Generated 22 fev. 2014 10:53:47 by Hibernate Tools 3.6.0

import java.util.Date;

/**
 * Enchere generated by hbm2java
 */
public class Enchere  implements java.io.Serializable {


     private Integer id;
     private Produit produit;
     private Date dateDebut;
     private float prix;
     private Integer quantite;
     private Date dateFin;

    public Enchere() {
    }

	
    public Enchere(Produit produit, Date dateDebut, float prix, Date dateFin) {
        this.produit = produit;
        this.dateDebut = dateDebut;
        this.prix = prix;
        this.dateFin = dateFin;
    }
    public Enchere(Produit produit, Date dateDebut, float prix, Integer quantite, Date dateFin) {
       this.produit = produit;
       this.dateDebut = dateDebut;
       this.prix = prix;
       this.quantite = quantite;
       this.dateFin = dateFin;
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
    public Date getDateDebut() {
        return this.dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public float getPrix() {
        return this.prix;
    }
    
    public void setPrix(float prix) {
        this.prix = prix;
    }
    public Integer getQuantite() {
        return this.quantite;
    }
    
    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    public Date getDateFin() {
        return this.dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }




}

