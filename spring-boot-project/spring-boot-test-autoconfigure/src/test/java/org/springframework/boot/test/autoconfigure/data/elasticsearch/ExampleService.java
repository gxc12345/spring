/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.test.autoconfigure.data.elasticsearch;

import org.springframework.stereotype.Service;

/**
 * Example service used with {@link DataElasticsearchTest @DataElasticsearchTest} tests.
 *
 * @author Eddú Meléndez
 */
@Service
@SuppressWarnings("deprecation")
public class ExampleService {

	private final org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate elasticsearchRestTemplate;

	public ExampleService(
			org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	public ExampleDocument findById(String id) {
		return this.elasticsearchRestTemplate.get(id, ExampleDocument.class);
	}

}
