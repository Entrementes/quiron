package org.entrementes.quiron.component;

import static org.junit.Assert.*;

import org.entrementes.quiron.model.RestMethodDependency;
import org.junit.Test;

public class UriBuilderTest {

	@Test
	public void must_build_full_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setHost("entrementes.org");
		dependency.setPort(7777);
		dependency.setContext("quiron");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"http://entrementes.org:7777/quiron/resources");
	}
	
	@Test
	public void must_build_hostless_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setPort(7777);
		dependency.setContext("quiron");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency), "/quiron/resources");
	}
	
	@Test
	public void must_build_portless_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setHost("entrementes.org");
		dependency.setContext("quiron");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"http://entrementes.org/quiron/resources");
	}
	
	@Test
	public void must_build_contextless_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setHost("entrementes.org");
		dependency.setPort(7777);
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"http://entrementes.org:7777/resources");
	}
	
	@Test
	public void must_build_minimal_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"resources");
	}

}
