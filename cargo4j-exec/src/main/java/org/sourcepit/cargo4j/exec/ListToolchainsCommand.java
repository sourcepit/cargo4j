package org.sourcepit.cargo4j.exec;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.sourcepit.cargo4j.model.toolchain.ToolchainIdentifier;

public class ListToolchainsCommand extends AbstractRustupCommand<List<ToolchainIdentifier>> {

	public ListToolchainsCommand(File workingDirectory, File rustupExecutable) {
		super(workingDirectory, rustupExecutable);
	}

	@Override
	protected void addArguments(CommandLine cmdLine) {
		cmdLine.addArgument("toolchain");
		cmdLine.addArgument("list");
	}

	@Override
	protected List<ToolchainIdentifier> execute(Executor executor, CommandLine cmdLine) throws IOException {
		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PumpStreamHandler streamHandler = new PumpStreamHandler(out, err);
		executor.setStreamHandler(streamHandler);
		final int exitValue = executor.execute(cmdLine);
		if (exitValue == 0) {
			final String result = new String(out.toByteArray(), "UTF-8").trim();
			final List<ToolchainIdentifier> toolchainIds = new ArrayList<>();
			final BufferedReader lines = new BufferedReader(new StringReader(result));
			String line = lines.readLine();
			while (line != null) {
				String[] segments = line.split(" ");
				if (segments.length > 0) {
					ToolchainIdentifier toolchainId = ToolchainIdentifier.parse(segments[0]);
					if (segments.length > 1) {
						toolchainIds.add(0, toolchainId);
					} else {
						toolchainIds.add(toolchainId);
					}
				}
				line = lines.readLine();
			}
			return toolchainIds;
		} else {
			final String errorMessage = new String(err.toByteArray(), "UTF-8");
			throw new IOException(errorMessage);
		}
	}

}
