# Section 3 : Java - Les Fondamentaux

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre le fonctionnement de la JVM
- Écrire et compiler votre premier programme Java
- Utiliser les types primitifs et les variables
- Manipuler les chaînes de caractères (String)
- Travailler avec les tableaux (1D et 2D)

---

## 1. Introduction à Java

### 1.1 Pourquoi Java ?

**Avantages de Java** :

✅ **Portabilité** : "Write Once, Run Anywhere" (WORA)  
✅ **Orienté Objet** : Tout est objet (ou presque)  
✅ **Robuste** : Gestion automatique de la mémoire (Garbage Collector)  
✅ **Sécurisé** : Pas de pointeurs, vérifications strictes  
✅ **Riche** : Bibliothèques standard très complètes  
✅ **Populaire** : Grande communauté, beaucoup de ressources  

---

### 1.2 La Machine Virtuelle Java (JVM)

#### Principe de fonctionnement

```
┌─────────────────────────────────────────────────┐
│  1. CODE SOURCE                                 │
│  Fichier : HelloWorld.java                      │
│                                                 │
│  public class HelloWorld {                      │
│      public static void main(String[] args) {   │
│          System.out.println("Hello World");     │
│      }                                          │
│  }                                              │
└─────────────────┬───────────────────────────────┘
                  │
                  │ javac HelloWorld.java (COMPILATION)
                  ▼
┌─────────────────────────────────────────────────┐
│  2. BYTECODE                                    │
│  Fichier : HelloWorld.class                     │
│                                                 │
│  CA FE BA BE 00 00 00 34 00 1D 0A 00 06 ...    │
│  (Code intermédiaire indépendant de la machine) │
└─────────────────┬───────────────────────────────┘
                  │
                  │ java HelloWorld (EXÉCUTION)
                  ▼
┌─────────────────────────────────────────────────┐
│  3. JVM (Java Virtual Machine)                  │
│  ┌───────────────────────────────────────────┐ │
│  │ Windows JVM │ Linux JVM │ Mac JVM         │ │
│  └───────────────────────────────────────────┘ │
│  Interprète le bytecode pour votre OS          │
└─────────────────┬───────────────────────────────┘
                  │
                  ▼
            [RÉSULTAT]
          Hello World
```

---

#### Avantage : Portabilité

```
Un seul fichier .class fonctionne sur :

🪟 Windows   🐧 Linux   🍎 macOS   ☕ Serveurs   📱 Android

=> Tant que la JVM est installée !
```

---

## 2. Premier Programme Java

### 2.1 HelloWorld.java

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

---

### 2.2 Décortiquons le code

```java
public class HelloWorld {      // ① Déclaration de la classe
    
    public static void main(String[] args) {  // ② Méthode principale
        
        System.out.println("Hello, World!");  // ③ Instruction
    }
}
```

**Explications** :

| Ligne | Explication |
|-------|-------------|
| **①** `public class HelloWorld` | Déclare une classe publique nommée `HelloWorld`. Le nom du fichier DOIT être `HelloWorld.java` |
| **②** `public static void main(...)` | Point d'entrée du programme. C'est la méthode appelée au démarrage |
| **③** `System.out.println(...)` | Affiche un message dans la console |

---

### 2.3 Compiler et Exécuter

#### Étape 1 : Compilation
```bash
javac HelloWorld.java
```

**Résultat** : Crée `HelloWorld.class` (bytecode)

#### Étape 2 : Exécution
```bash
java HelloWorld
```

**Résultat** :
```
Hello, World!
```

⚠️ **Attention** : On écrit `java HelloWorld` (sans `.class`)

---

## 3. Types Primitifs en Java

### 3.1 Les 8 Types Primitifs

