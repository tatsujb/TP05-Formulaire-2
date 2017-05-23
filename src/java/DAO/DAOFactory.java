/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Utilisateur;

/**
 *
 * @author Stefan
 */
public class DAOFactory {

    public static DAO<Utilisateur> getUtilisateurDAO() {
        return new UtilisateurDAO();
    }

}
