# Solution Exercice 1 : Analyse du Cycle de Vie

## Cas 1 : Application Mobile "FitTrack" 📱

### 1. Phase mal exécutée

**Réponse** : La phase **Analyse des Besoins** (Phase 1)

**Explication** :  
Le client voulait une application sur Android ET iPhone, mais cette exigence n'a pas été correctement identifiée. L'équipe a développé uniquement pour iOS.

---

### 2. Questions à poser au début

**Questions essentielles** :
1. ❓ Sur quelles **plateformes** l'application doit-elle fonctionner ?
   - iOS uniquement ?
   - Android uniquement ?
   - Les deux ?

2. ❓ Quelle est la **priorité** ?
   - Lancer d'abord sur iOS puis Android ?
   - Lancement simultané ?

3. ❓ Quel est le **budget** et le **délai** ?
   - Développer pour 2 plateformes coûte 2x plus cher
   - Développement natif ou cross-platform (React Native, Flutter) ?

4. ❓ Quelle est la **cible principale** ?
   - Part de marché iOS vs Android dans le pays cible

---

### 3. Impact sur le projet

**Coût** :  
- 💸 Développement Android complémentaire : +100% du budget
- 💸 Ou refonte en cross-platform : +50% du budget

**Délai** :  
- ⏰ +6 mois pour développer la version Android
- ⏰ Ou +2 mois pour migrer vers React Native/Flutter

**Autres impacts** :
- 😢 Client insatisfait
- 📉 Perte de marché (concurrents avancés)
- 💔 Confiance érodée

---

## Cas 2 : Site E-commerce "TechStore" 🛒

### 1. Phase négligée

**Réponse** : La phase **Tests** (Phase 5), spécifiquement les **tests de performance**

**Explication** :  
Le site n'a pas été testé avec une charge réaliste (50 utilisateurs simultanés). Les tests ont probablement été faits avec 1-5 utilisateurs seulement.

---

### 2. Type de test nécessaire

**Tests de charge/performance** :

```
Outils utilisés :
- JMeter (Apache)
- Gatling
- LoadRunner

Scénario de test :
1. Simuler 50, 100, 200 utilisateurs simultanés
2. Chaque utilisateur :
   - Parcourt le catalogue
   - Ajoute produits au panier
   - Passe commande
3. Mesurer :
   - Temps de réponse
   - Taux d'erreur
   - Utilisation CPU/Mémoire serveur
```

**Critères de réussite** :
- ✅ Temps de réponse < 2 secondes pour 100 utilisateurs
- ✅ Taux d'erreur < 1%
- ✅ Pas de crash

---

### 3. Solution pour l'avenir

**Procédure recommandée** :

1. **Intégrer les tests de performance** dans le cycle de développement
   - Tests automatiques à chaque déploiement
   - CI/CD avec tests de charge

2. **Définir des critères de performance** dès le cahier des charges
   ```
   Exigences non fonctionnelles :
   - Support de 200 utilisateurs simultanés
   - Temps de réponse < 2s
   - Disponibilité 99.9%
   ```

3. **Mettre en place un environnement de pré-production**
   - Identique à la production
   - Tests réalistes avant mise en ligne

4. **Monitoring en production**
   - Alertes si temps de réponse dégradé
   - Dashboards temps réel

---

## Cas 3 : Logiciel de Gestion "InvoiceX" 💼

### 1. Critère de qualité non respecté

**Réponse** : **Évolutivité** (et Réutilisabilité)

**Explication** :  
Le code a été conçu de manière rigide, sans anticiper les évolutions futures. Ajouter une nouvelle fonctionnalité (devis) nécessite une refonte complète.

---

### 2. Erreur de conception

**Problèmes probables** :

1. **Couplage fort** : Code trop dépendant, difficile à modifier
   ```java
   // ❌ MAUVAIS : Logique mélangée
   class Facture {
       void genererPDF() {
           // Génération PDF imbriquée dans la classe
           // Impossible de réutiliser pour un devis
       }
   }
   ```

2. **Absence de modélisation abstraite**
   ```java
   // ✅ BON : Utiliser une classe parent
   abstract class Document {
       void genererPDF();
   }
   
   class Facture extends Document { }
   class Devis extends Document { }  // Facile à ajouter
   ```

3. **Code dupliqué** au lieu de réutilisable

---

### 3. Comment anticiper ce besoin ?

**Bonnes pratiques** :

