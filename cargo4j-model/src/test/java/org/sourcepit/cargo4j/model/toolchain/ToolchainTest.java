package org.sourcepit.cargo4j.model.toolchain;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.sourcepit.cargo4j.model.toolchain.Target;
import org.sourcepit.cargo4j.model.toolchain.Toolchain;

public class ToolchainTest {

	@Test
	public void test() {
		Toolchain toolchain = Toolchain.parse("stable-2018-07-30-x86_64-unknown-linux-gnu");
		assertEquals("stable", toolchain.getChannel());
		assertEquals(LocalDate.parse("2018-07-30"), toolchain.getDate().get());
		assertEquals(Target.parse("x86_64-unknown-linux-gnu"), toolchain.getTarget().get());
		assertEquals("stable-2018-07-30-x86_64-unknown-linux-gnu", toolchain.toString());

		toolchain = Toolchain.parse("1.8.0-x86_64-unknown-linux-gnu");
		assertEquals("1.8.0", toolchain.getChannel());
		assertEquals(false, toolchain.getDate().isPresent());
		assertEquals(Target.parse("x86_64-unknown-linux-gnu"), toolchain.getTarget().get());
		assertEquals("1.8.0-x86_64-unknown-linux-gnu", toolchain.toString());

		toolchain = Toolchain.parse("nightly");
		assertEquals("nightly", toolchain.getChannel());
		assertEquals(false, toolchain.getDate().isPresent());
		assertEquals(false, toolchain.getTarget().isPresent());
		assertEquals("nightly", toolchain.toString());

		try {
			toolchain = Toolchain.parse(null);
			fail();
		} catch (NullPointerException e) {
		}
		try {
			toolchain = Toolchain.parse("");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

}
