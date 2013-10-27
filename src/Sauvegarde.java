import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Sauvegarde {
	
	//Classe pour sauvergarder les resultats
	
	public PrintWriter ecrivain;

	public Sauvegarde(String nom_fichier, int fitness, int[] rules) throws IOException{

		String outName = "C:/Users/Simon/Desktop" + nom_fichier + ".dat";

		ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(outName)));
		
		printToFile(fitness, rules);
		
		ecrivain.close();
		
		//System.out.println("Sauvergarde effectuée");

	}

	public void printToFile(int fitness, int [] rules) {
		ecrivain.print(fitness);
		for(int i = 0; i < 216; i++) {
			ecrivain.print(" ");
			ecrivain.print(rules[i]);
		}
		ecrivain.println();
		
	}
	
	

}
