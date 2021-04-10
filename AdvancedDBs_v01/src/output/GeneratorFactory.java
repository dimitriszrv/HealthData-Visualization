package output;

public class GeneratorFactory {
	
	public GeneratorFactory() {}
	
	public AbstractChartGenerator createGenerator(String generatorType) {
		switch (generatorType) {
		case "BarChart" :
			return new BarChartGenerator();
		case "ScatterPlot" :
			return new ScatterPlotGenerator();
		case "Timeline" :
			return new TimelinesGenerator();
		default:
			throw new IllegalArgumentException("Unsupported chart : " + generatorType);
		}
	}
}
