package blow_up;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 *
 * @author rafaelorita
 */
public class Block {

    protected BufferedImage sprite = BlockSheet.bois;
    protected double x, y;
    
    private int materiau, n_mat = 4;
    private boolean gen_up = false, gen_down = false, gen_right = false, gen_left = false;
    private  boolean est_fixe = false;
    
    protected static int Bwidth = 104;
    protected static int spd = 6;
    
    

    public Block() {
        
        //GENERATION ALEATOIRE 
        genRandom();
        
        lancer();
    }

    public void miseAJour() {
        
        if(!est_fixe){
            
            //Bloque Tombe
            if(!isCollidingBlock((int) this.getX(),(int) this.getY()+spd)){
               y = y + spd; 
            }else{
                est_fixe = true;
            }
            
            
            //Colision avec le sol
            if(gen_down && y>=10400-getLargeur()-Bwidth){      
            
                y = 10400-getLargeur()-Bwidth;
                est_fixe = true;
            }
            if(y>=10400-getLargeur()){
            
                y = 10400-getLargeur();
                est_fixe = true;
            }
            
            
            //Colision avec les bloques
            if(gen_down && y>=10400-getLargeur()-Bwidth){      
            
                y = 10400-getLargeur()-Bwidth;
                est_fixe = true;
            }
            if(y>=10400-getLargeur()){
            
                y = 10400-getLargeur();
                est_fixe = true;
            }
        }
        
    }

    public void rendu(Graphics2D contexte) {

        contexte.drawImage(this.sprite, (int) x, (int) y - Camera.camera_y, null);

        //DRAW
        if(gen_up){
            contexte.drawImage(this.sprite, (int) x, (int) y-Bwidth - Camera.camera_y, null);
        }
        if(gen_down){
            contexte.drawImage(this.sprite, (int) x, (int) y+Bwidth - Camera.camera_y, null);
        }
        if(gen_left){
            contexte.drawImage(this.sprite, (int) x-Bwidth, (int) y - Camera.camera_y, null);
        }
        if(gen_right){
            contexte.drawImage(this.sprite, (int) x+Bwidth, (int) y - Camera.camera_y, null);
        }
    }

    public void lancer() {
        Random rand = new Random();
        int min = 0;
        int max = 9;
        int nombreAleatoire = rand.nextInt(max - min + 1) + min;
        this.x = nombreAleatoire*104;
        this.y = Camera.camera_y - 10*104;
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
        if(FenetreDeJeu.rand.nextInt(100)>50) {
            gen_up = true;
        }
        if(FenetreDeJeu.rand.nextInt(100)>50) {
            gen_down = true;
        }
        if(FenetreDeJeu.rand.nextInt(100)>50) {
            gen_right = true;
        }
        if(FenetreDeJeu.rand.nextInt(100)>50){
            gen_left = true;
        }
        
        //Materiau
        int i = FenetreDeJeu.rand.nextInt(4);
        
                if(i==0) {
                sprite = BlockSheet.rocher;
                }
                if(i==1){
                sprite = BlockSheet.meteore;
                }
                if(i==2){
                sprite = BlockSheet.brique;
                }
                if(i==3){
                sprite = BlockSheet.bois;
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
    
    
    public boolean isCollidingBlock(int xnext, int ynext){
        
        List<Rectangle> currentRects =  new ArrayList<>();
        
        //If the block has a down block or not
        if (this.gen_down){
            currentRects.add(new Rectangle(xnext,ynext+this.Bwidth,this.Bwidth,this.Bwidth));
                    
        }else{
            currentRects.add(new Rectangle(xnext,ynext,this.Bwidth,this.Bwidth));
        }
        
        //If the block has a left block or not
        if (this.gen_left){
            currentRects.add(new Rectangle(xnext-this.Bwidth,ynext,this.Bwidth,this.Bwidth));
                    
        }
        
        //If the block has a right block or not
        if (this.gen_right){
            currentRects.add(new Rectangle(xnext+this.Bwidth,ynext,this.Bwidth,this.Bwidth));
                    
        }
        
        for(int i=0; i<MondeGenerateur.blocos.size(); i++){
            Block b = MondeGenerateur.blocos.get(i);
            
            if(b == this){
                continue;
            }
            
            /*
            ///////////////////////////////////
            CREATE HITBOX TARGET BLOCKS
            ///////////////////////////////////
            */
            //If the Targetblock has an up block or not
            List<Rectangle> targetRects =  new ArrayList<>();
            
            if (b.gen_up){
                targetRects.add(new Rectangle((int) b.getX(),(int) b.getY()-this.Bwidth,this.Bwidth,this.Bwidth));
                    
            }else{
                targetRects.add(new Rectangle((int) b.getX(),(int) b.getY(),this.Bwidth,this.Bwidth));
            }
        
            //If the Targetblock has a left block or not
            if (b.gen_left){
                targetRects.add(new Rectangle((int) b.getX()-this.Bwidth,(int) b.getY(),this.Bwidth,this.Bwidth));
                    
            }
        
            //If the Targetblock has a right block or not
            if (b.gen_right){
                targetRects.add(new Rectangle((int) b.getX()+this.Bwidth,(int) b.getY(),this.Bwidth,this.Bwidth));
                    
            }
            
            /*
            ///////////////////////////////////
            TEST COLLISION
            ///////////////////////////////////
            */
            for(int j=0; j<currentRects.size(); j++){
                for(int k=0; k<targetRects.size(); k++){
                    if(currentRects.get(j).intersects(targetRects.get(k))){
                        currentRects.clear();
                        targetRects.clear();
                        return true;
                    }
                }
            }
            
        }
        
        return false;
    }
    

}

