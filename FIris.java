import java.awt.EventQueue;  

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FIris extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable grilla;
	private JTextField caja1;
	private JTextField caja2;
	private JTextField caja3;
	private JTextField caja4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FIris frame = new FIris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FIris() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 109);
		contentPane.add(scrollPane);
		
		grilla = new JTable();
		grilla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				caja1.setText(String.valueOf(grilla.getValueAt(grilla.getSelectedRow(), 0)));
				caja2.setText(String.valueOf(grilla.getValueAt(grilla.getSelectedRow(), 1)));
				caja3.setText(String.valueOf(grilla.getValueAt(grilla.getSelectedRow(), 2)));
				caja4.setText(String.valueOf(grilla.getValueAt(grilla.getSelectedRow(), 3)));
			}
		});
		scrollPane.setViewportView(grilla);
		
		caja1 = new JTextField();
		caja1.setBounds(10, 131, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		
		caja2 = new JTextField();
		caja2.setBounds(117, 131, 86, 20);
		contentPane.add(caja2);
		caja2.setColumns(10);
		
		caja3 = new JTextField();
		caja3.setBounds(236, 131, 86, 20);
		contentPane.add(caja3);
		caja3.setColumns(10);
		
		caja4 = new JTextField();
		caja4.setBounds(338, 131, 86, 20);
		contentPane.add(caja4);
		caja4.setColumns(10);
		
		JButton btnNewButton = new JButton("PREDECIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Instances iris= DataSource.read("iris.arff"); //cargamos el dataset
					Classifier model=new J48();//ÁRBOL DE DECISIÓN
					iris.setClassIndex(iris.numAttributes()-1);//indicamos que la etiqueta es el índice 4
					model.buildClassifier(iris);//entrenamiento
					Instance muestra= new DenseInstance(iris.numAttributes());//declaramos un fila
					muestra.setDataset(iris);//establecemos el dataset de la fila y sus valores
					muestra.setValue(0, Double.parseDouble(caja1.getText()));
					muestra.setValue(1, Double.parseDouble(caja2.getText()));
					muestra.setValue(2, Double.parseDouble(caja3.getText()));
					muestra.setValue(3, Double.parseDouble(caja4.getText()));

					double indice= model.classifyInstance(muestra);//realizamos una predicción y obtenemos el índice de la clase
					String clase= iris.classAttribute().value((int) indice);//obtenemos la clase String en el índice obtenido
					JOptionPane.showMessageDialog(null, clase);//mostramos dicha clase
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBounds(164, 184, 98, 46);
		contentPane.add(btnNewButton);
		cargarDatos();
	}

	private void cargarDatos() {
		try {
			Instances iris= DataSource.read("iris.arff");
			//obtener los atributos de iris en un array
			String[] atributos= new String[iris.numAttributes()];
			for (int i = 0; i < atributos.length; i++) {
				atributos[i]= iris.attribute(i).name();//pasamos cada gnombre de atributo al indice correspondiente de nuestro array 
				//System.out.println(atributos[i]);
			}
			DefaultTableModel model= new DefaultTableModel(null, atributos);
			for (Instance i: iris) {
				model.addRow(new Object[] {
						i.value(0),
						i.value(1),
						i.value(2),
						i.value(3),
						i.stringValue(4)
				});
			}
			grilla.setModel(model);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
