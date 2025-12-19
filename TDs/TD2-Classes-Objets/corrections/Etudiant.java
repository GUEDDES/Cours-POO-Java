import java.util.Scanner;

public class Etudiant {
    private int matricule;
    private String nom;
    private String prenom;
    private double moyenne;
    
    // Constructeur par defaut
    public Etudiant() {
        this.matricule = 0;
        this.nom = "Inconnu";
        this.prenom = "Inconnu";
        this.moyenne = 0.0;
    }
    
    // Constructeur avec parametres
    public Etudiant(int matricule, String nom, String prenom, double moyenne) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
    }
    
    // Constructeur de copie
    public Etudiant(Etudiant e) {
        this.matricule = e.matricule;
        this.nom = e.nom;
        this.prenom = e.prenom;
        this.moyenne = e.moyenne;
    }
    
    // Getter pour la moyenne
    public double getMoyenne() {
        return moyenne;
    }
    
    // Methode saisir
    public void saisir() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Matricule : ");
        this.matricule = sc.nextInt();
        sc.nextLine(); // Consommer le retour a la ligne
        
        System.out.print("Nom : ");
        this.nom = sc.nextLine();
        
        System.out.print("Prenom : ");
        this.prenom = sc.nextLine();
        
        System.out.print("Moyenne : ");
        this.moyenne = sc.nextDouble();
    }
    
    // Methode afficher
    public void afficher() {
        System.out.println("Matricule : " + matricule);
        System.out.println("Nom : " + nom);
        System.out.println("Prenom : " + prenom);
        System.out.println("Moyenne : " + moyenne);
        System.out.println("Statut : " + (admis() ? "Admis" : "Non admis"));
        System.out.println("Mention : " + mention());
        System.out.println("------------------------");
    }
    
    @Override
    public String toString() {
        return "Etudiant [Matricule=" + matricule +
            ", Nom=" + nom + ", Prenom=" + prenom +
            ", Moyenne=" + moyenne +
            ", Mention=" + mention() + "]";
    }
    
    public boolean admis() {
        return moyenne >= 10;
    }
    
    public String mention() {
        if (moyenne < 10) {
            return "Aucune";
        } else if (moyenne < 12) {
            return "Passable";
        } else if (moyenne < 14) {
            return "Assez bien";
        } else if (moyenne < 16) {
            return "Bien";
        } else {
            return "Tres bien";
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Declarer n etudiants
        System.out.print("Nombre d'etudiants : ");
        int n = sc.nextInt();
        Etudiant[] etudiants = new Etudiant[n];
        
        // 2. Saisir les informations
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Etudiant " + (i + 1) + " ---");
            etudiants[i] = new Etudiant();
            etudiants[i].saisir();
        }
        
        // 3. Afficher la liste complete
        System.out.println("\n===== LISTE COMPLETE =====");
        for (int i = 0; i < n; i++) {
            System.out.println("\nEtudiant " + (i+1) + " :");
            etudiants[i].afficher();
        }
        
        // 4. Afficher les etudiants admis
        System.out.println("\n===== ETUDIANTS ADMIS =====");
        boolean aucunAdmis = true;
        for (int i = 0; i < n; i++) {
            if (etudiants[i].admis()) {
                etudiants[i].afficher();
                aucunAdmis = false;
            }
        }
        if (aucunAdmis) {
            System.out.println("Aucun etudiant admis.");
        }
        
        // 5. Afficher le meilleur etudiant
        System.out.println("\n===== MEILLEUR ETUDIANT =====");
        int indiceMeilleur = 0;
        double meilleureMoyenne = etudiants[0].getMoyenne();
        
        for (int i = 1; i < n; i++) {
            if (etudiants[i].getMoyenne() > meilleureMoyenne) {
                meilleureMoyenne = etudiants[i].getMoyenne();
                indiceMeilleur = i;
            }
        }
        
        System.out.println("Meilleure moyenne : " + meilleureMoyenne);
        System.out.println("\nEtudiant correspondant :");
        etudiants[indiceMeilleur].afficher();
        
        sc.close();
    }
}
