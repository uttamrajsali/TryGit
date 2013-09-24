package report;

import java.util.NoSuchElementException;


public class Main {

	public static void main(String[] args){
		try
		{
			parseCommandLine(args);
			//Login s = new Login();
			//s.execute();
			//GenerateHtml.execute();
			SelectTimeRange x = new SelectTimeRange();
			//x.findthelables();
			//x.search();
			//x.dnisfilter();
			//x.abfilter();
			x.cssrNumbers();
			//x.numberFormat();
		}
		catch(Exception e)
		{
			System.out.print("trace: ");
			e.printStackTrace();
		}
	}
	
	private static void parseCommandLine(String[] args) {
		if(args.equals(null) || args.length ==  0){
			throw new NullPointerException("Missing Command line argument");
		}
		//here I am passing the browser name from the command line argument
		else{
			Login.browser = args[0];
		}
	}
}

