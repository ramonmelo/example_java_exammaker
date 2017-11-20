package beans;

import gui.edit.TeacherEditView;
import gui.view.TeacherShowView;
import interfaces.TitleLocal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import util.gui.begin.BeguinningFrame;
import util.gui.other.BaseGUI;
import util.gui.view.BaseGUIViewBase;
import util.other.abstracts.CollectionManager;
import util.other.annotations.VisibleColumn;
import util.other.interfaces.Persister;

@Entity
public class Teacher extends CollectionManager implements Persister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@VisibleColumn(columnLabel = "Nome", visible = true, show = true)
	private String name;
	@Column(name = "melouser")
	@VisibleColumn(columnLabel = "Usuário", visible = true, show = true)
	private String user;
	private String password;
	@OneToMany(cascade = CascadeType.MERGE, targetEntity = Exam.class, mappedBy = "teacher")
	private final Set<Exam> exams = new HashSet<Exam>();
	@VisibleColumn(columnLabel = "Turmas", show = true)
	@OneToMany(cascade = CascadeType.MERGE, targetEntity = CollegeClass.class, mappedBy = "teacher")
	private final Set<CollegeClass> collegeClasses = new HashSet<CollegeClass>();

	public Teacher() {
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<CollegeClass> getCollegeClasses() {
		return collegeClasses;
	}

	public void addCollegeClass(CollegeClass collegeClass) {
		this.collegeClasses.add(collegeClass);
	}

	public Set<Exam> getExams() {
		return exams;
	}

	public void addExam(Exam exam) {
		this.exams.add(exam);
	}

	@Override
	public BaseGUI invokeEditor(BeguinningFrame frame, Persister persister) {
		return new TeacherEditView(frame, TitleLocal.EDIT_TEACHER,
				(Teacher) persister);
	}

	@Override
	public BaseGUIViewBase invokeView(BeguinningFrame frame, Persister persister) {
		return new TeacherShowView(frame, TitleLocal.VIEW_TEACHER,
				TitleLocal.TEACHER, persister);
	}

	@Override
	public String[] toVector() {
		String[] strings = { getName(), getUser() };

		return strings;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", getUser(), getName());
	}

	@Override
	public void addCollectionElement(CollectionManager inputOnCollection) {
		if (inputOnCollection instanceof CollegeClass) {
			addCollegeClass((CollegeClass) inputOnCollection);
		}
	}

	@Override
	public void removeCollectionElement(CollectionManager inputOnCollection) {
		if (inputOnCollection instanceof CollegeClass) {
			this.collegeClasses.remove(inputOnCollection);
		}
	}

	@Override
	public void setOwnerField(CollectionManager ownerOfCollection) {
	}

	@Override
	public void setTextField(String value) {
	}

	@Override
	public List<Object> getCollection() {
		List<Object> list = new ArrayList<Object>();

		for (CollegeClass collegeClass : getCollegeClasses()) {
			list.add(collegeClass);
		}

		return list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Teacher other = (Teacher) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

}
