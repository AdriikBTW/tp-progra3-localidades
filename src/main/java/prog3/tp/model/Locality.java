	package prog3.tp.model;

import java.io.Serializable;
import java.util.Objects;

public class Locality implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _name;
    private String _state;
    private double _latitude;
    private double _longitude;

    public Locality(String name, String state, double latitude, double longitude) {
        this._name = name;
        this._state = state;
        this._latitude = latitude;
        this._longitude = longitude;
    }
    
    public String getName() {
		return _name;
	}
	public String getState() {
		return _state;
	}
	public double getLatitude() {
		return _latitude;
	}
	public double getLongitude() {
		return _longitude;
	}
	
	@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Locality)) return false;

	        Locality other = (Locality) o;

	        return Double.compare(_latitude, other._latitude) == 0
	                && Double.compare(_longitude, other._longitude) == 0
	                && Objects.equals(_name, other._name)
	                && Objects.equals(_state, other._state);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(_name, _state, _latitude, _longitude);
	    }

	    @Override
	    public String toString() {
	        return _name + " (" + _state + ")";
	    }
}
