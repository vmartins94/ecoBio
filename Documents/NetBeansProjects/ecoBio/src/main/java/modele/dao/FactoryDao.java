/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stagiaire
 */
public class FactoryDao {

    public static IDao<?> getDAO(Object objet) {
        try {
            String className = "modele.dao.Dao" + objet.getClass().getSimpleName();
            
            Class<?> cl;
            Object o = null;
            
            
            cl = Class.forName(className);
            o = cl.newInstance();
            
            
            
            return (IDao<?>) o;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FactoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FactoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FactoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static IDao<?> getDAO(String classe) {

        String className = "modele.dao.Dao"+classe;

        Class<?> cl;
        Object o = null;

        try {
            cl = Class.forName(className);
            o = cl.newInstance();
            
          return (IDao<?>) o;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FactoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FactoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FactoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
