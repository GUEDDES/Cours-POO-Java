/**
 * Exemple : Système de Paiement Polymorphe
 * 
 * Objectif : Illustrer le polymorphisme avec classe abstraite
 * Concepts :
 * - Classe abstraite MoyenPaiement
 * - Plusieurs implémentations (CarteBancaire, PayPal, Especes)
 * - Polymorphisme : traiter tous les paiements de façon uniforme
 * - Méthode abstraite
 * 
 * @author Cours POO Java
 */

abstract class MoyenPaiement {
    protected double montant;
    protected String reference;
    
    public MoyenPaiement(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
        this.montant = montant;
        this.reference = genererReference();
    }
    
    // Méthode abstraite : chaque type de paiement l'implémente différemment
    public abstract boolean effectuerPaiement();
    public abstract String getType();
    
    // Méthode concrète (commune à tous)
    private String genererReference() {
        return "REF" + System.currentTimeMillis();
    }
    
    public String getReference() {
        return reference;
    }
    
    public double getMontant() {
        return montant;
    }
}

class CarteBancaire extends MoyenPaiement {
    private String numero;
    private String titulaire;
    
    public CarteBancaire(double montant, String numero, String titulaire) {
        super(montant);
        this.numero = masquerNumero(numero);
        this.titulaire = titulaire;
    }
    
    private String masquerNumero(String numero) {
        if (numero.length() < 4) return "****";
        return "**** **** **** " + numero.substring(numero.length() - 4);
    }
    
    @Override
    public boolean effectuerPaiement() {
        System.out.println("💳 Paiement par Carte Bancaire");
        System.out.println("   Titulaire : " + titulaire);
        System.out.println("   Numéro    : " + numero);
        System.out.printf("   Montant   : %.2f€%n", montant);
        System.out.println("   ✅ Paiement autorisé");
        return true;
    }
    
    @Override
    public String getType() {
        return "Carte Bancaire";
    }
}

class PayPal extends MoyenPaiement {
    private String email;
    
    public PayPal(double montant, String email) {
        super(montant);
        this.email = email;
    }
    
    @Override
    public boolean effectuerPaiement() {
        System.out.println("🔵 Paiement via PayPal");
        System.out.println("   Email     : " + email);
        System.out.printf("   Montant   : %.2f€%n", montant);
        System.out.println("   ✅ Transaction PayPal réussie");
        return true;
    }
    
    @Override
    public String getType() {
        return "PayPal";
    }
}

class Especes extends MoyenPaiement {
    private double montantRecu;
    
    public Especes(double montant, double montantRecu) {
        super(montant);
        if (montantRecu < montant) {
            throw new IllegalArgumentException("Montant insuffisant");
        }
        this.montantRecu = montantRecu;
    }
    
    @Override
    public boolean effectuerPaiement() {
        System.out.println("💵 Paiement en Espèces");
        System.out.printf("   Montant   : %.2f€%n", montant);
        System.out.printf("   Reçu      : %.2f€%n", montantRecu);
        
        double rendu = montantRecu - montant;
        if (rendu > 0) {
            System.out.printf("   💰 Rendu    : %.2f€%n", rendu);
        }
        System.out.println("   ✅ Paiement encaissé");
        return true;
    }
    
    @Override
    public String getType() {
        return "Espèces";
    }
}

class Caisse {
    private double totalJournee;
    private int nombreTransactions;
    
    public Caisse() {
        this.totalJournee = 0;
        this.nombreTransactions = 0;
    }
    
    // POLYMORPHISME : accepte n'importe quel MoyenPaiement
    public void traiterPaiement(MoyenPaiement paiement) {
        System.out.println("\n========================================");
        System.out.println("🛠️  TRANSACTION #" + (nombreTransactions + 1));
        System.out.println("Référence : " + paiement.getReference());
        System.out.println("Type      : " + paiement.getType());
        System.out.println("========================================");
        
        if (paiement.effectuerPaiement()) {
            totalJournee += paiement.getMontant();
            nombreTransactions++;
            System.out.println("\n✅ TRANSACTION RÉUSSIE");
        } else {
            System.out.println("\n❌ TRANSACTION ÉCHOUÉE");
        }
        System.out.println("========================================");
    }
    
    public void afficherBilan() {
        System.out.println("\n\n📋 ========== BILAN DE LA JOURNÉE ==========");
        System.out.println("Nombre de transactions : " + nombreTransactions);
        System.out.printf("Total encaissé         : %.2f€%n", totalJournee);
        System.out.println("==========================================\n");
    }
}

public class SystemePaiement {
    public static void main(String[] args) {
        System.out.println("🏪 === SYSTÈME DE PAIEMENT POLYMORPHE ===");
        
        Caisse caisse = new Caisse();
        
        // Différents moyens de paiement
        MoyenPaiement[] paiements = {
            new CarteBancaire(45.50, "1234567812345678", "Alice Dupont"),
            new PayPal(30.00, "bob@exemple.com"),
            new Especes(25.00, 30.00),
            new CarteBancaire(120.00, "9876543298765432", "Charlie Martin"),
            new PayPal(15.99, "diane@exemple.com")
        };
        
        // Traiter tous les paiements de manière uniforme
        for (MoyenPaiement paiement : paiements) {
            caisse.traiterPaiement(paiement);  // POLYMORPHISME
            
            try {
                Thread.sleep(500);  // Pause pour lisibilité
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Afficher le bilan
        caisse.afficherBilan();
    }
}
