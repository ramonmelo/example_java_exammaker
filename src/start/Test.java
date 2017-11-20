package start;

import java.util.List;
import java.util.Set;

import beans.CollegeClass;
import beans.Teacher;

import util.other.BaseDAO;
import util.other.interfaces.Persister;

public class Test {
	public static void main(String[] args) {

		List<? extends Persister> find = BaseDAO.find(Teacher.class);

		for (Persister persister : find) {
			Teacher teacher = (Teacher) persister;

			Set<CollegeClass> classes = teacher.getCollegeClasses();

			for (CollegeClass collegeClass : classes) {
				System.out.println(collegeClass.toString());
			}
		}
	}
}
