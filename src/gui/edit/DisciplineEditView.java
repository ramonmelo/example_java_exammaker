package gui.edit;

import beans.CollegeClass;
import beans.Discipline;
import beans.Subject;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonEdit;
import util.gui.fields.BaseCollectionInputText;
import util.gui.fields.BaseField;
import util.gui.other.BaseGUI;
import util.other.abstracts.CollectionManager;
import util.other.annotations.IDField;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class DisciplineEditView extends BaseGUI {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("hourlyLoad")
	private final BaseField fieldHourlyLoad;

	@IDField("subjects")
	private final BaseCollectionInputText inputSubjects;

	@IDField("collegeClasses")
	private final BaseCollectionInputText inputCourseClass;

	private final BaseButtonEdit buttonEdit;

	public DisciplineEditView(BeguinningFrame frame, String title,
			Discipline discipline) {
		super(frame, title);

		fieldName = new BaseField("Nome", 28, TitleLocal.DISCIPLINE, false);
		fieldHourlyLoad = new BaseField("Carga Horária", 12,
				TitleLocal.DISCIPLINE, false, "\\d+");
		inputSubjects = new BaseCollectionInputText(Subject.class, discipline,
				TitleLocal.SUBJECT, TitleLocal.DISCIPLINE);
		inputCourseClass = new BaseCollectionInputText(CollegeClass.class,
				(CollectionManager) inputSubjects.getOwner(),
				TitleLocal.COLLEGE_CLASS, TitleLocal.DISCIPLINE);

		buttonEdit = new BaseButtonEdit(this, discipline);

		insert(fieldName, 0, 0, 4, 1);
		insert(fieldHourlyLoad, 0, 1, 2, 1);
		insert(inputSubjects, 0, 2, 4, 4);
		insert(inputCourseClass, 0, 12, 4, 4);
		insert(buttonEdit, 0, 16, 4, 1);
	}

	@Override
	public void goBack(Persister persister) {
	}

}
