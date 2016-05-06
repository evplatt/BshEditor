package bshdltkeditor.core;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.AbstractDLTKUILanguageToolkit;
import org.eclipse.jface.preference.IPreferenceStore;

import bshdltkeditor.Activator;
import bshdltkeditor.interpreter.BshInterpreterPreferencePage;

public class BshUILanguageToolkit extends AbstractDLTKUILanguageToolkit {
	@Override
	public IDLTKLanguageToolkit getCoreToolkit() {
		return BshScriptLanguageToolkit.getDefault();
	}

	@Override
	public IPreferenceStore getPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

	@Override
	public String getInterpreterPreferencePage() {
		return BshInterpreterPreferencePage.PAGE_ID;
	}
}