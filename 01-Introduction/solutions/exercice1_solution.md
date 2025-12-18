# Solution Exercice 1 : Analyse du Cycle de Vie

## Cas 1 : Application Mobile "FitTrack" 📱

### 1. Phase mal exécutée
**Réponse** : Phase d'**Analyse des Besoins** et **Spécification**

### 2. Questions à poser
Lors de la phase d'analyse, les questions suivantes auraient dû être posées :

- Sur quelles plateformes l'application doit-elle fonctionner ? (iOS, Android, Web, les trois ?)
- Quel est le budget alloué pour chaque plateforme ?
- Quelle est la priorité des plateformes ? (lancement simultané ou progressif ?)
- Combien d'utilisateurs cibles par plateforme ?
- Y a-t-il des contraintes techniques spécifiques par plateforme ?

### 3. Impact sur le projet

**Impact financier** :
- Coût de développement Android = ~80% du coût iOS (développement quasi complet à refaire)
- Budget initial : supposons 50 000€ pour iOS
- Coût supplémentaire : ~40 000€ pour Android
- **Total : +80% de dépassement budgétaire**

**Impact planning** :
- Délai initial : 6 mois
- Délai supplémentaire : 4-5 mois pour Android
- **Retard : +67% du délai initial**

**Impact business** :
- Perte de parts de marché (Android = 70% du marché mondial)
- Insatisfaction client
- Risque de rupture de contrat

**Leçon** : Une heure investie en analyse peut économiser des semaines de développement.

---

## Cas 2 : Site E-commerce "TechStore" 🛒

### 1. Phase négligée
**Réponse** : Phase de **Tests** (spécifiquement les **tests de performance**)

### 2. Type de test manquant
**Tests de charge (Load Testing)** :

- **Test de charge** : Simuler 100, 500, 1000 utilisateurs simultanés
- **Test de stress** : Trouver le point de rupture du système
- **Test d'endurance** : Vérifier la stabilité sur une longue période
- **Test de pic** : Simuler un afflux soudain (ex: ventes flash)

**Outils recommandés** :
- JMeter (gratuit)
- Gatling
- LoadRunner (commercial)

### 3. Solution préventive

**Architecture technique** :
```
Utilisateurs → Load Balancer → [Serveur 1, Serveur 2, Serveur 3]
                                        ↓
                                  Base de données
                                   (répliquée)
```

**Mesures correctives** :
1. **Court terme** :
   - Augmenter les ressources serveur (RAM, CPU)
   - Activer le cache (Redis/Memcached)
   - Optimiser les requêtes SQL

2. **Long terme** :
   - Architecture scalable (microservices)
   - CDN pour les ressources statiques
   - Tests de performance en continu (CI/CD)

**Procédure recommandée** :
```
Étape 1 : Définir les SLA (Service Level Agreement)
  → Exemple : "Le site doit supporter 500 utilisateurs simultanés
               avec un temps de réponse < 2s"

Étape 2 : Tests de charge AVANT la mise en production

Étape 3 : Monitoring en temps réel (New Relic, Datadog)
```

---

## Cas 3 : Logiciel de Gestion "InvoiceX" 💼

### 1. Critère de qualité non respecté
**Réponse** : **Évolutivité** (Extensibilité)

Le logiciel a été conçu de manière **rigide**, sans anticipation des évolutions futures.

### 2. Erreur de conception

**Mauvaise conception (probablement)** :
```java
// Code rigide et non évolutif
class Facture {
    private String numero;
    private double montant;
    
    public void genererPDF() {
        // Code spécifique aux factures
        // Logique en dur
    }
}
```

**Bonne conception (évolutive)** :
```java
// Architecture flexible
abstract class Document {
    protected String numero;
    protected double montant;
    
    public abstract void genererPDF();
}

class Facture extends Document {
    @Override
    public void genererPDF() {
        // Génération facture
    }
}

class Devis extends Document {  // Facile d'ajouter
    @Override
    public void genererPDF() {
        // Génération devis
    }
}
```

### 3. Anticipation du besoin

**Techniques d'anticipation** :

1. **Analyse métier approfondie** :
   - Interviewer plusieurs utilisateurs
   - Étudier les processus existants
   - Question clé : "Quelles évolutions envisagez-vous dans les 2-3 prochaines années ?"

