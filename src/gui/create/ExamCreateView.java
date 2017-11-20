package gui.create;

import gui.begin.BeginLayer;
import interfaces.TitleLocal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import beans.CollegeClass;
import beans.Course;
import beans.Discipline;
import beans.Exam;
import beans.ListOfQuestions;
import beans.Question;
import beans.Registration;
import beans.Student;
import beans.Subject;

import util.gui.button.BaseButton;
import util.gui.fields.BaseDataSelectionList;
import util.gui.fields.BaseField;
import util.gui.fields.BaseListBoxesCollection;
import util.gui.look.Icons;
import util.gui.other.BaseGUI;
import util.gui.other.BasePanel;
import util.other.BaseDAO;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class ExamCreateView extends BaseGUI {

	private final BasePanel panelSubjects;
	private final BasePanel panelQuestions;
	private final BasePanel panelStudents;

	// private BasePanel panelSubjectContent;
	// private BasePanel panelStudentContent;

	// Subject
	private final BaseListBoxesCollection baseListBoxesSubject;
	private final BaseField fieldTime;
	private final BaseButton buttonFind;

	// Students
	private final BaseField fieldExamName;
	private final BaseListBoxesCollection baseListBoxesStudent;

	// Questions
	private final BaseDataSelectionList selectionList;

	// Context
	private final BasePanel panelAll = new BasePanel();

	private final int width = 360;
	private final int heigth = 300;

	private final BaseButton buttonSend;

	private final BeginLayer layer;

	private final Icons icons = new Icons();

	@SuppressWarnings("unchecked")
	public ExamCreateView(BeginLayer frame, String title) {
		super(frame, title);
		this.layer = frame;

		panelSubjects = new BasePanel("Assuntos");
		panelSubjects.setPreferredSize(new Dimension(width, heigth));

		fieldTime = new BaseField("Tempo de duração (minutos)", 20,
				TitleLocal.EXAM, false);

		baseListBoxesSubject = new BaseListBoxesCollection(TitleLocal.SUBJECT,
				Course.class, Discipline.class, Subject.class);
		baseListBoxesSubject.setSize(200, 50);

		buttonFind = new BaseButton("Buscar questões", icons.getFind2());

		panelSubjects.insert(fieldTime, 0, 0, 1, 1);
		panelSubjects.insert(baseListBoxesSubject, 0, 1, 1, 1);
		panelSubjects.insert(buttonFind, 0, 2, 1, 1);

		selectionList = new BaseDataSelectionList("Questões", "Prova",
				"Questions");

		panelQuestions = new BasePanel("Listagem");
		panelQuestions.setLayout(new BorderLayout());

		panelQuestions.add(selectionList);

		panelStudents = new BasePanel("Turmas");
		panelStudents.setPreferredSize(new Dimension(width, heigth));

		fieldExamName = new BaseField("Identificação do Exame", 20,
				TitleLocal.EXAM, true);

		baseListBoxesStudent = new BaseListBoxesCollection(
				TitleLocal.COLLEGE_CLASS, Course.class, Discipline.class,
				CollegeClass.class);

		panelStudents.insert(fieldExamName, 0, 0, 1, 1);
		panelStudents.insert(baseListBoxesStudent, 0, 1, 1, 1);

		buttonSend = new BaseButton("Enviar prova", icons
				.getOther("sendExam.png"));

		actions();

		panelAll.setLayout(new BorderLayout());

		panelAll.add(panelSubjects, BorderLayout.WEST);
		panelAll.add(panelStudents, BorderLayout.EAST);
		panelAll.add(panelQuestions, BorderLayout.SOUTH);

		insert(panelAll, 0, 0, 3, 1);
		insert(buttonSend, 1, 1, 1, 1);
	}

	private void actions() {
		buttonFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectionList.setOrigin(baseListBoxesSubject.getValues());
			}
		});

		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Set<Persister> collegeClasses = baseListBoxesStudent
						.getValues();

				for (Persister persister : collegeClasses) {
					CollegeClass collegeClass = (CollegeClass) persister;

					Set<Registration> registrations = collegeClass
							.getRegistrations();

					for (Registration registration : registrations) {
						Student student = registration.getStudent();

						Exam exam = new Exam();

						ListOfQuestions questions = new ListOfQuestions();

						Set<Persister> selectedValues = selectionList
								.getSelectedValues();
						int time = Integer.parseInt(fieldTime.getText());

						for (Persister persisterAction : selectedValues) {
							questions.addQuestion((Question) persisterAction);
						}
						questions.setTestName(fieldExamName.getText());

						exam.setListOfQuestions(questions);
						exam.setStudent(student);
						exam.setTimeTest(time);
						exam.setTeacher(layer.getTeacher());

						student.addExam(exam);

						BaseDAO.update(student);
					}
				}
			}
		});
	}

	@Override
	public void goBack(Persister persister) {
	}

}
