import java.util.Objects;

/**
 * Solution de l'Exercice 2 : Gestion de Catalogue et Contrat Object
 */
class Article {
    String reference;
    String designation;
    double prix;

    // Constructeur complet
    public Article(String reference, String designation, double prix) {
        this.reference = (reference != null && !reference.trim().isEmpty()) ? reference.trim() : "REF_INCONNUE";
        this.designation = (designation != null) ? designation.trim() : "Sans désignation";
        this.prix = (prix >= 0) ? prix : 0.0;
    }

    // Constructeur surchargé avec délégation this(...)
    public Article(String reference, String designation) {
        this(reference, designation, 0.0);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %.2f DT", reference, designation, prix);
    }

    // Deux articles sont égaux si leur référence est identique (insensible à la casse)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article autre = (Article) obj;
        return reference.equalsIgnoreCase(autre.reference);
    }

    // Le hash code doit être calculé sur la base de la référence en minuscules
    @Override
    public int hashCode() {
        return Objects.hash(reference.toLowerCase());
    }
}

class Catalogue {
    private Article[] articles;
    private int nombreArticles;

    public Catalogue(int capaciteMax) {
        this.articles = new Article[capaciteMax];
        this.nombreArticles = 0;
    }

    public boolean ajouterArticle(Article a) {
        if (a == null || nombreArticles >= articles.length) {
            return false;
        }

        // Vérification de doublon via equals()
        for (int i = 0; i < nombreArticles; i++) {
            if (articles[i].equals(a)) {
                System.out.println("❌ Doublon détecté pour la référence : " + a.reference);
                return false;
            }
        }

        articles[nombreArticles++] = a;
        return true;
    }

    public Article rechercherArticle(String refCherchee) {
        if (refCherchee == null) return null;
        for (int i = 0; i < nombreArticles; i++) {
            if (articles[i].reference.equalsIgnoreCase(refCherchee)) {
                return articles[i];
            }
        }
        return null;
    }

    public void afficherCatalogue() {
        System.out.println("\n--- Contenu du Catalogue (" + nombreArticles + " articles) ---");
        for (int i = 0; i < nombreArticles; i++) {
            System.out.println((i + 1) + ". " + articles[i]);
        }
    }
}

public class Exercice2Solution {
    public static void main(String[] args) {
        Catalogue cat = new Catalogue(5);

        Article a1 = new Article("ART-001", "Clavier mécanique", 120.0);
        Article a2 = new Article("ART-002", "Souris sans fil", 65.5);
        Article a3 = new Article("art-001", "Clavier RGB de remplacement", 140.0); // Même référence logique !
        Article a4 = new Article("ART-003", "Écran 27 pouces 144Hz", 650.0);

        System.out.println("Ajout a1 : " + cat.ajouterArticle(a1));
        System.out.println("Ajout a2 : " + cat.ajouterArticle(a2));
        System.out.println("Ajout a3 (doublon référence) : " + cat.ajouterArticle(a3)); // Doit être rejeté
        System.out.println("Ajout a4 : " + cat.ajouterArticle(a4));

        cat.afficherCatalogue();

        System.out.println("\n--- Test du contrat equals/hashCode ---");
        System.out.println("a1.equals(a3) ? " + a1.equals(a3));
        System.out.println("a1.hashCode() == a3.hashCode() ? " + (a1.hashCode() == a3.hashCode()));
    }
}
