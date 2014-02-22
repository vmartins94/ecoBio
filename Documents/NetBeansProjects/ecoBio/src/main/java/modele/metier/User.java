package modele.metier;

// Generated 22 fevr. 2014 10:53:47 by Hibernate Tools 3.6.0



import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer id;
     private String nom;
     private String prenom;
     private String login;
     private String email;
     private String rue;
     private int cp;
     private String ville;
     private Boolean type;
     private String password;
     private Set evenements = new HashSet(0);
     private Set commandes = new HashSet(0);
     private Set produitHasUsers = new HashSet(0);
     private Set evenements_1 = new HashSet(0);

    public User() {
    }

	
    public User(String nom, String prenom, String login, String email, String rue, int cp, String ville, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.email = email;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.password = password;
    }
    public User(String nom, String prenom, String login, String email, String rue, int cp, String ville, Boolean type, String password, Set evenements, Set commandes, Set produitHasUsers, Set evenements_1) {
       this.nom = nom;
       this.prenom = prenom;
       this.login = login;
       this.email = email;
       this.rue = rue;
       this.cp = cp;
       this.ville = ville;
       this.type = type;
       this.password = password;
       this.evenements = evenements;
       this.commandes = commandes;
       this.produitHasUsers = produitHasUsers;
       this.evenements_1 = evenements_1;
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
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRue() {
        return this.rue;
    }
    
    public void setRue(String rue) {
        this.rue = rue;
    }
    public int getCp() {
        return this.cp;
    }
    
    public void setCp(int cp) {
        this.cp = cp;
    }
    public String getVille() {
        return this.ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    public Boolean getType() {
        return this.type;
    }
    
    public void setType(Boolean type) {
        this.type = type;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Set getEvenements() {
        return this.evenements;
    }
    
    public void setEvenements(Set evenements) {
        this.evenements = evenements;
    }
    public Set getCommandes() {
        return this.commandes;
    }
    
    public void setCommandes(Set commandes) {
        this.commandes = commandes;
    }
    public Set getProduitHasUsers() {
        return this.produitHasUsers;
    }
    
    public void setProduitHasUsers(Set produitHasUsers) {
        this.produitHasUsers = produitHasUsers;
    }
    public Set getEvenements_1() {
        return this.evenements_1;
    }
    
    public void setEvenements_1(Set evenements_1) {
        this.evenements_1 = evenements_1;
    }




}


