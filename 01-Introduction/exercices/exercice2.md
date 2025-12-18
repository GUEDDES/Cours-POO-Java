# Exercice 2 : Évaluation de la Qualité Logicielle

## 🎯 Objectif
Évaluer la qualité de code Java selon les 6 critères.

---

## 📋 Énoncé

On vous présente 3 versions d'une fonction qui calcule une réduction commerciale. Évaluez chaque version selon les critères de qualité.

---

### Version 1 : Code Initial

```java
public class V1 {
    public static double calc(double p, int t) {
        return p - (p * t / 100);
    }
}
```

---

### Version 2 : Code Amélioré

```java
public class V2 {
    public static double calculerPrixApresReduction(double prixInitial, int tauxReduction) {
        if (prixInitial < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        if (tauxReduction < 0 || tauxReduction > 100) {
            throw new IllegalArgumentException("Le taux doit être entre 0 et 100");
        }
        return prixInitial - (prixInitial * tauxReduction / 100);
    }
}
```

---

### Version 3 : Code Professionnel

```java
public class CalculateurReduction {
    
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
    
    private static void validerPrix(double prix) {
        if (prix < 0) {
            throw new IllegalArgumentException(
                String.format("Prix invalide : %.2f (doit être >= 0)", prix)
            );
        }
    }
    
    private static void validerTaux(int taux) {
        if (taux < 0 || taux > 100) {
            throw new IllegalArgumentException(
                String.format("Taux invalide : %d (doit être entre 0 et 100)", taux)
            );
        }
    }
    
    private static double calculerMontantReduction(double prix, int taux) {
        return prix * taux / 100.0;
    }
}
```

---

## 📊 Questions

Pour **chaque version**, évaluez (note de 0 à 5) les critères suivants et justifiez :

### Tableau à compléter

| Critère | V1 | V2 | V3 | Justification |
|---------|----|----|----|--------------|
| **1. Correction** | ? | ? | ? | |
| **2. Robustesse** | ? | ? | ? | |
| **3. Évolutivité** | ? | ? | ? | |
| **4. Réutilisabilité** | ? | ? | ? | |
| **5. Lisibilité** | ? | ? | ? | |
| **6. Maintenabilité** | ? | ? | ? | |
| **TOTAL (/30)** | ? | ? | ? | |

---

## 💡 Questions Complémentaires

1. **Scénario de bug** :  
   Un utilisateur saisit un prix de `-50€` et un taux de `150%`. Quelle version gère correctement ce cas ? Que se passe-t-il pour les autres ?

2. **Évolution** :  
   On veut ajouter un plafond de réduction (max 100€). Quelle version est la plus facile à modifier ? Montrez le code.

3. **Réutilisation** :  
   Vous devez créer une fonction `calculerPrixAvecTVA(prix, tauxTVA)`. Quelle approche de la V3 pouvez-vous réutiliser ?

4. **Performance** :  
   La V3 a plus de lignes. Est-elle moins performante ? Expliquez.

---

## 🎯 Livrable Attendu

1. Tableau complété avec notes et justifications
2. Réponses argumentées aux 4 questions
3. Votre recommandation : quelle version adopteriez-vous en production ?

---

**Durée estimée** : 1 heure  
**Difficulté** : ⭐⭐⭐☆☆

[Voir la solution →](../solutions/exercice2_solution.md)
