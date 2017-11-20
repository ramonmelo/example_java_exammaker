package beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import util.other.interfaces.SelectChoice;

@Entity
public class Choice implements SelectChoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String statement;
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Question.class)
	private Question question;
	private boolean marked;

	public Choice() {
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

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return getStatement();
	}

	@Override
	public void setText(String text) {
		setStatement(text);
	}

	@Override
	public void setMarked() {
		this.marked = true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (marked ? 1231 : 1237);
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((statement == null) ? 0 : statement.hashCode());
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
		Choice other = (Choice) obj;
		if (id != other.id) {
			return false;
		}
		if (marked != other.marked) {
			return false;
		}
		if (question == null) {
			if (other.question != null) {
				return false;
			}
		} else if (!question.equals(other.question)) {
			return false;
		}
		if (statement == null) {
			if (other.statement != null) {
				return false;
			}
		} else if (!statement.equals(other.statement)) {
			return false;
		}
		return true;
	}
}
