package com.synopsys.integration.detectable.detectables.bazel.parse.dependencydetail;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synopsys.integration.detectable.detectable.executable.ExecutableOutput;
import com.synopsys.integration.detectable.detectable.executable.ExecutableRunner;
import com.synopsys.integration.detectable.detectable.executable.ExecutableRunnerException;
import com.synopsys.integration.detectable.detectables.bazel.model.BazelExternalIdExtractionFullRule;

public class DetailsQueryExecutor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ExecutableRunner executableRunner;

    public DetailsQueryExecutor(final ExecutableRunner executableRunner) {
        this.executableRunner = executableRunner;
    }

    public Optional<String> executeDependencyDetailsQuery(File workspaceDir, File bazelExe, final BazelExternalIdExtractionFullRule fullRule, final List<String> dependencyDetailsQueryArgs,
        final Map<BazelExternalIdExtractionFullRule, Exception> exceptionsGenerated) {
        ExecutableOutput dependencyDetailsQueryResults = null;
        try {
            dependencyDetailsQueryResults = executableRunner.execute(workspaceDir, bazelExe, dependencyDetailsQueryArgs);
        } catch (ExecutableRunnerException e) {
            logger.debug(String.format("Error executing bazel with args: %s: %s", dependencyDetailsQueryArgs, e.getMessage()));
            exceptionsGenerated.put(fullRule, e);
            return Optional.empty();
        }
        final int dependencyDetailsXmlQueryReturnCode = dependencyDetailsQueryResults.getReturnCode();
        final String dependencyDetailsXmlQueryOutput = dependencyDetailsQueryResults.getStandardOutput();
        logger.debug(String.format("Bazel targetDependencieDetailsQuery returned %d; output: %s", dependencyDetailsXmlQueryReturnCode, dependencyDetailsXmlQueryOutput));

        final String xml = dependencyDetailsQueryResults.getStandardOutput();
        logger.debug(String.format("Bazel query returned %d; output: %s", dependencyDetailsXmlQueryReturnCode, xml));
        return Optional.of(xml);
    }
}
