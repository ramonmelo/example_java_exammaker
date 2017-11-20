package gui.find;

import beans.Teacher;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonFind;
import util.gui.fields.BaseField;
import util.gui.find.BaseGUIFind;
import util.gui.find.BaseTable;
import util.other.annotations.IDField;

@SuppressWarnings("serial")
public class TeacherFindView extends BaseGUIFind {

	@IDField("name")
	private final BaseField fieldName;

	@IDField("user")
	private final BaseField fieldUser;

	private final String name = "Nome";
	private final String user = "Usuário";

	public TeacherFindView(BeguinningFrame frame, String title) {
		super(frame, title);

		BaseTable baseTable = new BaseTable(Teacher.class);

		fieldName = new BaseField(name, 20, TitleLocal.TEACHER, false);
		fieldUser = new BaseField(user, 20, TitleLocal.TEACHER, false);

		addAll(fieldName, fieldUser, new BaseButtonFind(this, Teacher.class,
				baseTable, TitleLocal.TEACHER, true, true));

		add(baseTable.getScrollTable());
	}

}
