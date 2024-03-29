package com.thingtrack.konekti.view.web.form.internal;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.GeocodingException;
import org.vaadin.addons.locationtextfield.LocationProvider;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeNotifier;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class LatLonInputComponent extends CustomField implements
		ValueChangeNotifier {

	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private Button latLonFetchButton;
	@AutoGenerated
	private TextField lonTextField;
	@AutoGenerated
	private TextField latTextField;

	private DecimalFormat decimalFormat;

	private ExtendedURLConnectionGeocoder<GeocodedLocation> locationProvider;

	private List<ValueChangeListener> listeners;

	private GeocodedLocation geocodedLocation;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public LatLonInputComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		listeners = new ArrayList<ValueChangeListener>();
		
		latTextField.setNullRepresentation("");
		lonTextField.setNullRepresentation("");
		
		latLonFetchButton.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {

				if(locationProvider == null)
					throw new RuntimeException("The location provider is null");
				
				try {
					
					double latitude = (Double) decimalFormat.parse((String)latTextField.getValue());
					double longitude = (Double) decimalFormat.parse((String)lonTextField.getValue());
					
					List<GeocodedLocation> geocodedLocations = new ArrayList<GeocodedLocation>((Set<GeocodedLocation>) locationProvider.reverseGeocode(latitude, longitude));
					
					if(geocodedLocations.size() > 0)
						LatLonInputComponent.this.geocodedLocation = geocodedLocations.get(0);
					
					// Notify listeners a new geological location
					for(ValueChangeListener listener : listeners)
						listener.valueChange(new Field.ValueChangeEvent(LatLonInputComponent.this));
				
					
				}catch(ParseException e){
					// Nothing
				}
				catch (GeocodingException e) {
					throw new RuntimeException("No valid <latitude, longitude>");
				}
				
			}
		});
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("390px");
		mainLayout.setHeight("65px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("390px");
		setHeight("65px");

		// latTextField
		latTextField = new TextField();
		latTextField.setImmediate(false);
		latTextField.setWidth("-1px");
		latTextField.setHeight("-1px");
		latTextField.setInputPrompt("latitud");
		mainLayout.addComponent(latTextField);

		// lonTextField
		lonTextField = new TextField();
		lonTextField.setImmediate(false);
		lonTextField.setWidth("-1px");
		lonTextField.setHeight("-1px");
		lonTextField.setInputPrompt("longitud");
		mainLayout.addComponent(lonTextField);

		// latLonFetchButton
		latLonFetchButton = new Button();
		latLonFetchButton.setCaption("Buscar");
		latLonFetchButton.setImmediate(true);
		latLonFetchButton.setWidth("-1px");
		latLonFetchButton.setHeight("-1px");
		mainLayout.addComponent(latLonFetchButton);
		mainLayout.setExpandRatio(latLonFetchButton, 1.0f);

		return mainLayout;
	}

	@Override
	public void attach() {
		super.attach();

		decimalFormat = (DecimalFormat) DecimalFormat
				.getInstance(getApplication().getLocale());
		
		decimalFormat.setParseIntegerOnly(false);
		decimalFormat.setMaximumFractionDigits(6);
	}

	public void setLocationProvider(MapQuestNominatimGeocoder locationProvider) {
		this.locationProvider = locationProvider;
	}
	
	public void setLocationProvider(ExtendedGoogleGeocoder locationProvider) {
		this.locationProvider = locationProvider;
	}

	@Override
	public void addListener(ValueChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ValueChangeListener listener) {
		listeners.remove(listener);
	}

	@Override
	public Class<?> getType() {
		return GeocodedLocation.class;
	}

	@Override
	public Object getValue() {
		return geocodedLocation;
	}

	@Override
	public void setValue(Object newValue) throws ReadOnlyException,
			ConversionException {

		if (newValue != null && !(newValue instanceof GeocodedLocation))
			throw new ConversionException("There is an "
					+ GeocodedLocation.class.getSimpleName());

		geocodedLocation = (GeocodedLocation) newValue;
		
		//Nullable location
		if(geocodedLocation == null)
		{
			latTextField.setValue(null);
			lonTextField.setValue(null);
		}
		
		// It is not geocoded
		else if(geocodedLocation.getGeocodedAddress() == null){
			try {
				List<GeocodedLocation> geocodedLocations = new ArrayList<GeocodedLocation>((Set<GeocodedLocation>) locationProvider.reverseGeocode(geocodedLocation.getLat(), geocodedLocation.getLon()));
				
				if(geocodedLocations.size() > 0 )
					this.geocodedLocation = geocodedLocations.get(0);
				
				// Set the text values
				latTextField.setValue(decimalFormat.format(geocodedLocation.getLat()));
				lonTextField.setValue(decimalFormat.format(geocodedLocation.getLon()));
				
			} catch (GeocodingException e) {
				throw new RuntimeException(e);
			}
		}
		
		// Notify listeners a new geological location
		for(ValueChangeListener listener : listeners)
			listener.valueChange(new Field.ValueChangeEvent(LatLonInputComponent.this));

	}

}
