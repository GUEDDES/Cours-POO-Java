# 📸 Captures d'Écran des Exemples

Ce document présente les résultats d'exécution de tous les exemples du cours.

---

## 🛠️ Comment Exécuter les Exemples

### Méthode 1 : Ligne de commande

```bash
# Compiler
javac NomDuFichier.java

# Exécuter
java NomDuFichier
```

### Méthode 2 : IntelliJ IDEA

1. Ouvrir le fichier `.java`
2. Clic droit sur le fichier
3. Sélectionner "Run 'NomDuFichier.main()'"

### Méthode 3 : VS Code

1. Installer l'extension "Extension Pack for Java"
2. Ouvrir le fichier `.java`
3. Cliquer sur "Run" au-dessus de `public static void main`

---

## Section 3 : Java - Les Fondamentaux

### Exemple 1 : HelloWorld.java

**Fichier** : `03-Java-Bases/exemples/HelloWorld.java`

**Sortie attendue** :
```
=== HELLO WORLD =======================
Bonjour, monde !

Informations système :
 - Version Java : 17.0.x
 - Système d'exploitation : Windows 10 / macOS / Linux

=== FIN DU PROGRAMME ==================
```

**Description** : Premier programme Java classique qui affiche "Hello World" et des informations système.

---

### Exemple 2 : Calculatrice.java

**Fichier** : `03-Java-Bases/exemples/Calculatrice.java`

**Sortie attendue** :
```
=== CALCULATRICE SIMPLE ===============

Addition      : 10 + 5 = 15
Soustraction  : 10 - 5 = 5
Multiplication: 10 * 5 = 50
Division      : 10 / 5 = 2.00
Modulo        : 10 % 3 = 1
Puissance     : 2^8 = 256.00

=== OPÉRATIONS AVANCÉES ==============
Racine carrée de 16 : 4.00
Valeur absolue de -42 : 42.00
Max(15, 28) : 28
Min(15, 28) : 15

=== DIVISION PAR ZÉRO ================
❌ Erreur : Division par zéro impossible
```

**Description** : Démontre les opérations arithmétiques de base et la gestion d'erreurs.

---

### Exemple 3 : GestionTableaux.java

**Fichier** : `03-Java-Bases/exemples/GestionTableaux.java`

**Sortie attendue** :
```
=== TABLEAUX 1D =======================
Nombres : [10, 20, 30, 40, 50]
Somme : 150
Moyenne : 30.00
Max : 50
Min : 10

=== TABLEAUX 2D =======================
Matrice 3x3 :
  1   2   3
  4   5   6
  7   8   9

Somme totale : 45
Somme diagonale : 15 (1 + 5 + 9)

=== TRI ===============================
Avant tri : [64, 34, 25, 12, 22, 11, 90]
Après tri : [11, 12, 22, 25, 34, 64, 90]
```

**Description** : Manipulation de tableaux 1D et 2D, calculs statistiques et tri.

---

## Section 4 : Classes et Objets

### Exemple 1 : Voiture.java

**Fichier** : `04-Classes-Objets/exemples/Voiture.java`

**Sortie attendue** :
```
=== GESTION DE VOITURES ===============

✅ Voiture créée : Peugeot 308
✅ Voiture créée : Renault Clio

--- Voiture 1 ---
🚗 Démarrage de la Peugeot 308
⛽ Niveau de carburant : 50.0L
🏎️ Accélération !
🚦 Freinage
🛑 Arrêt de la Peugeot 308

Informations :
Marque    : Peugeot
Modèle    : 308
Année     : 2023
Couleur   : Bleu
Carburant : 50.0L / 60.0L
Kilométrage: 0 km

Nombre de voitures créées : 2
```

**Description** : Classe complète avec constructeurs, attributs, méthodes et compteur statique.

---

### Exemple 2 : CompteBancaire.java

**Fichier** : `04-Classes-Objets/exemples/CompteBancaire.java`

**Sortie attendue** :
```
=== GESTION DE COMPTES BANCAIRES ======

✅ Compte créé : FR12345 (Alice Dupont)
✅ Compte créé : FR67890 (Bob Martin)

--- Opérations sur compte Alice ---
✅ Dépôt de 500.00€ effectué
✅ Retrait de 200.00€ effectué

========== RELEVÉ ==========
Numéro    : FR12345
Titulaire : Alice Dupont
Solde     : 1300.00€
============================

--- Tentative de retrait excessif ---
❌ Solde insuffisant (solde: 1300.00€, demandé: 2000.00€)

Nombre de comptes : 2
Solde total : 2300.00€
```

**Description** : Simulation d'un système bancaire avec dépôts, retraits et validation.

---

## Section 5 : Éléments Statiques

