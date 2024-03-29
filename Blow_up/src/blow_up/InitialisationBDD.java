/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blow_up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author leandre
 */
public class InitialisationBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            Connection connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2022-2023_s1_vs1_tp1_blowup", "user_blowup", "RiFSA*oR!f*F3sPc");

            Statement statement = connexion.createStatement();

            statement.executeUpdate("DELETE FROM Joueur;");
       
            ResultSet resultat = statement.executeQuery("SELECT * FROM Joueur;");
            OutilsJDBC.afficherResultSet(resultat);

            statement.close();
            connexion.close();

            // TODO code application logic here
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
