# Section 5 : Éléments Statiques en Java

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre la différence entre membres statiques et d'instance
- Utiliser les attributs et méthodes statiques
- Créer des constantes avec `static final`
- Maîtriser les blocs d'initialisation statiques
- Concevoir des classes utilitaires

---

## 1. Membres d'Instance vs Membres Statiques

### 1.1 Rappel : Membres d'Instance

**Membres d'instance** = Attachés à **chaque objet**

```java
class Compteur {
    int valeur;  // Attribut d'instance
    
    void incrementer() {  // Méthode d'instance
        valeur++;
    }
}

// Utilisation
Compteur c1 = new Compteur();
Compteur c2 = new Compteur();

c1.valeur = 5;
c2.valeur = 10;

System.out.println(c1.valeur);  // 5
System.out.println(c2.valeur);  // 10
// Chaque objet a sa PROPRE valeur
```

---

### 1.2 Membres Statiques : Partagés par Tous

**Membres statiques** = Attachés à **la classe** (partagés par tous les objets)

```java
class Compteur {
    static int compteurGlobal = 0;  // Attribut STATIQUE
    int valeur;                      // Attribut d'instance
    
    Compteur() {
        compteurGlobal++;  // Incrémente à chaque création
        valeur = compteurGlobal;
    }
}

// Utilisation
Compteur c1 = new Compteur();
Compteur c2 = new Compteur();
Compteur c3 = new Compteur();

System.out.println(Compteur.compteurGlobal);  // 3
System.out.println(c1.valeur);                 // 1
System.out.println(c2.valeur);                 // 2
System.out.println(c3.valeur);                 // 3
```

**Schéma mémoire** :
```
┌──────────────────────────────┐
│   CLASSE Compteur            │
│   compteurGlobal = 3  ←──────┼─── PARTAGÉ (1 seule copie)
└──────────────────────────────┘

┌──────────┐  ┌──────────┐  ┌──────────┐
│ c1       │  │ c2       │  │ c3       │
│ valeur=1 │  │ valeur=2 │  │ valeur=3 │  ← PROPRE à chaque objet
└──────────┘  └──────────┘  └──────────┘
```

---

## 2. Attributs Statiques

### 2.1 Déclaration et Accès

```java
class Configuration {
    static String nomApp = "MonAppli";
    static String version = "1.0.0";
    static int maxUtilisateurs = 100;
}

// Accès via le nom de la classe (RECOMMANDÉ)
System.out.println(Configuration.nomApp);         // "MonAppli"
System.out.println(Configuration.maxUtilisateurs); // 100

// Accès via une instance (DÉCONSEILLÉ mais possible)
Configuration config = new Configuration();
System.out.println(config.nomApp);  // Fonctionne mais mauvaise pratique
```

---

### 2.2 Exemple Pratique : Compteur d'Instances

```java
class Livre {
    static int nombreLivres = 0;  // Compteur partagé
    
    String titre;
    String auteur;
    
    Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
        nombreLivres++;  // Incrémente à chaque création
    }
    
    static int getNombreLivres() {
        return nombreLivres;
    }
}

// Utilisation
System.out.println(Livre.getNombreLivres());  // 0

Livre l1 = new Livre("1984", "Orwell");
Livre l2 = new Livre("Le Seigneur des Anneaux", "Tolkien");

System.out.println(Livre.getNombreLivres());  // 2
```

---

## 3. Méthodes Statiques

### 3.1 Syntaxe et Utilisation

```java
class MathUtils {
    // Méthode statique : calcul du carré
    static int carre(int n) {
        return n * n;
    }
    
    // Méthode statique : calcul de la factorielle
    static long factorielle(int n) {
        if (n <= 1) return 1;
        return n * factorielle(n - 1);
    }
}

// Utilisation SANS créer d'objet
System.out.println(MathUtils.carre(5));        // 25
System.out.println(MathUtils.factorielle(5));  // 120
```

---

### 3.2 Restrictions des Méthodes Statiques

❌ **Une méthode statique NE PEUT PAS** :
1. Accéder aux attributs d'instance
2. Appeler des méthodes d'instance
3. Utiliser `this`

