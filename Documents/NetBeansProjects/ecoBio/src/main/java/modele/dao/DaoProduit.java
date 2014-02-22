/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Produit;
import modele.metier.ProduitHasUser;
import modele.metier.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Virginie
 */
public class DaoProduit implements IDao<Produit> {

    @Override
    public List<Produit> selectAll() {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(objet);
            tx.commit();
            execution = true;
        } catch (Exception e) {
            e.getMessage();
        }
        return execution;
    }

    @Override
    public boolean update(Produit objet) {
        boolean execution = false;
        try {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        }
        return execution;
    }

    @Override
    public Produit selectById(int id) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.delete(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        }
        return execution;

    }
//********** Requête TABLE : ProduitHasUser *********//

    /**
     * Cette méthode permet de récupéré tous les produits que l'utilisateur
     * qu'il a déjà vendu ou en vente
     *
     * @param user
     * @return type List d'objet ProduitHasUser
     */
    public List<ProduitHasUser> listeProduitByUser(User user) {
        List<ProduitHasUser> listeProduitByUser = new ArrayList();
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(ProduitHasUser.class);
            criteria.add(Restrictions.eq("user", user));
            
            listeProduitByUser = criteria.list();
            
        } catch (HibernateException e) {
            e.getMessage();
        }finally{
            session.close();
        }
        return listeProduitByUser;
    }

    /**
     * Cette méthode permet l'insertion la relation produit et user
     * @param produitUser
     * @return
     */
    public boolean insertProduitByUserTabAsso(ProduitHasUser produitUser) {
        boolean execution = false;
        Session session =  NewHibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(produitUser);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        }finally{
            session.close();
        }

        return execution;
    }

   
}
