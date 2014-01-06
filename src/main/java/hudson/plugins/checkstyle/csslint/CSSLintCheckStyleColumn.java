package hudson.plugins.checkstyle.csslint;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

import hudson.plugins.analysis.views.WarningsCountColumn;

import hudson.views.ListViewColumnDescriptor;

/**
 * A column that shows the total number of Checkstyle warnings in a job.
 */
public class CSSLintCheckStyleColumn extends WarningsCountColumn<CSSLintCheckStyleProjectAction> {
    /**
     * Creates a new instance of {@link CSSLintCheckStyleColumn}.
     */
    @DataBoundConstructor
    public CSSLintCheckStyleColumn() { // NOPMD: data binding
        super();
    }

    @Override
    protected Class<CSSLintCheckStyleProjectAction> getProjectAction() {
        return CSSLintCheckStyleProjectAction.class;
    }

    @Override
    public String getColumnCaption() {
        return Messages.Checkstyle_Warnings_ColumnHeader();
    }

    /**
     * Descriptor for the column.
     */
    @Extension
    public static class ColumnDescriptor extends ListViewColumnDescriptor {
        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return Messages.Checkstyle_Warnings_Column();
        }
    }
}
