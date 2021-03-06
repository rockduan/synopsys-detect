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
package com.synopsys.integration.detectable.detectables.go.functional;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.synopsys.integration.bdio.model.Forge;
import com.synopsys.integration.bdio.model.externalid.ExternalIdFactory;
import com.synopsys.integration.detectable.detectable.codelocation.CodeLocation;
import com.synopsys.integration.detectable.detectables.go.gomod.GoModGraphParser;
import com.synopsys.integration.detectable.util.FunctionalTestFiles;
import com.synopsys.integration.detectable.util.graph.NameVersionGraphAssert;

public class GoModParserTest {
    @Test
    public void parsesAthensExample() {
        final ExternalIdFactory externalIdFactory = new ExternalIdFactory();
        final List<String> listCommand = FunctionalTestFiles.asListOfStrings("/go/gomod/gomod_list_athens.txt");
        final List<String> graphCommand = FunctionalTestFiles.asListOfStrings("/go/gomod/gomod_go_mod_graph_athens.txt");

        final GoModGraphParser goModGraphParser = new GoModGraphParser(externalIdFactory);
        final List<CodeLocation> codeLocations = goModGraphParser.parseListAndGoModGraph(listCommand, graphCommand);

        Assertions.assertEquals(1, codeLocations.size());
        final CodeLocation codeLocation = codeLocations.get(0);

        final NameVersionGraphAssert graphAssert = new NameVersionGraphAssert(Forge.GOLANG, codeLocation.getDependencyGraph());
        graphAssert.hasRootDependency("github.com/codegangsta/negroni", "v1.0.0");
        graphAssert.hasRootDependency("github.com/sirupsen/logrus", "v1.1.1");
        graphAssert.hasParentChildRelationship("github.com/sirupsen/logrus", "v1.1.1", "github.com/davecgh/go-spew", "v1.1.1");
    }
}
