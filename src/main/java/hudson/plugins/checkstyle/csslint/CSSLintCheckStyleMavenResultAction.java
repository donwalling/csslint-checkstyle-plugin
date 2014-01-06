package hudson.plugins.checkstyle.csslint;

import java.util.List;
import java.util.Map;

import hudson.maven.MavenAggregatedReport;
import hudson.maven.MavenBuild;
import hudson.maven.MavenModule;
import hudson.maven.MavenModuleSet;
import hudson.maven.MavenModuleSetBuild;

import hudson.model.Action;
import hudson.model.AbstractBuild;

import hudson.plugins.analysis.core.HealthDescriptor;
import hudson.plugins.analysis.core.ParserResult;
import hudson.plugins.analysis.core.MavenResultAction;

/**
 * A {@link CSSLintCheckStyleResultAction} for native Maven jobs. This action
 * additionally provides result aggregation for sub-modules and for the main
 * project.
 */
public class CSSLintCheckStyleMavenResultAction extends MavenResultAction<CSSLintCheckStyleResult> {
    /**
     * Creates a new instance of {@link CSSLintCheckStyleMavenResultAction}.
     *
     * @param owner
     *            the associated build of this action
     * @param healthDescriptor
     *            health descriptor to use
     * @param defaultEncoding
     *            the default encoding to be used when reading and parsing files
     * @param result
     *            the result in this build
     */
    public CSSLintCheckStyleMavenResultAction(final AbstractBuild<?, ?> owner, final HealthDescriptor healthDescriptor,
            final String defaultEncoding, final CSSLintCheckStyleResult result) {
        super(new CSSLintCheckStyleResultAction(owner, healthDescriptor, result), defaultEncoding, CSSLintCheckStyleDescriptor.PLUGIN_NAME);
    }

    /** {@inheritDoc} */
    public MavenAggregatedReport createAggregatedAction(final MavenModuleSetBuild build, final Map<MavenModule, List<MavenBuild>> moduleBuilds) {
        return new CSSLintCheckStyleMavenResultAction(build, getHealthDescriptor(), getDefaultEncoding(),
                new CSSLintCheckStyleResult(build, getDefaultEncoding(), new ParserResult(), false));
    }

    /** {@inheritDoc} */
    public Action getProjectAction(final MavenModuleSet moduleSet) {
        return new CSSLintCheckStyleProjectAction(moduleSet, CSSLintCheckStyleMavenResultAction.class);
    }

    @Override
    public Class<? extends MavenResultAction<CSSLintCheckStyleResult>> getIndividualActionType() {
        return CSSLintCheckStyleMavenResultAction.class;
    }

    @Override
    protected CSSLintCheckStyleResult createResult(final CSSLintCheckStyleResult existingResult, final CSSLintCheckStyleResult additionalResult) {
        return new CSSLintCheckStyleReporterResult(getOwner(), additionalResult.getDefaultEncoding(),
                aggregate(existingResult, additionalResult), existingResult.useOnlyStableBuildsAsReference());
    }
}

