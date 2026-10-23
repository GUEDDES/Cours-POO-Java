/**
 * Solution de l'Exercice 2 (Section 10) : Moteur de Facturation Polymorphe
 */

// 1. Interface de stratégie de remise
interface ApplicableRemise {
    double appliquerRemise(double montantBrut);
}

// 2. Politiques concrètes
class RemisePourcentage implements ApplicableRemise {
    private double pourcentage;

    public RemisePourcentage(double pourcentage) {
        if (pourcentage < 0 || pourcentage > 100) {
            throw new IllegalArgumentException("Pourcentage invalide (0 à 100)");
        }
        this.pourcentage = pourcentage;
    }

    @Override
    public double appliquerRemise(double montantBrut) {
        return montantBrut * (1.0 - pourcentage / 100.0);
    }
}

class RemiseSeuil implements ApplicableRemise {
    private double seuil;
    private double montantRabais;

    public RemiseSeuil(double seuil, double montantRabais) {
        this.seuil = Math.max(0, seuil);
        this.montantRabais = Math.max(0, montantRabais);
    }

    @Override
    public double appliquerRemise(double montantBrut) {
        if (montantBrut >= seuil) {
            return Math.max(0, montantBrut - montantRabais);
        }
        return montantBrut;
    }
}

class RemiseNulle implements ApplicableRemise {
    @Override
    public double appliquerRemise(double montantBrut) {
        return montantBrut;
    }
}

// 3. Interfaces de contrat
interface Payable {
    double getMontantNet();
}

interface Imposable {
    double getTauxTVA();
    double getMontantTTC();
}

// 4. Ligne de facture
class LigneFacture {
    private String designation;
    private int quantite;
    private double prixUnitaire;

    public LigneFacture(String designation, int quantite, double prixUnitaire) {
        this.designation = (designation != null) ? designation : "Article";
        this.quantite = Math.max(1, quantite);
        this.prixUnitaire = Math.max(0.0, prixUnitaire);
    }

    public double getSousTotal() {
        return quantite * prixUnitaire;
    }

    @Override
    public String toString() {
        return String.format("- %s x%d : %.2f DT", designation, quantite, getSousTotal());
    }
}

// 5. Facture utilisant la liaison dynamique
class Facture implements Payable, Imposable {
    private String numeroFacture;
    private LigneFacture[] lignes;
    private int nbLignes;
    private ApplicableRemise politiqueRemise;
    private double tauxTVA;

    public Facture(String numeroFacture, int maxLignes, double tauxTVA) {
        this.numeroFacture = numeroFacture;
        this.lignes = new LigneFacture[maxLignes];
        this.nbLignes = 0;
        this.tauxTVA = (tauxTVA >= 0) ? tauxTVA : 0.19;
        this.politiqueRemise = new RemiseNulle(); // Remise par défaut
    }

    public void ajouterLigne(LigneFacture ligne) {
        if (ligne != null && nbLignes < lignes.length) {
            lignes[nbLignes++] = ligne;
        }
    }

    public void setPolitiqueRemise(ApplicableRemise politique) {
        this.politiqueRemise = (politique != null) ? politique : new RemiseNulle();
    }

    public double getMontantBrut() {
        double total = 0.0;
        for (int i = 0; i < nbLignes; i++) {
            total += lignes[i].getSousTotal();
        }
        return total;
    }

    @Override
    public double getMontantNet() {
        return politiqueRemise.appliquerRemise(getMontantBrut());
    }

    @Override
    public double getTauxTVA() {
        return tauxTVA;
    }

    @Override
    public double getMontantTTC() {
        return getMontantNet() * (1.0 + tauxTVA);
    }

    public void afficherRecapitulatif() {
        System.out.println("==========================================");
        System.out.println("Facture N° : " + numeroFacture);
        for (int i = 0; i < nbLignes; i++) {
            System.out.println(lignes[i]);
        }
        System.out.printf("Montant Brut : %.2f DT%n", getMontantBrut());
        System.out.printf("Montant Net (après remise) : %.2f DT%n", getMontantNet());
        System.out.printf("Montant TTC (TVA %.0f%%) : %.2f DT%n", tauxTVA * 100, getMontantTTC());
        System.out.println("==========================================");
    }
}

public class Exercice2Solution {
    public static void main(String[] args) {
        Facture f = new Facture("FAC-2026-001", 10, 0.19);
        f.ajouterLigne(new LigneFacture("PC Portable", 1, 1500.0));
        f.ajouterLigne(new LigneFacture("Souris Sans Fil", 2, 50.0));
        f.ajouterLigne(new LigneFacture("Support Ergonomique", 1, 100.0));

        System.out.println("\n1. Facture Sans Remise :");
        f.afficherRecapitulatif();

        System.out.println("\n2. Facture avec Remise Pourcentage (10%) :");
        f.setPolitiqueRemise(new RemisePourcentage(10.0));
        f.afficherRecapitulatif();

        System.out.println("\n3. Facture avec Remise Seuil (150 DT de remise dès 1000 DT d'achats) :");
        f.setPolitiqueRemise(new RemiseSeuil(1000.0, 150.0));
        f.afficherRecapitulatif();

        System.out.println("\n4. Traitement Polymorphe via l'interface Payable :");
        Payable dette = f;
        System.out.printf("Net à payer via référence Payable : %.2f DT%n", dette.getMontantNet());
    }
}
