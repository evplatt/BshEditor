package bshdltkeditor.text;

import org.eclipse.dltk.ui.text.ScriptSourceViewerConfiguration;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.ITextEditor;

import bshdltkeditor.editor.BshSourceViewerConfiguration;

public class BshTextTools extends ScriptTextTools {

	private final IPartitionTokenScanner fPartitionScanner;

	public BshTextTools(boolean autoDisposeOnDisplayDispose) {
		super(IBshPartitions.BSH_PARTITIONING, IBshPartitions.CONTENT_TYPES, autoDisposeOnDisplayDispose);
		fPartitionScanner = new BshPartitionScanner();
	}

	@Override
	public ScriptSourceViewerConfiguration createSourceViewerConfiguraton(IPreferenceStore preferenceStore,
			ITextEditor editor, String partitioning) {
		return new BshSourceViewerConfiguration(getColorManager(), preferenceStore, editor, partitioning);
	}

	@Override
	public IPartitionTokenScanner createPartitionScanner() {
		return fPartitionScanner;
	}
	
	@Override
	public IPartitionTokenScanner getPartitionScanner() {
		return fPartitionScanner;
	}
	
}
