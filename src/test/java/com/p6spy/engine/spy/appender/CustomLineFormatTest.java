/**
 * P6Spy
 *
 * Copyright (C) 2002 - 2017 P6Spy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.p6spy.engine.spy.appender;

import org.junit.After;
import org.junit.Test;

import com.p6spy.engine.spy.P6SpyOptions;

import org.junit.Assert;

public class CustomLineFormatTest {

	@After
	public void after() {
		// reset formatting setting
		P6SpyOptions.getActiveInstance().setCustomLogMessageFormat(null);
	}

	@Test
	public void formatPreparedStatementWithDollarSign() {
		String customLogMessageFormat = "%(currentTime)|%(executionTime)|%(category)|connection%(connectionId)\n%(effectiveSqlSingleLine)\n%(sqlSingleLine);\n";
		P6SpyOptions.getActiveInstance().setCustomLogMessageFormat(customLogMessageFormat);
		String logMsg = new CustomLineFormat().formatMessage(0, "1", 1L, "statement",
				"select value from V$parameter where lower(name)=?",
				"select value from V$parameter where lower(name)='compatible'");

		Assert.assertTrue(logMsg.contains(
				"select value from V$parameter where lower(name)=?\nselect value from V$parameter where lower(name)='compatible';\n"));
	}

}
