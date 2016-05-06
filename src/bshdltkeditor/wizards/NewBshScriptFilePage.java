package bshdltkeditor.wizards;

import org.eclipse.dltk.ui.wizards.NewSourceModulePage;

public class NewBshScriptFilePage extends NewSourceModulePage {

	@Override
	protected String getPageTitle() {
		return "BeanShell Script";
	}

	@Override
	protected String getPageDescription() {
		return "Create a new BeanShell Script.";
	}

	@Override
	protected String getRequiredNature() {
		return null;
	}
}