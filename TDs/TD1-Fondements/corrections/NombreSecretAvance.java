import java.util.Scanner;

public class NombreSecretAvance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int SECRET = 42;
        int nombreSaisi;
        int tentatives = 0;
        
        System.out.println("=== JEU DU NOMBRE SECRET ===");
        
        do {
            System.out.print("Tentative " + (tentatives + 1) +
                " - Devinez le nombre : ");
            nombreSaisi = sc.nextInt();
            tentatives++;
            
            if (nombreSaisi > SECRET) {
                System.out.println("Trop grand !\n");
            } else if (nombreSaisi < SECRET) {
                System.out.println("Trop petit !\n");
            }
        } while (nombreSaisi != SECRET);
        
        System.out.println("\nBravo ! Trouve en " + tentatives +
            " tentative(s) !");
        
        sc.close();
    }
}
