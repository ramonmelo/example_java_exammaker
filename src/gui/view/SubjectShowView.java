package gui.view;

import util.gui.begin.BeguinningFrame;
import util.gui.view.BaseGUIViewBase;
import util.other.interfaces.Persister;

@SuppressWarnings("serial")
public class SubjectShowView extends BaseGUIViewBase {

	public SubjectShowView(BeguinningFrame frame, String title,
			String entityName, Persister persister) {
		super(frame, title, entityName, persister);
	}

}
