/**
* Goes through the command line arguments and executes the commands
* If fewer than two arguments are provided, the program will print an error message:
"Invalid declareMajor command. Major not provided." and stop running.
* If the major is not a valid department, the program will print an error message: "Invalid
major: " followed by whatever was provided for the major, and stop running.
* If fewer than three arguments follow an addGrade command, the program will print an error
message: "Invalid addGrade command. Department, grade, and/or credits not provided."
* Other error conditions are handled by the methods that are called from main.
*
* If all the arguments are valid, the program will execute all the commands and print the
student's transcript after each change, i.e. after each grade is added or a new semester is
started.
* @param args
*/
public class AcademicTranscripts {

	public static void main(String[] args) {
		if (args.length < 2){
			System.out.println ("Invalid declareMajor command. Major not provided.");
			return;
		}
		if (args[0].equals("declareMajor")){
		
			String maj = args[1];

			declareMajor (maj);

			string major = getMajor (maj);
			
			String department;
			
			for (int i = 1; i < args.length; i += 2){
			department = args[i];
			isValidDepartment (department); //Check for Valid Department.
			
			} 

			//Variables
			String addgrade;
			String grade;
			String credits;
		

		for (int i = 2; i < args.length; i++){
			addgrade = args[4*i-6];
			department = args[4*i-5];
			grade = args[4*i-4];
			credits = args [4*i-3];

			//AddGrade
			addGrade (department, grade, credits);

			//Credits and Total Credits Calculation
			int credits1 = Integer.parseInt (credits); 
			
			int total_credits = getTotalCredits (credits1);
			int semester_credits = getCurrentSemesterCredits (credits1);
			 

			//GPA Calculation
			double Grade = getGradePoints (grade);
			double Gpa = getGpa (Grade, credits1); 
			


			

			//Major GPA and Credits
			while (department.equals(maj)){
				addGradeInMajor (grade, credits1);
				gpa_major = getMajorGPA (Grade, credits1);
				major_credits = getMajorCredits (credits1);
			}
			
			//Semester Credits and GPA
			while (total_credits <= 18) {
				
			int semester_credits = getCurrentSemesterCredits (total_credits);
			double gpa_semester = getCurrentSemesterGPA (Gpa);
			return semester_credits; 
			return gpa_semester;
			
			}
			

			printTranscript(total_credits, Gpa, semester_credits, gpa_semester, maj, major_credits, gpa_major); //print

			if (args[i].equals("startNewSemester")){
				new_semester_credits = 0;
				new_gpa_semester = 0;
				
				startNewSemester (new_semester_credits, new_gpa_semester);
			}




		}
		
			
			

	

			
			

		}
		else {
				return;
			}		
	}	


		

/**
* Resets the current semester credits and semester GPA to 0 and prints out the transcript.
* @see #printTranscript()
*/
	public static void startNewSemester(int semester_credits, double gpa_semester) {
		double Gpa = new_gpa_semester;
		int credits1 = new_semester_credits;
		getCurrentSemesterGPA (Gpa);
		getCurrentSemesterCredits (credits1);
	}
/**
* If the major is not a valid department, the program will print an error message: "Invalid
major: " followed by whatever was provided for the major.
* If the major is valid, the program will set the major to the provided major.
* For more information on what defines a valid department, see the isValidDepartment method.
* @param maj the major to declare
* @see #isValidDepartment(String)
*/
	public static void declareMajor(String maj) {  //Dooneeeee
		
		if (!isValidDepartment (maj)){
			System.out.println ("Invalid major: " + maj);
		}
		else {
			getMajor (maj);
		}
		
	}
/**	
* Checks if the department is a valid department.
* A valid department is a non-null, non-empty string of length 3.
* @param department the department to check
* @return true if the department is a valid department, false otherwise
*/
	public static boolean isValidDepartment(String department){ //Doneeeeeee

	    if (department.length() == 3){
	    	return true; 
	    }
	    else {
	    	return false;
	    }
	    
	}


/**
* Checks if the grade, department, and credits are valid.
* If the department is not a valid department, it must print an error message: "Invalid
department: " followed by whatever was provided for the department.
*
* If the grade is not a valid grade, it must print an error message: "Invalid grade: "
followed by whatever was provided for the grade.
* The valid grades are: A, A-, B+, B, B-, C+, C, C-, D+, D, D-, F.
*
* If the credits are not a number between 1 and 4, the program will print an error message:
"Invalid credits: " followed by whatever was provided for the credits.
* If all the arguments are valid, it returns true. Otherwise, it returns false.
* @param department the department to check
* @param grade the grade to check
* @param credits the credits to check
* @return true if the department, grade, and credits are valid, false otherwise
* */

