/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author roritaru
 */
public class RectangleTimer {
    
    protected BufferedImage sprite;
    protected double x, y, spd, margin;
    
    protected static int width = 10400;
    
    public RectangleTimer(){
        //Localisation initiale
        this.x = -100;
        this.y = 10700;
        this.spd = 5;
        this.margin = 500;
        
        //SPRITE
        try {
        	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/RectangleTimer.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void miseAJour() {
        if (this.y > Jeu.unMonde.blocPlusHaut()+margin){
            this.y -= spd;
        }
        System.out.println(Jeu.unMonde.blocPlusHaut());
        

    }
    
    
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y - Camera.camera_y, null);
    }
    
    
    
    public boolean isTouchingRect(int xnext, int ynext){
        
        Rectangle HitJoueur = new Rectangle(xnext,ynext,50,110);
        Rectangle HitRectTimer = new Rectangle((int) this.x,(int) this.y,this.width,this.width);
        
        return HitJoueur.intersects(HitRectTimer);

    }
    
    
    
    
    
    
    
    
    
    
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}
