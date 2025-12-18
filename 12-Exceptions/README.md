# Section 12 : Gestion des Exceptions

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre le mécanisme des exceptions
- Utiliser try-catch-finally
- Lancer des exceptions avec `throw`
- Déclarer des exceptions avec `throws`
- Créer des exceptions personnalisées
- Distinguer exceptions vérifiées et non vérifiées

---

## 1. Qu'est-ce qu'une Exception ?

### 1.1 Définition

Une **exception** est un événement **anormal** qui interrompt le flux d'exécution du programme.

**Exemples** :
```java
int x = 10 / 0;  // ArithmeticException (division par zéro)

String s = null;
s.length();  // NullPointerException

int[] tableau = new int[3];
tableau[10] = 5;  // ArrayIndexOutOfBoundsException

Integer.parseInt("abc");  // NumberFormatException
```

---

### 1.2 Hiérarchie des Exceptions

```
          Throwable
              |
      +-------+-------+
      |               |
    Error         Exception
                      |
              +-------+-------+
              |               |
     RuntimeException    Checked Exceptions
     (Non vérifiées)   (Vérifiées)
```

---

## 2. Capturer une Exception : try-catch

### 2.1 Syntaxe de Base

```java
try {
    // Code susceptible de lever une exception
} catch (TypeException e) {
    // Code exécuté en cas d'exception
}
```

---

### 2.2 Exemple Simple

```java
public class TestException {
    public static void main(String[] args) {
        try {
            int resultat = 10 / 0;  // Division par zéro
            System.out.println("Résultat : " + resultat);
        } catch (ArithmeticException e) {
            System.out.println("❌ Erreur : Division par zéro !");
            System.out.println("Message : " + e.getMessage());
        }
        
        System.out.println("Programme terminé");  // Exécuté normalement
    }
}
```

**Sortie** :
```
❌ Erreur : Division par zéro !
Message : / by zero
Programme terminé
```

---

### 2.3 Capturer Plusieurs Exceptions

```java
public class TestMultiCatch {
    public static void main(String[] args) {
        String nombre = "abc";
        
        try {
            int valeur = Integer.parseInt(nombre);
            int resultat = 100 / valeur;
            System.out.println("Résultat : " + resultat);
        } catch (NumberFormatException e) {
            System.out.println("❌ Erreur : Format de nombre invalide");
        } catch (ArithmeticException e) {
            System.out.println("❌ Erreur : Division par zéro");
        } catch (Exception e) {
            System.out.println("❌ Erreur inattendue : " + e.getMessage());
        }
    }
}
```

---

### 2.4 Multi-Catch (Java 7+)

```java
try {
    // Code
} catch (NumberFormatException | ArithmeticException e) {
    System.out.println("❌ Erreur numérique : " + e.getMessage());
}
```

---

## 3. Le Bloc `finally`

### 3.1 Principe

Le bloc `finally` est **toujours exécuté**, qu'il y ait exception ou non.

```java
try {
    // Code
} catch (Exception e) {
    // Gestion exception
} finally {
    // TOUJOURS exécuté
    // Utile pour libérer des ressources
}
```

---

### 3.2 Exemple

```java
import java.io.*;

public class TestFinally {
    public static void main(String[] args) {
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader("fichier.txt"));
            String ligne = reader.readLine();
            System.out.println(ligne);
        } catch (IOException e) {
            System.out.println("❌ Erreur de lecture : " + e.getMessage());
        } finally {
            // Fermer le fichier dans TOUS les cas
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("✅ Fichier fermé");
                } catch (IOException e) {
                    System.out.println("❌ Erreur lors de la fermeture");
                }
            }
        }
    }
}
```

---

### 3.3 Try-With-Resources (Java 7+)

**Meilleure pratique** : fermeture automatique des ressources.

```java
try (BufferedReader reader = new BufferedReader(new FileReader("fichier.txt"))) {
    String ligne = reader.readLine();
    System.out.println(ligne);
} catch (IOException e) {
    System.out.println("❌ Erreur : " + e.getMessage());
}
// reader.close() est appelé automatiquement
```

---

## 4. Lancer une Exception : `throw`

### 4.1 Syntaxe

```java
throw new TypeException("Message d'erreur");
```

---

### 4.2 Exemple : Validation

