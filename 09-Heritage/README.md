# Section 9 : Héritage

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre le concept d'héritage
- Utiliser le mot-clé `extends`
- Redéfinir des méthodes avec `@Override`
- Utiliser `super` pour accéder à la classe parent
- Comprendre le modificateur `final`
- Modéliser des hiérarchies de classes

---

## 1. Qu'est-ce que l'Héritage ?

### 1.1 Définition

**Héritage** = Mécanisme permettant à une classe de **réutiliser** les attributs et méthodes d'une autre classe.

**Relation "EST-UN"** :
```
Chien EST-UN Animal
Voiture EST-UN Véhicule
Étudiant EST-UNE Personne
```

**Vocabulaire** :
- **Classe mère** (parent, super-classe) : classe de base
- **Classe fille** (enfant, sous-classe) : classe qui hérite

---

### 1.2 Syntaxe

```java
class ClasseFille extends ClasseMere {
    // La classe fille hérite de tous les attributs et méthodes
    // publics et protected de la classe mère
}
```

---

## 2. Exemple Simple : Animal

### 2.1 Classe Mère

```java
class Animal {
    protected String nom;
    protected int age;
    
    public Animal(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }
    
    public void manger() {
        System.out.println(nom + " mange");
    }
    
    public void dormir() {
        System.out.println(nom + " dort");
    }
    
    public void afficher() {
        System.out.println("Nom : " + nom + ", Âge : " + age + " ans");
    }
}
```

---

### 2.2 Classes Filles

```java
class Chien extends Animal {
    private String race;
    
    public Chien(String nom, int age, String race) {
        super(nom, age);  // Appel du constructeur de Animal
        this.race = race;
    }
    
    // Méthode spécifique au chien
    public void aboyer() {
        System.out.println(nom + " aboie : Woof!");
    }
    
    @Override
    public void afficher() {
        super.afficher();  // Appel de la méthode de Animal
        System.out.println("Race : " + race);
    }
}

class Chat extends Animal {
    private String couleur;
    
    public Chat(String nom, int age, String couleur) {
        super(nom, age);
        this.couleur = couleur;
    }
    
    public void miauler() {
        System.out.println(nom + " miaule : Miaou!");
    }
    
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Couleur : " + couleur);
    }
}
```

---

### 2.3 Utilisation

```java
public class TestAnimal {
    public static void main(String[] args) {
        Chien chien = new Chien("Rex", 5, "Berger Allemand");
        Chat chat = new Chat("Minou", 3, "Noir");
        
        // Méthodes héritées de Animal
        chien.manger();  // "Rex mange"
        chien.dormir();  // "Rex dort"
        chat.manger();   // "Minou mange"
        
        // Méthodes spécifiques
        chien.aboyer();  // "Rex aboie : Woof!"
        chat.miauler();  // "Minou miaule : Miaou!"
        
        // Méthodes redéfinies
        System.out.println("\n=== Chien ===");
        chien.afficher();
        
        System.out.println("\n=== Chat ===");
        chat.afficher();
    }
}
```

**Sortie** :
```
Rex mange
Rex dort
Minou mange
Rex aboie : Woof!
Minou miaule : Miaou!

=== Chien ===
Nom : Rex, Âge : 5 ans
Race : Berger Allemand

=== Chat ===
Nom : Minou, Âge : 3 ans
Couleur : Noir
```

---

## 3. Le Mot-Clé `super`

### 3.1 Appeler le Constructeur Parent

```java
class Personne {
    protected String nom;
    protected int age;
    
    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }
}

class Etudiant extends Personne {
    private String matricule;
    
    public Etudiant(String nom, int age, String matricule) {
        super(nom, age);  // ✅ Appel du constructeur de Personne
        this.matricule = matricule;
    }
}
```

⚠️ **Important** : `super(...)` doit être la **première instruction** du constructeur.

---

### 3.2 Appeler une Méthode Parent

```java
class Vehicule {
    public void demarrer() {
        System.out.println("Le véhicule démarre");
    }
}

class Voiture extends Vehicule {
    @Override
    public void demarrer() {
        super.demarrer();  // Appelle la méthode de Vehicule
        System.out.println("La voiture démarre son moteur");
    }
}

public class Test {
    public static void main(String[] args) {
        Voiture v = new Voiture();
        v.demarrer();
    }
}
```

**Sortie** :
```
Le véhicule démarre
La voiture démarre son moteur
```

---

## 4. Redéfinition de Méthodes (`@Override`)

### 4.1 Principe

Une classe fille peut **redéfinir** (override) une méthode de la classe mère.

```java
class Forme {
    public double calculerAire() {
        return 0;
    }
}

class Rectangle extends Forme {
    private double longueur;
    private double largeur;
    
    public Rectangle(double longueur, double largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }
    
    @Override
    public double calculerAire() {
        return longueur * largeur;
    }
}

class Cercle extends Forme {
    private double rayon;
    
    public Cercle(double rayon) {
        this.rayon = rayon;
    }
    
    @Override
    public double calculerAire() {
        return Math.PI * rayon * rayon;
    }
}
```

---

### 4.2 Annotation `@Override`

✅ **Avantages** :
- Vérification à la compilation
- Détecte les erreurs de signature
- Améliore la lisibilité

```java
class Parent {
    public void methode() { }
}

class Enfant extends Parent {
    @Override
    public void methode() { }  // ✅ OK
    
    @Override
    public void metode() { }   // ❌ ERREUR : faute de frappe détectée
}
```

