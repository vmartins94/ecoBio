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
import modele.metier.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoCommande implements IDao<Commande>{

   
    @Override
    public List<Commande> selectAll() {
       Session session = new NewHibernateUtil().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query  queryAllCommande = session.createQuery("From Commande");
        List<Commande> listeCommande = queryAllCommande.list();
        return listeCommande;
    
    }

    @Override
    public boolean insert(Commande objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande> selectById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Commande objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Commande> selectAllMyCommande(User user){
        return null;
    }
    
}