```java
class CompteBancaire {
    private double solde;
    
    public CompteBancaire(double soldeInitial) {
        if (soldeInitial < 0) {
            throw new IllegalArgumentException("Le solde ne peut pas être négatif");
        }
        this.solde = soldeInitial;
    }
    
    public void retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant invalide");
        }
        if (montant > solde) {
            throw new IllegalStateException("Solde insuffisant");
        }
        solde -= montant;
    }
    
    public double getSolde() {
        return solde;
    }
}

public class TestThrow {
    public static void main(String[] args) {
        try {
            CompteBancaire compte = new CompteBancaire(1000);
            compte.retirer(1500);  // Exception
        } catch (IllegalStateException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}
```

---

## 5. Déclarer des Exceptions : `throws`

### 5.1 Principe

Le mot-clé `throws` indique qu'une méthode **peut** lever une exception.

```java
public void methode() throws IOException {
    // Code qui peut lever IOException
}
```

---

### 5.2 Exemple

```java
import java.io.*;

class FichierUtils {
    // Méthode déclare qu'elle peut lever IOException
    public static String lireFichier(String chemin) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(chemin));
        String ligne = reader.readLine();
        reader.close();
        return ligne;
    }
}

public class TestThrows {
    public static void main(String[] args) {
        try {
            String contenu = FichierUtils.lireFichier("fichier.txt");
            System.out.println(contenu);
        } catch (IOException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}
```

---

## 6. Exceptions Vérifiées vs Non Vérifiées

### 6.1 Exceptions Vérifiées (Checked)

**Doivent** être capturées ou déclarées.

**Exemples** :
- `IOException`
- `SQLException`
- `ClassNotFoundException`

```java
// ❌ ERREUR si non capturée
public void methode() {
    FileReader reader = new FileReader("fichier.txt");  // Erreur de compilation
}

// ✅ Solution 1 : Capturer
public void methode() {
    try {
        FileReader reader = new FileReader("fichier.txt");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}

// ✅ Solution 2 : Déclarer
public void methode() throws FileNotFoundException {
    FileReader reader = new FileReader("fichier.txt");
}
```

---

### 6.2 Exceptions Non Vérifiées (Unchecked)

**Ne nécessitent pas** de capture obligatoire.

**Exemples** :
- `NullPointerException`
- `ArrayIndexOutOfBoundsException`
- `ArithmeticException`
- `IllegalArgumentException`

```java
// Pas d'obligation de capturer
public void methode() {
    int x = 10 / 0;  // Pas d'erreur de compilation
}
```

---

## 7. Créer des Exceptions Personnalisées

### 7.1 Exception Personnalisée Simple

```java
class SoldeInsuffisantException extends Exception {
    public SoldeInsuffisantException(String message) {
        super(message);
    }
}

class CompteBancaire {
    private double solde;
    
    public CompteBancaire(double solde) {
        this.solde = solde;
    }
    
    public void retirer(double montant) throws SoldeInsuffisantException {
        if (montant > solde) {
            throw new SoldeInsuffisantException(
                "Solde insuffisant. Disponible : " + solde + "€, Demandé : " + montant + "€"
            );
        }
        solde -= montant;
    }
    
    public double getSolde() {
        return solde;
    }
}

public class TestCustomException {
    public static void main(String[] args) {
        CompteBancaire compte = new CompteBancaire(1000);
        
        try {
            compte.retirer(500);
            System.out.println("✅ Retrait réussi. Solde : " + compte.getSolde() + "€");
            
            compte.retirer(800);  // Exception
        } catch (SoldeInsuffisantException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}
```

---

### 7.2 Exception avec Informations Supplémentaires

```java
class ReservationException extends Exception {
    private String codeErreur;
    private int numeroVol;
    
    public ReservationException(String message, String codeErreur, int numeroVol) {
        super(message);
        this.codeErreur = codeErreur;
        this.numeroVol = numeroVol;
    }
    
    public String getCodeErreur() {
        return codeErreur;
    }
    
    public int getNumeroVol() {
        return numeroVol;
    }
    
    @Override
    public String toString() {
        return "ReservationException [Code: " + codeErreur + 
               ", Vol: " + numeroVol + "] " + getMessage();
    }
}

class SystemeReservation {
    public void reserverVol(int numeroVol, int places) throws ReservationException {
        if (places > 10) {
            throw new ReservationException(
                "Nombre de places dépassé",
                "ERR_MAX_PLACES",
                numeroVol
            );
        }
        System.out.println("✅ Réservation confirmée");
    }
}

public class TestReservation {
    public static void main(String[] args) {
        SystemeReservation systeme = new SystemeReservation();
        
        try {
            systeme.reserverVol(1234, 15);
        } catch (ReservationException e) {
            System.out.println("❌ " + e);
            System.out.println("Code erreur : " + e.getCodeErreur());
        }
    }
}
```

