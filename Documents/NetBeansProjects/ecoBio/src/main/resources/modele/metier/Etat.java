package modele.metier;

import java.util.HashSet;
import java.util.Set;

/**
 * Etat generated by hbm2java
 */
public class Etat  implements java.io.Serializable {


     private Integer id;
     private String nom;
     private Set commandes = new HashSet(0);

    public Etat() {
    }

	
    public Etat(String nom) {
        this.nom = nom;
    }
    public Etat(String nom, Set commandes) {
       this.nom = nom;
       this.commandes = commandes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Set getCommandes() {
        return this.commandes;
    }
    
    public void setCommandes(Set commandes) {
        this.commandes = commandes;
    }




}


