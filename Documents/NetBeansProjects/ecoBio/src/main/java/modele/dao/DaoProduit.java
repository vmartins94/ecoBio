/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.dao;

import hibernate.util.NewHibernateUtil;
import java.util.List;
import modele.metier.Produit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoProduit implements IDao<Produit>{


    @Override
    public List<Produit> selectAll() {
        Session session = new NewHibernateUtil().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        Query  queryAllProduit=  session.createQuery("From Produit");
        
        List<Produit> listeProduits = queryAllProduit.list();
        
        return listeProduits;
    
    }

    @Override
    public boolean insert(Produit objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produit> selectById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Produit objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}
