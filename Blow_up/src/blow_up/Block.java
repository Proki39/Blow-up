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
    private  boolean colision;
    
    
    

    public Block() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/block_test.png"));
        } catch (IOException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //GENERATION ALEATOIRE 
        genRandom();
        
        lancer();
    }

    public void miseAJour() {
        y = y + 5;

        if(gen_down && y>=728-getLargeur()-64){      //Colision avec le sol
            
            y = 728-getLargeur()-64;
        }
        if(y>=728-getLargeur()){
            
            y = 728-getLargeur();
        }
    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
      
        
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
    
    
    private void genRandom(){
        
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
    }

    public boolean getGenUp(){
        return this.gen_up;
    }
    public boolean getGenDown(){
        return this.gen_down;
    }
    public boolean getGenRight(){
        return this.gen_right;
    }
    public boolean getGenLeft(){
        return this.gen_left;
    }
    

}

