package Servlet;

import java.io.IOException;
import Bean.Utilisateur;
import Form.InscriptionForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by Valodia Tsacanias <https://github.com/valoTs> on 22/05/17.
 */
public class Inscription extends HttpServlet {

    /* Des constantes */
    private static final String ATT_FORM = "form";
    private static final String ATT_USER = "utilisateur";
    public static final String VUE = "/WEB-INF/inscription.jsp";

    /* Requête GET sur notre formulaire => affichage du formulaire */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /* Soumission du formulaire => réception et contrôle des données */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm();


        /*Appel au traitement et à la validation de la requête, et récupération* du bean en résultant*/
        Utilisateur utilisateur = form.inscrireUtilisateur(request);
        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, utilisateur);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}
