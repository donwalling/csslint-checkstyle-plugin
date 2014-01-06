package hudson.plugins.checkstyle.csslint;

import hudson.Launcher;
import hudson.matrix.MatrixRun;
import hudson.matrix.MatrixBuild;

import hudson.model.Action;
import hudson.model.BuildListener;

import hudson.plugins.analysis.core.AnnotationsAggregator;
import hudson.plugins.analysis.core.HealthDescriptor;
import hudson.plugins.analysis.core.ParserResult;

/**
 * Aggregates {@link CSSLintCheckStyleResultAction}s of {@link MatrixRun}s into
 * {@link MatrixBuild}.
 */
public class CSSLintCheckStyleAnnotationsAggregator extends AnnotationsAggregator {
    /**
     * Creates a new instance of {@link CSSLintCheckStyleAnnotationsAggregator}.
     *
     * @param build
     *            the matrix build
     * @param launcher
     *            the launcher
     * @param listener
     *            the build listener
     * @param healthDescriptor
     *            health descriptor
     * @param defaultEncoding
     *            the default encoding to be used when reading and parsing files
     * @param useStableBuildAsReference
     *            determines whether only stable builds should be used as
     *            reference builds or not
     */
    public CSSLintCheckStyleAnnotationsAggregator(final MatrixBuild build, final Launcher launcher,
            final BuildListener listener, final HealthDescriptor healthDescriptor, final String defaultEncoding,
            final boolean useStableBuildAsReference) {
        super(build, launcher, listener, healthDescriptor, defaultEncoding, useStableBuildAsReference);
    }

    @Override
    protected Action createAction(final HealthDescriptor healthDescriptor, final String defaultEncoding, final ParserResult aggregatedResult) {
        return new CSSLintCheckStyleResultAction(build, healthDescriptor,
                new CSSLintCheckStyleResult(build, defaultEncoding, aggregatedResult, useOnlyStableBuildsAsReference()));
    }

    @Override
    protected boolean hasResult(final MatrixRun run) {
        return getAction(run) != null;
    }

    @Override
    protected CSSLintCheckStyleResult getResult(final MatrixRun run) {
        return getAction(run).getResult();
    }

    private CSSLintCheckStyleResultAction getAction(final MatrixRun run) {
        return run.getAction(CSSLintCheckStyleResultAction.class);
    }
}

