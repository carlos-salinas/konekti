package com.thingtrack.konekti.view.module.dashboard.internal;

import com.thingtrack.konekti.view.kernel.IWorkbenchContext;
import com.thingtrack.konekti.view.module.dashboard.internal.KonektiPortal.KonektiPortlet;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class YoutubePortlet extends KonektiPortlet {
	private Table tableTest;
	private Embedded thkYoutube;
	private IWorkbenchContext context;
	
	public YoutubePortlet(IWorkbenchContext context, KonektiPortal konektiPortal) {
		konektiPortal.super();
		
		this.context = context;
		
		buildMainLayout();
	}
	
	@Override
	public void addComponent(Component c, int position) {
        super.addComponent(c, position);
        
    };
    
    private void buildMainLayout() {
		// common part: create layout
    	/*tableTest = new Table();
    	tableTest.setImmediate(false);
    	tableTest.setWidth("100%");
    	tableTest.setHeight("100%");
		
    	tableTest.setCaption("Portlet Statistical");*/
    		
    	Panel pnYoutube = new Panel();
    	pnYoutube.setWidth("100%");
    	pnYoutube.setHeight("100%");
    	
    	thkYoutube = new Embedded(null, new ExternalResource("http://www.youtube.com/watch?v=mkKsWguqJRU"));
    	thkYoutube.setAlternateText("Thingtrack Quickstart video");
    	thkYoutube.setMimeType("application/x-shockwave-flash");
    	thkYoutube.setParameter("allowFullScreen", "true");
    	thkYoutube.setSizeFull();
    	
    	pnYoutube.addComponent(thkYoutube);
    	
    	//thkYoutube.setWidth("320px");
    	//thkYoutube.setHeight("265px");
    	        
		// top-level component properties
        addComponent(pnYoutube);
		//addComponent(tableTest);
		
	}
}
