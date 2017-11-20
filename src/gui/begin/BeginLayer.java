package gui.begin;

import gui.create.CourseCreateView;
import gui.create.ExamCreateView;
import gui.create.QuestionCreateView;
import gui.create.StudentCreateView;
import gui.create.TeacherCreateView;
import gui.find.CourseFindView;
import gui.find.DisciplineFindView;
import gui.find.QuestionFindView;
import gui.find.StudentFindView;
import gui.find.SubjectFindView;
import gui.find.TeacherFindView;
import gui.other.Login;
import interfaces.TitleLocal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import beans.CollegeClass;
import beans.Course;
import beans.Discipline;
import beans.Subject;
import beans.Teacher;

import util.gui.begin.BeguinningFrame;
import util.gui.look.Icons;
import util.gui.other.BaseMenu;
import util.other.interfaces.Title;

@SuppressWarnings("serial")
public class BeginLayer extends BeguinningFrame {

	// Menu Create
	private final JMenuItem itemCreateStudent;
	private final JMenuItem itemCreateTeacher;

	private final BaseMenu menuEducational;
	private final JMenuItem itemEducationalCourse;
	private final JMenuItem itemEducationalQuestion;
	private final JMenuItem itemEducationalExam;

	// Menu Find
	private final JMenuItem itemFindStudent;
	private final JMenuItem itemFindTeacher;

	private final BaseMenu menuEducationalFind;
	private final JMenuItem itemEducationalFindCourse;
	private final JMenuItem itemEducationalFindDiscipline;
	private final JMenuItem itemEducationalFindSubject;
	private final JMenuItem itemEducationalFindQuestion;

	private final Icons icons = new Icons();

	private final JMenuItem itemArchiveLogout;

	private final Teacher teacher;

	public BeginLayer(Teacher teacher) {
		super(false, true);
		this.teacher = teacher;
		setTitle(TitleLocal.BEGINTITLE);
		setCredits("Ramon Soares de Melo", "workmelo@gmail.com",
				"(86) 9992 7362");

		itemArchiveLogout = new JMenuItem("Logout", icons
				.getOther("logout.png"));
		getMenuArchive().add(itemArchiveLogout);

		// Menu Create
		itemCreateStudent = new JMenuItem("Aluno", icons
				.getOther("student.png"));
		itemCreateTeacher = new JMenuItem("Professor", icons
				.getOther("teacher.png"));

		menuEducational = new BaseMenu("Educacional", icons
				.getOther("education.png"));

		itemEducationalCourse = new JMenuItem("Curso", icons
				.getOther("course.png"));
		itemEducationalQuestion = new JMenuItem("Questão", icons
				.getOther("question.png"));
		itemEducationalExam = new JMenuItem("Prova", icons.getOther("exam.png"));

		menuEducational.add(itemEducationalCourse);
		menuEducational.add(itemEducationalQuestion);
		menuEducational.add(itemEducationalExam);

		getMenuCreate().add(itemCreateStudent);
		getMenuCreate().add(itemCreateTeacher);
		getMenuCreate().add(menuEducational);

		// Menu Find
		itemFindStudent = new JMenuItem("Aluno", icons.getOther("student.png"));
		itemFindTeacher = new JMenuItem("Professor", icons
				.getOther("teacher.png"));

		menuEducationalFind = new BaseMenu("Educacional", icons
				.getOther("education.png"));

		itemEducationalFindCourse = new JMenuItem("Curso", icons
				.getOther("course.png"));
		itemEducationalFindDiscipline = new JMenuItem("Disciplina", icons
				.getOther("discipline.png"));
		itemEducationalFindSubject = new JMenuItem("Assunto", icons
				.getOther("subject.png"));
		itemEducationalFindQuestion = new JMenuItem("Questão", icons
				.getOther("question.png"));

		menuEducationalFind.add(itemEducationalFindCourse);
		menuEducationalFind.add(itemEducationalFindDiscipline);
		menuEducationalFind.add(itemEducationalFindSubject);
		menuEducationalFind.add(itemEducationalFindQuestion);

		getMenuFind().add(itemFindStudent);
		getMenuFind().add(itemFindTeacher);
		getMenuFind().add(menuEducationalFind);

		actions();
		toolTips();
	}

	private void actions() {
		addAloneClasses(Subject.class, Discipline.class);
		addAloneClasses(CollegeClass.class, Discipline.class);
		addAloneClasses(Discipline.class, Course.class);

		itemArchiveLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ops = { "Sim", "Não" };

				int answer = JOptionPane.showOptionDialog(BeginLayer.this,
						"Deseja realmente sair?", "Sair",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, icons.getExitBig(), ops,
						1);

				if (answer == 0) {
					BeginLayer.this.setVisible(false);
					new Login();
					BeginLayer.this.dispose();
				}
			}
		});

		// Menu Create

		itemCreateStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				change(new StudentCreateView(BeginLayer.this,
						TitleLocal.CREATE_STUDENT));
			}
		});

		itemCreateTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new TeacherCreateView(BeginLayer.this,
						TitleLocal.CREATE_TEACHER));
			}
		});

		itemEducationalCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new CourseCreateView(BeginLayer.this,
						TitleLocal.CREATE_COURSE));
			}
		});

		itemEducationalQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new QuestionCreateView(BeginLayer.this,
						TitleLocal.CREATE_QUESTION));
			}
		});

		itemEducationalExam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new ExamCreateView(BeginLayer.this,
						TitleLocal.CREATE_EXAM));
			}
		});

		// Menu Find
		itemFindStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new StudentFindView(BeginLayer.this,
						TitleLocal.FIND_STUDENT));
			}
		});

		itemFindTeacher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new TeacherFindView(BeginLayer.this,
						TitleLocal.FIND_TEACHER));
			}
		});

		itemEducationalFindCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new CourseFindView(BeginLayer.this,
						TitleLocal.FIND_COURSE));
			}
		});

		itemEducationalFindDiscipline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new DisciplineFindView(BeginLayer.this,
						TitleLocal.FIND_DISCIPLINE));
			}
		});

		itemEducationalFindSubject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new SubjectFindView(BeginLayer.this,
						TitleLocal.FIND_SUBJECT));
			}
		});

		itemEducationalFindQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				change(new QuestionFindView(BeginLayer.this,
						TitleLocal.FIND_QUESTION));
			}
		});
	}

	public Teacher getTeacher() {
		return teacher;
	}

	private void toolTips() {
		itemArchiveLogout.setToolTipText("Fazer logoff do sistema");
		itemCreateStudent.setToolTipText(Title.CREATE + TitleLocal.STUDENT);
		itemCreateTeacher.setToolTipText(Title.CREATE + TitleLocal.TEACHER);

		itemEducationalCourse.setToolTipText(Title.CREATE + TitleLocal.COURSE);
		itemEducationalExam.setToolTipText(Title.CREATE + TitleLocal.EXAM);
		itemEducationalQuestion.setToolTipText(Title.CREATE
				+ TitleLocal.QUESTION);

		itemFindStudent.setToolTipText(Title.FIND + TitleLocal.STUDENT);
		itemFindTeacher.setToolTipText(Title.FIND + TitleLocal.TEACHER);

		itemEducationalFindCourse
				.setToolTipText(Title.FIND + TitleLocal.COURSE);
	}
}
