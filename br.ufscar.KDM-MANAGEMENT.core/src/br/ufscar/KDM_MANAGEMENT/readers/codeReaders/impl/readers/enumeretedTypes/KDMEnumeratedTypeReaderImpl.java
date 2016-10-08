package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.enumeretedTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class KDMEnumeratedTypeReaderImpl implements KDMCodeGenericReader<EnumeratedType>{
	
	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMEnumeratedTypeReaderImpl() {
		super();
	}

	public KDMEnumeratedTypeReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(EnumeratedType elementToValidate) {
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
	public Map<String, List<EnumeratedType>> getAllFromSegment(Segment segmentToSearch) {
		
		Map<String, List<EnumeratedType>> enumeratedTypeUnitsPerModel = new HashMap<String, List<EnumeratedType>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				enumeratedTypeUnitsPerModel.put(kdmModel.getName(), this.getAllEnumeratedTypeFrom((CodeModel) kdmModel));
				
			}
		}

		return enumeratedTypeUnitsPerModel;
	}

	@Override
	public Map<String, List<EnumeratedType>> getAllFromModel(CodeModel codeModelToSearch) {

		Map<String, List<EnumeratedType>> allEnumeratedTypeFromModel = new HashMap<String, List<EnumeratedType>>();
		
		allEnumeratedTypeFromModel.put(codeModelToSearch.getName(), this.getAllEnumeratedTypeFrom(codeModelToSearch));
		
		return allEnumeratedTypeFromModel;
	}

	@Override
	public Map<String, List<EnumeratedType>> getAllFromPackage(Package packageToSearch) {
		
		Map<String, List<EnumeratedType>> allEnumeratedTypeFromPackage = new HashMap<String, List<EnumeratedType>>();
		
		allEnumeratedTypeFromPackage.put(packageToSearch.getName(), this.getAllEnumeratedTypeFrom(packageToSearch));
		
		return allEnumeratedTypeFromPackage;
	}

	@Override
	public Map<String, List<EnumeratedType>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		
		Map<String, List<EnumeratedType>> allEnumeratedTypeFromClassUnit = new HashMap<String, List<EnumeratedType>>();
		
		allEnumeratedTypeFromClassUnit.put(classUnitToSearch.getName(), this.getAllEnumeratedTypeFrom(classUnitToSearch));
		
		return allEnumeratedTypeFromClassUnit;
	}

	@Override
	public Map<String, List<EnumeratedType>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		
		Map<String, List<EnumeratedType>> allEnumeratedTypeFromInterfaceUnit = new HashMap<String, List<EnumeratedType>>();
		
		allEnumeratedTypeFromInterfaceUnit.put(interfaceUnitToSearch.getName(), this.getAllEnumeratedTypeFrom(interfaceUnitToSearch));
		
		return allEnumeratedTypeFromInterfaceUnit;
	}

	@Override
	public Map<String, List<EnumeratedType>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {

		Map<String, List<EnumeratedType>> allEnumeratedTypeFromEnumeratedType = new HashMap<String, List<EnumeratedType>>();

		allEnumeratedTypeFromEnumeratedType.put(enumeratedTypeToSearch.getName(), this.getAllEnumeratedTypeFrom(enumeratedTypeToSearch));
		
		return allEnumeratedTypeFromEnumeratedType;
	}

	@Override
	@Deprecated
	public Map<String, List<EnumeratedType>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<EnumeratedType>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<EnumeratedType>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<EnumeratedType>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<EnumeratedType>> getAllFromActionElement(ActionElement actionElementToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<EnumeratedType>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	
	private List<EnumeratedType> getAllEnumeratedTypeFrom(CodeModel codeModelToSearch) {
		List<EnumeratedType> allEnumeratedType = new ArrayList<EnumeratedType>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((ClassUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((InterfaceUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				if (validateFilter((EnumeratedType) abstractCodeElement)) {
					allEnumeratedType.add((EnumeratedType) abstractCodeElement);
				}
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((EnumeratedType) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof Package){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((Package) abstractCodeElement));
				
			}
		
		
		}
		
		return allEnumeratedType;
	}

	private List<EnumeratedType> getAllEnumeratedTypeFrom(ClassUnit classUnitToSearch) {
		List<EnumeratedType> allEnumeratedType = new ArrayList<EnumeratedType>();

		for (CodeItem codeItem : classUnitToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				if (validateFilter((EnumeratedType) codeItem)) {
					allEnumeratedType.add((EnumeratedType) codeItem);
				}
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((EnumeratedType) codeItem));
				
			}
		
		}
		
		return allEnumeratedType;
	}


	private List<EnumeratedType> getAllEnumeratedTypeFrom(InterfaceUnit interfaceUnitToSearch) {
		List<EnumeratedType> allEnumeratedType = new ArrayList<EnumeratedType>();

		for (CodeItem codeItem : interfaceUnitToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				if (validateFilter((EnumeratedType) codeItem)) {
					allEnumeratedType.add((EnumeratedType) codeItem);
				}
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((EnumeratedType) codeItem));
				
			}
			
		}
		
		return allEnumeratedType;
	}
	
	private List<EnumeratedType> getAllEnumeratedTypeFrom(EnumeratedType enumeratedTypeToSearch) {
		List<EnumeratedType> allEnumeratedType = new ArrayList<EnumeratedType>();

		for (CodeItem codeItem : enumeratedTypeToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				if (validateFilter((EnumeratedType) codeItem)) {
					allEnumeratedType.add((EnumeratedType) codeItem);
				}
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((EnumeratedType) codeItem));
				
			}
		
		}
		
		return allEnumeratedType;
	}
	
	private List<EnumeratedType> getAllEnumeratedTypeFrom(Package packageToSearch) {
		List<EnumeratedType> allEnumeratedType = new ArrayList<EnumeratedType>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((ClassUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((InterfaceUnit) abstractCodeElement));
			
			}else if (abstractCodeElement instanceof EnumeratedType) {
				
				if (validateFilter((EnumeratedType) abstractCodeElement)) {
					allEnumeratedType.add((EnumeratedType) abstractCodeElement);
				}
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((EnumeratedType) abstractCodeElement));
				
			}else if (abstractCodeElement instanceof Package) {
				
				allEnumeratedType.addAll(this.getAllEnumeratedTypeFrom((Package) abstractCodeElement));

			}
		
		}
		
		return allEnumeratedType;
	}

}
