# Section 10 : Polymorphisme

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Comprendre le polymorphisme
- Utiliser le polymorphisme avec héritage
- Connaître les classes abstraites
- Utiliser les interfaces
- Maîtriser le casting (upcast/downcast)
- Utiliser l'opérateur `instanceof`

---

## 1. Qu'est-ce que le Polymorphisme ?

### 1.1 Définition

**Polymorphisme** = "Plusieurs formes"

Capacité d'un objet à prendre **plusieurs formes**.

```java
Animal animal1 = new Chien();  // Un Chien VU COMME un Animal
Animal animal2 = new Chat();   // Un Chat VU COMME un Animal
```

---

### 1.2 Exemple Simple

```java
class Animal {
    public void faireDuBruit() {
        System.out.println("L'animal fait du bruit");
    }
}

class Chien extends Animal {
    @Override
    public void faireDuBruit() {
        System.out.println("Woof!");
    }
}

class Chat extends Animal {
    @Override
    public void faireDuBruit() {
        System.out.println("Miaou!");
    }
}

public class TestPolymorphisme {
    public static void main(String[] args) {
        // POLYMORPHISME : type déclaré = Animal, type réel = Chien/Chat
        Animal a1 = new Chien();
        Animal a2 = new Chat();
        
        a1.faireDuBruit();  // "Woof!"   (méthode de Chien)
        a2.faireDuBruit();  // "Miaou!"  (méthode de Chat)
        
        // La bonne méthode est appelée selon le TYPE RÉEL
    }
}
```

---

## 2. Types Statique vs Dynamique

### 2.1 Définitions

```java
Animal animal = new Chien();
//  ①          ②
```

- **① Type statique** (déclaré) = `Animal`
- **② Type dynamique** (réel) = `Chien`

---

### 2.2 Règle Importante

```java
Animal animal = new Chien();

// ✅ OK : méthode définie dans Animal
animal.faireDuBruit();

// ❌ ERREUR : méthode spécifique à Chien
animal.aboyer();  // Erreur de compilation

// Le compilateur ne voit que le TYPE STATIQUE (Animal)
```

---

## 3. Utilité du Polymorphisme

### 3.1 Traiter des Objets Différents de Manière Uniforme

```java
public class Refuge {
    public static void main(String[] args) {
        // Tableau d'animaux (types différents)
        Animal[] animaux = {
            new Chien(),
            new Chat(),
            new Chien(),
            new Chat()
        };
        
        // Boucle uniforme pour tous les animaux
        for (Animal animal : animaux) {
            animal.faireDuBruit();  // Appelle la bonne méthode
        }
    }
}
```

**Sortie** :
```
Woof!
Miaou!
Woof!
Miaou!
```

---

### 3.2 Méthodes Génériques

```java
class Veterinaire {
    public void examiner(Animal animal) {
        System.out.println("Examen de l'animal...");
        animal.faireDuBruit();
    }
}

public class Test {
    public static void main(String[] args) {
        Veterinaire veto = new Veterinaire();
        
        // Une seule méthode pour tous les types d'animaux
        veto.examiner(new Chien());
        veto.examiner(new Chat());
    }
}
```

---

## 4. Classes Abstraites

### 4.1 Définition

Une **classe abstraite** est une classe **incomplète** qui ne peut pas être instanciée.

```java
abstract class Forme {
    protected String couleur;
    
    public Forme(String couleur) {
        this.couleur = couleur;
    }
    
    // Méthode abstraite (sans corps)
    public abstract double calculerAire();
    
    // Méthode concrète
    public void afficherCouleur() {
        System.out.println("Couleur : " + couleur);
    }
}
```

❌ **Impossible d'instancier** :
```java
Forme f = new Forme("Rouge");  // ERREUR
```

---

### 4.2 Classes Concrètes

```java
class Rectangle extends Forme {
    private double longueur;
    private double largeur;
    
    public Rectangle(String couleur, double longueur, double largeur) {
        super(couleur);
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
    
    public Cercle(String couleur, double rayon) {
        super(couleur);
        this.rayon = rayon;
    }
    
    @Override
    public double calculerAire() {
        return Math.PI * rayon * rayon;
    }
}
```

---

### 4.3 Utilisation

```java
public class TestFormes {
    public static void main(String[] args) {
        Forme[] formes = {
            new Rectangle("Rouge", 5, 3),
            new Cercle("Bleu", 4),
            new Rectangle("Vert", 10, 2)
        };
        
        for (Forme forme : formes) {
            forme.afficherCouleur();
            System.out.println("Aire : " + forme.calculerAire());
            System.out.println();
        }
    }
}
```

---

## 5. Interfaces

### 5.1 Définition

Une **interface** est un **contrat** que les classes doivent respecter.

```java
interface Volant {
    void decoller();
    void atterrir();
    void voler();
}
```

---

### 5.2 Implémentation

```java
class Avion implements Volant {
    @Override
    public void decoller() {
        System.out.println("L'avion décolle");
    }
    
    @Override
    public void atterrir() {
        System.out.println("L'avion atterrit");
    }
    
    @Override
    public void voler() {
        System.out.println("L'avion vole à 10000m");
    }
}

class Oiseau implements Volant {
    @Override
    public void decoller() {
        System.out.println("L'oiseau s'envole");
    }
    
    @Override
    public void atterrir() {
        System.out.println("L'oiseau se pose");
    }
    
    @Override
    public void voler() {
        System.out.println("L'oiseau vole");
    }
}
```