```java
class Exemple {
    int attributInstance = 10;       // Attribut d'instance
    static int attributStatique = 20; // Attribut statique
    
    static void methodeStatique() {
        // ✅ OK : Accès à un attribut statique
        System.out.println(attributStatique);
        
        // ❌ ERREUR : Ne peut pas accéder à un attribut d'instance
        // System.out.println(attributInstance);
        
        // ❌ ERREUR : Ne peut pas utiliser this
        // System.out.println(this.attributInstance);
    }
    
    void methodeInstance() {
        // ✅ OK : Une méthode d'instance peut accéder à TOUT
        System.out.println(attributInstance);
        System.out.println(attributStatique);
    }
}
```

---

### 3.3 Exemple : Classe Utilitaire

```java
class StringUtils {
    // Méthode statique : inverser une chaîne
    static String inverser(String texte) {
        if (texte == null) return null;
        return new StringBuilder(texte).reverse().toString();
    }
    
    // Méthode statique : compter les voyelles
    static int compterVoyelles(String texte) {
        if (texte == null) return 0;
        int compteur = 0;
        String voyelles = "aeiouAEIOU";
        for (char c : texte.toCharArray()) {
            if (voyelles.indexOf(c) != -1) {
                compteur++;
            }
        }
        return compteur;
    }
}

// Utilisation
System.out.println(StringUtils.inverser("Java"));         // "avaJ"
System.out.println(StringUtils.compterVoyelles("Hello")); // 2
```

---

## 4. Constantes : `static final`

### 4.1 Déclarer une Constante

```java
class Constantes {
    // Constantes mathématiques
    public static final double PI = 3.141592653589793;
    public static final double E = 2.718281828459045;
    
    // Constantes de configuration
    public static final int MAX_TENTATIVES = 3;
    public static final String VERSION = "2.0.1";
    
    // Constantes de codes HTTP
    public static final int HTTP_OK = 200;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_ERROR = 500;
}

// Utilisation
double surface = Constantes.PI * rayon * rayon;

if (codeReponse == Constantes.HTTP_OK) {
    System.out.println("Succès");
}
```

**Convention** : Les constantes s'écrivent en **MAJUSCULES** avec `_` entre les mots.

---

### 4.2 Différence `final` vs `static final`

```java
class Exemple {
    // Constante d'instance (RARE)
    final int ID;
    
    // Constante de classe (COURANT)
    static final int MAX = 100;
    
    Exemple(int id) {
        this.ID = id;  // Peut être initialisée dans le constructeur
        // this.MAX = 200; // ❌ ERREUR : static final ne peut pas être modifié
    }
}

// Chaque objet a son propre ID final
Exemple e1 = new Exemple(1);
Exemple e2 = new Exemple(2);

// MAX est partagé
System.out.println(Exemple.MAX);  // 100
```

---

## 5. Blocs d'Initialisation Statiques

### 5.1 Syntaxe

Un **bloc statique** s'exécute **une seule fois** lors du **chargement de la classe**.

```java
class Configuration {
    static String cheminFichier;
    static int timeout;
    
    // Bloc d'initialisation statique
    static {
        System.out.println("Initialisation de la configuration...");
        cheminFichier = "/etc/config.properties";
        timeout = 30;
    }
    
    static void afficher() {
        System.out.println("Fichier : " + cheminFichier);
        System.out.println("Timeout : " + timeout + "s");
    }
}

// Utilisation
Configuration.afficher();
// Affiche d'abord : "Initialisation de la configuration..."
// Puis : "Fichier : /etc/config.properties"
//        "Timeout : 30s"
```

---

### 5.2 Ordre d'Exécution

```java
class Test {
    static int x = 10;  // ① Initialisation directe
    
    static {            // ② Bloc statique 1
        System.out.println("Bloc 1 : x = " + x);
        x = 20;
    }
    
    static {            // ③ Bloc statique 2
        System.out.println("Bloc 2 : x = " + x);
        x = 30;
    }
    
    static void afficher() {
        System.out.println("Méthode : x = " + x);
    }
}

// Première utilisation de la classe
Test.afficher();
```

**Sortie** :
```
Bloc 1 : x = 10
Bloc 2 : x = 20
Méthode : x = 30
```

---

## 6. Classes Utilitaires

### 6.1 Qu'est-ce qu'une Classe Utilitaire ?

Une classe qui contient **uniquement des méthodes statiques** et **ne doit pas être instanciée**.

