package bshdltkeditor.prefs;

import java.io.InputStream;

import org.eclipse.dltk.internal.ui.editor.ScriptSourceViewer;
import org.eclipse.dltk.ui.preferences.AbstractScriptEditorColoringConfigurationBlock;
import org.eclipse.dltk.ui.preferences.OverlayPreferenceStore;
import org.eclipse.dltk.ui.text.IColorManager;
import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.ITextEditor;

import bshdltkeditor.Activator;
import bshdltkeditor.editor.BshDocumentSetupParticipant;
import bshdltkeditor.editor.IBshColorConstants;
import bshdltkeditor.text.IBshPartitions;

public class BshColoringConfigurationBlock extends AbstractScriptEditorColoringConfigurationBlock {

	private static final String PREVIEW_FILE_NAME = "PreviewFile.txt"; //$NON-NLS-1$

	private static final String[][] fSyntaxColorListModel = new String[][] {
		{ "Comments", IBshColorConstants.BSH_COMMENT, sCommentsCategory },
		{ "Keywords", IBshColorConstants.BSH_KEYWORD, sCoreCategory },
		{ "Strings (Quoted text)", IBshColorConstants.BSH_STRING, sCoreCategory },
		{ "Default", IBshColorConstants.BSH_DEFAULT, sCoreCategory }

	};

	public BshColoringConfigurationBlock(OverlayPreferenceStore store) {
		super(store);
	}

	@Override
	protected String[][] getSyntaxColorListModel() {
		return fSyntaxColorListModel;
	}

	@Override
	protected ProjectionViewer createPreviewViewer(Composite parent, IVerticalRuler verticalRuler,
			IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles, IPreferenceStore store) {
		return new ScriptSourceViewer(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles, store);
	}

	@Override
	protected ScriptSourceViewerConfiguration createSimpleSourceViewerConfiguration(IColorManager colorManager,
			IPreferenceStore preferenceStore, ITextEditor editor, boolean configureFormatter) {
		return new SimpleBshSourceViewerConfiguration(colorManager, preferenceStore, editor,
				IBshPartitions.BSH_PARTITIONING, true);
	}

	@Override
	protected void setDocumentPartitioning(IDocument document) {
		BshDocumentSetupParticipant participant = new BshDocumentSetupParticipant();
		participant.setup(document);
	}

	@Override
	protected InputStream getPreviewContentReader() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}

	@Override
	protected ScriptTextTools getTextTools() {
		return Activator.getDefault().getTextTools();
	}

}
