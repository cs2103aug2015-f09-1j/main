package repo;
import java.util.Vector;

public class Logic {
	
	String commandStr;
	String contentStr;
	Parser.CommandType commandType;
	Logic(){
		System.out.println("Logic is ready.");
	}
	public void getInput(String str){
		commandStr = getFirstWord(str);	
		contentStr = removeFirstWord(str);
		commandType = Parser.determineCommandType(commandStr);
	}
	public void execute(){
		// TODO execute the command
	}
	public void getOriginalTasks(Vector<String> returnTasks) {
		// TODO initialize the vector for tasks
		
	}
	public Vector<String> returnNewTasks() {
		// TODO return the new vector contains tasks after each operation
		return null;
	}
	public void getOutput(String returnOutput) {
		// TODO deal with the output from storage
		
	}
	public String returnOutput() {
		/** TODO return the feedback to ui to print on screen
		 * (this should contains the feedback from storage, 
		 * use \n to make a new line in the string)
		 **/
		return null;
	}
	private static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}
	private static String removeFirstWord(String userCommand) {
		String temp = userCommand.replace(getFirstWord(userCommand), "").trim();
		return temp;
	}
}
