# Section 11 : Interfaces Fonctionnelles et Programmation Fonctionnelle

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre les expressions lambda
- Utiliser les interfaces fonctionnelles standard
- Travailler avec les Streams
- Appliquer les opérations fonctionnelles (map, filter, reduce)
- Utiliser les références de méthodes

---

## 1. Expressions Lambda

### 1.1 Définition

Une **lambda** est une fonction **anonyme** (sans nom) qu'on peut passer comme paramètre.

**Syntaxe** :
```java
(paramètres) -> expression
(paramètres) -> { instructions; }
```

---

### 1.2 Avant/Après Lambda

**Avant (Java 7)** :
```java
List<String> noms = Arrays.asList("Alice", "Bob", "Charlie");

// Classe anonyme
Collections.sort(noms, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
});
```

**Après (Java 8+)** :
```java
List<String> noms = Arrays.asList("Alice", "Bob", "Charlie");

// Lambda
Collections.sort(noms, (s1, s2) -> s1.compareTo(s2));

// Encore plus court avec référence de méthode
Collections.sort(noms, String::compareTo);
```

---

### 1.3 Syntaxes Lambda

```java
// Sans paramètre
() -> System.out.println("Hello")

// Un paramètre (parenthèses optionnelles)
x -> x * x

// Plusieurs paramètres
(a, b) -> a + b

// Bloc d'instructions
(x, y) -> {
    int somme = x + y;
    return somme * 2;
}

// Avec types explicites
(int a, int b) -> a + b
```

---

## 2. Interfaces Fonctionnelles Standard

### 2.1 Qu'est-ce qu'une Interface Fonctionnelle ?

Une interface avec **une seule méthode abstraite**.

```java
@FunctionalInterface
interface Calculateur {
    int calculer(int a, int b);
}

// Utilisation avec lambda
Calculateur addition = (a, b) -> a + b;
Calculateur multiplication = (a, b) -> a * b;

System.out.println(addition.calculer(5, 3));        // 8
System.out.println(multiplication.calculer(5, 3));  // 15
```

---

### 2.2 `Predicate<T>` : Test Booléen

**Signature** : `boolean test(T t)`

```java
import java.util.function.Predicate;
import java.util.*;

public class TestPredicate {
    public static void main(String[] args) {
        List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Prédicat : nombre pair
        Predicate<Integer> estPair = n -> n % 2 == 0;
        
        // Prédicat : nombre > 5
        Predicate<Integer> superieurA5 = n -> n > 5;
        
        // Filtrer les nombres pairs
        System.out.println("Nombres pairs :");
        nombres.stream()
               .filter(estPair)
               .forEach(System.out::println);
        
        // Filtrer les nombres pairs ET > 5
        System.out.println("\nNombres pairs > 5 :");
        nombres.stream()
               .filter(estPair.and(superieurA5))
               .forEach(System.out::println);
    }
}
```

---

### 2.3 `Function<T, R>` : Transformation

**Signature** : `R apply(T t)`

```java
import java.util.function.Function;
import java.util.*;

public class TestFunction {
    public static void main(String[] args) {
        List<String> noms = Arrays.asList("alice", "bob", "charlie");
        
        // Fonction : mettre en majuscules
        Function<String, String> majuscules = s -> s.toUpperCase();
        
        // Fonction : longueur de la chaîne
        Function<String, Integer> longueur = s -> s.length();
        
        // Transformer en majuscules
        System.out.println("Majuscules :");
        noms.stream()
            .map(majuscules)
            .forEach(System.out::println);
        
        // Obtenir les longueurs
        System.out.println("\nLongueurs :");
        noms.stream()
            .map(longueur)
            .forEach(System.out::println);
    }
}
```

---

### 2.4 `Consumer<T>` : Action Sans Retour

**Signature** : `void accept(T t)`

```java
import java.util.function.Consumer;
import java.util.*;

public class TestConsumer {
    public static void main(String[] args) {
        List<String> noms = Arrays.asList("Alice", "Bob", "Charlie");
        
        // Consumer : afficher avec préfixe
        Consumer<String> afficher = nom -> System.out.println("👤 " + nom);
        
        // Consumer : afficher en majuscules
        Consumer<String> afficherMaj = nom -> System.out.println(nom.toUpperCase());
        
        System.out.println("Avec icône :");
        noms.forEach(afficher);
        
        System.out.println("\nEn majuscules :");
        noms.forEach(afficherMaj);
    }
}
```

---

### 2.5 `Supplier<T>` : Production de Valeur

**Signature** : `T get()`

