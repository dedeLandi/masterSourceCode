package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.methods.parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.interfaces.KDMCodeGenericReader;
import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.factory.KDMModelReaderFactory;

public class KDMParameterReaderImpl implements KDMCodeGenericReader<ParameterUnit> {

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMParameterReaderImpl() {
		super();
	}

	public KDMParameterReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(ParameterUnit elementToValidate) {
		if(this.hasNoFilter){
			return true;
		}else if(this.hasFilterName){
			if(elementToValidate.getName().equalsIgnoreCase(this.filterName)){
				return true;
			}
		}
		return false;
	}	
	@Override
	public Map<String, List<ParameterUnit>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {
				
				allParametersUnit.put(kdmModel.getName(), this.getAllParametersFrom((CodeModel) kdmModel));
				
			}
			
		}
		
		return allParametersUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromModel(CodeModel codeModelToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(codeModelToSearch.getName(), this.getAllParametersFrom(codeModelToSearch));
		
		return allParametersUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromPackage(Package packageToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(packageToSearch.getName(), this.getAllParametersFrom(packageToSearch));
		
		return allParametersUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(classUnitToSearch.getName(), this.getAllParametersFrom(classUnitToSearch));
		
		return allParametersUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(interfaceUnitToSearch.getName(), this.getAllParametersFrom(interfaceUnitToSearch));
		
		return allParametersUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnit.put(enumeratedTypeToSearch.getName(), this.getAllParametersFrom(enumeratedTypeToSearch));
		
		return allParametersUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnitFromMethodUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnitFromMethodUnit.put(methodUnitToSearch.getName(), this.getAllParametersFrom(methodUnitToSearch));
		
		return allParametersUnitFromMethodUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromSignature(Signature signatureUnitToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnitFromMethodUnit = new HashMap<String, List<ParameterUnit>>();
		
		allParametersUnitFromMethodUnit.put("Signature: " + signatureUnitToSearch.getName(), signatureUnitToSearch.getParameterUnit());
		
		return allParametersUnitFromMethodUnit;
	}

	@Override
	public Map<String, List<ParameterUnit>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		Map<String, List<ParameterUnit>> allParametersUnitFromMethodUnit = new HashMap<String, List<ParameterUnit>>();
		
		List<ParameterUnit> paramenterList = new ArrayList<ParameterUnit>();
		paramenterList.add(parameterUnitUnitToSearch);
		allParametersUnitFromMethodUnit.put("ParameterUnit: " + parameterUnitUnitToSearch.getName(), paramenterList );
		
		return allParametersUnitFromMethodUnit;
	}
	
	@Override
	@Deprecated
	public Map<String, List<ParameterUnit>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<ParameterUnit>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		return null;
	}
	
	@Override
	@Deprecated
	public Map<String, List<ParameterUnit>> getAllFromActionElement(ActionElement actionElementToSearch) {
		return null;
	}
	
	private List<ParameterUnit> getAllParametersFrom(CodeModel codeModelToSearch) {
		List<ParameterUnit> modelParameters = new ArrayList<ParameterUnit>();
		
		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				modelParameters.addAll(this.getAllParametersFrom((ClassUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				modelParameters.addAll(this.getAllParametersFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				modelParameters.addAll(this.getAllParametersFrom((EnumeratedType)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof Package){
				
				modelParameters.addAll(this.getAllParametersFrom((Package)abstractCodeElement));
				
			}
			
		}
		
		return modelParameters;
	}
	
	private List<ParameterUnit> getAllParametersFrom(Package packageToSearch) {
		List<ParameterUnit> packageParameters = new ArrayList<ParameterUnit>();
		
		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				packageParameters.addAll(this.getAllParametersFrom((ClassUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				packageParameters.addAll(this.getAllParametersFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
			
				packageParameters.addAll(this.getAllParametersFrom((EnumeratedType)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				packageParameters.addAll(this.getAllParametersFrom((Package)abstractCodeElement));
				
			}
			
		}
		
		return packageParameters;
	}
	
	private List<ParameterUnit> getAllParametersFrom(ClassUnit classToSearch) {
		List<ParameterUnit> classParameters = new ArrayList<ParameterUnit>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				classParameters.addAll(this.getAllParametersFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				classParameters.addAll(this.getAllParametersFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
			
				classParameters.addAll(this.getAllParametersFrom((EnumeratedType)codeItem));
				
			}else if(codeItem instanceof ParameterUnit){
				
				if(validateFilter((ParameterUnit)codeItem)){
					classParameters.add((ParameterUnit)codeItem);
				}
				
			}else if (codeItem instanceof MethodUnit){
				
				classParameters.addAll(this.getAllParametersFrom((MethodUnit)codeItem));
				
			}
			
		}
		
		return classParameters;
	}
	
	private List<ParameterUnit> getAllParametersFrom(InterfaceUnit interfaceToSearch) {
		List<ParameterUnit> interfaceParameters = new ArrayList<ParameterUnit>();
		
		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				interfaceParameters.addAll(this.getAllParametersFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				interfaceParameters.addAll(this.getAllParametersFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
			
				interfaceParameters.addAll(this.getAllParametersFrom((EnumeratedType)codeItem));
				
			}else if(codeItem instanceof ParameterUnit){
				
				if(validateFilter((ParameterUnit)codeItem)){
					interfaceParameters.add((ParameterUnit)codeItem);
				}
				
			}else if (codeItem instanceof MethodUnit){
				
				interfaceParameters.addAll(this.getAllParametersFrom((MethodUnit)codeItem));
				
			}
			
		}
		
		return interfaceParameters;
	}
	
	private List<ParameterUnit> getAllParametersFrom(EnumeratedType enumeratedToSearch) {
		List<ParameterUnit> enumeratedTypeParameters = new ArrayList<ParameterUnit>();
		
		for (CodeItem codeItem : enumeratedToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				enumeratedTypeParameters.addAll(this.getAllParametersFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				enumeratedTypeParameters.addAll(this.getAllParametersFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
			
				enumeratedTypeParameters.addAll(this.getAllParametersFrom((EnumeratedType)codeItem));
				
			}else if(codeItem instanceof ParameterUnit){
			
				if(validateFilter((ParameterUnit)codeItem)){
					enumeratedTypeParameters.add((ParameterUnit)codeItem);
				}
			
			}else if (codeItem instanceof MethodUnit){
				
				enumeratedTypeParameters.addAll(this.getAllParametersFrom((MethodUnit)codeItem));
			
			}
			
		}
		
		return enumeratedTypeParameters;
	}
	
	private List<ParameterUnit> getAllParametersFrom(MethodUnit methodToSearch) {
		List<ParameterUnit> methodParameters = new ArrayList<ParameterUnit>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof Signature){
				
				EList<ParameterUnit> parameterUnit = ((Signature)abstractCodeElement).getParameterUnit();
				for (ParameterUnit parameterUnit2 : parameterUnit) {
					if(validateFilter(parameterUnit2)){
						methodParameters.add(parameterUnit2);
					}
				}
				
			}
		}
		
		return methodParameters;
	}
}
