package br.ufscar.KDM_MANAGEMENT.models.code.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;

public class KDMMethodReader {

	private Segment segmentMain = null;
	private CodeModel modelMain = null;
	private Package packageMain = null;
	private ClassUnit classMain = null;
	
	public KDMMethodReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMMethodReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMMethodReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}
	public KDMMethodReader(ClassUnit kdmClassUnit) {
		this.classMain = kdmClassUnit;
	}
	
	public Map<String, List<MethodUnit>> getAllStorables() {
		if(this.segmentMain != null){
			return getAllMethodsSegment();
		}else if(this.modelMain != null){
			return getAllMethodsModel();
		}else if(this.packageMain != null){
			return getAllMethodsPackage();
		}else if(this.classMain != null){
			return getAllMethodsClassUnit();
		}else{
			return null;
		}

	}
	
	private Map<String, List<MethodUnit>> getAllMethodsSegment() {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();
		
		for (KDMModel kdmModel : this.segmentMain.getModel()) {
			
			if (kdmModel instanceof CodeModel) {
				
				allMethodsUnit.put(kdmModel.getName(), this.getAllMethodsModel((CodeModel) kdmModel));
				
			}
			
		}
		
		return allMethodsUnit;
	}
	
	private Map<String, List<MethodUnit>> getAllMethodsModel() {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();
		
		allMethodsUnit.put(this.modelMain.getName(), this.getAllMethodsModel(this.modelMain));
		
		return allMethodsUnit;
	}
	
	private Map<String, List<MethodUnit>> getAllMethodsPackage() {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();
		
		allMethodsUnit.put(this.packageMain.getName(), this.getAllMethodsPackage(this.packageMain));
		
		return allMethodsUnit;
	}
	
	private Map<String, List<MethodUnit>> getAllMethodsClassUnit() {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();
		
		allMethodsUnit.put(this.classMain.getName(), this.getAllMethodsClassUnit(this.classMain));
		
		return allMethodsUnit;
	}
	
	private List<MethodUnit> getAllMethodsModel(CodeModel codeModelToSearch) {
		List<MethodUnit> modelMethods = new ArrayList<MethodUnit>();
		
		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				modelMethods.addAll(this.getAllMethodsClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				modelMethods.addAll(this.getAllMethodsPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return modelMethods;
	}
	
	private List<MethodUnit> getAllMethodsPackage(Package packageToSearch) {
		List<MethodUnit> packageMethods = new ArrayList<MethodUnit>();
		
		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				packageMethods.addAll(this.getAllMethodsClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				packageMethods.addAll(this.getAllMethodsPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return packageMethods;
	}
	
	private List<MethodUnit> getAllMethodsClassUnit(ClassUnit classToSearch) {
		List<MethodUnit> classMethods = new ArrayList<MethodUnit>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof MethodUnit){
			
				classMethods.add((MethodUnit)codeItem);
			
			}
			
		}
		
		return classMethods;
	}
}
