package br.ufscar.KDM_MANAGEMENT.tests;

import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.structure.Layer;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.load.KDMFileReader;
import br.ufscar.KDM_MANAGEMENT.models.KDMModelReader;
import br.ufscar.KDM_MANAGEMENT.models.code.storables.KDMStorableReader;
import br.ufscar.KDM_MANAGEMENT.models.structure.layers.KDMLayerReader;

public class Test {

	public static void main(String[] args) {

		//RecuperarLayers();
		//RecuperarStorables();
		
	}

	private static void RecuperarStorables() {
		KDMFileReader kdmFileReader = new KDMFileReader("C:/Java/workspaceMestradoMars64/masterSourceCode/br.ufscar.KDM-MANAGEMENT.core/src/br/ufscar/KDM_MANAGEMENT/tests/AprendendoEstruturaKDM_kdm.xmi", KDMFileReader.READ_KDM_TO_SEGMENT);

		Segment raizKdmInitialMap = (Segment) kdmFileReader.getKdmRead();

		
		KDMModelReader kdmModelReader = new KDMModelReader(raizKdmInitialMap);
		
		Map<String, List<KDMModel>> todosOsModelosCode = kdmModelReader.getAllCodeModel();
		
		for (String codeModelName : todosOsModelosCode.keySet()) {
			
			System.out.println("CodeModel analisado: " + codeModelName);
			
			try {
				KDMStorableReader kdmStorableReader = new KDMStorableReader(todosOsModelosCode.get(codeModelName).get(0));
				
				Map<String, List<StorableUnit>> allStorables = kdmStorableReader.getAllStorables();
				for (String nome : allStorables.keySet()) {
					
					System.out.println("	Nome da vez: " + nome);
					
					for (StorableUnit storableUnit : allStorables.get(nome)) {
						System.out.println("		Storable da vez: " + storableUnit.getName());
					}
					
				}
			
			} catch (KDMModelTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

	private static void RecuperarLayers() {
		KDMFileReader kdmFileReader = new KDMFileReader("C:/Java/workspaceMestradoMars64/masterSourceCode/br.ufscar.KDM-MANAGEMENT.core/src/br/ufscar/KDM_MANAGEMENT/tests/initialMap.xmi", KDMFileReader.READ_KDM_TO_SEGMENT);

		Segment raizKdmInitialMap = (Segment) kdmFileReader.getKdmRead();

		KDMModelReader kdmModelReader = new KDMModelReader(raizKdmInitialMap);
		
		String name = "initialMap";
		List<KDMModel> todosOsModelosComNomeIgual = kdmModelReader.getStructureModel(name);
		
		for (KDMModel kdmModel : todosOsModelosComNomeIgual) {
			
			try {
			
				KDMLayerReader kdmLayerReader = new KDMLayerReader(kdmModel);
				Map<String, List<Layer>> allPerLayersModel = kdmLayerReader.getAllLayers();
			
				for (String modelName : allPerLayersModel.keySet()) {
					
					System.out.println("Modelo: " + modelName);
					
					for (Layer layer : allPerLayersModel.get(modelName)) {
						System.out.println("	Layer:" + layer.getName());
						
						for (KDMEntity entity : layer.getImplementation()) {
							if(entity instanceof Package){
								System.out.println("		Implementation:P " + entity.getName() + " - " + entity.toString());
								
								for (AbstractCodeElement codeElement : ((Package) entity).getCodeElement()) {
									System.out.println("			Content: " + codeElement.getName() + " - " + codeElement.toString());
								}
								
							}else if(entity instanceof ClassUnit){
								System.out.println("		Implementation:C " + entity.getName() + " - " + entity.toString());
								for (AbstractCodeElement codeElement : ((ClassUnit) entity).getCodeElement()) {
									System.out.println("			Content: " + codeElement.getName() + " - " + codeElement.toString());
								}
							}else{
								System.out.println("		Implementation:O " + entity.getName() + " - " + entity.toString());
							}
							
					}
						
					}
					
				}
			
			} catch (KDMModelTypeException e) {
				System.out.println("model não é structure");
				e.printStackTrace();
			}
			

		}
	}

}
