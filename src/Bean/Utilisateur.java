package Bean;

/**
 * Created by Valodia Tsacanias <https://github.com/valoTs> on 22/05/17.
 */
public class Utilisateur {

    private int id;
    private String email;
    private String password;
    private String nom ;


    //Constructeurs
    public Utilisateur() {
    }

    public Utilisateur(int id, String email, String password, String nom) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
    }
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    //ToString
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
