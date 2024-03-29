package com.thingtrack.konekti.view.addon.ui;

import java.io.Serializable;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.thingtrack.konekti.view.addon.data.BindingSource;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeEvent;
import com.thingtrack.konekti.view.addon.data.BindingSource.IndexChangeListener;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class EditionToolbar extends AbstractToolbar {

	@AutoGenerated
	private HorizontalLayout toolbarLayout;

	@AutoGenerated
	private Button btnRemoveRegister;

	@AutoGenerated
	private Button btnEditRegister;

	@AutoGenerated
	private Button btnAddRegister;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
		
	private Object register;

	private int position = 0;
	
	// navigator button listeners
	private ClickAddButtonListener listenerAddButton = null;
	private ClickEditButtonListener listenerEditButton = null;
	private ClickRemoveButtonListener listenerRemoveButton = null;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */	
	 public EditionToolbar(int  position, final BindingSource<?> bindingSource) {
		 super(position, bindingSource);
		 
		buildMainLayout();
		
		// TODO add user code here
		btnAddRegister.setDescription("Añadir Registro");
		btnEditRegister.setDescription("Editar Registro");
		btnRemoveRegister.setDescription("Borrar Registro");
		
		this.position = position;
		
		// set add button listener
		btnAddRegister.addListener(new Button.ClickListener() {			
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {				
				if (listenerAddButton != null)
					//listenerAddButton.addButtonClick(new ClickNavigationEvent(event.getButton(), event.getComponent() , null, register, 0));					
					listenerAddButton.addButtonClick(new ClickNavigationEvent(event.getComponent(), register, 0));
				
			}
		});
		
		// set edit button listener
		btnEditRegister.addListener(new Button.ClickListener() {			
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {				
				int index = bindingSource.getIndex();
							
				if (index == 0)
					return;
				
				register = bindingSource.getItemId();
				
				if (listenerEditButton != null)									
					//listenerEditButton.editButtonClick(new ClickNavigationEvent(event.getButton(), event.getComponent() , null, register, index));				
					listenerEditButton.editButtonClick(new ClickNavigationEvent(event.getComponent() , register, index));
				
			}
		});
		
		// set delete button listener
		btnRemoveRegister.addListener(new Button.ClickListener() {			
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				int index = bindingSource.getIndex();
				
				if (index == 0)
					return;
				
				register = bindingSource.getItemId();
				
				if (listenerRemoveButton != null)					
					//listenerRemoveButton.deleteButtonClick(new ClickNavigationEvent(event.getButton(), event.getComponent() , null, register, index));				
					listenerRemoveButton.deleteButtonClick(new ClickNavigationEvent(event.getComponent(), register, index));
				
			}
		});
		
		//setWidth("250px");
	}
	
	
	public void addListenerAddButton(ClickAddButtonListener listener) {
		this.listenerAddButton = listener;
		
	}
	
	public void addListenerEditButton(ClickEditButtonListener listener) {
		this.listenerEditButton = listener;
		
	}
	
	public void addListenerDeleteButton(ClickRemoveButtonListener listener) {
		this.listenerRemoveButton = listener;
		
	}
	
	public interface ClickAddButtonListener extends Serializable {
        public void addButtonClick(ClickNavigationEvent event);

    }
	
	public interface ClickEditButtonListener extends Serializable {
        public void editButtonClick(ClickNavigationEvent event);

    }
	
	public interface ClickRemoveButtonListener extends Serializable {
        public void deleteButtonClick(ClickNavigationEvent event);

    }
	
	public class ClickNavigationEvent extends Event {
		private int index;
		private Object register;
		
		/*public ClickNavigationEvent(Button button, Component source) {
			//button.super(source);
			
		}

		public ClickNavigationEvent(Button button, Component source, MouseEventDetails details) {
			//button.super(source, details);
			
		}*/

		//public ClickNavigationEvent(Button button, Component source, MouseEventDetails details, Object register, int index) {
		public ClickNavigationEvent(Component source, Object register, int index) {
			super(source);
			//button.super(source, details);
			
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
	public int getPosition() {
		return this.position;
		
	}

	@Override
	public ComponentContainer getContent() {
		return this.toolbarLayout;
		
	}

	@Override
	public BindingSource<?> getBindingSource() {
		return this.bindingSource;
	}

	@Override
	public void setBindingSource(BindingSource<?> bindingSource) {
		this.bindingSource = bindingSource;
		
		// add change index binding source
		if (bindingSource != null) {
			bindingSource.addListenerToolBar((IndexChangeListener)this);
			
			// initialize binding source
			//bindingSource.Initialize();
		}
	}
	
	@Override
	public void bindingSourceIndexChange(IndexChangeEvent event) {
		if (bindingSource != null) {
			// TODO add user code here
			
		}
		
	}
	
	@AutoGenerated
	private void buildMainLayout() {

		// toolbarLayout
		toolbarLayout = buildToolbarLayout();
		addComponent(toolbarLayout);
	}

	@AutoGenerated
	private HorizontalLayout buildToolbarLayout() {
		
		// common part: create layout
		toolbarLayout = new HorizontalLayout();
		toolbarLayout.setImmediate(false);
		toolbarLayout.setSpacing(true);
		
		ButtonGroup editionButtonGroup = new ButtonGroup();
		toolbarLayout.addComponent(editionButtonGroup);
		
		// btnAddRegister
		btnAddRegister = new Button();
		btnAddRegister.setImmediate(true);
		btnAddRegister.setWidth("-1px");
		btnAddRegister.setHeight("-1px");
		btnAddRegister.setIcon(new ThemeResource("../konekti/images/icons/edition-toolbar/plus.png"));
		
		editionButtonGroup.addButton(btnAddRegister);
		
		// btnEditRegister
		btnEditRegister = new Button();
		//btnEditRegister.setCaption("U");
		btnEditRegister.setImmediate(true);
		btnEditRegister.setWidth("-1px");
		btnEditRegister.setHeight("-1px");
		btnEditRegister.setIcon(new ThemeResource("../konekti/images/icons/edition-toolbar/pencil.png"));
		
		editionButtonGroup.addButton(btnEditRegister);
		
		// btnRemoveRegister
		btnRemoveRegister = new Button();
		btnRemoveRegister.setImmediate(true);
		btnRemoveRegister.setWidth("-1px");
		btnRemoveRegister.setHeight("-1px");
		btnRemoveRegister.setIcon(new ThemeResource("../konekti/images/icons/edition-toolbar/minus.png"));
		
		editionButtonGroup.addButton(btnRemoveRegister);
		
		return toolbarLayout;
	}

}
