/*
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
package org.openmastery.dictionary.resources

import org.openmastery.dictionary.ComponentTest
import org.openmastery.dictionary.api.Entry
import org.openmastery.dictionary.client.DictionaryClient
import org.openmastery.testsupport.BeanCompare
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification


@ComponentTest
class DictionaryResourceSpec extends Specification {

	@Autowired
	private DictionaryClient dictionaryClient
	@Autowired
	private DictionaryResource dictionaryResource
	private BeanCompare comparator = new BeanCompare()

	def cleanup() {
		dictionaryResource.entries = [:]
	}

	def "should add entry"() {
		when:
		dictionaryClient.addEntry("tag", "tag description")

		then:
		Entry entry = dictionaryResource.entries["tag"]
		assert entry.hashtag == "tag"
		assert entry.description == "tag description"
	}

	def "should retrieve all entries"() {
		when:
		dictionaryClient.addEntry("tag1", "tag1 description")
		dictionaryClient.addEntry("tag2", "tag2 description")

		then:
		List<Entry> entries = dictionaryResource.findAll()
		comparator.assertEquals(entries, [
				new Entry("tag1", "tag1 description"),
				new Entry("tag2", "tag2 description"),
		])
	}

}
