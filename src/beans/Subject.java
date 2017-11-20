package beans;

import interfaces.TitleLocal;

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
import gui.edit.SubjectEditView;
import gui.view.SubjectShowView;

@Entity
public class Subject extends CollectionManager implements Persister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@VisibleColumn(columnLabel = "Nome", visible = true, show = true)
	private String name;
	@VisibleColumn(columnLabel = "Disciplina", visible = true, show = true)
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Discipline.class)
	private Discipline discipline;
	@VisibleColumn(columnLabel = "Questões", show = true)
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Question.class, mappedBy = "subject")
	private final Set<Question> questions = new HashSet<Question>();

	public Subject() {
	}

	public Subject(String name, Discipline discipline) {
		super();
		this.name = name;
		this.discipline = discipline;
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

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	@Override
	public void setTextField(String value) {
		this.name = value;
	}

	@Override
	public void setOwnerField(CollectionManager ownerOfCollection) {
		this.discipline = (Discipline) ownerOfCollection;
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
	public String toString() {
		return name;
	}

	@Override
	public BaseGUI invokeEditor(BeguinningFrame frame, Persister persister) {
		return new SubjectEditView(frame, TitleLocal.EDIT_SUBJECT,
				(Subject) persister);
	}

	@Override
	public BaseGUIViewBase invokeView(BeguinningFrame frame, Persister persister) {
		return new SubjectShowView(frame, TitleLocal.VIEW_SUBJECT,
				TitleLocal.SUBJECT, persister);
	}

	@Override
	public String[] toVector() {
		String[] strings = { getName(), getDiscipline().toString() };

		return strings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Subject other = (Subject) obj;
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
