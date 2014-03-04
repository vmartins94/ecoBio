/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import manager.ManagerArticle;
import modele.metier.Produit;
import modele.metier.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoProduit implements IDao<Produit> {

    @Override
    public List<Produit> selectAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> listeProduits = new ArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Query queryAllProduit = session.createQuery("From Produit");
            listeProduits = queryAllProduit.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }

        return listeProduits;

    }

    
    @Override
    public boolean insert(Produit objet) {
        boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        
        return execution;
    }

    @Override
    public boolean update(Produit objet) {
        boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.update(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        
        return execution;
    }

    @Override
    public Produit selectById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Produit produit = null;
        try {
            Transaction tx = session.beginTransaction();
            Query queryProduit = session.createQuery("From Produit where id = ?");
            queryProduit.setInteger(0, id);
            produit = (Produit) queryProduit.uniqueResult();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return produit;

    }

    @Override
    public boolean delete(Produit objet) {
        boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.delete(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        }  finally {
            session.close();
        }
        return execution;

    }
//********** Requête  *********//

  
  /**
     * Cette methode permet de récupérer tous les produits qui ont un statut
     * enchere.
     *
     * @return List de type Produit
     */
    public List<Produit> listeAllProduitByEnchere() {
        List<Produit> listeAllProduitByEnchere = new ArrayList<Produit>();
       Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Query queryProduit = session.createQuery("From Produit as p where p.avecEnchere=? and p.id in ( select en.produit.id from Enchere as en where en.dateFin >=  sysdate())");
            queryProduit.setBoolean(0, true);
            listeAllProduitByEnchere = queryProduit.list();
         
    
        }catch(HibernateException e){
            e.getMessage(); 
        }finally{
            session.close();
        }
        
        return listeAllProduitByEnchere;
    }

    public List<Produit> listeAllProduit() {
        List<Produit> listeAllProduit = new ArrayList<Produit>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();

            Query queryProduit = session.createQuery("From Produit where avecEnchere=?");
            queryProduit.setBoolean(0, false);
            //queryProduit.setDate(1, null)//regarder si la date de fin est plus petit que la date systeme
            listeAllProduit = queryProduit.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }

        return listeAllProduit;
    }
    
    /**
     * Récupération des produits en fonction d'un utilisateur
     * @param user
     * @return 
     */
    public List<Produit> selectAllByUser(User user) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Produit> listeProduits = new ArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Query queryAllProduit = session.createQuery("From Produit where userId=?");
            queryAllProduit.setInteger(0, user.getId());
            listeProduits = queryAllProduit.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }

        return listeProduits;

    }
    
    public boolean updateProduit(ManagerArticle MonManagerArticle)
    {
     boolean execution = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
           Integer quantiteActuelle = MonManagerArticle.getProduit().getQuantiteFinale();
           Integer nouvelleQuantite = quantiteActuelle - MonManagerArticle.getQuantite();
            MonManagerArticle.getProduit().setQuantiteFinale(nouvelleQuantite);
            session.update(MonManagerArticle.getProduit());
            tx.commit();
            execution = true;
                
            
           
        } catch (HibernateException e) {
            e.getMessage();
        }
        return execution;
    }
     public List<Produit> selectLastThreeProduit() {

        List<Produit> listeAllProduit = new ArrayList<Produit>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();

            Query queryProduit = session.createQuery("From Produit where avecEnchere=?").setMaxResults(3);
            queryProduit.setBoolean(0, false);
            
            listeAllProduit = queryProduit.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return listeAllProduit;
    }

}
