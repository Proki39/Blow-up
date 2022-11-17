/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

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
    private Colision colision;
    public List<Block> blocos =  new ArrayList<Block>();
  

    public Joueur(List<Block> blocos) {
        this.x = 540;
        this.y = 10400;
        this.gauche = false;
        this.droite = false;
        this.bas = false;
        this.haut = false;
        this.saut = false;
        this.blocos = blocos;
        this.colision = new Colision();

        try {
        	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void gravite() {
	   if (this.getY() + sprite.getHeight() <= 10400 && !colision.Colision1(0, 5, blocos, getJoueur())) {
			   this.setY(this.getY() + 10);
			  
	   }
	   if (this.getY() + sprite.getHeight() > 10400) {
		   		this.setY(10400-sprite.getHeight());
		   		
		   		
	   }
   }

    public void miseAJour(int speed) {

        if (this.gauche && !colision.Colision1(-speed, -10, blocos, getJoueur())) {
            x -=speed;
            try {
            	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/spritemiroir.png"));
        		} catch (IOException ex) {
        		Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}
            }
        
        if (this.droite && !colision.Colision1(speed, -10, blocos, getJoueur())) {
            x += speed;
            try {    	
        		this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));
        		} catch (IOException ex) {
        			Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        		}
        }
        if (x > 1040 + 45 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 1040 + 45 - sprite.getWidth();
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if(this.bas && !colision.Colision1(0, speed, blocos, getJoueur())){
            y+=speed; 
        }
        if(this.haut && !colision.Colision1(0, -speed, blocos, getJoueur())){
            y-=speed;
        }

        this.gravite();
        if (this.saut && (colision.Colision1(-10, 10, blocos, getJoueur()) || colision.Colision1(10, 10, blocos, getJoueur()) || this.getY() + sprite.getHeight() == 10400 )) {
        	this.setY(this.getY()- 150);
        	tempsSaut--;
        	if (tempsSaut <= 0) {
        		this.setSaut(false);
        	}
        	
        }
        Camera.camera_x = (int) this.getX()-1040/2;
        Camera.camera_y = (int) this.getY()-728/2;
        

    }


    public void rendu(Graphics2D contexte) {
            contexte.drawImage(this.sprite, (int) x, (int) y - Camera.camera_y, null);


        
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
