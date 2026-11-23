import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class IrisAnalisis {
	public static void main(String[] args) throws Exception {
		Instances iris= DataSource.read("iris.arff");
		int nAtributos= iris.numAttributes();
		int nInstancias= iris.numInstances();
		System.out.println("cantidad de columnas: " + nAtributos);
		System.out.println("cantidad de registros: " + nInstancias);
		System.out.println("---COLUMNAS DEL DATASET---");
		for (int i = 0; i < nAtributos; i++) {
			System.out.println(iris.attribute(i).name());
		}
		System.out.println("---REGISTRO DE FLORES---");
		/*for (int i = 0; i < nInstancias; i++) {
			System.out.println(iris.instance(i));
		}*/
		// OTRA FORMA
		for (Instance instance : iris) {
			System.out.println(instance);
		}
	}
}
