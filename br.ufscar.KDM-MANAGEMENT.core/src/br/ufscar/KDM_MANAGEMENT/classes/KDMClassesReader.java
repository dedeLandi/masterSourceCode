package br.ufscar.KDM_MANAGEMENT.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.models.KDMModelReader;

public class KDMClassesReader {

	
	private Segment segmentMain;
	private CodeModel modelMain;
	private Package packageMain;
	
	private Map<String, List<ClassUnit>> classesPerModel = null;
	
	
	public KDMClassesReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMClassesReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMClassesReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}
	
	public Map<String, List<ClassUnit>> getAllClasses() {
		if(this.segmentMain != null){
			return getAllClassesSegment();
		}else if(this.modelMain != null){
			return getAllClassesModel();
		}else if(this.packageMain != null){
			return getAllClassesPackage();
		}else{
			return null;
		}

	}
	
	private Map<String, List<ClassUnit>> getAllClassesSegment() {
		
		this.classesPerModel = new HashMap<String, List<ClassUnit>>();

		Map<String, List<KDMModel>> models = new KDMModelReader(this.segmentMain).getAllCodeModel(); 
		
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {
				
				List<ClassUnit> allClasses = new ArrayList<ClassUnit>();
				
				CodeModel codeModel = (CodeModel) kdmModel;
				
				EList<AbstractCodeElement> elements = codeModel.getCodeElement();
				
				for (int i = 0; i < elements.size() ; i++) {
					
					if (elements.get(i) instanceof Package) {
						
						Package packageKDM = (Package) elements.get(i);
						
						this.getClasses(packageKDM.getCodeElement(), allClasses);
					}
				}
				
				this.classesPerModel.put(nameCodeModel, allClasses);
			}
		}
		
		return this.classesPerModel;
		
	}
	
	private Map<String, List<ClassUnit>> getAllClassesModel() {
		
		this.classesPerModel = new HashMap<String, List<ClassUnit>>();
		
		List<ClassUnit> allClasses = new ArrayList<ClassUnit>();
		
		CodeModel codeModel = (CodeModel) this.modelMain;
		
		EList<AbstractCodeElement> elements = codeModel.getCodeElement();
		
		for (int i = 0; i < elements.size() ; i++) {
			
			if (elements.get(i) instanceof Package) {
				
				Package packageKDM = (Package) elements.get(i);
				
				this.getClasses(packageKDM.getCodeElement(), allClasses);
				
			}
			
		}
		
		this.classesPerModel.put(this.modelMain.getName(), allClasses);
		
		return this.classesPerModel;
		
	}
	
	private Map<String, List<ClassUnit>> getAllClassesPackage() {

		this.classesPerModel = new HashMap<String, List<ClassUnit>>();
		
		List<ClassUnit> allClasses = new ArrayList<ClassUnit>();

		EList<AbstractCodeElement> elements = this.packageMain.getCodeElement();

		for (int i = 0; i < elements.size() ; i++) {

			if (elements.get(i) instanceof Package) {

				Package packageKDM = (Package) elements.get(i);

				this.getClasses(packageKDM.getCodeElement(), allClasses);

			}else if (elements.get(i) instanceof ClassUnit) {

				allClasses.add((ClassUnit) elements.get(i));

			}

		}

		this.classesPerModel.put(this.packageMain.getName(), allClasses);
		
		return this.classesPerModel;

	}
	
	/**
	 * Recursive class search 
	 * @param elements
	 * @param allClasses
	 */
	private void getClasses(EList<AbstractCodeElement> elements, List<ClassUnit> allClasses) {

		for (AbstractCodeElement abstractCodeElement : elements) {

			if (abstractCodeElement instanceof ClassUnit) {

				allClasses.add((ClassUnit) abstractCodeElement);

			} else if (abstractCodeElement instanceof Package) {

				Package packageToPass = (Package) abstractCodeElement;

				getClasses(packageToPass.getCodeElement(), allClasses);

			}

		}

	}
	
}
