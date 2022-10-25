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
    Joueur unJoueur = new Joueur();
    Carte uneCarte = new Carte();
   
         
    
    public void rendu(Graphics2D contexte) {
        uneCarte.rendu(contexte);
        unJoueur.rendu(contexte);
        // 1. Rendu du décor
        //uneCarte.rendu(contexte);
        // 2. Rendu des sprites
        //uneBlock.rendu(contexte);
        // 3. Rendu des textes
    }
        
    
    
    public void miseAJour () {

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
    
    
    
}
