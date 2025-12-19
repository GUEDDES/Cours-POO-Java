# 📸 Guide : Créer des Screenshots des Exemples

Ce guide explique comment créer et organiser les screenshots d'exécution des exemples de code.

---

## 🛠️ Outils Requis

### Windows
- **Outil intégré** : `Win + Shift + S` (Outil Capture d'écran)
- **PowerToys** : Recommandé pour captures avancées
- **ShareX** : Gratuit, open source, très complet

### macOS
- **Outil intégré** : `Cmd + Shift + 4` (Capture de zone)
- **CleanShot X** : Application payante mais excellente

### Linux
- **Flameshot** : `sudo apt install flameshot`
- **GNOME Screenshot** : `gnome-screenshot -a`

---

## 📝 Étapes pour Chaque Exemple

### 1. Compiler le Code

```bash
# Naviguer vers le dossier de l'exemple
cd 03-Java-Bases/exemples

# Compiler
javac HelloWorld.java

# Exécuter
java HelloWorld
```

### 2. Capturer l'Exécution

**Configuration du terminal recommandée** :
- Police : `Fira Code`, `JetBrains Mono`, ou `Consolas`
- Taille : 14-16pt
- Thème : Clair ou sombre (cohérent dans tout le projet)
- Largeur : ~100-120 colonnes

**Ce qu'il faut capturer** :
1. La **commande** d'exécution (`java NomClasse`)
2. La **sortie complète** du programme
3. Le **prompt** final pour montrer que c'est terminé

### 3. Éditer et Optimiser

**Outils d'édition recommandés** :
- **Photopea** (en ligne, gratuit) : https://www.photopea.com/
- **GIMP** (gratuit)
- **Photoshop** (payant)

**Optimisations** :
1. **Rogner** les zones inutiles
2. **Annoter** si nécessaire (flèches, encadrés)
3. **Exporter** en PNG (format optimal pour texte)
4. **Compression** : https://tinypng.com/ (réduit la taille sans perte visible)

### 4. Nommer et Organiser

**Convention de nommage** :
```
<section>-<nom-exemple>-screenshot.png

Exemples :
03-helloworld-screenshot.png
04-voiture-screenshot.png
06-composition-voiture-moteur-screenshot.png
```

**Structure des dossiers** :
```
XX-Nom-Section/
├── exemples/
│   ├── Exemple.java
│   └── screenshots/
│       ├── exemple-screenshot.png
│       └── exemple-output.txt  (sortie texte brute)
```

---

## 📊 Liste des Screenshots à Créer

### Section 3 : Java Bases

#### ☐ HelloWorld.java
```bash
javac HelloWorld.java
java HelloWorld
```
**Sortie attendue** : "Hello, World!"

#### ☐ CalculatriceSimple.java
```bash
javac CalculatriceSimple.java
java CalculatriceSimple
```
**Sortie attendue** : Résultats des 4 opérations

#### ☐ GestionTableaux.java
```bash
javac GestionTableaux.java
java GestionTableaux
```
**Sortie attendue** : Affichage des tableaux 1D et 2D

---

### Section 4 : Classes et Objets

#### ☐ Voiture.java
```bash
javac Voiture.java
java Voiture
```
**Sortie attendue** : Scénario d'utilisation de la voiture

#### ☐ CompteBancaire.java
```bash
javac CompteBancaire.java
java CompteBancaire
```
**Sortie attendue** : Opérations bancaires + virements

---

### Section 5 : Éléments Statiques

#### ☐ Calculatrice.java
```bash
javac Calculatrice.java
java Calculatrice
```
**Sortie attendue** : Toutes les opérations mathématiques

---

### Section 6 : Relations entre Classes

#### ☐ VoitureMoteur.java
```bash
javac VoitureMoteur.java
java VoitureMoteur
```
**Sortie attendue** : Démonstration composition

#### ☐ UniversiteEtudiant.java
```bash
javac UniversiteEtudiant.java
java UniversiteEtudiant
```
**Sortie attendue** : Démonstration agrégation

---

### Section 9 : Héritage

#### ☐ AnimalHierarchie.java
```bash
javac AnimalHierarchie.java
java AnimalHierarchie
```
**Sortie attendue** : Polymorphisme avec animaux

---

### Section 10 : Polymorphisme

#### ☐ SystemePaiement.java
```bash
javac SystemePaiement.java
java SystemePaiement
```
**Sortie attendue** : Transactions avec différents moyens de paiement

---

### Section 12 : Exceptions

#### ☐ GestionFichiers.java
```bash
javac GestionFichiers.java
java GestionFichiers
```
**Sortie attendue** : Création/lecture/suppression de fichier avec gestion d'erreurs

---

## 🎨 Bonnes Pratiques

### DO ✅

1. **Résolution** : Minimum 1920x1080 pour captures
2. **Lisibilité** : Police assez grande (14-16pt)
3. **Contexte** : Inclure la commande d'exécution
4. **Complétude** : Toute la sortie visible
5. **Cohérence** : Même terminal/thème pour tous
6. **Annotation** : Ajouter des flèches/cadres si nécessaire
7. **Compression** : Réduire la taille (< 500 KB par image)

### DON'T ❌

1. **Captures floues** : Toujours en qualité maximale
2. **Informations personnelles** : Pas de chemins absolus avec username
3. **Couleurs criardes** : Thèmes professionnels uniquement
4. **Captures partielles** : Toujours la sortie complète
5. **Fichiers lourds** : Pas de BMP/TIFF, toujours PNG compressé

---

## 📝 Modèle de Documentation

Dans chaque `README.md` de section avec exemples, ajouter :

```markdown
## 💻 Exemples de Code

### Exemple 1 : Voiture

**Fichier** : [`Voiture.java`](exemples/Voiture.java)

**Description** : Illustre la création d'une classe avec constructeurs, attributs et méthodes.

**Exécution** :
```bash
cd exemples
javac Voiture.java
java Voiture
```

**Sortie** :

![Screenshot Voiture](exemples/screenshots/voiture-screenshot.png)

Ou voir [sortie texte complète](exemples/screenshots/voiture-output.txt)
```

---

## 🚀 Automatisation (Avancé)

Pour automatiser la capture (utilisateurs avancés) :

### Script Bash (Linux/macOS)

```bash
#!/bin/bash
# capture-all-examples.sh

EXAMPLES=(
    "03-Java-Bases/exemples/HelloWorld.java"
    "04-Classes-Objets/exemples/Voiture.java"
    # ... ajouter tous les exemples
)

for example in "${EXAMPLES[@]}"; do
    dir=$(dirname "$example")
    file=$(basename "$example" .java)
    
    cd "$dir" || continue
    
    echo "Compilation de $file..."
    javac "$file.java"
    
    echo "Exécution de $file..."
    java "$file" > "screenshots/${file}-output.txt" 2>&1
    
    echo "Sortie capturée dans screenshots/${file}-output.txt"
    
    cd - > /dev/null
done
```

---

## 📊 Checklist Complète

- [ ] Installer outil de capture recommandé
- [ ] Configurer terminal (police, taille, thème)
- [ ] Créer dossiers `screenshots/` dans chaque section
- [ ] Capturer les 9 exemples
- [ ] Éditer et optimiser les images
- [ ] Compresser les PNG (< 500 KB chacun)
- [ ] Renommer selon convention
- [ ] Sauvegarder sorties texte (.txt)
- [ ] Mettre à jour les README avec images
- [ ] Commit et push vers GitHub
- [ ] Vérifier affichage sur GitHub

---

## 📧 Besoin d'Aide ?

Si vous rencontrez des difficultés :

1. Ouvrez une [issue GitHub](https://github.com/GUEDDES/Cours-POO-Java/issues)
2. Consultez [CONTRIBUTING.md](CONTRIBUTING.md)
3. Contactez le mainteneur

---

**Bon courage pour les captures ! 📸**
