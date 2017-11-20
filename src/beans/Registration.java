package beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import util.other.interfaces.RelationshipClass;
import util.other.interfaces.SideOfRelationship;

@Entity
public class Registration implements RelationshipClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = CollegeClass.class)
	private CollegeClass collegeClass;
	@ManyToOne(cascade = CascadeType.MERGE, targetEntity = Student.class)
	private Student student;

	public Registration() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CollegeClass getCourseClass() {
		return collegeClass;
	}

	public void setCourseClass(CollegeClass courseClass) {
		this.collegeClass = courseClass;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public void setRelationshipItens(SideOfRelationship sideOfRelationship1,
			SideOfRelationship sideOfRelationship2) {

		// if (sideOfRelationship1 instanceof Student) {
		this.student = (Student) sideOfRelationship1;
		// } else if (sideOfRelationship1 instanceof CollegeClass) {
		this.collegeClass = (CollegeClass) sideOfRelationship2;
		// }

		// if (sideOfRelationship2 instanceof Student) {
		// this.student = (Student) sideOfRelationship2;
		// } else if (sideOfRelationship2 instanceof CollegeClass) {
		// this.collegeClass = (CollegeClass) sideOfRelationship2;
		// }

	}

	@Override
	public String toString() {
		return getCourseClass().toString();
	}

	@Override
	public SideOfRelationship getSecondSide() {
		return collegeClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((collegeClass == null) ? 0 : collegeClass.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		Registration other = (Registration) obj;
		if (collegeClass == null) {
			if (other.collegeClass != null) {
				return false;
			}
		} else if (!collegeClass.equals(other.collegeClass)) {
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
		return true;
	}

}
