package hudson.plugins.checkstyle.csslint;

import hudson.Extension;

import hudson.plugins.analysis.core.PluginDescriptor;

/**
 * Descriptor for the class {@link CSSLintCheckStylePublisher}.
 */
@Extension(ordinal = 100) // NOCHECKSTYLE
public final class CSSLintCheckStyleDescriptor extends PluginDescriptor {
    /** Plugin Name. */
    public static final String PLUGIN_NAME = "CSSLINT";

    /** Default Checkstyle result file name. */
    public static final String DEFAULT_FILE_NAME = "csslint-result.xml";

    /** Default Checkstyle result file pattern. */
    public static final String DEFAULT_FILE_PATTERN = "**/" + DEFAULT_FILE_NAME;

    /** The ID of this plug-in is used as URL. */
    static final String PLUGIN_ID = "csslint";

    /** The URL of the result action. */
    static final String RESULT_URL = PluginDescriptor.createResultUrlName(PLUGIN_ID);

    /** Icons prefix. */
    static final String ICON_URL_PREFIX = "/plugin/checkstyle/icons/";

    /** Icon to use for the result and project action. */
    static final String ICON_URL = ICON_URL_PREFIX + "checkstyle-24x24.png";

    /**
     * Instantiates a new find bugs descriptor.
     */
    public CSSLintCheckStyleDescriptor() {
        super(CSSLintCheckStylePublisher.class);
    }

    @Override
    public String getDisplayName() {
        return Messages.Checkstyle_Publisher_Name();
    }

    @Override
    public String getPluginName() {
        return PLUGIN_ID;
    }

    @Override
    public String getIconUrl() {
        return ICON_URL;
    }

    @Override
    public String getSummaryIconUrl() {
        return ICON_URL_PREFIX + "checkstyle-48x48.png";
    }
}