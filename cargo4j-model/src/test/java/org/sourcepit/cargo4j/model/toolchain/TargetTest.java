package org.sourcepit.cargo4j.model.toolchain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sourcepit.cargo4j.model.toolchain.Target;

public class TargetTest {

	@Test
	public void test() {
		Target target = Target.parse("x86_64-linux-gnu");
		assertEquals("x86_64", target.getArch());
		assertEquals(false, target.getVendor().isPresent());
		assertEquals("linux-gnu", target.getOs());
		assertEquals("x86_64-linux-gnu", target.toString());
		
		target = Target.parse("i686-apple-darwin");
		assertEquals("i686", target.getArch());
		assertEquals(false, target.getVendor().isPresent());
		assertEquals("apple-darwin", target.getOs());
		assertEquals("i686-apple-darwin", target.toString());
		
		target = Target.parse("x86_64-pc-windows-msvc");
		assertEquals("x86_64", target.getArch());
		assertEquals(true, target.getVendor().isPresent());
		assertEquals("pc", target.getVendor().get());
		assertEquals("windows-msvc", target.getOs());
		assertEquals("x86_64-pc-windows-msvc", target.toString());
	}

}
