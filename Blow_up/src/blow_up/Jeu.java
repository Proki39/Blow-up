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
        int positionJoueur=(int) this.unJoueur.getY()/104;
        if(positionJoueur > 97){
            for(int i = 0 ; i < 7 ; i++){       //défilement de l'arrière plan
                for(int j=0;j<10;j++){
                    int valeur_tuile = this.uneCarte.getDecor()[i+93][j];
                    contexte.drawImage(this.uneCarte.getTuiles()[valeur_tuile], 104*j, 104*i, null);
                }
            }
        }
        else{
        for(int i = 0 ; i < 7 ; i++){       //défilement de l'arrière plan
            for(int j=0;j<10;j++){
                int valeur_tuile = this.uneCarte.getDecor()[i+positionJoueur-4][j];
                contexte.drawImage(this.uneCarte.getTuiles()[valeur_tuile], 104*j, 104*i, null);
                
                
            }
        }
        }
        
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
