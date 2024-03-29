package com.thingtrack.konekti.view.web.form;

import com.thingtrack.konekti.domain.Agent;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class UserViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Button btnRemove;
	@AutoGenerated
	private Button brnAdd;
	@AutoGenerated
	private Table rolesField;
	@AutoGenerated
	private TextField usernameField;
	@AutoGenerated
	private PasswordField passwordField;
	@AutoGenerated
	private TextField commentField;
	@AutoGenerated
	private CheckBox activeField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public UserViewForm() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
	}
	
	private void initComponents() {
		usernameField.setNullRepresentation("");
		passwordField.setNullRepresentation("");
		
		usernameField.setRequiredError(usernameField.getCaption() + " es un campo requerido");
		passwordField.setRequiredError(passwordField.getCaption() + " es un campo requerido");
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("580px");
		mainLayout.setHeight("440px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("580px");
		setHeight("440px");
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Activo");
		activeField.setImmediate(false);
		activeField.setWidth("-1px");
		activeField.setHeight("-1px");
		activeField.setTabIndex(1);
		activeField.setRequired(true);
		mainLayout.addComponent(activeField, "top:14.0px;left:507.0px;");
		
		// commentField
		commentField = new TextField();
		commentField.setCaption("Comentario");
		commentField.setImmediate(false);
		commentField.setWidth("540px");
		commentField.setHeight("160px");
		commentField.setTabIndex(3);
		mainLayout.addComponent(commentField, "top:100.0px;left:20.0px;");
		
		// passwordField
		passwordField = new PasswordField();
		passwordField.setCaption("Clave");
		passwordField.setImmediate(false);
		passwordField.setWidth("160px");
		passwordField.setHeight("-1px");
		passwordField.setTabIndex(2);
		passwordField.setRequired(true);
		mainLayout.addComponent(passwordField, "top:56.0px;left:20.0px;");
		
		// usernameField
		usernameField = new TextField();
		usernameField.setCaption("Nombre usuario");
		usernameField.setImmediate(false);
		usernameField.setWidth("160px");
		usernameField.setHeight("-1px");
		usernameField.setRequired(true);
		mainLayout.addComponent(usernameField, "top:17.0px;left:20.0px;");
		
		// rolesField
		rolesField = new Table();
		rolesField.setCaption("Roles");
		rolesField.setImmediate(false);
		rolesField.setWidth("501px");
		rolesField.setHeight("140px");
		mainLayout.addComponent(rolesField, "top:281.0px;left:19.0px;");
		
		// brnAdd
		brnAdd = new Button();
		brnAdd.setCaption("+");
		brnAdd.setImmediate(true);
		brnAdd.setWidth("-1px");
		brnAdd.setHeight("-1px");
		mainLayout.addComponent(brnAdd, "top:283.0px;left:525.0px;");
		
		// btnRemove
		btnRemove = new Button();
		btnRemove.setCaption("-");
		btnRemove.setImmediate(true);
		btnRemove.setWidth("-1px");
		btnRemove.setHeight("-1px");
		mainLayout.addComponent(btnRemove, "top:320.0px;left:525.0px;");
		
		return mainLayout;
	}
}