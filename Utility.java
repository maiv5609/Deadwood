import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {
	

	public static String inputReader(){
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = br.readLine();
			//br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;	
	}

}
