package beans;

import interfaces.TitleLocal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import util.gui.begin.BeguinningFrame;
import util.gui.other.BaseGUI;
import util.gui.view.BaseGUIViewBase;
import util.other.abstracts.CollectionManager;
import util.other.annotations.VisibleColumn;
import util.other.interfaces.Persister;
import gui.edit.DisciplineEditView;
import gui.view.DisciplineShowView;

@Entity
public class Discipline extends CollectionManager implements Persister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@VisibleColumn(columnLabel = "Nome", visible = true, show = true)
	private String name;
	@VisibleColumn(columnLabel = "Carga Horária", visible = true, show = true)
	private int hourlyLoad;
	@VisibleColumn(columnLabel = "Curso", visible = true, show = true)
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Course.class)
	private Course course;
	@VisibleColumn(columnLabel = "Assuntos", show = true)
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Subject.class, mappedBy = "discipline")
	private final Set<Subject> subjects = new HashSet<Subject>();
	@VisibleColumn(columnLabel = "Turmas", show = true)
	@OneToMany(cascade = CascadeType.ALL, targetEntity = CollegeClass.class, mappedBy = "discipline")
	private final Set<CollegeClass> collegeClasses = new HashSet<CollegeClass>();

	public Discipline() {
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

	public int getHourlyLoad() {
		return hourlyLoad;
	}

	public void setHourlyLoad(int hourlyLoad) {
		this.hourlyLoad = hourlyLoad;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}

	public Set<CollegeClass> getCollegeClasses() {
		return collegeClasses;
	}

	public void addCourseClass(CollegeClass courseClass) {
		this.collegeClasses.add(courseClass);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public BaseGUI invokeEditor(BeguinningFrame frame, Persister persister) {
		return new DisciplineEditView(frame, TitleLocal.EDIT_DISCIPLINE,
				(Discipline) persister);
	}

	@Override
	public BaseGUIViewBase invokeView(BeguinningFrame frame, Persister persister) {
		return new DisciplineShowView(frame, TitleLocal.VIEW_DISCIPLINE,
				TitleLocal.DISCIPLINE, persister);
	}

	@Override
	public String[] toVector() {
		String[] strings = { getName(), String.valueOf(getHourlyLoad()),
				getCourse().toString() };

		return strings;
	}

	@Override
	public void addCollectionElement(CollectionManager inputOnCollection) {
		if (inputOnCollection instanceof Subject) {
			addSubject((Subject) inputOnCollection);
		}
		if (inputOnCollection instanceof CollegeClass) {
			addCourseClass((CollegeClass) inputOnCollection);
		}
	}

	@Override
	public void removeCollectionElement(CollectionManager inputOnCollection) {
		if (inputOnCollection instanceof Subject) {
			getSubjects().remove(inputOnCollection);
		}
		if (inputOnCollection instanceof CollegeClass) {
			getCollegeClasses().remove(inputOnCollection);
		}
	}

	@Override
	public void setOwnerField(CollectionManager ownerOfCollection) {
		this.course = (Course) ownerOfCollection;
	}

	@Override
	public void setTextField(String value) {
	}

	@Override
	public String toString() {
		return name;
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
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + hourlyLoad;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Discipline other = (Discipline) obj;
		if (course == null) {
			if (other.course != null) {
				return false;
			}
		} else if (!course.equals(other.course)) {
			return false;
		}
		if (hourlyLoad != other.hourlyLoad) {
			return false;
		}
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
		return true;
	}
}
