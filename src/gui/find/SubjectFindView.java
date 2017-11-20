package gui.find;

import beans.Subject;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonFind;
import util.gui.fields.BaseField;
import util.gui.find.BaseGUIFind;
import util.gui.find.BaseTable;
import util.other.annotations.IDField;

@SuppressWarnings("serial")
public class SubjectFindView extends BaseGUIFind {

	@IDField("name")
	private final BaseField fieldName;

	private final String name = "Nome";

	public SubjectFindView(BeguinningFrame frame, String title) {
		super(frame, title);

		BaseTable baseTable = new BaseTable(Subject.class);

		fieldName = new BaseField(name, 20, TitleLocal.SUBJECT, false);

		addAll(fieldName, new BaseButtonFind(this, Subject.class, baseTable,
				TitleLocal.SUBJECT, true, true));

		add(baseTable.getScrollTable());
	}
}
