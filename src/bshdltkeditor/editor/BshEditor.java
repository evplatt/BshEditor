package bshdltkeditor.editor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.internal.Workbench;

import bshdltkeditor.Activator;
import bshdltkeditor.BshLinterThread;
import bshdltkeditor.IdleTimer;
import bshdltkeditor.IdleTimerListener;
import bshdltkeditor.core.BshScriptLanguageToolkit;
import bshdltkeditor.text.BshTextTools;
import bshdltkeditor.text.IBshPartitions;

public class BshEditor extends ScriptEditor {

	public static final String EDITOR_CONTEXT = "#BshScriptEditorContext";
	public static final String EDITOR_ID = "bshdltkeditor.editor";
	private BshLinterThread linterThread;
	private IdleTimer idleTimer;
	
	@Override
	public void dispose(){
	
		super.dispose();
		if (linterThread != null)
			try {
				linterThread.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        if (idleTimer != null)
			try {
				idleTimer.dispose();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
	}
	
	@Override
	protected void connectPartitioningToElement(IEditorInput input, IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentExtension3 extension = (IDocumentExtension3) document;
			if (extension.getDocumentPartitioner(IBshPartitions.BSH_PARTITIONING) == null) {
				//BshDocumentSetupParticipant participant = new BshDocumentSetupParticipant();
				//participant.setup(document);
				BshTextTools tools = Activator.getDefault().getTextTools();
                tools.setupDocumentPartitioner(document, IBshPartitions.BSH_PARTITIONING);
			}
		}
	}

	@Override
	public String getEditorId() {
		return EDITOR_ID;
	}

	@Override
	public IDLTKLanguageToolkit getLanguageToolkit() {
		return BshScriptLanguageToolkit.getDefault();
	}

	@Override
	public IPreferenceStore getScriptPreferenceStore() {
		return Activator.getDefault().getPreferenceStore();
	}

	@Override
	public ScriptTextTools getTextTools() {
		return Activator.getDefault().getTextTools();
	}

	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId(EDITOR_CONTEXT);
       
		
	}
	
    public void registerIdleListener(IdleTimerListener obj)
    {
        idleTimer.addListener(obj);
    }
    
    public void doSave(IProgressMonitor monitor)
    {
        super.doSave(monitor);
        revalidateSyntax();
    }
    public void doRevertToSaved()
    {
        super.doRevertToSaved();
        revalidateSyntax();
    }
    public void doSaveAs()
    {
        super.doSaveAs();
        revalidateSyntax();
    }

    public void revalidateSyntax()
    {
        if (linterThread != null) linterThread.revalidate();
    }

    private void installSyntaxValidationThread()
    {
        //IResource resource = getResource();
        //if (resource == null) return;
        
    	ISourceViewer v = getSourceViewer();
    	
        // Always check syntax when editor is opened
        linterThread = new BshLinterThread();
        linterThread.setPriority(Thread.MIN_PRIORITY);
        linterThread.start();
        linterThread.setDocument(
            //resource,
            v.getDocument());
        
        registerIdleListener(linterThread);
        
    }
    
    public void createPartControl(Composite parent)
    {
        // Workaround for Eclipse Bug 75440 (to fix it somehow) [LeO]
        if (Workbench.getInstance().isClosing()) return;

        super.createPartControl(parent);
    
		installIdleTimer();
        installSyntaxValidationThread();
		
		if (!idleTimer.isRegistered(linterThread))
        {
            this.registerIdleListener(linterThread);
        }
        
    }
    
    private IResource getResource()
    {
        return (IResource)
            ((IAdaptable) getEditorInput()).getAdapter(IResource.class);
    }

    private void installIdleTimer()
    {
        idleTimer = new IdleTimer(getSourceViewer(), Display.getCurrent());
        idleTimer.start();
    }

}
