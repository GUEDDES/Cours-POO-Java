# 📝 Intégration LaTeX - Cours POO Java

Ce document explique comment intégrer les références GitHub dans votre cours LaTeX.

---

## 📦 Packages Requis

Ajoutez ces packages dans le préambule de votre document LaTeX :

```latex
\usepackage{hyperref}      % Liens hypertextes
\usepackage{xcolor}        % Couleurs
\usepackage{tcolorbox}     % Boîtes colorées
\usepackage{fontawesome5}  % Icônes (GitHub, etc.)
\usepackage{qrcode}        % QR codes (optionnel)
```

---

## 🎨 Configuration Hyperref

Personnalisez les liens :

```latex
\hypersetup{
    colorlinks=true,
    linkcolor=blue,
    filecolor=magenta,      
    urlcolor=cyan,
    pdftitle={Cours POO Java},
    pdfauthor={Votre Nom},
    pdfsubject={Programmation Orientée Objet},
    pdfkeywords={Java, POO, Cours}
}
```

---

## 🔗 Commandes Personnalisées

### Option 1 : Commandes Simples

```latex
% Définir l'URL du dépôt
\newcommand{\repourl}{https://github.com/GUEDDES/Cours-POO-Java}

% Lien GitHub simple
\newcommand{\githublink}[1]{%
    \href{\repourl/#1}{\texttt{#1}}%
}

% Lien avec icône GitHub
\newcommand{\githubrepo}{%
    \faGithub\ \href{\repourl}{github.com/GUEDDES/Cours-POO-Java}%
}
```

### Option 2 : Boîtebox Colorée

```latex
% Boîte "Voir sur GitHub"
\newtcolorbox{githubbox}[1]{
    colback=blue!5!white,
    colframe=blue!75!black,
    fonttitle=\bfseries,
    title=#1,
    boxrule=0.5pt,
    left=5pt,
    right=5pt,
    top=5pt,
    bottom=5pt
}

% Commande pour encart GitHub
\newcommand{\voirgithub}[2]{%
    \begin{githubbox}{\faGithub\ Ressources GitHub}
        \textbf{#1}
        
        \vspace{3pt}
        
        Voir sur GitHub : \href{\repourl/#2}{\texttt{#2}}
    \end{githubbox}
}
```

### Option 3 : Boîte Avancée avec QR Code

```latex
% Boîte avec QR code
\newcommand{\githubqr}[3]{%
    \begin{tcolorbox}[colback=gray!5!white, colframe=blue!75!black, title={\faGithub\ #1}]
        #2
        
        \vspace{5pt}
        
        \begin{minipage}{0.65\textwidth}
            \href{\repourl/#3}{\texttt{#3}}
        \end{minipage}
        \hfill
        \begin{minipage}{0.3\textwidth}
            \qrcode[height=2cm]{\repourl/#3}
        \end{minipage}
    \end{tcolorbox}
}
```

---

## 📝 Exemples d'Utilisation par Section

### Section 1 : Introduction

```latex
\section{Introduction à la POO}

La Programmation Orientée Objet (POO) est un paradigme...

\voirgithub{Exercices et Solutions}{01-Introduction/exercices}

% Ou version simple :
\paragraph{Ressources :} 
\githublink{01-Introduction/exercices/exercice1.md}
```

### Section 3 : Bases de Java

```latex
\section{Bases de Java}

\subsection{Syntaxe de Base}

Java utilise une syntaxe similaire au C++...

\begin{githubbox}{\faCode\ Exemples de Code}
    \textbf{HelloWorld.java}
    
    Voir le code complet : \href{\repourl/tree/main/03-Java-Bases/exemples}{exemples/}
    
    \begin{itemize}
        \item HelloWorld.java
        \item CalculatriceSimple.java  
        \item GestionTableaux.java
    \end{itemize}
\end{githubbox}
```

### Section 4 : Classes et Objets

```latex
\section{Classes et Objets}

\subsection{Définition d'une Classe}

Une classe est un modèle...

\voirgithub{Exemple : Classe Voiture}{04-Classes-Objets/exemples/Voiture.java}

\subsection{Exercices}

\voirgithub{TD : Classe Étudiant (avec solution)}{04-Classes-Objets/exercices}
```

### Section 6 : Relations entre Classes

```latex
\section{Relations entre Classes}

\subsection{Composition vs Agrégation}

\begin{githubbox}{\faProjectDiagram\ Diagrammes UML}
    \textbf{Visualisation des relations}
    
    \begin{itemize}
        \item Diagramme UML : \href{\repourl/tree/main/06-Relations-Classes/diagrammes}{diagrammes/}
        \item Exemple Composition : \href{\repourl/blob/main/06-Relations-Classes/exemples/VoitureMoteur.java}{VoitureMoteur.java}
        \item Exemple Agrégation : \href{\repourl/blob/main/06-Relations-Classes/exemples/UniversiteEtudiant.java}{UniversiteEtudiant.java}
    \end{itemize}
\end{githubbox}
```

---

## 📚 Modèle Complet pour Chaque Section

