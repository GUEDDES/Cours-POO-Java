public class Voiture extends Vehicule implements Conduisible {
    
    public Voiture(String marque, String modele, int annee) {
        super(marque, modele, annee);
    }
    
    @Override
    public void decrire() {
        System.out.println("C'est une voiture " + marque + " " +
            modele + " de " + annee + ".");
    }
    
    @Override
    public String getType() {
        return "Voiture";
    }
    
    @Override
    public void demarrer() {
        System.out.println("La voiture " + marque + " " + modele + " demarre.");
    }
}
