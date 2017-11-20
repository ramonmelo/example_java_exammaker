package gui.other;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import other.LoginAcess;
import util.gui.button.BaseButton;
import util.gui.fields.BaseField;
import util.gui.fields.BaseFieldPassword;
import util.gui.look.BaseColors;
import util.gui.look.Icons;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private final BaseField fieldUser;

	private final BaseFieldPassword fieldPassword;

	private GridBagConstraints constraints;

	private final JPanel panelButtons;
	private final BaseButton buttonLogin;
	private final BaseButton buttonExit;

	private final String user = "Usuário";
	private final String pass = "Senha";
	private final String login = "Login";
	private final Icons icons = new Icons();

	public Login() {
		setUndecorated(true);
		setLayout(new GridBagLayout());
		setBackground(BaseColors.colorBackground);
		setForeground(BaseColors.colorBackground);

		fieldUser = new BaseField(user, 20, login, false);
		fieldPassword = new BaseFieldPassword(pass, 20, login);

		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());
		panelButtons.setBackground(BaseColors.colorBackground);

		buttonLogin = new BaseButton("Login", icons.getOther("decrypted.png"));
		buttonExit = new BaseButton("Sair", icons.getOther("encrypted.png"));

		panelButtons.add(buttonLogin, BorderLayout.EAST);
		panelButtons.add(buttonExit, BorderLayout.WEST);

		insert(fieldUser, 0, 0, 4, 1);
		insert(fieldPassword, 0, 1, 4, 1);
		insert(panelButtons, 0, 2, 4, 1);

		actions();

		getRootPane().setDefaultButton(buttonLogin);
		pack();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void actions() {
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = fieldUser.getText();
				String pass = new String(fieldPassword.getPassword());

				if (!LoginAcess.loginTest(user, pass)) {
					JOptionPane.showMessageDialog(Login.this,
							"Nome e/ou Senha incorretos", "",
							JOptionPane.WARNING_MESSAGE, icons.getWarn());
				} else {
					setVisible(false);
					dispose();
				}
			}
		});

		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ops = { "Sim", "Não" };

				int resposta = JOptionPane.showOptionDialog(Login.this,
						"Deseja realmente sair?", "Sair",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, icons.getExitBig(), ops,
						1);

				if (resposta == 0) {
					System.exit(0);
				}
			}
		});
	}

	public void insert(Component component, int x, int y, int width, int height) {
		constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;

		constraints.fill = GridBagConstraints.HORIZONTAL;

		add(component, constraints);
	}
}
