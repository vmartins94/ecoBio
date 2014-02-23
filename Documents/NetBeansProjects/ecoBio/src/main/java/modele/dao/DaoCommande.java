/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import modele.metier.Commande;
import modele.metier.CommandeHasProduit;
import modele.metier.Produit;
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
public class DaoCommande implements IDao<Commande> {

    @Override
    public List<Commande> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Commande> listeCommande = new ArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Query queryAllCommande = session.createQuery("From Commande");
            listeCommande = queryAllCommande.list();
        }catch(HibernateException e){
            e.getMessage();
        }finally{
            session.close();
        }
        return listeCommande;

    }

    @Override
    public boolean insert(Commande commande) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean execution = false;
        try {

            Transaction tx = session.beginTransaction();
            session.save(commande);
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
    public boolean update(Commande objet) {
        boolean execution = false;
        Session session =  HibernateUtil.getSessionFactory().openSession();
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
    public Commande selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Commande commande = null;
        try {

            Transaction tx = session.beginTransaction();
            Query queryCommande = session.createQuery("From Commande where id=?");
            queryCommande.setInteger(0, id);
            commande = (Commande) queryCommande.uniqueResult();
            return commande;
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return commande;
    }

    @Override
    public boolean delete(Commande objet) {
        boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.delete(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return execution;

    }

    /**
     * Cette methode permet de récupere toutes les commandes passés par un
     * client
     *
     * @param user
     * @return type liste d'objet commande
     */
    public List<Commande> selectAllMyCommande(User user) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Commande> listeCommandeByUser = new ArrayList();
        try {

            Transaction tx = session.beginTransaction();

            Query queryCommande = session.createQuery("From Commande where user.id=:id");
            int id = user.getId();
            queryCommande.setInteger("id", id);

            listeCommandeByUser = queryCommande.list();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            session.close();
        }

        return listeCommandeByUser;
    }

    //********** Requete pour la table CommandeHasProduit **********//
    /**
     * Cette methode permet d'insert une commande en fonction d'un pronduit et
     * d'une quantité
     *
     * @param commande
     * @param produit
     * @param qte
     * @return type boolean = true lorsque la requête a bien été executer
     */
    public boolean insertTableAsso(CommandeHasProduit commandeHasProduit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean execution = false;
        try {
            Transaction tx = session.beginTransaction();
            session.save(commandeHasProduit);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return execution;
    }

    /**
     * Cette méthode permet de récupere tous les commandes passés et les
     * produits
     *
     * @return type Liste d'objet de CommandeHasProduit
     */
    public List<CommandeHasProduit> selectAllCommandeProduitTableAsso() {

        List<CommandeHasProduit> listeCommandesProduits = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(CommandeHasProduit.class);
            listeCommandesProduits = criteria.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return listeCommandesProduits;
    }


}
