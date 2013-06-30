package de.larmic.jsf2.component.showcase;

public class TextAreaShowcaseComponent extends AbstractShowcaseComponent {

	@Override
	protected Object initValue() {
		return "value";
	}

	@Override
	public String getReadableValue() {
		return (String) this.getValue();
	}

	@Override
	public String getXHtml() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<l:textArea id=\"input\"\n");
		sb.append("        	   label=\"" + this.getLabel() + "\"\n");
		sb.append("        	   value=\"" + this.getValue() + "\"\n");
		if (this.getTooltip() != null && !"".equals(this.getTooltip())) {
			sb.append("            tooltip=\"" + this.getTooltip() + "\"\n");
		}
		sb.append("        	   readonly=\"" + this.isReadonly() + "\"\n");
		sb.append("        	   required=\"" + this.isRequired() + "\"\n");
		sb.append("        	   floating=\"" + this.isFloating() + "\"\n");
		sb.append("        	   rendered=\"" + this.isRendered() + "\">\n");

		this.createAjaxXhtml(sb, "keyup");

		if (this.isValidation()) {
			sb.append("    <f:validateLength minimum=\"2\" maximum=\"10\"/>\n");
		}
		sb.append("</l:textArea>");

		this.createOutputXhtml(sb);

		return sb.toString();
	}
}
