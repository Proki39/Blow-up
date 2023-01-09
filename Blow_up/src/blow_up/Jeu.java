/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.sql.ResultSet;

/**
 *
 * @author lucienboulard
 */
public class Jeu {
        protected BufferedImage sprite, coeur;
	private Joueur unJoueur;
	private Carte uneCarte;
	public static MondeGenerateur unMonde;
	public RectangleTimer unRectTimer;
	private int score;
	private BufferedImage victory, gameOver;
	private Colision colision;
	private Meteore meteore;

	public Jeu(String nomJoueur){
		this.uneCarte = new Carte();
		this.unMonde = new MondeGenerateur();
		this.unRectTimer = new RectangleTimer();
		this.unJoueur = new Joueur(nomJoueur);
		this.score = 0;
		this.colision = new Colision();
		this.meteore = new Meteore();
		try {
			this.victory = ImageIO.read(getClass().getResource("../resources/victory.jpeg"));
		} catch (IOException ex) {
			Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			this.gameOver = ImageIO.read(getClass().getResource("../resources/GameOver.png"));
		} catch (IOException ex) {
			Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
		}
                try{
                this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));            
        	} catch (IOException ex) {
                    Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

	
//        public void dessinerAdverssaires(Graphics2D contexte) throws SQLException{
//            try{
//            java.sql.Connection connexion =  DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2022-2023_s1_vs1_tp1_blowup", "user_blowup", "RiFSA*oR!f*F3sPc");
//            PreparedStatement requete = connexion.prepareStatement("SELECT latitudeX , longitudeY FROM Joueur WHERE pseudo <> ?");
//            requete.setString(1,unJoueur.getName());
//            ResultSet resultat = requete.executeQuery();
//            
//            while(resultat.next()){
//                int y = (int) resultat.getDouble("longitudeY");
//                int x = (int) resultat.getDouble("latitudeX");
//                try {
//        	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));            
//        	} catch (IOException ex) {
//                    Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    this.coeur = ImageIO.read(getClass().getClassLoader().getResource("resources/Coeur1.png"));
//                } catch (IOException ex) {
//                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                contexte.drawImage(this.sprite, (int) (x), (int) (y), null); 
//                
//            }
//            requete.close();
//            connexion.close();
//          
//        } catch (SQLException ex){
//            ex.printStackTrace();
//            
//        }
//        }
            
        
        

	public void rendu(Graphics2D contexte){
		uneCarte.rendu(contexte);
		meteore.rendu(contexte);
		unJoueur.rendu(contexte);
                dessinerAdverssaires(contexte);
		unMonde.rendu(contexte);
		unRectTimer.rendu(contexte);
		contexte.drawString("Score : " + score, 20, 40);
		// 1. Rendu du décor
		// uneCarte.rendu(contexte);
		// 2. Rendu des sprites
		// uneBlock.rendu(contexte);
		// 3. Rendu des textes

	}

	public void miseAJour() {
		uneCarte.miseAJour();
		unMonde.miseAJour();
		unRectTimer.miseAJour();
		unJoueur.miseAJour();
                
		this.score = 10400 - (int) this.unJoueur.getY();
		// 1. Mise à jour de l’avatar en fonction des commandes des joueurs
		// 2. Mise à jour des autres éléments (objets, monstres, etc.)
		// uneBlock.miseAJour();
		// 3. Gérer les interactions (collisions et autres règles)
	}

	public boolean estTermine(Graphics2D contexte) {
		if (score > 10000) {
			contexte.drawImage(this.victory, (1040 - 360) / 2, (728 - 360) / 2, null);
			return true;
		}
		// Renvoie vrai si la partie est terminée (gagnée ou perdue)
		if (colision.estMort((float) unJoueur.getX(), (float) unJoueur.getY(), (float) unJoueur.getLargeur(), 
		(float) unJoueur.getHauteur(), unMonde.blocos)) {
			this.unJoueur.respawn();
		}

		if (this.unJoueur.estMortDefinitivement()) {
			contexte.drawImage(this.gameOver, 0, 0, null);
			return true;
		}

		// Si le rectangle touche le joueur
		if (unRectTimer.isTouchingRect((int) unJoueur.getX(), (int) unJoueur.getY())) {
			this.unJoueur.respawn();
		}

		return false;
	}
        public void dessinerAdverssaires(Graphics2D contexte){
            try{
            java.sql.Connection connexion =  DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2022-2023_s1_vs1_tp1_blowup", "user_blowup", "RiFSA*oR!f*F3sPc");
            PreparedStatement requete = connexion.prepareStatement("SELECT latitudeX , longitudeY FROM Joueur WHERE pseudo <> ?");
            requete.setString(1,unJoueur.getName());
            ResultSet resultat = requete.executeQuery();
            
            while(resultat.next()){
                int y = (int) resultat.getDouble("longitudeY");
                System.out.println(y);
                int x = (int) resultat.getDouble("latitudeX");
                contexte.drawImage(this.sprite, (int) (x), (int) (y)- Camera.camera_y, null);
                
                
            }
            requete.close();
            connexion.close();
          
        } catch (SQLException ex){
            ex.printStackTrace();
            
        }
        }

	public Joueur getUnJoueur() {
		return unJoueur;
	}

	public Carte getUneCarte() {
		return uneCarte;
	}

}
