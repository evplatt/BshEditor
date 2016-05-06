package bshdltkeditor.wizards;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.NewSourceModulePage;
import org.eclipse.dltk.ui.wizards.NewSourceModuleWizard;

public class NewBshScriptFileWizard extends NewSourceModuleWizard {

	public NewBshScriptFileWizard() {
		setWindowTitle("New Shell Script");
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
	}

	@Override
	protected NewSourceModulePage createNewSourceModulePage() {
		return new NewBshScriptFilePage();
	}
}