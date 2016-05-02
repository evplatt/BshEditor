package bshdltkeditor;

import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;

import bshdltkeditor.core.BshNature;

public class LaunchConfigurationDelegate extends AbstractScriptLaunchConfigurationDelegate {

	@Override
	public String getLanguageId() {
		return BshNature.BSH_NATURE;
	}

}