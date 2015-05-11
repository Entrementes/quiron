package org.entrementes.quiron.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.model.builder.SpringWebApplicationBuilder;
import org.entrementes.quiron.component.SpringAnnotationParser;
import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodDependency;
import org.entrementes.quiron.model.RestResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SpringMvcInspectionService implements InspectionServce {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringMvcInspectionService.class.getCanonicalName());

	private SpringAnnotationParser annotationParser;

	private RestAPI api;

	@Autowired
	public SpringMvcInspectionService(ApplicationContext springContext,
			SpringAnnotationParser annotationParser,
			@Value("${api.version}") String version,
			@Value("${api.id}") String id) {
		this.annotationParser = annotationParser;
		LOGGER.debug("building API with annotation parsing strattegy.");
		this.api = this.annotationParser.buildApi(springContext, id, version);
	}

	@Override
	public RestInterface getApi(HttpServletRequest request) {
		return new SpringWebApplicationBuilder()
							.api(this.api)
							.request(request)
						.build();
	}

	@Override
	public RestInterfaceHealth getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResource getResource(String resourceId) {
		List<RestResource> query = this.api.getResources().stream()
				.filter(r -> r.getId().equals(resourceId))
				.collect(Collectors.toCollection(ArrayList::new));
		if (query.size() == 1) {
			return query.get(0);
		}
		if (query.size() == 0) {
			return new RestResource();
		}
		LOGGER.warn("ambigous id configuration, found: {}", query.size());
		return query.get(0);
	}

	@Override
	public RestMethod getMethod(String resourceId, String methodId) {
		List<RestMethod> query = getResource(resourceId).getMethods().stream()
											.filter(r -> r.getId().equals(methodId))
											.collect(Collectors.toCollection(ArrayList::new));
		if (query.size() == 1) {
			return query.get(0);
		}
		if (query.size() == 0) {
			return new RestMethod();
		}
		LOGGER.warn("ambigous id configuration, found: {}", query.size());
		return query.get(0);
	}

	@Override
	public RestMethodDependency getMethodDependency(String resourceId, String methodId, String dependencyId) {
		List<RestMethodDependency> query = getMethod(resourceId, methodId).getDependencies().stream()
											.filter(r -> r.getId().equals(dependencyId))
											.collect(Collectors.toCollection(ArrayList::new));
		if (query.size() == 1) {
			return query.get(0);
		}
		if (query.size() == 0) {
			return new RestMethodDependency();
		}
		LOGGER.warn("ambigous id configuration, found: {}", query.size());
		return query.get(0);
	}

}
