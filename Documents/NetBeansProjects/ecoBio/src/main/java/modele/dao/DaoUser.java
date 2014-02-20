/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.dao;

import hibernate.util.NewHibernateUtil;
import java.util.List;
import modele.metier.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Virginie
 */
public class DaoUser implements IDao<User>{

    @Override
    public List<User> selectAll() {
      Session session = new NewHibernateUtil().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query  queryAllUser = session.createQuery("From User");
        List<User> listeUser = queryAllUser.list();
        
        return listeUser;
    
    
    }

    @Override
    public boolean insert(User objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> selectById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //TODO C
    public User getUserByLoginPassword(User user){
        return user;
    }
    
}
