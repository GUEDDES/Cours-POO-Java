# Section 6 : Relations entre Classes

## 🎯 Objectifs d'apprentissage

À la fin de cette section, vous serez capable de :
- Distinguer composition et agrégation
- Implémenter une relation de composition
- Implémenter une relation d'agrégation
- Choisir le bon type de relation selon le contexte
- Modéliser des systèmes complexes

---

## 1. Introduction aux Relations

### 1.1 Pourquoi les Relations ?

Dans un système réel, les objets **ne vivent pas isolés**. Ils collaborent et interagissent.

**Exemples** :
- Une voiture **contient** un moteur
- Une université **a des** étudiants
- Un ordinateur **est composé de** processeur, RAM, disque dur
- Une équipe **comprend des** joueurs

---

### 1.2 Types de Relations

```
┌─────────────────────────────────────────────┐
│         RELATIONS ENTRE CLASSES             │
├─────────────────────────────────────────────┤
│                                             │
│  1. COMPOSITION (relation FORTE)            │
│     "contient" - cycle de vie dépendant     │
│     Symbole UML : ◆────────>                │
│                                             │
│  2. AGRÉGATION (relation FAIBLE)            │
│     "a un" - cycle de vie indépendant       │
│     Symbole UML : ◇────────>                │
│                                             │
└─────────────────────────────────────────────┘
```

---

## 2. Composition : Relation Forte

### 2.1 Définition

**Composition** = Relation "**contient**" où la partie **ne peut pas exister** sans le conteneur.

**Caractéristiques** :
- ✅ Les parties sont **créées PAR** le conteneur
- ✅ Les parties sont **détruites AVEC** le conteneur
- ✅ Cycle de vie **dépendant**
- ✅ Possession **exclusive**

---

### 2.2 Exemple : Voiture et Moteur

**Analyse** :
- Un moteur est **créé avec** la voiture
- Si la voiture est détruite, le moteur **n'existe plus**
- Un moteur **appartient à UNE seule** voiture

```
Voiture ◆────────> Moteur
         contient
```

---

### 2.3 Implémentation Java

#### Classe Moteur

```java
class Moteur {
    private int puissance;      // En chevaux
    private String type;        // Essence, Diesel, Électrique
    
    Moteur(int puissance, String type) {
        this.puissance = puissance;
        this.type = type;
    }
    
    void demarrer() {
        System.out.println("Moteur " + type + " de " + puissance + "ch démarre");
    }
    
    void afficher() {
        System.out.println("Moteur : " + type + " - " + puissance + "ch");
    }
}
```

---

#### Classe Voiture (Composition)

```java
class Voiture {
    private String marque;
    private String modele;
    private Moteur moteur;  // Composition
    
    // Le moteur est créé DANS le constructeur
    Voiture(String marque, String modele, int puissanceMoteur, String typeMoteur) {
        this.marque = marque;
        this.modele = modele;
        this.moteur = new Moteur(puissanceMoteur, typeMoteur);  // ⚡ Création interne
    }
    
    void demarrer() {
        System.out.println("Démarrage de " + marque + " " + modele);
        moteur.demarrer();
    }
    
    void afficherInfos() {
        System.out.println("Voiture : " + marque + " " + modele);
        moteur.afficher();
    }
}
```

---

#### Test

```java
public class TestComposition {
    public static void main(String[] args) {
        // Création d'une voiture (le moteur est créé automatiquement)
        Voiture maVoiture = new Voiture("Tesla", "Model 3", 450, "Électrique");
        
        maVoiture.afficherInfos();
        // Voiture : Tesla Model 3
        // Moteur : Électrique - 450ch
        
        maVoiture.demarrer();
        // Démarrage de Tesla Model 3
        // Moteur Électrique de 450ch démarre
        
        // Quand maVoiture sera détruite (GC), son moteur le sera aussi
        maVoiture = null;  // Le moteur n'est plus accessible
    }
}
```

---

### 2.4 Exemple 2 : Ordinateur et Composants

```java
class Processeur {
    private String modele;
    private int frequence;  // En GHz
    
    Processeur(String modele, int frequence) {
        this.modele = modele;
        this.frequence = frequence;
    }
    
    void afficher() {
        System.out.println("CPU : " + modele + " @ " + frequence + "GHz");
    }
}

class RAM {
    private int capacite;  // En Go
    
    RAM(int capacite) {
        this.capacite = capacite;
    }
    
    void afficher() {
        System.out.println("RAM : " + capacite + "Go");
    }
}

class Ordinateur {
    private String marque;
    private Processeur cpu;  // Composition
    private RAM ram;         // Composition
    
    Ordinateur(String marque, String modeleCPU, int freqCPU, int capaciteRAM) {
        this.marque = marque;
        this.cpu = new Processeur(modeleCPU, freqCPU);  // Création interne
        this.ram = new RAM(capaciteRAM);                 // Création interne
    }
    
    void afficherConfig() {
        System.out.println("=== " + marque + " ===");
        cpu.afficher();
        ram.afficher();
    }
}
```

