/**
 * Copyright 2017 New Iron Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openmastery.dictionary.config.config;

import com.bancvue.rest.config.ObjectMapperContextResolver;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.openmastery.logging.LoggingFilter;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;

@Slf4j
@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	@Inject
	private ApplicationContext appCtx;

	@PostConstruct
	public void initialize() {
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		register(LoggingFilter.class);
		register(CORSResponseFilter.class);
		register(ObjectMapperContextResolver.class);
	}

	/**
	 * workaround for https://java.net/jira/browse/JERSEY-2085
	 * see https://github.com/spring-projects/spring-boot/issues/1345
	 */
	@Bean
	public ResourceConfigCustomizer jersey() {
		return config -> {
			log.info("Jersey resource classes found:");
			appCtx.getBeansWithAnnotation(Path.class).forEach((name, resource) -> {
				log.info(" -> {}", resource.getClass().getName());
				config.register(resource);
			});
		};
	}

}

