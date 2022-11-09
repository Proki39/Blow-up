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
 * @author roritaru
 */
public class BlockSheet {
    
    public static BufferedImage tilesheet;
    public static BufferedImage rocher, meteore, brique, bois;
    protected static int width = 128, height = 128;
    
    
    public BlockSheet(){
        try {
            tilesheet = ImageIO.read(getClass().getResource("../resources/BlockTileSheet.png"));
        } catch (IOException ex) {
            Logger.getLogger(BlockSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        rocher = BlockSheet.getSprite(3*width, 3*height, width, height);
        meteore = BlockSheet.getSprite(0*width, 0*height, width, height);
        brique = BlockSheet.getSprite(4*width, 1*height, width, height);
        bois = BlockSheet.getSprite(1*width, 4*height, width, height);
    }
    
    
    
    public static BufferedImage getSprite(int x, int y, int width, int height){
        return tilesheet.getSubimage(x, y, width, height);
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
}