```java
import java.util.function.Supplier;
import java.util.*;

public class TestSupplier {
    public static void main(String[] args) {
        // Supplier : nombre aléatoire
        Supplier<Integer> aleatoire = () -> (int)(Math.random() * 100);
        
        // Supplier : UUID
        Supplier<String> uuid = () -> UUID.randomUUID().toString();
        
        // Générer 5 nombres aléatoires
        System.out.println("Nombres aléatoires :");
        for (int i = 0; i < 5; i++) {
            System.out.println(aleatoire.get());
        }
        
        // Générer un UUID
        System.out.println("\nUUID : " + uuid.get());
    }
}
```

---

### 2.6 `BinaryOperator<T>` : Opération sur 2 Valeurs

**Signature** : `T apply(T t1, T t2)`

```java
import java.util.function.BinaryOperator;

public class TestBinaryOperator {
    public static void main(String[] args) {
        BinaryOperator<Integer> addition = (a, b) -> a + b;
        BinaryOperator<Integer> multiplication = (a, b) -> a * b;
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        
        System.out.println("5 + 3 = " + addition.apply(5, 3));           // 8
        System.out.println("5 * 3 = " + multiplication.apply(5, 3));     // 15
        System.out.println("max(5, 3) = " + max.apply(5, 3));            // 5
    }
}
```

---

## 3. Les Streams

### 3.1 Qu'est-ce qu'un Stream ?

Un **Stream** est un flux de données sur lequel on applique des opérations.

**Caractéristiques** :
- Ne modifie PAS la collection d'origine
- Opérations chaînées
- Lazy (exécution retardée)

---

### 3.2 Créer un Stream

```java
import java.util.*;
import java.util.stream.*;

// Depuis une collection
List<Integer> liste = Arrays.asList(1, 2, 3, 4, 5);
Stream<Integer> stream1 = liste.stream();

// Depuis un tableau
Integer[] tableau = {1, 2, 3, 4, 5};
Stream<Integer> stream2 = Arrays.stream(tableau);

// Avec Stream.of()
Stream<String> stream3 = Stream.of("A", "B", "C");

// Stream infini
Stream<Integer> stream4 = Stream.iterate(0, n -> n + 2);  // 0, 2, 4, 6...
Stream<Double> stream5 = Stream.generate(Math::random);    // Nombres aléatoires
```

---

### 3.3 Opérations Intermédiaires

#### `filter()` : Filtrer

```java
List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Garder uniquement les nombres pairs
List<Integer> pairs = nombres.stream()
                             .filter(n -> n % 2 == 0)
                             .collect(Collectors.toList());

System.out.println(pairs);  // [2, 4, 6, 8, 10]
```

---

#### `map()` : Transformer

```java
List<String> noms = Arrays.asList("alice", "bob", "charlie");

// Transformer en majuscules
List<String> majuscules = noms.stream()
                               .map(String::toUpperCase)
                               .collect(Collectors.toList());

System.out.println(majuscules);  // [ALICE, BOB, CHARLIE]
```

---

#### `sorted()` : Trier

```java
List<Integer> nombres = Arrays.asList(5, 2, 8, 1, 9);

// Tri croissant
List<Integer> tries = nombres.stream()
                             .sorted()
                             .collect(Collectors.toList());

System.out.println(tries);  // [1, 2, 5, 8, 9]

// Tri décroissant
List<Integer> triesDesc = nombres.stream()
                                 .sorted(Comparator.reverseOrder())
                                 .collect(Collectors.toList());

System.out.println(triesDesc);  // [9, 8, 5, 2, 1]
```

---

#### `distinct()` : Supprimer les Doublons

```java
List<Integer> nombres = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);

List<Integer> uniques = nombres.stream()
                               .distinct()
                               .collect(Collectors.toList());

System.out.println(uniques);  // [1, 2, 3, 4, 5]
```

---

#### `limit()` et `skip()`

```java
List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Prendre les 5 premiers
List<Integer> premiers = nombres.stream()
                                .limit(5)
                                .collect(Collectors.toList());

System.out.println(premiers);  // [1, 2, 3, 4, 5]

// Sauter les 3 premiers
List<Integer> apres3 = nombres.stream()
                              .skip(3)
                              .collect(Collectors.toList());

System.out.println(apres3);  // [4, 5, 6, 7, 8, 9, 10]
```

---

### 3.4 Opérations Terminales

#### `forEach()` : Itérer

```java
List<String> noms = Arrays.asList("Alice", "Bob", "Charlie");

noms.stream()
    .forEach(nom -> System.out.println("👤 " + nom));
```

