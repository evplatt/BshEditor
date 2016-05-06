package bshdltkeditor.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.editors.text.EditorsUI;

import bshdltkeditor.Activator;
import bshdltkeditor.editor.IBshColorConstants;

public class BshPreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault()
				.getPreferenceStore();
		
		EditorsUI.useAnnotationsPreferencePage(store);
		EditorsUI.useQuickDiffPreferencePage(store);
		
		// Initialize DLTK default values
		PreferenceConstants.initializeDefaultValues(store);

		PreferenceConverter.setDefault(store, IBshColorConstants.BSH_COMMENT, new RGB(63, 127, 95));
		PreferenceConverter.setDefault(store, IBshColorConstants.BSH_KEYWORD, new RGB(144, 4, 86));
		PreferenceConverter.setDefault(store, IBshColorConstants.BSH_STRING, new RGB(210, 126, 0));
		
		store.setDefault(IBshColorConstants.BSH_COMMENT + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(IBshColorConstants.BSH_COMMENT + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);

		store.setDefault(IBshColorConstants.BSH_KEYWORD + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(IBshColorConstants.BSH_KEYWORD + PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);

		store.setDefault(IBshColorConstants.BSH_STRING + PreferenceConstants.EDITOR_BOLD_SUFFIX, false);
		store.setDefault(IBshColorConstants.BSH_STRING+ PreferenceConstants.EDITOR_ITALIC_SUFFIX, false);
		
		store.setDefault(PreferenceConstants.EDITOR_TAB_WIDTH, 8);
		store.setDefault(PreferenceConstants.EDITOR_SYNC_OUTLINE_ON_CURSOR_MOVE, true);
		
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR, CodeFormatterConstants.TAB);
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "8");
		store.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE,"8");
	}
	
}
