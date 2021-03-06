/*
 * [The "BSD licence"]
 * Copyright (c) 2013-2014 Dandelion
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
package com.github.dandelion.datatables.core.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.dandelion.datatables.core.export.ColumnElement;
import com.github.dandelion.datatables.core.extension.Extension;

/**
 * Contains the column configuration.
 * 
 * @author Thibault Duchateau
 * @since 0.9.0
 */
public class ColumnConfiguration {

	private Map<ConfigToken<?>, Object> configurations;
	private Map<ConfigToken<?>, Object> stagingConfigurations;
	private Map<ConfigToken<?>, Extension> stagingExtension;
	private List<ColumnElement> columnElements;

	public ColumnConfiguration() {
		this.configurations = new HashMap<ConfigToken<?>, Object>();
		this.stagingConfigurations = new HashMap<ConfigToken<?>, Object>();
		this.setStagingExtension(new HashMap<ConfigToken<?>, Extension>());
	}

	public Map<ConfigToken<?>, Object> getConfigurations() {
		return configurations;
	}

	public void set(ConfigToken<?> configToken, Object object) {
		configurations.put(configToken, object);
	}

	public List<ColumnElement> getColumnElements() {
		return columnElements;
	}

	public void setColumnElements(List<ColumnElement> columnElements) {
		this.columnElements = columnElements;
	}

	public Map<ConfigToken<?>, Object> getStagingConfigurations() {
		return stagingConfigurations;
	}

	public void setStagingConfigurations(Map<ConfigToken<?>, Object> stagingConfigurations) {
		this.stagingConfigurations = stagingConfigurations;
	}

	public void setConfigurations(Map<ConfigToken<?>, Object> configurations) {
		this.configurations = configurations;
	}

	public Map<ConfigToken<?>, Extension> getStagingExtension() {
		return stagingExtension;
	}

	public void setStagingExtension(Map<ConfigToken<?>, Extension> stagingExtension) {
		this.stagingExtension = stagingExtension;
	}
}