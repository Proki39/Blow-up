/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author roritaruuu
 */
public class BlockSheet {
    
    public static BufferedImage tilesheet;
    public static BufferedImage rocher, meteore, brique, bois;
    
    
    public BlockSheet(){
        try {
            tilesheet = ImageIO.read(getClass().getResource("../resources/BlockTileSheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(BlockSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rocher = BlockSheet.getSprite(192, 192, 64, 64);
        meteore = BlockSheet.getSprite(0, 0, 64, 64);
        brique = BlockSheet.getSprite(256, 64, 64, 64);
        bois = BlockSheet.getSprite(64, 256, 64, 64);
    }
    
    
    
    public static BufferedImage getSprite(int x, int y, int width, int height){
        return tilesheet.getSubimage(x, y, width, height);
    }
    
}
