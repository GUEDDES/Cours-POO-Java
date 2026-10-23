# Exercice 2 : Moteur de Facturation et Stratégies de Remise Polymorphes (Avancé)

## 🎯 Objectifs

- Maîtriser le polymorphisme basé sur les contrats d'interfaces.
- Appliquer le principe ouvert/fermé (*Open/Closed Principle*) : enrichir les comportements sans modifier les classes existantes.
- Utiliser la composition et la délégation de comportement (Pattern Strategy).
- Manipuler des interfaces multiples (`Payable`, `Imposable`).

---

## 📋 Énoncé

Dans un système de gestion commerciale, une facture doit calculer son total net en appliquant diverses politiques de remises sans figer la logique de calcul au sein de la classe `Facture`.

### 1. Interface `ApplicableRemise`

Définissez l'interface `ApplicableRemise` contenant la méthode suivante :
```java
public interface ApplicableRemise {
    double appliquerRemise(double montantBrut);
}
```

### 2. Implémentations de Politiques de Remise

Créez trois classes concrètes implémentant `ApplicableRemise` :
1. `RemisePourcentage` : applique une réduction proportionnelle (ex: 10%). Le constructeur valide que le taux est compris entre `0` et `100`.
2. `RemiseSeuil` : applique un rabais forfaitaire fixe (ex: 20 DT) uniquement si le montant brut dépasse un seuil spécifié (ex: 100 DT).
3. `RemiseNulle` : n'applique aucune remise (retourne le montant brut sans modification).

---

### 3. Interfaces `Payable` et `Imposable`

1. Interface `Payable` :
   ```java
   public interface Payable {
       double getMontantNet();
   }
   ```
2. Interface `Imposable` :
   ```java
   public interface Imposable {
       double getTauxTVA();
       double getMontantTTC();
   }
   ```

---

### 4. Classes Métier

#### Classe `LigneFacture`
- Attributs : `String designation`, `int quantite`, `double prixUnitaire`.
- Constructeur avec validation (quantité $> 0$, prix unitaire $\ge 0$).
- Méthode `double getSousTotal()` qui calcule `quantite * prixUnitaire`.

#### Classe `Facture`
- Implémente `Payable` et `Imposable`.
- Attributs :
  - `String numeroFacture`
  - `LigneFacture[] lignes`
  - `int nbLignes`
  - `ApplicableRemise politiqueRemise` (référence polymorphique vers l'interface)
  - `double tauxTVA` (ex: 0.19 pour 19%)
- Méthodes :
  - `void ajouterLigne(LigneFacture ligne)`
  - `void setPolitiqueRemise(ApplicableRemise nouvellePolitique)` : permet de modifier dynamiquement la stratégie commerciale.
  - `double getMontantBrut()` : somme des sous-totaux des lignes.
  - `double getMontantNet()` : montant brut après application de la politique de remise.
  - `double getMontantTTC()` : montant net augmenté de la TVA.
  - `void afficherRecapitulatif()` : affiche le détail des lignes et les différents montants calculés.

---

### 5. Programme de Test

Dans `Exercice2Solution`, écrivez un programme de test illustrant :
1. La création d'une facture avec plusieurs articles.
2. Le calcul avec une `RemisePourcentage`.
3. Le changement dynamique de politique à l'exécution vers une `RemiseSeuil`, puis vers `RemiseNulle`.
4. L'appel polymorphe à travers une variable de type `Payable`.
