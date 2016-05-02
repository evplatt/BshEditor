package bshdltkeditor.interpreter;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;

public class BshScriptInterpreterLibraryBlock extends AbstractInterpreterLibraryBlock {
	public BshScriptInterpreterLibraryBlock(AddScriptInterpreterDialog d) {
		super(d);
	}

	@Override
	protected IBaseLabelProvider getLabelProvider() {
		return new LibraryLabelProvider();
	}
}