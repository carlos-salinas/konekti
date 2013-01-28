package com.thingtrack.konekti.view.module.calendar.addon;

import java.io.Serializable;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeEvent;
import com.thingtrack.konekti.view.addon.ui.AbstractToolbar;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.client.MouseEventDetails;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class CalendarToolbar extends AbstractToolbar {
	@AutoGenerated
	private HorizontalLayout toolbarLayout;

	@AutoGenerated
	private Button btnSave;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private Object register;
	
	private int position = 0;
		
	// navigator button listeners
	private ClickSaveButtonListener listenerSaveButton = null;
	
	private IViewContainer viewContainer;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */	
	public CalendarToolbar(int position, final BindingSource<?> bindingSource, IViewContainer viewContainer) {
		super(position, bindingSource);
			
		buildMainLayout();

		// TODO add user code here
		this.position = position;
		this.viewContainer = viewContainer;
		
		setBindingSource(bindingSource);
		
		btnSave.setDescription("Grabar Calendario");
		
		// set reject button listener
		btnSave.addListener(new ClickListener() {			
			public void buttonClick(ClickEvent event) {
				/*int index = bindingSource.getIndex();
				
				if (index == 0)
					return;
				
				register = bindingSource.getItemId();*/
				
				if (listenerSaveButton != null)
					listenerSaveButton.saveButtonClick(new ClickNavigationEvent(event.getButton(), event.getComponent() , null, register, 0));					
				
			}
		});
		
	}
	
	@Override
	public int getPosition() {
		return this.position;
		
	}

	@Override
	public ComponentContainer getContent() {
		return this.toolbarLayout;
		
	}
	
	public void addListenerSaveButton(ClickSaveButtonListener listener) {
		this.listenerSaveButton = listener;
		
	}
	
	
	public interface ClickSaveButtonListener extends Serializable {
        public void saveButtonClick(ClickNavigationEvent event);

    }
	
	public class ClickNavigationEvent extends ClickEvent {
		private int index;
		private Object register;
		
		public ClickNavigationEvent(Button button, Component source) {
			button.super(source);
			
		}

		public ClickNavigationEvent(Button button, Component source, MouseEventDetails details) {
			button.super(source, details);
			
		}

		public ClickNavigationEvent(Button button, Component source, MouseEventDetails details, Object register, int index) {
			button.super(source, details);
			
			this.index = index;
			this.register = register;
		}

		public int getIndex() {
			return this.index;
			
		}
		
		public Object getRegister() {
			return this.register;
			
		}
		
	  }
		
	@Override
	public void bindingSourceIndexChange(IndexChangeEvent event) {
		if (bindingSource != null) {
			// TODO Auto-generated method stub
		}
		
	}
	
	@AutoGenerated
	private void buildMainLayout() {
		toolbarLayout = buildToolbarLayout();
		addComponent(toolbarLayout);
	}

	@AutoGenerated
	private HorizontalLayout buildToolbarLayout() {
		// common part: create layout
		toolbarLayout = new HorizontalLayout();
		toolbarLayout.setImmediate(false);
		toolbarLayout.setSpacing(true);
		
		ButtonGroup calendarButtonGroup = new ButtonGroup();
		toolbarLayout.addComponent(calendarButtonGroup);
		
		// btnSave
		btnSave = new Button();
		btnSave.setCaption("Grabar Calendario");
		btnSave.setIcon(new ThemeResource("../konekti/images/icons/calendar-module/calendar-select-days-span.png"));
		btnSave.setImmediate(true);
		btnSave.setWidth("-1px");
		btnSave.setHeight("-1px");
		
		calendarButtonGroup.addButton(btnSave);
		
		return toolbarLayout;
	}

}