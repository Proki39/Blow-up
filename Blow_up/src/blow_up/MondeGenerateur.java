/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blow_up;

import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author roritaru
 */
public class MondeGenerateur {
    
    public static List<Block> blocos =  new ArrayList<Block>();
    
    private double SpawnRate = 2;

    public static List<Block> getBlocos() {
        return blocos;
    }
    
    private double Timer = 0;
    
    
    public MondeGenerateur() {
        

        
    }
    
    public void miseAJour() {
        Timer = Timer+0.04;
        
        //Timer pour generer les blocs
        if (Timer>SpawnRate){
            //Creation de Bloques
            blocos.add(new Block());
        
            Timer = 0.;
        }
        
        //Mise a Jour des Bloques
        for(int i=0; i<blocos.size(); i++){
                blocos.get(i).miseAJour();
            }
        
    }
    
    public void rendu(Graphics2D contexte) {
        
        //Rendu des Bloques
        for(int i=0; i<blocos.size(); i++){
            blocos.get(i).rendu(contexte);
        }
        
        
    }
    
    public double blocPlusHaut(){
        double maxY = 99999;
        
        //Optimization
        int j=0;
        
        if (this.blocos.size()>50){
            j = this.blocos.size()-50;  //S'il y a plus de 50 blocs, verifier les 50 derniers
        }
        
        for(int i=j; i<this.blocos.size(); i++){
            Block b = MondeGenerateur.blocos.get(i);
            
            if (b.getYfixe()<maxY){
                maxY = b.getYfixe();
            }
        }
        
        return maxY;
    }
    
}
