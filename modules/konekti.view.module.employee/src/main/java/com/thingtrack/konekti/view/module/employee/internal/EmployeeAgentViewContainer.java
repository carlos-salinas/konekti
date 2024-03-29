package com.thingtrack.konekti.view.module.employee.internal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.knowledge.service.api.EmployeeAgentKnowledgeService;
import com.thingtrack.konekti.service.api.AddressService;
import com.thingtrack.konekti.service.api.EmployeeAgentService;
import com.thingtrack.konekti.service.api.EmployeeAgentStatusService;
import com.thingtrack.konekti.service.api.EmployeeAgentTypeService;
import com.thingtrack.konekti.view.addon.ui.AbstractViewContainer;
import com.thingtrack.konekti.view.addon.ui.SliderView;
import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.kernel.ui.layout.ISliderView;
import com.thingtrack.konekti.view.kernel.ui.layout.IViewChangeListener;
import com.thingtrack.konekti.view.kernel.ui.layout.IView;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class EmployeeAgentViewContainer extends AbstractViewContainer {

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private SliderView sliderView;

	/** Views **/
	private EmployeeAgentView employeeAgentView;
		
	/** Business services **/
	@Autowired
	private AddressService addressService;
	@Autowired
	private EmployeeAgentService employeeAgentService;
	@Autowired
	private EmployeeAgentTypeService employeeAgentTypeService;
	@Autowired
	private EmployeeAgentStatusService employeeAgentStatusService;
	@Autowired
	private EmployeeAgentKnowledgeService employeeAgentKnowledgeService;
	
	private IWorkbenchContext context; 
	
	// Thread Local Bundle Business Services
	private static ThreadLocal<AddressService> threadAddressService = new ThreadLocal<AddressService>();
	private static ThreadLocal<EmployeeAgentService> threadEmployeeAgentService = new ThreadLocal<EmployeeAgentService>();
	private static ThreadLocal<EmployeeAgentTypeService> threadEmployeeAgentTypeService = new ThreadLocal<EmployeeAgentTypeService>();
	private static ThreadLocal<EmployeeAgentStatusService> threadEmployeeAgentStatusService = new ThreadLocal<EmployeeAgentStatusService>();
	private static ThreadLocal<EmployeeAgentKnowledgeService> threadEmployeeAgentKnowledgeService = new ThreadLocal<EmployeeAgentKnowledgeService>();
		
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public EmployeeAgentViewContainer(IWorkbenchContext context) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		this.context = context;
	}
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void createViews() {
		// initialize thread local bundle services
		threadAddressService.set(addressService);
		threadEmployeeAgentService.set(employeeAgentService);
		threadEmployeeAgentTypeService.set(employeeAgentTypeService);
		threadEmployeeAgentStatusService.set(employeeAgentStatusService);
		threadEmployeeAgentKnowledgeService.set(employeeAgentKnowledgeService);
		
		// add all views controlled by SliderView Component
		employeeAgentView = new EmployeeAgentView(context, this);
		sliderView.addView(employeeAgentView);
		views.put(0, employeeAgentView);
	}
		
	@SuppressWarnings("unused")
	@PreDestroy
	private void destroyViews() {
		// destroy thread local bundle services
		threadAddressService.set(null);
		threadEmployeeAgentService.set(null);
		threadEmployeeAgentTypeService.set(null);
		threadEmployeeAgentStatusService.set(null);
		threadEmployeeAgentKnowledgeService.set(null);
		
	}
    
    public static AddressService getAddressService() {
        return threadAddressService.get();
        
    }
    
    public static EmployeeAgentService getEmployeeAgentService() {
        return threadEmployeeAgentService.get();
        
    }
    
    public static EmployeeAgentTypeService getEmployeeAgentTypeService() {
        return threadEmployeeAgentTypeService.get();
        
    }
    
    public static EmployeeAgentStatusService getEmployeeAgentStatusService() {
        return threadEmployeeAgentStatusService.get();
        
    }
    
    public static EmployeeAgentKnowledgeService getEmployeeAgentKnowledgeService() {
        return threadEmployeeAgentKnowledgeService.get();
        
    }
    
	@Override
	public ISliderView getSliderView() {
		return sliderView;
		
	}
	
	@Override
	public IView getSelectedView() {
		return sliderView.getSelectedView();
		
	}
	
	@Override
	public void addListener(IViewChangeListener listener) {
		sliderView.addListener(listener);
		
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
		
		// sliderView
		sliderView = new SliderView();
		sliderView.setImmediate(false);
		sliderView.setWidth("100.0%");
		sliderView.setHeight("100.0%");
		mainLayout.addComponent(sliderView);
		mainLayout.setExpandRatio(sliderView, 1.0f);
		
		return mainLayout;
	}
}
