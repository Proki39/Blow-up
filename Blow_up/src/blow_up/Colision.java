package blow_up;

public class Colision {
    
    public Colision(){
        
    }
    
    public boolean Colision1(int speedX, int speedY, Block unBlock, Joueur unJoueur){
        if(unBlock.getGenUp() &&                                            //Colision avec le block superieur
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=unBlock.getY() - 64 &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX>=unBlock.getX() &&
        unJoueur.getX()+speedX<=unBlock.getX()+unBlock.getHauteur() &&
        unJoueur.getY()+speedY<=unBlock.getY()+unBlock.getLargeur()){
            return true;}

        if(unBlock.getGenDown() &&                                            //Colision avec le block inferieur
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=unBlock.getY() &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX>=unBlock.getX() &&
        unJoueur.getX()+speedX<=unBlock.getX()+unBlock.getHauteur() &&
        unJoueur.getY()+speedY<=unBlock.getY()+unBlock.getLargeur() + 64){
            return true;}

        if(unBlock.getGenRight() &&                                            //Colision avec le block droite
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=unBlock.getY() &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX>=unBlock.getX() &&
        unJoueur.getX()+speedX<=unBlock.getX()+unBlock.getHauteur() + 64 &&
        unJoueur.getY()+speedY<=unBlock.getY()+unBlock.getLargeur()){
            return true;}

        if(unBlock.getGenLeft() &&                                            //Colision avec le block gauche
        unJoueur.getY()+unJoueur.getLargeur()+speedY>=unBlock.getY() &&
        unJoueur.getX()+unJoueur.getHauteur()+speedX>=unBlock.getX() - 64 &&
        unJoueur.getX()+speedX<=unBlock.getX()+unBlock.getHauteur() &&
        unJoueur.getY()+speedY<=unBlock.getY()+unBlock.getLargeur()){
            return true;}

        if(unJoueur.getY()+unJoueur.getLargeur()+speedY>=unBlock.getY() &&     //Colision avec le centre du bloc
        unJoueur.getX()+unJoueur.getHauteur()+speedX>=unBlock.getX() &&
        unJoueur.getX()+speedX<=unBlock.getX()+unBlock.getHauteur() &&
        unJoueur.getY()+speedY<=unBlock.getY()+unBlock.getLargeur()){
            return true;
        }else{
            return false;}
    }
}
