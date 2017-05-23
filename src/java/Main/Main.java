
import Bean.Utilisateur;
import DAO.UtilisateurDAO;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UtilisateurDAO utilisateur = new UtilisateurDAO();
        utilisateur.create(new Utilisateur(0, "lct@hotmail.fr", "12345", "paul"));
    }
}
