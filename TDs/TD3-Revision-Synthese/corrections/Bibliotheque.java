public class Bibliotheque {
    
    private static int nombreLivres = 0;
    
    public void ajouterLivre() {
        nombreLivres++;
    }
    
    public static void retirerLivre() {
        if (nombreLivres > 0) {
            nombreLivres--;
        }
    }
    
    public final int getNombreLivres() {
        return nombreLivres;
    }
    
    public static void main(String[] args) {
        Bibliotheque biblio1 = new Bibliotheque();
        Bibliotheque biblio2 = new Bibliotheque();
        
        biblio1.ajouterLivre();
        System.out.println("Nombre de livres (biblio1) : " + biblio1.getNombreLivres());
        System.out.println("Nombre de livres (biblio2) : " + biblio2.getNombreLivres());
        
        biblio2.ajouterLivre();
        System.out.println("\nApres ajout via biblio2 :");
        System.out.println("Nombre de livres (biblio1) : " + biblio1.getNombreLivres());
        System.out.println("Nombre de livres (biblio2) : " + biblio2.getNombreLivres());
        
        Bibliotheque.retirerLivre();
        System.out.println("\nApres retrait statique :");
        System.out.println("Nombre de livres : " + biblio1.getNombreLivres());
        
        System.out.println("\n==> L'attribut static est partage par toutes les instances !");
    }
}