	public static boolean isAddGradeValid(String department, String grade, String credits) { //Dooonneee

		if (!isValidDepartment (department)) {
		System.out.println ("Invalid department: " + department);
		return false;
		}

		if (grade.equals("A")  || 
			grade.equals("B+") || 
			grade.equals("B")  || 
			grade.equals("B-") || 
			grade.equals("C+") ||
			grade.equals("C")  ||
			grade.equals("C-") ||
			grade.equals("D+") ||
			grade.equals("D")  ||
			grade.equals("D-") ||
			grade.equals("F")) {
		
		} else {
		System.out.println ("Invalid grade: " + grade);
		return false;
		}
		 if (credits.equals("1") || credits.equals("2") || credits.equals("3") || credits.equals("4")) {
		return true;
		}
		else {
		System.out.println ("Invalid credits: " + credits);
		return false;
		}
	}
/**	
* Adds the grade to the student's transcript.
* If the major is not declared, the program will print an error message: "Cannot add grade.
Major is not declared."
* The three parameters should be checked for validity using the isAddGradeValid method. This
method should imediately return if the parameters are not valid.
* If the number of credits would exceed the maximum allowed per semester, the program will
print an error message: "Cannot add grade. Maximum credits allowed per semester is 18."
* It must update the total credits, current semester credits, overall GPA, and current
semester GPA.
* If the department is the major, it must also update the major GPA and major credits.
* It must print out a message: "Grade added: " followed by the department, grade, and
credits.
* It must then print out the transcript.
* @see #isAddGradeValid(String, String, String)
* @see #addGradeInMajor(String, int)
* @see #printTranscript()
* @param department the department of the grade
* @param grade the grade
* @param credits the number of credits8
*/
	public static void addGrade(String department, String grade, String credits) {

		if ( maj.equals(department) && !declareMajor (maj)){
			System.out.println ("Cannot add grade.Major is not declared.");
		}
	    
	    int ditis = 0;
	    int cre = Integer.parseInt (credits);

	    for (int i = 0; i <= 18; i++){
	    	ditis += cre;
	    	if (ditis == 19){
	    	break;
	    }
	    System.out.println ("Cannot add grade. Maximum credits allowed per semester is 18.");
	    }


	    if (total_credits > 18){
			
		}
		if (isAddGradeValid (department, grade, credits)) {
			System.out.println (" Grade added: " + department + "" + grade + "" + credits);
		} 
		else{
			return;
		}

	}
/**
* Updates the major GPA and major credits.
* @param grade the grade to add
* @param credits the number of credits to add
*/
	public static void addGradeInMajor(String grade, int credits){

		   double sum = 0;
		   double total_credits = 0;
		   double gpa_major;
		   double mult;
		   double gradegpa =  getGradePoints(grade);
		    
		   total_credits += credits1;
		
		   getMajorCredits (total_credits);
		    
		   mult = (double)gradegpa*credits1;
		   sum += mult;

		   gpa_major = sum/total_credits;

			if (gpa_major == gradegpa){
				getMajor (gradegpa);
			}else  {
				getMajor (gpa_major);
			}
			


	}
/**
* Prints out the student's transcript.
* The transcript should include the following information, each on a separate line:
* "==========" (10 equal signs, without the quotes)
* "Total Credits: " followed by the total number of credits the student has taken
* "Overall GPA: " followed by the overall GPA, rounded to two decimal places
* "Current Semester Credits: " followed by the number of credits the student took in the
current semester
* "Current Semester GPA: " followed by the student's GPA in the classes he took in the
current semester, rounded to two decimal places
* "Major: " followed by the student's declared major
* "Major Credits: " followed by the number of credits the student has taken in their major
* "Major GPA: " followed by the student's major GPA, rounded to two decimal places
* "==========" (10 equal signs, without the quotes)
*/
	public static void printTranscript(int total_credits, double Gpa, int semester_credits, double gpa_semester, string major, int major_credits, double gpa_major){
		System.out.println ("==========");
		System.out.println ("Total Credits: " + total_credits);
		System.out.println ("Overall GPA:  " + Gpa);
		System.out.println ("Current Semester Credits: " + semester_credits);
		System.out.println ("Current Semester GPA: " + gpa_semester);
		System.out.println ("Major: " + major);
		System.out.println ("Major Credits: " + major_credits);
		System.out.println ("Major GPA: " +gpa_major);
		System.out.println ("==========");
	}
/**
* @return the total number of credits the student has taken
*/
	public static int getTotalCredits(int credits1) {
		int total_credits = 0;
		int semester_credits = 0; 
		total_credits += credits1;
		return total_credits;
	}
/**
* @return the number of credits the student took in the current semester
*/
	public static int getCurrentSemesterCredits(int credits1) {
		int semester_credits = 0; 
		semester_credits += credits1;
		if (semester_credits <= 18){
			return semester_credits;
		}
		else {
			return 0;
		}
		
		
	}
/**
* @return the number of credits the student has taken in their major
*/
	public static int getMajorCredits(int credits1) {
		int total_credits = 0;
		total_credits += credits1;
		return total_credits;

	}
/**
* @return the student's GPA in the current semester
*/
	public static double getCurrentSemesterGPA(double Gpa) {
		return Gpa;
	}
/**
* @return the student's major GPA, i.e. their GPA for all courses taken in their major
*/
	public static double getMajorGPA(double Grade, int credits1) {
		double gpa;
		double mult = 0;
		double gpa_credits = 0;
		 mult += (double)Grade*credits1;
		
		gpa_credits += (double)credits1;

		gpa = mult/gpa_credits; 

		return gpa;

		   return gpa;
		    }
			
	   
	
/**
* @return the student's overall GPA
*/
	public static double getGpa(double Grade, int credits1 ) {
		double gpa;
		double mult = 0;
		double gpa_credits = 0;
		 mult += (double)Grade*credits1;
		
		gpa_credits += (double)credits1;

		gpa = mult/gpa_credits; 

		return gpa;
	}
/**
* @return the student's declared major
*/
		public static String getMajor(string maj) {
			return maj;
	}
		private static double getGradePoints(string grade){
		switch(grade){
		case "A":
		return 4.0;
		case "A-":
		return 3.66;
		case "B+":
		return 3.33;
		case "B":
		return 3.0;
		case "B-":
		return 2.66;
		case "C+":
		return 2.33;
		case "C":
		return 2.0;
		case "C-":
		return 1.66;
		case "D+":
		return 1.33;
		case "D":
		return 1.0;
		case "D-":
		return 0.66;
		case "F":
		return 0.0;
		}
		return -1;
		}
		
}






		



