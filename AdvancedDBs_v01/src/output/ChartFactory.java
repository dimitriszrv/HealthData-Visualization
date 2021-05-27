package output;

public class ChartFactory {
	
	public ChartFactory() {}
	
	public SystemChart createChart(String generatorType) {
		switch (generatorType) {
		case "BarChart" :
			return new MyBarChart();
		case "ScatterPlot" :
			return new ScatterPlot();
		case "Timeline" :
			return new TimelinesChart();
		default:
			throw new IllegalArgumentException("Unsupported chart : " + generatorType);
		}
	}
}
