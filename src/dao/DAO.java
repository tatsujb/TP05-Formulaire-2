/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Singleton.SingletonConnection;
import java.sql.Connection;

/**
 *
 * @author Stefan
 */
public abstract class DAO<T> {

    protected Connection connection = SingletonConnection.getInstance();
    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return l ’ objet recherché
     */
    public abstract T find(int id);

    /* Permet de créer une entrée dans la base de données par rapport à un
objet
*
* @param obj
* @returnl’objet créé avec son id*/
    public abstract T create(T obj);
    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     *
     * @param obj
     * @returnl’objet modifié
     */

    public abstract T update(T obj);

    /**
     * Permet la suppression d'une entrée de la base** @param obj
     */

    public abstract void delete(T obj);

}
