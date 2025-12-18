# Section 5 : Éléments Statiques en Java

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre la différence entre membres statiques et d'instance
- Utiliser les attributs et méthodes statiques
- Définir des constantes avec `static final`
- Créer des blocs d'initialisation statiques
- Maîtriser les classes utilitaires

---

## 1. Principe des Éléments Statiques

### 1.1 Membres d'Instance vs Membres Statiques

**Membres d'instance** : Appartiennent à chaque objet individuellement

```
┌─────────────────┐    ┌─────────────────┐
│   Objet 1       │    │   Objet 2       │
├─────────────────┤    ├─────────────────┤
│ nom: "Alice"    │    │ nom: "Bob"      │
│ age: 25         │    │ age: 30         │
└─────────────────┘    └─────────────────┘
     Valeurs              Valeurs
   différentes          différentes
```

**Membres statiques** : Appartiennent à la classe, partagés par tous les objets

```
     ┌──────────────────────────┐
     │   CLASSE Compteur        │
     │  ┌────────────────────┐  │
     │  │ compteur (static)  │  │ ← Partagé par tous
     │  │      = 5           │  │
     │  └────────────────────┘  │
     └──────────────────────────┘
              ↑        ↑
              │        │
    ┌─────────┴───┐   │
    │ Objet 1     │   │
    └─────────────┘   │
                      │
            ┌─────────┴───┐
            │ Objet 2     │
            └─────────────┘
```

---

## 2. Attributs Statiques

### 2.1 Déclaration

```java
class Compteur {
    static int total = 0;  // Attribut statique
    int valeur;            // Attribut d'instance
    
    Compteur() {
        total++;           // Incrémente le compteur partagé
        valeur = total;    // Stocke la valeur actuelle
    }
    
    void afficher() {
        System.out.println("Je suis le compteur n°" + valeur);
        System.out.println("Total de compteurs : " + total);
    }
}
```

---

### 2.2 Utilisation

```java
public class TestCompteur {
    public static void main(String[] args) {
        System.out.println("Total initial : " + Compteur.total);  // 0
        
        Compteur c1 = new Compteur();
        c1.afficher();
        // Je suis le compteur n°1
        // Total de compteurs : 1
        
        Compteur c2 = new Compteur();
        c2.afficher();
        // Je suis le compteur n°2
        // Total de compteurs : 2
        
        Compteur c3 = new Compteur();
        
        // ✅ Accès via la classe (recommandé)
        System.out.println("Total : " + Compteur.total);  // 3
        
        // ⚠️ Accès via une instance (possible mais déconseillé)
        System.out.println("Total : " + c1.total);  // 3 (même valeur)
    }
}
```

---

### 2.3 Exemple Pratique : Compteur d'Utilisateurs

```java
class Utilisateur {
    static int nombreUtilisateurs = 0;  // Compteur partagé
    
    String nom;
    int id;
    
    Utilisateur(String nom) {
        nombreUtilisateurs++;
        this.id = nombreUtilisateurs;
        this.nom = nom;
    }
    
    static int getNombreUtilisateurs() {
        return nombreUtilisateurs;
    }
    
    void afficher() {
        System.out.println("Utilisateur #" + id + " : " + nom);
    }
}
```

**Test** :
```java
Utilisateur u1 = new Utilisateur("Alice");
Utilisateur u2 = new Utilisateur("Bob");
Utilisateur u3 = new Utilisateur("Charlie");

u1.afficher();  // Utilisateur #1 : Alice
u2.afficher();  // Utilisateur #2 : Bob
u3.afficher();  // Utilisateur #3 : Charlie

System.out.println("Total : " + Utilisateur.getNombreUtilisateurs());  // 3
```

---

## 3. Constantes : `static final`

### 3.1 Déclarer des Constantes

```java
class Configuration {
    // Constantes : static final
    public static final double PI = 3.141592653589793;
    public static final int MAX_CONNEXIONS = 100;
    public static final String VERSION = "1.0.0";
    public static final double TAUX_TVA = 0.20;
}
```

**Convention** : Constantes en MAJUSCULES avec `_`

---

### 3.2 Utilisation

