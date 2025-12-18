# Exercice 1 : Classe Utilitaire Mathématique

## 🎯 Objectif

Créer une classe utilitaire `MathPlus` avec des méthodes statiques pour des opérations mathématiques avancées.

---

## 📋 Énoncé

Créez une classe `MathPlus` (similaire à `java.lang.Math`) contenant uniquement des méthodes statiques.

---

## 📝 Fonctionnalités à implémenter

### 1. Constructeur

```java
// Constructeur privé pour empêcher l'instanciation
private MathPlus() {
    throw new AssertionError("Classe utilitaire");
}
```

### 2. Constantes

```java
public static final double NOMBRE_OR = 1.618033988749895;  // Phi (φ)
public static final double RACINE_2 = 1.41421356237;       // √2
public static final double RACINE_3 = 1.73205080757;       // √3
```

### 3. Méthodes à implémenter

#### A. Nombres de Fibonacci

```java
/**
 * Calcule le nième nombre de Fibonacci
 * Suite : 0, 1, 1, 2, 3, 5, 8, 13, 21...
 * @param n Position dans la suite (n >= 0)
 * @return Le nième nombre de Fibonacci
 */
public static long fibonacci(int n)
```

#### B. Nombre Premier

```java
/**
 * Vérifie si un nombre est premier
 * @param n Le nombre à tester
 * @return true si n est premier, false sinon
 */
public static boolean estPremier(int n)

/**
 * Trouve le prochain nombre premier après n
 * @param n Nombre de départ
 * @return Le plus petit nombre premier > n
 */
public static int prochainPremier(int n)
```

#### C. Combinatoire

```java
/**
 * Calcule le coefficient binomial C(n, k) = n! / (k! * (n-k)!)
 * Nombre de façons de choisir k éléments parmi n
 * @param n Nombre total d'éléments
 * @param k Nombre d'éléments à choisir
 * @return C(n, k)
 */
public static long coefficientBinomial(int n, int k)
```

#### D. Statistiques

```java
/**
 * Calcule la médiane d'un tableau
 * @param nombres Tableau de nombres
 * @return La médiane
 */
public static double mediane(double[] nombres)

/**
 * Calcule l'écart-type d'un tableau
 * @param nombres Tableau de nombres
 * @return L'écart-type
 */
public static double ecartType(double[] nombres)
```

#### E. Géométrie

```java
/**
 * Calcule la distance entre deux points (x1,y1) et (x2,y2)
 * Distance = √((x2-x1)² + (y2-y1)²)
 */
public static double distance(double x1, double y1, double x2, double y2)

/**
 * Calcule l'aire d'un trapèze
 * Aire = (petiteBase + grandeBase) * hauteur / 2
 */
public static double aireTrapeze(double petiteBase, double grandeBase, double hauteur)
```

---

## 💡 Indications

### Fibonacci (Méthode itérative)

```java
// F(0) = 0
// F(1) = 1
// F(n) = F(n-1) + F(n-2)

// Exemple :
// F(6) = 8
// Suite : 0, 1, 1, 2, 3, 5, 8
```

### Médiane

```java
// 1. Trier le tableau
// 2. Si taille impaire : élément du milieu
// 3. Si taille paire : moyenne des deux éléments du milieu

// Exemple : [1, 3, 5, 7, 9] → médiane = 5
//           [1, 3, 5, 7] → médiane = (3+5)/2 = 4
```

### Écart-type

```java
// 1. Calculer la moyenne
// 2. Calculer la somme des carrés des écarts à la moyenne
// 3. Diviser par n
// 4. Prendre la racine carrée

// Formule : √( Σ(xi - moyenne)² / n )
```

---

## ✅ Programme de test

Créer une classe `TestMathPlus` qui teste toutes les méthodes :

```java
public class TestMathPlus {
    public static void main(String[] args) {
        // Tester Fibonacci
        System.out.println("=== FIBONACCI ===");
        for (int i = 0; i <= 10; i++) {
            System.out.println("F(" + i + ") = " + MathPlus.fibonacci(i));
        }
        
        // Tester nombres premiers
        // ...
        
        // Tester statistiques
        // ...
    }
}
```

### Sortie attendue

```
=== CONSTANTES ===
Nombre d'or : 1.618033988749895
Racine de 2 : 1.41421356237

=== FIBONACCI ===
F(0) = 0
F(1) = 1
F(2) = 1
F(3) = 2
F(10) = 55

=== NOMBRES PREMIERS ===
17 est premier ? true
Prochain premier après 20 : 23

=== COMBINATOIRE ===
C(5, 2) = 10
C(10, 3) = 120

=== STATISTIQUES ===
Tableau : [2, 4, 6, 8, 10]
Médiane : 6.0
Écart-type : 2.83

=== GÉOMÉTRIE ===
Distance (0,0) → (3,4) : 5.0
Aire trapèze (3, 7, 4) : 20.0
```

---

## 🎯 Critères de validation

- [ ] Constructeur privé qui lance une exception
- [ ] 3 constantes déclarées (public static final)
- [ ] Méthode `fibonacci(n)` correcte
- [ ] Méthode `estPremier(n)` correcte
- [ ] Méthode `prochainPremier(n)` correcte
- [ ] Méthode `coefficientBinomial(n,k)` correcte
- [ ] Méthode `mediane(tableau)` correcte
- [ ] Méthode `ecartType(tableau)` correcte
- [ ] Méthode `distance(...)` correcte
- [ ] Méthode `aireTrapeze(...)` correcte
- [ ] Programme de test complet
- [ ] Toutes les méthodes sont statiques
- [ ] Validation des paramètres (ex: n >= 0)

---

## 🚀 Bonus

1. Ajouter `factorielleGrande(n)` qui utilise `BigInteger` pour très grands nombres
2. Ajouter `suite Premiers(n)` qui retourne les n premiers nombres premiers
3. Ajouter `variance(tableau)` (carré de l'écart-type)

---

**Bon courage ! 🔢**
