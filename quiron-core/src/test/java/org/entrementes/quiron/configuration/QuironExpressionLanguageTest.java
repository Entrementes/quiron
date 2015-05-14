package org.entrementes.quiron.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.entrementes.quiron.component.QuironExpressionLanguage;
import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class QuironExpressionLanguageTest {

	@Test
	public void defaultConfigurationTest() throws JsonSyntaxException, JsonIOException, IOException {
		QuironExpressionLanguage qel = new QuironConfigurationBuilder()
													.buildDefault()
													.getExpressionLanguage();
		assertTrue(qel.isQuironEL("${cobaia}"));
		assertTrue(qel.isQuironEL("${c0b4i4}"));
		assertTrue(qel.isQuironEL("${update_hamsifu.json}"));
		assertTrue(qel.isQuironEL("${update-hamsifu.json}"));
		assertFalse(qel.isQuironEL("{c0:::b4i4}"));
		assertFalse(qel.isQuironEL(null));
		
		assertEquals("teste",qel.unwrap("${teste}"));
	}

}