---

## 8. Bonnes Pratiques

### 8.1 ✅ À Faire

```java
// 1. Capturer des exceptions spécifiques
try {
    // Code
} catch (FileNotFoundException e) {
    // Traiter fichier introuvable
} catch (IOException e) {
    // Traiter autres erreurs I/O
}

// 2. Utiliser try-with-resources
try (FileReader reader = new FileReader("fichier.txt")) {
    // Code
} catch (IOException e) {
    // Traiter
}

// 3. Fournir des messages d'erreur clairs
throw new IllegalArgumentException("Le prix doit être positif. Valeur reçue : " + prix);
```

---

### 8.2 ❌ À Éviter

```java
// 1. Capturer Exception sans raison
try {
    // Code
} catch (Exception e) {  // Trop générique
    // Traitement
}

// 2. Bloc catch vide
try {
    // Code
} catch (IOException e) {
    // Ne rien faire = MAUVAIS
}

// 3. Afficher et relancer
try {
    // Code
} catch (IOException e) {
    e.printStackTrace();  // Log
    throw e;  // Relance = duplication
}
```

---

## 9. Exemple Complet : Gestion de Fichiers

```java
import java.io.*;
import java.util.*;

class FichierException extends Exception {
    public FichierException(String message) {
        super(message);
    }
}

class GestionnaireFichiers {
    public List<String> lireFichier(String chemin) throws FichierException {
        List<String> lignes = new ArrayList<>();
        
        // Vérifier que le fichier existe
        File fichier = new File(chemin);
        if (!fichier.exists()) {
            throw new FichierException("Fichier introuvable : " + chemin);
        }
        
        // Lire le fichier
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                lignes.add(ligne);
            }
        } catch (IOException e) {
            throw new FichierException("Erreur lors de la lecture : " + e.getMessage());
        }
        
        return lignes;
    }
    
    public void ecrireFichier(String chemin, List<String> lignes) throws FichierException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(chemin))) {
            for (String ligne : lignes) {
                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("✅ Fichier écrit avec succès");
        } catch (IOException e) {
            throw new FichierException("Échec de l'écriture : " + e.getMessage());
        }
    }
}

public class TestFichier {
    public static void main(String[] args) {
        GestionnaireFichiers gestionnaire = new GestionnaireFichiers();
        
        // Écrire un fichier
        List<String> contenu = Arrays.asList(
            "Ligne 1",
            "Ligne 2",
            "Ligne 3"
        );
        
        try {
            gestionnaire.ecrireFichier("test.txt", contenu);
            
            // Lire le fichier
            List<String> lignes = gestionnaire.lireFichier("test.txt");
            System.out.println("\nContenu du fichier :");
            lignes.forEach(System.out::println);
            
        } catch (FichierException e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Description |
|---------|-------------|
| **Exception** | Événement anormal qui interrompt le flux |
| **try-catch** | Capturer et gérer une exception |
| **finally** | Bloc toujours exécuté |
| **throw** | Lancer une exception |
| **throws** | Déclarer qu'une méthode peut lever une exception |
| **Checked** | Exception vérifiée (capture obligatoire) |
| **Unchecked** | Exception non vérifiée (RuntimeException) |

---

## 🎯 Points à Retenir

1. **try-catch** pour gérer les exceptions
2. **finally** pour le code de nettoyage
3. **throw** pour lancer, **throws** pour déclarer
4. Exceptions **checked** = capture obligatoire
5. Créer des exceptions personnalisées pour des cas métier

---

## 🎉 Fin du Cours !

Félicitations ! Vous avez terminé le cours complet de **Programmation Orientée Objet en Java**.

**Vous maîtrisez maintenant** :
- Les bases de Java
- La POO (classes, objets, héritage, polymorphisme)
- Les concepts avancés (interfaces, lambdas, streams)
- La gestion d'erreurs

**Prochaines étapes** :
- Pratiquer avec des projets personnels
- Explorer les frameworks (Spring, JavaFX, etc.)
- Contribuer à des projets open source

**Bon courage pour la suite ! 🚀**
