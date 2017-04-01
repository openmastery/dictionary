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
package org.openmastery.dictionary.client;

import com.bancvue.rest.client.crud.CrudClient;
import org.openmastery.dictionary.api.Entry;
import org.openmastery.dictionary.api.ResourcePaths;

import java.util.List;

public class DictionaryClient extends CrudClient<Entry, DictionaryClient> {

	public DictionaryClient(String baseUrl) {
		super(baseUrl, ResourcePaths.DICTIONARY_PATH, Entry.class);
	}

	public void addEntry(String hashtag, String description) {
		Entry entry = Entry.builder()
				.hashtag(hashtag)
				.description(description)
				.build();
		crudClientRequest.createWithPost(entry);
	}

	public List<Entry> findAll() {
		return crudClientRequest.findMany();
	}

}
