package gui.find;

import beans.Course;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonFind;
import util.gui.fields.BaseField;
import util.gui.find.BaseGUIFind;
import util.gui.find.BaseTable;
import util.other.annotations.IDField;

@SuppressWarnings("serial")
public class CourseFindView extends BaseGUIFind {

	@IDField("name")
	private final BaseField fieldName;

	private final String name = "Nome";

	public CourseFindView(BeguinningFrame frame, String title) {
		super(frame, title);

		BaseTable baseTable = new BaseTable(Course.class);

		fieldName = new BaseField(name, 20, TitleLocal.COURSE, false);

		addAll(fieldName, new BaseButtonFind(this, Course.class, baseTable,
				TitleLocal.COURSE, true, true));

		add(baseTable.getScrollTable());
	}

}
