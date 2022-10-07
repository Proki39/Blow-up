/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Graphics2D;

/**
 *
 * @author lucienboulard
 */
public class Jeu {
    
    Block uneBlock = new Block();
    
    public void rendu(Graphics2D contexte) { 
        
        // 1. Rendu du décor
        // 2. Rendu des sprites
        uneBlock.rendu(contexte);
        // 3. Rendu des textes
    }
    
    public void miseAJour () {
        
        // 1. Mise à jour de l’avatar en fonction des commandes des joueurs
        // 2. Mise à jour des autres éléments (objets, monstres, etc.)
        uneBlock.miseAJour();
        // 3. Gérer les interactions (collisions et autres règles)
    }
    
    public boolean estTermine() {
        
        // Renvoie vrai si la partie est terminée (gagnée ou perdue)
        return false; 
    }
    
}