```java
public class TestConfig {
    public static void main(String[] args) {
        double rayon = 5.0;
        double aire = Configuration.PI * rayon * rayon;
        System.out.println("Aire : " + aire);
        
        double prixHT = 100.0;
        double prixTTC = prixHT * (1 + Configuration.TAUX_TVA);
        System.out.println("Prix TTC : " + prixTTC + "€");
        
        // ❌ ERREUR : impossible de modifier une constante
        // Configuration.PI = 3.14;  // Erreur de compilation
    }
}
```

---

### 3.3 Exemple : Classe Math Personnalisée

```java
class MathUtils {
    // Constantes mathématiques
    public static final double E = 2.718281828459045;
    public static final double PHI = 1.618033988749895;  // Nombre d'or
    
    // Constantes de conversion
    public static final double KM_VERS_MILES = 0.621371;
    public static final double KG_VERS_POUNDS = 2.20462;
    
    // Méthodes statiques (voir section suivante)
    public static double convertirKmEnMiles(double km) {
        return km * KM_VERS_MILES;
    }
}
```

---

## 4. Méthodes Statiques

### 4.1 Déclaration et Utilisation

```java
class Calculatrice {
    // Méthode statique
    static int additionner(int a, int b) {
        return a + b;
    }
    
    static double pourcentage(double valeur, double taux) {
        return valeur * taux / 100;
    }
    
    static int maximum(int a, int b) {
        return (a > b) ? a : b;
    }
}
```

**Utilisation** :
```java
// Appel SANS créer d'objet
int somme = Calculatrice.additionner(5, 3);  // 8

double remise = Calculatrice.pourcentage(100, 15);  // 15.0

int max = Calculatrice.maximum(10, 25);  // 25
```

---

### 4.2 Restrictions des Méthodes Statiques

❌ **Une méthode statique NE PEUT PAS** :
- Accéder directement à des attributs d'instance
- Appeler des méthodes d'instance
- Utiliser `this`

```java
class Exemple {
    int valeur = 10;              // Attribut d'instance
    static int compteur = 0;      // Attribut statique
    
    void methodeInstance() {
        System.out.println("Instance");
    }
    
    static void methodeStatique() {
        // ❌ ERREUR : accès à un attribut d'instance
        // System.out.println(valeur);
        
        // ❌ ERREUR : appel d'une méthode d'instance
        // methodeInstance();
        
        // ❌ ERREUR : utilisation de this
        // this.compteur = 5;
        
        // ✅ OK : accès à un attribut statique
        compteur = 5;
        System.out.println(compteur);
    }
}
```

---

### 4.3 Exemple : Classe Utilitaire StringUtils

```java
class StringUtils {
    // Méthode statique : inverser une chaîne
    static String inverser(String texte) {
        StringBuilder sb = new StringBuilder(texte);
        return sb.reverse().toString();
    }
    
    // Méthode statique : compter les voyelles
    static int compterVoyelles(String texte) {
        int count = 0;
        String voyelles = "aeiouAEIOU";
        for (int i = 0; i < texte.length(); i++) {
            if (voyelles.indexOf(texte.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }
    
    // Méthode statique : capitaliser
    static String capitaliser(String texte) {
        if (texte == null || texte.isEmpty()) {
            return texte;
        }
        return texte.substring(0, 1).toUpperCase() + texte.substring(1).toLowerCase();
    }
}
```

**Test** :
```java
System.out.println(StringUtils.inverser("Java"));        // avaJ
System.out.println(StringUtils.compterVoyelles("Hello")); // 2
System.out.println(StringUtils.capitaliser("jAvA"));      // Java
```

---

## 5. Blocs d'Initialisation Statiques

### 5.1 Syntaxe

```java
class Configuration {
    static String databaseUrl;
    static int maxConnections;
    
    // Bloc d'initialisation statique
    static {
        System.out.println("Initialisation de la configuration...");
        databaseUrl = "jdbc:mysql://localhost:3306/mydb";
        maxConnections = 50;
    }
    
    static void afficherConfig() {
        System.out.println("URL : " + databaseUrl);
        System.out.println("Max connexions : " + maxConnections);
    }
}
```

**Utilisation** :
```java
public class Test {
    public static void main(String[] args) {
        // Le bloc statique s'exécute AU PREMIER accès à la classe
        Configuration.afficherConfig();
        // Sortie :
        // Initialisation de la configuration...
        // URL : jdbc:mysql://localhost:3306/mydb
        // Max connexions : 50
    }
}
```

