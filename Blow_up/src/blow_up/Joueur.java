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
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;


/**
 * Exemple de classe lutin
 *
 * @author guillaume.laurent
 */
public class Joueur {

    protected BufferedImage sprite;
    protected double x, y;
    private boolean gauche, droite, bas, haut, saut;
    public static int tempsSaut = 1 ; 
    private String name;
    protected Rectangle2D.Float hitBox;
    private float xDrawOffset = 36;

    public Joueur(String name) {
        this.x = 540;
        this.y = 10200;
        this.gauche = false;
        this.droite = false;
        this.bas = false;
        this.haut = false;
        this.saut = false;
        this.name = name;
        
        try {
        	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }

        initHitBox();

    }

    protected void drawHitbox(Graphics contexte){
        contexte.setColor(Color.PINK);
        contexte.drawRect((int) hitBox.x, (int) (hitBox.y - Camera.camera_y), (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitBox(){
        hitBox = new Rectangle2D.Float((float) x, (float) y, 52, 103);
    }

    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }

    public void miseAJour() {

        int speed = 10, speedX = 0, speedY = 0;

        if (this.gauche && !this.droite) {
            speedX -=speed;
            try {
            	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/spritemiroir.png"));
        		} catch (IOException ex) {
        		Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}
            }
        if (this.droite && !this.gauche) {
            speedX += speed;
            try {    	
        		this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
        		} catch (IOException ex) {
        			Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}
        }
        if(this.bas && !this.haut)
            speedY+=speed; 
        if(this.haut && !this.bas)
            speedY-=2*speed;

        if (this.saut &&
        (!Colision.peutBouger((float) hitBox.x+10, (float) hitBox.y+10, hitBox.width, hitBox.height, Jeu.unMonde.blocos) || 
        !Colision.peutBouger((float) hitBox.x-10, (float) hitBox.y+10, hitBox.width, hitBox.height, Jeu.unMonde.blocos) || 
        hitBox.y + hitBox.height == 10400)) {
        	speedY -= 150;
        	tempsSaut--;
        	if (tempsSaut <= 0) {
        		this.setSaut(false);
                tempsSaut = 1;
        	}
        }

        if (Colision.peutBouger((float) hitBox.x, (float) hitBox.y+10,              //gravité
        hitBox.width, hitBox.height, Jeu.unMonde.blocos)) {
            hitBox.y += 10;  
        }

        if (Colision.peutBouger((float) hitBox.x+speedX, (float) hitBox.y+speedY, 
        hitBox.width, hitBox.height, Jeu.unMonde.blocos)){
            hitBox.x += speedX;
            hitBox.y += speedY;
        }

        

        Camera.camera_x = (int) this.getX()-1040/2;
        Camera.camera_y = (int) this.getY()-728/2;
        

    }


    public void rendu(Graphics2D contexte) {
            contexte.drawImage(this.sprite, (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - Camera.camera_y), null);
            drawHitbox(contexte);

        
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
    public void setSaut(boolean saut) {
        this.saut = saut;
    }
    
    public void setY(double y) {
        hitBox.y = (float) y;
    }

    public double getX() {
        return hitBox.x;
    }

    public double getY() {
        return hitBox.y;
    }

    public double getLargeur() {
        return hitBox.width;
    }

    public double getHauteur() {
        return hitBox.height;
    }
    
    public Joueur getJoueur(){
        return this;
    }

    @Override
    public String toString() {
        return "Joueur{" + "x=" + x + ", y=" + y + ", name=" + name + '}';
    }
    

}
