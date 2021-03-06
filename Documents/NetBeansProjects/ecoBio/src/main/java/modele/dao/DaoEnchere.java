/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Enchere;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoEnchere implements IDao<Enchere> {

    @Override
    public boolean insert(Enchere objet) {

        boolean execution = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(objet);
            tx.commit();
            execution = true;
        } catch (Exception e) {

        }
        return execution;
    }

    @Override
    public List<Enchere> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Enchere> listeEncheres = new ArrayList();
        try {
            Query queryAllEnchere = session.createQuery("From Enchere");
            listeEncheres = queryAllEnchere.list();
        } catch (HibernateException e) {
            session.close();
        } finally {
            session.close();
        }

        return listeEncheres;
    }

    @Override
    public Enchere selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Enchere enchere = null;
        try {
            Query queryEnchere = session.createQuery("From Enchere where id=?");
            queryEnchere.setInteger(0, id);
            enchere = (Enchere) queryEnchere.uniqueResult();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return enchere;
    }

    @Override
    public boolean update(Enchere uneEnchere) {
        boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
             Query query = session.createQuery("Update Enchere Set prix = ? Where int_userId = ?");
             query.setParameter(0,uneEnchere.getPrix());
             query.setParameter(1,uneEnchere.getIntUserId());
             query.executeUpdate();
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
    public boolean delete(Enchere objet) {
        boolean execution = false;
        Session session =  HibernateUtil.getSessionFactory().openSession();
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
