/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.dao;

import hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import modele.metier.Type;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoType implements IDao<Type> {

    @Override
    public boolean insert(Type objet) {
      boolean execution = false;
      Session session =  HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        }finally{
            session.close();
        }
        return execution;
    
    
    }

    @Override
    public List<Type> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Type> listeType = new ArrayList();
         try{
        Transaction tx = session.beginTransaction();
        Query  queryAllType = session.createQuery("From Type");
        listeType = queryAllType.list();
         }catch(HibernateException e){
             e.getMessage();
         }finally{
             session.close();
         }
         
        return listeType;
    
    }

    @Override
    public Type selectById(int id) {
     Session session = HibernateUtil.getSessionFactory().openSession();
      Type type = null;
        try {
            Transaction tx = session.beginTransaction();
            Query queryType = session.createQuery("From Type where id=?");
            queryType.setInteger(0, id);
            type = (Type) queryType.uniqueResult();
       
        } catch (HibernateException e) {
            e.getMessage();
        } finally {
            session.close();
        }
        return type; }

    @Override
    public boolean update(Type objet) {
        boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.update(objet);
            tx.commit();
            execution = true;
        } catch (HibernateException e) {
            e.getMessage();
        }finally{
            session.close();
        }
        return execution;
    }

    @Override
    public boolean delete(Type objet) {
    boolean execution = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.delete(objet);
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
