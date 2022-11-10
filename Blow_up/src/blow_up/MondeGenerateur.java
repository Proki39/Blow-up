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
    
    public List<Block> blocos =  new ArrayList<Block>();
    
    private double SpawnRate = 4;
    
    private double Timer = 0;
    
    
    public MondeGenerateur() {
        
        blocos.add(new Block());

        
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
            blocos.get(i).rendu(contexte, 93);
        }
        
        
    }
    
}
