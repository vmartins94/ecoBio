/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.io.Serializable;
import java.util.List;
import modele.dao.DaoUser;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.User;
import utils.Constantes;

/**
 *
 * @author Virginie
 */
public class ManagerUser implements IManager<User> ,Serializable{
    
    //TODO C : Si on modifie le user, on modifira le bean injecté
    private  User user;
    //TODO C 
    private ManagerPanier monPanier;

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String create() {
        IDao userDAO = FactoryDao.getDAO("User");
        userDAO.insert(user);
        return "";
    }
    
    //TODO C : La connexion se fera en ajax
    public String verifAuthentification(){
        DaoUser userDAO = (DaoUser) FactoryDao.getDAO("User");
//        user = userDAO.getUserByLoginPassword(user);
        
        user=new User();
        user.setId(-1);
      
        if(user.getId() > 0){
            
            //SI C'EST UN ADMIN ON REDIRIGE VERS LE BACK OFFICE
            if(true /*user.getRole == 1*/){
                return Constantes.AUTHENTIFICATION_SUCCESS_ADMIN;
            } else {
                //SINON ON RESTE SUR LA PAGE COURANTE
                return Constantes.AUTHENTIFICATION_SUCCESS_USER; 
            }
        }
        //DANS LE CAS DE L'ECHAC ON RETOURNE DANS LA PAGE D'ACCUEIL
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
