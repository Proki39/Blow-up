package blow_up;

import java.security.KeyStore.TrustedCertificateEntry;
import java.util.ArrayList;
import java.util.List;

public class Colision {
    
    public static boolean peutBouger(float x, float y, float width, float height, List<Block> blocos){
        if(!estSolide(x, y, blocos))
            if(!estSolide(x+width, y+height, blocos))
                if(!estSolide(x+width, y, blocos))
                    if(!estSolide(x, y+height, blocos))
                        return true;
        return false;

    }

    public static boolean estSolide(float x, float y, List<Block> blocos){
        if(x < 0 || x >= 1040)
            return true;
        if(y >= 10400)
            return true;
        for(int i=0; i<blocos.size(); i++){
            if(blocos.get(i).getGenUp() &&                                            //Colision avec le carré superieur
            y >= blocos.get(i).getY() - blocos.get(i).getHauteur() &&
            x >= blocos.get(i).getX() &&
            x <= blocos.get(i).getX()+blocos.get(i).getLargeur() &&
            y <= blocos.get(i).getY()){
                return true;}
        
            if(blocos.get(i).getGenDown() &&                                            //Colision avec le carré inferieur
            y >= blocos.get(i).getY() + blocos.get(i).getHauteur() &&
            x >= blocos.get(i).getX() &&
            x <= blocos.get(i).getX()+blocos.get(i).getLargeur() &&
            y <= blocos.get(i).getY() + 2*blocos.get(i).getHauteur()){
                return true;}
        
            if(blocos.get(i).getGenRight() &&                                            //Colision avec le carré droite
            y>=blocos.get(i).getY() &&
            x>=blocos.get(i).getX()+blocos.get(i).getLargeur() &&
            x<=blocos.get(i).getX()+2*blocos.get(i).getLargeur() &&
            y<=blocos.get(i).getY()+blocos.get(i).getHauteur()){
                return true;}
        
            if(blocos.get(i).getGenLeft() &&                                            //Colision avec le carré gauche
            y>=blocos.get(i).getY() &&
            x>=blocos.get(i).getX() - blocos.get(i).getLargeur() &&
            x<=blocos.get(i).getX() &&
            y<=blocos.get(i).getY() + blocos.get(i).getHauteur()){
                return true;}

            if(y>=blocos.get(i).getY() &&                                               //Colision avec le care centale
            x>=blocos.get(i).getX() &&
            x<=blocos.get(i).getX()+blocos.get(i).getLargeur() &&
            y<=blocos.get(i).getY()+blocos.get(i).getHauteur()){
                return true;}
            }
        return false;
    }
    

    //la methode estMort verifie si le block a ecrasé le joueur
    public boolean estMort(float x, float y, List<Block> blocos){
        for(int i=0; i<blocos.size(); i++){
            if(y>blocos.get(i).getY() &&                                               //Colision avec le care centale
            x>blocos.get(i).getX() &&
            x<blocos.get(i).getX()+blocos.get(i).getLargeur() &&
            y<blocos.get(i).getY()+blocos.get(i).getHauteur()){
                return true;}
        
            if(blocos.get(i).getGenDown() &&                                            //Colision avec le carré inferieur
            y > blocos.get(i).getY() + blocos.get(i).getHauteur() &&
            x > blocos.get(i).getX() &&
            x < blocos.get(i).getX()+blocos.get(i).getLargeur() &&
            y < blocos.get(i).getY() + 2*blocos.get(i).getHauteur()){
                return true;}
        
            if(blocos.get(i).getGenRight() &&                                            //Colision avec le carré droite
            y>blocos.get(i).getY() &&
            x>blocos.get(i).getX()+blocos.get(i).getLargeur() &&
            x<blocos.get(i).getX()+2*blocos.get(i).getLargeur() &&
            y<blocos.get(i).getY()+blocos.get(i).getHauteur()){
                return true;}
        
            if(blocos.get(i).getGenLeft() &&                                            //Colision avec le carré gauche
            y>blocos.get(i).getY() &&
            x>blocos.get(i).getX() - blocos.get(i).getLargeur() &&
            x<blocos.get(i).getX() &&
            y<blocos.get(i).getY() + blocos.get(i).getHauteur()){
                    return true;}}
        
        return false;
    }

}
