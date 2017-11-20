package beans;

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
import util.other.interfaces.Persister;
import util.other.interfaces.RelationshipClass;
import util.other.interfaces.SideOfRelationship;

@Entity
public class CollegeClass extends CollectionManager implements
		SideOfRelationship, Persister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Teacher.class)
	private Teacher teacher;
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Discipline.class)
	private Discipline discipline;
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Registration.class, mappedBy = "collegeClass")
	private final Set<Registration> registrations = new HashSet<Registration>();

	public CollegeClass() {
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void addRegistration(Registration registration) {
		this.registrations.add(registration);
	}

	@Override
	public String toString() {
		if (discipline.toString() != null) {
			return discipline.toString() + " - " + name;
		} else {
			return name;
		}
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
	public void setOwnerField(CollectionManager ownerOfCollection) {
		if (ownerOfCollection instanceof Teacher) {
			this.teacher = (Teacher) ownerOfCollection;
		}
		if (ownerOfCollection instanceof Discipline) {
			this.discipline = (Discipline) ownerOfCollection;
		}
	}

	@Override
	public void setTextField(String value) {
		setName(value);
	}

	@Override
	public void addCollectionElement(CollectionManager inputOnCollection) {
	}

	@Override
	public void removeCollectionElement(CollectionManager inputOnCollection) {
	}

	@Override
	public List<Object> getCollection() {
		return null;
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
				+ ((discipline == null) ? 0 : discipline.hashCode());
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
		CollegeClass other = (CollegeClass) obj;
		if (discipline == null) {
			if (other.discipline != null) {
				return false;
			}
		} else if (!discipline.equals(other.discipline)) {
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
