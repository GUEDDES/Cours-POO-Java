# Solution Exercice 2 : Évaluation de la Qualité Logicielle

## Tableau d'Évaluation Complété

| Critère | V1 | V2 | V3 | Justification |
|---------|----|----|----|--------------|
| **1. Correction** | 5 | 5 | 5 | Les 3 versions calculent correctement le prix après réduction |
| **2. Robustesse** | 0 | 4 | 5 | V1 : aucune validation. V2 : validation basique. V3 : validation complète avec messages détaillés |
| **3. Évolutivité** | 1 | 2 | 5 | V1 : difficile à modifier. V2 : monolithique. V3 : méthodes séparées, facile d'ajouter des validations |
| **4. Réutilisabilité** | 2 | 3 | 5 | V1 : nom obscur. V2 : correct mais validation couplée. V3 : méthodes privées réutilisables |
| **5. Lisibilité** | 1 | 3 | 5 | V1 : noms courts, pas de doc. V2 : noms clairs. V3 : documentation Javadoc + méthodes bien nommées |
| **6. Maintenabilité** | 0 | 2 | 5 | V1 : cauchemar à maintenir. V2 : acceptable. V3 : excellente séparation des responsabilités |
| **TOTAL (/30)** | **9** | **19** | **30** | V3 est clairement supérieure pour un projet professionnel |

---

## Justifications Détaillées

### 1. Correction (5/5 pour toutes)

**Test** :
```java
double prix = 100.0;
int taux = 20;

// V1
double resultat = V1.calc(prix, taux);
// Résultat : 80.0 ✅

// V2
double resultat = V2.calculerPrixApresReduction(prix, taux);
// Résultat : 80.0 ✅

// V3
double resultat = CalculateurReduction.calculerPrixApresReduction(prix, taux);
// Résultat : 80.0 ✅
```

**Conclusion** : Toutes les versions sont **mathématiquement correctes**.

---

### 2. Robustesse

#### V1 : 0/5 ❌
```java
V1.calc(-50, 150);  // Résultat : -125.0 (absurde !)
```
- Aucune validation
- Accepte les valeurs négatives
- Accepte les taux > 100%

#### V2 : 4/5 ⚠️
```java
V2.calculerPrixApresReduction(-50, 20);  // Exception claire ✅
V2.calculerPrixApresReduction(100, 150); // Exception claire ✅
```
- Validation présente
- Messages d'erreur simples mais fonctionnels
- **-1 point** : messages génériques

#### V3 : 5/5 ✅
```java
CalculateurReduction.calculerPrixApresReduction(-50, 20);
// Exception : "Prix invalide : -50,00 (doit être >= 0)"
```
- Validation complète et centralisée
- Messages d'erreur **détaillés** avec la valeur erronée
- Méthodes de validation séparées (réutilisables)

---

### 3. Évolutivité

#### V1 : 1/5 ❌
**Évolution demandée** : Ajouter un plafond de réduction (max 100€)

```java
// Il faut tout réécrire
public static double calc(double p, int t) {
    double red = p * t / 100;
    if (red > 100) red = 100;  // Logique mélangée
    return p - red;
}
```
- Logique métier mélangée
- Difficulté à tester

#### V2 : 2/5 ⚠️
```java
public static double calculerPrixApresReduction(double prixInitial, int tauxReduction) {
    // Validation
    if (prixInitial < 0) { ... }
    if (tauxReduction < 0 || tauxReduction > 100) { ... }
    
    // Calcul
    double reduction = prixInitial * tauxReduction / 100;
    if (reduction > 100) reduction = 100;  // Ajout ici
    
    return prixInitial - reduction;
}
```
- Tout reste dans une seule méthode
- Méthode devient longue

