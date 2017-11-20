package other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import beans.Choice;
import beans.Question;

import util.gui.button.BaseButton;
import util.gui.fields.BaseChoiceGroup;
import util.gui.fields.BaseFieldArea;
import util.gui.look.Icons;
import util.gui.other.BaseGUI;
import util.gui.other.BasePanel;
import util.other.BaseDAO;
import util.other.enums.ValidationReturnType;
import util.other.interfaces.BaseInput;
import util.other.interfaces.Persister;
import util.other.interfaces.SelectChoice;

@SuppressWarnings("serial")
public class ButtonEditQuestion extends BasePanel {
	private final String errorMsg = "Impossivel realizar operação:";

	private final BaseButton buttonUpdate;

	private final String UPDATE = "Atualizar";

	private final BaseFieldArea fieldArea;
	private final BaseChoiceGroup choiceGroup;
	private final BaseGUI baseGUI;

	private final Question question;

	private final Icons icons = new Icons();

	public ButtonEditQuestion(BaseGUI baseGUI, BaseFieldArea fieldArea,
			BaseChoiceGroup choiceGroup, Question question) {
		this.question = question;

		this.fieldArea = fieldArea;
		this.choiceGroup = choiceGroup;
		this.baseGUI = baseGUI;

		buttonUpdate = new BaseButton(UPDATE);

		actions();

		insert(buttonUpdate, 0, 0, 1, 1);
		popular();
	}

	private void actions() {
		buttonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateCreate()) {
					List<Choice> choiceList = new ArrayList<Choice>();

					List<Choice> choices = question.getChoices();
					for (Choice choice : choices) {
						BaseDAO.delete((Persister) choice);
					}

					for (SelectChoice choice : choiceGroup.getChoices()) {
						((Choice) choice).setQuestion(question);
						choiceList.add((Choice) choice);
					}

					question.setChoices(choiceList);
					question.setStatement(fieldArea.toString());

					BaseDAO.update(question);

					JOptionPane.showMessageDialog(baseGUI,
							"Atualização realizada com sucesso", "Atualização",
							JOptionPane.INFORMATION_MESSAGE, icons.getUpdate());
				}
			}
		});
	}

	private void popular() {
		fieldArea.setText(question.getStatement());
		choiceGroup.popular(new ArrayList<SelectChoice>(question.getChoices()));
	}

	private boolean validateCreate() {
		String errors = errorMsg;

		List<BaseInput> fields = baseGUI.getFields();
		for (BaseInput baseField : fields) {
			if (!((Boolean) baseField.validation(ValidationReturnType.BOOLEAN))) {
				errors += (String) baseField
						.validation(ValidationReturnType.STRING);
			}
		}

		if (!errors.equals(errorMsg)) {
			JOptionPane.showMessageDialog(this.baseGUI, errors,
					"Campos obrigatórios", JOptionPane.ERROR_MESSAGE, icons
							.getError());
			return false;
		} else {
			return true;
		}
	}
}
