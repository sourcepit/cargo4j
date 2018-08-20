package org.sourcepit.cargo4j.exec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

public class CargoNewProjectCommand {

	public void execute(File cargoProject) throws IOException {

		final CommandLine cmdLine = CommandLine.parse("cargo new --bin \"" + cargoProject.getName() + "\"");

		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PumpStreamHandler streamHandler = new PumpStreamHandler(out, err);

		final File workingDirectory = cargoProject.getParentFile();
		
		workingDirectory.mkdirs();
		
		final DefaultExecutor executor = new DefaultExecutor();
		executor.setWorkingDirectory(workingDirectory);
		executor.setStreamHandler(streamHandler);

		final int exitValue = executor.execute(cmdLine);
		if (exitValue != 0) {
			final String errorMessage = new String(err.toByteArray(), "UTF-8");
			throw new IOException(errorMessage);
		}
	}
}