---

### 5.2 Ordre d'Exécution

```java
class Test {
    static int a = initialiserA();
    
    static {
        System.out.println("Bloc statique 1");
    }
    
    static int b = initialiserB();
    
    static {
        System.out.println("Bloc statique 2");
    }
    
    static int initialiserA() {
        System.out.println("Initialisation de a");
        return 10;
    }
    
    static int initialiserB() {
        System.out.println("Initialisation de b");
        return 20;
    }
    
    public static void main(String[] args) {
        System.out.println("main()");
    }
}
```

**Sortie** :
```
Initialisation de a
Bloc statique 1
Initialisation de b
Bloc statique 2
main()
```

**Ordre** : De haut en bas dans l'ordre de déclaration

---

## 6. Classe avec Uniquement des Membres Statiques

### 6.1 Classe Utilitaire

Une **classe utilitaire** contient uniquement des méthodes statiques.

```java
class MathUtils {
    // Constructeur privé : empêche l'instanciation
    private MathUtils() {
        throw new AssertionError("Cette classe ne doit pas être instanciée");
    }
    
    // Constantes
    public static final double PI = 3.141592653589793;
    public static final double E = 2.718281828459045;
    
    // Méthodes utilitaires
    public static double carre(double x) {
        return x * x;
    }
    
    public static double cube(double x) {
        return x * x * x;
    }
    
    public static int factorielle(int n) {
        if (n <= 1) return 1;
        return n * factorielle(n - 1);
    }
    
    public static boolean estPair(int n) {
        return n % 2 == 0;
    }
}
```

---

### 6.2 Exemple Complet : Classe DateUtils

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DateUtils {
    private DateUtils() {}  // Empêche l'instanciation
    
    // Formats de date constants
    public static final DateTimeFormatter FORMAT_FR = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMAT_US = 
        DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    // Méthode : formater une date
    public static String formaterDate(LocalDate date, DateTimeFormatter format) {
        return date.format(format);
    }
    
    // Méthode : obtenir la date du jour formatée
    public static String aujourdhuiFR() {
        return LocalDate.now().format(FORMAT_FR);
    }
    
    // Méthode : calculer l'âge
    public static int calculerAge(LocalDate dateNaissance) {
        return LocalDate.now().getYear() - dateNaissance.getYear();
    }
}
```

**Test** :
```java
System.out.println("Aujourd'hui : " + DateUtils.aujourdhuiFR());

LocalDate naissance = LocalDate.of(1990, 5, 15);
int age = DateUtils.calculerAge(naissance);
System.out.println("Âge : " + age + " ans");
```

---

## 7. Méthode main() : Point d'Entrée

La méthode `main()` est **statique** car elle doit être appelée SANS créer d'objet.

```java
public class MonProgramme {
    // Point d'entrée du programme
    public static void main(String[] args) {
        System.out.println("Démarrage du programme");
        
        // Accès à des méthodes statiques
        afficherBienvenue();
        
        // Création d'objets
        MonProgramme programme = new MonProgramme();
        programme.executer();
    }
    
    // Méthode statique (peut être appelée depuis main)
    static void afficherBienvenue() {
        System.out.println("Bienvenue !");
    }
    
    // Méthode d'instance
    void executer() {
        System.out.println("Exécution en cours...");
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Définition | Accès |
|---------|------------|-------|
| **Attribut statique** | Partagé par tous les objets | `Classe.attribut` |
| **Méthode statique** | Fonction sans instance | `Classe.methode()` |
| **Constante** | `static final` | `Classe.CONSTANTE` |
| **Bloc statique** | Initialisation au chargement | Exécution automatique |
| **Classe utilitaire** | Uniquement membres statiques | Constructeur privé |

---

## 🎯 Points à Retenir

1. Les membres **statiques** appartiennent à la **classe**, pas aux objets
2. Accès recommandé : `NomClasse.membre` (pas via instance)
3. Constantes : `public static final` en MAJUSCULES
4. Méthodes statiques : **pas d'accès** aux membres d'instance
5. Classe utilitaire : constructeur **privé** + membres **statiques**

---

**Prochaine section** : [Section 6 : Relations entre Classes →](../06-Relations-Classes/README.md)
