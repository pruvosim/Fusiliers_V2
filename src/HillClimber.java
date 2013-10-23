import java.io.IOException;
import java.util.Random;


public class HillClimber {

	//D�claration de l'automate et des r�gles
	int n = 5 + 1;
	int [] rules = new int[n * n * n];
	Initialization init;
	Automata automate;
	Random generator;

	public HillClimber()
	{
		//Initialisation de l'automate et des r�gles
		generator = new Random();
		init = new Initialization();
		automate = new Automata(20);

		init.init(rules);

	}

	public void iterer(int nb_iterations) throws IOException
	{

		//Tableau de r�gles qui contiendra celles de la solution pr�c�dent si le voisin est moins bon
		int [] meilleures_regles = new int[216];

		//Indice de la r�gle � changer
		int regle_a_changera = 0;
		int regle_a_changerb = 0;
		int regle_a_changerc = 0;
		int regle_a_changerd = 0;
		int regle_a_changere = 0;

		//On r�initialise les r�gles pour garder celles de Initialization
		//init.init(regles);

		int fit_actuel = automate.f(rules, 20);
		int fit_ancien = -1;

		for (int i = 1; i < nb_iterations; i++) {
			
			generator = new Random();
			if(fit_actuel >= fit_ancien)
			{
				meilleures_regles = copier_tableau(rules);

				//On stocke les r�gles pour pouvoir les recopier en cas de moins bonne performance
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


			//On choisit une r�gle au hasard � changer
			regle_a_changera = generator.nextInt(rules.length);
			regle_a_changerb = generator.nextInt(rules.length);
			regle_a_changerc = generator.nextInt(rules.length);
			regle_a_changerd = generator.nextInt(rules.length);
			regle_a_changere = generator.nextInt(rules.length);

			//On lui affecte une valeur al�atoire
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

	//Fonction qui permet de recopier un tableau en entr�e
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
