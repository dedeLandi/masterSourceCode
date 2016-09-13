package br.ufscar.KDM_MANAGEMENT.models.code.methods.signatures;

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
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;

public class KDMSignatureReader {

	private Segment segmentMain = null;
	private CodeModel modelMain = null;
	private Package packageMain = null;
	private ClassUnit classMain = null;
	private MethodUnit methodMain = null;
	
	public KDMSignatureReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMSignatureReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMSignatureReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}
	public KDMSignatureReader(ClassUnit kdmClassUnit) {
		this.classMain = kdmClassUnit;
	}
	public KDMSignatureReader(MethodUnit kdmMethodUnit) {
		this.methodMain = kdmMethodUnit;
	}

	public Map<String, List<Signature>> getAllSignatures() {
		if(this.segmentMain != null){
			return getAllSignaturesSegment();
		}else if(this.modelMain != null){
			return getAllSignaturesModel();
		}else if(this.packageMain != null){
			return getAllSignaturesPackage();
		}else if(this.classMain != null){
			return getAllSignaturesClassUnit();
		}else if(this.methodMain != null){
			return getAllSignaturesMethodUnit();
		}else{
			return null;
		}

	}
	
	
	private Map<String, List<Signature>> getAllSignaturesSegment() {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();
		
		for (KDMModel kdmModel : this.segmentMain.getModel()) {
			
			if (kdmModel instanceof CodeModel) {
				
				allSignaturesUnit.put(kdmModel.getName(), this.getAllSignaturesModel((CodeModel) kdmModel));
				
			}
			
		}
		
		return allSignaturesUnit;
	}
	
	private Map<String, List<Signature>> getAllSignaturesModel() {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();
		
		allSignaturesUnit.put(this.modelMain.getName(), this.getAllSignaturesModel(this.modelMain));
		
		return allSignaturesUnit;
	}
	
	private Map<String, List<Signature>> getAllSignaturesPackage() {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();
		
		allSignaturesUnit.put(this.packageMain.getName(), this.getAllSignaturesPackage(this.packageMain));
		
		return allSignaturesUnit;
	}
	
	private Map<String, List<Signature>> getAllSignaturesClassUnit() {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();
		
		allSignaturesUnit.put(this.classMain.getName(), this.getAllSignaturesClassUnit(this.classMain));
		
		return allSignaturesUnit;
	}
	
	private Map<String, List<Signature>> getAllSignaturesMethodUnit() {
		Map<String, List<Signature>> allSignaturesUnitFromMethodUnit = new HashMap<String, List<Signature>>();
		
		allSignaturesUnitFromMethodUnit.put(this.methodMain.getName(), this.getAllSignaturesMethodUnit(this.methodMain));
		
		return allSignaturesUnitFromMethodUnit;
	}
	
	private List<Signature> getAllSignaturesModel(CodeModel codeModelToSearch) {
		List<Signature> modelSignatures = new ArrayList<Signature>();
		
		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				modelSignatures.addAll(this.getAllSignaturesClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				modelSignatures.addAll(this.getAllSignaturesPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return modelSignatures;
	}
	
	private List<Signature> getAllSignaturesPackage(Package packageToSearch) {
		List<Signature> packageSignatures = new ArrayList<Signature>();
		
		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				packageSignatures.addAll(this.getAllSignaturesClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				packageSignatures.addAll(this.getAllSignaturesPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return packageSignatures;
	}
	
	private List<Signature> getAllSignaturesClassUnit(ClassUnit classToSearch) {
		List<Signature> classSignatures = new ArrayList<Signature>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof Signature){
			
				classSignatures.add((Signature)codeItem);
			
			}else if (codeItem instanceof MethodUnit){
				
				classSignatures.addAll(this.getAllSignaturesMethodUnit((MethodUnit)codeItem));
			
			}
			
		}
		
		return classSignatures;
	}
	
	private List<Signature> getAllSignaturesMethodUnit(MethodUnit methodToSearch) {
		List<Signature> methodSignatures = new ArrayList<Signature>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof Signature){
				
				methodSignatures.add(((Signature)abstractCodeElement));
				 
			}
		}
		
		return methodSignatures;
	}
	
}
