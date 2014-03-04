/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import modele.dao.DaoEvenement;
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
    private Evenement selectedEvenement;
    

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

    public void createEvent(Evenement event, User connectedUser) {
        monEvenement = event;
        monEvenement.setUser(connectedUser);
        create();
    }

    public void participer(Evenement event, User user) {
        monEvenement = event;
        monEvenement.getUsers().add(user);

        IDao eventDao = FactoryDao.getDAO("Evenement");
        eventDao.update(monEvenement);
    }
    
     public List<Evenement> selectLastThreeEvenement() {

        DaoEvenement daoEvenement = (DaoEvenement) FactoryDao.getDAO("Evenement");
        List<Evenement> listeEvenement = daoEvenement.selectLastThreeEvenement();

        if (!listeEvenement.isEmpty()) {
            return listeEvenement;
        }
        return null;
    }

    public Evenement getMonEvenement() {
        return monEvenement;
    }

    public void setMonEvenement(Evenement monEvenement) {
        this.monEvenement = monEvenement;
    }

    public Evenement getSelectedEvenement() {
        return selectedEvenement;
    }

    public void setSelectedEvenement(Evenement selectedEvenement) {
        this.selectedEvenement = selectedEvenement;
    }

    /**
     * Cette methode permet de récupérer tous les evenements cree par user
     * @param user
     * @return liste evenements
     */
    public List<Evenement> listeEvenementCreeByUser(User user) {
        DaoEvenement eventDao = (DaoEvenement) FactoryDao.getDAO("Evenement");
        return eventDao.selectAllByUserCree(user);

    }
   
}
