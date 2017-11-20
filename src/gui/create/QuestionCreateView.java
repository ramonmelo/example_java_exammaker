package gui.create;

import beans.Choice;
import beans.Course;
import beans.Discipline;
import beans.Subject;
import interfaces.TitleLocal;
import other.ButtonCreateQuestion;
import util.gui.begin.BeguinningFrame;
import util.gui.fields.BaseChoiceGroup;
import util.gui.fields.BaseFieldArea;
import util.gui.fields.BaseListBoxes;
import util.gui.labels.BaseLabel;
import util.gui.other.BaseGUI;
import util.other.enums.ChoiceType;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class QuestionCreateView extends BaseGUI {

	private final BaseLabel labelTitle;

	private final BaseFieldArea fieldStatement;

	private final BaseChoiceGroup choiceGroup;

	private final BaseListBoxes baseListBoxes;

	private final ButtonCreateQuestion buttonCreateQuestion;

	private final String STATEMENT = "Enunciado";

	@SuppressWarnings("unchecked")
	public QuestionCreateView(BeguinningFrame frame, String title) {
		super(frame, title);

		labelTitle = new BaseLabel(TitleLocal.QUESTION, 18);
		fieldStatement = new BaseFieldArea(STATEMENT, 26, 8,
				TitleLocal.QUESTION, false);
		choiceGroup = new BaseChoiceGroup(Choice.class, 5, 26, ChoiceType.CHECK);
		baseListBoxes = new BaseListBoxes(Course.class, Discipline.class,
				Subject.class);

		buttonCreateQuestion = new ButtonCreateQuestion(this, fieldStatement,
				choiceGroup, baseListBoxes);

		insert(labelTitle, 0, 0, 6, 1);
		insert(fieldStatement, 0, 1, 6, 6);
		insert(choiceGroup, 0, 8, 6, 1);
		insert(baseListBoxes, 0, 10, 6, 1);
		insert(buttonCreateQuestion, 0, 11, 6, 1);
	}

	@Override
	public void goBack(Persister persister) {
	}

}
