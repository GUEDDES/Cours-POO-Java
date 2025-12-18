# Exercice 1 : Système de Formes Géométriques

## 🎯 Objectif

Créer une hiérarchie polymorphe de formes géométriques avec classes abstraites et interfaces.

---

## 📋 Énoncé

Créez un système complet de gestion de formes géométriques utilisant :
- **Classe abstraite** : `Forme`
- **Interfaces** : `Dessinable`, `Colorable`
- **Classes concrètes** : `Cercle`, `Rectangle`, `Triangle`

---

## 📝 Classes et Interfaces à créer

### Interface `Dessinable`

```java
interface Dessinable {
    void dessiner();      // Affiche la forme en mode texte
    void effacer();       // Efface la forme
    void deplacer(int x, int y);  // Change la position
}
```

### Interface `Colorable`

```java
interface Colorable {
    void setCouleur(String couleur);
    String getCouleur();
    void afficherCouleur();
}
```

### Classe Abstraite `Forme`

```java
abstract class Forme implements Dessinable, Colorable {
    protected int x, y;           // Position
    protected String couleur;
    
    public Forme(int x, int y, String couleur)
    
    // Méthodes abstraites
    public abstract double calculerAire();
    public abstract double calculerPerimetre();
    public abstract String getNom();
    
    // Méthode concrète
    public void afficherInfos() {
        System.out.println(getNom() + " - Couleur: " + couleur);
        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.printf("Aire: %.2f, Périmètre: %.2f%n", 
            calculerAire(), calculerPerimetre());
    }
    
    // Implémenter Colorable
    @Override
    public void setCouleur(String couleur) { this.couleur = couleur; }
    
    @Override
    public String getCouleur() { return couleur; }
    
    @Override
    public void afficherCouleur() {
        System.out.println("🎨 Couleur : " + couleur);
    }
}
```

### Classes Concrètes

```java
class Cercle extends Forme {
    private double rayon;
    
    public Cercle(int x, int y, String couleur, double rayon) {
        super(x, y, couleur);
        this.rayon = rayon;
    }
    
    @Override
    public double calculerAire() {
        return Math.PI * rayon * rayon;
    }
    
    @Override
    public double calculerPerimetre() {
        return 2 * Math.PI * rayon;
    }
    
    @Override
    public String getNom() {
        return "Cercle";
    }
    
    @Override
    public void dessiner() {
        System.out.println("● Dessin d'un cercle de rayon " + rayon);
    }
    
    @Override
    public void effacer() {
        System.out.println("○ Cercle effacé");
    }
    
    @Override
    public void deplacer(int newX, int newY) {
        System.out.printf("Cercle déplacé de (%d,%d) vers (%d,%d)%n", x, y, newX, newY);
        this.x = newX;
        this.y = newY;
    }
}

class Rectangle extends Forme {
    private double longueur;
    private double largeur;
    
    // Implémenter toutes les méthodes...
}

class Triangle extends Forme {
    private double cote1, cote2, cote3;
    
    // Implémenter toutes les méthodes...
    // Utiliser la formule de Héron pour l'aire
}
```

---

## 💡 Indications

### Formule de Héron (Triangle)

```java
// Aire = √(s * (s-a) * (s-b) * (s-c))
// où s = (a + b + c) / 2 (demi-périmètre)

double s = (cote1 + cote2 + cote3) / 2;
double aire = Math.sqrt(s * (s - cote1) * (s - cote2) * (s - cote3));
```

### Dessin ASCII

```java
// Cercle
public void dessiner() {
    System.out.println("    ***    ");
    System.out.println("  *     *  ");
    System.out.println(" *       * ");
    System.out.println("  *     *  ");
    System.out.println("    ***    ");
}

// Rectangle
public void dessiner() {
    System.out.println("*********");
    System.out.println("*       *");
    System.out.println("*       *");
    System.out.println("*********");
}
```

---

## ✅ Programme de test

```java
public class TestFormes {
    public static void main(String[] args) {
        // Créer des formes
        Forme[] formes = {
            new Cercle(0, 0, "Rouge", 5),
            new Rectangle(10, 10, "Bleu", 8, 4),
            new Triangle(20, 20, "Vert", 3, 4, 5)
        };
        
        System.out.println("=== POLYMORPHISME : AFFICHAGE ===");
        for (Forme forme : formes) {
            forme.afficherInfos();
            System.out.println();
        }
        
        System.out.println("\n=== DESSIN DES FORMES ===");
        for (Forme forme : formes) {
            forme.dessiner();
        }
        
        System.out.println("\n=== CHANGEMENT DE COULEUR ===");
        formes[0].setCouleur("Jaune");
        formes[0].afficherCouleur();
        
        System.out.println("\n=== DÉPLACEMENT ===");
        formes[1].deplacer(50, 50);
        
        System.out.println("\n=== CALCUL AIRE TOTALE ===");
        double aireToale = 0;
        for (Forme forme : formes) {
            aireTotale += forme.calculerAire();
        }
        System.out.printf("Aire totale : %.2f%n", aireTotale);
        
        System.out.println("\n=== INSTANCEOF ===");
        for (Forme forme : formes) {
            if (forme instanceof Cercle) {
                System.out.println("C'est un cercle !");
            } else if (forme instanceof Rectangle) {
                System.out.println("C'est un rectangle !");
            }
        }
    }
}
```

---

## 📊 Sortie Attendue

```
=== POLYMORPHISME : AFFICHAGE ===
Cercle - Couleur: Rouge
Position: (0, 0)
Aire: 78.54, Périmètre: 31.42

Rectangle - Couleur: Bleu
Position: (10, 10)
Aire: 32.00, Périmètre: 24.00

Triangle - Couleur: Vert
Position: (20, 20)
Aire: 6.00, Périmètre: 12.00

=== DESSIN DES FORMES ===
● Dessin d'un cercle de rayon 5.0
▭ Dessin d'un rectangle 8.0 x 4.0
△ Dessin d'un triangle (3.0, 4.0, 5.0)

=== CHANGEMENT DE COULEUR ===
🎨 Couleur : Jaune

=== DÉPLACEMENT ===
Rectangle déplacé de (10,10) vers (50,50)

=== CALCUL AIRE TOTALE ===
Aire totale : 116.54

=== INSTANCEOF ===
C'est un cercle !
C'est un rectangle !
```

---

## 🎯 Critères de Validation

- [ ] Interface `Dessinable` créée
- [ ] Interface `Colorable` créée
- [ ] Classe abstraite `Forme` implémente les deux interfaces
- [ ] 3 classes concrètes (Cercle, Rectangle, Triangle)
- [ ] Toutes les méthodes abstraites implémentées
- [ ] Méthodes `calculerAire()` et `calculerPerimetre()` correctes
- [ ] Polymorphisme démontré (tableau de formes)
- [ ] Utilisation de `instanceof` et casting
- [ ] Programme de test complet
- [ ] Toutes les méthodes des interfaces implémentées

---

## 🚀 Bonus

1. Ajouter une classe `Carre` qui hérite de `Rectangle`
2. Créer une interface `Redimensionnable` avec `agrandir(double facteur)`
3. Ajouter une méthode statique `comparerAires(Forme f1, Forme f2)`
4. Implémenter `Comparable<Forme>` pour trier par aire

---

**Bon courage ! 🟢▭△**
