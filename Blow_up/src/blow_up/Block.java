package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;

/**
 * 
 *
 * @author rafaeloritaa
 */
public class Block {

    protected BufferedImage sprite = BlockSheet.bois;
    protected double x, y;
    
    private int materiau, n_mat = 4;
    private boolean gen_up = false, gen_down = false, gen_right = false, gen_left = false;
    private  boolean colision;
    
    
    

    public Block() {
        
        //GENERATION ALEATOIRE 
        genRandom();
        
        lancer();
    }

    public void miseAJour() {
        y = y + 5;
        System.out.println(y);
        if(gen_down && y>=10400-getLargeur()-64){      //Colision avec le sol
            
            y = 10400-getLargeur()-64;
        }
        if(y>=10400-getLargeur()){
            
            y = 10400-getLargeur();
        }
    }

    public void rendu(Graphics2D contexte, int a) {

        contexte.drawImage(this.sprite, (int) x, (int) y - a*104, null);

        //DRAW
        if(gen_up){
            contexte.drawImage(this.sprite, (int) x, (int) y-64 - a*104, null);
        }
        if(gen_down){
            contexte.drawImage(this.sprite, (int) x, (int) y+64 - a*104, null);
        }
        if(gen_left){
            contexte.drawImage(this.sprite, (int) x-64, (int) y - a*104, null);
        }
        if(gen_right){
            contexte.drawImage(this.sprite, (int) x+64, (int) y - a*104, null);
        }
    }

    public void lancer() {
        this.x = 10 + Math.random() * 600;
        this.y = 93*104;
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
        
        //Forme
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
        
        //Materiau
        Random random= new Random();  
        IntStream ds = random.ints(1, this.n_mat);
        
        ds.limit(5).forEach(new IntConsumer() {
            @Override
            public void accept(int i) {
                if(i==1) {
                sprite = BlockSheet.rocher;
                }
                if(i==2){
                sprite = BlockSheet.meteore;
                }
                if(i==3){
                sprite = BlockSheet.brique;
                }
                if(i==4){
                sprite = BlockSheet.bois;
                }
            }
        });
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

