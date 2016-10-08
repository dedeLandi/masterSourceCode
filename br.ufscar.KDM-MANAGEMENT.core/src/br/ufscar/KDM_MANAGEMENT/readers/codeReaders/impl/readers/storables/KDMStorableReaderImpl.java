package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.storables;

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

public class KDMStorableReaderImpl implements KDMCodeGenericReader<StorableUnit>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMStorableReaderImpl() {
		super();
	}

	public KDMStorableReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(StorableUnit elementToValidate) {
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
	public Map<String, List<StorableUnit>> getAllFromSegment(Segment segmentToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				allStorablesUnit.put(kdmModel.getName(), this.getAllStorablesFrom((CodeModel) kdmModel));

			}

		}

		return allStorablesUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromModel(CodeModel codeModelToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnit.put(codeModelToSearch.getName(), this.getAllStorablesFrom(codeModelToSearch));

		return allStorablesUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromPackage(Package packageToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnit.put(packageToSearch.getName(), this.getAllStorablesFrom(packageToSearch));

		return allStorablesUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromClassUnit(ClassUnit classUnitToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnit.put(classUnitToSearch.getName(), this.getAllStorablesFrom(classUnitToSearch));

		return allStorablesUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnit.put(interfaceUnitToSearch.getName(), this.getAllStorablesFrom(interfaceUnitToSearch));

		return allStorablesUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnit.put(enumeratedTypeToSearch.getName(), this.getAllStorablesFrom(enumeratedTypeToSearch));

		return allStorablesUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnitFromMethodUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnitFromMethodUnit.put(methodUnitToSearch.getName(), this.getAllStorablesFrom(methodUnitToSearch));

		return allStorablesUnitFromMethodUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnitFromBlockUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnitFromBlockUnit.put("Block Unit: " + blockUnitToSearch.getName(), this.getAllStorablesFrom(blockUnitToSearch));

		return allStorablesUnitFromBlockUnit;
	}

	@Override
	public Map<String, List<StorableUnit>> getAllFromActionElement(ActionElement actionElementToSearch) {

		Map<String, List<StorableUnit>> allStorablesUnitFromBlockUnit = new HashMap<String, List<StorableUnit>>();

		allStorablesUnitFromBlockUnit.put("Action Element: " + actionElementToSearch.getName(), this.getAllStorablesFrom(actionElementToSearch));

		return allStorablesUnitFromBlockUnit;
	}

	@Override
	@Deprecated
	public Map<String, List<StorableUnit>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<StorableUnit>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<StorableUnit>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}

	//fazer as pesquisas	
	private List<StorableUnit> getAllStorablesFrom(CodeModel codeModelToSearch) {
		List<StorableUnit> modelStorables = new ArrayList<StorableUnit>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				modelStorables.addAll(this.getAllStorablesFrom((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof InterfaceUnit){

				modelStorables.addAll(this.getAllStorablesFrom((InterfaceUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof EnumeratedType){

				modelStorables.addAll(this.getAllStorablesFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				modelStorables.addAll(this.getAllStorablesFrom((Package)abstractCodeElement));

			}

		}

		return modelStorables;
	}

	private List<StorableUnit> getAllStorablesFrom(Package packageToSearch) {
		List<StorableUnit> packageStorables = new ArrayList<StorableUnit>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				packageStorables.addAll(this.getAllStorablesFrom((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof InterfaceUnit){

				packageStorables.addAll(this.getAllStorablesFrom((InterfaceUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof EnumeratedType){

				packageStorables.addAll(this.getAllStorablesFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				packageStorables.addAll(this.getAllStorablesFrom((Package)abstractCodeElement));

			}

		}

		return packageStorables;
	}

	private List<StorableUnit> getAllStorablesFrom(ClassUnit classToSearch) {
		List<StorableUnit> classStorables = new ArrayList<StorableUnit>();

		for (CodeItem codeItem : classToSearch.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				if(validateFilter((StorableUnit)codeItem)){
					classStorables.add((StorableUnit)codeItem);
				}
				
			}else if (codeItem instanceof ClassUnit){
				
				classStorables.addAll(this.getAllStorablesFrom((ClassUnit)codeItem));
				
			}else if (codeItem instanceof InterfaceUnit){
				
				classStorables.addAll(this.getAllStorablesFrom((InterfaceUnit)codeItem));
				
			}else if (codeItem instanceof EnumeratedType){
				
				classStorables.addAll(this.getAllStorablesFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){

				classStorables.addAll(this.getAllStorablesFrom((MethodUnit)codeItem));

			}

		}

		return classStorables;
	}

	private List<StorableUnit> getAllStorablesFrom(InterfaceUnit interfaceToSearch) {
		List<StorableUnit> interfaceStorables = new ArrayList<StorableUnit>();

		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				if(validateFilter((StorableUnit)codeItem)){
					interfaceStorables.add((StorableUnit)codeItem);
				}

			}else if (codeItem instanceof ClassUnit){
				
				interfaceStorables.addAll(this.getAllStorablesFrom((ClassUnit)codeItem));
				
			}else if (codeItem instanceof InterfaceUnit){
				
				interfaceStorables.addAll(this.getAllStorablesFrom((InterfaceUnit)codeItem));
				
			}else if (codeItem instanceof EnumeratedType){
				
				interfaceStorables.addAll(this.getAllStorablesFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){

				interfaceStorables.addAll(this.getAllStorablesFrom((MethodUnit)codeItem));

			}

		}

		return interfaceStorables;
	}

	private List<StorableUnit> getAllStorablesFrom(EnumeratedType enumeratedTypeToSearch) {
		List<StorableUnit> enumeratedTypeStorables = new ArrayList<StorableUnit>();

		for (CodeItem codeItem : enumeratedTypeToSearch.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				if(validateFilter((StorableUnit)codeItem)){
					enumeratedTypeStorables.add((StorableUnit)codeItem);
				}

			}else if (codeItem instanceof ClassUnit){
				
				enumeratedTypeStorables.addAll(this.getAllStorablesFrom((ClassUnit)codeItem));
				
			}else if (codeItem instanceof InterfaceUnit){
				
				enumeratedTypeStorables.addAll(this.getAllStorablesFrom((InterfaceUnit)codeItem));
				
			}else if (codeItem instanceof EnumeratedType){
				
				enumeratedTypeStorables.addAll(this.getAllStorablesFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){

				enumeratedTypeStorables.addAll(this.getAllStorablesFrom((MethodUnit)codeItem));

			}

		}

		return enumeratedTypeStorables;
	}

	private List<StorableUnit> getAllStorablesFrom(MethodUnit methodToSearch) {
		List<StorableUnit> methodStorables = new ArrayList<StorableUnit>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof BlockUnit){

				methodStorables.addAll(getAllStorablesFrom((BlockUnit) abstractCodeElement));

			}
		}

		return methodStorables;
	}

	private List<StorableUnit> getAllStorablesFrom(BlockUnit blockToSearch) {

		List<StorableUnit> blockStorables = new ArrayList<StorableUnit>();

		for (AbstractCodeElement abstractCodeElement : blockToSearch.getCodeElement()) {

			if (abstractCodeElement instanceof StorableUnit) {

				if(validateFilter((StorableUnit)abstractCodeElement)){
					blockStorables.add((StorableUnit)abstractCodeElement);
				}

			}else if(abstractCodeElement instanceof ActionElement ){

				blockStorables.addAll(getAllStorablesFrom((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				blockStorables.addAll(getAllStorablesFrom((BlockUnit)abstractCodeElement));

			}

		}

		return blockStorables;
	}

	private List<StorableUnit> getAllStorablesFrom(ActionElement actionToSearch) {
		List<StorableUnit> actionStorables = new ArrayList<StorableUnit>();

		for (AbstractCodeElement abstractCodeElement : actionToSearch.getCodeElement()) {

			if (abstractCodeElement instanceof StorableUnit) {

				if(validateFilter((StorableUnit)abstractCodeElement)){
					actionStorables.add((StorableUnit)abstractCodeElement);
				}

			}else if(abstractCodeElement instanceof ActionElement ){

				actionStorables.addAll(getAllStorablesFrom((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				actionStorables.addAll(getAllStorablesFrom((BlockUnit)abstractCodeElement));

			}

		}

		return actionStorables;
	}


}
