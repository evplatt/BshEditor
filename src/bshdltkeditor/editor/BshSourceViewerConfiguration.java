package bshdltkeditor.editor;

import org.eclipse.dltk.ui.text.AbstractScriptScanner;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptPresentationReconciler;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.SingleTokenScriptScanner;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.texteditor.ITextEditor;

import bshdltkeditor.text.BshCodeScanner;
import bshdltkeditor.text.IBshPartitions;

public class BshSourceViewerConfiguration extends ScriptSourceViewerConfiguration {

	@Override
	protected ContentAssistPreference getContentAssistPreference() {
		return BshContentAssistPreference.getDefault();
	}

	// Scanners for all the various content types provided by the partitioner
	private AbstractScriptScanner fCodeScanner;
	private AbstractScriptScanner fCommentScanner;
	private AbstractScriptScanner fStringScanner;

	public BshSourceViewerConfiguration(IColorManager colorManager, IPreferenceStore preferenceStore,
			ITextEditor editor, String partitioning) {
		super(colorManager, preferenceStore, editor, partitioning);
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new ScriptPresentationReconciler();
		reconciler.setDocumentPartitioning(this.getConfiguredDocumentPartitioning(sourceViewer));

		DefaultDamagerRepairer dr;

		dr = new DefaultDamagerRepairer(this.fCodeScanner);
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(this.fCommentScanner);
		reconciler.setDamager(dr, IBshPartitions.COMMENT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IBshPartitions.COMMENT_CONTENT_TYPE);

		dr = new DefaultDamagerRepairer(this.fStringScanner);
		reconciler.setDamager(dr, IBshPartitions.STRING_CONTENT_TYPE);
		reconciler.setRepairer(dr, IBshPartitions.STRING_CONTENT_TYPE);

		return reconciler;
	}
	
	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		return new IAutoEditStrategy[] { new DefaultIndentLineAutoEditStrategy() };
	}
	
	public String[] getIndentPrefixes(ISourceViewer sourceViewer,
			String contentType) {
		return new String[] { "\t", "        " };
	}

	// This method called from base class.
	@Override
	protected void initializeScanners() {
		// This is our code scanner
		this.fCodeScanner = new BshCodeScanner(this.getColorManager(), this.fPreferenceStore);
		// This is default scanners for partitions with same color.
		this.fCommentScanner = new SingleTokenScriptScanner(this.getColorManager(), this.fPreferenceStore, IBshColorConstants.BSH_COMMENT);
		this.fStringScanner = new SingleTokenScriptScanner(this.getColorManager(), this.fPreferenceStore, IBshColorConstants.BSH_STRING);
		
	}

	
	@Override
	public boolean affectsTextPresentation(PropertyChangeEvent event) {
		return fCodeScanner.affectsBehavior(event) || fCommentScanner.affectsBehavior(event) || fStringScanner.affectsBehavior(event);
	}

	@Override
	public void handlePropertyChangeEvent(PropertyChangeEvent event) {
		if (fCodeScanner.affectsBehavior(event)) {
			fCodeScanner.adaptToPreferenceChange(event);
		}
		if (fCommentScanner.affectsBehavior(event)) {
			fCommentScanner.adaptToPreferenceChange(event);
		}
		if (fStringScanner.affectsBehavior(event)) {
			fStringScanner.adaptToPreferenceChange(event);
		}
		
	}
}
