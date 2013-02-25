package com.thingtrack.konekti.view.web.form.field;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.EmployeeAgent;
import com.thingtrack.konekti.view.web.form.selector.EmployeeAgentSelectorWindow;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class EmployeeAgentSelectorField extends CustomField {
	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private Button btnSearch;
	@AutoGenerated
	private TextField employeeAgentField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private EmployeeAgent employeeAgent;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public EmployeeAgentSelectorField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		employeeAgentField.setNullRepresentation("");
		employeeAgentField.setRequiredError(employeeAgentField.getCaption() + " es un campo requerido");
		
		btnSearch.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					@SuppressWarnings("unused")
					EmployeeAgentSelectorWindow windowDialog = new EmployeeAgentSelectorWindow(getApplication().getMainWindow(), employeeAgent, new EmployeeAgentSelectorWindow.CloseWindowDialogListener() {
					    public void windowDialogClose(EmployeeAgentSelectorWindow.CloseWindowDialogEvent event) {
					    	if (event.getDialogResult() != EmployeeAgentSelectorWindow.DialogResult.SELECT)
					    		return ;
					    	
					    	employeeAgent = event.getDomainEntity();
					    	employeeAgentField.setValue(employeeAgent.getName() + employeeAgent.getSurname());
					    }
		
					});
				} catch (Exception e) {
					throw new RuntimeException("¡No se pudo abrir el selector de personal!", e);
				}
			}
		});
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		employeeAgent = (EmployeeAgent) newDataSource.getValue();

		if (employeeAgent != null)
			employeeAgentField.setValue(employeeAgent.getName());
		
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {
		return employeeAgent;
	}
	
	@Override
	public Class<?> getType() {
		return EmployeeAgent.class;
		
	}
	
	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("28px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("28px");
		
		// holeField
		employeeAgentField = new TextField();
		employeeAgentField.setImmediate(false);
		employeeAgentField.setWidth("150px");
		employeeAgentField.setHeight("-1px");
		mainLayout.addComponent(employeeAgentField);
		
		// btnSearch
		btnSearch = new Button();
		btnSearch.setIcon(new ThemeResource("../konekti/images/icons/container-module/magnifier-zoom.png"));
		btnSearch.setImmediate(true);
		btnSearch.setWidth("-1px");
		btnSearch.setHeight("-1px");
		mainLayout.addComponent(btnSearch);
		mainLayout.setExpandRatio(btnSearch, 1.0f);
		
		return mainLayout;
	}

}
