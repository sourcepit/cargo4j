package org.sourcepit.cargo4j.exec;

import java.io.File;
import java.io.IOException;

public abstract class AbstractCommand<R> {
	
	protected final File workingDirectory;
	
	public AbstractCommand(File workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public abstract R execute() throws IOException;
}
