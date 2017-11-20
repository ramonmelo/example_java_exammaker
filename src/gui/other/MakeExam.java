package gui.other;

import interfaces.TitleLocal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import beans.Exam;
import beans.Question;
import beans.Student;

import util.gui.labels.BaseLabel;
import util.gui.look.BaseColors;
import util.gui.look.Icons;
import util.gui.other.BasePanel;
import util.other.BaseDAO;
import enums.ExamSituation;
import gui.begin.BeginLayerStudent;

@SuppressWarnings("serial")
public class MakeExam extends JFrame {

	private final Icons icons = new Icons();

	private final JMenuBar barOption;
	private final JMenuItem itemOver;

	private final JPanel panelSouth;
	private final BaseLabel label;
	private final JButton buttonBack;
	private final JButton buttonForward;

	private final BasePanel panelContext;

	private final Exam exam;
	private final Student student;
	private final JFrame frame;

	private final JTabbedPane tabbedPane;
	private final List<QuestionMaker> panelsQuestions = new ArrayList<QuestionMaker>();

	// Msg
	private final String overTime = "Tempo esgotado";

	public MakeExam(JFrame frameOrigin, Exam exam, Student student) {
		this.frame = frameOrigin;
		this.exam = exam;
		this.student = student;

		setLayout(new BorderLayout());
		setTitle(exam.getListOfQuestions().getTestName());

		// South
		panelSouth = new JPanel();
		panelSouth.setBackground(BaseColors.colorBackground);
		panelSouth.setLayout(new BorderLayout());

		buttonBack = new JButton("<<<");
		buttonBack.setPreferredSize(new Dimension(100, 40));

		buttonForward = new JButton(">>>");
		buttonForward.setPreferredSize(new Dimension(100, 40));

		label = new BaseLabel("", 10);

		panelSouth.add(buttonBack, BorderLayout.WEST);
		panelSouth.add(label, BorderLayout.CENTER);
		panelSouth.add(buttonForward, BorderLayout.EAST);

		// Menu
		barOption = new JMenuBar();
		itemOver = new JMenuItem("Finalizar exame");
		barOption.add(itemOver);

		setJMenuBar(barOption);

		// Context
		panelContext = new BasePanel(TitleLocal.EXAM);
		panelContext.setLayout(new BorderLayout());

		tabbedPane = new JTabbedPane(SwingConstants.LEFT,
				JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setPreferredSize(new Dimension(500, 360));

		actions();

		invalidate();
		panelContext.add(tabbedPane, BorderLayout.CENTER);
		repaint();
		validate();

		add(panelContext, BorderLayout.CENTER);
		add(panelSouth, BorderLayout.SOUTH);

		pack();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		timer(exam.getTimeTest());
	}

	private void actions() {
		// Buttons
		buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.getSelectedIndex();
				if (index != 0) {
					tabbedPane.setSelectedIndex(index - 1);
				}
			}
		});

		buttonForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tabbedPane.getTabCount();

				if (tabbedPane.getSelectedIndex() != (index - 1)) {
					tabbedPane
							.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
				}
			}
		});

		itemOver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] ops = { "Sim", "Não" };

				int answer = JOptionPane.showOptionDialog(MakeExam.this,
						"Deseja realmente finalizar o exame?", "Sair",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, icons.getExitBig(), ops,
						1);

				if (answer == 0) {
					revise();
				}
			}
		});

		// Popular
		Set<Question> questions = exam.getListOfQuestions().getQuestions();

		int i = 1;
		for (Question question : questions) {
			tabbedPane.invalidate();

			QuestionMaker questionMaker = new QuestionMaker(question);
			tabbedPane.addTab("Questão " + i, questionMaker);
			panelsQuestions.add(questionMaker);

			tabbedPane.repaint();
			tabbedPane.validate();
			i++;
		}

		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				String[] ops = { "Sim", "Não" };

				int answer = JOptionPane.showOptionDialog(MakeExam.this,
						"Deseja realmente finalizar o exame?", "Sair",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, icons.getExitBig(), ops,
						1);

				if (answer == 0) {
					revise();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}

	private void revise() {
		int total = panelsQuestions.size();
		int corrects = 0;

		for (QuestionMaker questionMaker : panelsQuestions) {
			if (questionMaker.isCorrect()) {
				corrects++;
			}
		}

		double grade = (corrects * 100) / total;

		exam.setGrade(grade);
		exam.setExamSituation(ExamSituation.FINISH);

		BaseDAO.update(exam);

		setVisible(false);
		new BeginLayerStudent(student);
		dispose();
	}

	private void timer(final int min) {
		final Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int segTotal = min * 60;

			@Override
			public void run() {
				int minutos = segTotal / 60;

				int segundos = segTotal % 60;

				String seg = "" + segundos;
				String minu = "" + minutos;

				if (minutos < 10) {
					minu = "0" + minutos;
				}
				if (segundos < 10) {
					seg = "0" + segundos;
				}

				String mostra = minu + ":" + seg;

				label.setText("Tempo restante ( " + mostra + " )");

				if (mostra.equals("00:00")) {
					this.cancel();
					timer.cancel();
					JOptionPane.showMessageDialog(frame, overTime, "",
							JOptionPane.WARNING_MESSAGE, icons.getWarn());
					revise();
				}

				segTotal--;
			}
		};
		timer.schedule(task, 0, 1000);
	}

}
