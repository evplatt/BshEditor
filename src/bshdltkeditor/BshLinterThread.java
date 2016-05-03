package bshdltkeditor;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.MarkerUtilities;

import bsh.ParseException;

public class BshLinterThread extends Thread implements IdleTimerListener {      
	private final Object lock = new Object();
	private final int waitForTermination = 1000; // millis
	
	private IResource resource;
	private IDocument document;
	private String code;
	
	private StringReaderThread srt = new StringReaderThread();
	
	public BshLinterThread()
	{
		super("BshLinterThread");       
	}
	
	/**
	 * Terminates this thread, releases resources.
	 */
	public void dispose() throws InterruptedException
	{
	    try { srt.dispose(); } catch(InterruptedException e) { }
	    interrupt();
	    join(waitForTermination);
	}
	
	public void onEditorIdle(ISourceViewer viewer)
	{
	    validate();
	}
	
	public void revalidate()
	{
	    validate();
	}
	
	public void setDocument(IResource resource, IDocument document)
	{
	    synchronized (lock)
	    {
	        this.document = document;
	        this.resource = resource;
	        
	        if (this.document != null) validate();
	    }
	}
	
	public void run()
	{
	    try { runImpl(); }
	    catch (InterruptedException e) { /* we were requested to terminate */ }
	}
	
	private void runImpl() throws InterruptedException
	{
		while (!Thread.interrupted())
	    {
	        String text;
	        synchronized (lock)
	        {
	            while (code == null) lock.wait();
	            text = code;
	            code = null;
	        }
	        
	        StringReader sr = new StringReader(text);
	        try {
	        	
	        	resource.deleteMarkers(IMarker.PROBLEM,true, IResource.DEPTH_INFINITE);
				
				Map<String,String> errors = BshLinter.lint(sr);
				System.out.println(errors);
				
		        for (Map.Entry<String, String> entry : errors.entrySet()) {
		        	Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, Status.OK, entry.getValue(), null));
		        	setMarker(Integer.parseInt(entry.getKey()),entry.getValue());
				}
	
	        } catch (IOException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}   
	        
		}
	}
	
	private void validate()
	{
	    synchronized (lock)
	    {
	        code = document.get();
	        lock.notifyAll();
	    }
	}
	
	private void setMarker(int lineNum, String msg){
	
		IMarker marker;
		try {
			marker = resource.createMarker(IMarker.PROBLEM);
			MarkerUtilities.setLineNumber(marker, lineNum);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.MESSAGE, msg);	
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}