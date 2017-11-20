package gui.find;

import beans.Discipline;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonFind;
import util.gui.fields.BaseField;
import util.gui.find.BaseGUIFind;
import util.gui.find.BaseTable;
import util.other.annotations.IDField;

@SuppressWarnings("serial")
public class DisciplineFindView extends BaseGUIFind {

	@IDField("name")
	private final BaseField fieldName;

	private final String name = "Nome";

	public DisciplineFindView(BeguinningFrame frame, String title) {
		super(frame, title);

		BaseTable table = new BaseTable(Discipline.class);

		fieldName = new BaseField(name, 20, TitleLocal.DISCIPLINE, false);

		addAll(fieldName, new BaseButtonFind(this, Discipline.class, table,
				TitleLocal.DISCIPLINE, true, true));

		add(table.getScrollTable());
	}

}
