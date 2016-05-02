package bshdltkeditor.text;

import org.eclipse.jface.text.IDocument;

public interface IBshPartitions {

	public final String BSH_PARTITIONING = "__bsh_partitioning";

	// Content types supplied by the shell script partitioner
	public final String COMMENT_CONTENT_TYPE = "__bsh_comment";
	
	String[] CONTENT_TYPES = new String[] { IDocument.DEFAULT_CONTENT_TYPE, COMMENT_CONTENT_TYPE };

}
