public abstract class Vehicule {
    protected String marque;
    protected String modele;
    protected int annee;
    
    public Vehicule(String marque, String modele, int annee) {
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
    }
    
    public abstract void decrire();
    
    public String getType() {
        return "Vehicule";
    }
    
    // Getters / setters eventuels
    public String getMarque() {
        return marque;
    }
    
    public String getModele() {
        return modele;
    }
    
    public int getAnnee() {
        return annee;
    }
}
