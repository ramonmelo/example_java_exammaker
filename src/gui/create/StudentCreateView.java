package gui.create;

import beans.CollegeClass;
import beans.Registration;
import beans.Student;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonCreate;
import util.gui.fields.BaseCollectionInputComboBoxRelationship;
import util.gui.fields.BaseField;
import util.gui.fields.BaseFieldPassword;
import util.gui.other.BaseGUI;
import util.other.annotations.IDField;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class StudentCreateView extends BaseGUI {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("user")
	private final BaseField fieldUser;

	@IDField("password")
	private final BaseFieldPassword fieldPassword;

	@IDField("registrations")
	private final BaseCollectionInputComboBoxRelationship relationshipRegistration;

	private final BaseButtonCreate buttonCreate;

	// Strings
	private final String name = "Nome";
	private final String user = "Usuário";
	private final String password = "Senha";

	public StudentCreateView(BeguinningFrame frame, String title) {
		super(frame, title);

		fieldName = new BaseField(name, 20, TitleLocal.STUDENT, false);
		fieldUser = new BaseField(user, 20, TitleLocal.STUDENT, false);
		fieldPassword = new BaseFieldPassword(password, 20, TitleLocal.STUDENT);
		relationshipRegistration = new BaseCollectionInputComboBoxRelationship(
				CollegeClass.class, new Student(), Registration.class,
				TitleLocal.COLLEGE_CLASS, TitleLocal.STUDENT);

		buttonCreate = new BaseButtonCreate(this,
				(Persister) relationshipRegistration.getEntitySide(), false);

		insert(fieldName, 0, 0, 4, 1);
		insert(fieldUser, 0, 1, 4, 1);
		insert(fieldPassword, 0, 2, 4, 1);
		insert(relationshipRegistration, 0, 3, 4, 4);
		insert(buttonCreate, 0, 8, 4, 1);
	}

	@Override
	public void goBack(Persister persister) {
	}

}
