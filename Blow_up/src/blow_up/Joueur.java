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
    private boolean gauche, droite, bas, haut, saut;
    public static int tempsSaut = 1 ; 
  

    public Joueur() {
        this.x = 540;
        this.y = 10300;
        this.gauche = false;
        this.droite = false;
        this.bas = false;
        this.haut = false;
        this.saut = false;

        try {
        	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void gravite() {
	   if (this.getY() + sprite.getWidth() <= 10400) {
			   this.setY(this.getY() + 5);
			  
	   }
	   if (this.getY() + sprite.getWidth() > 10400) {
		   		this.setY(10400-sprite.getHeight());
		   		
		   		
	   }
   }

    public void miseAJour() {
        if (this.gauche) {
            x -=10;
            try {
            	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
        		} catch (IOException ex) {
        		Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}     
        }
        if (this.droite) {
            x += 10;
            try {    	
        		this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
        		} catch (IOException ex) {
        			Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}
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
        this.gravite();
        if (this.saut) {
        	this.setY(this.getY()- 30);
        	tempsSaut--;
        	if (tempsSaut <= 0) {
        		this.setSaut(false);
        	}
        	
        }
         
       
       
       

    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y- 93*104 , null);
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
        this.y = y;
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
