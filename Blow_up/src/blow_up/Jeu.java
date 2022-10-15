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
    
    private BufferedImage fond;
    public Joueur joueur;
    
    public void jeu() { 
        try {
            this.fond = ImageIO.read(getClass().getClassLoader().getResource("resources/jungle.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.joueur = new Joueur();
    }
        // 1. Rendu du décor
        //uneCarte.rendu(contexte);
        // 2. Rendu des sprites
        //uneBlock.rendu(contexte);
        // 3. Rendu des textes
    
    
    public void miseAJour () {
         this.joueur.miseAJour();
        // 1. Mise à jour de l’avatar en fonction des commandes des joueurs
        // 2. Mise à jour des autres éléments (objets, monstres, etc.)
        //uneBlock.miseAJour();
        // 3. Gérer les interactions (collisions et autres règles)
    }
    
    public boolean estTermine() {
        
        // Renvoie vrai si la partie est terminée (gagnée ou perdue)
        return false; 
    }
    
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.fond, 0, 0, null);
        this.joueur.rendu(contexte);
    }
    
}
