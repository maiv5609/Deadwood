import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utility {
    
    
    /**
     * Reads input from user
     * 		returns: input : String
     */
    public  String inputReader(){
        String input = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return input;
    }
    
    
    /**
     * Parse input and returns array of parameters,
     * where first param is always name of the action, the 2nd and the 3d depends on
     * the action name (no more than 3)
     * 		params : parameters : String
     * 		returns : params : String[]
     */
    public String[] parseParams(String parameters){
        
        String[] params = parameters.split(" ");
        for (int i = 0; i < params.length; i++){
            params[i] = params[i].toLowerCase();
        }
        return params;	
    }
    
    public List<String> getColorArray(){
    	List<String> colors = new ArrayList<String>();
    	colors.add(Constants.WHITE_COLOR);
    	colors.add(Constants.BLACK_COLOR);
    	colors.add(Constants.RED_COLOR);
    	colors.add(Constants.ORANGE_COLOR);
    	colors.add(Constants.BLUE_COLOR);
    	colors.add(Constants.GREEN_COLOR);
    	colors.add(Constants.PEACH_COLOR);
    	colors.add(Constants.YELLOW_COLOR);
    	
    	return colors;
    }
    
}
