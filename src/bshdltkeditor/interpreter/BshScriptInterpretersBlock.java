package bshdltkeditor.interpreter;

import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

import bshdltkeditor.core.BshNature;

public class BshScriptInterpretersBlock extends InterpretersBlock {
	@Override
	protected AddScriptInterpreterDialog createInterpreterDialog(IEnvironment environment,
			IInterpreterInstall standin) {
		AddBshScriptInterpreterDialog dialog = new AddBshScriptInterpreterDialog(this, getShell(),
				ScriptRuntime.getInterpreterInstallTypes(getCurrentNature()), standin);
		dialog.setEnvironment(environment);
		return dialog;
	}

	@Override
	protected String getCurrentNature() {
		return BshNature.BSH_NATURE;
	}

}