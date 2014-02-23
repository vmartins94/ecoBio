/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Saison;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoSaison implements IDao<Saison> {

    @Override
    public boolean insert(Saison objet) {
        boolean execution = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        }
        return execution;
    }

    @Override
    public List<Saison> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Saison> listeSaison = new ArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Query queryAllSaison = session.createQuery("From Saison");
            listeSaison = queryAllSaison.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return listeSaison;
    }

    @Override
    public Saison selectById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Saison selectByName(String nom) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Saison saison = null;
        try {
            Transaction tx = session.beginTransaction();
            Query querySaison = session.createQuery("From Saison where nom =?");
            querySaison.setString(0, nom);
            saison = (Saison) querySaison.uniqueResult();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return saison;
    }

    @Override
    public boolean update(Saison objet) {

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
    public boolean delete(Saison objet) {
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

}
