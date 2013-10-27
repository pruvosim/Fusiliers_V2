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
