package bshdltkeditor;

import org.eclipse.jface.text.source.ISourceViewer;

public interface IdleTimerListener
{
    /**
     * This notification occurs on the Display thread within a configured
     * time period after the viewed document has changed, provided that
     * the viewer is visible at that time. If the document has not changed
     * or the viewer is not visible, the notification may, but does not have
     * to occur.
     */
	public void onEditorIdle(ISourceViewer viewer);
}
