package gui.create;

import interfaces.CourseUtil;
import interfaces.TitleLocal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import beans.Course;
import beans.Discipline;

import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButton;
import util.gui.button.BaseButtonCreate;
import util.gui.fields.BaseCollectionInputComboBox;
import util.gui.fields.BaseField;
import util.gui.look.Icons;
import util.gui.other.BaseGUI;
import util.other.abstracts.CollectionManager;
import util.other.annotations.IDField;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class CourseCreateView extends BaseGUI implements CourseUtil {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("quantityOfModules")
	private final BaseField fieldQuantityOfModules;

	private final BaseCollectionInputComboBox inputComboBoxDiscipline;

	private final BaseButton buttonCreateDiscipline;
	private final BeguinningFrame frame;

	private final BaseButtonCreate buttonCreate;

	private final Icons icons = new Icons();

	public CourseCreateView(BeguinningFrame frame, String title) {
		super(frame, title);
		this.frame = frame;

		buttonCreateDiscipline = new BaseButton("Cadastrar disciplina", icons
				.getOther("discipline.png"));

		fieldName = new BaseField("Nome", 20, TitleLocal.COURSE, false);
		fieldQuantityOfModules = new BaseField("Quantidade de Modulos", 20,
				TitleLocal.COURSE, false, "\\d+");
		inputComboBoxDiscipline = new BaseCollectionInputComboBox(
				Discipline.class, new Course(), TitleLocal.DISCIPLINE,
				TitleLocal.COURSE, false);

		buttonCreate = new BaseButtonCreate(this, inputComboBoxDiscipline
				.getOwner(), false);

		insert(fieldName, 0, 0, 4, 1);
		insert(fieldQuantityOfModules, 0, 1, 4, 1);
		insert(buttonCreateDiscipline, 0, 2, 4, 1);
		insert(inputComboBoxDiscipline, 0, 6, 4, 4);
		insert(buttonCreate, 0, 10, 4, 1);

		actions();
	}

	private void actions() {
		buttonCreateDiscipline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addPanelRight(new DisciplineCreateView(frame,
						TitleLocal.CREATE_DISCIPLINE, CourseCreateView.this));
			}
		});
	}

	@Override
	public void goBack(Persister persister) {
	}

	public void setPersister(Persister persister) {
		inputComboBoxDiscipline
				.addCollectionManager((CollectionManager) persister);
	}
}
