import java.util.Random;


public class IteratedLocalSearch {

	//Déclaration de l'automate et des règles
	int n = 5 + 1;
	int [] rules = new int[n * n * n];
	Initialization init;
	Automata automate;

	public IteratedLocalSearch()
	{
		//Initialisation de l'automate et des règles
		Random generator = new Random();
		init = new Initialization();
		automate = new Automata(20);

		try {

			for(int i=0;i<216;i++)
			{		
				rules[i] = generator.nextInt(4);
			} 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public void iterer(int nb_iterations, int [] regles, int iterations_avant_perturbation)
	{
		//Nouvelle Random Seed
		Random generator = new Random();

		//Tableau de règles qui contiendra celles de la solution précédent si le voisin est moins bon
		int [] anciennes_regles;

		//Indice de la règle à changer
		int regle_a_changer = 0;
		int fit = 0;

		//Compteur qui servira à compter les itérations pour savoir quand lancer une perturbation
		int compteur = 0;

		//Flag pour signaler le lancement d'une perturbation et éviter de l'écraser avec les anciennes règles si moins bonne performacnes
		int flag_perturbation = 0;

		for (int i = 1; i < nb_iterations; i++) {

			//On réinitialise les règles pour garder celles de Initialization
			init.init(regles);
			//On stocke les règles pour pouvoir les recopier en cas de moins bonne performance
			anciennes_regles = copier_tableau(regles);
			//On choisit une règle au hasard à changer
			regle_a_changer = generator.nextInt(regles.length);
			//On lui affecte une valeur aléatoire
			regles[regle_a_changer] = generator.nextInt(4);

			if(compteur == iterations_avant_perturbation)
			{
				regles = perturbation(21, regles);
				flag_perturbation = 1;
				compteur = 0;
			}
			if(automate.f(regles, 20) >= fit)
			{
				if(automate.f(regles, 20) > fit) System.out.println("Nouvelle performance : " + fit);
				fit = automate.f(regles, 20);
			}
			else
			{
				if(flag_perturbation == 1) flag_perturbation = 0;
				else
				{
					regles = copier_tableau(anciennes_regles);
					compteur += 1;
					//System.out.println("BAD : " + automate.f(regles, 20));
				}
			}
		}
		System.out.println("Fin");

	}

	public int[] perturbation(int taille_perturbation, int[] regles)
	{
		Random generator = new Random();
		int regle_a_perturber = 0;
		int[] regles_a_retourner = copier_tableau(regles);
		
		for (int i = 0; i < taille_perturbation; i++) {
			
			regle_a_perturber = generator.nextInt(regles.length);
			regles_a_retourner[regle_a_perturber] = generator.nextInt(4);
			
		}
		
		return regles_a_retourner;
	}

	//Fonction qui permet de recopier un tableau en entrée
	public int[] copier_tableau(int[] tableau_a_copier)
	{
		int[] result = new int[tableau_a_copier.length];

		for (int i = 0; i < tableau_a_copier.length; i++) {
			result[i] = tableau_a_copier[i];	
		}

		return result;
	}

}
