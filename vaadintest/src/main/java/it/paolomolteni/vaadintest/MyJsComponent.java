package it.paolomolteni.vaadintest;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

@JavaScript({"http://www.openlayers.org/api/OpenLayers.js",
	"vaadin://myMap.js", 
	"vaadin://mycomponent-connector.js"
	})
public class MyJsComponent extends AbstractJavaScriptComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2057998348007147100L;
	
	@Override
    protected MyComponentState getState() {
        return (MyComponentState) super.getState();
    }
	
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return getState().latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		getState().latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return getState().longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		getState().longitude = longitude;
	}

}
