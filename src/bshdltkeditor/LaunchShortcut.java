package bshdltkeditor;

import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.dltk.internal.debug.ui.launcher.AbstractScriptLaunchShortcut;
import bshdltkeditor.core.BshNature;

public class LaunchShortcut extends AbstractScriptLaunchShortcut {

	@Override
	protected ILaunchConfigurationType getConfigurationType() {
		return getLaunchManager().getLaunchConfigurationType("org.eclipse.dltk.sh.ui.launchConfigurationType1");
	}

	@Override
	protected String getNatureId() {
		return BshNature.BSH_NATURE;
	}

}