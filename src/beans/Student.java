package beans;

import interfaces.TitleLocal;

import java.util.HashSet;
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
import util.other.annotations.VisibleColumn;
import util.other.interfaces.Persister;
import util.other.interfaces.RelationshipClass;
import util.other.interfaces.SideOfRelationship;
import gui.edit.StudentEditView;
import gui.view.StudentShowView;

/**
 * 
 * @author melo
 * 
 *         Estudante
 */
@Entity
public class Student implements Persister, SideOfRelationship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@VisibleColumn(columnLabel = "Nome", visible = true, show = true)
	private String name;
	@Column(name = "melouser")
	@VisibleColumn(columnLabel = "Usuário", visible = true, show = true)
	private String user;
	private String password;
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Exam.class, mappedBy = "student")
	@VisibleColumn(columnLabel = "Exames", show = true)
	private final Set<Exam> exams = new HashSet<Exam>();
	@VisibleColumn(columnLabel = "Matriculas", show = true)
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Registration.class, mappedBy = "student")
	private final Set<Registration> registrations = new HashSet<Registration>();

	public Student() {
	}

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

	public Set<Exam> getExams() {
		return exams;
	}

	public void addExam(Exam exam) {
		this.exams.add(exam);
	}

	public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void addRegistration(Registration registration) {
		this.registrations.add(registration);
	}

	@Override
	public BaseGUI invokeEditor(BeguinningFrame frame, Persister persister) {
		return new StudentEditView(frame, TitleLocal.EDIT_STUDENT,
				(Student) persister);
	}

	@Override
	public BaseGUIViewBase invokeView(BeguinningFrame frame, Persister persister) {
		return new StudentShowView(frame, TitleLocal.VIEW_STUDENT,
				TitleLocal.STUDENT, persister);
	}

	@Override
	public String[] toVector() {
		String[] strings = { getName(), getUser() };

		return strings;
	}

	@Override
	public void addRelationship(RelationshipClass relationshipClass) {
		addRegistration((Registration) relationshipClass);
	}

	@Override
	public void removeRelationship(RelationshipClass relationshipClass) {
		this.registrations.remove(relationshipClass);
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", getUser(), getName());
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
		Student other = (Student) obj;
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
