/**
 * Exemple : Gestion de Comptes Bancaires
 * Démontre : Constructeurs, this, surcharge, encapsulation basique
 */

class CompteBancaire {
    // Attributs
    private String numeroCompte;
    private String titulaire;
    private double solde;
    private static int compteurComptes = 0;  // Compteur de comptes créés
    
    // Constructeur complet
    public CompteBancaire(String numeroCompte, String titulaire, double soldeInitial) {
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
        compteurComptes++;
        System.out.println("✅ Compte créé : " + numeroCompte);
    }
    
    // Constructeur avec solde initial à 0
    public CompteBancaire(String numeroCompte, String titulaire) {
        this(numeroCompte, titulaire, 0.0);  // Appel du constructeur complet
    }
    
    // Méthode : Dépôt
    public void deposer(double montant) {
        if (montant <= 0) {
            System.out.println("❌ Montant invalide");
            return;
        }
        solde += montant;
        System.out.println("💵 Dépôt de " + montant + "€. Nouveau solde : " + solde + "€");
    }
    
    // Méthode : Retrait
    public boolean retirer(double montant) {
        if (montant <= 0) {
            System.out.println("❌ Montant invalide");
            return false;
        }
        
        if (montant > solde) {
            System.out.println("❌ Solde insuffisant. Disponible : " + solde + "€");
            return false;
        }
        
        solde -= montant;
        System.out.println("💸 Retrait de " + montant + "€. Nouveau solde : " + solde + "€");
        return true;
    }
    
    // Méthode : Virement vers un autre compte
    public boolean virer(double montant, CompteBancaire destinataire) {
        if (this.retirer(montant)) {
            destinataire.deposer(montant);
            System.out.println("🔄 Virement de " + montant + "€ vers " + destinataire.numeroCompte);
            return true;
        }
        return false;
    }
    
    // Méthode : Afficher les informations
    public void afficher() {
        System.out.println("\n========== COMPTE BANCAIRE ==========");
        System.out.println("Numéro  : " + numeroCompte);
        System.out.println("Titulaire : " + titulaire);
        System.out.println("Solde     : " + solde + "€");
        System.out.println("====================================\n");
    }
    
    // Getters
    public String getNumeroCompte() {
        return numeroCompte;
    }
    
    public double getSolde() {
        return solde;
    }
    
    // Méthode statique
    public static int getNombreComptes() {
        return compteurComptes;
    }
}

// Classe de test
public class TestCompteBancaire {
    public static void main(String[] args) {
        System.out.println("\n🏛️ === SYSTÈME BANCAIRE ===\n");
        
        // Création de comptes
        CompteBancaire compte1 = new CompteBancaire("FR001", "Alice Dupont", 1000);
        CompteBancaire compte2 = new CompteBancaire("FR002", "Bob Martin");
        
        System.out.println("\n📊 Nombre de comptes créés : " + CompteBancaire.getNombreComptes());
        
        // Opérations sur compte1
        System.out.println("\n--- Opérations sur Compte 1 ---");
        compte1.afficher();
        compte1.deposer(500);
        compte1.retirer(300);
        compte1.retirer(2000);  // Échec : solde insuffisant
        
        // Opérations sur compte2
        System.out.println("\n--- Opérations sur Compte 2 ---");
        compte2.afficher();
        compte2.deposer(2000);
        
        // Virement
        System.out.println("\n--- Virement ---");
        compte1.virer(200, compte2);
        
        // État final
        System.out.println("\n--- ÉTAT FINAL ---");
        compte1.afficher();
        compte2.afficher();
    }
}
