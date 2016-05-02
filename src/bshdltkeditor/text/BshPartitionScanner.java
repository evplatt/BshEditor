package bshdltkeditor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;

public class BshPartitionScanner extends RuleBasedPartitionScanner {

	public BshPartitionScanner() {
		//super();
		IToken comment = new Token(IBshPartitions.COMMENT_CONTENT_TYPE);
		
		List<IRule> rules = new ArrayList<>();
		
		EndOfLineRule commentRule = new EndOfLineRule("//", comment);
		
		rules.add(commentRule);
		
		
		IPredicateRule[] result = new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}

	
}
