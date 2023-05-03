/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ennou
 */    
  public interface IUser<T> {
    String login(T t) throws SQLException;
    void forgotPassword(T t) throws SQLException;
}
