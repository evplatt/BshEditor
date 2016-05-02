package bshdltkeditor.interpreter;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.core.environment.IFileHandle;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.EnvironmentVariable;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.LibraryLocation;

import bshdltkeditor.Activator;
import bshdltkeditor.core.BshNature;

public class BshScriptInstallType extends AbstractInterpreterInstallType {
	private static final String[] INTERPRETER_NAMES = { "java" };

	@Override
	protected IPath createPathFile(IDeployment deployment) {
		return null;
	}

	@Override
	public synchronized LibraryLocation[] getDefaultLibraryLocations(IFileHandle installLocation,
			EnvironmentVariable[] variables, IProgressMonitor monitor) {
		return new LibraryLocation[0];
	}

	@Override
	protected IInterpreterInstall doCreateInterpreterInstall(String id) {
		return new BshScriptInstall(this, id);
	}

	@Override
	protected ILog getLog() {
		return Activator.getDefault().getLog();
	}

	@Override
	public String getName() {
		return "Beanshell Interpreter";
	}

	@Override
	public String getNatureId() {
		return BshNature.BSH_NATURE;
	}

	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}

	@Override
	protected String[] getPossibleInterpreterNames() {
		return INTERPRETER_NAMES;
	}

}