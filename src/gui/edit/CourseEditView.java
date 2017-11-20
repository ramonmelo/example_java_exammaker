package gui.edit;

import gui.create.DisciplineCreateView;
import interfaces.CourseUtil;
import interfaces.TitleLocal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import beans.Course;
import beans.Discipline;

import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonEdit;
import util.gui.fields.BaseCollectionInputComboBox;
import util.gui.fields.BaseField;
import util.gui.other.BaseGUI;
import util.other.abstracts.CollectionManager;
import util.other.annotations.IDField;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class CourseEditView extends BaseGUI implements CourseUtil {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("quantityOfModules")
	private final BaseField fieldQuantityOfModules;

	@IDField("disciplines")
	private final BaseCollectionInputComboBox inputComboBoxDiscipline;

	private final JButton buttonCreateDiscipline;
	private final BeguinningFrame frame;

	private final BaseButtonEdit buttonEdit;

	public CourseEditView(BeguinningFrame frame, String title, Course course) {
		super(frame, title);
		this.frame = frame;

		buttonCreateDiscipline = new JButton("Cadastrar disciplina");

		fieldName = new BaseField("Nome", 20, TitleLocal.COURSE, false);
		fieldQuantityOfModules = new BaseField("Quantidade de Modulos", 20,
				TitleLocal.COURSE, false);
		inputComboBoxDiscipline = new BaseCollectionInputComboBox(
				Discipline.class, course, TitleLocal.DISCIPLINE,
				TitleLocal.COURSE, false);

		buttonEdit = new BaseButtonEdit(this, course);

		insert(fieldName, 0, 0, 4, 1);
		insert(fieldQuantityOfModules, 0, 1, 4, 1);
		insert(buttonCreateDiscipline, 0, 2, 4, 1);
		insert(inputComboBoxDiscipline, 0, 6, 4, 4);
		insert(buttonEdit, 0, 10, 4, 1);

		actions();
	}

	private void actions() {
		buttonCreateDiscipline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addPanelRight(new DisciplineCreateView(frame,
						TitleLocal.CREATE_DISCIPLINE, CourseEditView.this));
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
