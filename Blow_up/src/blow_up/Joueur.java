/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blow_up;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.DriverManager;
import java.util.List;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Exemple de classe lutin
 *
 * @author guillaume.laurent
 */
public class Joueur {

    protected BufferedImage sprite, coeur;
    protected double x, y;
    private boolean gauche, droite, bas, haut, saut;
    public static int tempsSaut = 1 ; 
    private String name;
    protected Rectangle2D.Float hitBox;
    private float xDrawOffset = 36;
    private int nbLife;
    private int nbSaut;
    private float gravity_valeur = (float) 3;
    private float gravity_saut = gravity_valeur;
    private float vspd = 0;
    private float hauteur_saut = 35;
    

    public Joueur(String name) {
        this.x = 540;
        this.y = 10200;
        this.gauche = false;
        this.droite = false;
        this.bas = false;
        this.haut = false;
        this.saut = false;
        this.name = name;
        this.nbLife = 10;
        this.nbSaut = 2;
        
        try {
        	this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/sprite.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.coeur = ImageIO.read(getClass().getClassLoader().getResource("resources/Coeur1.png"));
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
        //Colision.peutBouger((float) hitBox.x, (float) hitBox.y-150, hitBox.width, hitBox.height, Jeu.unMonde.blocos) &&
        (!Colision.peutBouger((float) hitBox.x+10, (float) hitBox.y+10, hitBox.width, hitBox.height, Jeu.unMonde.blocos) || 
        !Colision.peutBouger((float) hitBox.x-10, (float) hitBox.y+10, hitBox.width, hitBox.height, Jeu.unMonde.blocos) || 
        hitBox.y + hitBox.height == 10400 ||  this.nbSaut !=0)) {
        	if( this.nbSaut !=0){
                this.nbSaut = this.nbSaut -1;
                
                //this.setY(this.getY()- 150);
                vspd = -hauteur_saut;
                
                tempsSaut--;
                if (tempsSaut <= 0) {
                        this.setSaut(false);
    	}} else {
                this.setNbSaut(2);
            }
        }

        
        //Colision Verticale
        if(!Colision.peutBouger((float) hitBox.x, (float) hitBox.y+vspd,
        hitBox.width, hitBox.height, Jeu.unMonde.blocos)) {
			
            int signVsp;
                if(vspd >= 0)
                {
                    signVsp = 1;
                }else  {
                    signVsp = -1;
                }
                
            while(Colision.peutBouger((float) hitBox.x, (float) hitBox.y+signVsp,
            hitBox.width, hitBox.height, Jeu.unMonde.blocos)) {
                hitBox.y = hitBox.y+signVsp;
            }
            
            if(vspd < 0)
                {
                    vspd = 10;
                }else  {
                    vspd = 0;
                    this.setNbSaut(2);
                    gravity_saut = gravity_valeur;
                }
	}
        
        
        if (Colision.peutBouger((float) hitBox.x, (float) hitBox.y+vspd,              //gravité
        hitBox.width, hitBox.height, Jeu.unMonde.blocos)) {
            
            hitBox.y = hitBox.y + vspd;
            vspd+=gravity_saut;
            
        }

        if (Colision.peutBouger((float) hitBox.x+speedX, (float) hitBox.y, 
        hitBox.width, hitBox.height, Jeu.unMonde.blocos)){
            hitBox.x += speedX;
        }

        

        Camera.camera_x = (int) this.getX()-1040/2;
        Camera.camera_y = (int) this.getY()-728/2;
        // actualisation des coordonée des joueurs
        try {
            java.sql.Connection connexion =  DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2022-2023_s1_vs1_tp1_blowup", "user_blowup", "RiFSA*oR!f*F3sPc");
            PreparedStatement requete = connexion.prepareStatement("UPDATE Joueur SET latitudeX=?, longitudeY=? WHERE pseudo=?");
            requete.setDouble(1,this.getX());
            requete.setDouble(2,this.getY());
            requete.setString(3,this.name);
            requete.executeUpdate();
            requete.close();
            connexion.close();
          
        } catch (SQLException ex){
            ex.printStackTrace();
            
        }
        

    }


    public void rendu(Graphics2D contexte) {
            contexte.drawImage(this.sprite, (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - Camera.camera_y), null);
            this.renduVie(contexte);
            contexte.drawString(this.name,(int) this.getX()+20,(int)(this.getY() - (Camera.camera_y)-30));
            drawHitbox(contexte);

        
    }
    
    public void renduVie(Graphics2D contexte){
        if(this.nbLife == 3){
            contexte.drawImage(this.coeur, (int) this.getX(), (int) (this.getY() - (Camera.camera_y)-25), null);
            contexte.drawImage(this.coeur, (int) this.getX()+28, (int) (this.getY() - (Camera.camera_y)-25), null);
            contexte.drawImage(this.coeur, (int) this.getX()+56, (int) (this.getY() - (Camera.camera_y)-25), null);
        }
        if(this.nbLife == 2){
            contexte.drawImage(this.coeur, (int) this.getX(), (int) (this.getY() - (Camera.camera_y)-25), null);
            contexte.drawImage(this.coeur, (int) this.getX()+28, (int) (this.getY() - (Camera.camera_y)-25), null);
        }
        if(this.nbLife == 1){
            contexte.drawImage(this.coeur, (int) this.getX(), (int) (this.getY() - (Camera.camera_y)-25), null);
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
        hitBox.y = (float) y;
    }
    
    public void setX(double x) {
        hitBox.x = (float) x;
    }
    
    public void setNbSaut(int saut) {
    	this.nbSaut = saut;
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
    public boolean estMortDefinitivement() {
        return nbLife <= 0;
    }

    public void perdreUneVie() {
        this.nbLife = this.nbLife - 1;
    }

    public void respawn() {
        this.perdreUneVie();
        this.setX(540);
        this.setY(Camera.camera_y - 100);
        vspd = 0;
        //gravity_saut = (float) 0;
    }

  public void setnbLife(int a) {
        this.nbLife = a;
    }

    public String getName() {
        return name;
    }
    

}
