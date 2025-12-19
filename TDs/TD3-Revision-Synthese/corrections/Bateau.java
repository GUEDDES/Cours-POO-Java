public class Bateau extends Vehicule implements Flottable {
    
    public Bateau(String marque, String modele, int annee) {
        super(marque, modele, annee);
    }
    
    @Override
    public void decrire() {
        System.out.println("C'est un bateau " + marque + " " +
            modele + " de " + annee + ".");
    }
    
    @Override
    public String getType() {
        return "Bateau";
    }
    
    @Override
    public void naviguer() {
        System.out.println("Le bateau " + marque + " " + modele + " navigue.");
    }
}
