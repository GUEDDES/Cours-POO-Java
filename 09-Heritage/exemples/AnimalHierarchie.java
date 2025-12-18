/**
 * Exemple : Hiérarchie d'Héritage Animal
 * 
 * Objectif : Illustrer l'héritage, la redéfinition et le polymorphisme
 * Concepts :
 * - Classe mère Animal
 * - Classes filles (Chien, Chat, Oiseau)
 * - Redéfinition de méthodes (@Override)
 * - super pour appeler la méthode parent
 * - Polymorphisme
 * 
 * @author Cours POO Java
 */

// Classe mère
class Animal {
    protected String nom;
    protected int age;
    protected String espece;
    
    public Animal(String nom, int age, String espece) {
        this.nom = nom;
        this.age = age;
        this.espece = espece;
    }
    
    public void manger() {
        System.out.println(🍽️ " + nom + " mange");
    }
    
    public void dormir() {
        System.out.println("😴 " + nom + " dort");
    }
    
    public void faireDuBruit() {
        System.out.println("🔊 " + nom + " fait du bruit");
    }
    
    public void afficher() {
        System.out.println("🐾 Nom : " + nom + ", Âge : " + age + " ans, Espèce : " + espece);
    }
}

// Classe fille : Chien
class Chien extends Animal {
    private String race;
    
    public Chien(String nom, int age, String race) {
        super(nom, age, "Chien");  // Appel du constructeur parent
        this.race = race;
    }
    
    // Redéfinition de faireDuBruit
    @Override
    public void faireDuBruit() {
        System.out.println("🐶 " + nom + " aboie : Woof! Woof!");
    }
    
    // Méthode spécifique au chien
    public void jouerAvecBalle() {
        System.out.println("⚽ " + nom + " joue avec une balle");
    }
    
    @Override
    public void afficher() {
        super.afficher();  // Appelle la méthode de Animal
        System.out.println("   Race : " + race);
    }
}

// Classe fille : Chat
class Chat extends Animal {
    private String couleur;
    
    public Chat(String nom, int age, String couleur) {
        super(nom, age, "Chat");
        this.couleur = couleur;
    }
    
    @Override
    public void faireDuBruit() {
        System.out.println("🐱 " + nom + " miaule : Miaou! Miaou!");
    }
    
    public void grimper() {
        System.out.println("🌳 " + nom + " grimpe à un arbre");
    }
    
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("   Couleur : " + couleur);
    }
}

// Classe fille : Oiseau
class Oiseau extends Animal {
    private double envergure;  // en cm
    
    public Oiseau(String nom, int age, double envergure) {
        super(nom, age, "Oiseau");
        this.envergure = envergure;
    }
    
    @Override
    public void faireDuBruit() {
        System.out.println("🐦 " + nom + " chante : Cui! Cui!");
    }
    
    public void voler() {
        System.out.println("🛫 " + nom + " vole dans le ciel");
    }
    
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("   Envergure : " + envergure + " cm");
    }
}

public class AnimalHierarchie {
    public static void main(String[] args) {
        System.out.println("=== HÉRITAGE : HIÉRARCHIE ANIMAL ===");
        
        // Créer des animaux
        System.out.println("\n--- Création des animaux ---");
        Chien chien = new Chien("Rex", 5, "Berger Allemand");
        Chat chat = new Chat("Minou", 3, "Noir");
        Oiseau oiseau = new Oiseau("Tweety", 1, 15.5);
        
        // Afficher les informations
        System.out.println("\n--- Informations ---");
        chien.afficher();
        System.out.println();
        chat.afficher();
        System.out.println();
        oiseau.afficher();
        
        // Méthodes héritées (communes à tous)
        System.out.println("\n--- Méthodes communes (héritées) ---");
        chien.manger();
        chat.manger();
        oiseau.manger();
        
        System.out.println();
        chien.dormir();
        chat.dormir();
        oiseau.dormir();
        
        // Méthodes redéfinies (polymorphisme)
        System.out.println("\n--- Méthodes redéfinies (polymorphisme) ---");
        chien.faireDuBruit();
        chat.faireDuBruit();
        oiseau.faireDuBruit();
        
        // Méthodes spécifiques
        System.out.println("\n--- Méthodes spécifiques ---");
        chien.jouerAvecBalle();
        chat.grimper();
        oiseau.voler();
        
        // POLYMORPHISME : tableau d'animaux
        System.out.println("\n--- Polymorphisme (tableau d'animaux) ---");
        Animal[] animaux = {chien, chat, oiseau};
        
        System.out.println("Tous les animaux font du bruit :");
        for (Animal animal : animaux) {
            animal.faireDuBruit();  // Appelle la bonne méthode selon le type réel
        }
        
        System.out.println("\nTous les animaux dorment :");
        for (Animal animal : animaux) {
            animal.dormir();
        }
    }
}
