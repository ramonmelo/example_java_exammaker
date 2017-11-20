package beans;

import interfaces.TitleLocal;

import java.util.List;

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
import util.other.annotations.VisibleColumn;
import util.other.interfaces.Persister;
import gui.edit.QuestionEditView;
import gui.view.QuestionShowView;

@Entity
public class Question implements Persister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@VisibleColumn(columnLabel = "Enunciado", visible = true, show = true)
	private String statement;
	@VisibleColumn(columnLabel = "Assunto", visible = true, show = true)
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Subject.class)
	private Subject subject;
	@VisibleColumn(columnLabel = "Alternativas", show = true)
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Choice.class, mappedBy = "question")
	private List<Choice> choices;

	public Question() {
	}

	public Question(String statement) {
		this.statement = statement;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return statement;
	}

	@Override
	public BaseGUI invokeEditor(BeguinningFrame frame, Persister persister) {
		return new QuestionEditView(frame, TitleLocal.EDIT_QUESTION,
				(Question) persister);
	}

	@Override
	public BaseGUIViewBase invokeView(BeguinningFrame frame, Persister persister) {
		return new QuestionShowView(frame, TitleLocal.VIEW_QUESTION,
				TitleLocal.QUESTION, persister);
	}

	@Override
	public String[] toVector() {
		String[] strings = {
				getStatement().substring(
						0,
						getStatement().length() < 30 ? getStatement().length()
								: 30), getSubject().toString() };

		return strings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((statement == null) ? 0 : statement.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Question other = (Question) obj;
		if (id != other.id) {
			return false;
		}
		if (statement == null) {
			if (other.statement != null) {
				return false;
			}
		} else if (!statement.equals(other.statement)) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		return true;
	}

}
