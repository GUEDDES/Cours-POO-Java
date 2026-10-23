/**
 * Solution de l'Exercice 2 (Section 12) : Audit et Transactions Bancaires Robustes
 */

// 1. Hiérarchie d'exceptions
abstract class TransactionException extends Exception {
    public TransactionException(String message) {
        super(message);
    }
}

class SoldeInsuffisantException extends TransactionException {
    private double soldeActuel;
    private double montantDemande;

    public SoldeInsuffisantException(double soldeActuel, double montantDemande) {
        super(String.format("Solde insuffisant : solde=%.2f DT, demandé=%.2f DT", soldeActuel, montantDemande));
        this.soldeActuel = soldeActuel;
        this.montantDemande = montantDemande;
    }

    public double getSoldeActuel() { return soldeActuel; }
    public double getMontantDemande() { return montantDemande; }
}

class CompteBloqueException extends RuntimeException {
    public CompteBloqueException(String numeroCompte) {
        super("Le compte N° " + numeroCompte + " est actuellement bloqué ou inactif.");
    }
}

// 2. Entité bancaire
class CompteBancaire {
    private String numero;
    private double solde;
    private boolean actif;

    public CompteBancaire(String numero, double soldeInitial, boolean actif) {
        this.numero = numero;
        this.solde = Math.max(0.0, soldeInitial);
        this.actif = actif;
    }

    public String getNumero() { return numero; }
    public double getSolde() { return solde; }
    public boolean isActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }

    public void debiter(double montant) throws SoldeInsuffisantException {
        if (!actif) {
            throw new CompteBloqueException(numero);
        }
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant du débit doit être positif.");
        }
        if (solde < montant) {
            throw new SoldeInsuffisantException(solde, montant);
        }
        this.solde -= montant;
    }

    public void crediter(double montant) {
        if (!actif) {
            throw new CompteBloqueException(numero);
        }
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant du crédit doit être positif.");
        }
        this.solde += montant;
    }

    @Override
    public String toString() {
        return String.format("Compte[%s : %.2f DT, %s]", numero, solde, actif ? "Actif" : "Bloqué");
    }
}

// 3. Modélisation abstraite de Transaction
abstract class Transaction {
    private static long compteurId = 1000;
    protected long idTransaction;
    protected double montant;
    protected boolean reussie;

    public Transaction(double montant) {
        this.idTransaction = ++compteurId;
        this.montant = montant;
        this.reussie = false;
    }

    public long getIdTransaction() { return idTransaction; }
    public boolean isReussie() { return reussie; }

    public abstract void executer() throws TransactionException;
}

// 4. Virement atomique
class Virement extends Transaction {
    private CompteBancaire source;
    private CompteBancaire destination;

    public Virement(CompteBancaire source, CompteBancaire destination, double montant) {
        super(montant);
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void executer() throws TransactionException {
        // Débit du compte source
        source.debiter(montant);

        try {
            // Crédit du compte destination
            destination.crediter(montant);
            this.reussie = true;
        } catch (RuntimeException e) {
            // Rollback en cas d'échec sur la destination pour garantir la cohérence
            source.crediter(montant);
            throw e;
        }
    }

    @Override
    public String toString() {
        return String.format("Virement #%d de %.2f DT (%s -> %s)",
                idTransaction, montant, source.getNumero(), destination.getNumero());
    }
}

// 5. Service d'audit
class ServiceAudit {
    public static void executerEtAuditer(Transaction t) {
        System.out.println("\n[AUDIT] Début exécution : " + t);
        try {
            t.executer();
            System.out.println("✅ [AUDIT] Succès de la transaction #" + t.getIdTransaction());
        } catch (SoldeInsuffisantException e) {
            System.err.println("❌ [AUDIT ÉCHEC] Solde insuffisant : " + e.getMessage());
        } catch (CompteBloqueException e) {
            System.err.println("⛔ [AUDIT ÉCHEC] Compte verrouillé : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("⚠️ [AUDIT ÉCHEC] Anomalie imprévue : " + e.getMessage());
        } finally {
            System.out.println("[AUDIT STATUT FINAL] Transaction #" + t.getIdTransaction() +
                    " -> État : " + (t.isReussie() ? "CONFIRMÉE" : "ANNULÉE"));
        }
    }
}

public class Exercice2Solution {
    public static void main(String[] args) {
        CompteBancaire c1 = new CompteBancaire("TN-01-A", 1000.0, true);
        CompteBancaire c2 = new CompteBancaire("TN-02-B", 300.0, true);
        CompteBancaire c3 = new CompteBancaire("TN-03-C", 500.0, false); // Compte bloqué

        System.out.println("--- État Initial ---");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        // 1. Transaction normale
        Virement v1 = new Virement(c1, c2, 200.0);
        ServiceAudit.executerEtAuditer(v1);

        // 2. Transaction rejetée : Solde insuffisant
        Virement v2 = new Virement(c2, c1, 9000.0);
        ServiceAudit.executerEtAuditer(v2);

        // 3. Transaction avec compte bloqué (Rollback garanti)
        Virement v3 = new Virement(c1, c3, 150.0);
        ServiceAudit.executerEtAuditer(v3);

        System.out.println("\n--- État Final des Comptes ---");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }
}
