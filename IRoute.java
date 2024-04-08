package taxify;

import java.util.List;

public interface IRoute {

    public boolean hasLocations();
    public ILocation getNextLocation();
    public String toString();
    public List<ILocation> getRoute();
    public void setRoute(List<ILocation> route);

}