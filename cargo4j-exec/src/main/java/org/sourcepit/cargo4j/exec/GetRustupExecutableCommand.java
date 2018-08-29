package org.sourcepit.cargo4j.exec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

public class GetRustupExecutableCommand extends AbstractCommand<File> {

	public GetRustupExecutableCommand(File workingDirectory) {
		super(workingDirectory);
	}

	@Override
	public File execute() throws IOException {
		final CommandLine cmdLine = new CommandLine("which");
		cmdLine.addArgument("rustup");

		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PumpStreamHandler streamHandler = new PumpStreamHandler(out, err);

		final DefaultExecutor executor = new DefaultExecutor();
		executor.setWorkingDirectory(workingDirectory);
		executor.setStreamHandler(streamHandler);

		final int exitValue = executor.execute(cmdLine);
		if (exitValue == 0) {
			final String path = new String(out.toByteArray(), "UTF-8").trim();
			final File rustupExecutable = new File(path);
			if (!rustupExecutable.exists()) {
				throw new FileNotFoundException();
			}
			return rustupExecutable;
		} else {
			final String errorMessage = new String(err.toByteArray(), "UTF-8");
			throw new IOException(errorMessage);
		}
	}
}
