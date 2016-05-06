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
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import bshdltkeditor.editor.IBshColorConstants;

public class BshCodeScanner extends AbstractScriptScanner {

	public static final String[] KEYWORDS = {
		"abstract", "continue", "for", "new", "switch", "assert",
		"default", "goto", "package", "synchronized", "boolean",
		"do", "if", "private", "this", "break", "double",
		"implements", "protected", "throw", "byte", "else",
		"import", "public", "throws", "case", "enum", "instanceof",
		"return", "transient", "catch", "extends", "int", "short",
		"try", "char", "final", "interface", "static", "void",
		"class", "finally", "long", "strictfp", "volatile", "const",
		"float", "native", "super", "while"};
	
	public static class BshWordDetector implements IWordDetector {
		@Override
		public boolean isWordPart(char character) {
			return Character.isJavaIdentifierPart(character);
		}

		@Override
		public boolean isWordStart(char character) {
			return Character.isJavaIdentifierStart(character);
		}
	}

	private static String fgTokenProperties[] = new String[] { IBshColorConstants.BSH_DEFAULT, IBshColorConstants.BSH_COMMENT, IBshColorConstants.BSH_STRING, IBshColorConstants.BSH_KEYWORD };

	public BshCodeScanner(IColorManager manager, IPreferenceStore store) {
		super(manager, store);
		this.initialize();
	}

	@Override
	protected List<IRule> createRules() {
		List<IRule> rules = new ArrayList<>();
		IToken keyword = this.getToken(IBshColorConstants.BSH_KEYWORD);
		IToken comment = this.getToken(IBshColorConstants.BSH_COMMENT);
		IToken other = this.getToken(IBshColorConstants.BSH_DEFAULT);
		IToken strtok = this.getToken(IBshColorConstants.BSH_STRING);
		rules.add(new EndOfLineRule("//", comment));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		rules.add(new SingleLineRule("\"", "\"", strtok, '\\', false, true));
		rules.add(new SingleLineRule("'", "'", strtok, '\\', false, true));
		
		WordRule wordRule = new WordRule(new BshWordDetector(), other);
		for (String element : KEYWORDS) {
			wordRule.addWord(element, keyword);
		}
		rules.add(wordRule);
		
		this.setDefaultReturnToken(other);
		return rules;
	}

	@Override
	protected String[] getTokenProperties() {
		return fgTokenProperties;
	}
	
}
