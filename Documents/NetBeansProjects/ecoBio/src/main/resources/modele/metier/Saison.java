package modele.metier;
// Generated 22 fevr. 2014 10:53:47 by Hibernate Tools 3.6.0



import java.util.HashSet;
import java.util.Set;

/**
 * Saison generated by hbm2java
 */
public class Saison  implements java.io.Serializable {


     private String nom;
     private Set types = new HashSet(0);

    public Saison() {
    }

	
    public Saison(String nom) {
        this.nom = nom;
    }
    public Saison(String nom, Set types) {
       this.nom = nom;
       this.types = types;
    }
   
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Set getTypes() {
        return this.types;
    }
    
    public void setTypes(Set types) {
        this.types = types;
    }




}

