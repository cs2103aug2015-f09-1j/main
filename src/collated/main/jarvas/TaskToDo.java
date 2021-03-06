package main.jarvas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import executor.GetRepeat;
import executor.GetRepeat.RepeatingFrequency;

/**
 * Task class is a helper class
 */
//@@author A0126259B
public class TaskToDo implements Task, Comparable<TaskToDo>{
	
	public static final String EMPTY_SPACE = " ";
	private static final String LABEL_TASK_NAME = "task name = ";
	private static final String LABEL_TASK_DUEDATE = "task due date = ";
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private String taskName;
	private int index;
	DateOfEvent dateOfEvent;
	Date repeatDate;
	private boolean done;
	RepeatingFrequency frequency;
	
	//@@author A0126259B
	public TaskToDo(){
		taskName = new String();
		dateOfEvent = new DateOfEvent();
		dateOfEvent.setStartDate(null);
		index = 0;
		frequency=RepeatingFrequency.NOTREPEATING;
		repeatDate = new Date();
		dateOfEvent.setUntilDate(null);
	}
	
	//@@author A0126259B
	public TaskToDo(String taskName, int index, boolean status){
		setName(taskName);
		this.index = index;
		dateOfEvent = new DateOfEvent();
		done = status;
		frequency=RepeatingFrequency.NOTREPEATING;
		dateOfEvent.setUntilDate(null);
	}
	
	//@@author A0126259B
	public TaskToDo(String taskName, String dueDate,  int index, boolean status){
		setName(taskName);
		this.index = index;
		dateOfEvent = new DateOfEvent();
		dateOfEvent.setStartDate(JParser.dateConverter(dueDate));
		done = status;
		frequency=RepeatingFrequency.NOTREPEATING;
		dateOfEvent.setUntilDate(null);
	}
	
	//@@author A0126259B
	public TaskToDo(String taskName, String dueDate,  int index, boolean status,RepeatingFrequency frequency){
		setName(taskName);
		this.index = index;
		dateOfEvent = new DateOfEvent();
		dateOfEvent.setStartDate(JParser.dateConverter(dueDate));
		done = status;
		this.frequency=frequency;
		dateOfEvent.setUntilDate(null);
	}
	
	//@@author A0126259B
	public TaskToDo(String taskName, String dueDate,  int index, boolean status,RepeatingFrequency frequency,String untilDate){
		setName(taskName);
		this.index = index;
		dateOfEvent = new DateOfEvent();
		dateOfEvent.setStartDate(JParser.dateConverter(dueDate));
		done = status;
		this.frequency=frequency;
		dateOfEvent.setUntilDate(JParser.dateConverter(untilDate));
		
	}
	
	/**
	 * @param name
	 * @param date
	 * @param parseInt
	 * @param done2
	 * @param frequency2
	 */
	//@@author A0126259B
	public TaskToDo(String taskName, String dueDate, int index, boolean status, String frequency) {
		// TODO Auto-generated constructor stub
		setName(taskName);
		this.index = index;
		dateOfEvent = new DateOfEvent();
		dateOfEvent.setStartDate(JParser.dateConverter(dueDate));
		done = status;
		this.frequency= GetRepeat.convertStrtoFrequency(frequency);
		dateOfEvent.setUntilDate(null);
	}
	
	//@@author A0126259B
	public TaskToDo(String taskName, String dueDate, int index, boolean status, String frequency,String untilDate) {
		// TODO Auto-generated constructor stub
		setName(taskName);
		this.index = index;
		dateOfEvent = new DateOfEvent();
		dateOfEvent.setStartDate(JParser.dateConverter(dueDate));
		done = status;
		this.frequency= GetRepeat.convertStrtoFrequency(frequency);
		dateOfEvent.setUntilDate(JParser.dateConverter(untilDate));
	}

	/**
	 * @param frequency2
	 * @return
	 */
	//@@author A0126259B
	public RepeatingFrequency getFrequency() {
		return frequency;
	}

