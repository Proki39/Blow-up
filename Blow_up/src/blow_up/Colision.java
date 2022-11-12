package blow_up;

import java.util.ArrayList;
import java.util.List;

public class Colision {
    
    public Colision(){
        
    }
    
    //la methode Colision1 interdit le joueur de se deplacer dans le bloc
    public boolean Colision1(int speedX, int speedY, List<Block> blocos, Joueur unJoueur){
        for(int i=0; i<blocos.size(); i++){
        if(blocos.get(i).getGenUp() &&                                            //Colision avec le carré superieur
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=blocos.get(i).getY() - blocos.get(i).getHauteur() &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX-45>=blocos.get(i).getX() &&
        unJoueur.getX()+speedX<=blocos.get(i).getX()+blocos.get(i).getHauteur() &&
        unJoueur.getY()+speedY+10<=blocos.get(i).getY()+blocos.get(i).getLargeur()){
            return true;}

        if(blocos.get(i).getGenDown() &&                                            //Colision avec le carré inferieur
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=blocos.get(i).getY() &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX-45>=blocos.get(i).getX() &&
        unJoueur.getX()+speedX<=blocos.get(i).getX()+blocos.get(i).getHauteur() &&
        unJoueur.getY()+speedY+10<=blocos.get(i).getY()+blocos.get(i).getLargeur() + blocos.get(i).getHauteur()){
            return true;}

        if(blocos.get(i).getGenRight() &&                                            //Colision avec le carré droite
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=blocos.get(i).getY() &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX-45>=blocos.get(i).getX() &&
        unJoueur.getX()+speedX<=blocos.get(i).getX()+blocos.get(i).getHauteur() + blocos.get(i).getLargeur() &&
        unJoueur.getY()+speedY+10<=blocos.get(i).getY()+blocos.get(i).getLargeur()){
            return true;}

        if(blocos.get(i).getGenLeft() &&                                            //Colision avec le carré gauche
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=blocos.get(i).getY() &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX-45>=blocos.get(i).getX() - blocos.get(i).getLargeur() &&
        unJoueur.getX()+speedX<=blocos.get(i).getX()+blocos.get(i).getHauteur() &&
        unJoueur.getY()+speedY+10<=blocos.get(i).getY()+blocos.get(i).getLargeur()){
            return true;}
    }
            return false;
    }

    //la methode estMort verifie si le block a ecrasé le joueur
    public boolean estMort(List<Block> blocos, Joueur unJoueur){
        for(int i=0; i<blocos.size(); i++){
        if(blocos.get(i).getGenDown() &&                                            //Colision avec le carré inferieur
        unJoueur.getY()+unJoueur.getLargeur()>blocos.get(i).getY() &&
        unJoueur.getX()+unJoueur.getHauteur()-45>blocos.get(i).getX() &&
        unJoueur.getX()<blocos.get(i).getX()+blocos.get(i).getHauteur() &&
        unJoueur.getY()+10<blocos.get(i).getY()+blocos.get(i).getLargeur() + 128){
            return true;}
        
        if(unJoueur.getY()+unJoueur.getLargeur()>blocos.get(i).getY() &&          //Colision avec le centre du carré
        unJoueur.getX()+unJoueur.getHauteur()-45>blocos.get(i).getX() &&
        unJoueur.getX()<blocos.get(i).getX()+blocos.get(i).getHauteur() &&
        unJoueur.getY()+10<blocos.get(i).getY()+blocos.get(i).getLargeur()){
            return true;}
        
        if(blocos.get(i).getGenLeft() &&                                            //Colision avec le carré gauche
        unJoueur.getY()+unJoueur.getLargeur()>blocos.get(i).getY() &&
        unJoueur.getX()+unJoueur.getHauteur()-45>blocos.get(i).getX() - blocos.get(i).getLargeur() &&
        unJoueur.getX()<blocos.get(i).getX()+blocos.get(i).getHauteur() &&
        unJoueur.getY()+10<blocos.get(i).getY()+blocos.get(i).getLargeur()){
            return true;}
        
        if(blocos.get(i).getGenRight() &&                                            //Colision avec le carré droite
        unJoueur.getY()+unJoueur.getLargeur()>blocos.get(i).getY() &&
        unJoueur.getX()+unJoueur.getHauteur()-45>blocos.get(i).getX() &&
        unJoueur.getX()<blocos.get(i).getX()+blocos.get(i).getHauteur() + blocos.get(i).getLargeur() &&
        unJoueur.getY()+10<blocos.get(i).getY()+blocos.get(i).getLargeur()){
            return true;}}
        
        return false;
    }

}
