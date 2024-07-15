package refactured.studentUtilities;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.school.School;
import model.student.Student;

public class StudentRepository {

	private EntityManager em;
	private EntityManagerFactory emf;

	public StudentRepository() {
		this.emf = Persistence.createEntityManagerFactory("Student_PU");
		this.em = this.emf.createEntityManager();
	}

	//adds new student and returns student object
	public Student addStudent(Student std) {
		em.getTransaction().begin();
		em.persist(std);
		em.getTransaction().commit();

		return std;
	}

	//adds school
	public Student  addStdSchool(long id, School sch) {
		em.getTransaction().begin();
		Student std = findStudent(id);
		std.setSch(sch);
		em.getTransaction().commit();
		return std;
	}
	//returns student object by their id
	public Student findStudent(long id) {
		return em.find(Student.class, id);
	}
	
	//returns student object by their id(named query)
	public Student findbyId(long id) {
		Query query = em.createNamedQuery("find student by id");
		query.setParameter("id", id);

		return (Student)query.getSingleResult();
	}

	//updates students details
	public Student updateStudent(Student std) {
		Student stdUpdate = findStudent(std.getId());
		em.getTransaction().begin();
		stdUpdate.setFname(std.getFname());
		stdUpdate.setLname(std.getLname());
		em.getTransaction().commit();

		return stdUpdate;
	}

	
	//returns first name
	public List<String> findfirstnames(){
		Query query = em.createQuery("SELECT s.fname FROM Student s");
		return query.getResultList();
	}
	
	//updates only the first name basing on id
	public Student updateFirstName(String fname, long id) {
		em.getTransaction().begin();
		Query query = em.createQuery("update Student set fname ='"+ fname +"'where id = "+id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.clear(); 	//to persist the changes since jpql does not affect the directly
		return findbyId(id);
	}
	
	//updates last name basing id
	public Student updateLastName(String lname, long id) {
		em.getTransaction().begin();
		Query query = em.createQuery("update Student set lname ='"+ lname +"'where id = "+id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.clear(); 	//to persist the changes since jpql does not affect the directly
		return findbyId(id);
	}
	
	//returns last name
	public List<String> findlasttnames(){
		Query query = em.createQuery("SELECT s.lname FROM Student s");
		
		return query.getResultList();
	}
	
	
	//deletes student object from db
		public void deleteStudent(Student std) {
			Student stdUpdate = findStudent(std.getId());
			em.getTransaction().begin();
			em.remove(stdUpdate);
			em.getTransaction().commit();
		}
		
	//deletes student from db by id
		public void deletestdbyId(long id) {
			em.getTransaction().begin();
			Query query = em.createQuery("Delete from Student where id = "+id);
			query.executeUpdate();
			em.getTransaction().commit();
		}
		
		
		//additional functions
		
		//like$  not used
		public List<Student> findfnameStartWith(String keyword){
			Query qry = em.createQuery("Select s from Student s where s.fname like '"+keyword+"$'");
			
			return qry.getResultList();
		}
		
		//counts all students in the DB\
		public long count() {
			Query qry = em.createQuery("Select count(s) from Student s");
			return (long) qry.getSingleResult();
		}
		
		//sort functions
		public List<Student>sortFname(){
			Query qry = em.createQuery("Select s from Student order by s.fnsme desc");
			return qry.getResultList();
		}

	public void close() {
		this.em.close();
		this.emf.close();
	}

}
