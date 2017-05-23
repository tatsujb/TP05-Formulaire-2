package DAO;

import Bean.Utilisateur;
import DAO.DAO;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Stefan Matei <https://github.com/hormatei> on 22/05/17.
 */
public class UtilisateurDAO extends DAO<Utilisateur> {

    private final String TABLE = "inscrits";

    @Override

    public Utilisateur find(int id) {
        Utilisateur utilisateur = null;
        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id = ?";
            //System.out.println("requête : " + req); // DEBUG
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                utilisateur = new Utilisateur(id, result.getString("email"), result.getString("password"), result.getString("name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return utilisateur;
    }

    @Override

    public Utilisateur create(Utilisateur obj) {
        try {
            String req = "INSERT INTO " + TABLE + " (id , email, password,name) VALUES(?,?,?,?)";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, obj.getId());
            pstmt.setString(2, obj.getEmail());
            pstmt.setString(3, obj.getPassword());
            pstmt.setString(4, obj.getName());

// On soumet la requête et on récupère le nombre d'id créés
            int id = pstmt.executeUpdate();
// On pourrait s'arrêter là, mais je préfère récupérer la ligne créée

// Ca permet de savoir ce qu'on a réellement mis dans la DB
            ResultSet rs = pstmt.getGeneratedKeys();
            int last_inserted_id;
            if (rs.first()) { // Si on a des id créés
                last_inserted_id = rs.getInt(1);
// On récupère l'enregistrement créé
                obj = this.find(last_inserted_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;

    }

    @Override

    public Utilisateur update(Utilisateur obj) {
        try {
            String req = "UPDATE " + TABLE + " SET email = ?, "
                    + "password = ?, " + "name=?  WHERE id = ?";
// System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, obj.getEmail());
            pstmt.setString(2, obj.getPassword());
            pstmt.setString(3, obj.getName());
            pstmt.setLong(4, obj.getId());
            pstmt.executeUpdate();
// On récupère l'enregistrement modifié
            obj = this.find(obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override

    public void delete(Utilisateur obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
