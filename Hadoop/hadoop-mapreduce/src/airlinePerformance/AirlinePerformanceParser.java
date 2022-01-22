package airlinePerformance;

import org.apache.hadoop.io.Text;

public class AirlinePerformanceParser {
	
	private int year;
	private int month;
	private int arrive;
	private int arriveDelaytime = 0;
	private int departureDelaytime = 0;
	private int distance = 0;
	private boolean arriveDelayAvailable = true;
	private boolean departureDelayAvailable = true;
	private boolean distanceAvailable = true;
	private String uniqueCarrier;

	public AirlinePerformanceParser(Text text) {
		String[] columns = text.toString().split(",");
		year = Integer.parseInt(columns[0]);
		month = Integer.parseInt(columns[1]);
		uniqueCarrier = columns[8];

		if (columns[15].equals("NA")) {
			departureDelayAvailable = false;
		} else {
			departureDelaytime = Integer.parseInt(columns[15]);
		}

		if (columns[14].equals("NA")) {
			arriveDelayAvailable = false;
		} else {
			arriveDelaytime = Integer.parseInt(columns[14]);
		}

		if (columns[18].equals("NA")) {
			distanceAvailable = false;
		} else {
			distance = Integer.parseInt(columns[18]);
		}

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getArrive() {
		return arrive;
	}

	public void setArrive(int arrive) {
		this.arrive = arrive;
	}

	public int getArriveDelaytime() {
		return arriveDelaytime;
	}

	public void setArriveDelaytime(int arriveDelaytime) {
		this.arriveDelaytime = arriveDelaytime;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isArriveDelayAvailable() {
		return arriveDelayAvailable;
	}

	public void setArriveDelayAvailable(boolean arriveDelayAvailable) {
		this.arriveDelayAvailable = arriveDelayAvailable;
	}

	public boolean isDepartureDelayAvailable() {
		return departureDelayAvailable;
	}

	public void setDepartureDelayAvailable(boolean departureDelayAvailable) {
		this.departureDelayAvailable = departureDelayAvailable;
	}

	public boolean isDistanceAvailable() {
		return distanceAvailable;
	}

	public void setDistanceAvailable(boolean distanceAvailable) {
		this.distanceAvailable = distanceAvailable;
	}

	public String getUniqueCarrier() {
		return uniqueCarrier;
	}

	public void setUniqueCarrier(String uniqueCarrier) {
		this.uniqueCarrier = uniqueCarrier;
	}

	public int getDepartureDelaytime() {
		return departureDelaytime;
	}

	public void setDepartureDelaytime(int departureDelaytime) {
		this.departureDelaytime = departureDelaytime;
	}


}
