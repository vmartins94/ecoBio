/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Evenement;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoEvenement implements IDao<Evenement> {

    @Override
    public boolean insert(Evenement objet) {
        boolean execution = false;
        Session session = new NewHibernateUtil().getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {

        } finally {
            session.close();
        }
        return execution;
    }

    @Override
    public List<Evenement> selectAll() {
        Session session = new NewHibernateUtil().getSessionFactory().openSession();
         List<Evenement> listeEtat= new ArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Query queryEtat = session.createQuery("From Evenement");
            listeEtat = queryEtat.list();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return listeEtat;
    }

    @Override
    public Evenement selectById(int id) {
        Session session = new NewHibernateUtil().getSessionFactory().openSession();
        Evenement evenement = null;
        try {
            Transaction tx = session.beginTransaction();
            Query queryEvenement = session.createQuery("From Evenement where id=?");
            queryEvenement.setInteger(1, id);
            evenement = (Evenement) queryEvenement.uniqueResult();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return evenement;
    }

    @Override
    public boolean update(Evenement objet) {
        boolean execution = false;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
    public boolean delete(Evenement objet) {
        boolean execution = false;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
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
