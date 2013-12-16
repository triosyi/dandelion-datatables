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
package com.github.dandelion.datatables.core.processor.i18n;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dandelion.datatables.core.i18n.MessageResolver;
import com.github.dandelion.datatables.core.processor.AbstractTableProcessor;
import com.github.dandelion.datatables.core.util.ClassUtils;

public class MessageResolverProcessor extends AbstractTableProcessor {

	// Logger
	private static Logger logger = LoggerFactory.getLogger(MessageResolverProcessor.class);

	@Override
	@SuppressWarnings("unchecked")
	public void doProcess() {
		MessageResolver resourceProvider = null;

		if (stringifiedValue != null) {
			try {
				Class<MessageResolver> classProperty = (Class<MessageResolver>) ClassUtils.getClass(stringifiedValue);
				resourceProvider = classProperty.getDeclaredConstructor(new Class[] { HttpServletRequest.class })
						.newInstance(tableConfiguration.getRequest());

				logger.info("MessageResolver initialized with {}", resourceProvider.getClass().getSimpleName());
			} catch (Exception e) {
				logger.warn("Unable to instantiate the configured {} due to a {} exception", stringifiedValue, e.getClass()
						.getName(), e);
			}
		} else {
			logger.info("No {} configured", MessageResolver.class.getSimpleName());
		}

		tableConfiguration.setInternalMessageResolver(resourceProvider);
	}
}