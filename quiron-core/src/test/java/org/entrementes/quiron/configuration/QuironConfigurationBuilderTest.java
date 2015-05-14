package org.entrementes.quiron.configuration;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class QuironConfigurationBuilderTest {

	@Test
	public void deafultConfigurationTest() throws JsonSyntaxException, JsonIOException, IOException {
		QuironConfiguration build = new QuironConfigurationBuilder().buildDefault();
		assertNotNull(build.getHttpConfiguration());
		assertNotNull(build.getExpressionLanguage());
		assertNotNull(build.getJsonCatalog());
	}

}
