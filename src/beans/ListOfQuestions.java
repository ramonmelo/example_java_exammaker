package beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ListOfQuestions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String testName;
	@OneToMany(cascade = CascadeType.MERGE, targetEntity = Question.class)
	private final Set<Question> questions = new HashSet<Question>();
	@OneToOne(cascade = CascadeType.MERGE, targetEntity = Exam.class, mappedBy = "listOfQuestions")
	private Exam exam;

	public ListOfQuestions() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void addQuestion(Question question) {
		questions.add(question);
	}

	@Override
	public String toString() {
		return testName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exam == null) ? 0 : exam.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((testName == null) ? 0 : testName.hashCode());
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
		ListOfQuestions other = (ListOfQuestions) obj;
		if (exam == null) {
			if (other.exam != null) {
				return false;
			}
		} else if (!exam.equals(other.exam)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (testName == null) {
			if (other.testName != null) {
				return false;
			}
		} else if (!testName.equals(other.testName)) {
			return false;
		}
		return true;
	}
}
