/*
 * [The "BSD licence"]
 * Copyright (c) 2012 Dandelion
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of Dandelion nor the names of its contributors 
 * may be used to endorse or promote products derived from this software 
 * without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.dandelion.datatables.core.processor.feature;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;

import com.github.dandelion.datatables.core.configuration.ConfigToken;
import com.github.dandelion.datatables.core.configuration.TableConfig;
import com.github.dandelion.datatables.core.extension.Extension;
import com.github.dandelion.datatables.core.extension.feature.MultiFilterFeature;
import com.github.dandelion.datatables.core.processor.ConfigurationProcessor;
import com.github.dandelion.datatables.core.processor.MapEntry;
import com.github.dandelion.datatables.core.processor.TableProcessorBaseTest;

public class FeatureFilterSelectorProcessorTest extends TableProcessorBaseTest {

	@Override
	public ConfigurationProcessor getProcessor() {
		return new FeatureFilterSelectorProcessor();
	}

	@Test
	public void should_do_nothing_when_empty_string() {
		entry = new MapEntry<ConfigToken<?>, Object>(TableConfig.FEATURE_FILTER_SELECTOR, "");
		processor.process(entry, tableConfiguration);
		assertThat(entry.getValue()).isEqualTo("");
		assertThat(tableConfiguration.getInternalExtensions()).isNull();
	}
	
	@Test
	public void should_enable_multifiltering_when_nonempty_string() {
		entry = new MapEntry<ConfigToken<?>, Object>(TableConfig.FEATURE_FILTER_SELECTOR, "aCssSelector");
		processor.process(entry, tableConfiguration);
		assertThat(entry.getValue()).isEqualTo("aCssSelector");
		assertThat(new ArrayList<Extension>(tableConfiguration.getInternalExtensions())).contains(new MultiFilterFeature());
		assertThat(tableConfiguration.getStagingConfiguration().get(TableConfig.FEATURE_FILTER_TRIGGER)).isEqualTo("click");
	}
}