### Exemple : Calculatrice (classe utilitaire)

**Fichier** : `05-Elements-Statiques/exemples/Calculatrice.java`

**Sortie attendue** :
```
=== CLASSE UTILITAIRE CALCULATRICE ====

Constante PI : 3.141592653589793

--- Opérations basiques ---
Addition    : 15 + 7 = 22
Multiplier  : 6 * 8 = 48
Puissance   : 2^10 = 1024.0

--- Géométrie ---
Aire cercle (r=5) : 78.54
Aire rectangle (8x4) : 32.00
Aire triangle (b=6, h=4) : 12.00

--- Conversions ---
Celsius vers Fahrenheit : 25°C = 77.00°F
Fahrenheit vers Celsius : 77°F = 25.00°C

Km vers Miles : 100 km = 62.14 miles
Miles vers Km : 62.14 miles = 100.00 km
```

**Description** : Classe utilitaire avec méthodes statiques pour calculs mathématiques.

---

## Section 6 : Relations entre Classes

### Exemple 1 : VoitureMoteur (Composition)

**Fichier** : `06-Relations-Classes/exemples/VoitureMoteur.java`

**Sortie attendue** :
```
=== COMPOSITION : VOITURE ET MOTEUR ===

⚙️ Moteur créé : Diesel 2.0L 150ch
🚗 Voiture créée : Peugeot 308

--- Démarrage ---
⚡ Démarrage du moteur Diesel...
✅ Moteur démarré
🚗 La voiture Peugeot 308 roule

--- Informations ---
Voiture : Peugeot 308 (2023)
Moteur  : Diesel 2.0L - 150ch
Statut  : En marche

--- Arrêt ---
🛑 Arrêt du moteur

REMARQUE : Si la voiture est détruite, le moteur l'est aussi (COMPOSITION)
```

**Description** : Composition forte entre Voiture et Moteur (cycle de vie lié).

---

### Exemple 2 : UniversiteEtudiant (Agrégation)

**Fichier** : `06-Relations-Classes/exemples/UniversiteEtudiant.java`

**Sortie attendue** :
```
=== AGRÉGATION : UNIVERSITÉ ET ÉTUDIANTS ===

🎓 Étudiant créé : Alice (E001)
🎓 Étudiant créé : Bob (E002)
🎓 Étudiant créé : Charlie (E003)

🏛️ Université créée : Université Paris-Saclay

✅ Alice inscrità Université Paris-Saclay
✅ Bob inscrit à Université Paris-Saclay
✅ Charlie inscrit à Université Paris-Saclay

=== LISTE DES ÉTUDIANTS ===
Université : Université Paris-Saclay
Nombre d'étudiants : 3

1. Alice (E001)
2. Bob (E002)
3. Charlie (E003)

❌ Alice désinscrit de l'université

Nombre d'étudiants : 2

REMARQUE : Si l'université est fermée, les étudiants existent toujours (AGRÉGATION)
```

**Description** : Agrégation faible entre Université et Étudiant (cycles indépendants).

---

## Section 9 : Héritage

### Exemple : AnimalHierarchie.java

**Fichier** : `09-Heritage/exemples/AnimalHierarchie.java`

**Sortie attendue** :
```
=== HIÉRARCHIE D'HÉRITAGE : ANIMAUX ===

✅ Animal créé : Rex (Chien)
✅ Animal créé : Miaou (Chat)
✅ Animal créé : Tweety (Oiseau)

=== TOUS LES ANIMAUX FONT DU BRUIT ===
🐕 Wouf wouf !
🐈 Miaou miaou !
🐦 Cui cui !

=== ANIMAUX DOMESTIQUES ===
🏠 Rex est un animal domestique
🏠 Miaou est un animal domestique

=== OISEAU VOLE ===
🐦 Tweety déploie ses ailes
✈️ Tweety vole !
🏞️ Tweety atterrit

=== INFORMATIONS DÉTAILLÉES ===
Chien : Rex, âge 3 ans, Race: Labrador
Chat : Miaou, âge 2 ans, Couleur: Noir
Oiseau : Tweety, âge 1 an, Envergure: 0.30m

Nombre total d'animaux : 3
```

**Description** : Hiérarchie complète avec classe mère Animal et sous-classes spécialisées.

---

## Section 10 : Polymorphisme

### Exemple : SystemePaiement.java

**Fichier** : `10-Polymorphisme/exemples/SystemePaiement.java`

