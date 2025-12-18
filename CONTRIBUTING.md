# Guide de Contribution

Merci de votre intérêt pour contribuer au **Cours POO Java** ! 🎉

---

## 👋 Comment Contribuer ?

### 1. Signaler un Problème

Si vous trouvez une erreur (faute de frappe, bug dans le code, explication incorrecte) :

1. Ouvrez une **Issue** sur GitHub
2. Donnez un titre clair : `[Section X] Description du problème`
3. Décrivez le problème avec précision :
   - Section concernée
   - Erreur constatée
   - Correction suggérée (si possible)

**Exemple** :
```
Titre : [Section 4] Erreur dans l'exemple Smartphone
Description :
Dans l'exemple Smartphone.java ligne 15, il manque un point-virgule.
Proposition : Ajouter `;` à la fin de la ligne.
```

---

### 2. Proposer une Amélioration

Vous avez une idée pour améliorer le cours ?

**Types d'améliorations acceptées** :
- ✅ Ajout d'exemples de code
- ✅ Nouveaux exercices avec solutions
- ✅ Clarifications dans les explications
- ✅ Schémas ou diagrammes UML
- ✅ Traductions

**Procédure** :
1. Ouvrez une Issue pour discuter de votre idée
2. Attendez l'approbation avant de commencer
3. Créez une Pull Request (voir ci-dessous)

---

### 3. Soumettre une Pull Request

#### Étape 1 : Fork et Clone

```bash
# Forker le dépôt sur GitHub, puis :
git clone https://github.com/VOTRE-USERNAME/Cours-POO-Java.git
cd Cours-POO-Java
```

#### Étape 2 : Créer une Branche

```bash
git checkout -b amelioration/description-courte
```

**Convention de nommage** :
- `fix/section-X-correction` pour une correction
- `feat/section-X-exercice` pour un nouvel exercice
- `docs/section-X-clarification` pour améliorer la doc

#### Étape 3 : Apporter vos Modifications

**Règles à respecter** :

✅ **Code Java** :
- Indentation : 4 espaces
- Conventions Java standard (camelCase, noms explicites)
- Commentaires en français
- Code complet et fonctionnel

✅ **Markdown** :
- Titres cohérents (`##`, `###`)
- Blocs de code avec langage spécifié (```java)
- Émoji pour améliorer la lisibilité

✅ **Structure** :
- Respecter l'organisation existante
- Ajouter les fichiers dans le bon dossier

#### Étape 4 : Tester

```bash
# Pour les exemples de code :
cd XX-Nom-Section/exemples/
javac NouvelExemple.java
java NouvelExemple

# Vérifier que ça fonctionne sans erreur
```

#### Étape 5 : Commit et Push

```bash
git add .
git commit -m "feat(section-4): Ajout exemple Voiture avec enum"
git push origin amelioration/description-courte
```

**Convention de message de commit** :
```
type(scope): description courte

type = fix | feat | docs | style | refactor
scope = section-X ou global
```

#### Étape 6 : Créer la Pull Request

1. Allez sur GitHub
2. Cliquez sur "Compare & pull request"
3. Remplissez le template :

```markdown
## Description
Brève description de votre contribution

## Type de changement
- [ ] Correction de bug
- [ ] Nouvel exercice
- [ ] Amélioration de la documentation
- [ ] Ajout d'exemple de code
- [ ] Autre (précisez)

## Section concernée
Section X : Nom de la section

## Checklist
- [ ] Le code compile sans erreur
- [ ] Les exemples sont testés et fonctionnels
- [ ] La documentation est à jour
- [ ] Le style respecte les conventions
```

---

## 📝 Règles de Style

### Code Java

```java
// ✅ BON
public class Voiture {
    private String marque;  // Attribut en camelCase
    
    public Voiture(String marque) {  // Constructeur clair
        this.marque = marque;
    }
    
    public void demarrer() {  // Méthode en camelCase
        System.out.println("La voiture démarre");
    }
}

// ❌ MAUVAIS
public class voiture {  // Classe doit commencer par majuscule
    String m;  // Nom trop court
    public voiture(String x){marque=x;}  // Pas d'espace, pas de this
}
```

### Markdown

```markdown
## ✅ BON

### Titre de Sous-Section

Texte explicatif avec des **mots en gras** pour l'emphase.

```java
// Code avec langage spécifié
public class Exemple { }
```

---

## ❌ MAUVAIS

Titre sans ##

Code sans langage:
```
public class Exemple { }
```
```

---

## 🎯 Types de Contributions Recherchées

### Priorité Haute
- 🔴 Solutions d'exercices manquantes
- 🔴 Exemples de code pour sections 5-12
- 🔴 Diagrammes UML

### Priorité Moyenne
- 🟡 Nouveaux exercices
- 🟡 Améliorations des explications
- 🟡 Schémas et illustrations

### Priorité Basse
- 🟢 Corrections de fautes de frappe
- 🟢 Mise en forme
- 🟢 Traductions

---

## ❓ Questions ?

Si vous avez des questions avant de contribuer :

1. Consultez les **Issues existantes**
2. Ouvrez une **Discussion** sur GitHub
3. Contactez [@GUEDDES](https://github.com/GUEDDES)

---

## 🚀 Après votre Pull Request

- Votre PR sera **reviewée** dans les 48h
- Des modifications peuvent être demandées
- Une fois approuvée, elle sera **mergée**
- Votre nom sera ajouté dans les **contributeurs** 🎉

---

**Merci de contribuer à l'amélioration de ce cours ! 💙**
