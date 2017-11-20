package gui.edit;

import beans.Choice;
import beans.Question;
import interfaces.TitleLocal;
import other.ButtonEditQuestion;
import util.gui.begin.BeguinningFrame;
import util.gui.fields.BaseChoiceGroup;
import util.gui.fields.BaseFieldArea;
import util.gui.labels.BaseLabel;
import util.gui.other.BaseGUI;
import util.other.enums.ChoiceType;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class QuestionEditView extends BaseGUI {

	private final BaseLabel labelTitle;

	private final BaseFieldArea fieldStatement;

	private final BaseChoiceGroup choiceGroup;

	private final ButtonEditQuestion buttonEditQuestion;

	private final String STATEMENT = "Enunciado";

	public QuestionEditView(BeguinningFrame frame, String title,
			Question question) {
		super(frame, title);

		labelTitle = new BaseLabel(TitleLocal.QUESTION, 18);
		fieldStatement = new BaseFieldArea(STATEMENT, 26, 8,
				TitleLocal.QUESTION, false);
		choiceGroup = new BaseChoiceGroup(Choice.class, 5, 26, ChoiceType.CHECK);

		buttonEditQuestion = new ButtonEditQuestion(this, fieldStatement,
				choiceGroup, question);

		insert(labelTitle, 0, 0, 6, 1);
		insert(fieldStatement, 0, 1, 6, 6);
		insert(choiceGroup, 0, 8, 6, 1);
		insert(buttonEditQuestion, 0, 11, 6, 1);
	}

	@Override
	public void goBack(Persister persister) {
	}

}
