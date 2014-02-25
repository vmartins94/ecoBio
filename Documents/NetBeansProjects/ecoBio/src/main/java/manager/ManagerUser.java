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
import modele.dao.DaoUser;
import modele.dao.FactoryDao;
import modele.dao.IDao;
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

    private List<String> selectedMovies;

    private Map<String, String> movies;

    public ManagerUser() {
        movies = new HashMap<String, String>();
        movies.put("Scarface", "Scarface");
        movies.put("Goodfellas", "Goodfellas");
        movies.put("Godfather", "Godfather");
        movies.put("Carlito's Way", "Carlito's Way");
    }

    public List<String> getSelectedMovies() {
        return selectedMovies;
    }

    public void setSelectedMovies(List<String> selectedMovies) {
        this.selectedMovies = selectedMovies;
    }

    public Map<String, String> getMovies() {
        return movies;
    }

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

    //TODO C : La connexion se fera en ajax
    public String verifAuthentification(User user2) {
        user = user2;
        DaoUser userDAO = (DaoUser) FactoryDao.getDAO("User");

        user = userDAO.getUserByLoginPassword(user);
        if (user != null) {

            if (user.getId() > 0) {

                //SI C'EST UN ADMIN ON REDIRIGE VERS LE BACK OFFICE
                if (user.getType() == true) {
                    return Constantes.AUTHENTIFICATION_SUCCESS_ADMIN;
                } else {
                    //SINON ON RESTE SUR LA PAGE COURANTE
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

}