---

#### `collect()` : Collecter en Collection

```java
List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5);

// En List
List<Integer> liste = nombres.stream()
                             .filter(n -> n > 2)
                             .collect(Collectors.toList());

// En Set
Set<Integer> ensemble = nombres.stream()
                               .collect(Collectors.toSet());

// En String
String chaine = nombres.stream()
                       .map(String::valueOf)
                       .collect(Collectors.joining(", "));

System.out.println(chaine);  // "1, 2, 3, 4, 5"
```

---

#### `count()` : Compter

```java
List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

long nbPairs = nombres.stream()
                      .filter(n -> n % 2 == 0)
                      .count();

System.out.println("Nombre de pairs : " + nbPairs);  // 5
```

---

#### `reduce()` : Réduire à Une Valeur

```java
List<Integer> nombres = Arrays.asList(1, 2, 3, 4, 5);

// Somme
int somme = nombres.stream()
                   .reduce(0, (a, b) -> a + b);

System.out.println("Somme : " + somme);  // 15

// Produit
int produit = nombres.stream()
                     .reduce(1, (a, b) -> a * b);

System.out.println("Produit : " + produit);  // 120

// Maximum
Optional<Integer> max = nombres.stream()
                               .reduce((a, b) -> a > b ? a : b);

max.ifPresent(m -> System.out.println("Max : " + m));  // 5
```

---

### 3.5 Exemple Complet : Gestion d'Étudiants

```java
import java.util.*;
import java.util.stream.*;

class Etudiant {
    private String nom;
    private int age;
    private double moyenne;
    
    public Etudiant(String nom, int age, double moyenne) {
        this.nom = nom;
        this.age = age;
        this.moyenne = moyenne;
    }
    
    public String getNom() { return nom; }
    public int getAge() { return age; }
    public double getMoyenne() { return moyenne; }
    
    @Override
    public String toString() {
        return nom + " (" + age + " ans) - " + moyenne;
    }
}

public class GestionEtudiants {
    public static void main(String[] args) {
        List<Etudiant> etudiants = Arrays.asList(
            new Etudiant("Alice", 20, 15.5),
            new Etudiant("Bob", 22, 12.0),
            new Etudiant("Charlie", 21, 16.5),
            new Etudiant("David", 19, 14.0),
            new Etudiant("Eve", 23, 11.5)
        );
        
        // 1. Étudiants ayant plus de 14 de moyenne
        System.out.println("=== Étudiants avec moyenne > 14 ===");
        etudiants.stream()
                 .filter(e -> e.getMoyenne() > 14)
                 .forEach(System.out::println);
        
        // 2. Noms des étudiants triés par ordre alphabétique
        System.out.println("\n=== Noms triés ===");
        etudiants.stream()
                 .map(Etudiant::getNom)
                 .sorted()
                 .forEach(System.out::println);
        
        // 3. Moyenne générale
        double moyenneGenerale = etudiants.stream()
                                          .mapToDouble(Etudiant::getMoyenne)
                                          .average()
                                          .orElse(0.0);
        
        System.out.println("\nMoyenne générale : " + moyenneGenerale);
        
        // 4. Meilleur étudiant
        Optional<Etudiant> meilleur = etudiants.stream()
                                               .max(Comparator.comparingDouble(Etudiant::getMoyenne));
        
        meilleur.ifPresent(e -> System.out.println("Meilleur : " + e));
        
        // 5. Nombre d'étudiants majeurs (>= 21 ans)
        long nbMajeurs = etudiants.stream()
                                  .filter(e -> e.getAge() >= 21)
                                  .count();
        
        System.out.println("\nNombre d'étudiants >= 21 ans : " + nbMajeurs);
    }
}
```

---

## 📝 Résumé de la Section

| Interface | Méthode | Usage |
|-----------|---------|-------|
| `Predicate<T>` | `boolean test(T t)` | Test booléen |
| `Function<T,R>` | `R apply(T t)` | Transformation |
| `Consumer<T>` | `void accept(T t)` | Action sans retour |
| `Supplier<T>` | `T get()` | Production de valeur |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | Opération binaire |

---

## 🎯 Points à Retenir

1. **Lambda** = fonction anonyme `(params) -> expression`
2. **Streams** = flux de données, opérations chaînées
3. **filter** = filtrer, **map** = transformer, **reduce** = réduire
4. Opérations **intermédiaires** (lazy) vs **terminales**
5. Les Streams rendent le code plus **lisible** et **concis**

---

**Prochaine section** : [Section 12 : Exceptions →](../12-Exceptions/README.md)
