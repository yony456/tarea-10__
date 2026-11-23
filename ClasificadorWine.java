import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClasificadorWine {
	public static void main(String[] args) { 
		try {
			Instances wine= DataSource.read("wine.arff");
			// obtenemos la  cantidad de columnas
			int nAtributos= wine.numAttributes();
			//obtenemos cantidad de filas
			int nInstancias= wine.numInstances();
			//Configuracion de la variable objetivo (TARGET/ETIQUETA)
			wine.setClassIndex(nAtributos-1); 
			//----MODELO DE CLASIFICACION---
			Classifier model = new IBk();
		   //---ENTRENAMIENTO DEL MODELO---
			model.buildClassifier(wine);
			//---declaramos nuestra muestra a predecir:
			Instance muestra= new DenseInstance(nAtributos);
			muestra.setDataset(wine); //13.67,   1.25,   1.92,   18, 94 ,   2.1,   1.79,   0.32,   0.73,   3.8,  1.23  ,2.46,   630,class_2	
			muestra.setValue(0 ,13.67);
			muestra.setValue(1, 1.25);
			muestra.setValue(2, 1.92);
			muestra.setValue(3, 18);
			muestra.setValue(4, 94);
			muestra.setValue(5, 2.1);
			muestra.setValue(6, 1.79);
			muestra.setValue(7, 0.32);
			muestra.setValue(8, 0.73);
			muestra.setValue(9, 3.8);
			muestra.setValue(10, 1.23);
			muestra.setValue(11, 2.46);
			muestra.setValue(12, 630);
			//obtenemos el indice del la prediccion
			double indice= model.classifyInstance(muestra);
			System.out.println(indice);
			//obtenemos el valor String de la predicción en el índice obtenido previamente
			String clase = wine.classAttribute().value((int) indice);
			System.out.println(clase);
		} catch (Exception e) {
			  
		}
	}
}
