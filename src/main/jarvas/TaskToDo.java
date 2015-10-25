package main.jarvas;
/**
 * Task class is a helper class
 */

public class TaskToDo implements Task{
	
	public static final String EMPTY_SPACE = " ";

	private static final String LABEL_TASK_NAME = "task name = ";

	private static final String LABEL_TASK_DUEDATE = "task due date = ";
	
	private String taskName;
	private String dueDate;
	private int index;
	
	public TaskToDo(){
		taskName = new String();
		dueDate = new String();
		index = 0;
	}
	
	public TaskToDo(String taskName, int index){
		setName(taskName);
		this.index = index;
		dueDate = null;
	}
	
	public TaskToDo(String taskName, String dueDate,  int index){
		setName(taskName);
		this.index = index;
		setDueDate(dueDate);
	}
	
	public int getIndex(){
		return index;
	}
	
	@Override
	public String getName() {
		return taskName;
	}

	@Override
	public void setName(String taskName) {
		this.taskName = taskName;
	}


	public String getDueDate() {
		return dueDate;
	}


	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	@Override
	public String print(){
		return taskName + EMPTY_SPACE + dueDate.toString();
	}
	
	/**
	 * This function return tasks in term of string
	 */
	@Override
	public String toString(){
		String temp = "";
		temp=temp.concat(String.format(LABEL_TASK_NAME, taskName));
		temp = temp.concat("    "+ String.format(LABEL_TASK_DUEDATE, dueDate));
		return temp;
		
	}
}
