import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClasificadorIris1 {
	public static void main(String[] args) { 
		try {
			//Carga de nuestra dataset
			Instances iris= DataSource.read("iris.arff");
			// obtenemos la  cantidad de columnas
			int nAtributos= iris.numAttributes();
			//obtenemos cantidad de filas
			int nInstancias= iris.numInstances();
			//Configuracion de la variable objetivo (TARGET/ETIQUETA)
			iris.setClassIndex(nAtributos-1); 
			//----MODELO DE CLASIFICACION---
			Classifier model = new IBk();
		   //---ENTRENAMIENTO DEL MODELO---
			model.buildClassifier(iris);
			//---declaramos nuestra muestra a predecir:
			Instance muestra= new DenseInstance(nAtributos);
			//establecemos el dataset de origen de la muestra
			muestra.setDataset(iris); //4.3,3,1.1,0.1
			//insertamos valores para cada atributo
			muestra.setValue(0, 4.3);
			muestra.setValue(1, 3);
			muestra.setValue(2, 1.1);
			muestra.setValue(3, 0.1);
			//obtenemos el indice del la prediccion
			double indice= model.classifyInstance(muestra);
			System.out.println(indice);
			//obtenemos el valor String de la predicción en el índice obtenido previamente
			String clase = iris.classAttribute().value((int) indice);
			System.out.println(clase);
		} catch (Exception e) {
			  
		}
	}
}
