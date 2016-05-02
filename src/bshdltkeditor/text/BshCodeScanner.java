package bshdltkeditor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WhitespaceRule;

import bshdltkeditor.editor.IBshColorConstants;

public class BshCodeScanner extends AbstractScriptScanner {

	public static class BshWordDetector implements IWordDetector {
		@Override
		public boolean isWordPart(char character) {
			return (Character.isJavaIdentifierPart(character) && (character != '$')) || (character == '-')
					|| (character == '.');
		}

		@Override
		public boolean isWordStart(char character) {
			return Character.isJavaIdentifierStart(character) && (character != 36);
		}
	}

	private static String fgTokenProperties[] = new String[] { IBshColorConstants.BSH_DEFAULT, IBshColorConstants.BSH_COMMENT};

	public BshCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		this.initialize();
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<>();
		IToken comment = this.getToken(IBshColorConstants.BSH_COMMENT);
		IToken other = this.getToken(IBshColorConstants.BSH_DEFAULT);
		rules.add(new EndOfLineRule("//", comment));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		this.setDefaultReturnToken(other);
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}
	
}
