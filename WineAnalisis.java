import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WineAnalisis {
	public static void main(String[] args) throws Exception {
		Instances wine= DataSource.read("wine.arff");
		int nAtributos= wine.numAttributes();
		int nInstancias= wine.numInstances();
		System.out.println("cantidad de columnas: " + nAtributos);
		System.out.println("cantidad de registros: " + nInstancias);
		System.out.println("---COLUMNAS DEL DATASET---");
		for (int i = 0; i < nAtributos; i++) {
			System.out.println(wine.attribute(i).name());
		}
		System.out.println("---REGISTRO DE VINOS---");
		/*for (int i = 0; i < nInstancias; i++) {
			System.out.println(wine.instance(i));
		}*/
		// OTRA FORMA
		for (Instance instance : wine) {
			System.out.println(instance);
		}
	}
}
