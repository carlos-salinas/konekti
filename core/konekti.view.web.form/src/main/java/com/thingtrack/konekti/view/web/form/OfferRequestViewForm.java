package com.thingtrack.konekti.view.web.form;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.domain.Client;
import com.thingtrack.konekti.domain.OfferRequestStatus;
import com.thingtrack.konekti.domain.OfferRequestType;
import com.thingtrack.konekti.service.api.ClientService;
import com.thingtrack.konekti.service.api.OfferRequestStatusService;
import com.thingtrack.konekti.service.api.OfferRequestTypeService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;


@SuppressWarnings("serial")
public class OfferRequestViewForm extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout hlSearchButtons;
	@AutoGenerated
	private Button btnAddClient;
	@AutoGenerated
	private Button btnRefreshClient;
	@AutoGenerated
	private ComboBox offerRequestTypeField;
	@AutoGenerated
	private ComboBox offerRequestStatusField;
	@AutoGenerated
	private DateField offerRequestDateField;
	@AutoGenerated
	private TextField observationField;
	@AutoGenerated
	private TextField codeField;
	@AutoGenerated
	private ComboBox clientField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	// form services
	private OfferRequestStatusService offerRequestStatusService;
	private OfferRequestTypeService offerRequestTypeService;
	private ClientService clientService;
	
	// offer request type datasource
	private BeanItemContainer<OfferRequestStatus> bcOfferRequestStatus = new BindingSource<OfferRequestStatus>(OfferRequestStatus.class);
	private BeanItemContainer<OfferRequestType> bcOfferRequestType = new BindingSource<OfferRequestType>(OfferRequestType.class);
	private BeanItemContainer<Client> bcClient = new BindingSource<Client>(Client.class);
	
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public OfferRequestViewForm() throws IllegalArgumentException, Exception {		 
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		initComponents();
		
		// configure Organization Type data
		offerRequestTypeField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);		
		offerRequestTypeField.setItemCaptionPropertyId("description");
		
		offerRequestStatusField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		offerRequestStatusField.setItemCaptionPropertyId("description");
		
		clientField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		clientField.setItemCaptionPropertyId("name");
		
		// get form services from OSGi Service Registry
		getServices();
		
		// load data sources
		loadData();
		
		btnAddClient.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				addClient();
				
			}
		});
		
		btnRefreshClient.addListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				refreshClient();
				
			}
		});
	}
	
	private void initComponents() {
		observationField.setNullRepresentation("");
		codeField.setNullRepresentation("");
		
		offerRequestDateField.setDateFormat("dd/MM/yyyy HH:mm");
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(OfferRequestViewForm.class).getBundleContext();
			
			ServiceReference offerRequestTypeServiceReference = bundleContext.getServiceReference(OfferRequestTypeService.class.getName());
			offerRequestTypeService = OfferRequestTypeService.class.cast(bundleContext.getService(offerRequestTypeServiceReference));
			
			ServiceReference offerRequestStatusServiceReference = bundleContext.getServiceReference(OfferRequestStatusService.class.getName());
			offerRequestStatusService = OfferRequestStatusService.class.cast(bundleContext.getService(offerRequestStatusServiceReference));
			
			ServiceReference clientServiceReference = bundleContext.getServiceReference(ClientService.class.getName());
			clientService = ClientService.class.cast(bundleContext.getService(clientServiceReference));
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	private void loadData() throws IllegalArgumentException, Exception {		
		bcOfferRequestType.removeAllItems();
		bcOfferRequestType.addAll(offerRequestTypeService.getAll());
		
		offerRequestTypeField.setContainerDataSource(bcOfferRequestType);
		
		bcOfferRequestStatus.removeAllItems();
		bcOfferRequestStatus.addAll(offerRequestStatusService.getAll());
		
		offerRequestStatusField.setContainerDataSource(bcOfferRequestStatus);
	
		bcClient.removeAllItems();
		bcClient.addAll(clientService.getAll());
		
		clientField.setContainerDataSource(bcClient);
		
	}
	
	private void refreshClient() {
		bcClient.removeAllItems();
		try {
			bcClient.removeAllItems();
			bcClient.addAll(clientService.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void addClient() {
		Client client = new Client();

		try {
			@SuppressWarnings("unused")
			WindowDialog<Client> windowDialog = new WindowDialog<Client>(getApplication().getMainWindow(), "Nuevo Cliente", 
					"Guardar", DialogResult.SAVE, "Cancelar", DialogResult.CANCEL, 
					new ClientViewForm(), client, 
					new WindowDialog.CloseWindowDialogListener<Client>() {
			    public void windowDialogClose(WindowDialog<Client>.CloseWindowDialogEvent<Client> event) {
			    	if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
			    		return ;
			    	
			    	try {
			    		Client savingClient = event.getDomainEntity();
			    		
			    		Client savedClient = clientService.save(savingClient);
			    		
			    		refreshClient();
					} catch (Exception e) {
						e.printStackTrace();
						
					}
			    }

			});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("580px");
		mainLayout.setHeight("280px");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("580px");
		setHeight("280px");
		
		// clientField
		clientField = new ComboBox();
		clientField.setCaption("Cliente");
		clientField.setImmediate(false);
		clientField.setWidth("461px");
		clientField.setHeight("-1px");
		mainLayout.addComponent(clientField, "top:96.0px;left:19.0px;");
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Código");
		codeField.setImmediate(false);
		codeField.setWidth("120px");
		codeField.setHeight("-1px");
		mainLayout.addComponent(codeField, "top:16.0px;left:20.0px;");
		
		// observationField
		observationField = new TextField();
		observationField.setCaption("Observaciones");
		observationField.setImmediate(false);
		observationField.setWidth("540px");
		observationField.setHeight("119px");
		mainLayout.addComponent(observationField, "top:141.0px;left:20.0px;");
		
		// offerRequestDateField
		offerRequestDateField = new DateField();
		offerRequestDateField.setCaption("Fecha Petición Oferta");
		offerRequestDateField.setImmediate(false);
		offerRequestDateField.setWidth("100.0%");
		offerRequestDateField.setHeight("23px");
		offerRequestDateField.setInvalidAllowed(false);
		mainLayout.addComponent(offerRequestDateField,
				"top:19.0px;right:19.0px;left:421.0px;");
		
		// offerRequestStatusField
		offerRequestStatusField = new ComboBox();
		offerRequestStatusField.setCaption("Estado");
		offerRequestStatusField.setImmediate(false);
		offerRequestStatusField.setWidth("220px");
		offerRequestStatusField.setHeight("-1px");
		mainLayout.addComponent(offerRequestStatusField,
				"top:58.0px;left:340.0px;");
		
		// offerRequestTypeField
		offerRequestTypeField = new ComboBox();
		offerRequestTypeField.setCaption("Tipo");
		offerRequestTypeField.setImmediate(false);
		offerRequestTypeField.setWidth("300px");
		offerRequestTypeField.setHeight("-1px");
		mainLayout.addComponent(offerRequestTypeField,
				"top:58.0px;left:20.0px;");
		
		// hlSearchButtons
		hlSearchButtons = buildHlSearchButtons();
		mainLayout.addComponent(hlSearchButtons, "top:96.0px;left:484.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHlSearchButtons() {
		// common part: create layout
		hlSearchButtons = new HorizontalLayout();
		hlSearchButtons.setImmediate(false);
		hlSearchButtons.setWidth("-1px");
		hlSearchButtons.setHeight("-1px");
		hlSearchButtons.setMargin(false);
		
		// btnRefreshClient
		btnRefreshClient = new Button();
		btnRefreshClient.setCaption("R");
		btnRefreshClient.setImmediate(true);
		btnRefreshClient.setWidth("-1px");
		btnRefreshClient.setHeight("-1px");
		hlSearchButtons.addComponent(btnRefreshClient);
		
		// btnAddClient
		btnAddClient = new Button();
		btnAddClient.setCaption("+");
		btnAddClient.setImmediate(true);
		btnAddClient.setWidth("-1px");
		btnAddClient.setHeight("-1px");
		hlSearchButtons.addComponent(btnAddClient);
		
		return hlSearchButtons;
	}
}