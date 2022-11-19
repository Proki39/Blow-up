/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lucienboulard
 */
public class Jeu {
	private Joueur unJoueur;
	private Carte uneCarte;
	public static MondeGenerateur unMonde;
	public RectangleTimer unRectTimer;
	private int score;
	private BufferedImage victory, gameOver;
	private Colision colision;

	public Jeu() {
		this.uneCarte = new Carte();
		this.unMonde = new MondeGenerateur();
		this.unRectTimer = new RectangleTimer();
		this.unJoueur = new Joueur("Joueur");
		this.score = 0;
		this.colision = new Colision();
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

	}

	public void rendu(Graphics2D contexte) {
		uneCarte.rendu(contexte);
		unJoueur.rendu(contexte);
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
		if (colision.estMort((float) unJoueur.getX(), (float) unJoueur.getY(), unMonde.blocos)) {
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

	public Joueur getUnJoueur() {
		return unJoueur;
	}

	public Carte getUneCarte() {
		return uneCarte;
	}

}
