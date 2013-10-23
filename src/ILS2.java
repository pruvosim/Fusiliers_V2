import java.io.IOException;
import java.util.Random;


public class ILS2 {

	//Déclaration de l'automate et des règles
	int n = 5 + 1;
	int [] rules = new int[n * n * n];
	Initialization init;
	Automata automate;

	public ILS2()
	{
		//Initialisation de l'automate et des règles
		Random generator = new Random();
		init = new Initialization();
		automate = new Automata(20);

		init.init(rules);

	}

	public void iterer(int nb_iterations, int iterations_avant_perturbation) throws IOException
	{
		//Nouvelle Random Seed
		Random generator = new Random();
		//Sauvegarde save = new Sauvegarde("prout");
		int compteur = 0;

		//Tableau de règles qui contiendra celles de la solution précédent si le voisin est moins bon
		int [] meilleures_regles = new int[216];

		//Indice de la règle à changer
		int regle_a_changer = 0;

		//On réinitialise les règles pour garder celles de Initialization
		//init.init(regles);

		int fit_actuel = automate.f(rules, 20);
		int fit_ancien = -1;

		for (int i = 1; i < nb_iterations; i++) {

			System.out.println("Compteur : " + compteur);
			
			if(compteur == iterations_avant_perturbation)
			{
				System.out.println("Perturabtions");
				perturbations();
				compteur = 0;
			}
			
			if(fit_actuel >= fit_ancien)
			{

				meilleures_regles = copier_tableau(rules);
				fit_ancien = fit_actuel;

				//On stocke les règles pour pouvoir les recopier en cas de moins bonne performance
				if(fit_actuel > fit_ancien)
				{
					System.out.println("Meilleure perf : " + fit_actuel);
					//save.printToFile(fit_actuel, meilleures_regles);
				}
				
				compteur = 0;
			}
			else
			{
				rules = copier_tableau(meilleures_regles);
				compteur ++;
			}


			//On choisit une règle au hasard à changer
			regle_a_changer = generator.nextInt(rules.length);

			//On lui affecte une valeur aléatoire
			rules[regle_a_changer] = generator.nextInt(4);

			//System.out.println("Fitness : " + fit_actuel);
			
			fit_actuel = automate.f(rules, 20);
			
			
		}
		System.out.println("Fin");

	}
	
	public void perturbations()
	{
		perturbation_uniforme();
		//perturbation_bit_flip();
	}
	
	public void perturbation_uniforme()
	{
		Random generator = new Random();
		
		for (int i = 0; i < rules.length; i++) {
			if(generator.nextBoolean())
			{
				int new_val = generator.nextInt(4);
				while(new_val == rules[i]) new_val = generator.nextInt(4);
				rules[i] = new_val;
			}
			
		}
	}
	
	public void perturbation_bit_flip()
	{
		Random generator = new Random();
		
		for (int i = 0; i < rules.length; i++) {
			if(generator.nextInt(216) == i)
			{
				int new_val = generator.nextInt(4);
				while(new_val == rules[i]) new_val = generator.nextInt(4);
				rules[i] = new_val;
			}
			
		}
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
