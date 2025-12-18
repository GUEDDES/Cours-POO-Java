# Exercice 2 : Modéliser une Plateforme de Streaming Musical 🎵

## 🎯 Objectif
Concevoir un système orienté objet complet pour une plateforme type Spotify.

---

## 📋 Cahier des Charges

**Description du système** :

> "Une plateforme de streaming musical permet aux utilisateurs d'écouter de la musique en ligne.
>
> Chaque utilisateur a un pseudonyme unique, une adresse e-mail, un type d'abonnement (gratuit ou premium) et une playlist favorite. Un utilisateur premium peut télécharger des chansons hors ligne.
>
> Une chanson a un titre, un artiste, une durée (en secondes), un genre musical (pop, rock, jazz, etc.) et un nombre d'écoutes. Une chanson appartient à un album.
>
> Un album a un titre, une année de sortie, un artiste et contient plusieurs chansons.
>
> Un artiste a un nom, une biographie et une liste d'albums publiés. Un artiste peut publier un nouvel album.
>
> Une playlist a un nom, un propriétaire (utilisateur) et contient une liste de chansons. Un utilisateur peut créer, modifier et partager ses playlists.
>
> La plateforme peut recommander des chansons à un utilisateur en fonction de son historique d'écoute et générer un top 10 hebdomadaire des chansons les plus écoutées."

---

## 📊 Questions

### Partie 1 : Modélisation (12 points)

#### Question 1.1 : Classes Principales (3 points)
Listez les **5 classes principales** à modéliser.

#### Question 1.2 : Attributs Détaillés (5 points)
Pour chaque classe, donnez :
- Les attributs avec leur **type précis** (int, String, boolean, etc.)
- Une **courte justification** pour les attributs moins évidents

#### Question 1.3 : Méthodes Principales (4 points)
Pour chaque classe, listez au moins **3 méthodes** avec leurs signatures complètes.

---

### Partie 2 : Relations entre Classes (4 points)

Identifiez et décrivez les **relations** entre les classes :

**Questions** :
1. Quelle classe contient une liste d'une autre classe ? (2 relations minimum)
2. Quel type de relation existe entre Album et Chanson ?
3. Un utilisateur peut-il avoir plusieurs playlists ? Comment le modéliser ?

---

### Partie 3 : Cas d'Usage (4 points)

Décrivez **2 scénarios complets** :

#### Scénario 1 : Écouter une chanson
```
1. L'utilisateur "Bob" recherche la chanson "Bohemian Rhapsody"
2. ...
3. ...
4. Le compteur d'écoutes de la chanson est incrémenté
```

#### Scénario 2 : Créer une playlist
```
1. L'utilisateur "Alice" crée une nouvelle playlist "Workout 2024"
2. ...
3. ...
```

---

### Partie 4 : Questions de Conception (Bonus +3 points)

1. **Différenciation Gratuit/Premium** :  
   Comment gérer les différences entre utilisateurs gratuits et premium ? (attribut, classe séparée, etc.)

2. **Historique d'Écoute** :  
   Où stocker l'historique des chansons écoutées par un utilisateur ? Quel type de données ?

3. **Genres Musicaux** :  
   Faut-il une classe `Genre` ou un simple attribut `String genre` dans `Chanson` ? Justifiez.

---

## 📦 Livrable Attendu

Un document structuré contenant :

1. **Page 1** : Liste des classes avec attributs et méthodes
2. **Page 2** : Diagramme de classes ASCII complet
3. **Page 3** : Relations entre classes + Scénarios
4. **Page 4** (bonus) : Réponses aux questions de conception

---

## 📝 Critères d'Évaluation

| Critère | Points |
|---------|--------|
| Classes complètes et pertinentes | 3 |
| Attributs bien typés et justifiés | 5 |
| Méthodes avec signatures claires | 4 |
| Relations entre classes identifiées | 4 |
| Scénarios détaillés et cohérents | 4 |
| **Bonus** : Questions de conception | +3 |
| **TOTAL** | /20 (+3) |

---

## 💡 Conseils

- 🔍 **Analysez** : Relisez le cahier des charges phrase par phrase
- 🎨 **Modélisez** : Dessinez un brouillon avant de rédiger
- ⚖️ **Équilibrez** : Ne sur-compliquez pas, restez pragmatique
- 🔄 **Vérifiez** : Assurez-vous que tous les besoins sont couverts

---

**Durée estimée** : 2 heures  
**Difficulté** : ⭐⭐⭐⭐☆

[Voir la solution →](../solutions/exercice2_solution.md)
