package start;

import gui.other.Login;

import java.awt.Color;

import util.gui.begin.Splash;
import util.gui.look.BaseColors;
import beans.Exam;

public class Execute {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		BaseColors.setColorBackground(Color.LIGHT_GRAY);
		BaseColors.setColorLabel(Color.BLACK);
		new Splash("Ramon Soares de Melo", "2009", Login.class, Exam.class);
	}
}