package bshdltkeditor.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;

import bshdltkeditor.Activator;
import bshdltkeditor.text.BshTextTools;
import bshdltkeditor.text.IBshPartitions;



public class BshDocumentSetupParticipant implements IDocumentSetupParticipant {

	@Override
	public void setup(IDocument document) {
		BshTextTools tools = Activator.getDefault().getTextTools();
		tools.setupDocumentPartitioner(document, IBshPartitions.BSH_PARTITIONING);
	}
}
