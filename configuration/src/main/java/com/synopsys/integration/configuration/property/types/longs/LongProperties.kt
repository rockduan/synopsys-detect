/**
 * configuration
 *
 * Copyright (c) 2020 Synopsys, Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.synopsys.integration.configuration.property.types.longs

import com.synopsys.integration.configuration.parse.ListValueParser
import com.synopsys.integration.configuration.property.base.NullableProperty
import com.synopsys.integration.configuration.property.base.ValuedListProperty
import com.synopsys.integration.configuration.property.base.ValuedProperty

class NullableLongProperty(key: String) : NullableProperty<Long>(key, LongValueParser()) {
    override fun describeType(): String? = "Optional Long"
}

class LongProperty(key: String, default: Long) : ValuedProperty<Long>(key, LongValueParser(), default) {
    override fun describeDefault(): String? = default.toString()
    override fun describeType(): String? = "Long"
}

class LongListProperty(key: String, default: List<Long>) : ValuedListProperty<Long>(key, ListValueParser(LongValueParser()), default) {
    override fun describeType(): String? = "Long List"
}