	//@@author A0126259B
	public void setFrequency(RepeatingFrequency frequency) {
		this.frequency = frequency;
	}
	
	//@@author A0126259B
	public int getIndex(){
		return index;
	}
	
	//@@author A0126259B
	public void setDone(String status){
		if(status.equals("done")){
			done = true;
		}
		else if (status.equals("undone")){
			done = false;
		} 
	}
	
	//@@author A0126259B
	public boolean getDone(){
		return done;
	}
	
	//@@author A0126259B
	@Override
	public String getName() {
		return taskName;
	}

	//@@author A0126259B
	@Override
	public void setName(String taskName) {
		this.taskName = taskName;
	}

	//@@author A0126259B
	public void setStart(String startDate){
		dateOfEvent.setStartDate(JParser.dateConverter(startDate));
	}	
	
	//@@author A0126259B
	public Date getStartDate() {
		return dateOfEvent.getStartDate();
	}
	
	//@@author A0126259B
	public void setUntil(String untilDate){
		dateOfEvent.setUntilDate(JParser.dateConverter(untilDate));
	}
	
	//@@author A0126259B
	public Date getUntilDate(){
		return dateOfEvent.getUntilDate();
	}
	
	//@@author A0126259B
	public String getStringUntilDate(){
		if(getUntilDate()!=null){
			return sdf.format(getUntilDate());	
		}
		else{
			return "";
		}
	}

	//@@author A0126259B
	public String getStringStartDate(){
		if(getStartDate()!=null){
			return sdf.format(getStartDate());	
		}
		else{
			return "--";
		}
	}
	
	//@@author A0126259B
	public String nextDate(){
		return getNextDate();
	}
	
	//@@author A0126259B
	public String getNextDate(){
		GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(getStartDate());
	    switch (frequency) {
		case DAILY:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case MONTHLY:
			calendar.add(Calendar.MONTH, 1);
			break;
		case YEARLY:
			calendar.add(Calendar.YEAR, 1);
			break;
		case WEEKLY:
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			break;
		default:
			
			break;
		}
		return sdf.format(calendar.getTime());
	}
	
	//@@author A0126259B
	public Date nextCompareDate(){
		return getNextCompareDate();
	}
	
	//@@author A0126259B
	public Date getNextCompareDate(){
		GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(getStartDate());
	    switch (frequency) {
		case DAILY:
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			break;
		case MONTHLY:
			calendar.add(Calendar.MONTH, 1);
			break;
		case YEARLY:
			calendar.add(Calendar.YEAR, 1);
			break;
		case WEEKLY:
			calendar.add(Calendar.WEEK_OF_YEAR, 1);
			break;
		default:
			
			break;
		}
		return calendar.getTime();
	}
	
	//@@author A0126259B
	public String getStrFrequency(){
		String temp=null;
		switch (frequency) {
		case DAILY:
			temp = "daily";
			break;
		case MONTHLY:
			temp = "monthly";
			break;
		case YEARLY:
			temp = "yearly";
			break;
		case WEEKLY:
			temp = "weekly";
			break;
		default:
			temp="not repeating";
			break;
		}
		return temp;
	}
	
	//@@author A0126259B
	public void setNextDate(){
		try {
			dateOfEvent.setStartDate(sdf.parse(getNextDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@@author A0126259B
	@Override
	public String print() {
		String temp = taskName + EMPTY_SPACE + sdf.format(getStartDate()); 
		return temp;
	}
	
	/**
	 * This function return tasks in term of string
	 */
	//@@author A0126259B
	@Override
	public String toString(){
		String temp = "";
		temp=temp.concat(String.format(LABEL_TASK_NAME, taskName));
		temp = temp.concat("    "+ String.format(LABEL_TASK_DUEDATE, getStringStartDate()));
		return temp;
		
	}
	
	//@@author A0134109N
	@Override
	public int compareTo(TaskToDo o) {
		if(getStartDate() == null){
			return 1;
		}
		else if(o.getStartDate() == null){
			return -1;
		}
		return getStartDate().compareTo(o.getStartDate());
	}
}
