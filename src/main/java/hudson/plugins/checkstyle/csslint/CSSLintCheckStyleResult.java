package hudson.plugins.checkstyle.csslint;

import com.thoughtworks.xstream.XStream;

import hudson.model.AbstractBuild;

import hudson.plugins.analysis.core.BuildHistory;
import hudson.plugins.analysis.core.ParserResult;
import hudson.plugins.analysis.core.ResultAction;
import hudson.plugins.analysis.core.BuildResult;
import hudson.plugins.checkstyle.parser.Warning;

/**
 * Represents the results of the Checkstyle analysis. One instance of this class
 * is persisted for each build via an XML file.
 */
public class CSSLintCheckStyleResult extends BuildResult {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of {@link CSSLintCheckStyleResult}.
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
    public CSSLintCheckStyleResult(final AbstractBuild<?, ?> build, final String defaultEncoding, final ParserResult result,
            final boolean useStableBuildAsReference) {
        this(build, defaultEncoding, result, useStableBuildAsReference, CSSLintCheckStyleResultAction.class);
    }

    /**
     * Creates a new instance of {@link CSSLintCheckStyleResult}.
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
     * @param actionType
     *            the type of the result action
     */
    protected CSSLintCheckStyleResult(final AbstractBuild<?, ?> build, final String defaultEncoding, final ParserResult result,
            final boolean useStableBuildAsReference, final Class<? extends ResultAction<CSSLintCheckStyleResult>> actionType) {
        this(build, new BuildHistory(build, actionType, useStableBuildAsReference), result, defaultEncoding, true);
    }

    CSSLintCheckStyleResult(final AbstractBuild<?, ?> build, final BuildHistory history,
            final ParserResult result, final String defaultEncoding, final boolean canSerialize) {
        super(build, history, result, defaultEncoding);

        if (canSerialize) {
            serializeAnnotations(result.getAnnotations());
        }
    }

    @Override
    public String getHeader() {
        return Messages.Checkstyle_ResultAction_Header();
    }

    @Override
    protected void configure(final XStream xstream) {
        xstream.alias("warning", Warning.class);
    }

    @Override
    public String getSummary() {
        return "CSSLint: " + createDefaultSummary(CSSLintCheckStyleDescriptor.RESULT_URL, getNumberOfAnnotations(), getNumberOfModules());
    }

    @Override
    protected String createDeltaMessage() {
        return createDefaultDeltaMessage(CSSLintCheckStyleDescriptor.RESULT_URL, getNumberOfNewWarnings(), getNumberOfFixedWarnings());
    }

    /**
     * Returns the name of the file to store the serialized annotations.
     *
     * @return the name of the file to store the serialized annotations
     */
    @Override
    protected String getSerializationFileName() {
        return "csslint-warnings.xml";
    }

    /** {@inheritDoc} */
    public String getDisplayName() {
        return Messages.Checkstyle_ProjectAction_Name();
    }

    @Override
    protected Class<? extends ResultAction<? extends BuildResult>> getResultActionType() {
        return CSSLintCheckStyleResultAction.class;
    }
}
