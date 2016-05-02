package bshdltkeditor;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;

import bsh.*;

public class BshLinterThread extends Thread implements IdleTimerListener {      
	private final Object lock = new Object();
	private final int waitForTermination = 1000; // millis
	
	//private IResource resource;
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
	
	public void setDocument(/*IResource resource,*/ IDocument document)
	{
	    synchronized (lock)
	    {
	        this.document = document;
	        //this.resource = resource;
	        
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
	    //int exceptions = 0;
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
				Map<String,String> errors = BshLinter.lint(sr);
				System.out.println(errors);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        	       
	        //TODO:  WHAT NOW????????????
	        
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
}