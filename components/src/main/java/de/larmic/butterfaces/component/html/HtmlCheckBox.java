package de.larmic.butterfaces.component.html;

import javax.el.ValueExpression;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;

@ResourceDependencies({
        @ResourceDependency(library = "css", name = "butterfaces.css", target = "head"),
        @ResourceDependency(library = "js", name = "butterfaces.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "jquery-2.1.1.min.js", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap-3.2.0.min.css", target = "head"),
        @ResourceDependency(library = "butterfaces-configurable", name = "bootstrap-3.2.0.min.js", target = "head"),
        @ResourceDependency(library = "js", name = "butterfaces-tooltip.jquery.js", target = "head")
})
@FacesComponent(HtmlCheckBox.COMPONENT_TYPE)
public class HtmlCheckBox extends HtmlSelectBooleanCheckbox implements HtmlInputComponent {

    public static final String COMPONENT_TYPE = "de.larmic.butterfaces.component.checkBox";
    public static final String COMPONENT_FAMILY = "de.larmic.butterfaces.component.family";
    public static final String RENDERER_TYPE = "de.larmic.butterfaces.component.renderkit.html_basic.CheckBoxRenderer";

    protected static final String PROPERTY_FLOATING = "floating";
    protected static final String PROPERTY_TOOLTIP = "tooltip";
    protected static final String PROPERTY_HIDE_LABEL = "hideLabel";
    protected static final String PROPERTY_STYLE_CLASS = "componentStyleClass";
    protected static final String PROPERTY_INPUT_STYLE_CLASS = "inputStyleClass";
    protected static final String PROPERTY_LABEL_STYLE_CLASS = "labelStyleClass";
    protected static final String PROPERTY_DESCRIPTION = "description";

    public HtmlCheckBox() {
        super();
        this.setRendererType(RENDERER_TYPE);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    @Override
    public String getComponentStyleClass() {
        return (String) this.getStateHelper().eval(PROPERTY_STYLE_CLASS);
    }

    public void setComponentStyleClass(final String styleClass) {
        this.updateStateHelper(PROPERTY_STYLE_CLASS, styleClass);
    }

    @Override
    public String getInputStyleClass() {
        return (String) this.getStateHelper().eval(PROPERTY_INPUT_STYLE_CLASS);
    }

    public void setInputStyleClass(final String inputStyleClass) {
        this.updateStateHelper(PROPERTY_INPUT_STYLE_CLASS, inputStyleClass);
    }

    @Override
    public String getLabelStyleClass() {
        return (String) this.getStateHelper().eval(PROPERTY_LABEL_STYLE_CLASS);
    }

    public void setLabelStyleClass(final String labelStyleClass) {
        this.updateStateHelper(PROPERTY_LABEL_STYLE_CLASS, labelStyleClass);
    }

    @Override
    public boolean getHideLabel() {
        return (Boolean) this.getStateHelper().eval(PROPERTY_HIDE_LABEL, false);
    }

    public void setHideLabel(final String hideLabel) {
        this.updateStateHelper(PROPERTY_HIDE_LABEL, hideLabel);
    }

    @Override
    public String getTooltip() {
        return (String) this.getStateHelper().eval(PROPERTY_TOOLTIP);
    }

    public void setTooltip(final String tooltip) {
        this.updateStateHelper(PROPERTY_TOOLTIP, tooltip);
    }

    @Override
    public boolean getFloating() {
        return (Boolean) this.getStateHelper().eval(PROPERTY_FLOATING, false);
    }

    public void setFloating(final Boolean floating) {
        this.updateStateHelper(PROPERTY_FLOATING, floating);
    }

    public String getDescription() {
        return (String) this.getStateHelper().eval(PROPERTY_DESCRIPTION);
    }

    public void setDescription(final String description) {
        this.updateStateHelper(PROPERTY_DESCRIPTION, description);
    }

    private void updateStateHelper(final String propertyName, final Object value) {
        this.getStateHelper().put(propertyName, value);

        final ValueExpression ve = this.getValueExpression(propertyName);

        if (ve != null) {
            ve.setValue(this.getFacesContext().getELContext(), value);
        }
    }
}
