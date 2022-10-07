/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.imageio.ImageIO;
import java.io.File;


/**
 *
 * @author lbrunel
 */
public class Carte {
    private int largeur = 12;
    private int hauteur = 9;
    private int tailleTuile = 32;
    
    private BufferedImage uneTuile ;
    
    public Carte () {
        try {
            BufferedImage tileset = ImageIO . read ( getClass () . getResource (" images /image jeu . png "));
            uneTuile = tileset . getSubimage (0 , 0, tailleTuile , tailleTuile );
        } catch ( IOException ex ) {
            Logger.getLogger ( Carte.class.getName () ).log (Level.SEVERE,null, ex );
        }
    }
    public void miseAJour () {
        }

    public void rendu ( Graphics2D contexte ) {
        contexte . drawImage ( uneTuile , 0, 0, null );
    }
}
