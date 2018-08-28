package org.sourcepit.cargo4j.exec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.sourcepit.cargo4j.model.toolchain.ToolchainIdentifier;

public class GetSysrootCommand extends AbstractToolchainCommand {

	public GetSysrootCommand(ToolchainIdentifier toolchain) {
		super(toolchain);
	}

	public String execute(File cargoProject) throws IOException {

		final CommandLine cmdLine = new CommandLine("rustup");
		cmdLine.addArgument("run");
		cmdLine.addArgument(toolchain.toString());
		cmdLine.addArgument("rustc");
		cmdLine.addArgument("--print");
		cmdLine.addArgument("sysroot");

		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PumpStreamHandler streamHandler = new PumpStreamHandler(out, err);

		final DefaultExecutor executor = new DefaultExecutor();
		executor.setWorkingDirectory(cargoProject);
		executor.setStreamHandler(streamHandler);

		final int exitValue = executor.execute(cmdLine);
		if (exitValue == 0) {
			final String sysRoot = new String(out.toByteArray(), "UTF-8");
			return sysRoot.trim();
		} else {
			final String errorMessage = new String(err.toByteArray(), "UTF-8");
			throw new IOException(errorMessage);
		}
	}
}
