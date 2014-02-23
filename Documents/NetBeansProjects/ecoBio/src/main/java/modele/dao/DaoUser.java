/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
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
public class DaoUser implements IDao<User> {

    @Override
    public List<User> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> listeUser = new ArrayList();
        try {
            Transaction tx = session.beginTransaction();
            Query queryAllUser = session.createQuery("From User");
            listeUser = queryAllUser.list();

        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return listeUser;
    }

    @Override
    public boolean insert(User objet) {
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
    public boolean update(User objet) {

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
    public User selectById(int id) {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try {
            Transaction tx = session.beginTransaction();
            Query queryUser = session.createQuery("From User where id=:id");
            queryUser.setInteger("id", id);
            user = (User) queryUser.uniqueResult();
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public boolean delete(User objet) {
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
     * Cette méthode permet de récuperer un utilisateur en fonction d'un mot de
     * passe et login
     *
     * @param user
     * @return
     */
    public User getUserByLoginPassword(User user) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", user)).add(Restrictions.eq("password", user));
        user = (User) criteria.uniqueResult();
        }catch(HibernateException e){
            e.getMessage();
        }finally{
            session.close();
        }
        return user;
    }

}
