package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.classes;

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

public class KDMClassReaderImpl implements KDMCodeGenericReader<ClassUnit>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMClassReaderImpl() {
		super();
	}

	public KDMClassReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(ClassUnit elementToValidate) {
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
	public Map<String, List<ClassUnit>> getAllFromSegment(Segment segmentToSearch) {
		
		Map<String, List<ClassUnit>> classUnitsPerModel = new HashMap<String, List<ClassUnit>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				classUnitsPerModel.put(kdmModel.getName(), this.getAllClassesFrom((CodeModel) kdmModel));
				
			}
		}

		return classUnitsPerModel;
	}

	@Override
	public Map<String, List<ClassUnit>> getAllFromModel(CodeModel codeModelToSearch) {

		Map<String, List<ClassUnit>> allClassesFromModel = new HashMap<String, List<ClassUnit>>();
		
		allClassesFromModel.put(codeModelToSearch.getName(), this.getAllClassesFrom(codeModelToSearch));
		
		return allClassesFromModel;
	}

	@Override
	public Map<String, List<ClassUnit>> getAllFromPackage(Package packageToSearch) {
		
		Map<String, List<ClassUnit>> allClassesFromPackage = new HashMap<String, List<ClassUnit>>();
		
		allClassesFromPackage.put(packageToSearch.getName(), this.getAllClassesFrom(packageToSearch));
		
		return allClassesFromPackage;
	}

	@Override
	public Map<String, List<ClassUnit>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		
		Map<String, List<ClassUnit>> allClassesFromClassUnit = new HashMap<String, List<ClassUnit>>();
		
		allClassesFromClassUnit.put(classUnitToSearch.getName(), this.getAllClassesFrom(classUnitToSearch));
		
		return allClassesFromClassUnit;
	}

	@Override
	public Map<String, List<ClassUnit>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		
		Map<String, List<ClassUnit>> allClassesFromInterfaceUnit = new HashMap<String, List<ClassUnit>>();
		
		allClassesFromInterfaceUnit.put(interfaceUnitToSearch.getName(), this.getAllClassesFrom(interfaceUnitToSearch));
		
		return allClassesFromInterfaceUnit;
	}

	@Override
	public Map<String, List<ClassUnit>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {

		Map<String, List<ClassUnit>> allClassesFromEnumeratedType = new HashMap<String, List<ClassUnit>>();

		allClassesFromEnumeratedType.put(enumeratedTypeToSearch.getName(), this.getAllClassesFrom(enumeratedTypeToSearch));
		
		return allClassesFromEnumeratedType;
	}

	@Override
	@Deprecated
	public Map<String, List<ClassUnit>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<ClassUnit>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<ClassUnit>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<ClassUnit>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<ClassUnit>> getAllFromActionElement(ActionElement actionElementToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<ClassUnit>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	
	private List<ClassUnit> getAllClassesFrom(CodeModel codeModelToSearch) {
		List<ClassUnit> allClassUnits = new ArrayList<ClassUnit>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				if (validateFilter((ClassUnit) abstractCodeElement)) {
					allClassUnits.add((ClassUnit) abstractCodeElement);
				}
				allClassUnits.addAll(this.getAllClassesFrom((ClassUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				allClassUnits.addAll(this.getAllClassesFrom((InterfaceUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				allClassUnits.addAll(this.getAllClassesFrom((EnumeratedType) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof Package){
				
				allClassUnits.addAll(this.getAllClassesFrom((Package) abstractCodeElement));
				
			}
		
		
		}
		
		return allClassUnits;
	}

	private List<ClassUnit> getAllClassesFrom(ClassUnit classUnitToSearch) {
		List<ClassUnit> allClassUnits = new ArrayList<ClassUnit>();

		for (CodeItem codeItem : classUnitToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				if (validateFilter((ClassUnit) codeItem)) {
					allClassUnits.add((ClassUnit) codeItem);
				}
				allClassUnits.addAll(this.getAllClassesFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				allClassUnits.addAll(this.getAllClassesFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				allClassUnits.addAll(this.getAllClassesFrom((EnumeratedType) codeItem));
				
			}
		
		}
		
		return allClassUnits;
	}


	private List<ClassUnit> getAllClassesFrom(InterfaceUnit interfaceUnitToSearch) {
		List<ClassUnit> allClassUnits = new ArrayList<ClassUnit>();

		for (CodeItem codeItem : interfaceUnitToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				if (validateFilter((ClassUnit) codeItem)) {
					allClassUnits.add((ClassUnit) codeItem);
				}
				allClassUnits.addAll(this.getAllClassesFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				allClassUnits.addAll(this.getAllClassesFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				allClassUnits.addAll(this.getAllClassesFrom((EnumeratedType) codeItem));
				
			}
			
		}
		
		return allClassUnits;
	}
	
	private List<ClassUnit> getAllClassesFrom(EnumeratedType enumeratedTypeToSearch) {
		List<ClassUnit> allClassUnits = new ArrayList<ClassUnit>();

		for (CodeItem codeItem : enumeratedTypeToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				if (validateFilter((ClassUnit) codeItem)) {
					allClassUnits.add((ClassUnit) codeItem);
				}
				allClassUnits.addAll(this.getAllClassesFrom((ClassUnit) codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				allClassUnits.addAll(this.getAllClassesFrom((InterfaceUnit) codeItem));
			
			}else if (codeItem instanceof EnumeratedType) {
				
				allClassUnits.addAll(this.getAllClassesFrom((EnumeratedType) codeItem));
				
			}
		
		}
		
		return allClassUnits;
	}
	
	private List<ClassUnit> getAllClassesFrom(Package packageToSearch) {
		List<ClassUnit> allClassUnits = new ArrayList<ClassUnit>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				
				if (validateFilter((ClassUnit) abstractCodeElement)) {
					allClassUnits.add((ClassUnit) abstractCodeElement);
				}
				allClassUnits.addAll(this.getAllClassesFrom((ClassUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				allClassUnits.addAll(this.getAllClassesFrom((InterfaceUnit) abstractCodeElement));
			
			}else if (abstractCodeElement instanceof EnumeratedType) {
				
				allClassUnits.addAll(this.getAllClassesFrom((EnumeratedType) abstractCodeElement));
				
			}else if (abstractCodeElement instanceof Package) {
				
				allClassUnits.addAll(this.getAllClassesFrom((Package) abstractCodeElement));

			}
		
		}
		
		return allClassUnits;
	}

}
