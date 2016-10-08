package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.interfaces;

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

public class KDMInterfaceReaderImpl implements KDMCodeGenericReader<InterfaceUnit>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMInterfaceReaderImpl() {
		super();
	}

	public KDMInterfaceReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(InterfaceUnit elementToValidate) {
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
	public Map<String, List<InterfaceUnit>> getAllFromSegment(Segment segmentToSearch) {
		
		Map<String, List<InterfaceUnit>> interfaceUnitsPerModel = new HashMap<String, List<InterfaceUnit>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				interfaceUnitsPerModel.put(kdmModel.getName(), this.getAllInterfaceFrom((CodeModel) kdmModel));
				
			}
		}

		return interfaceUnitsPerModel;
	}

	@Override
	public Map<String, List<InterfaceUnit>> getAllFromModel(CodeModel codeModelToSearch) {

		Map<String, List<InterfaceUnit>> allInterfacesFromModel = new HashMap<String, List<InterfaceUnit>>();
		
		allInterfacesFromModel.put(codeModelToSearch.getName(), this.getAllInterfaceFrom(codeModelToSearch));
		
		return allInterfacesFromModel;
	}

	@Override
	public Map<String, List<InterfaceUnit>> getAllFromPackage(Package packageToSearch) {
		
		Map<String, List<InterfaceUnit>> allInterfacesFromPackage = new HashMap<String, List<InterfaceUnit>>();
		
		allInterfacesFromPackage.put(packageToSearch.getName(), this.getAllInterfacesFrom(packageToSearch));
		
		return allInterfacesFromPackage;
	}

	@Override
	public Map<String, List<InterfaceUnit>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		
		Map<String, List<InterfaceUnit>> allInterfacesFromClassUnit = new HashMap<String, List<InterfaceUnit>>();
		
		allInterfacesFromClassUnit.put(classUnitToSearch.getName(), this.getAllInterfacesFrom(classUnitToSearch));
		
		return allInterfacesFromClassUnit;
	}

	@Override
	public Map<String, List<InterfaceUnit>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		
		Map<String, List<InterfaceUnit>> allInterfacesFromInterfaceUnit = new HashMap<String, List<InterfaceUnit>>();
		
		allInterfacesFromInterfaceUnit.put(interfaceUnitToSearch.getName(), this.getAllInterfacesFrom(interfaceUnitToSearch));
		
		return allInterfacesFromInterfaceUnit;
	}

	@Override
	public Map<String, List<InterfaceUnit>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {

		Map<String, List<InterfaceUnit>> allInterfacesFromEnumeratedType = new HashMap<String, List<InterfaceUnit>>();

		allInterfacesFromEnumeratedType.put(enumeratedTypeToSearch.getName(), this.getAllInterfacesFrom(enumeratedTypeToSearch));
		
		return allInterfacesFromEnumeratedType;
	}

	@Override
	@Deprecated
	public Map<String, List<InterfaceUnit>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<InterfaceUnit>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<InterfaceUnit>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<InterfaceUnit>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<InterfaceUnit>> getAllFromActionElement(ActionElement actionElementToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<InterfaceUnit>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	
	private List<InterfaceUnit> getAllInterfaceFrom(CodeModel codeModelToSearch) {
		List<InterfaceUnit> allInterfaceUnit = new ArrayList<InterfaceUnit>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((ClassUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				if(validateFilter((InterfaceUnit) abstractCodeElement)){
					allInterfaceUnit.add((InterfaceUnit) abstractCodeElement);
				}
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((InterfaceUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((EnumeratedType) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof Package){
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((Package) abstractCodeElement));
				
			}
		
		
		}
		
		return allInterfaceUnit;
	}

	private List<InterfaceUnit> getAllInterfacesFrom(ClassUnit classUnitToSearch) {
		List<InterfaceUnit> allInterfaceUnit = new ArrayList<InterfaceUnit>();

		for (CodeItem codeItem : classUnitToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				if(validateFilter((InterfaceUnit) codeItem)){
					allInterfaceUnit.add((InterfaceUnit) codeItem);
				}
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((EnumeratedType) codeItem));
				
			}
		
		}
		
		return allInterfaceUnit;
	}


	private List<InterfaceUnit> getAllInterfacesFrom(InterfaceUnit interfaceUnitToSearch) {
		List<InterfaceUnit> allInterfaceUnit = new ArrayList<InterfaceUnit>();

		for (CodeItem codeItem : interfaceUnitToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				if(validateFilter((InterfaceUnit) codeItem)){
					allInterfaceUnit.add((InterfaceUnit) codeItem);
				}
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((EnumeratedType) codeItem));
				
			}
			
		}
		
		return allInterfaceUnit;
	}
	
	private List<InterfaceUnit> getAllInterfacesFrom(EnumeratedType enumeratedTypeToSearch) {
		List<InterfaceUnit> allInterfaceUnit = new ArrayList<InterfaceUnit>();

		for (CodeItem codeItem : enumeratedTypeToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				if(validateFilter((InterfaceUnit) codeItem)){
					allInterfaceUnit.add((InterfaceUnit) codeItem);
				}
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((EnumeratedType) codeItem));
				
			}
		
		}
		
		return allInterfaceUnit;
	}
	
	private List<InterfaceUnit> getAllInterfacesFrom(Package packageToSearch) {
		List<InterfaceUnit> allInterfaceUnit = new ArrayList<InterfaceUnit>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((ClassUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				if(validateFilter((InterfaceUnit) abstractCodeElement)){
					allInterfaceUnit.add((InterfaceUnit) abstractCodeElement);
				}
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((InterfaceUnit) abstractCodeElement));
			
			}else if (abstractCodeElement instanceof EnumeratedType) {
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((EnumeratedType) abstractCodeElement));
				
			}else if (abstractCodeElement instanceof Package) {
				
				allInterfaceUnit.addAll(this.getAllInterfacesFrom((Package) abstractCodeElement));

			}
		
		}
		
		return allInterfaceUnit;
	}

}
