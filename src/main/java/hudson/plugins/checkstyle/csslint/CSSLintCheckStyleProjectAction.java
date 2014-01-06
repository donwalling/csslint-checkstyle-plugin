package hudson.plugins.checkstyle.csslint;

import hudson.model.AbstractProject;

import hudson.plugins.analysis.core.ResultAction;
import hudson.plugins.analysis.core.AbstractProjectAction;

/**
 * Entry point to visualize the Checkstyle trend graph in the project screen.
 * Drawing of the graph is delegated to the associated {@link ResultAction}.
 */
public class CSSLintCheckStyleProjectAction extends AbstractProjectAction<ResultAction<CSSLintCheckStyleResult>> {
    /**
     * Instantiates a new {@link CSSLintCheckStyleProjectAction}.
     *
     * @param project
     *            the project that owns this action
     */
    public CSSLintCheckStyleProjectAction(final AbstractProject<?, ?> project) {
        this(project, CSSLintCheckStyleResultAction.class);
    }

    /**
     * Instantiates a new {@link CSSLintCheckStyleProjectAction}.
     *
     * @param project
     *            the project that owns this action
     * @param type
     *            the result action type
     */
    public CSSLintCheckStyleProjectAction(final AbstractProject<?, ?> project,
            final Class<? extends ResultAction<CSSLintCheckStyleResult>> type) {
        super(project, type, Messages._Checkstyle_ProjectAction_Name(), Messages._Checkstyle_Trend_Name(),
                CSSLintCheckStyleDescriptor.PLUGIN_ID, CSSLintCheckStyleDescriptor.ICON_URL, CSSLintCheckStyleDescriptor.RESULT_URL);
    }
}

