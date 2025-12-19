import java.util.Scanner;

public class NombreSecret {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Definir le nombre secret
        final int SECRET = 42; // Modifiable
        
        // Saisie du nombre
        System.out.print("Devinez le nombre secret : ");
        int nombreSaisi = sc.nextInt();
        
        // Comparaison et affichage
        if (nombreSaisi > SECRET) {
            System.out.println("Nombre saisi superieur au " +
                "nombre secret.");
        } else if (nombreSaisi < SECRET) {
            System.out.println("Nombre saisi inferieur au " +
                "nombre secret.");
        } else {
            System.out.println("Nombre saisi egal au nombre " +
                "secret. Bravo !");
        }
        
        sc.close();
    }
}
