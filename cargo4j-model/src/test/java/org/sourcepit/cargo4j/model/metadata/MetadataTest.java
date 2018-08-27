package org.sourcepit.cargo4j.model.metadata;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.sourcepit.cargo4j.model.metadata.Metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class MetadataTest {

	@Test
	public void test() throws IOException {

		final Metadata metadata;
		try (InputStream in = ClassLoader.getSystemResourceAsStream("cargo-meta.json")) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new Jdk8Module());
			metadata = mapper.readValue(in, Metadata.class);
			
		}
		assertNotNull(metadata);
	}

}
