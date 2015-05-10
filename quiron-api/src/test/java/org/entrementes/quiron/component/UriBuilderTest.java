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
		dependency.setVersion("v1");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"http://entrementes.org:7777/quiron/v1/resources");
	}
	
	@Test
	public void must_build_hostless_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setPort(7777);
		dependency.setContext("quiron");
		dependency.setVersion("v1");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency), "/quiron/v1/resources");
	}
	
	@Test
	public void must_build_portless_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setHost("entrementes.org");
		dependency.setContext("quiron");
		dependency.setVersion("v1");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"http://entrementes.org/quiron/v1/resources");
	}
	
	@Test
	public void must_build_contextless_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setHost("entrementes.org");
		dependency.setPort(7777);
		dependency.setVersion("v1");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"http://entrementes.org:7777/v1/resources");
	}
	
	@Test
	public void must_build_version_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setVersion("v1");
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"v1/resources");
	}
	
	@Test
	public void must_build_minimal_dependency_path() {
		RestMethodDependency dependency = new RestMethodDependency();
		dependency.setPath("resources");
		assertEquals(new UriBuilder().getDependencyUri(dependency),"resources");
	}

}
