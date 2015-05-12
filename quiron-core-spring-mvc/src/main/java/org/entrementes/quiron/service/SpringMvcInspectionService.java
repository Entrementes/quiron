package org.entrementes.quiron.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.entrementes.quiron.component.SpringAnnotationParser;
import org.entrementes.quiron.component.SpringDrivenHealthTester;
import org.entrementes.quiron.exception.ExceptionCode;
import org.entrementes.quiron.exception.QuironException;
import org.entrementes.quiron.model.RestAPI;
import org.entrementes.quiron.model.RestAPIHealth;
import org.entrementes.quiron.model.RestInterface;
import org.entrementes.quiron.model.RestInterfaceHealth;
import org.entrementes.quiron.model.RestMethod;
import org.entrementes.quiron.model.RestMethodDependency;
import org.entrementes.quiron.model.RestResource;
import org.entrementes.quiron.model.builder.SpringWebApplicationBuilder;
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
	
	private SpringDrivenHealthTester healthTester;

	private RestAPI api;

	@Autowired
	public SpringMvcInspectionService(ApplicationContext springContext,
								SpringAnnotationParser annotationParser,
								SpringDrivenHealthTester healthTester,
								@Value("${api.version}") String version,
								@Value("${api.id}") String id) {
		this.annotationParser = annotationParser;
		LOGGER.debug("building API with annotation parsing strattegy.");
		this.api = this.annotationParser.buildApi(springContext, id, version);
		this.healthTester = healthTester;
	}

	@Override
	public RestInterface getApi(HttpServletRequest request) {
		return new SpringWebApplicationBuilder()
							.api(this.api)
							.request(request)
						.build();
	}

	@Override
	public RestInterfaceHealth getStatus(HttpServletRequest request) {
		RestAPIHealth healthCheck = this.healthTester.test(this.api);
		RestInterfaceHealth result = new RestInterfaceHealth();
		result.setApi(healthCheck);
		return result;
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
			throw new QuironException(ExceptionCode.NOT_FOUND, "resource no found.");
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
			throw new QuironException(ExceptionCode.NOT_FOUND, "method no found.");
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
			throw new QuironException(ExceptionCode.NOT_FOUND, "dependency no found.");
		}
		LOGGER.warn("ambigous id configuration, found: {}", query.size());
		return query.get(0);
	}

}
