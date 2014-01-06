package hudson.plugins.checkstyle.csslint;

import org.jvnet.localizer.Localizable;

import hudson.plugins.analysis.core.AbstractHealthDescriptor;
import hudson.plugins.analysis.core.HealthDescriptor;
import hudson.plugins.analysis.util.model.AnnotationProvider;

/**
 * A health descriptor for CheckStyle build results.
 */
public class CSSLintCheckStyleHealthDescriptor extends AbstractHealthDescriptor {
    /** Unique ID of this class. */
    private static final long serialVersionUID = -3404826986876607396L;

    /**
     * Creates a new instance of {@link CSSLintCheckStyleHealthDescriptor} based on the
     * values of the specified descriptor.
     *
     * @param healthDescriptor the descriptor to copy the values from
     */
    public CSSLintCheckStyleHealthDescriptor(final HealthDescriptor healthDescriptor) {
        super(healthDescriptor);
    }

    @Override
    protected Localizable createDescription(final AnnotationProvider result) {
        if (result.getNumberOfAnnotations() == 0) {
            return Messages._Checkstyle_ResultAction_HealthReportNoItem();
        }
        else if (result.getNumberOfAnnotations() == 1) {
            return Messages._Checkstyle_ResultAction_HealthReportSingleItem();
        }
        else {
            return Messages._Checkstyle_ResultAction_HealthReportMultipleItem(result.getNumberOfAnnotations());
        }
    }
}

