package bshdltkeditor.interpreter;

import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.ScriptInterpreterPreferencePage;

public class BshInterpreterPreferencePage extends ScriptInterpreterPreferencePage {

	public static final String PAGE_ID = "bshdltkeditor.preferences.interpreters";

	@Override
	public InterpretersBlock createInterpretersBlock() {
		return new BshScriptInterpretersBlock();
	}
}