package it.paolomolteni.vaadintest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	/**
	 * 
	 */
	private ComboBox<String> cmbCountry;
	
	/**
	 * 
	 */
	private ComboBox<String> cmbRegion;
	
	/**
	 * 
	 */
	private Registration cmbCountryListenerRegistration;
	
	/**
	 * 
	 */
	private Registration cmbRegionListenerRegistration;
	
	/**
	 * 
	 */
	public static List<String> country = new ArrayList<String>();
	
	/**
	 * 
	 */
	public static Map<String, List<String>> regionsPerCountry = new HashMap<String, List<String>>();
	
	/**
	 * 
	 */
	public static List<String> regions = new ArrayList<String>();
	
	static {
		country = new ArrayList<String>() {{
        	this.add("Italia");
        	this.add("Francia");
        	this.add("Inghilterra");
        	this.add("Usa");
        }};
        
        regionsPerCountry = new HashMap<String, List<String>>(){{
        	
        	this.put("Italia", new ArrayList<String>() {{
        		this.add("Lombardia");
        		this.add("Lazio");
        	}});
        	
        	this.put("Usa", new ArrayList<String>() {{
        		this.add("New York");
        		this.add("California");
        	}});
        	
        }};
        
        regions = new ArrayList<String>() {{
        	this.add("Lombardia");
        	this.add("Lazio");
        	this.add("New York");
        	this.add("California");
        }}; 
	}

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        cmbCountry = new ComboBox<>("Seleziona lo stato", country);
        cmbCountry.setSizeFull();
        addCmbCountryListener();
        
        cmbRegion = new ComboBox<>("Seleziona la regione", regions);
        cmbRegion.setSizeFull();
        addCmbRegionListener();
        
//        layout.addComponents(cmbCountry, cmbRegion);
        
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//        MyJsComponent myJsComponent = new MyJsComponent();
//        myJsComponent.setId("divMap");
//        myJsComponent.setSizeFull();
//        myJsComponent.setHeight(400, Unit.PIXELS);
//        
//        TextField txtLatitude = new TextField();
//        txtLatitude.setSizeFull();
//        
//        TextField txtLongitude = new TextField();
//        txtLongitude.setSizeFull();
//        
//        Button btnApply = new Button("Apply");
//        btnApply.addClickListener(event -> {
//        	myJsComponent.setLatitude(Double.valueOf(txtLatitude.getValue()));
//        	myJsComponent.setLongitude(Double.valueOf(txtLongitude.getValue()));
//        });
//       
//        
//        layout.addComponents(txtLatitude, txtLongitude, btnApply, myJsComponent);
        
        
        Button downloadButton = new Button("Download image");

        StreamResource myResource = createResource();
        FileDownloader fileDownloader = new FileDownloader(myResource);
        fileDownloader.extend(downloadButton);
        layout.addComponent(downloadButton);;
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    /**
     * 
     */
    private void addCmbCountryListener() {
    	cmbCountryListenerRegistration = cmbCountry.addValueChangeListener(event -> {
    		handlerCmbChangeListener(event);
        });
    }
    
    /**
     * 
     */
    private void addCmbRegionListener() {
    	cmbRegionListenerRegistration = cmbRegion.addValueChangeListener(event -> {
    		handlerCmbChangeListener(event);
        });
    }
    
    /**
     * @param event
     */
    private void handlerCmbChangeListener(ValueChangeEvent<String> event) {
    	
    	if(event.getComponent().equals(cmbCountry)) {
    		// Remove the region listener
    		cmbRegionListenerRegistration.remove();
    		// Reset the region value
    		
    		cmbRegion.setValue(null);
    		if(event.getValue() == null) {
    			// Reset the regions list
    			cmbRegion.setItems(regions);
    		}
    		else {
            	cmbRegion.setItems(getRegionsByCountry(event.getValue()));
    		}
    		
    		// Re-add the region listener
    		addCmbRegionListener();
    		
    	}
    	else if(event.getComponent().equals(cmbRegion)) {
    		
    		// Remove the country listener
    		cmbCountryListenerRegistration.remove();
    		
    		String country = getCountryByRegion(event.getValue());
    		
    		cmbCountry.setValue(country);
    		
    		if(country != null) {
    			cmbRegion.setItems(getRegionsByCountry(country));
    		}
    		else {
    			// Reset the regions list
    			cmbRegion.setItems(regions);
    		}
    		
    		// Re-add the country listener
    		addCmbCountryListener();
    	}
    	
    }
    
    /**
     * @param region
     * @return
     */
    private String getCountryByRegion(String region) {
    	
    	if(region != null) {
    		for(Entry<String, List<String>> entry : regionsPerCountry.entrySet()) {
    			if(entry.getValue().contains(region)) {
    				return entry.getKey();
    			}
    		}
    	}
    	
    	return null;
    }
    
    /**
     * @param country
     * @return
     */
    private List<String> getRegionsByCountry(String country){
    	List<String> regions = new ArrayList<String>();
    	
    	if(country != null && regionsPerCountry.get(country) != null) {
    		regions.addAll(regionsPerCountry.get(country));
    	}
    	
    	return regions;
    }
    
    private StreamResource createResource() {
    	StreamResource streamResource = new StreamResource(new StreamSource() {
            @Override
            public InputStream getStream() {
                String text = "My image";

                BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_3BYTE_BGR);
                bi.getGraphics().drawChars(text.toCharArray(), 0, text.length(), 10, 20);

                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(bi, "png", bos);
                    return new ByteArrayInputStream(bos.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

            }
        }, "myImage.png");
    	
    	streamResource.setCacheTime(0);
    	
    	return streamResource;
    }
}