| Type | Taille | Plage de valeurs | Exemple |
|------|--------|------------------|----------|
| `byte` | 8 bits | -128 à 127 | `byte age = 25;` |
| `short` | 16 bits | -32 768 à 32 767 | `short annee = 2024;` |
| `int` | 32 bits | -2 milliards à +2 milliards | `int population = 8000000;` |
| `long` | 64 bits | Très grand | `long distance = 9460730472580800L;` |
| `float` | 32 bits | ~6-7 chiffres décimaux | `float prix = 19.99f;` |
| `double` | 64 bits | ~15 chiffres décimaux | `double pi = 3.141592653589793;` |
| `char` | 16 bits | 1 caractère Unicode | `char lettre = 'A';` |
| `boolean` | 1 bit | `true` ou `false` | `boolean estMajeur = true;` |

---

### 3.2 Déclaration et Initialisation

```java
// Déclaration simple
int nombre;

// Déclaration avec initialisation
int age = 25;

// Déclarations multiples
int x = 5, y = 10, z = 15;

// ⚠️ ERREUR : variable non initialisée
int a;
System.out.println(a);  // ❌ Erreur de compilation
```

---

### 3.3 Constantes

```java
// Déclaration d'une constante avec final
final double PI = 3.14159;
final int MAX_UTILISATEURS = 100;

// ❌ ERREUR : impossible de modifier une constante
PI = 3.14;  // Erreur de compilation
```

**Convention** : Les constantes s'écrivent en MAJUSCULES avec `_`

---

### 3.4 Conversions de Types (Casting)

#### Conversion implicite (automatique)
```java
int a = 100;
double b = a;  // ✅ OK : int → double (pas de perte)
System.out.println(b);  // 100.0
```

#### Conversion explicite (cast)
```java
double x = 9.8;
int y = (int) x;  // ⚠️ Cast nécessaire : double → int (perte de précision)
System.out.println(y);  // 9 (partie décimale perdue)
```

---

## 4. Opérateurs en Java

### 4.1 Opérateurs Arithmétiques

```java
int a = 10, b = 3;

System.out.println(a + b);  // 13 (addition)
System.out.println(a - b);  // 7  (soustraction)
System.out.println(a * b);  // 30 (multiplication)
System.out.println(a / b);  // 3  (division entière)
System.out.println(a % b);  // 1  (modulo/reste)

// Division avec double
double c = 10.0 / 3.0;  // 3.3333...
```

---

### 4.2 Opérateurs d'Incrémentation

```java
int compteur = 5;

// Post-incrémentation
int x = compteur++;  // x = 5, compteur = 6

// Pré-incrémentation
int y = ++compteur;  // y = 7, compteur = 7

// Décrémentation
compteur--;  // compteur = 6
```

---

### 4.3 Opérateurs de Comparaison

```java
int a = 10, b = 20;

System.out.println(a == b);  // false (égal à)
System.out.println(a != b);  // true  (différent de)
System.out.println(a < b);   // true  (inférieur à)
System.out.println(a > b);   // false (supérieur à)
System.out.println(a <= b);  // true  (inférieur ou égal)
System.out.println(a >= b);  // false (supérieur ou égal)
```

---

### 4.4 Opérateurs Logiques

```java
boolean estMajeur = true;
boolean aPermis = false;

// ET logique (&&)
boolean peutConduire = estMajeur && aPermis;  // false

// OU logique (||)
boolean peutEntrer = estMajeur || aPermis;  // true

// NON logique (!)
boolean estMineur = !estMajeur;  // false
```

---

## 5. Structures de Contrôle

### 5.1 If / Else

```java
int note = 15;

if (note >= 16) {
    System.out.println("Très bien");
} else if (note >= 14) {
    System.out.println("Bien");
} else if (note >= 12) {
    System.out.println("Assez bien");
} else if (note >= 10) {
    System.out.println("Passable");
} else {
    System.out.println("Insuffisant");
}
```

---

### 5.2 Switch

```java
int jour = 3;
String nomJour;

switch (jour) {
    case 1:
        nomJour = "Lundi";
        break;
    case 2:
        nomJour = "Mardi";
        break;
    case 3:
        nomJour = "Mercredi";
        break;
    case 4:
        nomJour = "Jeudi";
        break;
    case 5:
        nomJour = "Vendredi";
        break;
    case 6:
    case 7:
        nomJour = "Week-end";
        break;
    default:
        nomJour = "Jour invalide";
}

System.out.println(nomJour);  // Mercredi
```

