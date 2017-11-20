package gui.create;

import interfaces.CourseUtil;
import interfaces.TitleLocal;

import java.awt.Dimension;

import beans.CollegeClass;
import beans.Discipline;
import beans.Subject;

import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonCreate;
import util.gui.fields.BaseCollectionInputText;
import util.gui.fields.BaseField;
import util.gui.other.BaseGUI;
import util.other.abstracts.CollectionManager;
import util.other.annotations.IDField;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class DisciplineCreateView extends BaseGUI {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("hourlyLoad")
	private final BaseField fieldHourlyLoad;

	@IDField("subjects")
	private final BaseCollectionInputText inputSubjects;

	@IDField("courseClasses")
	private final BaseCollectionInputText inputCourseClass;

	private final BaseButtonCreate buttonCreate;

	private final CourseUtil baseGUI;
	private final BeguinningFrame frame;

	public DisciplineCreateView(BeguinningFrame frame, String title,
			CourseUtil back) {
		super(frame, title);
		this.baseGUI = back;
		this.frame = frame;

		fieldName = new BaseField("Nome", 28, TitleLocal.DISCIPLINE, false);
		fieldHourlyLoad = new BaseField("Carga Horária", 12,
				TitleLocal.DISCIPLINE, false, "\\d+");
		inputSubjects = new BaseCollectionInputText(Subject.class,
				new Discipline(), TitleLocal.SUBJECT, TitleLocal.DISCIPLINE);
		inputCourseClass = new BaseCollectionInputText(CollegeClass.class,
				(CollectionManager) inputSubjects.getOwner(),
				TitleLocal.COLLEGE_CLASS, TitleLocal.DISCIPLINE);

		buttonCreate = new BaseButtonCreate(this, inputSubjects.getOwner(),
				true);

		setPreferredSize(new Dimension(400, 1));

		insert(fieldName, 0, 0, 4, 1);
		insert(fieldHourlyLoad, 0, 1, 2, 1);
		insert(inputSubjects, 0, 2, 4, 4);
		insert(inputCourseClass, 0, 12, 4, 4);
		insert(buttonCreate, 0, 16, 4, 1);
	}

	@Override
	public void goBack(Persister persister) {
		baseGUI.setPersister(persister);
		frame.removePanel(DisciplineCreateView.this);
		frame.setTitle(TitleLocal.CREATE_COURSE);
	}

}
