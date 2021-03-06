/**
 * detectable
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
package com.synopsys.integration.detectable.detectables.gradle.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.synopsys.integration.bdio.graph.DependencyGraph;
import com.synopsys.integration.bdio.model.externalid.ExternalIdFactory;
import com.synopsys.integration.detectable.detectables.gradle.parsing.parse.BuildGradleParser;
import com.synopsys.integration.detectable.util.FunctionalTestFiles;

public class BuildGradleParserTest {
    @Test
    public void testGettingGraphFromSimpleBuildGradle() {
        final InputStream buildGradleInputStream = FunctionalTestFiles.asInputStream("/gradle/simple_build.gradle.txt");

        final BuildGradleParser buildGradleParser = new BuildGradleParser(new ExternalIdFactory());
        final Optional<DependencyGraph> dependencyGraph = buildGradleParser.parse(buildGradleInputStream);

        assertEquals(9, dependencyGraph.get().getRootDependencies().size());
    }
}
