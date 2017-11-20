package gui.find;

import beans.Question;
import interfaces.TitleLocal;
import util.gui.begin.BeguinningFrame;
import util.gui.button.BaseButtonFind;
import util.gui.fields.BaseField;
import util.gui.find.BaseGUIFind;
import util.gui.find.BaseTable;
import util.other.annotations.IDField;

@SuppressWarnings("serial")
public class QuestionFindView extends BaseGUIFind {

	@IDField("statement")
	private final BaseField fieldStatement;

	private final String statement = "Enunciado";

	public QuestionFindView(BeguinningFrame frame, String title) {
		super(frame, title);

		BaseTable baseTable = new BaseTable(Question.class);

		fieldStatement = new BaseField(statement, 20, TitleLocal.QUESTION,
				false);

		addAll(fieldStatement, new BaseButtonFind(this, Question.class,
				baseTable, TitleLocal.STUDENT, false, true));
		add(baseTable.getScrollTable());
	}

}
