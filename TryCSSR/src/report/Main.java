package report;

import java.util.NoSuchElementException;


public class Main {

	public static void main(String[] args){
		try
		{
			parseCommandLine(args);
			Login.execute();
			SelectTimeRange.entertime();
		}
		catch(Exception e)
		{
			//throw new NoSuchElementException("didnt execute; ex");
			System.out.print("trace: ");
			e.printStackTrace();
		}
	}

	private static void parseCommandLine(String[] args) {
		if(args.equals(null) || args.length ==  0){
			throw new NullPointerException("Missing Command line argument");
		}
		//here passing the browser value from the command line argument
		else{
			Login.browser = args[0];
		}
	}
}

