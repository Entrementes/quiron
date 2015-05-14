package org.entrementes.quiron.configuration;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuironHttpConfigurationTest {

	@Test
	public void test() {
		QuironConfiguration build = new QuironConfigurationBuilder().buildDefault();
		QuironHttpConfiguration http = build.getHttpConfiguration();
		assertEquals("{\"quiron-request\":\"UUID\"}",http.getFilteredResponseFormat());
		assertEquals("quiron-control-request",http.getHttpControlRequestHeader());
		assertEquals("quiron-expected-status",http.getHttpExpectedStatusHeader());
	}

}
