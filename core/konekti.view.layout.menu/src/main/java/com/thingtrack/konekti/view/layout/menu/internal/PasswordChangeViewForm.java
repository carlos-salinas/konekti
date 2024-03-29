package com.thingtrack.konekti.view.layout.menu.internal;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import com.thingtrack.konekti.domain.User;

@SuppressWarnings("serial")
public class PasswordChangeViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private VerticalLayout verticalPasswordLayout;
	@AutoGenerated
	private HorizontalLayout horizontalPasswordLayout;
	@AutoGenerated
	private Button cancelPasswordChangebutton;
	@AutoGenerated
	private Button applyPasswordChangebutton;
	@AutoGenerated
	private PasswordField confirmPasswordField;
	@AutoGenerated
	private PasswordField newPasswordField;
	@AutoGenerated
	private PasswordField currentPasswordField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private User user;
	
	private PopupButton parent;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public PasswordChangeViewForm(PopupButton parent) {
		this.parent = parent;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		applyPasswordChangebutton.setEnabled(false);
		
		applyPasswordChangebutton.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// close popup panel
				PasswordChangeViewForm.this.parent.setPopupVisible(false);	
			}
		});

		cancelPasswordChangebutton.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {	
				// close popup panel
				PasswordChangeViewForm.this.parent.setPopupVisible(false);	
			}
		});
	}

	public User getUser() {
		return this.user;
		
	}
	
	public void setUser(User user) {
		this.user = user;
		
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("300px");
		mainLayout.setHeight("250px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("300px");
		setHeight("250px");
		
		// verticalLayout_1
		verticalPasswordLayout = buildVerticalLayout_1();
		mainLayout.addComponent(verticalPasswordLayout, "top:0.0px;left:0.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalPasswordLayout = new VerticalLayout();
		verticalPasswordLayout.setImmediate(false);
		verticalPasswordLayout.setWidth("100.0%");
		verticalPasswordLayout.setHeight("100.0%");
		verticalPasswordLayout.setMargin(true);
		verticalPasswordLayout.setSpacing(true);
		
		// currentPasswordField
		currentPasswordField = new PasswordField();
		currentPasswordField.setCaption("Contraseña actual");
		currentPasswordField.setImmediate(true);
		currentPasswordField.setWidth("100.0%");
		currentPasswordField.setHeight("-1px");
		verticalPasswordLayout.addComponent(currentPasswordField);
		
		// newPasswordField
		newPasswordField = new PasswordField();
		newPasswordField.setCaption("Nueva Contraseña");
		newPasswordField.setImmediate(true);
		newPasswordField.setWidth("100.0%");
		newPasswordField.setHeight("-1px");
		verticalPasswordLayout.addComponent(newPasswordField);
		
		// confirmPasswordField
		confirmPasswordField = new PasswordField();
		confirmPasswordField.setCaption("Confirmar contraseña");
		confirmPasswordField.setImmediate(true);
		confirmPasswordField.setWidth("100.0%");
		confirmPasswordField.setHeight("-1px");
		confirmPasswordField.addListener(new ValueChangeListener() {	
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (confirmPasswordField.getValue() != null)
					applyPasswordChangebutton.setEnabled(true);
				else
					applyPasswordChangebutton.setEnabled(false);
			}
		});
		
		verticalPasswordLayout.addComponent(confirmPasswordField);
		
		// horizontalLayout_1
		horizontalPasswordLayout = buildHorizontalLayout_1();
		verticalPasswordLayout.addComponent(horizontalPasswordLayout);
		verticalPasswordLayout.setExpandRatio(horizontalPasswordLayout, 1.0f);
		verticalPasswordLayout.setComponentAlignment(horizontalPasswordLayout,
				new Alignment(6));
		
		return verticalPasswordLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalPasswordLayout = new HorizontalLayout();
		horizontalPasswordLayout.setImmediate(false);
		horizontalPasswordLayout.setWidth("-1px");
		horizontalPasswordLayout.setHeight("-1px");
		horizontalPasswordLayout.setMargin(false);
		horizontalPasswordLayout.setSpacing(true);
		
		// applyPasswordChangebutton
		applyPasswordChangebutton = new Button();
		applyPasswordChangebutton.setCaption("Aplicar");
		applyPasswordChangebutton.setImmediate(false);
		applyPasswordChangebutton.setWidth("-1px");
		applyPasswordChangebutton.setHeight("-1px");
		horizontalPasswordLayout.addComponent(applyPasswordChangebutton);
		
		// cancelPasswordChangebutton
		cancelPasswordChangebutton = new Button();
		cancelPasswordChangebutton.setCaption("Cancelar");
		cancelPasswordChangebutton.setImmediate(false);
		cancelPasswordChangebutton.setWidth("-1px");
		cancelPasswordChangebutton.setHeight("-1px");
		horizontalPasswordLayout.addComponent(cancelPasswordChangebutton);
		
		return horizontalPasswordLayout;
	}

}
