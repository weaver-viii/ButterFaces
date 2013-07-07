package de.larmic.jsf2.component.showcase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import de.larmic.jsf2.component.showcase.comboBox.Foo;
import de.larmic.jsf2.component.showcase.comboBox.FooConverter;
import de.larmic.jsf2.component.showcase.comboBox.FooType;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class ComboBoxShowcaseComponent extends AbstractShowcaseComponent implements Serializable {

	private ComboBoxValueType comboBoxValueType = ComboBoxValueType.STRING;

	private final List<SelectItem> foos = new ArrayList<>();
	private final List<SelectItem> enums = new ArrayList<>();
	private final List<SelectItem> strings = new ArrayList<>();

	public ComboBoxShowcaseComponent() {
		this.initFoos();
		this.initStrings();
		this.initEnums();
	}

	@Override
	protected Object initValue() {
		return null;
	}

	@Override
	public Object getValue() {
		if (super.getValue() != null) {
			return super.getValue().toString();
		}

		return "(item is null)";
	}

	@Override
	public String getReadableValue() {
		if (super.getValue() != null) {
			if (super.getValue() instanceof Foo) {
				return ((Foo) super.getValue()).getValue();
			} else if (super.getValue() instanceof FooType) {
				return ((FooType) super.getValue()).label;
			}

			return (String) super.getValue();
		}

		return "(item is null)";
	}

	public List<SelectItem> getValues() {
		switch (this.comboBoxValueType) {
		case OBJECT:
			return this.foos;
		case ENUM:
			return this.enums;
		default:
			return this.strings;
		}
	}

	@Override
	public String getXHtml() {
		final StringBuilder sb = new StringBuilder();
		sb.append("<l:comboBox id=\"input\"\n");
		sb.append("        	   label=\"" + this.getLabel() + "\"\n");
		sb.append("        	   value=\"" + this.getValue() + "\"\n");
		if (this.getTooltip() != null && !"".equals(this.getTooltip())) {
			sb.append("        	   tooltip=\"" + this.getTooltip() + "\"\n");
		}
		sb.append("        	   readonly=\"" + this.isReadonly() + "\"\n");
		sb.append("        	   required=\"" + this.isRequired() + "\"\n");
		sb.append("        	   floating=\"" + this.isFloating() + "\"\n");
		sb.append("        	   rendered=\"" + this.isRendered() + "\">\n");

		if (this.comboBoxValueType == ComboBoxValueType.STRING) {
			sb.append("    <f:selectItem itemValue=\"#{null}\" \n");
			sb.append("                  itemLabel=\"Choose one...\"/>\n");
			sb.append("    <f:selectItem itemValue=\"2000\" \n");
			sb.append("                  itemLabel=\"Year 2000\"/>\n");
			sb.append("    <f:selectItem itemValue=\"2010\" \n");
			sb.append("                  itemLabel=\"Year 2010\"/>\n");
			sb.append("    <f:selectItem itemValue=\"2020\" \n");
			sb.append("                  itemLabel=\"Year 2020\"/>\n");
		} else if (this.comboBoxValueType == ComboBoxValueType.ENUM) {
			sb.append("    <f:selectItems value=\"#{bean.fooEnums}\"/>\n");
		} else if (this.comboBoxValueType == ComboBoxValueType.OBJECT) {
			sb.append("    <f:selectItems value=\"#{bean.fooObjects}\"/>\n");
			sb.append("    <f:converter converterId=\"fooConverter\"/>\n");
		}

		this.createAjaxXhtml(sb, "change");

		/*
		 * if (this.isValidation()) {
		 * sb.append("    <f:validateLength minimum=\"2\" maximum=\"10\"/>\n");
		 * }
		 */

		sb.append("</l:comboBox>");

		this.createOutputXhtml(sb);

		return sb.toString();
	}

	public boolean isConverterActive() {
		return this.comboBoxValueType == ComboBoxValueType.OBJECT;
	}

	public List<SelectItem> getComboBoxTypes() {
		final List<SelectItem> items = new ArrayList<SelectItem>();

		for (final ComboBoxValueType type : ComboBoxValueType.values()) {
			items.add(new SelectItem(type, type.label));
		}
		return items;
	}

	public ComboBoxValueType getComboBoxValueType() {
		return this.comboBoxValueType;
	}

	public void setComboBoxValueType(final ComboBoxValueType comboBoxValueType) {
		this.comboBoxValueType = comboBoxValueType;
	}

	private void initFoos() {
		this.foos.add(new SelectItem(null, "Choose one..."));

		for (final String key : FooConverter.fooMap.keySet()) {
			final Foo foo = FooConverter.fooMap.get(key);
			this.foos.add(new SelectItem(foo, foo.getKey()));
		}
	}

	private void initEnums() {
		this.enums.add(new SelectItem(null, "Choose one..."));

		for (final FooType fooType : FooType.values()) {
			this.enums.add(new SelectItem(fooType.label));
		}
	}

	private void initStrings() {
		this.strings.add(new SelectItem(null, "Choose one..."));
		this.strings.add(new SelectItem("2000", "Year 2000"));
		this.strings.add(new SelectItem("2010", "Year 2010"));
		this.strings.add(new SelectItem("2020", "Year 2020"));
	}
}