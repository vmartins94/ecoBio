/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modele.dao.FactoryDao;
import modele.dao.IDao;
import modele.metier.Commande;
import modele.metier.CommandeHasProduit;
import modele.metier.Produit;
import modele.metier.User;

/**
 *
 * @author Virginie
 */
public class ManagerCommandeHasProduit implements IManager<CommandeHasProduit> ,Serializable{
    private CommandeHasProduit commandeProduit;

    public CommandeHasProduit getCommandeProduit() {
        return commandeProduit;
    }

    public void setCommandeProduit(CommandeHasProduit commandeProduit) {
        this.commandeProduit = commandeProduit;
    }
    
    

    @Override
    public List<CommandeHasProduit> findAll() {
        
        
        IDao daoCommandeProduit = FactoryDao.getDAO("CommandeHasProduit");
        List<CommandeHasProduit> listeCommandeProduit = daoCommandeProduit.selectAll();
        
        return listeCommandeProduit;
    }

    @Override
    public String create() {
        
        return "ok";
    }
    
}
