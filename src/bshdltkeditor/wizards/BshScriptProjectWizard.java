package bshdltkeditor.wizards;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.ProjectWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;

import bshdltkeditor.core.BshNature;

public class BshScriptProjectWizard extends ProjectWizard {

	public BshScriptProjectWizard() {
		setWindowTitle("New Shell Script Project");
		setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
	}

	@Override
	public String getScriptNature() {
		return BshNature.BSH_NATURE;
	}

	@Override
	public void addPages() {
		super.addPages();
		ProjectWizardFirstPage firstPage = new ProjectWizardFirstPage() {

			@Override
			protected boolean interpeterRequired() {
				return false;
			}
		};

		// First page
		firstPage.setTitle("BeanShell Project");
		firstPage.setDescription("Create a new BeanShell project.");
		addPage(firstPage);

		// Second page
		ProjectWizardSecondPage secondPage = new ProjectWizardSecondPage(firstPage);
		addPage(secondPage);
	}
}