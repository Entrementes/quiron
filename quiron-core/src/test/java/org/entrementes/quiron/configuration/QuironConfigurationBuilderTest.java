package org.entrementes.quiron.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class QuironConfigurationBuilderTest {

	@Test
	public void deafultConfigurationTest() throws JsonSyntaxException, JsonIOException, IOException {
		QuironConfiguration build = new QuironConfigurationBuilder().buildDefault();
		assertEquals("{\"quiron-request\":\"UUID\"}",build.getFilteredResponseFormat());
		assertEquals("quiron-control-request",build.getHttpControlRequestHeader());
		assertEquals("quiron-expected-status",build.getHttpExpectedStatusHeader());
		assertNotNull(build.getExpressionLanguage());
		assertNotNull(build.getJsonCatalog());
	}

}
