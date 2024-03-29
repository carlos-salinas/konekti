package com.thingtrack.konekti.view.module.alarm.internal;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.thingtrack.konekti.domain.Alarm;
import com.thingtrack.konekti.service.api.AlarmService;
import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.ui.AbstractView;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickFilterButtonListener;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickNavigationEvent;
import com.thingtrack.konekti.view.addon.ui.BoxToolbar.ClickPrintButtonListener;
import com.thingtrack.konekti.view.addon.ui.DataGridView;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar.ClickDownButtonListener;
import com.thingtrack.konekti.view.addon.ui.NavigationToolbar.ClickRefreshButtonListener;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AlarmView extends AbstractView implements ClickDownButtonListener,
		ClickRefreshButtonListener, ClickFilterButtonListener, ClickPrintButtonListener {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private DataGridView dgAlarm;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private AlarmService alarmService;

	private BindingSource<Alarm> bsAlarm = new BindingSource<Alarm>(Alarm.class, 0);

	private NavigationToolbar navigationToolbar;
	private BoxToolbar boxToolbar;

	private IWorkbenchContext context;
	private IViewContainer viewContainer;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public AlarmView(IWorkbenchContext context, IViewContainer viewContainer) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.context = context;
		
		// set Slide View Services and ViewContainer to navigate
		this.viewContainer = viewContainer;
		this.alarmService = AlarmViewContainer.getAlarmService();

		// initialize datasource views
		initView();
	}

	private void initView() {		
		try {
			dgAlarm.setImmediate(true);
			dgAlarm.setSelectable(true);
			
			bsAlarm.addNestedContainerProperty("alarmType.description");
			bsAlarm.addNestedContainerProperty("alarmStatus.description");
			bsAlarm.addNestedContainerProperty("area.description");
			
			dgAlarm.setBindingSource(bsAlarm);
			
			dgAlarm.setVisibleColumns(new String[] { "area.description", "alarmGroup", "alarmName","alarmType.description", "message", "alarmStatus.description", "alarmDate" });
			dgAlarm.setColumnHeaders(new String[] { "Area Trabajo", "Grupo Alarma", "Nombre Alarma", "Tipo", "Mensaje", "Estado", "Fecha Alarma" });

		} catch (Exception ex) {
			ex.getMessage();
		}

		refreshAlarmBindindSource();
		injectAlarmBindingSource();
	}

	private void refreshAlarmBindindSource() {
		try {
			bsAlarm.removeAllItems();
			bsAlarm.addAll(alarmService.getAll());

			// select the first item if exist
			if (bsAlarm.size() > 0)
				bsAlarm.setItemId(bsAlarm.getIdByIndex(0));
			
//			// TEST: open Vehicle module version 0.0.1.SNAPSHOT
//			try {
//				context.openBundle("com.thingtrack.bustrack.view.module.vehicle", "0.0.1.SNAPSHOT");
//			}
//			catch (Exception ex) {
//				throw new  RuntimeException(ex.getMessage());
//			}
			
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("¡No se pudo refrescar las Alarmas!", e);
		} catch (Exception e) {
			throw new RuntimeException("¡No se pudo refrescar las Alarmas!", e);
		}
	}

	private void injectAlarmBindingSource() {
		navigationToolbar = new NavigationToolbar(0, bsAlarm, viewContainer);
		boxToolbar = new BoxToolbar(1, bsAlarm);

		navigationToolbar.addListenerRefreshButton(this);
		navigationToolbar.addListenerDownButton(this);
		
		boxToolbar.addListenerFilterButton(this);
		boxToolbar.addListenerPrintButton(this);
		
		removeAllToolbar();

		addToolbar(navigationToolbar);
		addToolbar(boxToolbar);
	}
	
	@Override
	public void refreshButtonClick(NavigationToolbar.ClickNavigationEvent event) {
		refreshAlarmBindindSource();

	}

	@Override
	public void downButtonClick(NavigationToolbar.ClickNavigationEvent event) {
		// roll to the detail Location View
		viewContainer.getSliderView().rollNext();	
		
	}
	
	@Override
	public void filterButtonClick(ClickNavigationEvent event) {
		dgAlarm.setFilterBarVisible();
		
	}

	@Override
	public void printButtonClick(ClickNavigationEvent event) {
		try {
			dgAlarm.print("Listado Alarmas");
		}
		catch (Exception e) {
			throw new RuntimeException("¡No se pudo imprimir el informe!", e);
		}
		
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
		dgAlarm = new DataGridView() {
			@Override
			protected String formatPropertyValue(Object rowId, Object colId, Property property) {
				// Format by property type
				if (property.getType() == Date.class) {
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

					if (property.getValue() != null)
						return df.format((Date) property.getValue());
				}

				return super.formatPropertyValue(rowId, colId, property);
			}
		};

		dgAlarm.setImmediate(false);
		dgAlarm.setWidth("100.0%");
		dgAlarm.setHeight("100.0%");
		mainLayout.addComponent(dgAlarm);
		mainLayout.setExpandRatio(dgAlarm, 1.0f);

		return mainLayout;
	}

}