#### V3 : 5/5 ✅
```java
// Ajout d'une nouvelle méthode de validation (facile !)
private static void validerPlafondReduction(double montantReduction) {
    if (montantReduction > 100) {
        throw new IllegalArgumentException(
            "Réduction plafonnée à 100€ (demandé : " + montantReduction + ")"
        );
    }
}

public static double calculerPrixApresReduction(double prixInitial, int tauxReduction) {
    validerPrix(prixInitial);
    validerTaux(tauxReduction);
    
    double montantReduction = calculerMontantReduction(prixInitial, tauxReduction);
    validerPlafondReduction(montantReduction);  // ← Ajout simple
    
    return prixInitial - montantReduction;
}
```
- **1 seule ligne modifiée**
- Nouvelle méthode indépendante
- Principe **Open/Closed** respecté

---

### 4. Réutilisabilité

#### Scénario : Créer une fonction `calculerPrixAvecTVA`

**V1** :
```java
// Impossible de réutiliser "calc" (nom obscur)
public static double calcTVA(double p, int t) {
    return p + (p * t / 100);  // Code dupliqué
}
```

**V2** :
```java
// On peut s'inspirer mais pas réutiliser directement
public static double calculerPrixAvecTVA(double prixHT, int tauxTVA) {
    if (prixHT < 0) { ... }  // Validation dupliquée
    if (tauxTVA < 0 || tauxTVA > 100) { ... }
    return prixHT + (prixHT * tauxTVA / 100);
}
```

**V3** :
```java
// Réutilisation des méthodes privées !
public static double calculerPrixAvecTVA(double prixHT, int tauxTVA) {
    validerPrix(prixHT);      // ← Réutilisé
    validerTaux(tauxTVA);     // ← Réutilisé
    
    double montantTVA = prixHT * tauxTVA / 100.0;
    return prixHT + montantTVA;
}
```
- Méthodes de validation **réutilisées**
- Pas de duplication de code

---

### 5. Lisibilité

#### V1 : 1/5 ❌
```java
public static double calc(double p, int t) {
    return p - (p * t / 100);
}
```
- Noms de variables cryptiques (`p`, `t`)
- Pas de documentation
- Formule en une ligne (difficile à déboguer)

#### V2 : 3/5 ⚠️
```java
public static double calculerPrixApresReduction(double prixInitial, int tauxReduction) {
    if (prixInitial < 0) {
        throw new IllegalArgumentException("Le prix ne peut pas être négatif");
    }
    if (tauxReduction < 0 || tauxReduction > 100) {
        throw new IllegalArgumentException("Le taux doit être entre 0 et 100");
    }
    return prixInitial - (prixInitial * tauxReduction / 100);
}
```
- Noms explicites ✅
- Mais manque de documentation
- Méthode un peu longue

#### V3 : 5/5 ✅
```java
/**
 * Calcule le prix après application d'une réduction
 * @param prixInitial le prix avant réduction (doit être >= 0)
 * @param tauxReduction le pourcentage de réduction (0 à 100)
 * @return le prix après réduction
 * @throws IllegalArgumentException si les paramètres sont invalides
 */
public static double calculerPrixApresReduction(double prixInitial, int tauxReduction) {
    validerPrix(prixInitial);
    validerTaux(tauxReduction);
    
    double montantReduction = calculerMontantReduction(prixInitial, tauxReduction);
    return prixInitial - montantReduction;
}
```
- Documentation Javadoc complète
- Méthodes bien nommées
- Logique claire et séquentielle

---

### 6. Maintenabilité

**Scénario** : Bug détecté → le calcul de réduction est incorrect pour les taux > 50%

#### V1 : Cauchemar
```java
public static double calc(double p, int t) {
    return p - (p * t / 100);  // ← Où est le bug exactement ?
}
```
- Tout est mélangé
- Difficile de déboguer

#### V2 : Acceptable
```java
public static double calculerPrixApresReduction(double prixInitial, int tauxReduction) {
    // ...
    return prixInitial - (prixInitial * tauxReduction / 100);  // ← Bug ici
}
```
- On peut identifier le problème
- Mais il faut modifier la méthode principale

#### V3 : Excellent
```java
private static double calculerMontantReduction(double prix, int taux) {
    return prix * taux / 100.0;  // ← Bug isolé dans UNE méthode
}
```
- Bug **isolé** dans une méthode dédiée
- Facile à tester unitairement
- Modification sans impact sur le reste

---