⚠️ **Important** : N'oubliez pas les `break` !

---

### 5.3 Boucle For

```java
// Afficher les nombres de 1 à 10
for (int i = 1; i <= 10; i++) {
    System.out.println(i);
}

// Table de multiplication de 5
for (int i = 1; i <= 10; i++) {
    System.out.println("5 x " + i + " = " + (5 * i));
}
```

---

### 5.4 Boucle While

```java
int compteur = 1;

while (compteur <= 5) {
    System.out.println("Compteur : " + compteur);
    compteur++;
}
```

---

### 5.5 Boucle Do-While

```java
int nombre;
Scanner scanner = new Scanner(System.in);

do {
    System.out.print("Entrez un nombre positif : ");
    nombre = scanner.nextInt();
} while (nombre <= 0);

System.out.println("Vous avez saisi : " + nombre);
```

---

## 6. Chaînes de Caractères (String)

### 6.1 Déclaration et Initialisation

```java
// Méthode 1 : Littéral
String nom = "Alice";

// Méthode 2 : Avec new
String prenom = new String("Bob");

// Chaîne vide
String vide = "";

// Concaténation
String message = "Bonjour " + nom + " !";
System.out.println(message);  // Bonjour Alice !
```

---

### 6.2 Méthodes Courantes

#### Longueur et Caractères
```java
String texte = "Java";

System.out.println(texte.length());      // 4
System.out.println(texte.charAt(0));     // 'J'
System.out.println(texte.charAt(3));     // 'a'
```

#### Extraction de Sous-chaînes
```java
String phrase = "Apprendre Java";

System.out.println(phrase.substring(0, 9));   // "Apprendre"
System.out.println(phrase.substring(10));     // "Java"
```

#### Changement de Casse
```java
String texte = "Hello World";

System.out.println(texte.toUpperCase());  // "HELLO WORLD"
System.out.println(texte.toLowerCase());  // "hello world"
```

#### Comparaison
```java
String s1 = "Java";
String s2 = "java";

System.out.println(s1.equals(s2));             // false
System.out.println(s1.equalsIgnoreCase(s2));  // true

// ❌ ERREUR FRÉQUENTE : Ne pas utiliser ==
System.out.println(s1 == s2);  // Comparaison de références, pas de contenu
```

#### Recherche
```java
String texte = "Java est génial";

System.out.println(texte.contains("Java"));       // true
System.out.println(texte.startsWith("Java"));    // true
System.out.println(texte.endsWith("génial"));    // true
System.out.println(texte.indexOf("est"));        // 5
```

#### Remplacement
```java
String texte = "Java est cool";
String nouveau = texte.replace("cool", "génial");
System.out.println(nouveau);  // "Java est génial"
```

#### Nettoyage
```java
String texte = "  Bonjour  ";
System.out.println(texte.trim());  // "Bonjour" (espaces supprimés)
```

#### Division en Tableau
```java
String phrase = "Java,Python,C++";
String[] langages = phrase.split(",");

for (String langage : langages) {
    System.out.println(langage);
}
// Java
// Python
// C++
```

---

### 6.3 String est Immuable

```java
String original = "Java";
original.toUpperCase();  // ⚠️ Ne modifie PAS original
System.out.println(original);  // "Java" (inchangé)

// ✅ Il faut récupérer le résultat
String majuscule = original.toUpperCase();
System.out.println(majuscule);  // "JAVA"
```

---

## 7. Tableaux (Arrays)

### 7.1 Tableaux à Une Dimension

#### Déclaration et Initialisation

```java
// Méthode 1 : Déclaration puis allocation
int[] nombres;
nombres = new int[5];  // Tableau de 5 entiers

// Méthode 2 : Déclaration + allocation
int[] ages = new int[10];

// Méthode 3 : Initialisation avec valeurs
int[] notes = {15, 18, 12, 14, 16};

// Alternative (syntaxe équivalente)
int notes2[] = {10, 12, 14};  // Moins recommandé
```

