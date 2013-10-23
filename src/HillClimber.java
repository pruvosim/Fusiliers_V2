import java.io.IOException;
import java.util.Random;


public class HillClimber {

	//Déclaration de l'automate et des règles
	int n = 5 + 1;
	int [] rules = new int[n * n * n];
	Initialization init;
	Automata automate;
	Random generator;

	public HillClimber()
	{
		//Initialisation de l'automate et des règles
		generator = new Random();
		init = new Initialization();
		automate = new Automata(20);

		init.init(rules);

	}

	public void iterer(int nb_iterations) throws IOException
	{

		//Tableau de règles qui contiendra celles de la solution précédent si le voisin est moins bon
		int [] meilleures_regles = new int[216];

		//Indice de la règle à changer
		int regle_a_changera = 0;
		int regle_a_changerb = 0;
		int regle_a_changerc = 0;
		int regle_a_changerd = 0;
		int regle_a_changere = 0;

		//On réinitialise les règles pour garder celles de Initialization
		//init.init(regles);

		int fit_actuel = automate.f(rules, 20);
		int fit_ancien = -1;

		for (int i = 1; i < nb_iterations; i++) {
			
			generator = new Random();
			if(fit_actuel >= fit_ancien)
			{
				meilleures_regles = copier_tableau(rules);

				//On stocke les règles pour pouvoir les recopier en cas de moins bonne performance
				if(fit_actuel > fit_ancien)
				{
					System.out.println("Meilleure perf : " + fit_actuel);
					Sauvegarde save = new Sauvegarde("prout", fit_actuel, meilleures_regles);
				}
				
				fit_ancien = fit_actuel;
			}
			else
			{
				rules = copier_tableau(meilleures_regles);
			}


			//On choisit une règle au hasard à changer
			regle_a_changera = generator.nextInt(rules.length);
			regle_a_changerb = generator.nextInt(rules.length);
			regle_a_changerc = generator.nextInt(rules.length);
			regle_a_changerd = generator.nextInt(rules.length);
			regle_a_changere = generator.nextInt(rules.length);

			//On lui affecte une valeur aléatoire
			rules[regle_a_changera] = generator.nextInt(4);
			rules[regle_a_changerb] = generator.nextInt(4);
			rules[regle_a_changerc] = generator.nextInt(4);
			rules[regle_a_changerd] = generator.nextInt(4);
			rules[regle_a_changere] = generator.nextInt(4);
			
			init.modif(rules);
			
			//System.out.println("Fitness : " + fit_actuel);
			
			fit_actuel = automate.f(rules, 20);
		}
		System.out.println("Fin");

	}

	//Fonction qui permet de recopier un tableau en entrée
	public int[] copier_tableau(int[] tableau_a_copier)
	{
		int[] result = new int[tableau_a_copier.length];

		for (int i = 0; i < tableau_a_copier.length; i++) {
			result[i] = tableau_a_copier[i];	
			//System.out.print(result[i] + " ");
		}

		return result;
	}

}
