/**
 * Exemple : Relation d'Agrégation Université-Étudiant
 * 
 * Objectif : Illustrer une relation d'agrégation (cycle de vie indépendant)
 * Concepts :
 * - Agrégation (relation faible)
 * - ArrayList pour gérer plusieurs objets
 * - Les étudiants existent indépendamment de l'université
 * 
 * @author Cours POO Java
 */
import java.util.ArrayList;

class Etudiant {
    private String nom;
    private String matricule;
    private double moyenne;
    
    public Etudiant(String nom, String matricule) {
        this.nom = nom;
        this.matricule = matricule;
        this.moyenne = 0.0;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getMatricule() {
        return matricule;
    }
    
    public void setMoyenne(double moyenne) {
        if (moyenne >= 0 && moyenne <= 20) {
            this.moyenne = moyenne;
        }
    }
    
    public double getMoyenne() {
        return moyenne;
    }
    
    public void afficher() {
        System.out.printf("🎓 %s (%s) - Moyenne : %.2f/20%n", nom, matricule, moyenne);
    }
}

class Universite {
    private String nom;
    private String ville;
    private ArrayList<Etudiant> etudiants;
    
    public Universite(String nom, String ville) {
        this.nom = nom;
        this.ville = ville;
        this.etudiants = new ArrayList<>();
        System.out.println("🏛️ Université créée : " + nom + " (" + ville + ")");
    }
    
    // AGRÉGATION : L'étudiant est passé en paramètre
    public void inscrire(Etudiant etudiant) {
        if (!etudiants.contains(etudiant)) {
            etudiants.add(etudiant);
            System.out.println("✅ " + etudiant.getNom() + " inscrit à " + nom);
        } else {
            System.out.println("⚠️ " + etudiant.getNom() + " déjà inscrit");
        }
    }
    
    public void desinscrire(Etudiant etudiant) {
        if (etudiants.remove(etudiant)) {
            System.out.println("❌ " + etudiant.getNom() + " désinscrit de " + nom);
        } else {
            System.out.println("⚠️ " + etudiant.getNom() + " n'est pas inscrit");
        }
    }
    
    public void afficherEtudiants() {
        System.out.println("\n========== " + nom + " ==========");
        System.out.println("🏙️ Ville : " + ville);
        System.out.println("👥 Nombre d'étudiants : " + etudiants.size());
        System.out.println("\nÉtudiants inscrits :");
        
        if (etudiants.isEmpty()) {
            System.out.println("  (Aucun étudiant inscrit)");
        } else {
            for (Etudiant e : etudiants) {
                System.out.print("  ");
                e.afficher();
            }
        }
        System.out.println("===========================\n");
    }
    
    public double moyenneGenerale() {
        if (etudiants.isEmpty()) return 0.0;
        
        double somme = 0;
        for (Etudiant e : etudiants) {
            somme += e.getMoyenne();
        }
        return somme / etudiants.size();
    }
    
    public ArrayList<Etudiant> etudiantsAdmis() {
        ArrayList<Etudiant> admis = new ArrayList<>();
        for (Etudiant e : etudiants) {
            if (e.getMoyenne() >= 10) {
                admis.add(e);
            }
        }
        return admis;
    }
}

public class UniversiteEtudiant {
    public static void main(String[] args) {
        System.out.println("=== SYSTÈME UNIVERSITAIRE ===");
        
        // Créer des étudiants INDÉPENDAMMENT
        System.out.println("\n--- Création des étudiants ---");
        Etudiant alice = new Etudiant("Alice Dupont", "E001");
        Etudiant bob = new Etudiant("Bob Martin", "E002");
        Etudiant charlie = new Etudiant("Charlie Durand", "E003");
        Etudiant diane = new Etudiant("Diane Lefebvre", "E004");
        
        // Définir les moyennes
        alice.setMoyenne(15.5);
        bob.setMoyenne(12.0);
        charlie.setMoyenne(9.5);
        diane.setMoyenne(16.8);
        
        // Créer des universités
        System.out.println("\n--- Création des universités ---");
        Universite paris = new Universite("Université de Paris", "Paris");
        Universite lyon = new Universite("Université de Lyon", "Lyon");
        
        // Inscrire des étudiants (AGRÉGATION)
        System.out.println("\n--- Inscriptions ---");
        paris.inscrire(alice);
        paris.inscrire(bob);
        paris.inscrire(charlie);
        
        lyon.inscrire(diane);
        lyon.inscrire(bob);  // Bob peut s'inscrire dans 2 universités !
        
        // Afficher les informations
        paris.afficherEtudiants();
        lyon.afficherEtudiants();
        
        // Statistiques
        System.out.println("--- Statistiques ---");
        System.out.printf("Moyenne générale à Paris : %.2f/20%n", paris.moyenneGenerale());
        System.out.printf("Moyenne générale à Lyon : %.2f/20%n", lyon.moyenneGenerale());
        
        // Étudiants admis
        System.out.println("\nÉtudiants admis à Paris (≥ 10/20) :");
        for (Etudiant e : paris.etudiantsAdmis()) {
            System.out.print("  ");
            e.afficher();
        }
        
        // Désinscrire un étudiant
        System.out.println("\n--- Désinscription ---");
        paris.desinscrire(charlie);
        paris.afficherEtudiants();
        
        // ✅ Charlie existe toujours, même désinscrit !
        System.out.println("Charlie après désinscription :");
        charlie.afficher();
        
        // ✅ Même si l'université disparaît, les étudiants existent
        paris = null;
        System.out.println("\n⚠️ Université de Paris supprimée");
        System.out.println("Alice existe toujours :");
        alice.afficher();
    }
}
