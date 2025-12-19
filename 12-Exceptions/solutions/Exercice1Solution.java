/**
 * Solution Exercice 1 : Gestion de Comptes Bancaires avec Exceptions
 * 
 * Exceptions : vérifiées (checked) et non vérifiées (unchecked)
 * 
 * @author Cours POO Java
 */
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Exercice1Solution {
    public static void main(String[] args) {
        System.out.println("=== SYSTÈME BANCAIRE AVEC EXCEPTIONS ===");
        
        try {
            // Création de comptes
            System.out.println("\n--- Création des comptes ---");
            CompteBancaire compte1 = new CompteBancaire("FR001", "Alice Dupont", 1000);
            CompteBancaire compte2 = new CompteBancaire("FR002", "Bob Martin", 500);
            
            compte1.afficherReleve();
            compte2.afficherReleve();
            
            // Test 1 : Dépôt
            System.out.println("\n--- Test 1 : Dépôt ---");
            compte1.deposer(200);
            
            // Test 2 : Retrait normal
            System.out.println("\n--- Test 2 : Retrait normal ---");
            compte1.retirer(300);
            
            // Test 3 : Retrait avec découvert
            System.out.println("\n--- Test 3 : Retrait avec découvert ---");
            compte2.retirer(700);  // Découvert de 200€
            
            // Test 4 : Retrait impossible (découvert dépassé)
            System.out.println("\n--- Test 4 : Retrait impossible ---");
            try {
                compte2.retirer(500);  // Dépasserait le découvert
            } catch (SoldeInsuffisantException e) {
                System.out.println("❌ Exception : " + e.getMessage());
                System.out.printf("   Solde actuel : %.2f€%n", e.getSoldeActuel());
                System.out.printf("   Montant demandé : %.2f€%n", e.getMontantDemande());
                System.out.printf("   Manquant : %.2f€%n", e.getManquant());
            }
            
            // Test 5 : Blocage de compte
            System.out.println("\n--- Test 5 : Blocage de compte ---");
            compte1.bloquer("Activité suspecte détectée");
            try {
                compte1.retirer(100);
            } catch (CompteBloqueException e) {
                System.out.println("❌ Exception : " + e.getMessage());
                System.out.println("   Raison : " + e.getRaison());
            }
            
            // Déblocage
            compte1.debloquer();
            
            // Test 6 : Virement
            System.out.println("\n--- Test 6 : Virement ---");
            compte1.virement(compte2, 400);
            
            compte1.afficherReleve();
            compte2.afficherReleve();
            
            // Test 7 : Montant invalide
            System.out.println("\n--- Test 7 : Montant invalide ---");
            try {
                compte1.deposer(-50);
            } catch (MontantInvalideException e) {
                System.out.println("❌ Exception non vérifiée : " + e.getMessage());
            }
            
            try {
                compte1.deposer(0);
            } catch (MontantInvalideException e) {
                System.out.println("❌ Exception non vérifiée : " + e.getMessage());
            }
            
            // Test 8 : Limite débit journalier
            System.out.println("\n--- Test 8 : Limite de débit ---");
            try {
                compte1.retirer(1500);  // Dépasse la limite journalière
            } catch (LimiteDebitException e) {
                System.out.println("❌ Exception : " + e.getMessage());
                System.out.printf("   Limite : %.2f€%n", e.getLimiteJournaliere());
                System.out.printf("   Déjà retiré : %.2f€%n", e.getMontantDejaRetire());
                System.out.printf("   Disponible : %.2f€%n", e.getMontantDisponible());
            } catch (SoldeInsuffisantException e) {
                System.out.println("❌ Solde insuffisant");
            }
            
            // Test 9 : Historique
            System.out.println("\n--- Test 9 : Historique ---");
            compte1.afficherHistorique();
            
            // Test 10 : Sauvegarde dans fichier
            System.out.println("\n--- Test 10 : Sauvegarde fichier ---");
            try {
                compte1.sauvegarderHistorique("historique_" + compte1.getNumeroCompte() + ".txt");
            } catch (IOException e) {
                System.out.println("❌ Erreur de sauvegarde : " + e.getMessage());
            }
            
            // Test 11 : Tentative de création avec solde négatif
            System.out.println("\n--- Test 11 : Création invalide ---");
            try {
                CompteBancaire compteInvalide = new CompteBancaire("FR999", "Test", -100);
            } catch (MontantInvalideException e) {
                System.out.println("❌ Exception : " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("\n❌ ERREUR CRITIQUE NON GÉRÉE : " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n🏁 Programme terminé (bloc finally toujours exécuté)");
            System.out.println("Merci d'avoir utilisé notre service bancaire.");
        }
    }
}

// === EXCEPTIONS PERSONNALISÉES ===

// Exception vérifiée (checked)
class SoldeInsuffisantException extends Exception {
    private double soldeActuel;
    private double montantDemande;
    
    public SoldeInsuffisantException(double soldeActuel, double montantDemande) {
        super(String.format("Solde insuffisant : %.2f€ disponible, %.2f€ demandé", 
                           soldeActuel, montantDemande));
        this.soldeActuel = soldeActuel;
        this.montantDemande = montantDemande;
    }
    
    public double getSoldeActuel() { return soldeActuel; }
    public double getMontantDemande() { return montantDemande; }
    public double getManquant() { return montantDemande - soldeActuel; }
}

class CompteBloqueException extends Exception {
    private String raison;
    
    public CompteBloqueException(String raison) {
        super("Compte bloqué : " + raison);
        this.raison = raison;
    }
    
    public String getRaison() { return raison; }
}

class LimiteDebitException extends Exception {
    private double limiteJournaliere;
    private double montantDejaRetire;
    
    public LimiteDebitException(double limiteJournaliere, double montantDejaRetire) {
        super(String.format("Limite de débit dépassée : %.2f€/jour (%.2f€ déjà retiré)",
                           limiteJournaliere, montantDejaRetire));
        this.limiteJournaliere = limiteJournaliere;
        this.montantDejaRetire = montantDejaRetire;
    }
    
    public double getLimiteJournaliere() { return limiteJournaliere; }
    public double getMontantDejaRetire() { return montantDejaRetire; }
    public double getMontantDisponible() { return limiteJournaliere - montantDejaRetire; }
}

// Exception non vérifiée (unchecked)
class MontantInvalideException extends RuntimeException {
    public MontantInvalideException(String message) {
        super(message);
    }
}

// === CLASSE COMPTEBANCAIRE ===

class CompteBancaire {
    private String numeroCompte;
    private String titulaire;
    private double solde;
    private boolean bloque;
    private String raisonBlocage;
    private ArrayList<String> historique;
    private double montantRetireAujourdhui;
    
    private static final double DECOUVERT_AUTORISE = 500.0;
    private static final double LIMITE_DEBIT_JOURNALIER = 1000.0;
    
    public CompteBancaire(String numeroCompte, String titulaire, double soldeInitial) {
        if (soldeInitial < 0) {
            throw new MontantInvalideException("Le solde initial ne peut pas être négatif");
        }
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
        this.bloque = false;
        this.historique = new ArrayList<>();
        this.montantRetireAujourdhui = 0;
        
        ajouterHistorique("Compte créé avec solde initial : " + soldeInitial + "€");
        System.out.println("✅ Compte créé : " + numeroCompte + " (" + titulaire + ")");
    }
    
    private void ajouterHistorique(String operation) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        historique.add("[" + timestamp + "] " + operation);
    }
    
    public void deposer(double montant) throws CompteBloqueException {
        if (bloque) {
            throw new CompteBloqueException(raisonBlocage);
        }
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant doit être strictement positif");
        }
        
        solde += montant;
        ajouterHistorique(String.format("Dépôt : +%.2f€ (solde: %.2f€)", montant, solde));
        System.out.printf("✅ Dépôt de %.2f€ effectué - Nouveau solde : %.2f€%n", montant, solde);
    }
    
    public void retirer(double montant) 
            throws CompteBloqueException, SoldeInsuffisantException, LimiteDebitException {
        
        if (bloque) {
            throw new CompteBloqueException(raisonBlocage);
        }
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant doit être strictement positif");
        }
        
        // Vérifier la limite journalière
        if (montantRetireAujourdhui + montant > LIMITE_DEBIT_JOURNALIER) {
            throw new LimiteDebitException(LIMITE_DEBIT_JOURNALIER, montantRetireAujourdhui);
        }
        
        double soldeApresRetrait = solde - montant;
        if (soldeApresRetrait < -DECOUVERT_AUTORISE) {
            throw new SoldeInsuffisantException(solde + DECOUVERT_AUTORISE, montant);
        }
        
        solde = soldeApresRetrait;
        montantRetireAujourdhui += montant;
        ajouterHistorique(String.format("Retrait : -%.2f€ (solde: %.2f€)", montant, solde));
        System.out.printf("✅ Retrait de %.2f€ effectué - Nouveau solde : %.2f€%n", montant, solde);
        
        if (solde < 0) {
            System.out.printf("⚠️  Découvert : %.2f€%n", -solde);
        }
    }
    
    public void virement(CompteBancaire destinataire, double montant) 
            throws CompteBloqueException, SoldeInsuffisantException, LimiteDebitException {
        
        if (destinataire == null) {
            throw new IllegalArgumentException("Compte destinataire invalide");
        }
        
        System.out.println("\n💸 Virement en cours...");
        System.out.printf("De : %s vers %s%n", this.titulaire, destinataire.titulaire);
        System.out.printf("Montant : %.2f€%n", montant);
        
        try {
            this.retirer(montant);
            destinataire.deposer(montant);
            
            ajouterHistorique(String.format("Virement émis vers %s : -%.2f€", 
                destinataire.numeroCompte, montant));
            destinataire.ajouterHistorique(String.format("Virement reçu de %s : +%.2f€", 
                this.numeroCompte, montant));
            
            System.out.println("✅ Virement réussi");
        } catch (CompteBloqueException | SoldeInsuffisantException | LimiteDebitException e) {
            System.out.println("❌ Virement échoué : " + e.getMessage());
            throw e;
        }
    }
    
    public void bloquer(String raison) {
        this.bloque = true;
        this.raisonBlocage = raison;
        ajouterHistorique("Compte bloqué : " + raison);
        System.out.println("🔒 Compte bloqué : " + raison);
    }
    
    public void debloquer() {
        this.bloque = false;
        String ancienneRaison = this.raisonBlocage;
        this.raisonBlocage = null;
        ajouterHistorique("Compte débloqué");
        System.out.println("🔓 Compte débloqué (raison précédente : " + ancienneRaison + ")");
    }
    
    public void afficherReleve() {
        System.out.println("\n========== RELEVÉ ==========" );
        System.out.println("Compte    : " + numeroCompte);
        System.out.println("Titulaire : " + titulaire);
        System.out.printf("Solde     : %.2f€%n", solde);
        System.out.println("Statut    : " + (bloque ? "🔒 Bloqué (" + raisonBlocage + ")" : "🔓 Actif"));
        System.out.printf("Découvert : %.2f€ autorisé%n", DECOUVERT_AUTORISE);
        System.out.printf("Retiré aujourd'hui : %.2f€ / %.2f€%n", 
            montantRetireAujourdhui, LIMITE_DEBIT_JOURNALIER);
        System.out.println("============================\n");
    }
    
    public void afficherHistorique() {
        System.out.println("\n=== HISTORIQUE DES OPÉRATIONS ===");
        System.out.println("Compte : " + numeroCompte + " - " + titulaire);
        System.out.println("Nombre d'opérations : " + historique.size());
        System.out.println();
        for (String operation : historique) {
            System.out.println(operation);
        }
        System.out.println("===================================\n");
    }
    
    public void sauvegarderHistorique(String nomFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            writer.write("HISTORIQUE DU COMPTE " + numeroCompte);
            writer.newLine();
            writer.write("Titulaire : " + titulaire);
            writer.newLine();
            writer.write("Solde actuel : " + solde + "€");
            writer.newLine();
            writer.newLine();
            writer.write("OPÉRATIONS :");
            writer.newLine();
            writer.write("=".repeat(50));
            writer.newLine();
            
            for (String operation : historique) {
                writer.write(operation);
                writer.newLine();
            }
            
            System.out.println("✅ Historique sauvegardé : " + nomFichier);
        }
    }
    
    public double getSolde() { return solde; }
    public String getTitulaire() { return titulaire; }
    public String getNumeroCompte() { return numeroCompte; }
}
