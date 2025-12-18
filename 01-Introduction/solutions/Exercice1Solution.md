# Solution : Exercice 1 - Analyse de Cycle de Vie

## Énoncé
Choisir un logiciel et décrire ses phases de développement.

---

## Solution Exemple : Application Mobile de Livraison de Repas

### 📱 Logiciel Choisi : **FoodExpress** (Clone Uber Eats)

---

## 1️⃣ Phase d'Analyse

### Objectifs
- Permettre aux utilisateurs de commander des repas depuis leur smartphone
- Connecter restaurants, livreurs et clients

### Besoins Fonctionnels
- ✅ Inscription/Connexion utilisateur
- ✅ Recherche de restaurants par localisation
- ✅ Navigation dans le menu (photos, prix, descriptions)
- ✅ Panier d'achat et validation commande
- ✅ Paiement en ligne sécurisé
- ✅ Suivi de livraison en temps réel (GPS)
- ✅ Notation des restaurants et livreurs
- ✅ Historique des commandes

### Besoins Non-Fonctionnels
- ⚡ Performance : temps de chargement < 3 secondes
- 🔒 Sécurité : chiffrement des paiements (PCI-DSS)
- 📱 Compatibilité : iOS 14+ et Android 10+
- 🌍 Scalabilité : supporter 10 000 utilisateurs simultanés
- ♿ Accessibilité : support VoiceOver et TalkBack

### Contraintes
- Budget : 100 000€
- Délai : 6 mois
- Équipe : 5 développeurs + 1 designer

---

## 2️⃣ Phase de Conception

### Architecture Technique

```
┌─────────────────────────────────────────────────┐
│           ARCHITECTURE SYSTÈME                  │
├─────────────────────────────────────────────────┤
│                                                 │
│   [Application Mobile]                          │
│   - React Native (iOS/Android)                  │
│   - Redux pour état global                      │
│                                                 │
│            ↕ (HTTPS/REST)                       │
│                                                 │
│   [Serveur Backend]                             │
│   - Node.js + Express                           │
│   - API RESTful                                 │
│   - Authentification JWT                        │
│                                                 │
│            ↕                                     │
│                                                 │
│   [Base de Données]                             │
│   - PostgreSQL (données relationnelles)         │
│   - Redis (cache)                               │
│   - MongoDB (logs)                              │
│                                                 │
│   [Services Externes]                           │
│   - Stripe (paiements)                          │
│   - Google Maps API (géolocalisation)           │
│   - Firebase (notifications push)               │
│                                                 │
└─────────────────────────────────────────────────┘
```

### Modèle de Données (Simplifié)

```sql
Utilisateur
  - id
  - email
  - mot_de_passe_hash
  - nom
  - telephone
  - adresse

Restaurant
  - id
  - nom
  - adresse
  - latitude, longitude
  - note_moyenne
  - temps_preparation_moyen

Produit
  - id
  - restaurant_id
  - nom
  - description
  - prix
  - photo_url

Commande
  - id
  - utilisateur_id
  - restaurant_id
  - livreur_id
  - statut (en_attente, en_preparation, en_livraison, livree)
  - montant_total
  - date_commande

Livreur
  - id
  - nom
  - telephone
  - latitude, longitude (position actuelle)
  - disponible (boolean)
```

### Maquettes (Wireframes)

```
┌────────────────────┐
│  Écran Accueil     │
├────────────────────┤
│ [Recherche...]     │
│                    │
│ 📍 Votre adresse   │
│                    │
│ Restaurants près   │
│ de vous :          │
│                    │
│ ┌────────────────┐ │
│ │ 🍕 Pizza Roma  │ │
│ │ ⭐ 4.5 · 30min │ │
│ └────────────────┘ │
│ ┌────────────────┐ │
│ │ 🍣 Sushi Bar   │ │
│ │ ⭐ 4.8 · 25min │ │
│ └────────────────┘ │
└────────────────────┘
```

---

## 3️⃣ Phase de Réalisation

### Méthode : **Agile Scrum**

#### Sprint 1 (2 semaines)
- ✅ Authentification (inscription, connexion)
- ✅ Profil utilisateur
- ✅ Interface liste des restaurants

#### Sprint 2 (2 semaines)
- ✅ Détails restaurant + menu
- ✅ Panier d'achat
- ✅ Validation commande (sans paiement)

#### Sprint 3 (2 semaines)
- ✅ Intégration Stripe (paiements)
- ✅ Gestion des statuts de commande
- ✅ Notifications push

#### Sprint 4 (2 semaines)
- ✅ Suivi livraison en temps réel (GPS)
- ✅ Système de notation
- ✅ Historique des commandes

