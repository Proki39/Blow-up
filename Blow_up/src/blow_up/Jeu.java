/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    public Block unBlock;

    public Jeu() {    
    this.uneCarte = new Carte();
    this.unBlock = new Block();
    this.unJoueur = new Joueur(this.unBlock);
    }

   
         
    
    public void rendu(Graphics2D contexte) {
        uneCarte.rendu(contexte);
        unJoueur.rendu(contexte);
        unBlock.rendu(contexte);
        // 1. Rendu du décor
        //uneCarte.rendu(contexte);
        // 2. Rendu des sprites
        //uneBlock.rendu(contexte);
        // 3. Rendu des textes
    }
        
    
    
    public void miseAJour () {
       uneCarte.miseAJour();
       unBlock.miseAJour();
       unJoueur.miseAJour();
       
        // 1. Mise à jour de l’avatar en fonction des commandes des joueurs
        // 2. Mise à jour des autres éléments (objets, monstres, etc.)
        //uneBlock.miseAJour();
        // 3. Gérer les interactions (collisions et autres règles)
    }
    
    public boolean estTermine() {
        
        // Renvoie vrai si la partie est terminée (gagnée ou perdue)
        return false; 
    }

    public Joueur getUnJoueur() {
        return unJoueur;
    }

    public Carte getUneCarte() {
        return uneCarte;
    }
    
    
    
}
