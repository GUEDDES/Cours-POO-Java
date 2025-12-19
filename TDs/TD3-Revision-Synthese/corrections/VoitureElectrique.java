public class VoitureElectrique extends Voiture {
    
    private double capaciteBatterie; // en kWh
    
    public VoitureElectrique(String marque, String modele, int annee,
                             double capaciteBatterie) {
        super(marque, modele, annee);
        this.capaciteBatterie = capaciteBatterie;
    }
    
    public double calculerAutonomie(double consommation) {
        // consommation en kWh/km
        if (consommation <= 0) {
            throw new IllegalArgumentException(
                "La consommation doit etre positive."
            );
        }
        return capaciteBatterie / consommation;
    }
    
    @Override
    public void decrire() {
        System.out.println("C'est une voiture electrique " + marque + " " +
            modele + " de " + annee +
            " avec une batterie de " + capaciteBatterie + " kWh.");
    }
    
    @Override
    public String getType() {
        return "VoitureElectrique";
    }
}
