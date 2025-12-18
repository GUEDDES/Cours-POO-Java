# Solution Exercice 1 : Analyse du Cycle de Vie

## 📋 Rappel de l'énoncé

Analyser le cycle de vie d'une application mobile (ex: application de gestion de tâches) en identifiant les différentes phases et les livrables associés.

---

## ✅ Solution Détaillée

### Application : **TaskMaster** - Gestionnaire de Tâches Mobile

---

## Phase 1 : Analyse et Spécification

### Activités
1. **Étude de marché**
   - Analyse des concurrents (Todoist, Microsoft To-Do, Any.do)
   - Identification des besoins utilisateurs
   - Définition du public cible (étudiants, professionnels, particuliers)

2. **Recueil des besoins**
   - Interviews avec utilisateurs potentiels
   - Questionnaires en ligne
   - Analyse des retours sur applications similaires

3. **Spécifications fonctionnelles**
   - Créer/modifier/supprimer des tâches
   - Organiser par catégories
   - Définir des priorités (haute, moyenne, basse)
   - Notifications et rappels
   - Synchronisation cloud

### Livrables
- ✅ **Cahier des charges** (30 pages)
- ✅ **Document d'exigences** (SRS - Software Requirements Specification)
- ✅ **User Stories** (50+ stories)
- ✅ **Personas** (3 profils types)
- ✅ **Wireframes** (maquettes basse fidélité)

### Durée
**4 semaines**

---

## Phase 2 : Conception

### Activités
1. **Architecture logicielle**
   - Architecture client-serveur
   - API RESTful pour synchronisation
   - Base de données locale (SQLite)
   - Base de données distante (PostgreSQL)

2. **Conception UI/UX**
   - Mockups haute fidélité (Figma)
   - Prototypes interactifs
   - Tests utilisateurs sur prototypes

3. **Modélisation**
   - Diagrammes UML (cas d'utilisation, classes, séquence)
   - Schéma de base de données
   - API endpoints documentation

### Livrables
- ✅ **Document d'architecture** (DAT)
- ✅ **Diagrammes UML** (10+ diagrammes)
- ✅ **Maquettes UI/UX** (Figma)
- ✅ **Schéma de base de données**
- ✅ **Documentation API**

### Durée
**6 semaines**

---

## Phase 3 : Développement

### Activités
1. **Configuration de l'environnement**
   - Setup Android Studio / Xcode
   - Configuration Git + GitHub
   - Mise en place CI/CD (GitHub Actions)

2. **Développement par sprints** (Méthode Agile)
   
   **Sprint 1** (2 semaines) : Fonctionnalités de base
   - Écran d'accueil
   - Création de tâches
   - Liste des tâches
   
   **Sprint 2** (2 semaines) : Organisation
   - Catégories
   - Priorités
   - Filtres et recherche
   
   **Sprint 3** (2 semaines) : Notifications
   - Rappels
   - Notifications push
   
   **Sprint 4** (2 semaines) : Synchronisation
   - Backend API
   - Sync cloud
   - Gestion offline

3. **Tests unitaires**
   - JUnit pour logique métier
   - Couverture de code > 80%

### Livrables
- ✅ **Code source** (GitHub repository)
- ✅ **Tests unitaires** (500+ tests)
- ✅ **Documentation technique** (Javadoc/KDoc)
- ✅ **Build CI/CD** (automatisé)

### Durée
**8 semaines** (4 sprints)

---

## Phase 4 : Tests et Validation

### Activités
1. **Tests fonctionnels**
   - Tests manuels de toutes les fonctionnalités
   - Vérification des user stories

2. **Tests non-fonctionnels**
   - Tests de performance (temps de réponse < 1s)
   - Tests de charge (1000+ utilisateurs simultanés)
   - Tests de sécurité (OWASP Top 10)

3. **Tests d'acceptation utilisateur** (UAT)
   - Beta testing avec 100 utilisateurs
   - Collecte de feedback

4. **Correction de bugs**
   - Priorité haute : 0 bug
   - Priorité moyenne : < 5 bugs
   - Priorité basse : documentés pour v2

### Livrables
- ✅ **Rapport de tests** (200+ tests)
- ✅ **Liste des bugs corrigés** (tracker)
- ✅ **Rapport de performance**
- ✅ **Certificat de sécurité**

### Durée
**4 semaines**

---

## Phase 5 : Déploiement

### Activités
1. **Préparation au lancement**
   - Soumission Google Play Store
   - Soumission Apple App Store
   - Configuration serveurs production

2. **Marketing**
   - Site web de présentation
   - Vidéo de démonstration
   - Campagne sur réseaux sociaux

3. **Documentation utilisateur**
   - Guide d'utilisation
   - FAQ
   - Tutoriels vidéo

### Livrables
- ✅ **Application publiée** (Stores)
- ✅ **Site web** (landing page)
- ✅ **Documentation utilisateur**
- ✅ **Support technique** (email + chat)

### Durée
**2 semaines**

---

## Phase 6 : Maintenance et Évolution

### Activités
1. **Maintenance corrective**
   - Surveillance des crashs (Firebase Crashlytics)
   - Correction des bugs signalés
   - Temps de réponse : < 48h pour bugs critiques

2. **Maintenance évolutive**
   - Nouvelles fonctionnalités (basées sur feedback)
   - Améliorations UI/UX
   - Optimisations de performance

3. **Mises à jour régulières**
   - Version mineure : tous les mois
   - Version majeure : tous les 6 mois

### Livrables
- ✅ **Mises à jour** (releases régulières)
- ✅ **Notes de version** (changelog)
- ✅ **Rapport d'utilisation** (analytics)

### Durée
**Continue** (toute la vie du produit)

---

## 📊 Synthèse du Cycle de Vie

```
┌────────────────────────────────────────────────────────┐
│              CYCLE DE VIE - TaskMaster                 │
├────────────────────────────────────────────────────────┤
│                                                        │
│  [1] Analyse ───────────► 4 semaines                  │
│       └─ Cahier des charges, Spécifications           │
│                                                        │
│  [2] Conception ───────► 6 semaines                   │
│       └─ Architecture, Maquettes, UML                 │
│                                                        │
│  [3] Développement ────► 8 semaines                   │
│       └─ Code, Tests unitaires, CI/CD                 │
│                                                        │
│  [4] Tests ────────────► 4 semaines                   │
│       └─ Tests fonctionnels, UAT, Bugfix              │
│                                                        │
│  [5] Déploiement ──────► 2 semaines                   │
│       └─ Publication, Marketing, Documentation        │
│                                                        │
│  [6] Maintenance ──────► Continue                     │
│       └─ Bugfix, Évolutions, Mises à jour             │
│                                                        │
│  TOTAL (hors maintenance) : 24 semaines (~6 mois)     │
└────────────────────────────────────────────────────────┘
```

---

## 💡 Points Clés

1. **Méthodologie** : Agile (Scrum) pour le développement
2. **Équipe** : 5 personnes (1 PM, 2 devs, 1 designer, 1 QA)
3. **Budget estimé** : 150 000€
4. **Technologies** : Kotlin (Android), Swift (iOS), Node.js (Backend)

---

## ✅ Critères de Validation

- ✅ Toutes les phases du cycle de vie identifiées
- ✅ Livrables précis pour chaque phase
- ✅ Durées réalistes
- ✅ Méthodologie appropriée (Agile)
- ✅ Documentation complète