---

#### Accès aux Éléments

```java
int[] nombres = {10, 20, 30, 40, 50};

// Lecture
System.out.println(nombres[0]);  // 10 (premier élément)
System.out.println(nombres[4]);  // 50 (dernier élément)

// Modification
nombres[2] = 35;
System.out.println(nombres[2]);  // 35

// Longueur du tableau
System.out.println(nombres.length);  // 5

// ❌ ERREUR : Index hors limites
System.out.println(nombres[10]);  // ArrayIndexOutOfBoundsException
```

---

#### Parcourir un Tableau

**Méthode 1 : Boucle for classique**
```java
int[] notes = {15, 18, 12, 14, 16};

for (int i = 0; i < notes.length; i++) {
    System.out.println("Note " + (i+1) + " : " + notes[i]);
}
```

**Méthode 2 : For-each (recommandé pour lecture)**
```java
int[] notes = {15, 18, 12, 14, 16};

for (int note : notes) {
    System.out.println(note);
}
```

---

#### Exemple : Calculer la Moyenne

```java
int[] notes = {15, 18, 12, 14, 16};
int somme = 0;

// Calculer la somme
for (int note : notes) {
    somme += note;
}

// Calculer la moyenne
double moyenne = (double) somme / notes.length;
System.out.println("Moyenne : " + moyenne);  // 15.0
```

---

### 7.2 Tableaux à Deux Dimensions (Matrices)

#### Déclaration et Initialisation

```java
// Méthode 1 : Déclaration + allocation
int[][] matrice = new int[3][4];  // 3 lignes, 4 colonnes

// Méthode 2 : Initialisation directe
int[][] grille = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Tableau irrégulier (lignes de tailles différentes)
int[][] irregular = {
    {1, 2},
    {3, 4, 5, 6},
    {7}
};
```

---

#### Accès aux Éléments

```java
int[][] matrice = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Lecture
System.out.println(matrice[0][0]);  // 1 (ligne 0, colonne 0)
System.out.println(matrice[1][2]);  // 6 (ligne 1, colonne 2)

// Modification
matrice[2][1] = 99;
System.out.println(matrice[2][1]);  // 99

// Dimensions
System.out.println("Lignes : " + matrice.length);       // 3
System.out.println("Colonnes : " + matrice[0].length);  // 3
```

---

#### Parcourir une Matrice

**Méthode 1 : Double boucle for**
```java
int[][] matrice = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

for (int i = 0; i < matrice.length; i++) {
    for (int j = 0; j < matrice[i].length; j++) {
        System.out.print(matrice[i][j] + " ");
    }
    System.out.println();  // Nouvelle ligne
}
```

**Résultat** :
```
1 2 3 
4 5 6 
7 8 9 
```

**Méthode 2 : For-each imbriqué**
```java
for (int[] ligne : matrice) {
    for (int valeur : ligne) {
        System.out.print(valeur + " ");
    }
    System.out.println();
}
```

---

## 📝 Résumé de la Section

| Concept | Points clés |
|---------|-------------|
| **JVM** | Compile en bytecode → Portabilité |
| **Types primitifs** | 8 types (int, double, boolean, etc.) |
| **String** | Immuable, nombreuses méthodes utiles |
| **Tableaux** | Taille fixe, index commence à 0 |
| **Tableaux 2D** | Matrice : `type[][] nom` |

---

## 🎯 Points à Retenir

1. Java est **portable** grâce à la JVM
2. Toute variable locale doit être **initialisée**
3. Pour comparer des String, utiliser **`.equals()`** (pas `==`)
4. Les tableaux ont une taille **fixe** définie à la création
5. L'index d'un tableau va de **0 à length-1**

---

**Prochaine section** : [Section 4 : Classes et Objets en Java →](../04-Classes-Objets/README.md)
