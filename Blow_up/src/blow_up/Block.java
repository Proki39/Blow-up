package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * 
 *
 * @author rafaelorita
 */
public class Block {

    protected BufferedImage sprite;
    protected double x, y;
    
    private boolean gen_up = false, gen_down = false, gen_right = false, gen_left = false;
    
    
    

    public Block() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../ressources/block_test.png"));
        } catch (IOException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lancer();
    }

    public void miseAJour() {
        y = y + 5;
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
        
        //GENERATION ALEATOIRE
        if(Math.random()>0.5f) {
            gen_up = true;
        }
        if(Math.random()>0.5f) {
            gen_down = true;
        }
        if(Math.random()>0.5f) {
            gen_right = true;
        }
        if(Math.random()>0.5f){
            gen_left = true;
        }
        
        //DRAW
        if(gen_up){
            contexte.drawImage(this.sprite, (int) x, (int) y-64, null);
        }
        if(gen_down){
            contexte.drawImage(this.sprite, (int) x, (int) y+64, null);
        }
        if(gen_left){
            contexte.drawImage(this.sprite, (int) x-64, (int) y, null);
        }
        if(gen_right){
            contexte.drawImage(this.sprite, (int) x+64, (int) y, null);
        }
    }

    public void lancer() {
        this.x = 10 + Math.random() * 600;
        this.y = -27;
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

