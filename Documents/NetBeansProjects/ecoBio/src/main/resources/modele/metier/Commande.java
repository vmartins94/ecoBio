package modele.metier;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Commande generated by hbm2java
 */
public class Commande  implements java.io.Serializable {


     private Integer id;
     private Etat etat;
     private User user;
     private Date dateCreation;
     private Float prixTotal;
     private Integer quantiteTotal;
     private Set commandeHasProduits = new HashSet(0);

    public Commande() {
    }

	
    public Commande(Etat etat, User user, Date dateCreation) {
        this.etat = etat;
        this.user = user;
        this.dateCreation = dateCreation;
    }
    public Commande(Etat etat, User user, Date dateCreation, Float prixTotal, Integer quantiteTotal, Set commandeHasProduits) {
       this.etat = etat;
       this.user = user;
       this.dateCreation = dateCreation;
       this.prixTotal = prixTotal;
       this.quantiteTotal = quantiteTotal;
       this.commandeHasProduits = commandeHasProduits;
    }
    
      public Commande(Etat etat, User user, Date dateCreation, Float prixTotal, Integer quantiteTotal) {
       this.etat = etat;
       this.user = user;
       this.dateCreation = dateCreation;
       this.prixTotal = prixTotal;
       this.quantiteTotal = quantiteTotal;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Etat getEtat() {
        return this.etat;
    }
    
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDateCreation() {
        return this.dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public Float getPrixTotal() {
        return this.prixTotal;
    }
    
    public void setPrixTotal(Float prixTotal) {
        this.prixTotal = prixTotal;
    }
    public Integer getQuantiteTotal() {
        return this.quantiteTotal;
    }
    
    public void setQuantiteTotal(Integer quantiteTotal) {
        this.quantiteTotal = quantiteTotal;
    }
    public Set getCommandeHasProduits() {
        return this.commandeHasProduits;
    }
    
    public void setCommandeHasProduits(Set commandeHasProduits) {
        this.commandeHasProduits = commandeHasProduits;
    }




}


