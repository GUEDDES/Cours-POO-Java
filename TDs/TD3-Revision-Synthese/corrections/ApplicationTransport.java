public class ApplicationTransport {
    
    public static void main(String[] args) {
        GestionTransport gt = new GestionTransport(10);
        
        Voiture v1 = new Voiture("Toyota", "Corolla", 2020);
        Bateau b1 = new Bateau("Beneteau", "Oceanis", 2018);
        VoitureElectrique ve1 = new VoitureElectrique("Tesla", "Model 3", 2023, 75.0);
        
        gt.ajouterVehicule(v1);
        gt.ajouterVehicule(b1);
        gt.ajouterVehicule(ve1);
        
        System.out.println("Descriptions des vehicules :");
        gt.decrireTousLesVehicules();
        
        // Appels specifiques
        System.out.println("\n--- Appels specifiques ---");
        v1.demarrer();
        b1.naviguer();
        ve1.demarrer();
        
        // Calcul autonomie voiture electrique
        double autonomie = ve1.calculerAutonomie(0.15); // 0.15 kWh/km
        System.out.println("\nAutonomie de la Tesla Model 3 : " + 
            String.format("%.2f", autonomie) + " km");
    }
}
