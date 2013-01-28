package com.thingtrack.konekti.view.web.form.field;

import org.vaadin.addon.customfield.CustomField;

import com.thingtrack.konekti.domain.User;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class UserField extends CustomField {

	private AbsoluteLayout mainLayout;
	private Button btnRemoveUser;
	private Button btnCancelUser;
	private Button btnApplyUser;
	private TextField usernameField;
	private PasswordField passwordField;	
	private PasswordField passwordConfirmField;
	
	private User undoUser;
	private User user;

	public UserField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		btnApplyUser.setEnabled(false);
		btnCancelUser.setEnabled(false);		
		passwordConfirmField.setEnabled(false);
		
		usernameField.addListener(new TextChangeListener() {			
			@Override
			public void textChange(TextChangeEvent event) {				
				btnApplyUser.setEnabled(true);
				btnCancelUser.setEnabled(true);		
			}
		});
		
		passwordField.addListener(new TextChangeListener() {			
			@Override
			public void textChange(TextChangeEvent event) {				
				btnApplyUser.setEnabled(true);
				btnCancelUser.setEnabled(true);
				passwordConfirmField.setEnabled(true);
			}
		});
		
		btnApplyUser.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				if (!passwordField.getValue().equals(passwordConfirmField.getValue())) {
					getWindow().showNotification("¡Las claves introducidas son diferentes!");
					return;
				}
				
//				if (user == null)
//					user = new User();
				
				user.setUsername(usernameField.getValue().toString());
				user.setPassword(passwordField.getValue().toString());
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
				passwordConfirmField.setEnabled(false);
			}
		});
	
		btnCancelUser.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				if (user != null) {
					user.setUsername(undoUser.getUsername());
					user.setPassword(undoUser.getPassword());
					
					usernameField.setValue(undoUser.getUsername());
					passwordField.setValue(undoUser.getUsername());
				}
				
				passwordConfirmField.setValue("");				
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
				passwordConfirmField.setEnabled(false);
			}
		});
		
		btnRemoveUser.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				user = null;
				
				usernameField.setValue("");
				passwordField.setValue("");
				passwordConfirmField.setValue("");	
				
				btnApplyUser.setEnabled(false);
				btnCancelUser.setEnabled(false);
			}
		});
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		undoUser = (User)newDataSource.getValue();
		user = (User)newDataSource.getValue();		

		if (user != null) {
			usernameField.setValue(user.getUsername());
			passwordField.setValue(user.getPassword());
		}
		else
			user = new User();
		
		super.setPropertyDataSource(newDataSource);

	}

	@Override
	public Class<?> getType() {
		return User.class;
		
	}

	@Override
	public Object getValue() {		
		return user;
		
	}
	
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("320px");
		mainLayout.setHeight("140px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("280px");
		setHeight("140px");
		
		// usernameField
		usernameField = new TextField();
		usernameField.setCaption("Nombre Usuario");
		usernameField.setImmediate(false);
		usernameField.setWidth("114px");
		usernameField.setHeight("-1px");
		mainLayout.addComponent(usernameField, "top:16.0px;left:6.0px;");
		
		// passwordField
		passwordField = new PasswordField();
		passwordField.setCaption("Contraseña");
		passwordField.setImmediate(false);
		passwordField.setWidth("114px");
		passwordField.setHeight("-1px");
		mainLayout.addComponent(passwordField, "top:60.0px;left:6.0px;");
		
		// btnApplyUser
		btnApplyUser = new Button();
		btnApplyUser.setCaption("Aplicar");
		btnApplyUser.setImmediate(true);
		btnApplyUser.setWidth("-1px");
		btnApplyUser.setHeight("-1px");
		mainLayout.addComponent(btnApplyUser, "top:16.0px;left:128.0px;");
		
		// btnRemoveUser
		btnRemoveUser = new Button();
		btnRemoveUser.setCaption("Borrar");
		btnRemoveUser.setImmediate(true);
		btnRemoveUser.setWidth("-1px");
		btnRemoveUser.setHeight("-1px");
		mainLayout.addComponent(btnRemoveUser, "top:54.0px;left:128.0px;");
		
		// btnCancelUser
		btnCancelUser = new Button();
		btnCancelUser.setCaption("Cancelar");
		btnCancelUser.setImmediate(true);
		btnCancelUser.setWidth("-1px");
		btnCancelUser.setHeight("-1px");
		mainLayout.addComponent(btnCancelUser, "top:16.0px;left:200.0px;");
		
		// passwordConfirmField
		passwordConfirmField = new PasswordField();
		passwordConfirmField.setCaption("Confirmar contraseña");
		passwordConfirmField.setImmediate(false);
		passwordConfirmField.setWidth("114px");
		passwordConfirmField.setHeight("-1px");
		mainLayout
				.addComponent(passwordConfirmField, "top:102.0px;left:6.0px;");
		
		return mainLayout;
	}
}