**Sortie attendue** :
```
🏪 === SYSTÈME DE PAIEMENT POLYMORPHE ===

========================================
🛠️  TRANSACTION #1
Référence : REF1734609600123
Type      : Carte Bancaire
========================================
💳 Paiement par Carte Bancaire
   Titulaire : Alice Dupont
   Numéro    : **** **** **** 5678
   Montant   : 45.50€
   ✅ Paiement autorisé

✅ TRANSACTION RÉUSSIE
========================================

========================================
🛠️  TRANSACTION #2
Référence : REF1734609600456
Type      : PayPal
========================================
🔵 Paiement via PayPal
   Email     : bob@exemple.com
   Montant   : 30.00€
   ✅ Transaction PayPal réussie

✅ TRANSACTION RÉUSSIE
========================================

========================================
🛠️  TRANSACTION #3
Référence : REF1734609600789
Type      : Espèces
========================================
💵 Paiement en Espèces
   Montant   : 25.00€
   Reçu      : 30.00€
   💰 Rendu    : 5.00€
   ✅ Paiement encaissé

✅ TRANSACTION RÉUSSIE
========================================

📋 ========== BILAN DE LA JOURNÉE ==========
Nombre de transactions : 5
Total encaissé         : 236.49€
==========================================
```

**Description** : Polymorphisme avec classe abstraite et multiples implémentations.

---

## Section 12 : Exceptions

### Exemple : GestionFichiers.java

**Fichier** : `12-Exceptions/exemples/GestionFichiers.java`

**Sortie attendue** :
```
=== GESTION D'EXCEPTIONS : FICHIERS ===

--- Création du fichier ---
✅ Fichier créé : test_exceptions.txt

--- Écriture dans le fichier ---
✅ Contenu écrit dans test_exceptions.txt (4 lignes)

--- Lecture du fichier ---
✅ Fichier lu : test_exceptions.txt (4 lignes)

Contenu :
  1. Ligne 1 : Hello World!
  2. Ligne 2 : Gestion des exceptions
  3. Ligne 3 : Try-Catch-Finally
  4. Ligne 4 : Exceptions personnalisées

--- Tentative de recréation (provoque exception) ---
❌ Exception capturée : FichierException [Code: ERR_FICHIER_EXISTE, Fichier: test_exceptions.txt] Le fichier existe déjà
   Code d'erreur : ERR_FICHIER_EXISTE

--- Suppression du fichier ---
✅ Fichier supprimé : test_exceptions.txt

--- Tentative de lecture après suppression ---
❌ Exception capturée : Le fichier n'existe pas
   Code : ERR_FICHIER_INTROUVABLE

--- Test exception non vérifiée ---
❌ ArithmeticException capturée : / by zero

🏁 Programme terminé (bloc finally toujours exécuté)
```

**Description** : Gestion complète des exceptions avec try-catch-finally et exceptions personnalisées.

---

## 📊 Résumé des Exemples

| Section | Exemple | Concepts Illustrés |
|---------|---------|----------------------|
| 3 | HelloWorld | Premier programme, affichage |
| 3 | Calculatrice | Opérations arithmétiques |
| 3 | GestionTableaux | Tableaux 1D/2D, boucles, tri |
| 4 | Voiture | Classes, objets, constructeurs |
| 4 | CompteBancaire | Encapsulation, validation |
| 5 | Calculatrice | Méthodes statiques, classe utilitaire |
| 6 | VoitureMoteur | Composition |
| 6 | UniversiteEtudiant | Agrégation |
| 9 | AnimalHierarchie | Héritage, super, @Override |
| 10 | SystemePaiement | Polymorphisme, classe abstraite |
| 12 | GestionFichiers | Exceptions, try-catch-finally |

**Total : 9 exemples complets et fonctionnels** ✅

---

## 🚀 Pour Aller Plus Loin

### Exécuter tous les exemples automatiquement

Créez un script `run_all.sh` (Linux/macOS) :

```bash
#!/bin/bash
for file in $(find . -name "*.java" -path "*/exemples/*"); do
    echo "=== Compilation de $file ==="
    javac "$file"
    
    if [ $? -eq 0 ]; then
        classname=$(basename "$file" .java)
        dirname=$(dirname "$file")
        echo "=== Exécution de $classname ==="
        (cd "$dirname" && java "$classname")
        echo ""
    fi
done
```

Ou `run_all.bat` (Windows) :

```batch
@echo off
for /r %%f in (exemples\*.java) do (
    echo === Compilation de %%f ===
    javac "%%f"
    if !errorlevel! equ 0 (
        echo === Exécution ===
        java -cp "%%~dpf" "%%~nf"
    )
)
```

---

## 📝 Notes

- Tous les exemples sont **testés et fonctionnels**
- Compatible **Java 8+** (recommandé : Java 17)
- Aucune dépendance externe nécessaire
- Chaque exemple est **autonome** (peut être exécuté indépendamment)

---

**Pour toute question ou amélioration, consultez [CONTRIBUTING.md](CONTRIBUTING.md) !**
