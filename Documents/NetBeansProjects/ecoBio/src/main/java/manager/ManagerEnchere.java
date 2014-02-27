/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIInput;
import modele.dao.DaoEnchere;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Enchere;
import modele.metier.Produit;
import modele.metier.User;

public class ManagerEnchere implements IManager<Enchere>, Serializable {

    private Enchere uneEnchere;
    private UIInput inputPrix;

    public Enchere getUneEnchere() {
        return uneEnchere;
    }

    public UIInput getInputPrix() {
        return inputPrix;
    }

    public void setInputPrix(UIInput inputPrix) {
        this.inputPrix = inputPrix;
    }

    public void setUneEnchere(Enchere uneEnchere) {
        this.uneEnchere = uneEnchere;
    }

    @Override
    public List<Enchere> findAll() {
        IDao daoEnchere = FactoryDao.getDAO("Enchere");
        List<Enchere> listeEnchere = daoEnchere.selectAll();
        if (!listeEnchere.isEmpty()) {
            return listeEnchere;
        }
        return null;
    }

    @Override
    public String create() {
        IDao daoEnchere = FactoryDao.getDAO("Enchere");
        boolean valeurExecution = daoEnchere.insert(uneEnchere);
        if (!valeurExecution) {
            return "isKO";
        }
        return "isOk";
    }
    
    public String create(Enchere enchere) {
        IDao daoEnchere = FactoryDao.getDAO("Enchere");
        boolean valeurExecution = daoEnchere.insert(enchere);
        if (!valeurExecution) {
            return "isKO";
        }
        return "isOk";
    }

    public String update(Enchere enchere) {
        IDao daoEnchere = FactoryDao.getDAO("Enchere");
        boolean valeurExecution = daoEnchere.update(enchere);
        if (!valeurExecution) {
            return "isKO";
        }
        return "";
    }

//    public String update(Enchere enchere) {
//        IDao daoEnchere = FactoryDao.getDAO("Enchere");
//        String chainePrix = (String) this.inputPrix.getSubmittedValue();
//        float prix = Float.valueOf(chainePrix);
//        this.uneEnchere.setPrix(prix);
//       enchere.setPrix(prix);
//       enchere.setIntUserId(1); // modifier faut prendre l'utilisateur en session
//        //enchere.setPrix(I);
//
//        boolean valeurExecution = daoEnchere.update(enchere);
//        if (!valeurExecution) {
//            return "isKO";
//        }
//        return "";
//    }

    
    
    /**
     * Cette permet de vérifiere si un utilisateur a déjà fait une enchere pour
     * un produit en particulier.
     * @param listeEnchereByProduit
     * @param userConnecter
     * @param nouveauEnchere
     * */
    public void enchereExisteByUser(List<Enchere> listeEnchereByProduit, User userConnecter, Enchere nouveauEnchere) {
        boolean dejaEnchereForProduit = false;
        if (!listeEnchereByProduit.isEmpty()) {
            for (Enchere enchere : listeEnchereByProduit) {
                if (enchere.getIntUserId().equals(userConnecter.getId())) {
                    dejaEnchereForProduit = true;
                }
            }
        }else{
                dejaEnchereForProduit = false;
        }
        
        //DAOEnchere
        String chainePrix = (String) this.inputPrix.getSubmittedValue();
        float prix = Float.valueOf(chainePrix);
        nouveauEnchere.setPrix(prix);
        nouveauEnchere.setIntUserId(userConnecter.getId());
        
        if(dejaEnchereForProduit){
            update(nouveauEnchere); 
        }else{
            create(nouveauEnchere);
        }
    }

    /**
     * Cette methode permet de retouner le derniere d'enchere pour un produit
     * @param listeEnchereByProduit
     * @return Objet Enchere
     */
    public Enchere derniereEnchereByProduit(List<Enchere> listeEnchereByProduit) {
        Enchere DerniereEnchere = null;
        if(listeEnchereByProduit != null){
           if(!listeEnchereByProduit.isEmpty()){
                DerniereEnchere = listeEnchereByProduit.get(listeEnchereByProduit.size()-1);
            } 
        }
        
        return DerniereEnchere;
    }

}
