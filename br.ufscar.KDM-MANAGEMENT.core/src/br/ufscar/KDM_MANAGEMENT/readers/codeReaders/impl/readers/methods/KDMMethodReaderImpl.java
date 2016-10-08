package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.methods;

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

public class KDMMethodReaderImpl implements KDMCodeGenericReader<MethodUnit>{
	
	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMMethodReaderImpl() {
		super();
	}

	public KDMMethodReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(MethodUnit elementToValidate) {
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
	public Map<String, List<MethodUnit>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				if (kdmModel instanceof CodeModel) {

					allMethodsUnit.put(kdmModel.getName(), this.getAllMethodsFrom((CodeModel) kdmModel));

				}

			}
		}
		return allMethodsUnit;
	}

	@Override
	public Map<String, List<MethodUnit>> getAllFromModel(CodeModel codeModelToSearch) {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();

		allMethodsUnit.put(codeModelToSearch.getName(), this.getAllMethodsFrom(codeModelToSearch));

		return allMethodsUnit;
	}

	@Override
	public Map<String, List<MethodUnit>> getAllFromPackage(Package packageToSearch) {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();

		allMethodsUnit.put(packageToSearch.getName(), this.getAllMethodsFrom(packageToSearch));

		return allMethodsUnit;
	}

	@Override
	public Map<String, List<MethodUnit>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();

		allMethodsUnit.put(classUnitToSearch.getName(), this.getAllMethodsFrom(classUnitToSearch));

		return allMethodsUnit;
	}

	@Override
	public Map<String, List<MethodUnit>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();

		allMethodsUnit.put(interfaceUnitToSearch.getName(), this.getAllMethodsFrom(interfaceUnitToSearch));

		return allMethodsUnit;
	}

	@Override
	public Map<String, List<MethodUnit>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();

		allMethodsUnit.put(enumeratedTypeToSearch.getName(), this.getAllMethodsFrom(enumeratedTypeToSearch));

		return allMethodsUnit;
	}

	@Override
	public Map<String, List<MethodUnit>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		Map<String, List<MethodUnit>> allMethodsUnit = new HashMap<String, List<MethodUnit>>();

		List<MethodUnit> methodListed = new ArrayList<MethodUnit>();
		methodListed.add(methodUnitToSearch);
		allMethodsUnit.put(methodUnitToSearch.getName(), methodListed );

		return allMethodsUnit;
	}

	@Override
	@Deprecated
	public Map<String, List<MethodUnit>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<MethodUnit>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<MethodUnit>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<MethodUnit>> getAllFromActionElement(ActionElement actionElementToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<MethodUnit>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	private List<MethodUnit> getAllMethodsFrom(CodeModel codeModelToSearch) {
		List<MethodUnit> modelMethods = new ArrayList<MethodUnit>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				modelMethods.addAll(this.getAllMethodsFrom((ClassUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				modelMethods.addAll(this.getAllMethodsFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				modelMethods.addAll(this.getAllMethodsFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				modelMethods.addAll(this.getAllMethodsFrom((Package)abstractCodeElement));

			}

		}

		return modelMethods;
	}

	private List<MethodUnit> getAllMethodsFrom(Package packageToSearch) {
		List<MethodUnit> packageMethods = new ArrayList<MethodUnit>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				packageMethods.addAll(this.getAllMethodsFrom((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				packageMethods.addAll(this.getAllMethodsFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				packageMethods.addAll(this.getAllMethodsFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				packageMethods.addAll(this.getAllMethodsFrom((Package)abstractCodeElement));

			}

		}

		return packageMethods;
	}

	private List<MethodUnit> getAllMethodsFrom(ClassUnit classToSearch) {
		List<MethodUnit> classMethods = new ArrayList<MethodUnit>();

		for (CodeItem codeItem : classToSearch.getCodeElement()) {

			if(codeItem instanceof ClassUnit){

				classMethods.addAll(this.getAllMethodsFrom((ClassUnit)codeItem));

			}else if(codeItem instanceof InterfaceUnit){
				
				classMethods.addAll(this.getAllMethodsFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				classMethods.addAll(this.getAllMethodsFrom((EnumeratedType)codeItem));

			}else if(codeItem instanceof MethodUnit){

				if(validateFilter((MethodUnit)codeItem)){
					classMethods.add((MethodUnit)codeItem);
				}

			}

		}

		return classMethods;
	}

	private List<MethodUnit> getAllMethodsFrom(InterfaceUnit interfaceToSearch) {
		List<MethodUnit> interfaceMethods = new ArrayList<MethodUnit>();
		
		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				interfaceMethods.addAll(this.getAllMethodsFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				interfaceMethods.addAll(this.getAllMethodsFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				interfaceMethods.addAll(this.getAllMethodsFrom((EnumeratedType)codeItem));
				
			}else if(codeItem instanceof MethodUnit){
				
				if(validateFilter((MethodUnit)codeItem)){
					interfaceMethods.add((MethodUnit)codeItem);
				}
				
			}
			
		}
		
		return interfaceMethods;
	}
	
	private List<MethodUnit> getAllMethodsFrom(EnumeratedType interfaceToSearch) {
		List<MethodUnit> enumeratedTypeMethods = new ArrayList<MethodUnit>();

		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {

			if(codeItem instanceof ClassUnit){

				enumeratedTypeMethods.addAll(this.getAllMethodsFrom((ClassUnit)codeItem));

			}else if(codeItem instanceof InterfaceUnit){
				
				enumeratedTypeMethods.addAll(this.getAllMethodsFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				enumeratedTypeMethods.addAll(this.getAllMethodsFrom((EnumeratedType)codeItem));

			}else if(codeItem instanceof MethodUnit){

				if(validateFilter((MethodUnit)codeItem)){
					enumeratedTypeMethods.add((MethodUnit)codeItem);
				}

			}

		}

		return enumeratedTypeMethods;
	}
}