1. **Analyse des besoins approfondie**
   - Poser la question : "Quelles évolutions prévoyez-vous dans les 2 prochaines années ?"
   - Même si non implémenté immédiatement, concevoir en prévoyant l'extension

2. **Concevoir avec des abstractions**
   ```
   Plutôt que :
   - Facture
   
   Concevoir :
   - Document (abstrait)
     - Facture
     - Devis (facile à ajouter)
     - Bon de commande (futur)
   ```

3. **Appliquer les principes SOLID**
   - **Open/Closed** : Ouvert à l'extension, fermé à la modification
   - Permet d'ajouter de nouvelles fonctionnalités sans modifier l'existant

4. **Revues de code régulières**
   - Vérifier la qualité et l'évolutivité

---

## Question 4 : Classement par Gravité

### Classement (du plus grave au moins grave)

**1️⁣ Cas 2 (TechStore)** - Le plus grave  
**Raison** :  
- 🔥 Impact immédiat sur les **utilisateurs en production**
- 💸 Perte de **revenus** (clients ne peuvent pas acheter)
- 💔 **Réputation** endommagée (site planté = amateurisme)
- ⏱️ Nécessite correction **urgente**

**2️♃ Cas 1 (FitTrack)** - Grave  
**Raison** :  
- 💰 Coût élevé (+100% budget)
- ⏰ Retard important (6 mois)
- 🎯 Mais utilisateurs iOS peuvent utiliser l'app

**3️♃ Cas 3 (InvoiceX)** - Moins grave  
**Raison** :  
- ✅ Le logiciel **fonctionne** pour son usage actuel
- 🔧 Évolution possible (même si coûteuse)
- 📈 Problème de dette technique, pas de blocage immédiat

---

## Question 5 : Méthodologie Adaptée

### Cas 1 : FitTrack (Application Mobile)

**Méthodologie recommandée** : 🏃 **AGILE (Scrum)**

**Justification** :
- ✅ Livraisons incrémentales (version iOS d'abord, puis Android)
- ✅ Feedback utilisateurs fréquent
- ✅ Adaptation aux changements (fonctionnalités mobiles évoluent vite)
- ✅ Marché mobile très concurrentiel = besoin de rapidité

**Mise en œuvre** :
```
Sprint 1-3 : MVP iOS (fonctionnalités de base)
Sprint 4-6 : Améliorations iOS selon feedback
Sprint 7-9 : Version Android
```

---

### Cas 2 : TechStore (E-commerce)

**Méthodologie recommandée** : 🌊 **Cascade** (avec phase de tests renforcée)

**Justification** :
- ✅ Exigences stables et bien connues (e-commerce = fonctionnalités standard)
- ✅ Architecture fixe (catalogue, panier, paiement)
- ✅ Besoin de **tests approfondis** avant mise en production
- ✅ Sécurité critique (paiements) = validation rigoureuse

**Phases clés** :
```
1. Analyse : 2 semaines
2. Conception : 3 semaines
3. Développement : 8 semaines
4. Tests (RENFORCÉS) : 4 semaines  ← IMPORTANT
   - Tests unitaires
   - Tests d'intégration
   - Tests de charge  ← CLÉ
   - Tests de sécurité
5. Déploiement : 1 semaine
```

---

### Cas 3 : InvoiceX (Logiciel de Gestion)

**Méthodologie recommandée** : 🏃 **AGILE (avec refactoring continu)**

**Justification** :
- ✅ Besoins évolutifs (nouvelles fonctionnalités régulières)
- ✅ Feedback utilisateurs essentiel (logiciel métier)
- ✅ Refactoring incrémental pour améliorer l'évolutivité
- ✅ Livraisons fréquentes de nouvelles fonctionnalités

**Approche** :
```
Sprint 1-2 : Refactoring architecture (Document abstrait)
Sprint 3 : Implémentation Devis
Sprint 4 : Intégration avec Factures existantes
Sprint 5+ : Nouvelles fonctionnalités (Bons de commande, etc.)
```

---

## 🎯 Points Clés à Retenir

1. **Phase d'analyse** = fondation du projet (erreur = coûts élevés)
2. **Tests de performance** = essentiels pour applications web
3. **Évolutivité** = concevoir avec l'avenir en tête
4. **Méthodologie** = choisir selon le contexte du projet
5. **Communication** = clé pour éviter les malentendus

---

**Temps de réalisation moyen** : 45 minutes  
**Difficulté** : ⭐⭐☆☆☆
