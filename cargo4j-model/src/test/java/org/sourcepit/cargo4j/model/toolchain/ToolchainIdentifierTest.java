package org.sourcepit.cargo4j.model.toolchain;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.sourcepit.cargo4j.model.toolchain.Target;
import org.sourcepit.cargo4j.model.toolchain.ToolchainIdentifier;

public class ToolchainIdentifierTest {

	@Test
	public void test() {
		ToolchainIdentifier toolchain = ToolchainIdentifier.parse("stable-2018-07-30-x86_64-unknown-linux-gnu");
		assertEquals("stable", toolchain.getChannel());
		assertEquals(LocalDate.parse("2018-07-30"), toolchain.getDate().get());
		assertEquals(Target.parse("x86_64-unknown-linux-gnu"), toolchain.getTarget().get());
		assertEquals("stable-2018-07-30-x86_64-unknown-linux-gnu", toolchain.toString());

		toolchain = ToolchainIdentifier.parse("1.8.0-x86_64-unknown-linux-gnu");
		assertEquals("1.8.0", toolchain.getChannel());
		assertEquals(false, toolchain.getDate().isPresent());
		assertEquals(Target.parse("x86_64-unknown-linux-gnu"), toolchain.getTarget().get());
		assertEquals("1.8.0-x86_64-unknown-linux-gnu", toolchain.toString());

		toolchain = ToolchainIdentifier.parse("nightly");
		assertEquals("nightly", toolchain.getChannel());
		assertEquals(false, toolchain.getDate().isPresent());
		assertEquals(false, toolchain.getTarget().isPresent());
		assertEquals("nightly", toolchain.toString());

		try {
			toolchain = ToolchainIdentifier.parse(null);
			fail();
		} catch (NullPointerException e) {
		}
		try {
			toolchain = ToolchainIdentifier.parse("");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

}