2. **Design Patterns** :
   - **Strategy** : pour des comportements variables
   - **Factory** : pour créer différents types de documents
   - **Template Method** : pour partager du code commun

3. **Principes SOLID** :
   - **Open/Closed** : Ouvert à l'extension, fermé à la modification
   - Exemple : Ajouter un nouveau type de document SANS modifier le code existant

4. **Architecture modulaire** :
```
Module Core (Factures)
  ↓
Module Extension 1 (Devis) ← Ajouté ultérieurement
  ↓
Module Extension 2 (Bons de commande) ← Ajouté encore plus tard
```

---

## Question 4 : Classement par Gravité

### Classement (du plus au moins grave)

**1. 🥇 Cas 1 - Application Mobile (Le plus grave)**

**Raison** :
- Erreur dès la phase d'**analyse** (début du projet)
- Impact : **+80% budget**, **+67% délai**
- 70% du marché ignoré (Android)
- Risque commercial majeur

---

**2. 🥈 Cas 3 - Logiciel de Gestion**

**Raison** :
- Dette technique importante
- Coût de réécriture élevé
- Impact sur la relation client (frustration)
- Mais : le logiciel **fonctionne**, pas de perte immédiate

---

**3. 🥉 Cas 2 - Site E-commerce (Le moins grave)**

**Raison** :
- Erreur **détectée rapidement** (à la mise en production)
- Solutions techniques **rapides** disponibles
- Coût de correction **relativement faible** (scalabilité)
- Impact limité dans le temps

---

## Question 5 : Méthodologie Adaptée

### Cas 1 : Application Mobile

**Méthodologie recommandée** : **Agile (Scrum)**

**Justification** :
- Besoins susceptibles d'évoluer
- Feedback utilisateur régulier
- Livraisons incrémentales (iOS d'abord, puis Android)
- Sprints de 2 semaines

**Plan d'action** :
```
Sprint 1-6 : MVP iOS (fonctionnalités essentielles)
Sprint 7-8 : Tests utilisateurs + ajustements
Sprint 9-14 : Version Android
Sprint 15 : Lancement simultané
```

---

### Cas 2 : Site E-commerce

**Méthodologie recommandée** : **Agile avec DevOps**

**Justification** :
- Besoin de déploiements fréquents
- Tests de performance continus
- Monitoring en temps réel
- Réactivité aux pics de charge

**Pratiques clés** :
- **CI/CD** (Continuous Integration/Deployment)
- **Tests automatisés** (unitaires, intégration, performance)
- **Infrastructure as Code** (scalabilité automatique)

---

### Cas 3 : Logiciel de Gestion

**Méthodologie recommandée** : **Cascade avec prototypage**

**Justification** :
- Logiciel de gestion = besoins **stables** et **bien définis**
- Réglementation stricte (comptabilité)
- Documentation complète nécessaire
- Validation par étapes

**Avec amélioration** :
- Phase de **prototypage initial** pour valider l'ergonomie
- Séances de **validation intermédiaires** avec le client
- **Design patterns** pour l'évolutivité

**Planning** :
```
Mois 1-2 : Analyse approfondie + Prototypage
Mois 3-4 : Conception détaillée (avec extensibilité)
Mois 5-8 : Développement
Mois 9-10 : Tests
Mois 11 : Déploiement
Mois 12+ : Maintenance évolutive
```

---

## 📊 Tableau Récapitulatif

| Cas | Phase critique | Impact | Méthodologie | Prévention |
|-----|---------------|--------|--------------|------------|
| **FitTrack** | Analyse | Très élevé | Agile | Cahier des charges détaillé |
| **TechStore** | Tests | Moyen | Agile + DevOps | Tests de charge systématiques |
| **InvoiceX** | Conception | Élevé | Cascade + Prototypage | Architecture évolutive |

---

## 🎯 Leçons à Retenir

1. **Investir dans l'analyse** = économies futures
2. **Tester tôt et souvent**, surtout les performances
3. **Concevoir pour le changement** (SOLID, patterns)
4. **Choisir la méthodologie** selon le contexte
5. **Communication client** = clé du succès

---

**Temps de résolution estimé** : 45-60 minutes
