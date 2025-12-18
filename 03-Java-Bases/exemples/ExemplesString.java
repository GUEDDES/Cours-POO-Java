/**
 * Démonstration des principales méthodes de la classe String
 */
public class ExemplesString {
    public static void main(String[] args) {
        System.out.println("=== CRÉATION ET CONCATÉNATION ===");
        String prenom = "Alice";
        String nom = "Dupont";
        String nomComplet = prenom + " " + nom;
        System.out.println("Nom complet : " + nomComplet);
        
        System.out.println("\n=== LONGUEUR ET CARACTÈRES ===");
        String mot = "Java";
        System.out.println("Longueur : " + mot.length());
        System.out.println("Premier caractère : " + mot.charAt(0));
        System.out.println("Dernier caractère : " + mot.charAt(mot.length() - 1));
        
        System.out.println("\n=== EXTRACTION ===");
        String phrase = "Apprendre Java est amusant";
        System.out.println("Sous-chaîne [0, 9] : " + phrase.substring(0, 9));
        System.out.println("Depuis index 10 : " + phrase.substring(10));
        
        System.out.println("\n=== CHANGEMENT DE CASSE ===");
        String texte = "Hello World";
        System.out.println("Majuscules : " + texte.toUpperCase());
        System.out.println("Minuscules : " + texte.toLowerCase());
        
        System.out.println("\n=== COMPARAISON ===");
        String s1 = "Java";
        String s2 = "java";
        String s3 = "Java";
        System.out.println("s1.equals(s2) : " + s1.equals(s2));  // false
        System.out.println("s1.equals(s3) : " + s1.equals(s3));  // true
        System.out.println("s1.equalsIgnoreCase(s2) : " + s1.equalsIgnoreCase(s2));  // true
        
        System.out.println("\n=== RECHERCHE ===");
        String email = "alice@exemple.com";
        System.out.println("Contient '@' ? " + email.contains("@"));
        System.out.println("Commence par 'alice' ? " + email.startsWith("alice"));
        System.out.println("Finit par '.com' ? " + email.endsWith(".com"));
        System.out.println("Position de '@' : " + email.indexOf("@"));
        
        System.out.println("\n=== REMPLACEMENT ===");
        String message = "Java est cool";
        String nouveauMessage = message.replace("cool", "génial");
        System.out.println("Original : " + message);
        System.out.println("Modifié : " + nouveauMessage);
        
        System.out.println("\n=== NETTOYAGE ===");
        String avec Espaces = "  Bonjour  ";
        System.out.println("Avec espaces : '" + avecEspaces + "'");
        System.out.println("Sans espaces : '" + avecEspaces.trim() + "'");
        
        System.out.println("\n=== DIVISION EN TABLEAU ===");
        String langages = "Java,Python,C++,JavaScript";
        String[] tableau = langages.split(",");
        System.out.println("Langages :");
        for (String langage : tableau) {
            System.out.println("- " + langage);
        }
    }
}
