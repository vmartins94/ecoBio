/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modele.dao.DaoUser;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Produit;
import modele.metier.User;
import utils.Constantes;

/**
 *
 * @author Virginie
 */
public class ManagerUser implements IManager<User>, Serializable {

    //TODO C : Si on modifie le user, on modifira le bean injecté
    private User user;
    //TODO C 
    private ManagerPanier monPanier;
    public ManagerUser() {}

    @Override
    public List<User> findAll() {

        IDao userDAO = FactoryDao.getDAO("User");

        return userDAO.selectAll();
    }

    @Override
    public String create() {
        IDao userDAO = FactoryDao.getDAO("User");
        userDAO.insert(user);
        return "";
    }
    public String create(User utilisateur) {
        user= utilisateur;
        IDao userDAO = FactoryDao.getDAO("User");
        userDAO.insert(user);
        return "Inscription_sucess";
    }

    //TODO C : La connexion se fera en ajax
    public String verifAuthentification(User user2) {
        
        user = user2;
        DaoUser userDAO = (DaoUser) FactoryDao.getDAO("User");

        user = userDAO.getUserByLoginPassword(user);
        if (user != null) {

            if (user.getId() > 0) {

                //SI C'EST UN ADMIN ON REDIRIGE VERS LE BACK OFFICE
                
                if(user.getType() != null){
                    if (user.getType() == true) {
                        return Constantes.AUTHENTIFICATION_SUCCESS_ADMIN;
                    } else {
                        //SINON ON RESTE SUR LA PAGE COURANTE
                        return Constantes.AUTHENTIFICATION_SUCCESS_USER;
                    }
                } else {
                    return Constantes.AUTHENTIFICATION_SUCCESS_USER;
                }
            }
        }
        //DANS LE CAS DE L'ECHEC ON RETOURNE DANS LA PAGE D'ACCUEIL
        return Constantes.AUTHENTIFICATION_ECHEC;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ManagerPanier getMonPanier() {
        return monPanier;
    }

    public void setMonPanier(ManagerPanier monPanier) {
        this.monPanier = monPanier;
    }
     public List convertirSetEnList(Set set){
        List list = new ArrayList(set);
        return list;
     }

}
