package gui.find;

import beans.Student;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonFind;
import util.gui.fields.BaseField;
import util.gui.find.BaseGUIFind;
import util.gui.find.BaseTable;
import util.other.annotations.IDField;

@SuppressWarnings("serial")
public class StudentFindView extends BaseGUIFind {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("user")
	private final BaseField fieldUser;

	private final String name = "Nome";
	private final String user = "Usuário";

	public StudentFindView(BeguinningFrame frame, String title) {
		super(frame, title);

		BaseTable baseTable = new BaseTable(Student.class);

		fieldName = new BaseField(name, 20, TitleLocal.STUDENT, false);
		fieldUser = new BaseField(user, 20, TitleLocal.STUDENT, false);

		addAll(fieldName, fieldUser, new BaseButtonFind(this, Student.class,
				baseTable, TitleLocal.STUDENT, true, true));

		add(baseTable.getScrollTable());
	}

}
