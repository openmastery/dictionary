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
package org.openmastery.dictionary;

import groovyx.net.http.RESTClient;
import org.openmastery.dictionary.client.DictionaryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.URISyntaxException;

@Configuration
public class DictionaryTestConfig {

	@Value("${test-server.base_url:http://localhost}")
	private String serverBaseUrl;
	@Value("${test-server.base_url:http://localhost}:${server.port}")
	private String hostUri;

	@Bean
	public DictionaryClient dictionaryClient() {
		return new DictionaryClient(hostUri);
	}

	@Bean
	@Primary
	public RESTClient restClient() throws URISyntaxException {
		RESTClient client = new RESTClient(hostUri);
		return client;
	}

	@Bean
	public RESTClient managementRestClient(@Value("${management.port}") String managementPort) throws URISyntaxException {
		RESTClient client = new RESTClient(serverBaseUrl + ":" + managementPort);
		return client;
	}

}
