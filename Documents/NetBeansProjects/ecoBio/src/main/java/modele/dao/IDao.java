/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.dao;

import java.util.List;

/**
 *
 * @author Virginie
 */
public interface IDao<T> {
    
    public boolean insert(T objet);
    public List<T> selectAll();
    public T selectById(int id);
    public boolean update(T objet);
    public boolean delete(T objet);
    
}