---

### 5.3 Polymorphisme avec Interface

```java
public class Test {
    public static void main(String[] args) {
        Volant[] volants = {
            new Avion(),
            new Oiseau()
        };
        
        for (Volant v : volants) {
            v.decoller();
            v.voler();
            v.atterrir();
            System.out.println();
        }
    }
}
```

---

### 5.4 Implémenter Plusieurs Interfaces

```java
interface Nageant {
    void nager();
}

interface Volant {
    void voler();
}

// Implémente 2 interfaces
class Canard implements Volant, Nageant {
    @Override
    public void voler() {
        System.out.println("Le canard vole");
    }
    
    @Override
    public void nager() {
        System.out.println("Le canard nage");
    }
}
```

---

## 6. Casting (Conversion de Type)

### 6.1 Upcast (Conversion Implicite)

```java
Chien chien = new Chien();
Animal animal = chien;  // ✅ Upcast automatique
```

---

### 6.2 Downcast (Conversion Explicite)

```java
Animal animal = new Chien();

// ❌ Ne compile pas
animal.aboyer();

// ✅ Downcast nécessaire
Chien chien = (Chien) animal;
chien.aboyer();
```

⚠️ **Danger** :
```java
Animal animal = new Chat();
Chien chien = (Chien) animal;  // ClassCastException à l'exécution !
```

---

## 7. Opérateur `instanceof`

### 7.1 Syntaxe

```java
objet instanceof Type
```

Retourne `true` si l'objet est une instance de ce type.

---

### 7.2 Utilisation

```java
public class Test {
    public static void traiter(Animal animal) {
        if (animal instanceof Chien) {
            Chien chien = (Chien) animal;
            chien.aboyer();
        } else if (animal instanceof Chat) {
            Chat chat = (Chat) animal;
            chat.miauler();
        }
    }
    
    public static void main(String[] args) {
        traiter(new Chien());  // "Woof!"
        traiter(new Chat());   // "Miaou!"
    }
}
```

---

## 8. Exemple Complet : Système de Paiement

```java
abstract class MoyenPaiement {
    protected double montant;
    
    public MoyenPaiement(double montant) {
        this.montant = montant;
    }
    
    public abstract boolean effectuerPaiement();
    public abstract String getType();
}

class CarteBancaire extends MoyenPaiement {
    private String numero;
    
    public CarteBancaire(double montant, String numero) {
        super(montant);
        this.numero = numero;
    }
    
    @Override
    public boolean effectuerPaiement() {
        System.out.println("💳 Paiement de " + montant + "€ par carte " + numero);
        return true;
    }
    
    @Override
    public String getType() {
        return "Carte Bancaire";
    }
}

class PayPal extends MoyenPaiement {
    private String email;
    
    public PayPal(double montant, String email) {
        super(montant);
        this.email = email;
    }
    
    @Override
    public boolean effectuerPaiement() {
        System.out.println("💵 Paiement de " + montant + "€ via PayPal (" + email + ")");
        return true;
    }
    
    @Override
    public String getType() {
        return "PayPal";
    }
}

class Especes extends MoyenPaiement {
    public Especes(double montant) {
        super(montant);
    }
    
    @Override
    public boolean effectuerPaiement() {
        System.out.println("💵 Paiement de " + montant + "€ en espèces");
        return true;
    }
    
    @Override
    public String getType() {
        return "Espèces";
    }
}

class Caisse {
    public void traiterPaiement(MoyenPaiement paiement) {
        System.out.println("Type de paiement : " + paiement.getType());
        if (paiement.effectuerPaiement()) {
            System.out.println("✅ Paiement réussi\n");
        } else {
            System.out.println("❌ Paiement échoué\n");
        }
    }
}

public class TestPaiement {
    public static void main(String[] args) {
        Caisse caisse = new Caisse();
        
        caisse.traiterPaiement(new CarteBancaire(50.0, "**** 1234"));
        caisse.traiterPaiement(new PayPal(30.0, "alice@exemple.com"));
        caisse.traiterPaiement(new Especes(20.0));
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Description |
|---------|-------------|
| **Polymorphisme** | Un objet peut prendre plusieurs formes |
| **Classe abstraite** | Classe incomplète avec méthodes abstraites |
| **Interface** | Contrat que les classes doivent respecter |
| **Upcast** | Conversion automatique vers le parent |
| **Downcast** | Conversion explicite vers l'enfant |
| **`instanceof`** | Vérifie le type réel d'un objet |

---

## 🎯 Points à Retenir

1. **Polymorphisme** = même code, comportements différents
2. **Classe abstraite** = `abstract`, contient méthodes abstraites
3. **Interface** = 100% abstraite, implémentation multiple possible
4. **`instanceof`** = vérifier le type avant downcast
5. Le polymorphisme rend le code **flexible** et **maintenable**

---

**Prochaine section** : [Section 11 : Interfaces Fonctionnelles →](../11-Interfaces-Fonctionnelles/README.md)
