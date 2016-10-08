package br.ufscar.KDM_MANAGEMENT.tests;

import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMEntity;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.structure.Layer;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.load.KDMFileReader;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.classes.KDMClassReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.methods.KDMMethodReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.storables.KDMStorableReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.impl.readers.KDMModelReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.readers.layers.KDMLayerReaderImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.factory.RecoverHierarchyFactory;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.interfaces.RecoverHierarchy;

public class Test {

	public static void main(String[] args) {

		//RecuperarLayers();
		//RecuperarStorables();
		
		RecuperarCaminhosDaHierarquia();
	}

	private static void RecuperarCaminhosDaHierarquia() {
		KDMFileReader kdmFileReader = new KDMFileReader("C:/Java/workspaceMestradoMars64/masterSourceCode/br.ufscar.KDM-MANAGEMENT.core/src/br/ufscar/KDM_MANAGEMENT/tests/initialMap.xmi", KDMFileReader.READ_KDM_TO_SEGMENT);

		Segment raizKdmInitialMap = (Segment) kdmFileReader.getKdmRead();

		KDMModelReaderImpl kdmModelReader = new KDMModelReaderImpl(raizKdmInitialMap);
		
		String name = "SystemExampleMVC-Simples";
		List<KDMModel> todosOsModelosComNomeIgual = kdmModelReader.getCodeModel(name);
		
		for (KDMModel kdmModel : todosOsModelosComNomeIgual) {
			
			try {
				KDMClassReaderImpl kdmClassReader = new KDMClassReaderImpl(kdmModel);
				
				Map<String, List<ClassUnit>> allClasses = kdmClassReader.getAllClasses();
				for (String nome : allClasses.keySet()) {

					System.out.println("Modelo nome: " + nome);
				
					for (ClassUnit classUnit : allClasses.get(nome)) {
						
						RecoverHierarchy recoverHierarchyComplete = RecoverHierarchyFactory.eINSTANCE.createRecoverHierarchyComplete();

						System.out.println("	Classe nome: " + classUnit.getName() + " ( " + recoverHierarchyComplete.getHierarchyOf(classUnit) + " )");
						
						KDMMethodReaderImpl kdmMethodReader = new KDMMethodReaderImpl(classUnit);
						
						Map<String, List<MethodUnit>> allMethodsClass = kdmMethodReader.getAllMethods();
						
						for (String nomeMetodo : allMethodsClass.keySet()) {
							
							for (MethodUnit methodUnit : allMethodsClass.get(nomeMetodo)) {
								
								System.out.println("		Metodos nome: " + methodUnit.getName() + " ( " + recoverHierarchyComplete.getHierarchyOf(methodUnit) + " )" );
								
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

	private static void RecuperarStorables() {
		KDMFileReader kdmFileReader = new KDMFileReader("C:/Java/workspaceMestradoMars64/masterSourceCode/br.ufscar.KDM-MANAGEMENT.core/src/br/ufscar/KDM_MANAGEMENT/tests/AprendendoEstruturaKDM_kdm.xmi", KDMFileReader.READ_KDM_TO_SEGMENT);

		Segment raizKdmInitialMap = (Segment) kdmFileReader.getKdmRead();

		
		KDMModelReaderImpl kdmModelReader = new KDMModelReaderImpl(raizKdmInitialMap);
		
		Map<String, List<KDMModel>> todosOsModelosCode = kdmModelReader.getAllCodeModel();
		
		for (String codeModelName : todosOsModelosCode.keySet()) {
			
			System.out.println("CodeModel analisado: " + codeModelName);
			
			try {
				KDMStorableReaderImpl kdmStorableReader = new KDMStorableReaderImpl(todosOsModelosCode.get(codeModelName).get(0));
				
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

		KDMModelReaderImpl kdmModelReader = new KDMModelReaderImpl(raizKdmInitialMap);
		
		String name = "initialMap";
		List<KDMModel> todosOsModelosComNomeIgual = kdmModelReader.getStructureModel(name);
		
		for (KDMModel kdmModel : todosOsModelosComNomeIgual) {
			
			try {
			
				KDMLayerReaderImpl kdmLayerReader = new KDMLayerReaderImpl(kdmModel);
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
