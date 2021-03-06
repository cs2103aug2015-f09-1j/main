
//@@author A0145381H
package GUI;
import java.util.*;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.jarvas.JParser;
import main.jarvas.Logic;
import main.jarvas.TaskEvent;
import main.jarvas.TaskToDo;
import javafx.scene.control.*;
import javafx.scene.layout.*;
/**
 *
 * 
 */

public class Jarvas extends Application{
	
	
	private static final String TASK_NAME = ". Task name: ";
	private static final String TASK_DUE = "    Due Date: ";
	private static final String EVENT_NAME = ". Event name: ";
	private static final String EVENT_START = "    Start Date: ";
	private static final String EVENT_END = "    End Date: ";
	private static final String DONE = "Y";
	private static final String UNDONE = "N";
	private static final String OVERDUE = "O";
	private static final char SEARCH = 'S';
	private static final char CDONE = 'Y';
	private static final char CUNDONE = 'N';
	private static final char COVERDUE = 'O';
	private static final String S_DONE = "SY";
	private static final String S_UNDONE = "SN";
	private static final String SEARCH_RESULT = "Searching Result:";
	private static final String SEARCH_END = "YEnd";
	private static final String EVENTS = "Events";
	private static final String TASKS = "Tasks";
	private static final String TASK_FOR_SEARCH = "STasks";
	private static final String EVENT_FOR_SEARCH = "SEvents";
	private static final String FONT_AVENIR = "Avenir";
	private static final String FONT_COURIER = "Courier";
	private static final String JARVAS = "Jarvas";
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	private static final String CST = "CST ";
	private static final String _DONE = "(DONE)";
	private static final String _OVERDUE = "(OVERDUE)";
	private static final String TODAY = "today";

	private ObservableList<String> alltasks;
	private ListView<String> allTasks;
	private Text log;
	private TextField input;
	private Text space1;
	private Text space2;
	private Text space3;
	private VBox pane;
	private VBox vbox;
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	
	//@@author A0145381H
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.out.println(JParser.dateConverter("TODAY"));
		Logic logic = new Logic();
        Vector<TaskToDo> tasks = logic.returnNewTasks();
        alltasks = FXCollections.observableArrayList();
        alltasks.add(TASKS);
        addTasks(tasks);
        Vector<TaskEvent> events = logic.returnNewEvents();
        alltasks.add(EVENTS);
        addEvents(events);
    	alltasks.add(UNDONE);
        allTasks = new ListView<>(alltasks);
        log = new Text();
        input = new TextField();
        space1 = new Text();
        space2 = new Text();
        space3 = new Text();
        log.setFont(new Font(0));
        space1.setFont(new Font(10));
        space2.setFont(new Font(10));
        space3.setFont(new Font(10));
        inputHandler newCommand = new inputHandler();
        input.setOnAction(newCommand);
        pane = new VBox();
		vbox = new VBox();
        VBox.setVgrow(allTasks, Priority.ALWAYS);
		pane.getChildren().add(space3);
		pane.getChildren().add(input);
		pane.getChildren().add(space1);
		pane.getChildren().add(log);
		pane.getChildren().add(space2);
		pane.getChildren().add(vbox);
		vbox.getChildren().add(allTasks);
		allTasks.setCellFactory(new Callback<ListView<String>, 
	            ListCell<String>>() {
	                @Override 
	                public ListCell<String> call(ListView<String> list) {
	                    return new ColorTaskCell();
	                }
	            }
	        );
        Scene scene = new Scene(pane, 600, 500,Color.rgb(238,236,218));
        primaryStage.setTitle(JARVAS);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

