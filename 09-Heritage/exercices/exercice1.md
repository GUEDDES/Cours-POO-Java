# Exercice 1 : Hiérarchie de Véhicules

## 🎯 Objectif

Créer une hiérarchie de classes pour représenter différents types de véhicules.

---

## 📋 Énoncé

Créez une hiérarchie de classes : `Vehicule` → `VehiculeTerrestre` / `VehiculeAerien` → `Voiture` / `Moto` / `Avion`

---

## 📝 Classes à créer

### Classe Mère : `Vehicule`

```java
abstract class Vehicule {
    protected String marque;
    protected String modele;
    protected int annee;
    protected double vitesseMax;  // en km/h
    
    public Vehicule(String marque, String modele, int annee, double vitesseMax)
    
    // Méthode abstraite
    public abstract void demarrer();
    public abstract void arreter();
    
    // Méthode concrète
    public void afficherInfos()
    public double convertirVitesseEnMph()  // 1 km/h = 0.621371 mph
}
```

### Classes Intermédiaires

```java
abstract class VehiculeTerrestre extends Vehicule {
    protected int nombreRoues;
    
    public abstract void klaxonner();
}

abstract class VehiculeAerien extends Vehicule {
    protected double altitudeMax;  // en mètres
    
    public abstract void decoller();
    public abstract void atterrir();
}
```

### Classes Concrètes

```java
class Voiture extends VehiculeTerrestre {
    private int nombrePortes;
    private String typeCarburant;  // "Essence", "Diesel", "Électrique"
    
    // Redéfinir toutes les méthodes abstraites
    @Override
    public void demarrer() { /* ... */ }
    
    @Override
    public void arreter() { /* ... */ }
    
    @Override
    public void klaxonner() { /* ... */ }
    
    // Méthode spécifique
    public void ouvrirCoffre() { /* ... */ }
}

class Moto extends VehiculeTerrestre {
    private boolean avecSidecar;
    
    // Redéfinir toutes les méthodes abstraites
}

class Avion extends VehiculeAerien {
    private int nombrePassagers;
    private String compagnie;
    
    // Redéfinir toutes les méthodes abstraites
    
    // Méthode spécifique
    public void activerPiloteAutomatique() { /* ... */ }
}
```

---

## 💡 Indications

### Redéfinition de méthodes

Chaque classe doit redéfinir les méthodes de manière spécifique :

```java
// Voiture
public void demarrer() {
    System.out.println("🚗 La voiture " + marque + " " + modele + " démarre");
}

public void klaxonner() {
    System.out.println("📢 Beep beep!");
}

// Moto
public void demarrer() {
    System.out.println("🏍️ La moto " + marque + " " + modele + " vrombit");
}

public void klaxonner() {
    System.out.println("📢 Tut tut!");
}

// Avion
public void demarrer() {
    System.out.println("✈️ L'avion " + marque + " " + modele + " allume ses réacteurs");
}

public void decoller() {
    System.out.println("🛫 Décollage... altitude max : " + altitudeMax + "m");
}
```

---

## ✅ Programme de test

```java
public class TestVehicules {
    public static void main(String[] args) {
        // Créer des véhicules
        Voiture voiture = new Voiture("Peugeot", "308", 2023, 220, 5, "Essence");
        Moto moto = new Moto("Yamaha", "MT-07", 2024, 180, false);
        Avion avion = new Avion("Boeing", "737", 2020, 850, 10000, 180, "Air France");
        
        // POLYMORPHISME : Tableau de véhicules
        Vehicule[] vehicules = {voiture, moto, avion};
        
        System.out.println("=== TOUS LES VÉHICULES DÉMARRENT ===");
        for (Vehicule v : vehicules) {
            v.demarrer();  // Appelle la bonne méthode selon le type
        }
        
        System.out.println("\n=== VÉHICULES TERRESTRES KLAXONNENT ===");
        for (Vehicule v : vehicules) {
            if (v instanceof VehiculeTerrestre) {
                ((VehiculeTerrestre) v).klaxonner();
            }
        }
        
        System.out.println("\n=== AVION DÉCOLLE ===");
        avion.decoller();
        avion.activerPiloteAutomatique();
        avion.atterrir();
        
        // Conversion vitesse
        System.out.println("\n=== VITESSES EN MPH ===");
        for (Vehicule v : vehicules) {
            System.out.printf("%s : %.2f mph%n", 
                v.getClass().getSimpleName(), 
                v.convertirVitesseEnMph());
        }
    }
}
```

---

## 📊 Sortie Attendue

```
=== TOUS LES VÉHICULES DÉMARRENT ===
🚗 La voiture Peugeot 308 démarre
🏍️ La moto Yamaha MT-07 vrombit
✈️ L'avion Boeing 737 allume ses réacteurs

=== VÉHICULES TERRESTRES KLAXONNENT ===
📢 Beep beep!
📢 Tut tut!

=== AVION DÉCOLLE ===
🛫 Décollage... altitude max : 10000m
✈️ Pilote automatique activé
🛬 Atterrissage en cours...

=== VITESSES EN MPH ===
Voiture : 136.70 mph
Moto : 111.85 mph
Avion : 528.17 mph
```

---

## 🎯 Critères de Validation

- [ ] Classe abstraite `Vehicule` créée
- [ ] Classes abstraites intermédiaires créées
- [ ] 3 classes concrètes (Voiture, Moto, Avion)
- [ ] Toutes les méthodes abstraites implémentées
- [ ] Utilisation de `super()` dans les constructeurs
- [ ] Méthodes redéfinies avec `@Override`
- [ ] Utilisation de `protected` pour les attributs hérités
- [ ] Polymorphisme démontré (tableau de véhicules)
- [ ] Utilisation de `instanceof` et casting
- [ ] Programme de test complet

---

## 🚀 Bonus

1. Ajouter une classe `VehiculeNautique` (Bateau, Jet-ski)
2. Créer une méthode `calculerTempsTrajet(distance)` dans `Vehicule`
3. Ajouter un compteur statique du nombre total de véhicules
4. Implémenter une interface `Polluant` avec méthode `calculerEmissions()`

---

**Bon courage ! 🚗🏍️✈️**
