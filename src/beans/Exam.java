package beans;

import interfaces.TitleLocal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import util.gui.begin.BeguinningFrame;
import util.gui.other.BaseGUI;
import util.gui.view.BaseGUIViewBase;
import util.other.interfaces.Persister;
import enums.ExamSituation;

@Entity
public class Exam implements Persister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private double grade;
	private int timeTest;
	private ExamSituation examSituation = ExamSituation.PENDENT;
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = ListOfQuestions.class)
	private ListOfQuestions listOfQuestions;
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Teacher.class)
	private Teacher teacher;
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Student.class)
	private Student student;

	public Exam() {
	}

	public Exam(int timeTest, ListOfQuestions test, Teacher teacher,
			Student student) {
		super();
		this.timeTest = timeTest;
		this.listOfQuestions = test;
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return String
				.format("%s (%smin.)", getListOfQuestions(), getTimeTest());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getTimeTest() {
		return timeTest;
	}

	public void setTimeTest(int timeTest) {
		this.timeTest = timeTest;
	}

	public ExamSituation getExamSituation() {
		return examSituation;
	}

	public void setExamSituation(ExamSituation examSituation) {
		this.examSituation = examSituation;
	}

	public ListOfQuestions getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(ListOfQuestions listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StringBuffer result() {
		StringBuffer out = new StringBuffer();
		out.append(String.format(TitleLocal.TEACHER + ": %s", getTeacher()
				.getName()));
		out.append(String.format("\nNúmero de Questões: %s",
				getListOfQuestions().getQuestions().size()));
		out.append(String
				.format("\n\nPorcentagem de acerto: %s %%", getGrade()));

		return out;
	}

	@Override
	public BaseGUI invokeEditor(BeguinningFrame frame, Persister persister) {
		return null;
	}

	@Override
	public BaseGUIViewBase invokeView(BeguinningFrame frame, Persister persister) {
		return null;
	}

	@Override
	public String[] toVector() {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((examSituation == null) ? 0 : examSituation.hashCode());
		long temp;
		temp = Double.doubleToLongBits(grade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		result = prime * result + timeTest;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Exam other = (Exam) obj;
		if (examSituation == null) {
			if (other.examSituation != null) {
				return false;
			}
		} else if (!examSituation.equals(other.examSituation)) {
			return false;
		}
		if (Double.doubleToLongBits(grade) != Double
				.doubleToLongBits(other.grade)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (student == null) {
			if (other.student != null) {
				return false;
			}
		} else if (!student.equals(other.student)) {
			return false;
		}
		if (teacher == null) {
			if (other.teacher != null) {
				return false;
			}
		} else if (!teacher.equals(other.teacher)) {
			return false;
		}
		if (timeTest != other.timeTest) {
			return false;
		}
		return true;
	}
}
