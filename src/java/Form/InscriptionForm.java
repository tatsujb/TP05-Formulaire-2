package Form;

import Bean.Utilisateur;
import DAO.UtilisateurDAO;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Valodia Tsacanias <https://github.com/valoTs> on 22/05/17.
 */
public class InscriptionForm {

    /* Des constantes */
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String CHAMP_NOM = "name";
    private static final String GLOBAL_ERROR = "existe";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;

    }

    public Utilisateur inscrireUtilisateur(HttpServletRequest request) {
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String password = getValeurChamp(request, CHAMP_PASS);
        String confirmation = getValeurChamp(request, CHAMP_CONF);
        String name = getValeurChamp(request, CHAMP_NOM);
        Utilisateur utilisateur = new Utilisateur();
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);
        try {
            validationMotsDePasse(password, confirmation);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
            setErreur(CHAMP_CONF, null);
        }
        utilisateur.setPassword(password);
        try {
            validationName(name);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        utilisateur.setName(name);
            System.out.println(this.erreurs);

        if (erreurs.isEmpty()) {
            System.out.println("coucou");
        HttpSession session = request.getSession();
            UtilisateurDAO methode = new UtilisateurDAO();
            if (methode.getFromEmail(email) != null){
                session.invalidate();
                setErreur(GLOBAL_ERROR, "l'utilisateur existe déja");
            }else{
                methode.create(utilisateur);
                /* Récupération de la session depuis la requête */
                
                session.setAttribute("user", utilisateur);
            }
            

        } else {
            resultat = "Échec de l'inscription.";
        }

        return utilisateur;
    }

    //Test Email
    private void validationEmail(String email) throws Exception {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {

                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    //Test Mot De Passe
    private void validationMotsDePasse(String password, String confirmation
    ) throws Exception {
        if (password != null && confirmation != null) {
            if (!password.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (password.length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception(
                    "Merci de saisir et confirmer votre mot de passe.");
        }
    }

    //Test Nom
    private void validationName(String name) throws Exception {
        if (name != null && name.length() < 2) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
        }
    }

    // Ajoute un message correspondant au champ spécifié à la map des erreurs.
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    //Méthode utilitaire qui retourne null si un champ est vide, et son contenu sinon.
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
