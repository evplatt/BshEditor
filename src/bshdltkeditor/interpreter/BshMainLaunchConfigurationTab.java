package bshdltkeditor.interpreter;

import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;

import bshdltkeditor.core.BshNature;

public class BshMainLaunchConfigurationTab extends MainLaunchConfigurationTab {

	public BshMainLaunchConfigurationTab(String mode) {
		super(mode);
	}

	@Override
	protected boolean breakOnFirstLinePrefEnabled(PreferencesLookupDelegate delegate) {
		return false;
	}

	@Override
	protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate) {
		return false;
	}

	@Override
	public String getNatureID() {
		return BshNature.BSH_NATURE;
	}

}
