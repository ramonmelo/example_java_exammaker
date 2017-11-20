package other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import beans.Choice;
import beans.Question;
import beans.Subject;

import util.gui.button.BaseButton;
import util.gui.fields.BaseChoiceGroup;
import util.gui.fields.BaseFieldArea;
import util.gui.fields.BaseListBoxes;
import util.gui.look.Icons;
import util.gui.other.BaseGUI;
import util.gui.other.BasePanel;
import util.other.BaseDAO;
import util.other.enums.ValidationReturnType;
import util.other.interfaces.BaseInput;
import util.other.interfaces.SelectChoice;

@SuppressWarnings("serial")
public class ButtonCreateQuestion extends BasePanel {

	private final String errorMsg = "Impossivel realizar operação:";

	private final BaseButton buttonCreate;
	private final BaseButton buttonErace;

	private final String CREATE = "Cadastrar";
	private final String ERACE = "Limpar Campos";

	private final BaseFieldArea fieldArea;
	private final BaseChoiceGroup choiceGroup;
	private final BaseListBoxes listBoxes;
	private final BaseGUI baseGUI;

	private final Icons icons = new Icons();

	public ButtonCreateQuestion(BaseGUI baseGUI, BaseFieldArea fieldArea,
			BaseChoiceGroup choiceGroup, BaseListBoxes listBoxes) {
		this.fieldArea = fieldArea;
		this.choiceGroup = choiceGroup;
		this.listBoxes = listBoxes;
		this.baseGUI = baseGUI;

		buttonCreate = new BaseButton(CREATE, icons.getCreate());
		buttonErace = new BaseButton(ERACE, icons.getErace());

		actions();

		insert(buttonCreate, 0, 0, 1, 1);
		insert(buttonErace, 1, 0, 1, 1);
	}

	private void actions() {
		buttonCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateCreate()) {
					Question question = new Question();
					List<Choice> choiceList = new ArrayList<Choice>();

					for (SelectChoice choice : choiceGroup.getChoices()) {
						((Choice) choice).setQuestion(question);
						choiceList.add((Choice) choice);
					}

					Subject subject = (Subject) listBoxes.getValue();

					question.setChoices(choiceList);
					question.setStatement(fieldArea.toString());
					question.setSubject(subject);

					subject.addQuestion(question);

					BaseDAO.create(question);
					BaseDAO.update(subject);

					clear();

					JOptionPane.showMessageDialog(baseGUI,
							"Cadastro realizado com sucesso", "Cadastro",
							JOptionPane.INFORMATION_MESSAGE, icons.getCreate());
				}
			}
		});

		buttonErace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
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

	private void clear() {
		fieldArea.clear();
		choiceGroup.clear();
	}

}
