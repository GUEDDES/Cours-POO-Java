# Exercice 2 : Système de Transactions Bancaires et Audit Robuste (Avancé)

## 🎯 Objectifs

- Créer et hiérarchiser des exceptions personnalisées (vérifiées et non vérifiées).
- Gérer l'atomicité et l'intégrité transactionnelle avec les blocs `try-catch-finally`.
- Propager correctement des anomalies avec `throw` et `throws`.
- Implémenter un mécanisme de journalisation défensif.

---

## 📋 Énoncé

Dans une application bancaire, les opérations financières doivent être exécutées de manière atomique : si une étape échoue au milieu d'un virement, l'état initial des comptes doit être garanti sans fuite ni corruption de solde.

### 1. Hiérarchie d'Exceptions Personnalisées

Créez les trois classes d'exception suivantes :
1. `TransactionException` : classe abstraite mère des exceptions bancaires (hérite de `Exception`).
2. `SoldeInsuffisantException` (hérite de `TransactionException`) : exception vérifiée levée lorsqu'un retrait ou un virement dépasse le solde disponible.
   - Attributs : `double soldeActuel`, `double montantDemande`.
3. `CompteBloqueException` (hérite de `RuntimeException`) : exception non vérifiée levée si une opération tente d'interagir avec un compte dont le statut est inactif ou verrouillé.

---

### 2. Classe `CompteBancaire`

Attributs :
- `String numero`
- `double solde`
- `boolean actif`

Méthodes :
- Constructeur avec validation (`solde >= 0`).
- `void debiter(double montant) throws SoldeInsuffisantException` : vérifie que le compte est actif (sinon lance `CompteBloqueException`), que le montant est positif, et que le solde est suffisant (sinon lance `SoldeInsuffisantException`).
- `void crediter(double montant)` : crédite le solde si le compte est actif.

---

### 3. Classe Abstraite `Transaction`

Attributs protégés :
- `long idTransaction` (identifiant unique auto-incrémenté via un compteur statique)
- `double montant`
- `boolean reussie`

Méthode abstraite :
```java
public abstract void executer() throws TransactionException;
```

---

### 4. Classe Concrète `Virement`

Hérite de `Transaction`.
- Attributs : `CompteBancaire source`, `CompteBancaire destination`.
- Implémentation de `executer()` :
  - Débite d'abord le compte source.
  - Tente de créditer le compte destination.
  - Si une exception survient pendant l'opération, restaure immédiatement le débit initial sur le compte source (**garantie d'atomicité / rollback**) et relance l'exception.

---

### 5. Classe `ServiceAudit`

Fournit une méthode statique de traitement :
```java
public static void executerEtAuditer(Transaction t)
```
- Exécute la transaction dans un bloc `try-catch-finally`.
- Capture spécifiquement `SoldeInsuffisantException` et `CompteBloqueException`.
- Affiche un journal horodaté du statut final de la transaction dans le bloc `finally`.

---

### 6. Programme de Test

Dans `Exercice2Solution`, écrivez un scénario complet testant :
1. Un virement réussi entre deux comptes valides.
2. Un virement échouant pour solde insuffisant (vérifier que le compte source conserve son solde intact).
3. Une tentative d'opération sur un compte bloqué.
