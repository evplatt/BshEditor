package bshdltkeditor.core;

import org.eclipse.dltk.core.AbstractLanguageToolkit;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;

public class BshScriptLanguageToolkit extends AbstractLanguageToolkit {
	private static BshScriptLanguageToolkit toolkit;

	public static IDLTKLanguageToolkit getDefault() {
		if (toolkit == null) {
			toolkit = new BshScriptLanguageToolkit();
		}
		return toolkit;
	}

	@Override
	public String getLanguageName() {
		return "Beanshell Script";
	}

	@Override
	public String getNatureId() {
		return BshNature.BSH_NATURE;
	}

	@Override
	public String getLanguageContentType() {
		return "bshdltkeditor.content-type";
	}

}