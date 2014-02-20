/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.dao;

import hibernate.util.NewHibernateUtil;
import java.util.Date;
import java.util.List;
import modele.metier.Commande;
import modele.metier.CommandeHasProduit;
import modele.metier.Produit;
import modele.metier.Type;
import modele.metier.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoCommandeHasProduit implements  IDao<CommandeHasProduit>{

   
    @Override
    public List<CommandeHasProduit> selectAll() {
   
        Session session = new NewHibernateUtil().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        Query  queryAllCommandeHasProduit=  session.createQuery("From CommandeHasProduit");
        
        List<CommandeHasProduit> listeCommandeHasProduits = queryAllCommandeHasProduit.list();
        
        return listeCommandeHasProduits;
    
    
    }

    @Override
    public boolean insert(CommandeHasProduit objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CommandeHasProduit> selectById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CommandeHasProduit objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
