package other;

import java.util.List;

import beans.Student;
import beans.Teacher;

import util.other.BaseDAO;
import util.other.interfaces.Persister;
import gui.begin.BeginLayer;
import gui.begin.BeginLayerStudent;

public class LoginAcess {

	public static boolean loginTest(String user, String pass) {
		List<? extends Persister> listStudent = BaseDAO.find(Student.class);

		for (Persister persister : listStudent) {
			Student student = (Student) persister;

			if (student.getUser().equals(user)
					&& student.getPassword().equals(pass)) {
				new BeginLayerStudent(student);
				return true;
			}
		}

		List<? extends Persister> listTeacher = BaseDAO.find(Teacher.class);

		if (listTeacher.isEmpty()) {
			Teacher teacher = new Teacher();
			teacher.setUser(user);
			teacher.setPassword(pass);
			teacher.setName(user);

			BaseDAO.create(teacher);

			new BeginLayer(teacher);
			return true;
		}

		for (Persister persister : listTeacher) {
			Teacher teacher = (Teacher) persister;

			if (teacher.getUser().equals(user)
					&& teacher.getPassword().equals(pass)) {
				new BeginLayer(teacher);
				return true;
			}
		}

		return false;
	}
}
