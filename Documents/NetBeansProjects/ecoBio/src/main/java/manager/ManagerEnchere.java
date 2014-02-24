/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.util.List;
import modele.dao.DaoEnchere;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Enchere;
import modele.metier.Produit;


public class ManagerEnchere  implements IManager<Enchere>{
    private Enchere uneEnchere;

    public Enchere getUneEnchere() {
        return uneEnchere;
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
        boolean  valeurExecution = daoEnchere.insert(uneEnchere);
        if (!valeurExecution) {
            return "isKO";
        }
        return "isOk";
    }
    
   
    
}
