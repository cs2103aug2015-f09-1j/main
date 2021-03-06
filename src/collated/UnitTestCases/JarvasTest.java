package UnitTestCases;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.jarvas.Logic;


//@@author A0126159A
public class JarvasTest {

	GUI.Jarvas jarvas = new GUI.Jarvas();
	Logic logic = new Logic();

	@Test
	public void testExecute() throws IOException{
		//equivalence partitioning for invalid value partition(without command)
		assertEquals("invalid input", logic.execute("444"));
		assertEquals("invalid input", logic.execute("eeeee"));
		//equivalence partitioning for valid value partition (addtask command follow by dueDate)
		assertEquals("task \"ola\" successfully added", logic.execute("add ola -due tommorrow"));
		//Test short form
		assertEquals("task \"yolo\" successfully added", logic.execute("a yolo"));
		assertEquals("\"task 1\" successfully edited", logic.execute("e task 1 due now"));
		assertEquals("\"task 1\" successfully deleted", logic.execute("d task 1"));

	}
	

	//@@author A0145381H
	
	@Test
	public void testAddEvent(){
		// equivalence partitioning with valid date format mm/dd/yyyy HH/MM
		assertEquals("event \"ola\" successfully added", logic.execute("a ola -from 12/12/2991 12:12 -to 12/12/2991 12:13"));
		//equivalence partitioning with invalid date format
		assertEquals("Event date error", logic.execute("a ola -from 12/12/2991 12 -to 12/12/1291 12:13"));
	}

	@Test
	public void testClear() throws IOException{
		//assertEquals("tasks is clear", logic.clearTask());
	}

	@Test
	public void testSort() throws IOException{
		
	}
	

	@Test
	public void testSearch() throws IOException{
	}

	@Before
	public void beforeRunTest(){
	//	logic.clearTask();
	}

	@After
	public void afterRunTest(){
	//	logic.clearTask();
	}
	
}
