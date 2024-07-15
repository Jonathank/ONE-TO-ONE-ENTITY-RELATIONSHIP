/**
 * 
 */
package refactured.application;

import model.school.School;
import model.school.SchoolRepository;
import model.student.Student;
import refactured.studentUtilities.StudentRepository;

/**
 * @author JONATHAN
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Student std = new Student();
		std.setFname("BAMUJJE");
		std.setLname("HERMAN");

		StudentRepository rep = new StudentRepository();
		SchoolRepository schrep = new SchoolRepository();
		
		rep.addStudent(std);
		System.out.println("\nADDED STUDENT  : " + std.toString());
		
		School sch = new School("NANA SCHOOLS","KAMPALA");
		schrep.addSchool(sch);
		System.out.println("\nADDED SCHOOL  : >>" + sch.toString());
		
		rep.findfirstnames().forEach(System.out::println);
		
		rep.findlasttnames().forEach(System.out::println);

		std = rep.findbyId(std.getId());
		System.out.println("\nFOUND STUDENT BY ID  : " + std.toString());
		
		rep.findStudent(std.getId());
		System.out.println("\nFOUND STUDENT  : " + std.toString());

		std.setFname("KING");
		std.setLname("NEUTON");
		rep.updateStudent(std);
		System.out.println("\nUPDATED STUDENT  : " + std.toString());

		std = rep.updateFirstName("KYEYUNE",std.getId());
		System.out.println("\nNEW UPDATED FIRST NAME  : >>>>" + std.toString());

		std = rep.updateLastName("HERMAN",std.getId());
		System.out.println("\nNEW UPDATED LAST NAME  : >>>>" + std.toString());

		//rep.deletestdbyId(std.getId());
		//System.out.println("\nDELETED STUDENT BY ID : >>>>" + std.toString());
		

		rep.close();
	}

}