**Exemples dans Java** :
- `Math` : `Math.sqrt()`, `Math.pow()`, etc.
- `Arrays` : `Arrays.sort()`, `Arrays.toString()`, etc.
- `Collections` : `Collections.sort()`, etc.

---

### 6.2 Créer une Classe Utilitaire

```java
class CalculUtils {
    // ❌ Empêcher l'instanciation
    private CalculUtils() {
        throw new AssertionError("Classe utilitaire : ne pas instancier");
    }
    
    // Méthodes statiques utilitaires
    public static boolean estPair(int n) {
        return n % 2 == 0;
    }
    
    public static boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    
    public static int pgcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

// Utilisation
System.out.println(CalculUtils.estPair(10));      // true
System.out.println(CalculUtils.estPremier(17));   // true
System.out.println(CalculUtils.pgcd(48, 18));     // 6

// ❌ ERREUR : constructeur privé
// CalculUtils util = new CalculUtils();
```

---

## 7. Exemple Complet : Gestion de Comptes Bancaires

```java
class CompteBancaire {
    // Attributs statiques
    private static int compteurComptes = 0;
    private static final double TAUX_INTERET = 0.03;  // 3%
    private static final double FRAIS_GESTION = 5.0;  // 5€
    
    // Attributs d'instance
    private int numero;
    private String titulaire;
    private double solde;
    
    // Bloc statique
    static {
        System.out.println("Système bancaire initialisé");
        System.out.println("Taux d'intérêt : " + (TAUX_INTERET * 100) + "%");
    }
    
    // Constructeur
    public CompteBancaire(String titulaire, double soldeInitial) {
        this.numero = ++compteurComptes;
        this.titulaire = titulaire;
        this.solde = soldeInitial;
    }
    
    // Méthode statique : nombre total de comptes
    public static int getNombreComptes() {
        return compteurComptes;
    }
    
    // Méthode statique : appliquer frais à tous
    public static void appliquerFraisGestion(CompteBancaire[] comptes) {
        for (CompteBancaire compte : comptes) {
            compte.solde -= FRAIS_GESTION;
        }
        System.out.println("Frais de " + FRAIS_GESTION + "€ appliqués");
    }
    
    // Méthode d'instance : appliquer intérêts
    public void appliquerInterets() {
        solde += solde * TAUX_INTERET;
    }
    
    // Méthode d'instance : afficher
    public void afficher() {
        System.out.printf("Compte %d - %s : %.2f€%n", numero, titulaire, solde);
    }
}

// Utilisation
public class TestBanque {
    public static void main(String[] args) {
        CompteBancaire c1 = new CompteBancaire("Alice", 1000);
        CompteBancaire c2 = new CompteBancaire("Bob", 2000);
        CompteBancaire c3 = new CompteBancaire("Charlie", 1500);
        
        System.out.println("Nombre de comptes : " + CompteBancaire.getNombreComptes());
        
        c1.afficher();
        c2.afficher();
        c3.afficher();
        
        // Appliquer intérêts individuellement
        c1.appliquerInterets();
        System.out.println("Après intérêts :");
        c1.afficher();
        
        // Appliquer frais à tous
        CompteBancaire[] comptes = {c1, c2, c3};
        CompteBancaire.appliquerFraisGestion(comptes);
        
        c1.afficher();
        c2.afficher();
        c3.afficher();
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Description |
|---------|-------------|
| **Attribut statique** | Partagé par toutes les instances (1 seule copie en mémoire) |
| **Méthode statique** | Appelée sur la classe, pas sur un objet |
| **`static final`** | Constante de classe (partagée et immuable) |
| **Bloc statique** | Code exécuté une fois au chargement de la classe |
| **Classe utilitaire** | Contient uniquement des méthodes statiques, constructeur privé |

---

## 🎯 Points à Retenir

1. **Statique** = attaché à la **classe**, pas aux objets
2. Accès via **`NomClasse.membre`** (recommandé)
3. Méthodes statiques **ne peuvent pas** utiliser `this` ni accéder aux membres d'instance
4. **Constantes** = `public static final TYPE NOM`
5. **Blocs statiques** s'exécutent au chargement de la classe

---

**Prochaine section** : [Section 6 : Relations entre Classes →](../06-Relations-Classes/README.md)
