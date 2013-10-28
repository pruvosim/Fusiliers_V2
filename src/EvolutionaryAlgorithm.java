import java.util.Random;


public class EvolutionaryAlgorithm {

	public Population parents;
	public Population geniteurs;
	public Population enfants;

	int mu; //Taille des parents
	int lambda; //Taille des geniteurs et enfants

	public EvolutionaryAlgorithm(int mu, int lambda)
	{
		parents = new Population(mu);
		geniteurs = new Population(lambda);
		enfants = new Population(lambda);

		this.mu = mu;
		this.lambda = lambda;

		parents.init();
		//On évalue les parents pour avoir le fitness des solutions
		parents.evalPopulation();
		parents.bestFitness();
	}
	
	public void run()
	{
		System.out.println("Generation 1");
		selectionParentale();
		//mutation();
		croisement();
		System.out.println(this.toString());
		
		System.out.println("\nGeneration 2");
		parents = selectionFuturParents();
		selectionParentale();
		//mutation();
		croisement();
		System.out.println(this.toString());
	}

	public void selectionParentale()
	{
		Random rand = new Random();
		int first_parent = -1;
		int second_parent = -1;

		//Selection parentale par tournoi
		for (int i = 0; i < lambda; i++) 
		{
			first_parent = rand.nextInt(parents.taille_population);
			second_parent = rand.nextInt(parents.taille_population);

			//Tournoi entre deux parents aléatoires (avec remise)
			if(parents.regles[first_parent].fitness > parents.regles[second_parent].fitness)
			{
				geniteurs.regles[i] = parents.regles[first_parent];
			}
			else
			{
				geniteurs.regles[i] = parents.regles[second_parent];
			}
		}

		//On évalue les geniteurs pour avoir le fitness des solutions
		geniteurs.evalPopulation();
		geniteurs.bestFitness();
	}

	public void mutation()
	{
		//Bit-Flip

		enfants = geniteurs.clone(geniteurs);

		Random rand = new Random();
		double probabilite_changement = 0;
		double reference_changement = (1.0 / 216);
		System.out.println("Reference changement :   1/" +216 + " = " + reference_changement);

		for (int i = 0; i < lambda; i++) 
		{

			//Sur chaque regle du tableau de regles
			for (int j = 0; j < 216; j++) {

				probabilite_changement = rand.nextDouble();
				//System.out.println("Probabilite Changement : " + probabilite_changement);
				if(probabilite_changement <= reference_changement)
				{

					enfants.regles[i].rules[j] = rand.nextInt();

				}

			}

		}

		enfants.evalPopulation();
		enfants.bestFitness();
	}


	public void croisement()
	{
		//Uniforme

		enfants = geniteurs.clone(geniteurs);

		Random rand = new Random();
		int first_rule = -1;
		int second_rule = -1;
		boolean probabilite_changement = false;
		int temp = -1;


		for (int i = 0; i < lambda; i++) 
		{
			//On prend deux Regles au hasard dans une population pour le croisement
			first_rule = rand.nextInt(enfants.taille_population);
			second_rule = rand.nextInt(enfants.taille_population);

			//Sur chaque regle du tableau de regles
			for (int j = 0; j < 216; j++) {

				probabilite_changement = rand.nextBoolean();
				if(probabilite_changement)
				{
					temp = enfants.regles[first_rule].rules[j];
					enfants.regles[first_rule].rules[j] = enfants.regles[second_rule].rules[j];
					enfants.regles[second_rule].rules[j] = temp;
				}

			}

		}
		enfants.evalPopulation();
		enfants.bestFitness();

	}

	public Population selectionFuturParents()
	{
		Regles[] all_rules = new Regles[mu + lambda];

		for (int i = 0; i < parents.taille_population; i++) {
			all_rules[i] = parents.regles[i];
		}

		for (int i = 0; i < enfants.taille_population; i++) {
			all_rules[i + mu] = enfants.regles[i];
		}

		int best_fitness = 0;

		for (int i = 0; i < all_rules.length; i++) {
			if(all_rules[i].eval() > best_fitness) best_fitness = all_rules[i].eval();
		}

		Regles[] best_rules = new Regles[mu + lambda];

		int p=0;

		while(best_fitness > -1)
		{

			for (int i = 0; i < all_rules.length; i++) {

				if(all_rules[i].eval() == best_fitness)
				{
					best_rules[p] = all_rules[i];
					p ++;
				}
			}
			best_fitness --;
		}
		
		Regles[] regles_futurs_parents = new Regles[mu];
		
		for (int i = 0; i < regles_futurs_parents.length; i++) {
			regles_futurs_parents[i] = best_rules[i];
		}
		
		Population futur_parents = new Population(regles_futurs_parents);
		futur_parents.evalPopulation();
		futur_parents.bestFitness();
		return futur_parents;

	}


	public String toString()
	{
		String result = "";
		result += "Parents   : " + parents.toStringLight();
		result += "\nGeniteurs : " + geniteurs.toStringLight();
		result += "\nEnfants   : " + enfants.toStringLight();

		return result;
	}

}
