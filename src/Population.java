import java.util.Arrays;
import java.util.Random;


public class Population {
	
	//Population composée d'ensembles de regles
	
	Initialization init;
	Random generator;
	
	public Regles[] regles;
	public int taille_population;
	
	public int best_fitness = -1;
	
	public Population(int taille_population)
	{
		regles = new Regles[taille_population];
		this.taille_population = taille_population;
		
		for (int i = 0; i < taille_population; i++) {
			regles[i] = new Regles();
		}
	}
	
	public Population(Regles[] reg)
	{
		taille_population = reg.length;
		regles = new Regles[taille_population];
		
		for (int i = 0; i < taille_population; i++) {
			regles[i] = reg[i];
		}
	}
	
	public void init()
	{
		for (int i = 0; i < taille_population; i++) {
			regles[i].init();
		}
		
	}
	
	public void evalPopulation()
	{
		for (int i = 0; i < taille_population; i++) {
			regles[i].eval();
		}
	}
	
	public int bestFitness()
	{
		int best = -1;
		
		for (int i = 0; i < taille_population; i++) {
			if(regles[i].fitness > best) best = regles[i].fitness;
		}
		
		best_fitness = best;
		return best;
	}
	
	public Population clone(Population population_a_cloner)
	{
		Population result = new Population(population_a_cloner.taille_population);
		
		for (int i = 0; i < taille_population; i++) {
			regles[i] = regles[i].clone(population_a_cloner.regles[i]);
		}
		
		return result;
	}

	@Override
	public String toString() {
		
		String result = "";
		result += "Population de taille " + taille_population + " : ";
		for (int i = 0; i < taille_population; i++) {
			result += "\n" + regles[i].toString();
		}
		
		return result;
	}
	
	public String toStringLight()
	{
		String result = "";
		result += "Population de taille " + taille_population + " . Best Fitness : " + bestFitness();
		return result;
	}

}
