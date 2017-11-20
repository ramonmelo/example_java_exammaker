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
import javax.persistence.OneToMany;

import util.gui.begin.BeguinningFrame;
import util.gui.other.BaseGUI;
import util.gui.view.BaseGUIViewBase;
import util.other.abstracts.CollectionManager;
import util.other.annotations.VisibleColumn;
import util.other.interfaces.Persister;
import gui.edit.CourseEditView;
import gui.view.CourseShowView;

@Entity
public class Course extends CollectionManager implements Persister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@VisibleColumn(columnLabel = "Nome", visible = true, show = true)
	private String name;
	@VisibleColumn(columnLabel = "Quantidade de Modulos", visible = true, show = true)
	private int quantityOfModules;
	@VisibleColumn(columnLabel = "Disciplinas", visible = false, show = true)
	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE }, targetEntity = Discipline.class, mappedBy = "course")
	private final Set<Discipline> disciplines = new HashSet<Discipline>();

	public Course() {
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

	public int getQuantityOfModules() {
		return quantityOfModules;
	}

	public void setQuantityOfModules(int quantityOfModules) {
		this.quantityOfModules = quantityOfModules;
	}

	public Set<Discipline> getDisciplines() {
		return disciplines;
	}

	public void addDiscipline(Discipline discipline) {
		this.disciplines.add(discipline);
	}

	@Override
	public BaseGUI invokeEditor(BeguinningFrame frame, Persister persister) {
		return new CourseEditView(frame, TitleLocal.EDIT_COURSE,
				(Course) persister);
	}

	@Override
	public BaseGUIViewBase invokeView(BeguinningFrame frame, Persister persister) {
		return new CourseShowView(frame, TitleLocal.VIEW_COURSE,
				TitleLocal.COURSE, persister);
	}

	@Override
	public String[] toVector() {
		String[] strings = { getName(), String.valueOf(getQuantityOfModules()) };

		return strings;
	}

	@Override
	public void addCollectionElement(CollectionManager inputOnCollection) {
		this.disciplines.add((Discipline) inputOnCollection);
	}

	@Override
	public void removeCollectionElement(CollectionManager inputOnCollection) {
		this.disciplines.remove(inputOnCollection);
	}

	@Override
	public void setOwnerField(CollectionManager ownerOfCollection) {
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

		for (Discipline discipline : getDisciplines()) {
			list.add(discipline);
		}

		return list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantityOfModules;
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
		Course other = (Course) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (quantityOfModules != other.quantityOfModules) {
			return false;
		}
		return true;
	}

}
