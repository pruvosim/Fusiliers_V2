
public class TestPopulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Population pop = new Population(50);
		pop.init();
		pop.evalPopulation();
		System.out.println(pop.toString());
		
		
	}

}