#### Sprint 5-6 (4 semaines)
- ✅ Tests et corrections de bugs
- ✅ Optimisation performances
- ✅ Déploiement

### Stack Technique

```
Frontend Mobile:
- React Native 0.72
- Redux Toolkit
- React Navigation
- Axios (requêtes HTTP)

Backend:
- Node.js 18 LTS
- Express 4.18
- Sequelize ORM
- JWT (authentification)

Base de Données:
- PostgreSQL 15
- Redis 7 (cache)

Outils:
- Git + GitHub
- Docker
- Jest (tests)
- ESLint + Prettier
```

---

## 4️⃣ Phase de Test

### Tests Unitaires
```javascript
// Exemple : test de validation de commande
describe('Commande', () => {
  test('devrait calculer le montant total correctement', () => {
    const commande = new Commande();
    commande.ajouterProduit({ prix: 10, quantite: 2 });
    commande.ajouterProduit({ prix: 5, quantite: 1 });
    expect(commande.getMontantTotal()).toBe(25);
  });
});
```

### Tests d'Intégration
- ✅ Création commande → Notification restaurant
- ✅ Paiement → Mise à jour statut commande
- ✅ Livraison → Notification client

### Tests d'Acceptation
- ✅ **Scénario 1** : Commander une pizza et recevoir notification de livraison
- ✅ **Scénario 2** : Annuler une commande dans les 2 minutes
- ✅ **Scénario 3** : Noter un restaurant après livraison

### Tests de Charge
- ✅ 1 000 utilisateurs simultanés : ✅ OK (temps réponse < 2s)
- ✅ 5 000 utilisateurs simultanés : ✅ OK (temps réponse < 3s)
- ✅ 10 000 utilisateurs simultanés : ⚠️ Optimisation nécessaire

---

## 5️⃣ Phase de Déploiement

### Environnements

```
Développement → Staging → Production
   (local)       (AWS)      (AWS)
```

### Infrastructure AWS

```
┌─────────────────────────────────────┐
│  CloudFront (CDN)                   │
└──────────┬──────────────────────────┘
           │
┌──────────▼──────────────────────────┐
│  Load Balancer (ELB)                │
└──────────┬──────────────────────────┘
           │
    ┌──────┴──────┐
    │             │
┌───▼───┐    ┌───▼───┐
│ EC2-1 │    │ EC2-2 │  (Serveurs Node.js)
└───┬───┘    └───┬───┘
    │             │
    └──────┬──────┘
           │
    ┌──────▼──────┐
    │ RDS         │  (PostgreSQL)
    │ (Multi-AZ)  │
    └─────────────┘
```

### Stratégie de Déploiement
- **Blue-Green Deployment** : zéro downtime
- **Rollback automatique** en cas d'erreur
- **Monitoring** : CloudWatch + Sentry

### App Store & Google Play
- ✅ Soumission iOS : 3 jours de validation
- ✅ Soumission Android : 24h de validation

---

## 6️⃣ Phase de Maintenance

### Maintenance Corrective
- **Bug critique** : correction sous 4h
- **Bug mineur** : correction sous 48h

### Maintenance Évolutive
- **Version 1.1** (1 mois après) : Filtres restaurants (cuisine, prix, note)
- **Version 1.2** (2 mois après) : Programme de fidélité
- **Version 2.0** (6 mois après) : Commande groupée

### Monitoring
- **Uptime** : 99.9%
- **Nombre d'utilisateurs actifs** : +15% par mois
- **Temps moyen de livraison** : 28 minutes
- **Taux de satisfaction** : 4.6/5

---

## 📊 Bilan du Projet

### Respect du Planning
- ✅ **Délai** : 6 mois respectés
- ✅ **Budget** : 95 000€ (sous le budget)

### Métriques de Succès
- 📱 **10 000 téléchargements** le premier mois
- 💰 **50 000€ de transactions** le premier mois
- ⭐ **4.5/5** sur l'App Store et Google Play

---

## 🎯 Conclusion

Ce projet illustre l'importance de **chaque phase du cycle de vie** :

1. **Analyse** : Définir clairement les besoins évite les malentendus
2. **Conception** : Une bonne architecture facilite l'évolution
3. **Réalisation** : L'approche Agile permet d'itérer rapidement
4. **Tests** : Détecter les bugs avant la production
5. **Déploiement** : Stratégie Blue-Green = zéro interruption
6. **Maintenance** : Écouter les utilisateurs pour améliorer

---

**Projet réussi grâce à une méthodologie rigoureuse ! ✅**
