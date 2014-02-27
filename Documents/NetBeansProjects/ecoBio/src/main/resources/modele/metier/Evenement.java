package modele.metier;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Evenement generated by hbm2java
 */
public class Evenement  implements java.io.Serializable {


     private Integer id;
     private User user;
     private String titre;
     private int cp;
     private String rue;
     private Integer prix;
     private Integer nbParticipant;
     private String description;
     private String adresse;
     private String ville;
     private Date dateDebut;
     private Date dateFin;
     private Set users = new HashSet(0);

    public Evenement() {
    }

	
    public Evenement(User user, String titre, int cp, String rue, String description, String adresse, String ville, Date dateDebut, Date dateFin) {
        this.user = user;
        this.titre = titre;
        this.cp = cp;
        this.rue = rue;
        this.description = description;
        this.adresse = adresse;
        this.ville = ville;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    public Evenement(User user, String titre, int cp, String rue, Integer prix, Integer nbParticipant, String description, String adresse, String ville, Date dateDebut, Date dateFin, Set users) {
       this.user = user;
       this.titre = titre;
       this.cp = cp;
       this.rue = rue;
       this.prix = prix;
       this.nbParticipant = nbParticipant;
       this.description = description;
       this.adresse = adresse;
       this.ville = ville;
       this.dateDebut = dateDebut;
       this.dateFin = dateFin;
       this.users = users;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getTitre() {
        return this.titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public int getCp() {
        return this.cp;
    }
    
    public void setCp(int cp) {
        this.cp = cp;
    }
    public String getRue() {
        return this.rue;
    }
    
    public void setRue(String rue) {
        this.rue = rue;
    }
    public Integer getPrix() {
        return this.prix;
    }
    
    public void setPrix(Integer prix) {
        this.prix = prix;
    }
    public Integer getNbParticipant() {
        return this.nbParticipant;
    }
    
    public void setNbParticipant(Integer nbParticipant) {
        this.nbParticipant = nbParticipant;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getVille() {
        return this.ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    public Date getDateDebut() {
        return this.dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return this.dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }




}