---

## 5. Le Modificateur `protected`

### 5.1 Utilité

`protected` permet aux **sous-classes** d'accéder aux membres de la classe mère.

```java
class CompteBancaire {
    protected double solde;  // Accessible aux sous-classes
    private String numeroCompte;  // Pas accessible aux sous-classes
    
    public CompteBancaire(double solde) {
        this.solde = solde;
    }
}

class CompteEpargne extends CompteBancaire {
    private double tauxInteret;
    
    public CompteEpargne(double solde, double tauxInteret) {
        super(solde);
        this.tauxInteret = tauxInteret;
    }
    
    public void appliquerInterets() {
        solde += solde * tauxInteret;  // ✅ Accès à solde (protected)
        // numeroCompte  // ❌ Impossible (private)
    }
}
```

---

## 6. Exemple Complet : Hiérarchie Personne

```java
class Personne {
    protected String nom;
    protected String prenom;
    protected int age;
    
    public Personne(String nom, String prenom, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }
    
    public void afficher() {
        System.out.println(prenom + " " + nom + ", " + age + " ans");
    }
    
    public void sePresenter() {
        System.out.println("Je m'appelle " + prenom + " " + nom);
    }
}

class Etudiant extends Personne {
    private String matricule;
    private double moyenne;
    
    public Etudiant(String nom, String prenom, int age, String matricule) {
        super(nom, prenom, age);
        this.matricule = matricule;
        this.moyenne = 0.0;
    }
    
    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
    
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Matricule : " + matricule + ", Moyenne : " + moyenne);
    }
    
    @Override
    public void sePresenter() {
        super.sePresenter();
        System.out.println("Je suis étudiant (" + matricule + ")");
    }
    
    public void etudier() {
        System.out.println(prenom + " étudie");
    }
}

class Enseignant extends Personne {
    private String matiere;
    private double salaire;
    
    public Enseignant(String nom, String prenom, int age, String matiere, double salaire) {
        super(nom, prenom, age);
        this.matiere = matiere;
        this.salaire = salaire;
    }
    
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Matière : " + matiere + ", Salaire : " + salaire + "€");
    }
    
    @Override
    public void sePresenter() {
        super.sePresenter();
        System.out.println("J'enseigne " + matiere);
    }
    
    public void enseigner() {
        System.out.println(prenom + " donne un cours de " + matiere);
    }
}

public class TestPersonne {
    public static void main(String[] args) {
        Etudiant etudiant = new Etudiant("Dupont", "Alice", 20, "E12345");
        etudiant.setMoyenne(15.5);
        
        Enseignant enseignant = new Enseignant("Martin", "Pierre", 45, "Mathématiques", 3000);
        
        System.out.println("=== ÉTUDIANT ===");
        etudiant.afficher();
        etudiant.sePresenter();
        etudiant.etudier();
        
        System.out.println("\n=== ENSEIGNANT ===");
        enseignant.afficher();
        enseignant.sePresenter();
        enseignant.enseigner();
    }
}
```

---

## 7. Le Modificateur `final`

### 7.1 Classe `final`

Une classe `final` **ne peut pas être héritée**.

```java
final class String {
    // La classe String est final
    // Impossible de créer une sous-classe
}

// ❌ ERREUR
class MaSuperString extends String {  // Impossible
}
```

---

### 7.2 Méthode `final`

Une méthode `final` **ne peut pas être redéfinie**.

```java
class Vehicule {
    public final void demarrer() {
        System.out.println("Démarrage standard");
    }
}

class Voiture extends Vehicule {
    // ❌ ERREUR : impossible de redéfinir une méthode final
    @Override
    public void demarrer() {
        System.out.println("Démarrage personnalisé");
    }
}
```

---

## 8. Héritage Multiple : Interdit en Java

```java
class A { }
class B { }

// ❌ ERREUR : héritage multiple interdit
class C extends A, B { }
```

✅ **Solution** : Utiliser les **interfaces** (Section 10)

---

## 9. Constructeurs et Héritage

### 9.1 Ordre d'Exécution

```java
class A {
    A() {
        System.out.println("Constructeur A");
    }
}

class B extends A {
    B() {
        super();  // Appel implicite si absent
        System.out.println("Constructeur B");
    }
}

class C extends B {
    C() {
        super();
        System.out.println("Constructeur C");
    }
}

public class Test {
    public static void main(String[] args) {
        C c = new C();
    }
}
```

**Sortie** :
```
Constructeur A
Constructeur B
Constructeur C
```

---

## 📝 Résumé de la Section

| Concept | Description |
|---------|-------------|
| **Héritage** | Relation "EST-UN", réutilisation de code |
| **`extends`** | Mot-clé pour hériter d'une classe |
| **`super`** | Accès à la classe parent |
| **`@Override`** | Redéfinition d'une méthode |
| **`protected`** | Accessible aux sous-classes |
| **`final`** | Interdit l'héritage ou la redéfinition |

---

## 🎯 Points à Retenir

1. **Héritage** = relation "EST-UN"
2. `super()` = appel du constructeur parent (première ligne)
3. **`@Override`** = redéfinir une méthode
4. **`protected`** = visible par les sous-classes
5. **Java interdit** l'héritage multiple

---

**Prochaine section** : [Section 10 : Polymorphisme →](../10-Polymorphisme/README.md)