    //@@author A0145381H
	private void addTasks(Vector<TaskToDo> tasks) {
		for(int i=0; i<tasks.size();i++) {
        	if(tasks.get(i).getDone()){
            	if(tasks.get(i).getStartDate() == null){
                	alltasks.add(DONE + (i+1) + TASK_NAME + tasks.get(i).getName() + _DONE);
            		alltasks.add(DONE + TASK_DUE + tasks.get(i).getStartDate());
            	}
            	else{
            		if(JParser.dateChecker(tasks.get(i).getStartDate().toString(), TODAY)){
                    	alltasks.add(DONE + (i+1) + TASK_NAME + tasks.get(i).getName() + _DONE + _OVERDUE);
            		}
            		else{
                    	alltasks.add(DONE + (i+1) + TASK_NAME + tasks.get(i).getName() + _DONE);
            		}
                	String task = DONE + TASK_DUE + tasks.get(i).getStartDate().toString().replace(CST, EMPTY);
            		alltasks.add(task.substring(0, task.length() - 8) + task.substring(task.length() - 5));
            	}
        	}
        	else{
            	if(tasks.get(i).getStartDate() == null){
                	alltasks.add(UNDONE + (i+1) + TASK_NAME + tasks.get(i).getName());
            		alltasks.add(UNDONE + TASK_DUE + tasks.get(i).getStartDate());
            	}
            	else if(JParser.dateChecker(tasks.get(i).getStartDate().toString(), TODAY)){
            		System.out.println(i);
                	alltasks.add(OVERDUE + UNDONE + (i+1) + TASK_NAME + tasks.get(i).getName() + _OVERDUE);
                	String task = OVERDUE + UNDONE + TASK_DUE + tasks.get(i).getStartDate().toString().replace(CST, EMPTY);
            		alltasks.add(task.substring(0, task.length() - 8) + task.substring(task.length() - 5));
            	}
            	else{
                	alltasks.add(UNDONE + (i+1) + TASK_NAME + tasks.get(i).getName());
                	String task = UNDONE + TASK_DUE + tasks.get(i).getStartDate().toString().replace(CST, EMPTY);
            		alltasks.add(task.substring(0, task.length() - 8) + task.substring(task.length() - 5));
            	}
        	}
        }
	}

    //@@author A0145381H
	private void addEvents(Vector<TaskEvent> events) {
		String event;
		for(int i=0; events != null && i<events.size();i++) {
        	if(events.get(i).getDone()){
        		if(JParser.dateChecker(events.get(i).getEndDate().toString(), TODAY)){
        			alltasks.add(DONE + (i+1) + EVENT_NAME + events.get(i).getName() + _DONE + _OVERDUE);
        		}
        		else{
        			alltasks.add(DONE + (i+1) + EVENT_NAME + events.get(i).getName() + _DONE);
        		}
            	event = DONE + EVENT_START + events.get(i).getStartDate().toString().replace(CST, EMPTY);
        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
        		event = DONE + EVENT_END + events.get(i).getEndDate().toString().replace(CST, EMPTY);
        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
        	}
        	else if(JParser.dateChecker(events.get(i).getEndDate().toString(), TODAY)){
            	alltasks.add(OVERDUE + UNDONE + (i+1) + EVENT_NAME + events.get(i).getName() + _OVERDUE);
            	event = OVERDUE + UNDONE + EVENT_START + events.get(i).getStartDate().toString().replace(CST, EMPTY);
        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
        		event = OVERDUE + UNDONE + EVENT_END + events.get(i).getEndDate().toString().replace(CST, EMPTY);
        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
        	}
        	else{
            	alltasks.add(UNDONE + (i+1) + EVENT_NAME + events.get(i).getName());
            	event = UNDONE + EVENT_START + events.get(i).getStartDate().toString().replace(CST, EMPTY);
        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
            	event = UNDONE + EVENT_END + events.get(i).getEndDate().toString().replace(CST, EMPTY);
        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
        	}
        }
	}
	
