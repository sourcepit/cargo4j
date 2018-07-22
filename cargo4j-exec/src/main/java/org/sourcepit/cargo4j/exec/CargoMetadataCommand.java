package org.sourcepit.cargo4j.exec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.sourcepit.cargo4j.model.Metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class CargoMetadataCommand {

	private final ObjectMapper mapper;

	public CargoMetadataCommand() {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		this.mapper = mapper;
	}

	public Metadata execute(File cargoProject) throws IOException {

		final CommandLine cmdLine = CommandLine.parse("cargo metadata");

		final ByteArrayOutputStream err = new ByteArrayOutputStream();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final PumpStreamHandler streamHandler = new PumpStreamHandler(out, err);
		
		final DefaultExecutor executor = new DefaultExecutor();
		executor.setWorkingDirectory(cargoProject);
		executor.setStreamHandler(streamHandler);

		final int exitValue = executor.execute(cmdLine);
		if (exitValue == 0) {
			return mapper.readValue(out.toByteArray(), Metadata.class);
		} else {
			final String errorMessage = new String(err.toByteArray(), "UTF-8");
			throw new IOException(errorMessage);
		}
	}
}