**Test** :
```java
Ordinateur pc = new Ordinateur("Dell", "Intel i7", 3, 16);
pc.afficherConfig();
// === Dell ===
// CPU : Intel i7 @ 3GHz
// RAM : 16Go
```

---

## 3. Agrégation : Relation Faible

### 3.1 Définition

**Agrégation** = Relation "**a un**" où les parties peuvent **exister indépendamment** du conteneur.

**Caractéristiques** :
- ✅ Les parties **existent AVANT** le conteneur
- ✅ Les parties **survivent APRÈS** la destruction du conteneur
- ✅ Cycle de vie **indépendant**
- ✅ Les parties peuvent être **partagées**

---

### 3.2 Exemple : Université et Étudiants

**Analyse** :
- Les étudiants **existent avant** de rejoindre l'université
- Si l'université ferme, les étudiants **continuent d'exister**
- Un étudiant peut changer d'université

```
Université ◇────────> Étudiant
            a des
```

---

### 3.3 Implémentation Java

#### Classe Étudiant

```java
class Etudiant {
    private String nom;
    private String matricule;
    private int age;
    
    Etudiant(String nom, String matricule, int age) {
        this.nom = nom;
        this.matricule = matricule;
        this.age = age;
    }
    
    String getNom() {
        return nom;
    }
    
    String getMatricule() {
        return matricule;
    }
    
    void afficher() {
        System.out.println("Étudiant : " + nom + " (" + matricule + "), " + age + " ans");
    }
}
```

---

#### Classe Université (Agrégation)

```java
import java.util.ArrayList;
import java.util.List;

class Universite {
    private String nom;
    private List<Etudiant> etudiants;  // Agrégation
    
    Universite(String nom) {
        this.nom = nom;
        this.etudiants = new ArrayList<>();
    }
    
    // L'étudiant est PASSÉ en paramètre (créé à l'extérieur)
    void inscrire(Etudiant etudiant) {
        etudiants.add(etudiant);
        System.out.println(etudiant.getNom() + " inscrit à " + nom);
    }
    
    void desinscrire(Etudiant etudiant) {
        if (etudiants.remove(etudiant)) {
            System.out.println(etudiant.getNom() + " désinscrit de " + nom);
        }
    }
    
    void afficherEtudiants() {
        System.out.println("\n=== " + nom + " ===");
        System.out.println("Nombre d'étudiants : " + etudiants.size());
        for (Etudiant e : etudiants) {
            e.afficher();
        }
    }
}
```

---

#### Test

```java
public class TestAgregation {
    public static void main(String[] args) {
        // 1. Créer des étudiants (existent indépendamment)
        Etudiant alice = new Etudiant("Alice Dupont", "E12345", 20);
        Etudiant bob = new Etudiant("Bob Martin", "E12346", 22);
        Etudiant charlie = new Etudiant("Charlie Durand", "E12347", 21);
        
        // 2. Créer une université
        Universite isitcom = new Universite("ISITCom");
        
        // 3. Inscrire les étudiants
        isitcom.inscrire(alice);
        isitcom.inscrire(bob);
        isitcom.inscrire(charlie);
        
        // 4. Afficher
        isitcom.afficherEtudiants();
        
        // 5. Désinscrire un étudiant
        isitcom.desinscrire(bob);
        
        // 6. Bob existe toujours !
        System.out.println("\nBob après désinscription :");
        bob.afficher();
        
        // 7. Bob peut s'inscrire ailleurs
        Universite autre = new Universite("Université de Sousse");
        autre.inscrire(bob);
    }
}
```

**Sortie** :
```
Alice Dupont inscrit à ISITCom
Bob Martin inscrit à ISITCom
Charlie Durand inscrit à ISITCom

=== ISITCom ===
Nombre d'étudiants : 3
Étudiant : Alice Dupont (E12345), 20 ans
Étudiant : Bob Martin (E12346), 22 ans
Étudiant : Charlie Durand (E12347), 21 ans
Bob Martin désinscrit de ISITCom

Bob après désinscription :
Étudiant : Bob Martin (E12346), 22 ans
Bob Martin inscrit à Université de Sousse
```

---

### 3.4 Exemple 2 : Équipe de Football

```java
import java.util.ArrayList;
import java.util.List;

class Joueur {
    private String nom;
    private int numero;
    private String poste;
    
    Joueur(String nom, int numero, String poste) {
        this.nom = nom;
        this.numero = numero;
        this.poste = poste;
    }
    
    String getNom() {
        return nom;
    }
    
    void afficher() {
        System.out.println("#" + numero + " " + nom + " (" + poste + ")");
    }
}

class Equipe {
    private String nom;
    private List<Joueur> joueurs;
    
    Equipe(String nom) {
        this.nom = nom;
        this.joueurs = new ArrayList<>();
    }
    
    void recruter(Joueur joueur) {
        joueurs.add(joueur);
        System.out.println(joueur.getNom() + " rejoint " + nom);
    }
    
    void licencier(Joueur joueur) {
        if (joueurs.remove(joueur)) {
            System.out.println(joueur.getNom() + " quitte " + nom);
        }
    }
    
    void afficherEquipe() {
        System.out.println("\n" + nom + " - Effectif :");
        for (Joueur j : joueurs) {
            j.afficher();
        }
    }
}
```