	//@@author A0145381H
	static class ColorTaskCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if(item == null){
            	return;
            }
            switch(item){
            case SEARCH_RESULT:
            	setTextFill(Color.rgb(253, 50, 50));
            	setFont(new Font(FONT_AVENIR, 23));
            	setText(item);
            	break;
            case SEARCH_END:
            	setTextFill(Color.rgb(253, 120, 120));
            	setFont(new Font(FONT_AVENIR, 23));
            	setText(item.substring(1));
            	break;
            case TASK_FOR_SEARCH:
            case EVENT_FOR_SEARCH:
            	setTextFill(Color.rgb(255, 120, 120));
            	setFont(new Font(FONT_COURIER, 20));
            	setText(item.substring(1));
            	break;
            case TASKS:
            case EVENTS:
            	setTextFill(Color.rgb(71, 184, 251));
            	setFont(new Font(FONT_COURIER, 20));
            	setText(item);
            	break;
            default:
            	if(item.charAt(0) == CDONE){
                	setTextFill(Color.DARKGRAY);
                	setFont(new Font(FONT_COURIER, 14));
                	setText(item.substring(1));
            	}
            	else if(item.charAt(0) == CUNDONE){
                	setTextFill(Color.CORNFLOWERBLUE);
                	setFont(new Font(FONT_COURIER, 14));
                	setText(item.substring(1));
            	}
            	else if(item.charAt(0) == SEARCH){
                	if(item.charAt(1) == CDONE){
                    	setTextFill(Color.rgb(255, 180, 80));
                    	setFont(new Font(FONT_COURIER, 14));
                    	setText(item.substring(2));
                	}
                	else if(item.charAt(1) == CUNDONE){
                    	setTextFill(Color.rgb(255, 80, 180));
                    	setFont(new Font(FONT_COURIER, 14));
                    	setText(item.substring(2));
                	}
            	}
            	else if(item.charAt(0) == COVERDUE){
            		if(item.charAt(1) == SEARCH){
                    	setTextFill(Color.RED);
                    	setFont(new Font(FONT_COURIER, 14));
                    	setText(item.substring(3));
            		}
            		else{
                    	setTextFill(Color.RED);
                    	setFont(new Font(FONT_COURIER, 14));
                    	setText(item.substring(2));
            		}
            	}
            }
        }
    }

    //@@author A0145381H 
    class inputHandler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent ae){
        	Logic logic = new Logic();
            String Input = input.getText();
            String outcome = logic.execute(Input);
            log.setText(SPACE + outcome);
            log.setFont(Font.font(FONT_COURIER, 12));
            Vector<TaskToDo> tasks = logic.returnNewTasks();
            Vector<TaskEvent> events = logic.returnNewEvents();
            alltasks = FXCollections.observableArrayList();
            if(logic.getIsCommandSearch()){
                Vector<TaskToDo> tasksForSearch = logic.getTasksForSearch();
                Vector<TaskEvent> eventsForSearch = logic.getEventsForSearch();
                
            	alltasks.add(SEARCH_RESULT);

                alltasks.add(TASK_FOR_SEARCH);
                addTasksForSearch(tasksForSearch);
                alltasks.add(EVENT_FOR_SEARCH);
                addEventsForSearch(eventsForSearch);
            	alltasks.add(DONE);
            	
            }
            alltasks.add(TASKS);
            addTasks(tasks);
            alltasks.add(EVENTS);
            addEvents(events);
        	alltasks.add(UNDONE);
            input.setText(EMPTY);
            allTasks = new ListView<>(alltasks);
            vbox.getChildren().clear();
            vbox.getChildren().add(allTasks);
    		allTasks.setCellFactory(new Callback<ListView<String>, 
    	            ListCell<String>>() {
    	                @Override 
    	                public ListCell<String> call(ListView<String> list) {
    	                    return new ColorTaskCell();
    	                }
    	            });
        }

        //@@author A0145381H
		private void addEventsForSearch(Vector<TaskEvent> eventsForSearch) {
			String event;
			for(int i=0; eventsForSearch != null && i<eventsForSearch.size();i++) {
				if(eventsForSearch.get(i).getDone()){
					if(JParser.dateChecker(eventsForSearch.get(i).getEndDate().toString(), TODAY)){
				    	alltasks.add(S_DONE + (i+1) + EVENT_NAME + eventsForSearch.get(i).getName() + _DONE + _OVERDUE);
					}
					else{
				    	alltasks.add(S_DONE + (i+1) + EVENT_NAME + eventsForSearch.get(i).getName() + _DONE);
					}
			    	event = S_DONE + EVENT_START + eventsForSearch.get(i).getStartDate().toString().replace(CST, EMPTY);
	        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
			    	event = S_DONE + EVENT_END + eventsForSearch.get(i).getEndDate().toString().replace(CST, EMPTY);
	        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
				}
				else if(JParser.dateChecker(eventsForSearch.get(i).getEndDate().toString(), TODAY)){
			    	alltasks.add(OVERDUE + S_UNDONE + (i+1) + EVENT_NAME + eventsForSearch.get(i).getName() + _OVERDUE);
			    	event = OVERDUE + S_UNDONE + EVENT_START + eventsForSearch.get(i).getStartDate().toString().replace(CST, EMPTY);
	        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
			    	event = OVERDUE + S_UNDONE + EVENT_END + eventsForSearch.get(i).getEndDate().toString().replace(CST, EMPTY);
	        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
				}
				else{
			    	alltasks.add(S_UNDONE + (i+1) + EVENT_NAME + eventsForSearch.get(i).getName());
			    	event = S_UNDONE + EVENT_START + eventsForSearch.get(i).getStartDate().toString().replace(CST, EMPTY);
	        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
			    	event = S_UNDONE + EVENT_END + eventsForSearch.get(i).getEndDate().toString().replace(CST, EMPTY);
	        		alltasks.add(event.substring(0, event.length() - 8) + event.substring(event.length() - 5));
				}
			}
		}

	    //@@author A0145381H
		private void addTasksForSearch(Vector<TaskToDo> tasksForSearch) {
			for(int i=0; i<tasksForSearch.size();i++) {
				if(tasksForSearch.get(i).getDone()){
			    	if(tasksForSearch.get(i).getStartDate() == null){
				    	alltasks.add(S_DONE + (i+1) + TASK_NAME + tasksForSearch.get(i).getName() + _DONE);
			    		alltasks.add(S_DONE + TASK_DUE + tasksForSearch.get(i).getStartDate());
			    	}
			    	else if(JParser.dateChecker(tasksForSearch.get(i).getStartDate().toString(), TODAY)){
				    	alltasks.add(S_DONE + (i+1) + TASK_NAME + tasksForSearch.get(i).getName() + _DONE + _OVERDUE);
			        	alltasks.add(S_DONE + TASK_DUE + tasksForSearch.get(i).getStartDate().toString().replace(CST, EMPTY));
			    	}
			    	else{
				    	alltasks.add(S_DONE + (i+1) + TASK_NAME + tasksForSearch.get(i).getName() + _DONE);
			        	alltasks.add(S_DONE + TASK_DUE + tasksForSearch.get(i).getStartDate().toString().replace(CST, EMPTY));
			    	}
				}
				else{
			    	if(tasksForSearch.get(i).getStartDate() == null){
				    	alltasks.add(S_UNDONE + (i+1) + TASK_NAME + tasksForSearch.get(i).getName());
			    		alltasks.add(S_UNDONE + TASK_DUE + tasksForSearch.get(i).getStartDate());
			    	}
	            	else if(JParser.dateChecker(tasksForSearch.get(i).getStartDate().toString(), TODAY)){
	                	alltasks.add(OVERDUE + S_UNDONE + (i+1) + TASK_NAME + tasksForSearch.get(i).getName() + _OVERDUE);
	                	String task = OVERDUE + S_UNDONE + TASK_DUE + tasksForSearch.get(i).getStartDate().toString().replace(CST, EMPTY);
	            		alltasks.add(task.substring(0, task.length() - 8) + task.substring(task.length() - 5));
	            	}
			    	else{
				    	alltasks.add(S_UNDONE + (i+1) + TASK_NAME + tasksForSearch.get(i).getName());
			        	String task = S_UNDONE + TASK_DUE + tasksForSearch.get(i).getStartDate().toString().replace(CST, EMPTY);
	            		alltasks.add(task.substring(0, task.length() - 8) + task.substring(task.length() - 5));
			    	}
				}
			}
		}
    }
	
    //@@author A0145381H
	public static void main(String[] args) {
        launch(args);
    }
	
}
