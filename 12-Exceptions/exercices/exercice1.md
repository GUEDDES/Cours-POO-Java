# Exercice 1 : Gestion de Comptes Bancaires avec Exceptions

## 🎯 Objectif

Créer un système bancaire avec gestion complète des exceptions (vérifiées et non vérifiées).

---

## 📋 Énoncé

Créez un système de gestion de comptes bancaires qui gère proprement toutes les erreurs possibles.

---

## 📝 Classes et Exceptions à créer

### Exceptions Personnalisées

```java
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

// Exception vérifiée
class CompteBloqueException extends Exception {
    private String raison;
    
    public CompteBloqueException(String raison) {
        super("Compte bloqué : " + raison);
        this.raison = raison;
    }
    
    public String getRaison() { return raison; }
}

// Exception non vérifiée (unchecked)
class MontantInvalideException extends RuntimeException {
    public MontantInvalideException(String message) {
        super(message);
    }
}
```

### Classe `CompteBancaire`

```java
class CompteBancaire {
    private String numeroCompte;
    private String titulaire;
    private double solde;
    private boolean bloque;
    private String raisonBlocage;
    private static final double DECOUVERT_AUTORISE = 500.0;
    
    public CompteBancaire(String numeroCompte, String titulaire, double soldeInitial) {
        if (soldeInitial < 0) {
            throw new MontantInvalideException("Le solde initial ne peut pas être négatif");
        }
        this.numeroCompte = numeroCompte;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
        this.bloque = false;
    }
    
    /**
     * Dépose un montant sur le compte
     * @throws CompteBloqueException si le compte est bloqué
     * @throws MontantInvalideException si le montant est négatif ou nul
     */
    public void deposer(double montant) throws CompteBloqueException {
        if (bloque) {
            throw new CompteBloqueException(raisonBlocage);
        }
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant doit être positif");
        }
        solde += montant;
        System.out.printf("✅ Dépôt de %.2f€ effectué%n", montant);
    }
    
    /**
     * Retire un montant du compte
     * @throws CompteBloqueException si le compte est bloqué
     * @throws SoldeInsuffisantException si le solde est insuffisant
     * @throws MontantInvalideException si le montant est négatif ou nul
     */
    public void retirer(double montant) 
            throws CompteBloqueException, SoldeInsuffisantException {
        
        if (bloque) {
            throw new CompteBloqueException(raisonBlocage);
        }
        if (montant <= 0) {
            throw new MontantInvalideException("Le montant doit être positif");
        }
        
        double soldeApresRetrait = solde - montant;
        if (soldeApresRetrait < -DECOUVERT_AUTORISE) {
            throw new SoldeInsuffisantException(solde, montant);
        }
        
        solde = soldeApresRetrait;
        System.out.printf("✅ Retrait de %.2f€ effectué%n", montant);
        
        if (solde < 0) {
            System.out.printf("⚠️  Découvert : %.2f€%n", -solde);
        }
    }
    
    /**
     * Effectue un virement vers un autre compte
     */
    public void virement(CompteBancaire destinataire, double montant) 
            throws CompteBloqueException, SoldeInsuffisantException {
        
        if (destinataire == null) {
            throw new IllegalArgumentException("Destinataire invalide");
        }
        
        System.out.println("\n💸 Virement en cours...");
        try {
            this.retirer(montant);
            destinataire.deposer(montant);
            System.out.println("✅ Virement réussi");
        } catch (CompteBloqueException | SoldeInsuffisantException e) {
            System.out.println("❌ Virement échoué : " + e.getMessage());
            throw e;  // Relancer l'exception
        }
    }
    
    public void bloquer(String raison) {
        this.bloque = true;
        this.raisonBlocage = raison;
        System.out.println("🔒 Compte bloqué : " + raison);
    }
    
    public void debloquer() {
        this.bloque = false;
        this.raisonBlocage = null;
        System.out.println("🔓 Compte débloqué");
    }
    
    public void afficherReleve() {
        System.out.println("\n========== RELEVÉ ==========" );
        System.out.println("Compte  : " + numeroCompte);
        System.out.println("Titulaire : " + titulaire);
        System.out.printf("Solde   : %.2f€%n", solde);
        System.out.println("Statut  : " + (bloque ? "🔒 Bloqué" : "🔓 Actif"));
        System.out.println("============================\n");
    }
    
    public double getSolde() { return solde; }
    public String getTitulaire() { return titulaire; }
}
```

---

## ✅ Programme de test

```java
public class TestCompteBancaire {
    public static void main(String[] args) {
        System.out.println("=== SYSTÈME BANCAIRE ===");
        
        try {
            // Création de comptes
            CompteBancaire compte1 = new CompteBancaire("FR001", "Alice", 1000);
            CompteBancaire compte2 = new CompteBancaire("FR002", "Bob", 500);
            
            compte1.afficherReleve();
            compte2.afficherReleve();
            
            // Test 1 : Dépôt
            System.out.println("--- Test 1 : Dépôt ---");
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
                System.out.printf("   Manquant : %.2f€%n", e.getManquant());
            }
            
            // Test 5 : Blocage de compte
            System.out.println("\n--- Test 5 : Blocage de compte ---");
            compte1.bloquer("Activité suspecte");
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
                System.out.println("❌ Exception : " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("❌ ERREUR CRITIQUE : " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n🏁 Fin du programme");
        }
    }
}
```

---

## 🎯 Critères de Validation

- [ ] 3 exceptions personnalisées créées
- [ ] 1 exception vérifiée (extends Exception)
- [ ] 1 exception non vérifiée (extends RuntimeException)
- [ ] Méthode `deposer()` avec throws
- [ ] Méthode `retirer()` avec throws
- [ ] Méthode `virement()` avec throws
- [ ] Utilisation de try-catch-finally
- [ ] Relance d'exception (throw e)
- [ ] Multi-catch utilisé
- [ ] Validation des paramètres
- [ ] Programme de test complet
- [ ] Tous les cas d'erreur testés

---

## 🚀 Bonus

1. Ajouter une exception `LimiteDebitException` (limite de retrait journalier)
2. Logger toutes les transactions dans un fichier avec try-with-resources
3. Créer une méthode `historiqueTransactions()` qui peut lever `IOException`
4. Ajouter un système d'alerte par email en cas d'exception critique

---

**Bon courage ! 💰**
