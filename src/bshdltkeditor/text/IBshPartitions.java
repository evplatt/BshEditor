package bshdltkeditor.text;

import org.eclipse.jface.text.IDocument;

public interface IBshPartitions {

	public final String BSH_PARTITIONING = "__bsh_partitioning";

	// Content types supplied by the script partitioner
	public final String COMMENT_CONTENT_TYPE = "__bsh_comment";
	public final String STRING_CONTENT_TYPE = "__bsh_string";
	
	
	String[] CONTENT_TYPES = new String[] { IDocument.DEFAULT_CONTENT_TYPE, COMMENT_CONTENT_TYPE, STRING_CONTENT_TYPE};

}
