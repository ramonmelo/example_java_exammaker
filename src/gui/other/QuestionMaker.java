package gui.other;

import interfaces.TitleLocal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import beans.Choice;
import beans.Question;

import util.gui.fields.BaseChoiceGroup;
import util.gui.fields.BaseFieldArea;
import util.gui.look.BaseColors;
import util.other.enums.ChoiceType;
import util.other.interfaces.SelectChoice;

@SuppressWarnings("serial")
public class QuestionMaker extends JPanel {

	private final BaseFieldArea fieldStatement;

	private final BaseChoiceGroup choiceGroup;

	private final Question question;

	public QuestionMaker(Question question) {
		this.question = question;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(BaseColors.colorBackground);

		fieldStatement = new BaseFieldArea("", 26, 8, TitleLocal.QUESTION,
				false);
		fieldStatement.setEnabled(false);

		choiceGroup = new BaseChoiceGroup(Choice.class, 5, 26, ChoiceType.CHECK);
		choiceGroup.setEnabled(false);

		popular();

		add(fieldStatement);
		add(choiceGroup);
	}

	private void popular() {
		fieldStatement.setText(question.getStatement());
		choiceGroup.popularNotEnable(new ArrayList<SelectChoice>(question
				.getChoices()));
	}

	public boolean isCorrect() {
		int cont = 0;
		int target = 0;

		List<Choice> choices = question.getChoices();
		List<JToggleButton> populedToggleButton = choiceGroup
				.getPopuledToggleButton();

		for (Choice choice : choices) {
			if (choice.isMarked()) {
				target++;
			}
		}

		for (Choice choice : choices) {
			for (JToggleButton toggleButton : populedToggleButton) {
				if (toggleButton.isSelected()) {
					if (choice.getStatement().equals(toggleButton.getText())) {
						cont++;
					}
				}
			}
		}

		if (cont == target) {
			return true;
		}

		return false;
	}
}
