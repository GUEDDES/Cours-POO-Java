/**
 * Exemple 2 : Compte Bancaire
 * 
 * Objectif : Illustrer l'encapsulation et la validation dans les méthodes
 * Concepts :
 * - Attributs privés
 * - Validation des données
 * - Getters (pas de setters pour la sécurité)
 * - Méthodes métier
 * 
 * @author Cours POO Java
 */
public class CompteBancaire {
    // Attributs privés
    private String numeroCompte;
    private String titulaire;
    private double solde;
    private static int compteurComptes = 0;
    
    // Constructeur
    public CompteBancaire(String titulaire, double soldeInitial) {
        if (soldeInitial < 0) {
            throw new IllegalArgumentException("Le solde initial ne peut pas être négatif");
        }
        
        compteurComptes++;
        this.numeroCompte = "CPT" + String.format("%06d", compteurComptes);
        this.titulaire = titulaire;
        this.solde = soldeInitial;
        
        System.out.println("✅ Compte créé : " + numeroCompte + " pour " + titulaire);
    }
    
    // Getters (pas de setters pour la sécurité)
    public String getNumeroCompte() {
        return numeroCompte;
    }
    
    public String getTitulaire() {
        return titulaire;
    }
    
    public double getSolde() {
        return solde;
    }
    
    // Méthode pour déposer de l'argent
    public void deposer(double montant) {
        if (montant <= 0) {
            System.out.println("❌ Le montant doit être positif");
            return;
        }
        
        solde += montant;
        System.out.printf("✅ Dépôt de %.2f€ effectué. Nouveau solde : %.2f€%n", montant, solde);
    }
    
    // Méthode pour retirer de l'argent
    public boolean retirer(double montant) {
        if (montant <= 0) {
            System.out.println("❌ Le montant doit être positif");
            return false;
        }
        
        if (montant > solde) {
            System.out.printf("❌ Solde insuffisant. Disponible : %.2f€, Demandé : %.2f€%n", solde, montant);
            return false;
        }
        
        solde -= montant;
        System.out.printf("✅ Retrait de %.2f€ effectué. Nouveau solde : %.2f€%n", montant, solde);
        return true;
    }
    
    // Méthode pour effectuer un virement
    public boolean virement(CompteBancaire destinataire, double montant) {
        if (destinataire == null) {
            System.out.println("❌ Destinataire invalide");
            return false;
        }
        
        System.out.println("\n💸 Virement de " + montant + "€ vers " + destinataire.getTitulaire());
        
        if (this.retirer(montant)) {
            destinataire.deposer(montant);
            System.out.println("✅ Virement réussi");
            return true;
        }
        
        System.out.println("❌ Virement échoué");
        return false;
    }
    
    // Méthode pour afficher le relevé
    public void afficherReleve() {
        System.out.println("\n========== RELEVÉ DE COMPTE ==========");
        System.out.println("Numéro  : " + numeroCompte);
        System.out.println("Titulaire : " + titulaire);
        System.out.printf("Solde   : %.2f€%n", solde);
        System.out.println("======================================\n");
    }
    
    // Méthode statique pour obtenir le nombre de comptes
    public static int getNombreComptes() {
        return compteurComptes;
    }
    
    // Programme de test
    public static void main(String[] args) {
        System.out.println("=== TEST COMPTE BANCAIRE ===");
        
        // Créer des comptes
        CompteBancaire compte1 = new CompteBancaire("Alice Dupont", 1000.0);
        CompteBancaire compte2 = new CompteBancaire("Bob Martin", 500.0);
        
        System.out.println("\nNombre total de comptes : " + CompteBancaire.getNombreComptes());
        
        // Afficher les relevés initiaux
        compte1.afficherReleve();
        compte2.afficherReleve();
        
        // Opérations bancaires
        System.out.println("--- Opérations ---");
        compte1.deposer(200);
        compte1.retirer(150);
        compte1.retirer(2000);  // Tentative de retrait supérieur au solde
        
        // Virement
        compte1.virement(compte2, 300);
        
        // Afficher les relevés finaux
        compte1.afficherReleve();
        compte2.afficherReleve();
    }
}
