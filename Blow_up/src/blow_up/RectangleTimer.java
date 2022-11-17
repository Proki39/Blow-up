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
 * @author roritaru
 */
public class RectangleTimer {
    
    protected BufferedImage sprite;
    protected double x, y, spd, margin;
    
    public RectangleTimer(){
        //Localisation initiale
        this.x = -100;
        this.y = 10600;
        this.spd = 5;
        this.margin = 300;
        
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
        
    }
    
    
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y - Camera.camera_y, null);
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}