---

## 4. Composition vs Agrégation : Comment Choisir ?

### 4.1 Tableau Comparatif

| Critère | Composition | Agrégation |
|---------|-------------|------------|
| **Relation** | "contient" (forte) | "a un" (faible) |
| **Création** | Partie créée PAR le conteneur | Partie créée AVANT |
| **Destruction** | Partie détruite AVEC | Partie survit |
| **Partage** | NON (exclusif) | OUI (possible) |
| **Implémentation** | `new` dans constructeur | Passé en paramètre |
| **Symbole UML** | ◆─────> | ◇─────> |

---

### 4.2 Questions à se Poser

**Pour savoir si c'est une COMPOSITION** :

1. ❓ La partie a-t-elle un sens sans le conteneur ?  
   → **NON** = Composition

2. ❓ Si le conteneur est détruit, la partie doit-elle l'être aussi ?  
   → **OUI** = Composition

3. ❓ La partie est-elle créée en même temps que le conteneur ?  
   → **OUI** = Composition

---

### 4.3 Exemples de Classification

| Relation | Type | Justification |
|----------|------|---------------|
| Maison → Pièce | **Composition** | Une pièce n'existe pas sans maison |
| Bibliothèque → Livre | **Agrégation** | Un livre existe avant/après la bibliothèque |
| Ordinateur → Processeur | **Composition** | Le processeur est assemblé avec l'ordinateur |
| Playlist → Chanson | **Agrégation** | Une chanson existe indépendamment |
| Voiture → Roue | **Composition** | Les roues sont installées avec la voiture |
| Parking → Voiture | **Agrégation** | Une voiture peut changer de parking |

---

## 5. Exemple Complet : Système de Gestion d'Hôtel

### 5.1 Analyse

**Hôtel** :
- **Contient** des chambres (composition)
- **A des** clients (agrégation)

```
Hôtel ◆────────> Chambre  (composition)
      ◇────────> Client   (agrégation)
```

---

### 5.2 Implémentation

```java
import java.util.ArrayList;
import java.util.List;

// Classe Chambre (composition)
class Chambre {
    private int numero;
    private String type;  // Simple, Double, Suite
    private double prixNuit;
    private boolean estOccupee;
    
    Chambre(int numero, String type, double prixNuit) {
        this.numero = numero;
        this.type = type;
        this.prixNuit = prixNuit;
        this.estOccupee = false;
    }
    
    void occuper() {
        estOccupee = true;
    }
    
    void liberer() {
        estOccupee = false;
    }
    
    boolean estDisponible() {
        return !estOccupee;
    }
    
    void afficher() {
        String statut = estOccupee ? "Occupée" : "Disponible";
        System.out.println("Chambre " + numero + " (" + type + ") - " + 
                          prixNuit + "€/nuit - " + statut);
    }
}

// Classe Client (agrégation)
class Client {
    private String nom;
    private String email;
    
    Client(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }
    
    String getNom() {
        return nom;
    }
    
    void afficher() {
        System.out.println("Client : " + nom + " (" + email + ")");
    }
}

// Classe Hôtel
class Hotel {
    private String nom;
    private List<Chambre> chambres;  // Composition
    private List<Client> clients;    // Agrégation
    
    Hotel(String nom, int nombreChambres) {
        this.nom = nom;
        this.chambres = new ArrayList<>();
        this.clients = new ArrayList<>();
        
        // Créer les chambres (composition)
        for (int i = 1; i <= nombreChambres; i++) {
            String type = (i % 3 == 0) ? "Suite" : (i % 2 == 0) ? "Double" : "Simple";
            double prix = (type.equals("Suite")) ? 150 : (type.equals("Double")) ? 80 : 50;
            chambres.add(new Chambre(i, type, prix));
        }
    }
    
    void enregistrerClient(Client client) {
        clients.add(client);
        System.out.println(client.getNom() + " enregistré à l'hôtel " + nom);
    }
    
    void afficherChambresDisponibles() {
        System.out.println("\nChambres disponibles :");
        for (Chambre c : chambres) {
            if (c.estDisponible()) {
                c.afficher();
            }
        }
    }
}
```

---

## 📝 Résumé de la Section

| Concept | Points clés |
|---------|-------------|
| **Composition** | Partie créée DANS le conteneur, cycle de vie dépendant |
| **Agrégation** | Partie passée EN PARAMÈTRE, cycle de vie indépendant |
| **Choix** | Si la partie n'a pas de sens seule → Composition |

---

## 🎯 Points à Retenir

1. **Composition** = "contient" → `new` dans le constructeur
2. **Agrégation** = "a un" → Objet passé en paramètre
3. Composition : partie **détruite avec** le conteneur
4. Agrégation : partie **survit** au conteneur
5. Posez-vous : "La partie a-t-elle un sens seule ?" → **NON** = Composition

---

**Prochaine section** : [Section 7 : Paquetages (Packages) →](../07-Paquetages/README.md)
