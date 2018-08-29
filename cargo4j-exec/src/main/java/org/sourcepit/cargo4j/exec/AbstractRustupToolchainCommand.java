package org.sourcepit.cargo4j.exec;

import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.sourcepit.cargo4j.model.toolchain.ToolchainIdentifier;

public abstract class AbstractRustupToolchainCommand<R> extends AbstractRustupCommand<R> {

	protected final ToolchainIdentifier toolchain;

	public AbstractRustupToolchainCommand(File workingDirectory, File rustupExecutable, ToolchainIdentifier toolchain) {
		super(workingDirectory, rustupExecutable);
		this.toolchain = toolchain;
	}
	
	@Override
	protected void addArguments(CommandLine cmdLine) {
		cmdLine.addArgument("run");
		cmdLine.addArgument(toolchain.toString());
	}

}
