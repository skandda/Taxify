package taxify;

public interface IApplicationSimulator {

    public void show();
    public void showStatistics();
    public void update();
    public int getTotalServices();
	public void requestService(VehicleType vehicleType, SoundType soundType);
    
}