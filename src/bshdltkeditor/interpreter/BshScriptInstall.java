package bshdltkeditor.interpreter;

import org.eclipse.dltk.internal.launching.StandardInterpreterRunner;
import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;

import bshdltkeditor.core.BshNature;

public class BshScriptInstall extends AbstractInterpreterInstall {
	public BshScriptInstall(IInterpreterInstallType type, String id) {
		super(type, id);
	}

	@Override
	public String getNatureId() {
		return BshNature.BSH_NATURE;
	}

	@Override
	public IInterpreterRunner getInterpreterRunner(String mode) {
		return new StandardInterpreterRunner(this);
	}

}
