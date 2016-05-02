package bshdltkeditor.editor;

import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

import bshdltkeditor.Activator;

public class BshContentAssistPreference extends ContentAssistPreference {

	private static BshContentAssistPreference instance;

	public static ContentAssistPreference getDefault() {
		if (instance == null) {
			instance = new BshContentAssistPreference();
		}
		return instance;
	}

	@Override
	protected ScriptTextTools getTextTools() {
		return Activator.getDefault().getTextTools();
	}
	
}
