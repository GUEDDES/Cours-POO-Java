/**
 * Exemple : Gestion des Exceptions avec Fichiers
 * 
 * Objectif : Illustrer la gestion d'exceptions (try-catch-finally, exceptions personnalisées)
 * Concepts :
 * - try-catch-finally
 * - Exception personnalisée
 * - try-with-resources
 * - Propagation d'exceptions (throws)
 * 
 * @author Cours POO Java
 */
import java.io.*;
import java.util.*;

// Exception personnalisée
class FichierException extends Exception {
    private String nomFichier;
    private String codeErreur;
    
    public FichierException(String message, String nomFichier, String codeErreur) {
        super(message);
        this.nomFichier = nomFichier;
        this.codeErreur = codeErreur;
    }
    
    public String getNomFichier() {
        return nomFichier;
    }
    
    public String getCodeErreur() {
        return codeErreur;
    }
    
    @Override
    public String toString() {
        return "FichierException [Code: " + codeErreur + ", Fichier: " + nomFichier + "] " + getMessage();
    }
}

class GestionnaireFichiers {
    
    // Méthode qui déclare lever une exception
    public void creerFichier(String chemin) throws FichierException {
        File fichier = new File(chemin);
        
        if (fichier.exists()) {
            throw new FichierException(
                "Le fichier existe déjà",
                chemin,
                "ERR_FICHIER_EXISTE"
            );
        }
        
        try {
            if (fichier.createNewFile()) {
                System.out.println("✅ Fichier créé : " + chemin);
            }
        } catch (IOException e) {
            throw new FichierException(
                "Impossible de créer le fichier : " + e.getMessage(),
                chemin,
                "ERR_CREATION"
            );
        }
    }
    
    // Try-with-resources (fermeture automatique)
    public void ecrireFichier(String chemin, List<String> lignes) throws FichierException {
        File fichier = new File(chemin);
        
        if (!fichier.exists()) {
            throw new FichierException(
                "Le fichier n'existe pas",
                chemin,
                "ERR_FICHIER_INTROUVABLE"
            );
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            for (String ligne : lignes) {
                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("✅ Contenu écrit dans " + chemin + " (" + lignes.size() + " lignes)");
        } catch (IOException e) {
            throw new FichierException(
                "Échec de l'écriture : " + e.getMessage(),
                chemin,
                "ERR_ECRITURE"
            );
        }
    }
    
    public List<String> lireFichier(String chemin) throws FichierException {
        File fichier = new File(chemin);
        
        if (!fichier.exists()) {
            throw new FichierException(
                "Le fichier n'existe pas",
                chemin,
                "ERR_FICHIER_INTROUVABLE"
            );
        }
        
        if (!fichier.canRead()) {
            throw new FichierException(
                "Pas de permission de lecture",
                chemin,
                "ERR_PERMISSION"
            );
        }
        
        List<String> lignes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
            System.out.println("✅ Fichier lu : " + chemin + " (" + lignes.size() + " lignes)");
        } catch (IOException e) {
            throw new FichierException(
                "Erreur lors de la lecture : " + e.getMessage(),
                chemin,
                "ERR_LECTURE"
            );
        }
        
        return lignes;
    }
    
    public void supprimerFichier(String chemin) throws FichierException {
        File fichier = new File(chemin);
        
        if (!fichier.exists()) {
            throw new FichierException(
                "Le fichier n'existe pas",
                chemin,
                "ERR_FICHIER_INTROUVABLE"
            );
        }
        
        if (fichier.delete()) {
            System.out.println("✅ Fichier supprimé : " + chemin);
        } else {
            throw new FichierException(
                "Impossible de supprimer le fichier",
                chemin,
                "ERR_SUPPRESSION"
            );
        }
    }
}

public class GestionFichiers {
    public static void main(String[] args) {
        System.out.println("=== GESTION D'EXCEPTIONS : FICHIERS ===");
        
        GestionnaireFichiers gestionnaire = new GestionnaireFichiers();
        String nomFichier = "test_exceptions.txt";
        
        try {
            // 1. Créer un fichier
            System.out.println("\n--- Création du fichier ---");
            gestionnaire.creerFichier(nomFichier);
            
            // 2. Écrire dans le fichier
            System.out.println("\n--- Écriture dans le fichier ---");
            List<String> contenu = Arrays.asList(
                "Ligne 1 : Hello World!",
                "Ligne 2 : Gestion des exceptions",
                "Ligne 3 : Try-Catch-Finally",
                "Ligne 4 : Exceptions personnalisées"
            );
            gestionnaire.ecrireFichier(nomFichier, contenu);
            
            // 3. Lire le fichier
            System.out.println("\n--- Lecture du fichier ---");
            List<String> lignes = gestionnaire.lireFichier(nomFichier);
            System.out.println("\nContenu :");
            for (int i = 0; i < lignes.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + lignes.get(i));
            }
            
            // 4. Tentative de recréation (génère une exception)
            System.out.println("\n--- Tentative de recréation (provoque exception) ---");
            try {
                gestionnaire.creerFichier(nomFichier);
            } catch (FichierException e) {
                System.out.println("❌ Exception capturée : " + e);
                System.out.println("   Code d'erreur : " + e.getCodeErreur());
            }
            
            // 5. Supprimer le fichier
            System.out.println("\n--- Suppression du fichier ---");
            gestionnaire.supprimerFichier(nomFichier);
            
            // 6. Tentative de lecture après suppression (génère exception)
            System.out.println("\n--- Tentative de lecture après suppression ---");
            try {
                gestionnaire.lireFichier(nomFichier);
            } catch (FichierException e) {
                System.out.println("❌ Exception capturée : " + e.getMessage());
                System.out.println("   Code : " + e.getCodeErreur());
            }
            
        } catch (FichierException e) {
            System.err.println("\n❌ ERREUR CRITIQUE : " + e);
            System.err.println("   Fichier : " + e.getNomFichier());
            System.err.println("   Code    : " + e.getCodeErreur());
        } finally {
            System.out.println("\n🏁 Programme terminé (bloc finally toujours exécuté)");
        }
        
        // Test : division par zéro (exception non vérifiée)
        System.out.println("\n--- Test exception non vérifiée ---");
        try {
            int resultat = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("❌ ArithmeticException capturée : " + e.getMessage());
        }
    }
}
