package interfaces;

import util.other.interfaces.Title;

public interface TitleLocal extends Title {

	static String WHITE_SPACE = " - ";

	public static String BEGINTITLE = "Prova Eletrônica Scritus";

	public static String STUDENT = "Aluno";

	public static String TEACHER = "Professor";

	public static String DISCIPLINE = "Disciplina";

	public static String COURSE = "Curso";

	public static String SUBJECT = "Assunto";

	public static String COLLEGE_CLASS = "Turma";

	public static String QUESTION = "Questão";

	public static String EXAM = "Prova";

	public static String CREDITS = BEGINTITLE + WHITE_SPACE + Title.CREDITS;

	public static String CREATE_TEACHER = BEGINTITLE + WHITE_SPACE
			+ Title.CREATE + TEACHER;

	public static String CREATE_STUDENT = BEGINTITLE + WHITE_SPACE
			+ Title.CREATE + STUDENT;

	public static String CREATE_DISCIPLINE = BEGINTITLE + WHITE_SPACE
			+ Title.CREATE + DISCIPLINE;

	public static String CREATE_COURSE = BEGINTITLE + WHITE_SPACE
			+ Title.CREATE + COURSE;

	public static String CREATE_QUESTION = BEGINTITLE + WHITE_SPACE
			+ Title.CREATE + QUESTION;

	public static String CREATE_EXAM = BEGINTITLE + WHITE_SPACE + Title.CREATE
			+ EXAM;

	public static String FIND_STUDENT = BEGINTITLE + WHITE_SPACE + Title.FIND
			+ STUDENT;

	public static String FIND_TEACHER = BEGINTITLE + WHITE_SPACE + Title.FIND
			+ TEACHER;

	public static String FIND_COURSE = BEGINTITLE + WHITE_SPACE + Title.FIND
			+ COURSE;

	public static String FIND_DISCIPLINE = BEGINTITLE + WHITE_SPACE
			+ Title.FIND + DISCIPLINE;

	public static String FIND_SUBJECT = BEGINTITLE + WHITE_SPACE + Title.FIND
			+ SUBJECT;

	public static String FIND_QUESTION = BEGINTITLE + WHITE_SPACE + Title.FIND
			+ QUESTION;

	public static String EDIT_STUDENT = BEGINTITLE + WHITE_SPACE + Title.EDIT
			+ STUDENT;

	public static String EDIT_TEACHER = BEGINTITLE + WHITE_SPACE + Title.EDIT
			+ TEACHER;

	public static String EDIT_COURSE = BEGINTITLE + WHITE_SPACE + Title.EDIT
			+ COURSE;

	public static String EDIT_DISCIPLINE = BEGINTITLE + WHITE_SPACE
			+ Title.EDIT + DISCIPLINE;

	public static String EDIT_SUBJECT = BEGINTITLE + WHITE_SPACE + Title.EDIT
			+ SUBJECT;

	public static String EDIT_QUESTION = BEGINTITLE + WHITE_SPACE + Title.EDIT
			+ QUESTION;

	public static String VIEW_COURSE = BEGINTITLE + WHITE_SPACE + Title.VIEW
			+ COURSE;

	public static String VIEW_DISCIPLINE = BEGINTITLE + WHITE_SPACE
			+ Title.VIEW + DISCIPLINE;

	public static String VIEW_SUBJECT = BEGINTITLE + WHITE_SPACE + Title.VIEW
			+ SUBJECT;

	public static String VIEW_QUESTION = BEGINTITLE + WHITE_SPACE + Title.VIEW
			+ QUESTION;

	public static String VIEW_STUDENT = BEGINTITLE + WHITE_SPACE + Title.VIEW
			+ STUDENT;

	public static String VIEW_TEACHER = BEGINTITLE + WHITE_SPACE + Title.VIEW
			+ TEACHER;
}
