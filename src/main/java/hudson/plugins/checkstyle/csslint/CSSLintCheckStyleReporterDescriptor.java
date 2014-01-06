package hudson.plugins.checkstyle.csslint;

import hudson.Extension;

import hudson.plugins.analysis.core.ReporterDescriptor;

/**
 * Descriptor for the class {@link CSSLintCheckStyleReporter}. Used as a singleton. The
 * class is marked as public so that it can be accessed from views.
 */
@Extension(ordinal = 100, optional = true) // NOCHECKSTYLE
public class CSSLintCheckStyleReporterDescriptor extends ReporterDescriptor {
    /**
     * Creates a new instance of <code>CSSLintCheckStyleReporterDescriptor</code>.
     */
    public CSSLintCheckStyleReporterDescriptor() {
        super(CSSLintCheckStyleReporter.class, new CSSLintCheckStyleDescriptor());
    }
}
