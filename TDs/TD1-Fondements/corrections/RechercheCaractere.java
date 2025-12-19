import java.util.Scanner;

public class RechercheCaractere {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Saisie de la chaine de caracteres
        System.out.print("Entrez une chaine de caracteres : ");
        String ch = sc.nextLine();
        
        // Saisie du caractere a rechercher
        System.out.print("Entrez le caractere a rechercher : ");
        char x = sc.next().charAt(0);
        
        // Comptage des occurrences
        int nombreOccurrences = 0;
        for (int i = 0; i < ch.length(); i++) {
            if (ch.charAt(i) == x) {
                nombreOccurrences++;
            }
        }
        
        // Affichage du resultat
        System.out.println("\nLe caractere '" + x +
            "' apparait " + nombreOccurrences +
            " fois dans la chaine \"" + ch + "\".");
        
        sc.close();
    }
}
