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
    private boolean gauche, droite, bas, haut, saut, moving;
    public static int tempsSaut = 1 ; 
    private Block unBlock;
    private Colision colision;
  

    public Joueur(Block unBlock) {
        this.x = 540;
        this.y = 10400;
        this.gauche = false;
        this.droite = false;
        this.bas = false;
        this.haut = false;
        this.saut = false;
        this.unBlock = unBlock;
        this.colision = new Colision();

        try {
        	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void gravite() {
	   if (this.getY() + sprite.getWidth() <= 10400 && !colision.Colision1(0, +5, unBlock, getJoueur())) {
			   this.setY(this.getY() + 5);
			  
	   }
	   if (this.getY() + sprite.getWidth() > 10400) {
		   		this.setY(10400-sprite.getHeight());
		   		
		   		
	   }
   }

    public void miseAJour() {;
        if (this.gauche && !colision.Colision1(-10, 0, unBlock, getJoueur())) {
            x -=10;
            try {
            	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
        		} catch (IOException ex) {
        		Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}  
            }else{
                if (this.gauche && !colision.Colision1(-2, 0, unBlock, getJoueur())) {
                    x -=2;
                    try {
                        this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
                        } catch (IOException ex) {
                        Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
                        }  
                    }
            }
        
        if (this.droite && !colision.Colision1(10, 0, unBlock, getJoueur())) {
            x += 10;
            try {    	
        		this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
        		} catch (IOException ex) {
        			Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}
        }else{
            if (this.droite && !colision.Colision1(2, 0, unBlock, getJoueur())) {
                x += 2;
                try {    	
                    this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
                    } catch (IOException ex) {
                        Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
        if (x > 1040 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 1040 - sprite.getWidth();
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if(this.bas && !colision.Colision1(0, 10, unBlock, getJoueur())){
            y+=10;  
        }else{
            if(this.bas && !colision.Colision1(0, 2, unBlock, getJoueur())){
                y+=2;
            }
        }
        if(this.haut && !colision.Colision1(0, -10, unBlock, getJoueur())){
            y-=10;
        }else{
            if(this.haut && !colision.Colision1(0, -2, unBlock, getJoueur())){
                y-=2;
            }
        }
        this.gravite();
        if (this.saut) {
        	this.setY(this.getY()- 104);
        	tempsSaut--;
        	if (tempsSaut <= 0) {
        		this.setSaut(false);
        	}
        	
        }
        

    }


    public void rendu(Graphics2D contexte) {
        if( y > 10400 - 3*104 ){
            contexte.drawImage(this.sprite, (int) x, (int) y- 93*104 , null);       
        }
        else {
            
            contexte.drawImage(this.sprite, (int) x, (int) y%104 + 4*104 , null);
        }
        
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
    
    public Joueur getJoueur(){
        return this;
    }
    

}