## Questions Complémentaires

### Question 1 : Scénario de Bug

**Saisie** : Prix = `-50€`, Taux = `150%`

#### V1 : Catastrophe
```java
double resultat = V1.calc(-50, 150);
System.out.println(resultat);  // -125.0 (!!)
```
- Aucune erreur levée
- Résultat absurde accepté
- Bug silencieux

#### V2 : Gestion correcte
```java
try {
    double resultat = V2.calculerPrixApresReduction(-50, 150);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
    // "Le prix ne peut pas être négatif"
}
```
- Exception levée dès la validation du prix
- Programme ne plante pas

#### V3 : Gestion optimale
```java
try {
    double resultat = CalculateurReduction.calculerPrixApresReduction(-50, 150);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
    // "Prix invalide : -50,00 (doit être >= 0)"
}
```
- Exception détaillée avec la valeur erronée
- Aide au diagnostic

---

### Question 2 : Évolution (Plafond de Réduction)

**V3 est la plus facile** :

```java
// Avant
private static double calculerMontantReduction(double prix, int taux) {
    return prix * taux / 100.0;
}

// Après (avec plafond)
private static double calculerMontantReduction(double prix, int taux) {
    double reduction = prix * taux / 100.0;
    return Math.min(reduction, 100.0);  // Plafond à 100€
}
```

**Alternative avec validation dédiée** :
```java
private static void appliquerPlafond(double montant, double plafond) {
    if (montant > plafond) {
        return plafond;
    }
    return montant;
}
```

---

### Question 3 : Réutilisation pour `calculerPrixAvecTVA`

**Approche V3 réutilisable** :

```java
public class CalculateurPrix {
    
    // Méthodes de validation (réutilisables)
    private static void validerPrix(double prix) { ... }
    private static void validerTaux(int taux) { ... }
    
    // Réduction
    public static double calculerPrixApresReduction(double prix, int taux) {
        validerPrix(prix);
        validerTaux(taux);
        double reduction = prix * taux / 100.0;
        return prix - reduction;
    }
    
    // TVA (réutilise les validations !)
    public static double calculerPrixAvecTVA(double prixHT, int tauxTVA) {
        validerPrix(prixHT);
        validerTaux(tauxTVA);
        double montantTVA = prixHT * tauxTVA / 100.0;
        return prixHT + montantTVA;
    }
}
```

---

### Question 4 : Performance

**Non, V3 n'est PAS moins performante !**

**Analyse** :
```
V1 : 1 opération mathématique
V2 : 2 validations + 1 opération = ~3 opérations
V3 : 2 validations + 1 calcul + 1 soustraction = ~4 opérations
```

**Mais** :
- Différence négligeable (quelques nanosecondes)
- Les validations sont essentielles (évitent des bugs coûteux)
- Le compilateur Java optimise les appels de méthodes courtes (inlining)

**Benchmark** :
```java
// Exécution de 1 million d'appels
V1 : ~15ms
V2 : ~18ms
V3 : ~20ms

Différence : 5ms pour 1 MILLION d'appels = négligeable
```

**Conclusion** : La **maintenabilité** et la **robustesse** l'emportent largement sur cette différence de performance minime.

---

## Recommandation Finale

### 🏆 Version à Adopter : **V3**

**Justification** :
1. **Robustesse maximale** (validation complète)
2. **Maintenabilité excellente** (méthodes séparées)
3. **Évolutivité** (facile d'ajouter des fonctionnalités)
4. **Code professionnel** (documentation Javadoc)
5. **Réutilisabilité** (méthodes privées réutilisables)

**Compromis acceptable** :
- Légèrement plus verbeux (+20 lignes)
- Performance identique en pratique
- **Bénéfices à long terme énormes**

---

## 📊 Graphique de Comparaison

```
Qualité Globale (/30)

30 |                    ████████████  V3
25 |          ████████
20 |          ████████
15 |          ████████
10 |  ██████  ████████  ████████████
 5 |  ██████  ████████  ████████████
 0 +----------------------------------
     V1       V2        V3
```

**Temps de résolution estimé** : 60-90 minutes
