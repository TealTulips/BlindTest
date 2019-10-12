package quizretakes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Year;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPrintQuizScheduleForm {

	// Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    
	@BeforeEach
	void setUp() throws Exception {
		course = (new courseReader()).read(path + "course-swe437.xml");
		quizList = (new quizReader()).read(path + "quiz-orig-swe437.xml");
		quizRetakeList = (new retakesReader()).read(path + "quiz-retakes-swe437.xml");
		// Tell Java to use your special stream
	    System.setOut(ps);
		
	}

	/**
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		 // Put things back
	    System.out.flush();
	    System.setOut(old);
	}	
	  
	
	private static String path = "C:\\Users\\Hamza\\eclipse-workspace\\QuizRetakes\\src\\quizretakes\\";
//	private static String path = "/home/jorge/GMU/swe437/group/asst4/quizretakes/";
	//private static String path = "/Users/AqeelAT/gmu/swe437/quizretakes/";
	quizzes quizList;
	retakes quizRetakeList;
	courseBean course;
	
	
	/**
	 * Checks to ensure that a Null pointer exception is thrown in the different combinations of inputs to 
	 * printQuizSchedule
	 * 
	 * Observability: the method should be returning a null exception with the given inputs, easy for the user to observe
	 * Controllability: the method is given different combinations of null for all the input values.
	 * @throws Exception
	 */
	@Test
	void test() {
		Assertions.assertThrows(NullPointerException.class, () -> quizsched.printQuizScheduleForm(null, null, null));
		Assertions.assertThrows(NullPointerException.class, () -> quizsched.printQuizScheduleForm(null, quizRetakeList, null));
		Assertions.assertThrows(NullPointerException.class, () -> quizsched.printQuizScheduleForm(quizList, null, null));
		Assertions.assertThrows(NullPointerException.class, () -> quizsched.printQuizScheduleForm(quizList, quizRetakeList, null));
	}
	
	/* implements No1 from the Doc list
	 * Test1 is just testing that arguments have been created 
	 * and that the class returns with no errors
	 * Further tests will address each individual line in the output and make sure 
	 * its printing the expected output  
	 * considering the inputs that we are using 
	 * 
	 * Observability: This test displays the contents of the populated quiz retakes created
	 * Controllability: Multiple quizBean and retakeBean and courseBean objects are created, and 
	 * quizBean and retakBean lists and courseBean object are passed to printQuizScheduleForm to determine behavior.
	 * */
	@Test
	void test1() {
		
		//setup a quizList 
		String courseID = "swe437"; //this is just for the catch 
		
		 try { 
			   	 quizzes quizList = new quizzes(); 
		         Integer ID, month, day, hour, minute;
		         String loc = "EB 4430";
		         
		         quizBean quiz;
		         
		         //populate the quiz list
		         quiz = new quizBean (ID = 1, 			month = 1, day = 29, hour = 10, minute = 30);
		         quizList.addQuiz (quiz);
		         quiz = new quizBean (ID = 2, 			month = 2, day = 5,  hour = 10, minute = 30);
		         quizList.addQuiz (quiz);
		         quiz = new quizBean (ID = 3, 			month = 2, day = 12, hour = 10, minute = 30);
		         quizList.addQuiz (quiz);
		         quiz = new quizBean (ID = 4, 			month = 2, day = 17, hour = 10, minute = 30);
		         quizList.addQuiz (quiz);
		         quiz = new quizBean (ID = 5, 			month = 2, day = 24, hour = 10, minute = 30);
		         quizList.addQuiz (quiz);
		         quiz = new quizBean (ID = 6, 			month = 3, day = 3,  hour = 10, minute = 30);
		         quizList.addQuiz (quiz);
		         quiz = new quizBean (ID = 7, 			month = 3, day = 10, hour = 10, minute = 30);
		         quizList.addQuiz (quiz);

		      
		         //now populate the re-take list
		         retakes retakesList = new retakes(); 
		         retakeBean retake;
		         	
		         retake = new retakeBean (ID =1 , loc , month = 1, day = 30, hour = 15, minute = 30);
		         retakesList.addRetake (retake);
		         retake = new retakeBean (ID =2 , loc , month = 2, day = 5, hour = 15, minute = 30);
		         retakesList.addRetake (retake);
		         retake = new retakeBean (ID =3 , loc , month = 2, day = 12, hour = 15, minute = 30);
		         retakesList.addRetake (retake);
		         retake = new retakeBean (ID =4 , loc , month = 2, day = 19, hour = 15, minute = 30);
		         retakesList.addRetake (retake);
		         retake = new retakeBean (ID =5 , loc , month = 2, day = 26, hour = 15, minute = 30);
		         retakesList.addRetake (retake);
		         retake = new retakeBean (ID =6 , loc , month = 3, day = 5, hour = 15, minute = 30);
		         retakesList.addRetake (retake);
		         	
		         
		         //now make the courseBean
		         courseBean course;
		         		courseID = 			"swe437";
		         String courseTitle = 		"Software Testing";
		         String retakeDuration = 	"14";
		         Integer startSkipMonth = 	1;
		         Integer startSkipDay = 	21;
		         Integer endSkipMonth = 	1;
		         Integer endSkipDay = 		25;
		         String dataLocation = "/var/www/CS/webapps/offutt/WEB-INF/data/";
		         
		         int year = Year.now().getValue();
		         LocalDate startSkip = LocalDate.of(year, startSkipMonth, startSkipDay);
		         LocalDate endSkip   = LocalDate.of(year, endSkipMonth, endSkipDay);
		         course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		         
		      
		      quizsched.printQuizScheduleForm(quizList, retakesList, course);
		      assertTrue(true);
		      	
		   } catch(Exception e) {
		      System.out.println("Can't read the data files for course ID " + courseID + ". You can try again with a different courseID.");
		      fail("Method did not return");
		      return;
		   }
		 
		 
	}//end test1
	
	/*
	 * Observability: this test shows user if the class exception is thrown, and shows user if a string output is 
	 * correctly displayed by printQuizScheduleForm method or not.
	 * Controllability: an output string is manually created and tested against the output of printQuizScheduleForm. 
	 * Also, a different combinations of null values are passed to printQuizScheduleForm to ensure class exception is thrown.
	 */
	@Test
	void test2() {
        String output = String.format("%n" +
                "%n" +
                "******************************************************************************%n" +
                "GMU quiz retake scheduler for class Software testing%n" +
                "******************************************************************************%n" +
                "%n" +
                "%n" +
                "You can sign up for quiz retakes within the next two weeks. %n" +
                "Enter your name (as it appears on the class roster), %n" +
                "then select which date, time, and quiz you wish to retake from the following list.%n" +
                "%n" +
                "Today is TUESDAY, FEBRUARY 5%n" +
                "Currently scheduling quizzes for the next two weeks, until TUESDAY, FEBRUARY 19%n");


		// case 1
		Assertions.assertThrows(Exception.class, () -> quizsched.printQuizScheduleForm(quizList, null , course));
		assertTrue(baos.toString().equals(output));
		baos.reset();

		// case 2
		Assertions.assertThrows(Exception.class, () -> quizsched.printQuizScheduleForm(null, null, course));
		assertTrue(baos.toString().equals(output));
		baos.reset();

        // case 3
        output += String.format("RETAKE: TUESDAY, FEBRUARY 5, at 16:00 in ???%n");
        Assertions.assertThrows(Exception.class, () -> quizsched.printQuizScheduleForm(null, quizRetakeList, course));
        assertTrue(baos.toString().equals(output));
        baos.reset();

	}//end test2



	/*
	 * Observability: this test case will show the user if the observed string output matches the expected output.
	 * Controllability: this test case creates lists of quizBean and retakeBean objects and a courseBean object, and 
	 * passes them to printQuizScheduleForm to obtain output and compare to expected output.
	 */
	@Test
	void test3() {
		//checks that first print statement printed correctly
		String courseID = "swe437"; //this is just for the catch 
		quizzes quizList = new quizzes(); 
        Integer ID, month, day, hour, minute;
        String loc = "EB 4430";
        
        quizBean quiz;
        
        //populate the quiz list
        quiz = new quizBean (ID = 1, 			month = 1, day = 29, hour = 10, minute = 30);
        quizList.addQuiz (quiz);
        quiz = new quizBean (ID = 2, 			month = 2, day = 5,  hour = 10, minute = 30);
        quizList.addQuiz (quiz);
        quiz = new quizBean (ID = 3, 			month = 2, day = 12, hour = 10, minute = 30);
        quizList.addQuiz (quiz);
        quiz = new quizBean (ID = 4, 			month = 2, day = 17, hour = 10, minute = 30);
        quizList.addQuiz (quiz);
        quiz = new quizBean (ID = 5, 			month = 2, day = 24, hour = 10, minute = 30);
        quizList.addQuiz (quiz);
        quiz = new quizBean (ID = 6, 			month = 3, day = 3,  hour = 10, minute = 30);
        quizList.addQuiz (quiz);
        quiz = new quizBean (ID = 7, 			month = 3, day = 10, hour = 10, minute = 30);
        quizList.addQuiz (quiz);

     
        //now populate the re-take list
        retakes retakesList = new retakes(); 
        retakeBean retake;
        	
        retake = new retakeBean (ID =1 , loc , month = 1, day = 30, hour = 16, minute = 30);
        retakesList.addRetake (retake);
        retake = new retakeBean (ID =2 , loc , month = 2, day = 5, hour = 16, minute = 30);
        retakesList.addRetake (retake);
        retake = new retakeBean (ID =3 , loc , month = 2, day = 12, hour = 16, minute = 30);
        retakesList.addRetake (retake);
        retake = new retakeBean (ID =4 , loc , month = 2, day = 19, hour = 16, minute = 30);
        retakesList.addRetake (retake);
        retake = new retakeBean (ID =5 , loc , month = 2, day = 26, hour = 16, minute = 30);
        retakesList.addRetake (retake);
        retake = new retakeBean (ID =6 , loc , month = 3, day = 5, hour = 16, minute = 30);
        retakesList.addRetake (retake);
        	
        
        //now make the courseBean
        courseBean course;
        		courseID = 			"swe437";
        String courseTitle = 		"Software Testing";
        String retakeDuration = 	"14";
        Integer startSkipMonth = 	1;
        Integer startSkipDay = 	21;
        Integer endSkipMonth = 	1;
        Integer endSkipDay = 		25;
        String dataLocation = "/var/www/CS/webapps/offutt/WEB-INF/data/";
        
        int year = Year.now().getValue();
        LocalDate startSkip = LocalDate.of(year, startSkipMonth, startSkipDay);
        LocalDate endSkip   = LocalDate.of(year, endSkipMonth, endSkipDay);
        course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
        
    	          
        quizsched.printQuizScheduleForm(quizList, retakesList, course);
        
        //this is the line being tested
		String regx = "GMU quiz retake scheduler for class Software Test";
		Pattern pattern = Pattern.compile(regx);		
		Matcher matcher = pattern.matcher(baos.toString());
		
		//show what happened
		//System.out.flush();
	    //System.setOut(old);
		
		boolean found = false;
		while(matcher.find()) {
			
			System.out.printf(	"I found the text: %s "+
								"starting at index: %d "+
								"and ending at index: %d.\n",
					               matcher.group(0),
					               matcher.start(),         
					               matcher.end());
			found = true;
		}
		if(found){
			
			assertTrue(true);
			
			
		}else {
			System.out.println("No match found.");
			
			fail("no match for: "+regx);
			
		}
				
	}//end test3
	
	/**
	 * Observability: this test case will show the output of printQuizScheduleForm after it outputs the retakes available
	 * for a given quiz. The output of the method will be compared to the expected output.
	 * Controllability: the printQuizScheduleForm is given a list of poulated quizBean and a list of retakeBean and
	 * a courseBean object to observe the printed statements for the retakes. 
	 */
	@Test
	void test4() {
		//checks that following print statements print statement printed correctly
		/*this test corresponds to No 4 on the list in our Doc * 
		 * 
		 */
		String courseID = "swe437"; //this is just for the catch 
		quizzes quizList = new quizzes(); 
	    Integer ID, month, day, hour, minute;
	    String loc = "EB 4430";
	    
	    quizBean quiz;
	    
	    //populate the quiz list
	    quiz = new quizBean (ID = 1, 			month = 1, day = 29, hour = 10, minute = 30);
	    quizList.addQuiz (quiz);
	    quiz = new quizBean (ID = 2, 			month = 2, day = 5,  hour = 10, minute = 30);
	    quizList.addQuiz (quiz);
	    quiz = new quizBean (ID = 3, 			month = 2, day = 12, hour = 10, minute = 30);
	    quizList.addQuiz (quiz);
	    quiz = new quizBean (ID = 4, 			month = 2, day = 19, hour = 10, minute = 30);
	    quizList.addQuiz (quiz);
	    quiz = new quizBean (ID = 5, 			month = 2, day = 24, hour = 10, minute = 30);
	    quizList.addQuiz (quiz);
	    quiz = new quizBean (ID = 6, 			month = 3, day = 3,  hour = 10, minute = 30);
	    quizList.addQuiz (quiz);
	    quiz = new quizBean (ID = 7, 			month = 3, day = 10, hour = 10, minute = 30);
	    quizList.addQuiz (quiz);
	
	 
	    //now populate the re-take list
	    retakes retakesList = new retakes(); 
	    retakeBean retake;
	    	
	    retake = new retakeBean (ID =1 , loc , month = 1, day = 30, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =2 , loc , month = 2, day = 5, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =3 , loc , month = 2, day = 12, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =4 , loc , month = 2, day = 19, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =5 , loc , month = 2, day = 26, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =6 , loc , month = 3, day = 5, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =7 , loc , month = 3, day = 12, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =8 , loc , month = 3, day = 19, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    retake = new retakeBean (ID =9 , loc , month = 3, day = 26, hour = 16, minute = 30);
	    retakesList.addRetake (retake);
	    	
	    
	    //now make the courseBean
	    courseBean course;
	    		courseID = 			"swe437";
	    String courseTitle = 		"Software Testing";
	    String retakeDuration = 	"14";
	    Integer startSkipMonth = 	1;
	    Integer startSkipDay = 	21;
	    Integer endSkipMonth = 	1;
	    Integer endSkipDay = 		25;
	    String dataLocation = "/var/www/CS/webapps/offutt/WEB-INF/data/";
	    
	    int year = Year.now().getValue();
	    LocalDate startSkip = LocalDate.of(year, startSkipMonth, startSkipDay);
	    LocalDate endSkip   = LocalDate.of(year, endSkipMonth, endSkipDay);
	    course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
	    
		          
	    quizsched.printQuizScheduleForm(quizList, retakesList, course);
	    
	    //this is the lines being tested
		String regx =   "You can sign up for quiz retakes within the next two weeks\\. \\s{0,2}"+//+"\n"+
						"Enter your name \\(as it appears on the class roster\\), \\s{0,2}"+
						"then select which date, time, and quiz you wish to retake from the following list\\.\\s{0,2}"+
						"\\s{0,2}"+
						"Today is TUESDAY, FEBRUARY 5\\s{0,2}"+ 
						"Currently scheduling quizzes for the next two weeks, until TUESDAY, FEBRUARY 19\\s{0,2}" + 
						"RETAKE: TUESDAY, FEBRUARY 5, at 16:30 in EB 4430" + 
						"\\s{0,2}"+ 
						"    1\\) Quiz 1 from TUESDAY, JANUARY 29\\s{0,2}" + 
						"    2\\) Quiz 2 from TUESDAY, FEBRUARY 5\\s{0,2}" + 
						"RETAKE: TUESDAY, FEBRUARY 12, at 16:30 in EB 4430\\s{0,2}" + 
						"    3\\) Quiz 1 from TUESDAY, JANUARY 29\\s{0,2}" + 
						"    4\\) Quiz 2 from TUESDAY, FEBRUARY 5\\s{0,2}" + 
						"    5\\) Quiz 3 from TUESDAY, FEBRUARY 12\\s{0,2}"+ 
						"RETAKE: TUESDAY, FEBRUARY 19, at 16:30 in EB 4430\\s{0,2}"+ 
						"    6\\) Quiz 2 from TUESDAY, FEBRUARY 5\\s{0,2}" + 
						"    7\\) Quiz 3 from TUESDAY, FEBRUARY 12\\s{0,2}"+    
						"    8\\) Quiz 4 from TUESDAY, FEBRUARY 19\\s{0,2}";    
		Pattern pattern = Pattern.compile(regx);		
		Matcher matcher = pattern.matcher(baos.toString());
		
		//show what happened
		System.out.flush();
	    System.setOut(old);
		
		boolean found = false;
		while(matcher.find()) {
			
			System.out.printf(	"I found the text: %s "+
								"starting at index: %d "+
								"and ending at index: %d.\n",
					               matcher.group(0),
					               matcher.start(),         
					               matcher.end());
			found = true;
		}
		if(found){
			
			assertTrue(true);
			
			
		}else {
			System.out.println("No match found.");
			
			fail("no match for: "+regx);
			
		}
				
	}//end test4

}





