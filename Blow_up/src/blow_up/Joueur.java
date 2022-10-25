/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe lutin
 *
 * @author guillaume.laurent
 */
public class Joueur {

    protected BufferedImage sprite;
    protected double x, y;
    private boolean gauche, droite, bas, haut;

    public Joueur() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/banane.png"));
        } catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 520;
        this.y = 0;
        this.gauche = false;
        this.droite = false;
        this.bas = false;
        this.haut = false;

    }

    public void miseAJour() {
        if (this.gauche) {
            x -=10;
        }
        if (this.droite) {
            x += 10;
        }
        if (x > 1040 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 1040 - sprite.getWidth();
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if(this.bas){
            y+=10;
        }
        if(this.haut){
            y-=10;
        }
       

    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }

    public void setGauche(boolean gauche) {
        this.gauche = gauche;
    }

    public void setDroite(boolean droite) {
        this.droite = droite;
    }
    public void setHaut(boolean haut) {
        this.haut = haut;
    }
    public void setBas(boolean bas) {
        this.bas = bas;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLargeur() {
        return sprite.getHeight();
    }

    public double getHauteur() {
        return sprite.getWidth();
    }

}