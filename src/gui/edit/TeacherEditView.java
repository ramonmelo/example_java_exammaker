package gui.edit;

import beans.CollegeClass;
import beans.Teacher;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonEdit;
import util.gui.fields.BaseCollectionInputComboBox;
import util.gui.fields.BaseField;
import util.gui.fields.BaseFieldPassword;
import util.gui.other.BaseGUI;
import util.other.annotations.IDField;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class TeacherEditView extends BaseGUI {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("user")
	private final BaseField fieldUser;

	@IDField("password")
	private final BaseFieldPassword fieldPassword;

	@IDField("collegeClasses")
	private final BaseCollectionInputComboBox comboBoxCollegeClass;

	private final BaseButtonEdit buttonEdit;

	// Strings
	private final String name = "Nome";
	private final String user = "Usuário";
	private final String password = "Senha";

	public TeacherEditView(BeguinningFrame frame, String title, Teacher teacher) {
		super(frame, title);

		fieldName = new BaseField(name, 20, TitleLocal.TEACHER, false);
		fieldUser = new BaseField(user, 20, TitleLocal.TEACHER, false);
		fieldPassword = new BaseFieldPassword(password, 20, TitleLocal.TEACHER);
		comboBoxCollegeClass = new BaseCollectionInputComboBox(
				CollegeClass.class, teacher, TitleLocal.COLLEGE_CLASS,
				TitleLocal.TEACHER, true);
		comboBoxCollegeClass.setEnabled(false);

		buttonEdit = new BaseButtonEdit(this, teacher);

		insert(fieldName, 0, 0, 4, 1);
		insert(fieldUser, 0, 1, 4, 1);
		insert(fieldPassword, 0, 2, 4, 1);
		insert(comboBoxCollegeClass, 0, 3, 4, 4);

		insert(buttonEdit, 0, 8, 4, 1);
	}

	@Override
	public void goBack(Persister persister) {
	}

}
