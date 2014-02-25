/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.util.List;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Evenement;
import modele.metier.User;

/**
 *
 * @author Chac
 */
public class ManagerEvenement implements IManager<Evenement> {

    Evenement monEvenement;
    
    @Override
    public List<Evenement> findAll() {
        IDao eventDao = FactoryDao.getDAO("Evenement");
        return eventDao.selectAll();
    }

    @Override
    public String create() {
        IDao eventDao = FactoryDao.getDAO("Evenement");
        eventDao.insert(monEvenement);
        return "";
    }

    public void createEvent(Evenement event){
        User user =  new User();
        user.setId(16);
        monEvenement = event;
        monEvenement.setUser(user);
        create();
    }
    
    public Evenement getMonEvenement() {
        return monEvenement;
    }

    public void setMonEvenement(Evenement monEvenement) {
        this.monEvenement = monEvenement;
    }
    
}
