package com.thingtrack.konekti.view.module.supplier.internal;

import org.vaadin.dialogs.ConfirmDialog;

import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.service.api.ConfigurationService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.AbstractView;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickFilterButtonListener;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickPrintButtonListener;
import com.thingtrack.konekti.view.addon.ui.DataGridView;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickAddButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickEditButtonListener;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickNavigationEvent;
import com.thingtrack.konekti.view.addon.ui.EditionToolbar.ClickRemoveButtonListener;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar.ClickRefreshButtonListener;
import com.thingtrack.konekti.view.addon.ui.WindowDialog;
import com.thingtrack.konekti.view.addon.ui.WindowDialog.DialogResult;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.thingtrack.konekti.view.web.form.ConfigurationViewForm;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.CustomTable.ColumnGenerator;

@SuppressWarnings("serial")
public class ConfigurationView extends AbstractView implements
		ClickRefreshButtonListener,
		ClickAddButtonListener, ClickEditButtonListener,
		ClickRemoveButtonListener, ClickFilterButtonListener,
		ClickPrintButtonListener {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private DataGridView dgConfiguration;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private ConfigurationService configurationService;
	
	private BindingSource<Configuration> bsConfiguration = new BindingSource<Configuration>(Configuration.class, 0);

	private NavigationToolbar navigationToolbar;
	private EditionToolbar editionToolbar;
	private BoxToolbar boxToolbar;

	private IViewContainer viewContainer;
	private IWorkbenchContext context;
		
	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public ConfigurationView(IWorkbenchContext context, IViewContainer viewContainer) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.context = context;
		
		// set Slide View Services and ViewContainer to navigate
		this.viewContainer = viewContainer;
		this.configurationService = ConfigurationViewContainer.getConfigurationService();		
	    
		// initialize datasource views
		initView();
	}

	private void initView() {		
		try {
			// initialize Slide View Organization View
			dgConfiguration.setImmediate(true);
			dgConfiguration.setSelectable(true);
	
			// add column generator and nested columns
			OrganizationNameColumn organizationNameColumn = new OrganizationNameColumn();			
			if (dgConfiguration.getColumnGenerator(OrganizationNameColumn.ORGANIZATION_NAME_COLUMN_ID) == null) 
				dgConfiguration.addGeneratedColumn(OrganizationNameColumn.ORGANIZATION_NAME_COLUMN_ID, organizationNameColumn);

			MenuResourceNameColumn menuResourceNameColumn = new MenuResourceNameColumn();			
			if (dgConfiguration.getColumnGenerator(MenuResourceNameColumn.MENU_RESOURCE_NAME_COLUMN_ID) == null) 
				dgConfiguration.addGeneratedColumn(MenuResourceNameColumn.MENU_RESOURCE_NAME_COLUMN_ID, menuResourceNameColumn);
			
			dgConfiguration.setBindingSource(bsConfiguration);			
			
			dgConfiguration.setVisibleColumns(new String[] { "configurationId", OrganizationNameColumn.ORGANIZATION_NAME_COLUMN_ID, "tag", "description", "type", "value", MenuResourceNameColumn.MENU_RESOURCE_NAME_COLUMN_ID });
			dgConfiguration.setColumnHeaders(new String[] { "Id", "Organizacion", "Tag", "Descripción", "Tipo", "Valor", "Modulo" });								

			dgConfiguration.setColumnCollapsed("configurationId", true);
		} catch (Exception ex) {
			ex.getMessage();
		}

		refreshBindindSource();
		injectConfigurationBindingSource();
		
	}

	private void refreshBindindSource() {
		try {
			bsConfiguration.removeAllItems();
			bsConfiguration.addAll(configurationService.getAll(context.getUser()));
			
			// select the first item if exist
			if (bsConfiguration.size() > 0)
				bsConfiguration.setItemId(bsConfiguration.getIdByIndex(0));
		} catch (IllegalArgumentException e) {
 			throw new RuntimeException("¡No se pudo refrescar las Configuraciones!", e);
		} catch (Exception e) {
			throw new RuntimeException("¡No se pudo refrescar las Configuraciones!", e);
		}
	}

	private void injectConfigurationBindingSource() {
		navigationToolbar = new NavigationToolbar(0, bsConfiguration, viewContainer);
		editionToolbar = new EditionToolbar(1, bsConfiguration);
		boxToolbar = new BoxToolbar(2, bsConfiguration);
		
		navigationToolbar.addListenerRefreshButton(this);
		navigationToolbar.setUpButton(false);
		navigationToolbar.setDownButton(false);
		
		editionToolbar.addListenerAddButton(this);
		editionToolbar.addListenerEditButton(this);
		editionToolbar.addListenerDeleteButton(this);

		boxToolbar.addListenerFilterButton(this);
		boxToolbar.addListenerPrintButton(this);
				
		dgConfiguration.addListenerAddButton(this);
		dgConfiguration.addListenerEditButton(this);
		dgConfiguration.addListenerDeleteButton(this);
		
		removeAllToolbar();

		addToolbar(navigationToolbar);
		addToolbar(editionToolbar);
		addToolbar(boxToolbar);
	}
	
	@Override
	public void refreshButtonClick(NavigationToolbar.ClickNavigationEvent event) {
		refreshBindindSource();

	}
	
	private void refreshDataGridView(Configuration configurationSaved) {
		if(bsConfiguration.containsId(configurationSaved)){			
			Configuration configurationSupplier = bsConfiguration.prevItemId(configurationSaved);
			
			bsConfiguration.removeItem(configurationSaved);
			bsConfiguration.addItemAfter(configurationSupplier, configurationSaved);
			bsConfiguration.setItemId(configurationSaved);
		}
		else
			bsConfiguration.addItem(configurationSaved);
		
	}
	
	@Override
	public void addButtonClick(ClickNavigationEvent event) {
		Configuration configuration = null;
		try {
			configuration = configurationService.createNewEntity(context.getUser().getActiveOrganization());
		} catch (Exception e) {
			throw new RuntimeException(
					"¡No se pudo crear el nueva configuration!",
					e);
		}

		try {
			@SuppressWarnings("unused")
			WindowDialog<Configuration> windowDialog = new WindowDialog<Configuration>(
					getWindow(), "Nuevo Configuración", "Guardar",
					DialogResult.SAVE, "Cancelar", DialogResult.CANCEL,
					new ConfigurationViewForm(), configuration,
					new WindowDialog.CloseWindowDialogListener<Configuration>() {
						public void windowDialogClose(
								WindowDialog<Configuration>.CloseWindowDialogEvent<Configuration> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Configuration savingConfiguration = event.getDomainEntity();
					    		
								Configuration savedConfiguration = configurationService.save(savingConfiguration);
					    		savingConfiguration.setConfigurationId(savedConfiguration.getConfigurationId());
					    		
					    		refreshDataGridView(savingConfiguration);

							} catch (Exception e) {
								throw new RuntimeException("¡No se pudo crear el nueva configuración!", e);

							}
						}

					});
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("¡No se pudo abrir el formulario Nueva Configuración!", e);
		} catch (Exception e) {
			throw new RuntimeException("¡No se pudo abrir el formulario Nueva Configuración!", e);
		}

	}

	@Override
	public void editButtonClick(ClickNavigationEvent event) {
		Configuration editingConfiguration = (Configuration) event.getRegister();

		try {
			@SuppressWarnings("unused")
			WindowDialog<Configuration> windowDialog = new WindowDialog<Configuration>(
					getWindow(), "Editor Configuración", "Guardar",
					DialogResult.SAVE, "Cancelar", DialogResult.CANCEL,
					new ConfigurationViewForm(), editingConfiguration,
					new WindowDialog.CloseWindowDialogListener<Configuration>() {
						public void windowDialogClose(
								WindowDialog<Configuration>.CloseWindowDialogEvent<Configuration> event) {
							if (event.getDialogResult() != WindowDialog.DialogResult.SAVE)
								return;

							try {
								Configuration savingConfiguration = event.getDomainEntity();
					    		
								Configuration savedConfiguration = configurationService.save(savingConfiguration);
					    		
					    		refreshDataGridView(savingConfiguration);
										
							} catch (Exception e) {
								throw new RuntimeException("¡No se pudo modificar la configuración!", e);

							}
						}

					});
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("¡No se pudo abrir el formulario Editor Configuración!", e);
		} catch (Exception e) {
			throw new RuntimeException("¡No se pudo abrir el formulario Editor Configuración!", e);
		}

	}

	@Override
	public void deleteButtonClick(ClickNavigationEvent event) {
		final Configuration editingConfiguration = (Configuration) event.getRegister();

		if (editingConfiguration == null)
			return;

		ConfirmDialog.show(getWindow(), "Borrar Configuración", "¿Estás seguro?",
				"Si", "No", new ConfirmDialog.Listener() {

					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							try {
								configurationService.delete(editingConfiguration);
		            			
		            			bsConfiguration.removeItem(editingConfiguration);		            			

							} catch (IllegalArgumentException e) {
								throw new RuntimeException("¡No se pudo borrar la configuracion!", e);
							} catch (Exception e) {
								throw new RuntimeException("¡No se pudo borrar la configuracion!", e);
							}
						}
					}
				});

	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// dgEmployee
		dgConfiguration = new DataGridView();
		dgConfiguration.setImmediate(false);
		dgConfiguration.setWidth("100.0%");
		dgConfiguration.setHeight("100.0%");
		mainLayout.addComponent(dgConfiguration);
		mainLayout.setExpandRatio(dgConfiguration, 1.0f);

		return mainLayout;
	}

	@Override
	public void filterButtonClick(BoxToolbar.ClickNavigationEvent event) {
		dgConfiguration.setFilterBarVisible();

	}

	@Override
	public void printButtonClick(BoxToolbar.ClickNavigationEvent event) {
		try {
			dgConfiguration.print("Maestro Configuraciones");
		}
		catch (Exception e) {
			throw new RuntimeException("¡No se pudo imprimir el informe!", e);
		}
		
	}

	private class OrganizationNameColumn implements ColumnGenerator {
		static final String  ORGANIZATION_NAME_COLUMN_ID = "organization-name-column-id";
		
		@Override
		public Object generateCell(CustomTable source, Object itemId, Object columnId) {
			
			Label  organizationName = new Label();
			
			Configuration configuration = (Configuration) itemId;
			
			if(configuration.getOrganization() != null)
				organizationName.setValue(configuration.getOrganization().getName());
			
			return organizationName;
		}
				
	}
	
	private class MenuResourceNameColumn implements ColumnGenerator {
		static final String  MENU_RESOURCE_NAME_COLUMN_ID = "menu-resource-name-column-id";
		
		@Override
		public Object generateCell(CustomTable source, Object itemId, Object columnId) {
			
			Label  menuResourcenName = new Label();
			
			Configuration configuration = (Configuration) itemId;
			
			if(configuration.getMenuCommandResource() != null)
				menuResourcenName.setValue(configuration.getMenuCommandResource().getCaption());
			
			return menuResourcenName;
		}
				
	}
}
