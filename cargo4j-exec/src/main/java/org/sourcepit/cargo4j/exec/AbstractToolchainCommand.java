package org.sourcepit.cargo4j.exec;

import org.sourcepit.cargo4j.model.toolchain.ToolchainIdentifier;

public abstract class AbstractToolchainCommand {

	protected final ToolchainIdentifier toolchain;

	public AbstractToolchainCommand(ToolchainIdentifier toolchain) {
		this.toolchain = toolchain;
	}
	
}
