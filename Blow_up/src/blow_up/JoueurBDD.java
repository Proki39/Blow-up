/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Color;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leandre
 */
public class JoueurBDD {
    protected Carte laCarte;

    public Joueur(Carte laCarte) {
        this.laCarte = laCarte;
    }

    public void miseAJour() {
    }

    public void rendu(Graphics2D contexte) {

        try {

            Connection connexion = SingletonJDBC.getInstance().getConnection();

            PreparedStatement requete = connexion.prepareStatement("SELECT pseudo, latitudeX, longitudeY FROM Joueur;");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) {
                
                String pseudo = resultat.getString("pseudo");
                double latitude = resultat.getDouble("latitudeX");
                double longitude = resultat.getDouble("longitudeY");
                System.out.println(pseudo + " = (" + latitude + "; " + longitude + ")");
                
                int x = laCarte.longitudeEnPixel(longitude);
                int y = laCarte.latitudeEnPixel(latitude);
                contexte.setColor(Color.red);
                contexte.fillOval(x - 5, y - 5, 10, 10);
                contexte.drawString(pseudo, x + 5, y - 5);
            }

            requete.close();           

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    
}
