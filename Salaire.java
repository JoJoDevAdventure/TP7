package TP7;

public class Salaire {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vente("Pierre", "Business ", 45, "1995", 30000.0));
        p.ajouterEmploye(new Representation("Léon", "Vendtout ", 25, "2001", 2000.0));
        p.ajouterEmploye(new Production("Yvan", "doseur ", 29, "2000", 1000));
        p.ajouterEmploye(new Manutention("Jeanne", "Dupont ", 32, "1993",45.0));
        p.ajouterEmploye(new ProductionDanger("Jean", "Filippe ", 28, "2000", 1000));
        p.ajouterEmploye(new ManutentionDanger("Ali", "Abordage ", 35, "2002", 45.0));
        
        p.calculerSalaires();
        System.out.println("Le salaire moyen de l'entreprise eset = " + p.salaireMoyen() + "$");

    }
}

abstract class Employe {
    String nom;
    String prenom;
    int age;
    String dateEntree;

    Employe(String nom, String prenom, int age, String dateEntree) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.dateEntree = dateEntree;
    }

    abstract Double calculerSalaire();

    String getNom() {
        return "L'employé : " + prenom + nom;
    }
}

class Vente extends Employe {

    double chiffreAffaire;

    Vente(String nom, String prenom, int age, String dateEntree,Double chiffreAffaire) {
        super(nom, prenom, age, dateEntree);
        this.chiffreAffaire = chiffreAffaire ;
    }

    @Override
    Double calculerSalaire() {
        return (chiffreAffaire*20)/100 + 400; 
    }

    @Override
    String getNom() {
        return super.getNom() + " Vendeur";
    }
}

class Representation extends Employe {

    double chiffreAffaire;

    Representation(String nom, String prenom, int age, String dateEntree,Double chiffreAffaire) {
        super(nom, prenom, age, dateEntree);
        this.chiffreAffaire = chiffreAffaire ;
    }

    @Override
    Double calculerSalaire() {
        return (chiffreAffaire*20)/100 + 800; 
    }

    @Override
    String getNom() {
        return super.getNom() + " Representant";
    }
}

class Production extends Employe {

    int nombreUnit;

    Production(String nom, String prenom, int age, String dateEntree,int nombreUnit) {
        super(nom, prenom, age, dateEntree);
        this.nombreUnit = nombreUnit ;
    }

    @Override
    Double calculerSalaire() {
        return nombreUnit * 5.0 ;
    }

    @Override
    String getNom() {
        return super.getNom() + " Technicien";
    }
}

class Manutention extends Employe {

    double nombreHeure;

    Manutention(String nom, String prenom, int age, String dateEntree, Double nombreHeure) {
        super(nom, prenom, age, dateEntree);
        this.nombreHeure = nombreHeure ;
    }

    @Override
    Double calculerSalaire() {
        return nombreHeure * 65;
    }

    @Override
    String getNom() {
        return super.getNom() + " Manutentier";
    }
}

class ProductionDanger extends Production {
    ProductionDanger(String nom, String prenom, int age, String dateEntree,int nombreUnit) {
        super(nom, prenom, age, dateEntree, nombreUnit);
    }

    @Override
    Double calculerSalaire() {
        return super.calculerSalaire() + ARisque.primeARisque;
    }

    @Override
    String getNom() {
        return super.getNom() + " producteur à risque";
    }
}

class ManutentionDanger extends Manutention {
    ManutentionDanger(String nom, String prenom, int age, String dateEntree,Double nombreHeure) {
        super(nom, prenom, age, dateEntree, nombreHeure);
    }

    @Override
    Double calculerSalaire() {
        return super.calculerSalaire() + ARisque.primeARisque;
    }

    @Override
    String getNom() {
        return super.getNom() + " manutantier à risque";
    }
}

interface ARisque {
    final int primeARisque = 200;
}

class Personnel {
	private static int id=0;
	private Employe listemploye[] = new Employe[100];

	void ajouterEmploye(Employe employe) {
		listemploye[id]=employe ;
		id++;
	}

    void calculerSalaires() {
	    for (int i = 0;i<id;i++) {
		System.out.println("le salaire de "+listemploye[i].getNom()+ " " +listemploye[i].calculerSalaire());
	    }
    }

    double salaireMoyen() {
	    Double s= 0.0;
	    for (int i = 0;i<id;i++) {
	    	s=s+ listemploye[i].calculerSalaire();
	    }
	    return(s/(id+1));
	}
}