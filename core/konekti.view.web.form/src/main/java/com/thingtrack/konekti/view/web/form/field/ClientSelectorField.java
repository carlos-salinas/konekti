package com.thingtrack.konekti.view.web.form.field;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.vaadin.addon.customfield.CustomField;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.service.api.ClientService;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.web.form.ClientViewForm;
import com.thingtrack.konekti.view.web.form.selector.ClientSelectorWindow;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class ClientSelectorField extends CustomField {
	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private Button btnSearchEntity;
	@AutoGenerated
	private Button btnClearEntity;
	@AutoGenerated
	private Button btnAddEntity;	
	@AutoGenerated
	private TextField clientField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	private IWorkbenchContext context;
	
	private Client client;
	
	private ClientService clientService;
	
	private boolean clearEntityVisible = true;
	private boolean addEntityVisible = true;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public ClientSelectorField() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here		
		getServices();
		
		clientField.setNullRepresentation("");
		clientField.setRequiredError(clientField.getCaption() + " es un campo requerido");
		
		btnSearchEntity.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					@SuppressWarnings("unused")
					ClientSelectorWindow windowDialog = new ClientSelectorWindow(getApplication().getMainWindow(), client, new ClientSelectorWindow.CloseWindowDialogListener() {
					    public void windowDialogClose(ClientSelectorWindow.CloseWindowDialogEvent event) {
					    	if (event.getDialogResult() != ClientSelectorWindow.DialogResult.SELECT)
					    		return ;
					    	
					    	client = event.getDomainEntity();
					    	clientField.setValue(client.getName());
					    }
		
					});
				} catch (Exception e) {
					throw new RuntimeException("¡No se pudo abrir el selector de clientes!", e);
				}
			}
		});
		
		btnClearEntity.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				client = null;
				clientField.setValue(null);
			}
		});
		
		btnAddEntity.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				Client client = null;
				try {
					client = clientService.createNewClient(ClientSelectorField.this.context.getUser().getActiveOrganization());
				} catch (Exception e) {
					throw new RuntimeException(
							"¡No se pudo crear el nuevo código cliente!",
							e);
				}	
				
				try {
					@SuppressWarnings("unused")
					WindowDialog<Client> windowDialog = new WindowDialog<Client>(
							getApplication().getMainWindow(), "Nuevo Cliente", "Guardar", DialogResult.SAVE,
							"Cancelar", DialogResult.CANCEL, new ClientViewForm(), client,
							new WindowDialog.CloseWindowDialogListener<Client>() {
								public void windowDialogClose(
										WindowDialog<Client>.CloseWindowDialogEvent<Client> event) {
									if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
										return;

									try {
										Client savingClient = event.getDomainEntity();

										Client savedClient = clientService.save(savingClient);
										savingClient.setAgentId(savedClient.getAgentId());
										
										clientField.setValue(savingClient.getName());
										
										ClientSelectorField.this.client = savingClient;
									} catch (Exception e) {
										throw new RuntimeException(
												"¡No se pudo crear el nuevo cliente!",
												e);

									}
								}

							});
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(
							"¡No se pudo abrir el formulario Nuevo Cliente!", e);
				} catch (Exception e) {
					throw new RuntimeException(
							"¡No se pudo abrir el formulario Nuevo Cliente!", e);
				}

			}
		});
		
		btnClearEntity.setVisible(clearEntityVisible);
		btnAddEntity.setVisible(addEntityVisible);
	}

	@Override
    public void focus() {
		clientField.focus();
		
	}
	
	public void setContext(IWorkbenchContext context) {
		this.context = context;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(ClientSelectorField.class).getBundleContext();
			
			ServiceReference clientServiceReference = bundleContext.getServiceReference(ClientService.class.getName());
			clientService = ClientService.class.cast(bundleContext.getService(clientServiceReference));
			
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		client = (Client) newDataSource.getValue();

		if (client != null)
			clientField.setValue(client.getName());
		
		super.setPropertyDataSource(newDataSource);
	}
	
	@Override
	public Object getValue() {
		return client;
	}
	
	@Override
	public Class<?> getType() {
		return Client.class;
		
	}
	
	/**
	 * @return the clearEntityVisible
	 */
	public boolean isClearEntityVisible() {
		return clearEntityVisible;
	}

	/**
	 * @param clearEntityVisible the clearEntityVisible to set
	 */
	public void setClearEntityVisible(boolean clearEntityVisible) {
		this.clearEntityVisible = clearEntityVisible;
		btnClearEntity.setVisible(clearEntityVisible);
	}

	/**
	 * @return the addEntityVisible
	 */
	public boolean isAddEntityVisible() {
		return addEntityVisible;
	}

	/**
	 * @param addEntityVisible the addEntityVisible to set
	 */
	public void setAddEntityVisible(boolean addEntityVisible) {
		this.addEntityVisible = addEntityVisible;
		btnAddEntity.setVisible(addEntityVisible);
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
		clientField = new TextField();
		clientField.setImmediate(false);
		clientField.setWidth("150px");
		clientField.setHeight("-1px");
		mainLayout.addComponent(clientField);
		
		ButtonGroup editionButtonGroup = new ButtonGroup();
		
		// btnSearch
		btnSearchEntity = new Button();
		btnSearchEntity.setIcon(new ThemeResource("../konekti/images/icons/container-module/magnifier-zoom.png"));
		btnSearchEntity.setImmediate(true);
		btnSearchEntity.setWidth("-1px");
		btnSearchEntity.setHeight("-1px");
		editionButtonGroup.addButton(btnSearchEntity);
		
		btnClearEntity = new Button();
		btnClearEntity.setIcon(new ThemeResource("../konekti/images/icons/eraser.png"));
		btnClearEntity.setImmediate(true);
		btnClearEntity.setWidth("-1px");
		btnClearEntity.setHeight("-1px");
		editionButtonGroup.addButton(btnClearEntity);
		
		btnAddEntity = new Button();
		btnAddEntity.setIcon(new ThemeResource("../konekti/images/icons/plus-circle.png"));
		btnAddEntity.setImmediate(true);
		btnAddEntity.setWidth("-1px");
		btnAddEntity.setHeight("-1px");
		editionButtonGroup.addButton(btnAddEntity);
		
		mainLayout.addComponent(editionButtonGroup);
		mainLayout.setExpandRatio(editionButtonGroup, 1.0f);
		
		return mainLayout;
	}

}
