import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		automate = new Automata(25);

		init.init(rules);

	}

	public void iterer(int nb_iterations) throws IOException
	{

		//Tableau de règles qui contiendra celles de la solution précédente si le voisin est moins bon
		int [] meilleures_regles = new int[216];

		//Indice de la règle à changer
		int regle_a_changer = 0;
		
		int nb_changements = 0;
		

		//On réinitialise les règles pour garder celles de Initialization
		//init.init(regles);

		int fit_actuel = automate.f(rules, 25);
		int fit_ancien = -1;
		int best_fitness = -1;
		
		nb_changements = generator.nextInt(51);
		
		String outName = "C:/Users/Simon/Desktop/Resultats_HC/HillClimber_";
		String date = new SimpleDateFormat("dd_MM_yy-HH_mm_ss").format(new Date());
		outName += date + ".dat";
		
		PrintWriter ecrivain;
		
		ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(outName)));

		for (int i = 1; i < nb_iterations; i++) {
			
			generator = new Random();
			if(fit_actuel >= fit_ancien)
			{
				meilleures_regles = copier_tableau(rules);

				if(fit_actuel > fit_ancien)
				{
					//System.out.println("Meilleure perf : " + fit_actuel);
					printToFile(fit_actuel, meilleures_regles, ecrivain);
					best_fitness = fit_actuel;
					//Sauvegarde save = new Sauvegarde("prout", fit_actuel, meilleures_regles);
				}
				
				fit_ancien = fit_actuel;
			}
			else
			{
				rules = copier_tableau(meilleures_regles);
			}
			

			//On change j regles au lieu d'une seule comme dans l'exemple du OneMax
			for (int j = 0; j < nb_changements; j++) {
				
				regle_a_changer = generator.nextInt(rules.length);
				rules[regle_a_changer] = generator.nextInt(4);
				
			}
			
			//On laisse les regles de bases
			init.ajoutReglesBase(rules);
			
			fit_actuel = automate.f(rules, 25);
		}
		System.out.println("Fin. Best Fitness : " + best_fitness + " Nb Changements : " + nb_changements +  ". Fichier : " + outName);
		ecrivain.close();
	}
	
	public void runForStatistics(int nb_HillClimber, int nb_iterations) throws IOException
	{
		for (int i = 0; i < nb_HillClimber; i++) 
		{
			HillClimber HC = new HillClimber();
			HC.iterer(nb_iterations);
		}
	}
	
	public static void printToFile(int fitness, int [] rules, PrintWriter ecrivain) {
		ecrivain.print(fitness);
		for(int i = 0; i < 216; i++) {
			ecrivain.print(" ");
			ecrivain.print(rules[i]);
		}
		ecrivain.println();
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
