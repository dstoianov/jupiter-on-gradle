package se.techinsight.test.feature.ex;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

// https://junit.org/junit5/docs/current/user-guide/#extensions-lifecycle-callbacks

@Slf4j
public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback {

    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL,
        ABORTED,
        FAILED,
        DISABLED;
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info(
            "Test Disabled for test {}: with reason :- {}",
            context.getDisplayName(),
            reason.orElse("No reason")
        );

        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("Test Successful for test {}: ", context.getDisplayName());

        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("Test Aborted for test {}: ", context.getDisplayName());

        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("Test Aborted for test {}: ", context.getDisplayName());

        testResultsStatus.add(TestResultStatus.FAILED);
    }

    @Override
    public void afterAll(ExtensionContext context)  {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        log.info("Test result summary for {} {}", context.getDisplayName(), summary.toString());
    }
}
