/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Etat;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author v.martins
 */
public class DaoEtat implements IDao<Etat> {

    @Override
    public boolean insert(Etat objet) {
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
    public List<Etat> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Etat> listeEtat = new ArrayList<Etat>();
        try {
            Transaction tx = session.beginTransaction();
            Query queryEtat = session.createQuery("From Etat");
            listeEtat = queryEtat.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return listeEtat;
    }

    @Override
    public Etat selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Etat etat = null;
        try {
            Transaction tx = session.beginTransaction();
            Query queryEtat = session.createQuery("From Etat where id=?");
            queryEtat.setInteger(0, id);
            etat = (Etat) queryEtat.uniqueResult();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return etat;
    }

    @Override
    public boolean update(Etat objet) {
        boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.update(objet);
            tx.commit();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return execution;

    }

    @Override
    public boolean delete(Etat objet) {
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
