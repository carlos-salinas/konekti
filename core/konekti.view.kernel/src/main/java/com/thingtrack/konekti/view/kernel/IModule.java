package com.thingtrack.konekti.view.kernel;

import com.thingtrack.konekti.view.kernel.ui.layout.IViewContainer;


public interface IModule {
	
    public String getName();
    
    public String getDescription();
    
    public IViewContainer createViewComponent(IWorkbenchContext context);
   
}