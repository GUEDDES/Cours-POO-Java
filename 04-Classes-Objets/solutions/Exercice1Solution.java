/**
 * Solution Exercice 1 : Classe Étudiant
 * 
 * @author Cours POO Java
 */
public class Exercice1Solution {
    
    public static void main(String[] args) {
        System.out.println("=== TEST CLASSE ÉTUDIANT ===");
        
        // Création d'étudiants
        System.out.println("\n--- Création ---");
        Etudiant e1 = new Etudiant("E00001", "DUPONT", "Alice", "15/03/2003");
        Etudiant e2 = new Etudiant("E00002", "MARTIN", "Bob", "22/08/2004");
        Etudiant e3 = new Etudiant("E00003", "DURAND", "Charlie", "10/12/2003");
        
        // Définir les moyennes
        e1.setMoyenne(15.5);
        e2.setMoyenne(9.0);
        e3.setMoyenne(17.8);
        
        // Ajouter des absences
        e1.ajouterAbsence();
        e1.ajouterAbsence();
        
        for (int i = 0; i < 12; i++) {
            e2.ajouterAbsence();
        }
        
        // Afficher les informations
        System.out.println("\n=== INFORMATIONS ===\n");
        e1.afficher();
        e2.afficher();
        e3.afficher();
        
        // Tests des méthodes
        System.out.println("\n=== TESTS MÉTHODES ===\n");
        
        System.out.println("toString() e1 : " + e1);
        System.out.println("\nComparaison moyennes :");
        e1.comparerMoyenne(e3);
        
        System.out.println("\nMentions :");
        System.out.println(e1.getNom() + " : " + e1.obtenirMention());
        System.out.println(e2.getNom() + " : " + e2.obtenirMention());
        System.out.println(e3.getNom() + " : " + e3.obtenirMention());
        
        System.out.println("\nNombre total d'étudiants : " + Etudiant.getNombreEtudiants());
        
        // Test validation
        System.out.println("\n=== TESTS VALIDATION ===\n");
        try {
            Etudiant e4 = new Etudiant("123", "TEST", "Test", "01/01/2000");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Exception capturée : " + e.getMessage());
        }
        
        try {
            e1.setMoyenne(25);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Exception capturée : " + e.getMessage());
        }
    }
}

class Etudiant {
    // Attributs d'instance
    private String matricule;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private double moyenne;
    private int nbAbsences;
    
    // Attribut statique
    private static int nombreEtudiants = 0;
    
    // Constructeur
    public Etudiant(String matricule, String nom, String prenom, String dateNaissance) {
        // Validation matricule
        if (!validerMatricule(matricule)) {
            throw new IllegalArgumentException("Matricule invalide. Format attendu : E + 5 chiffres");
        }
        
        // Validation nom et prénom
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être vide");
        }
        
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.moyenne = 0.0;
        this.nbAbsences = 0;
        
        nombreEtudiants++;
        System.out.println("✅ Étudiant créé : " + matricule);
    }
    
    // Méthode de validation du matricule
    private boolean validerMatricule(String matricule) {
        if (matricule == null || matricule.length() != 6) {
            return false;
        }
        if (matricule.charAt(0) != 'E') {
            return false;
        }
        for (int i = 1; i < 6; i++) {
            if (!Character.isDigit(matricule.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    // Getters
    public String getMatricule() {
        return matricule;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public String getDateNaissance() {
        return dateNaissance;
    }
    
    public double getMoyenne() {
        return moyenne;
    }
    
    public int getNbAbsences() {
        return nbAbsences;
    }
    
    // Setters avec validation
    public void setMoyenne(double moyenne) {
        if (moyenne < 0 || moyenne > 20) {
            throw new IllegalArgumentException("La moyenne doit être entre 0 et 20");
        }
        this.moyenne = moyenne;
    }
    
    public void setNbAbsences(int nbAbsences) {
        if (nbAbsences < 0) {
            throw new IllegalArgumentException("Le nombre d'absences ne peut pas être négatif");
        }
        this.nbAbsences = nbAbsences;
    }
    
    // Méthode statique
    public static int getNombreEtudiants() {
        return nombreEtudiants;
    }
    
    // Méthodes métier
    public void ajouterAbsence() {
        nbAbsences++;
    }
    
    public boolean estAdmis() {
        return moyenne >= 10;
    }
    
    public boolean risqueExclusion() {
        return nbAbsences > 10;
    }
    
    public int calculerAge() {
        // Extraire l'année (4 derniers caractères)
        String anneeStr = dateNaissance.substring(6);
        int anneeNaissance = Integer.parseInt(anneeStr);
        return 2025 - anneeNaissance;
    }
    
    public void afficher() {
        System.out.println("========== FICHE ÉTUDIANT ==========");
        System.out.println("Matricule      : " + matricule);
        System.out.println("Nom            : " + nom + " " + prenom);
        System.out.println("Date naissance : " + dateNaissance);
        System.out.println("Âge            : " + calculerAge() + " ans");
        System.out.printf("Moyenne        : %.2f/20%n", moyenne);
        System.out.println("Absences       : " + nbAbsences);
        System.out.println("Statut         : " + (estAdmis() ? "✅ Admis" : "❌ Non admis"));
        
        if (risqueExclusion()) {
            System.out.println("⚠️  RISQUE D'EXCLUSION (" + nbAbsences + " absences)");
        }
        System.out.println("====================================");
        System.out.println();
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s %s (%.2f/20)", matricule, prenom, nom, moyenne);
    }
    
    // BONUS
    public void comparerMoyenne(Etudiant autre) {
        System.out.println("Comparaison : " + this.nom + " vs " + autre.nom);
        if (this.moyenne > autre.moyenne) {
            System.out.println(this.nom + " a une meilleure moyenne (" + this.moyenne + " > " + autre.moyenne + ")");
        } else if (this.moyenne < autre.moyenne) {
            System.out.println(autre.nom + " a une meilleure moyenne (" + autre.moyenne + " > " + this.moyenne + ")");
        } else {
            System.out.println("Même moyenne : " + this.moyenne);
        }
    }
    
    public String obtenirMention() {
        if (moyenne >= 16) return "🎖️ Très Bien";
        if (moyenne >= 14) return "🥈 Bien";
        if (moyenne >= 12) return "🥉 Assez Bien";
        if (moyenne >= 10) return "✅ Passable";
        return "❌ Échec";
    }
}