```latex
% ================================================
% SECTION X : TITRE
% ================================================

\section{Titre de la Section}

% Contenu théorique
Votre contenu théorique ici...

% Boîte Exemples de Code
\begin{githubbox}{\faCode\ Exemples de Code}
    \textbf{Exemples disponibles sur GitHub}
    
    \begin{itemize}
        \item \href{\repourl/tree/main/XX-Nom-Section/exemples}{Dossier exemples/}
        \item \href{\repourl/blob/main/XX-Nom-Section/exemples/screenshots/output-examples.txt}{Sorties d'exécution}
    \end{itemize}
\end{githubbox}

% Boîte Exercices
\begin{githubbox}{\faPencilAlt\ Exercices Pratiques}
    \textbf{Travaux Dirigés}
    
    \begin{itemize}
        \item \href{\repourl/blob/main/XX-Nom-Section/exercices/exercice1.md}{Énoncé Exercice 1}
        \item \href{\repourl/blob/main/XX-Nom-Section/solutions/Exercice1Solution.java}{Solution Exercice 1}
    \end{itemize}
\end{githubbox}

% Boîte Diagrammes (si applicable)
\begin{githubbox}{\faProjectDiagram\ Diagrammes UML}
    \textbf{Modélisation visuelle}
    
    Voir les diagrammes : \href{\repourl/tree/main/XX-Nom-Section/diagrammes}{diagrammes/}
\end{githubbox}
```

---

## 🎯 Encart Global (Début du Document)

Ajoutez ceci après le `\maketitle` :

```latex
\begin{tcolorbox}[colback=blue!5!white, colframe=blue!75!black, boxrule=1pt, arc=4mm]
    \centering
    \Large \faGithub\ \textbf{Dépôt GitHub du Cours}
    
    \vspace{5pt}
    
    \large Tous les exemples, exercices et solutions sont disponibles sur :
    
    \vspace{3pt}
    
    \href{\repourl}{\texttt{github.com/GUEDDES/Cours-POO-Java}}
    
    \vspace{3pt}
    
    \qrcode[height=3cm]{\repourl}
\end{tcolorbox}
```

---

## 📝 Encart de Pied de Page

```latex
\usepackage{fancyhdr}
\pagestyle{fancy}
\fancyfoot[C]{\faGithub\ \href{\repourl}{github.com/GUEDDES/Cours-POO-Java}}
```

---

## 📄 Template Section Complète

Voici un template complet pour une section :

```latex
\section{Classes et Objets}
\label{sec:classes-objets}

\subsection{Introduction}

Une classe est un modèle (ou blueprint) qui définit...

\subsection{Syntaxe}

\begin{lstlisting}[language=Java, caption=Définition d'une classe]
public class Voiture {
    // Attributs
    private String marque;
    private int annee;
    
    // Constructeur
    public Voiture(String marque, int annee) {
        this.marque = marque;
        this.annee = annee;
    }
    
    // Méthodes
    public void demarrer() {
        System.out.println("La voiture démarre");
    }
}
\end{lstlisting}

% ENCART GITHUB
\vspace{10pt}
\begin{tcolorbox}[colback=blue!5!white, colframe=blue!75!black, title={\faGithub\ Ressources GitHub - Section 4}]
    \textbf{\faCode\ Exemples de Code}
    \begin{itemize}
        \item \href{\repourl/blob/main/04-Classes-Objets/exemples/Voiture.java}{Voiture.java} - Classe complète avec tests
        \item \href{\repourl/blob/main/04-Classes-Objets/exemples/CompteBancaire.java}{CompteBancaire.java} - Gestion bancaire
        \item \href{\repourl/blob/main/04-Classes-Objets/exemples/screenshots/output-examples.txt}{Sorties d'exécution}
    \end{itemize}
    
    \vspace{5pt}
    
    \textbf{\faPencilAlt\ Exercices Pratiques}
    \begin{itemize}
        \item \href{\repourl/blob/main/04-Classes-Objets/exercices/exercice1.md}{Énoncé : Classe Étudiant}
        \item \href{\repourl/blob/main/04-Classes-Objets/solutions/Exercice1Solution.java}{Solution complète}
    \end{itemize}
\end{tcolorbox}

\subsection{Exercices}

Se référer au dépôt GitHub pour les exercices pratiques.
```

---

## 🎨 Personnalisation des Couleurs

```latex
% Définir des couleurs personnalisées
\definecolor{githubblue}{RGB}{0, 119, 200}
\definecolor{githubgray}{RGB}{36, 41, 46}

% Utiliser dans les boîtes
\begin{tcolorbox}[colback=githubblue!5!white, colframe=githubblue]
    ...
\end{tcolorbox}
```

---

## ✅ Checklist Intégration

- [ ] Ajouter les packages requis dans le préambule
- [ ] Définir les commandes personnalisées
- [ ] Configurer hyperref
- [ ] Ajouter l'encart global après le titre
- [ ] Intégrer les boîtes GitHub dans chaque section
- [ ] Ajouter le pied de page (optionnel)
- [ ] Vérifier que tous les liens fonctionnent
- [ ] Générer le PDF et tester les hyperliens

---

## 📚 Ressources

- Documentation hyperref : https://ctan.org/pkg/hyperref
- Documentation tcolorbox : https://ctan.org/pkg/tcolorbox
- FontAwesome5 : https://ctan.org/pkg/fontawesome5
- QR codes : https://ctan.org/pkg/qrcode

---

**Bon courage pour l'intégration ! 🚀**
