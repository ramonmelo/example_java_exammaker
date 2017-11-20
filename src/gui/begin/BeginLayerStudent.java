package gui.begin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import beans.Exam;
import beans.Student;

import util.gui.look.BaseColors;
import util.gui.look.Icons;
import util.gui.other.BaseGUIBottom;
import util.gui.other.BaseMenu;
import util.gui.other.BasePanel;
import enums.ExamSituation;
import gui.other.Login;
import gui.other.MakeExam;

@SuppressWarnings("serial")
public class BeginLayerStudent extends JFrame {

	private final JMenuBar menuBar;

	private final BaseMenu menuArchive;

	private final JMenuItem itemArchiveExit;
	private final JMenuItem itemArchiveLogout;

	private final Icons icons = new Icons();
	private final String SELECT_LINE = "Por favor, selecione um exame";

	private final BasePanel panelPendent;
	private final BasePanel panelFinish;

	private final DefaultListModel listModelPendent;
	private final DefaultListModel listModelFinish;

	private final JList listPendent;
	private final JList listFinish;

	private final JButton buttonMake;
	private final JButton buttonView;

	private final BasePanel panelLists;

	private final Student student;

	public BeginLayerStudent(Student student) {
		this.student = student;
		setLayout(new BorderLayout());

		menuBar = new JMenuBar();

		menuArchive = new BaseMenu("Arquivo", icons.getArchive());
		itemArchiveExit = new JMenuItem("Sair", icons.getExit());
		itemArchiveLogout = new JMenuItem("Logout", icons
				.getOther("logout.png"));

		menuArchive.add(itemArchiveExit);
		menuArchive.add(itemArchiveLogout);

		menuBar.add(menuArchive);
		setJMenuBar(menuBar);

		panelLists = new BasePanel();

		panelPendent = new BasePanel("Provas pendentes");
		panelPendent.setLayout(new BorderLayout());
		panelPendent.setPreferredSize(new Dimension(220, 140));

		listModelPendent = new DefaultListModel();
		listPendent = new JList(listModelPendent);
		listPendent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPendent.setBackground(BaseColors.colorBackground);
		listPendent.setVisibleRowCount(5);

		buttonMake = new JButton("Realizar Prova");

		panelPendent.add(listPendent, BorderLayout.CENTER);
		panelPendent.add(buttonMake, BorderLayout.SOUTH);

		panelFinish = new BasePanel("Provas realizadas");
		panelFinish.setLayout(new BorderLayout());
		panelFinish.setPreferredSize(new Dimension(220, 140));

		listModelFinish = new DefaultListModel();
		listFinish = new JList(listModelFinish);
		listFinish.setBackground(BaseColors.colorBackground);
		listFinish.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFinish.setVisibleRowCount(5);

		buttonView = new JButton("Vizualizar Resultado");

		panelFinish.add(listFinish, BorderLayout.CENTER);
		panelFinish.add(buttonView, BorderLayout.SOUTH);

		panelLists.insert(panelPendent, 0, 0, 1, 1);
		panelLists.insert(panelFinish, 1, 0, 1, 1);

		add(panelLists, BorderLayout.CENTER);
		add(new BaseGUIBottom(), BorderLayout.SOUTH);

		actions();
		toolTips();

		pack();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void actions() {
		// Arquivo
		itemArchiveExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ops = { "Sim", "Não" };

				int resposta = JOptionPane.showOptionDialog(
						BeginLayerStudent.this,
						"Deseja realmente sair do sistema?", "Sair",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, icons.getExitBig(), ops,
						1);

				if (resposta == 0) {
					System.exit(0);
				}
			}
		});

		itemArchiveLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ops = { "Sim", "Não" };

				int resposta = JOptionPane.showOptionDialog(
						BeginLayerStudent.this, "Deseja realmente sair?",
						"Sair", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, icons.getExitBig(), ops,
						1);

				if (resposta == 0) {
					BeginLayerStudent.this.setVisible(false);
					new Login();
					BeginLayerStudent.this.dispose();
				}
			}
		});

		// Contexto
		buttonMake.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Exam exam = (Exam) listPendent.getSelectedValue();

				if (exam != null) {
					new MakeExam(BeginLayerStudent.this, exam, student);
					BeginLayerStudent.this.dispose();
				} else {
					JOptionPane.showMessageDialog(BeginLayerStudent.this,
							SELECT_LINE, "", JOptionPane.WARNING_MESSAGE, icons
									.getWarn());
				}
			}
		});

		buttonView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Exam exam = (Exam) listFinish.getSelectedValue();

				if (exam != null) {
					JOptionPane.showMessageDialog(BeginLayerStudent.this, exam
							.result(), "", JOptionPane.INFORMATION_MESSAGE,
							icons.getCreate());
				} else {
					JOptionPane.showMessageDialog(BeginLayerStudent.this,
							SELECT_LINE, "", JOptionPane.WARNING_MESSAGE, icons
									.getWarn());
				}
			}
		});

		// Listas
		Set<Exam> exams = student.getExams();
		for (Exam exam : exams) {
			if (exam.getExamSituation().equals(ExamSituation.PENDENT)) {
				listModelPendent.addElement(exam);
			} else if (exam.getExamSituation().equals(ExamSituation.FINISH)) {
				listModelFinish.addElement(exam);
			}
		}
	}

	private void toolTips() {
		menuArchive.setToolTipText("Itens de arquivo");
		itemArchiveExit.setToolTipText("Sair do programa");
		itemArchiveLogout.setToolTipText("Fazer logoff do sistema");
		buttonMake.setToolTipText("Clique para responder o exame agora");
		buttonView.setToolTipText("Clique para vizualizar o resultado do exam");
		listPendent.setToolTipText("Listagem de exames pendentes");
		listPendent.setToolTipText("Listagem de exames já respondidos");
	}
}
