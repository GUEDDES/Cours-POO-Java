public class GestionTransport {
    private Vehicule[] vehicules;
    private int nbVehicules;
    
    public GestionTransport(int capacite) {
        vehicules = new Vehicule[capacite];
        nbVehicules = 0;
    }
    
    public void ajouterVehicule(Vehicule vehicule) {
        if (nbVehicules < vehicules.length) {
            vehicules[nbVehicules] = vehicule;
            nbVehicules++;
        } else {
            System.out.println("Capacite maximale atteinte.");
        }
    }
    
    public void decrireTousLesVehicules() {
        for (int i = 0; i < nbVehicules; i++) {
            vehicules[i].decrire();
            System.out.println("Type : " + vehicules[i].getType());
            System.out.println();
        }
    }
}
