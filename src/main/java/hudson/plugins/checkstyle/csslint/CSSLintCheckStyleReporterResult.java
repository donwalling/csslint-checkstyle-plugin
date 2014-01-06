package hudson.plugins.checkstyle.csslint;

import hudson.model.AbstractBuild;

import hudson.plugins.analysis.core.ParserResult;
import hudson.plugins.analysis.core.ResultAction;
import hudson.plugins.analysis.core.BuildResult;

/**
 * Represents the aggregated results of the Checkstyle analysis in m2 jobs.
 */
public class CSSLintCheckStyleReporterResult extends CSSLintCheckStyleResult {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of {@link CSSLintCheckStyleReporterResult}.
     *
     * @param build
     *            the current build as owner of this action
     * @param defaultEncoding
     *            the default encoding to be used when reading and parsing files
     * @param result
     *            the parsed result with all annotations
     * @param useStableBuildAsReference
     *            determines whether only stable builds should be used as
     *            reference builds or not
     */
    public CSSLintCheckStyleReporterResult(final AbstractBuild<?, ?> build, final String defaultEncoding, final ParserResult result,
            final boolean useStableBuildAsReference) {
        super(build, defaultEncoding, result, useStableBuildAsReference, CSSLintCheckStyleMavenResultAction.class);
    }

    @Override
    protected Class<? extends ResultAction<? extends BuildResult>> getResultActionType() {
        return CSSLintCheckStyleMavenResultAction.class;
    }
}
