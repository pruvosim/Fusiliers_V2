import java.util.Random;


public class IteratedLocalSearch {

	//D�claration de l'automate et des r�gles
	int n = 5 + 1;
	int [] rules = new int[n * n * n];
	Initialization init;
	Automata automate;

	public IteratedLocalSearch()
	{
		//Initialisation de l'automate et des r�gles
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

		//Tableau de r�gles qui contiendra celles de la solution pr�c�dent si le voisin est moins bon
		int [] anciennes_regles;

		//Indice de la r�gle � changer
		int regle_a_changer = 0;
		int fit = 0;

		//Compteur qui servira � compter les it�rations pour savoir quand lancer une perturbation
		int compteur = 0;

		//Flag pour signaler le lancement d'une perturbation et �viter de l'�craser avec les anciennes r�gles si moins bonne performacnes
		int flag_perturbation = 0;

		for (int i = 1; i < nb_iterations; i++) {

			//On r�initialise les r�gles pour garder celles de Initialization
			init.init(regles);
			//On stocke les r�gles pour pouvoir les recopier en cas de moins bonne performance
			anciennes_regles = copier_tableau(regles);
			//On choisit une r�gle au hasard � changer
			regle_a_changer = generator.nextInt(regles.length);
			//On lui affecte une valeur al�atoire
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

	//Fonction qui permet de recopier un tableau en entr�e
	public int[] copier_tableau(int[] tableau_a_copier)
	{
		int[] result = new int[tableau_a_copier.length];

		for (int i = 0; i < tableau_a_copier.length; i++) {
			result[i] = tableau_a_copier[i];	
		}

		return result;
	}

}
