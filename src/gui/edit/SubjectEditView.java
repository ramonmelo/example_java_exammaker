package gui.edit;

import beans.Subject;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonEdit;
import util.gui.fields.BaseField;
import util.gui.other.BaseGUI;
import util.other.annotations.IDField;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class SubjectEditView extends BaseGUI {

	@IDField("name")
	private final BaseField fieldName;

	private final BaseButtonEdit buttonEdit;

	private final String name = "Nome";

	public SubjectEditView(BeguinningFrame frame, String title, Subject subject) {
		super(frame, title);

		fieldName = new BaseField(name, 20, TitleLocal.SUBJECT, false);

		buttonEdit = new BaseButtonEdit(this, subject);

		insert(fieldName, 0, 0, 4, 1);
		insert(buttonEdit, 0, 1, 4, 1);
	}

	@Override
	public void goBack(Persister persister) {
	}

}
