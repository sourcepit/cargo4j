package org.sourcepit.cargo4j.exec;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

public abstract class AbstractRustupCommand<R> extends AbstractCommand<R> {

	protected final File rustupExecutable;

	public AbstractRustupCommand(File workingDirectory, File rustupExecutable) {
		super(workingDirectory);
		this.rustupExecutable = rustupExecutable;
	}

	@Override
	public R execute() throws IOException {
		final CommandLine cmdLine = new CommandLine(rustupExecutable);
		addArguments(cmdLine);

		final DefaultExecutor executor = new DefaultExecutor();
		executor.setWorkingDirectory(workingDirectory);
		
		return execute(executor, cmdLine);
	}
	
	protected abstract R execute(Executor executor, CommandLine cmdLine)throws IOException;

	protected abstract void addArguments(CommandLine cmdLine);
}
