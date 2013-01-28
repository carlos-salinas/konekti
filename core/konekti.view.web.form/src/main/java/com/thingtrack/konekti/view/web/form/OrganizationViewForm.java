package com.thingtrack.konekti.view.web.form;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.domain.OrganizationType;
import com.thingtrack.konekti.service.api.OrganizationService;
import com.thingtrack.konekti.service.api.OrganizationTypeService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.web.form.field.AddressField;
import com.thingtrack.konekti.view.web.form.field.ChildOrganizationCollectionField;
import com.thingtrack.konekti.view.web.form.field.LocationCollectionField;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Select;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class OrganizationViewForm extends CustomComponent {
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private AddressField socialAddressField;
	@AutoGenerated
	private TabSheet tbsOrganization;
	@AutoGenerated
	private ComboBox organizationTypeField;
	@AutoGenerated
	private ComboBox parentOrganizationField;
	@AutoGenerated
	private TextField nameField;
	@AutoGenerated
	private TextField descriptionField;
	@AutoGenerated
	private TextField commentField;
	@AutoGenerated
	private TextField codeField;
	@AutoGenerated
	private TextField cifField;
	@AutoGenerated
	private CheckBox activeField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private ChildOrganizationCollectionField childOrganizationCollectionField;
	private LocationCollectionField locationCollectionField;
	
	// form services
	private OrganizationService organizationService;
	private OrganizationTypeService organizationTypeService;
	
	// organization type datasource
	private BeanItemContainer<Organization> bcOrganization = new BindingSource<Organization>(Organization.class);
	private BeanItemContainer<OrganizationType> bcOrganizationType = new BindingSource<OrganizationType>(OrganizationType.class);	
	
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */	
	public OrganizationViewForm() throws IllegalArgumentException, Exception {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	    
		// TODO add user code here		
		initComponents();
		
		// configure Organization Type data
		parentOrganizationField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		parentOrganizationField.setItemCaptionPropertyId("name");
		
		organizationTypeField.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		organizationTypeField.setItemCaptionPropertyId("description");
		
		// configure Table Child Organization data		
		tbsOrganization.setCaption("Ubicaciones/Organizaciones Hijas");
		
		childOrganizationCollectionField = new ChildOrganizationCollectionField();		
		childOrganizationCollectionField.setSizeFull();
		tbsOrganization.addTab(childOrganizationCollectionField, "Sub Organizaciones");
		
		locationCollectionField = new LocationCollectionField();
		locationCollectionField.setSizeFull();
		tbsOrganization.addTab(locationCollectionField, "Ubicaciones");
				
		// get form services from OSGi Service Registry
		getServices();
		
		// load data sources
		loadData();
	}
	
	private void initComponents() {
		nameField.setNullRepresentation("");
		descriptionField.setNullRepresentation("");
		commentField.setNullRepresentation("");
		codeField.setNullRepresentation("");
		cifField.setNullRepresentation("");
		
		codeField.setRequiredError(codeField.getCaption() + " es un campo requerido");
		nameField.setRequiredError(nameField.getCaption() + " es un campo requerido");
		organizationTypeField.setRequiredError(nameField.getCaption() + " es un campo requerido");
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void getServices() {
		try {
			BundleContext bundleContext = FrameworkUtil.getBundle(OrganizationViewForm.class).getBundleContext();
			
			ServiceReference organizationServiceReference = bundleContext.getServiceReference(OrganizationService.class.getName());
			organizationService = OrganizationService.class.cast(bundleContext.getService(organizationServiceReference));
			
			ServiceReference organizationTypeServiceReference = bundleContext.getServiceReference(OrganizationTypeService.class.getName());
			organizationTypeService = OrganizationTypeService.class.cast(bundleContext.getService(organizationTypeServiceReference));
		}
		catch (Exception e) {
			e.getMessage();
			
		}
		
	}
	
	private void loadData() throws IllegalArgumentException, Exception {		
		bcOrganization.removeAllItems();
		bcOrganization.addAll(organizationService.getAll());
		
		parentOrganizationField.setContainerDataSource(bcOrganization);
		
		bcOrganizationType.removeAllItems();
		bcOrganizationType.addAll(organizationTypeService.getAll());
		
		organizationTypeField.setContainerDataSource(bcOrganizationType);
		
	}
	
	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("560px");
		mainLayout.setHeight("520px");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("560px");
		setHeight("520px");
		
		// activeField
		activeField = new CheckBox();
		activeField.setCaption("Activo");
		activeField.setImmediate(false);
		activeField.setWidth("-1px");
		activeField.setHeight("-1px");
		activeField.setTabIndex(3);
		activeField.setRequired(true);
		mainLayout.addComponent(activeField, "top:20.0px;left:486.0px;");
		
		// cifField
		cifField = new TextField();
		cifField.setCaption("CIF");
		cifField.setImmediate(false);
		cifField.setWidth("172px");
		cifField.setHeight("-1px");
		cifField.setTabIndex(5);
		cifField.setSecret(false);
		mainLayout.addComponent(cifField, "top:136.0px;left:368.0px;");
		
		// codeField
		codeField = new TextField();
		codeField.setCaption("Código");
		codeField.setImmediate(false);
		codeField.setWidth("120px");
		codeField.setHeight("-1px");
		codeField.setTabIndex(1);
		codeField.setRequired(true);
		codeField.setSecret(false);
		mainLayout.addComponent(codeField, "top:20.0px;left:20.0px;");
		
		// commentField
		commentField = new TextField();
		commentField.setCaption("Comentarios");
		commentField.setImmediate(false);
		commentField.setWidth("520px");
		commentField.setHeight("40px");
		commentField.setTabIndex(8);
		commentField.setSecret(false);
		mainLayout.addComponent(commentField, "top:280.0px;left:20.0px;");
		
		// descriptionField
		descriptionField = new TextField();
		descriptionField.setCaption("Descripción");
		descriptionField.setImmediate(false);
		descriptionField.setWidth("520px");
		descriptionField.setHeight("-1px");
		descriptionField.setTabIndex(4);
		descriptionField.setSecret(false);
		mainLayout.addComponent(descriptionField, "top:56.0px;left:20.0px;");
		
		// nameField
		nameField = new TextField();
		nameField.setCaption("Nombre");
		nameField.setImmediate(false);
		nameField.setWidth("320px");
		nameField.setHeight("-1px");
		nameField.setTabIndex(2);
		nameField.setRequired(true);
		nameField.setSecret(false);
		mainLayout.addComponent(nameField, "top:20.0px;left:160.0px;");
		
		// parentOrganizationField
		parentOrganizationField = new ComboBox();
		parentOrganizationField.setCaption("Organización Padre");
		parentOrganizationField.setImmediate(true);
		parentOrganizationField.setWidth("172px");
		parentOrganizationField.setHeight("-1px");
		parentOrganizationField.setTabIndex(6);
		mainLayout.addComponent(parentOrganizationField,
				"top:176.0px;left:368.0px;");
		
		// organizationTypeField
		organizationTypeField = new ComboBox();
		organizationTypeField.setCaption("Tipo Organización");
		organizationTypeField.setImmediate(true);
		organizationTypeField.setWidth("172px");
		organizationTypeField.setHeight("24px");
		organizationTypeField.setTabIndex(7);
		organizationTypeField.setRequired(true);
		mainLayout.addComponent(organizationTypeField,
				"top:216.0px;left:368.0px;");
		
		// tbsOrganization
		tbsOrganization = new TabSheet();
		tbsOrganization.setImmediate(false);
		tbsOrganization.setWidth("520px");
		tbsOrganization.setHeight("160px");
		mainLayout.addComponent(tbsOrganization, "top:341.0px;left:20.0px;");
		
		// socialAddressField
		socialAddressField = new AddressField();
		socialAddressField.setImmediate(false);
		socialAddressField.setWidth("320px");
		socialAddressField.setHeight("160px");
		mainLayout.addComponent(socialAddressField, "top:100.0px;left:20.0px;");
		
		return mainLayout;
	}

}