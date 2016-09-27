package br.ufscar.KDM_MANAGEMENT.models.code.parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;

public class KDMParameterReader {

	private Segment segmentMain = null;
	private CodeModel modelMain = null;
	private Package packageMain = null;
	private ClassUnit classMain = null;
	private InterfaceUnit interfaceMain = null;
	private MethodUnit methodMain = null;
	
	public KDMParameterReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMParameterReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMParameterReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}
	public KDMParameterReader(ClassUnit kdmClassUnit) {
		this.classMain = kdmClassUnit;
	}
	public KDMParameterReader(InterfaceUnit kdmInterfaceUnit) {
		this.interfaceMain = kdmInterfaceUnit;
	}
	public KDMParameterReader(MethodUnit kdmMethodUnit) {
		this.methodMain = kdmMethodUnit;
	}

	public Map<String, List<ParameterUnit>> getAllParameters() {
		if(this.segmentMain != null){
			return getAllParametersSegment();
		}else if(this.modelMain != null){
			return getAllParametersModel();
		}else if(this.packageMain != null){
			return getAllParametersPackage();
		}else if(this.classMain != null){
			return getAllParametersClassUnit();
		}else if(this.interfaceMain != null){
			return getAllParametersInterfaceUnit();
		}else if(this.methodMain != null){
			return getAllParametersMethodUnit();
		}else{
			return null;
		}

	}
	
	
	private Map<String, List<ParameterUnit>> getAllParametersSegment() {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		for (KDMModel kdmModel : this.segmentMain.getModel()) {
			
			if (kdmModel instanceof CodeModel) {
				
				allParametersUnit.put(kdmModel.getName(), this.getAllParametersModel((CodeModel) kdmModel));
				
			}
			
		}
		
		return allParametersUnit;
	}
	
	private Map<String, List<ParameterUnit>> getAllParametersModel() {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(this.modelMain.getName(), this.getAllParametersModel(this.modelMain));
		
		return allParametersUnit;
	}
	
	private Map<String, List<ParameterUnit>> getAllParametersPackage() {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(this.packageMain.getName(), this.getAllParametersPackage(this.packageMain));
		
		return allParametersUnit;
	}
	
	private Map<String, List<ParameterUnit>> getAllParametersClassUnit() {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(this.classMain.getName(), this.getAllParametersClassUnit(this.classMain));
		
		return allParametersUnit;
	}
	
	private Map<String, List<ParameterUnit>> getAllParametersInterfaceUnit() {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(this.interfaceMain.getName(), this.getAllParametersInterfaceUnit(this.interfaceMain));
		
		return allParametersUnit;
	}
	
	private Map<String, List<ParameterUnit>> getAllParametersMethodUnit() {
		Map<String, List<ParameterUnit>> allParametersUnitFromMethodUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnitFromMethodUnit.put(this.methodMain.getName(), this.getAllParametersMethodUnit(this.methodMain));
		
		return allParametersUnitFromMethodUnit;
	}
	
	private List<ParameterUnit> getAllParametersModel(CodeModel codeModelToSearch) {
		List<ParameterUnit> modelParameters = new ArrayList<ParameterUnit>();
		
		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				modelParameters.addAll(this.getAllParametersClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				modelParameters.addAll(this.getAllParametersPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return modelParameters;
	}
	
	private List<ParameterUnit> getAllParametersPackage(Package packageToSearch) {
		List<ParameterUnit> packageParameters = new ArrayList<ParameterUnit>();
		
		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				packageParameters.addAll(this.getAllParametersClassUnit((ClassUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
			
				packageParameters.addAll(this.getAllParametersInterfaceUnit((InterfaceUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				packageParameters.addAll(this.getAllParametersPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return packageParameters;
	}
	
	private List<ParameterUnit> getAllParametersClassUnit(ClassUnit classToSearch) {
		List<ParameterUnit> classParameters = new ArrayList<ParameterUnit>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof ParameterUnit){
				
				classParameters.add((ParameterUnit)codeItem);
				
			}else if (codeItem instanceof MethodUnit){
				
				classParameters.addAll(this.getAllParametersMethodUnit((MethodUnit)codeItem));
				
			}
			
		}
		
		return classParameters;
	}
	
	private List<ParameterUnit> getAllParametersInterfaceUnit(InterfaceUnit interfaceToSearch) {
		List<ParameterUnit> interfaceParameters = new ArrayList<ParameterUnit>();
		
		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {
			
			if(codeItem instanceof ParameterUnit){
			
				interfaceParameters.add((ParameterUnit)codeItem);
			
			}else if (codeItem instanceof MethodUnit){
				
				interfaceParameters.addAll(this.getAllParametersMethodUnit((MethodUnit)codeItem));
			
			}
			
		}
		
		return interfaceParameters;
	}
	
	private List<ParameterUnit> getAllParametersMethodUnit(MethodUnit methodToSearch) {
		List<ParameterUnit> methodParameters = new ArrayList<ParameterUnit>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof Signature){
				
				methodParameters.addAll(((Signature)abstractCodeElement).getParameterUnit());
				 
			}
		}
		
		return methodParameters;
	}
	
}
