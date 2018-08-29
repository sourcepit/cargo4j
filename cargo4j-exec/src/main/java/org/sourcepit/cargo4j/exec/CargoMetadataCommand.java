package org.sourcepit.cargo4j.exec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.sourcepit.cargo4j.model.metadata.Metadata;
import org.sourcepit.cargo4j.model.toolchain.ToolchainIdentifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class CargoMetadataCommand extends AbstractRustupToolchainCommand<Metadata> {

	private final ObjectMapper mapper;

	private final boolean resolveDependencies;

	public CargoMetadataCommand(File workingDirectory, File rustupExecutable, ToolchainIdentifier toolchain,
			boolean resolveDependencies) {
		super(workingDirectory, rustupExecutable, toolchain);
		this.resolveDependencies = resolveDependencies;
		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		this.mapper = mapper;
	}

	@Override
	protected void addArguments(CommandLine cmdLine) {
		super.addArguments(cmdLine);
		cmdLine.addArgument("cargo");
		cmdLine.addArgument("metadata");
		cmdLine.addArgument("--format-version");
		cmdLine.addArgument("1");
		if (!resolveDependencies) {
			cmdLine.addArgument("--no-deps");
		}
	}

	@Override
	protected Metadata execute(Executor executor, CommandLine cmdLine) throws IOException {

		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PumpStreamHandler streamHandler = new PumpStreamHandler(out, err);

		executor.setStreamHandler(streamHandler);
		
		try {
			executor.execute(cmdLine);
			return mapper.readValue(out.toByteArray(), Metadata.class);
		} catch (ExecuteException e) {
			final String errorMessage = new String(err.toByteArray(), "UTF-8");
			throw new IOException(errorMessage, e);
		}
	}
}
