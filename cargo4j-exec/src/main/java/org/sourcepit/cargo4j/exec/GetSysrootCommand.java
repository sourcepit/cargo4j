package org.sourcepit.cargo4j.exec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.sourcepit.cargo4j.model.toolchain.ToolchainIdentifier;

public class GetSysrootCommand extends AbstractRustupToolchainCommand<File> {

	public GetSysrootCommand(File workingDirectory, File rustupExecutable, ToolchainIdentifier toolchain) {
		super(workingDirectory, rustupExecutable, toolchain);
	}

	@Override
	protected void addArguments(CommandLine cmdLine) {
		super.addArguments(cmdLine);
		cmdLine.addArgument("rustc");
		cmdLine.addArgument("--print");
		cmdLine.addArgument("sysroot");
	}

	@Override
	protected File execute(Executor executor, CommandLine cmdLine) throws IOException {
		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PumpStreamHandler streamHandler = new PumpStreamHandler(out, err);
		executor.setStreamHandler(streamHandler);
		final int exitValue = executor.execute(cmdLine);
		if (exitValue == 0) {
			final File sysroot = new File(new String(out.toByteArray(), "UTF-8").trim());
			if (!sysroot.exists()) {
				throw new FileNotFoundException();
			}
			return sysroot;
		} else {
			final String errorMessage = new String(err.toByteArray(), "UTF-8");
			throw new IOException(errorMessage);
		}
	}
}
