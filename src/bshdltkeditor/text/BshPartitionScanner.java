package bshdltkeditor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

import bshdltkeditor.editor.IBshColorConstants;
import bshdltkeditor.text.BshCodeScanner.BshWordDetector;

public class BshPartitionScanner extends RuleBasedPartitionScanner {

	public BshPartitionScanner() {
		
		List<IRule> rules = new ArrayList<>();

		rules.add(new SingleLineRule("\"", "\"", new Token(IBshPartitions.STRING_CONTENT_TYPE), '\\', false, true));
		rules.add(new SingleLineRule("'", "'", new Token(IBshPartitions.STRING_CONTENT_TYPE), '\\', false, true));
		rules.add(new EndOfLineRule("//", new Token(IBshPartitions.COMMENT_CONTENT_TYPE)));
		
		IPredicateRule[] result = new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}

}