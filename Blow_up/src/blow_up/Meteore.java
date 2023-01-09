package blow_up;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Meteore {
    protected BufferedImage sprite1, sprite2;
    protected double x1, y1, x2, y2;

    public Meteore(){
        this.x1 = 0;
        this.y1 = 0;

        this.x2 = 0;
        this.y2 = 10322;

        try {
        	this.sprite1 = ImageIO.read(getClass().getClassLoader().getResource("resources/Meteore.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
        	this.sprite2 = ImageIO.read(getClass().getClassLoader().getResource("resources/Floor.png"));            
        	} catch (IOException ex) {
            Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite1, (int) x1, (int) y1 - Camera.camera_y, null);
        contexte.drawImage(this.sprite2, (int) x2, (int) y2 - Camera.camera_y, null);
    }

}
