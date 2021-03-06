package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.actionElements;

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

import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.enums.KDMActionElementsType;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.interfaces.KDMCodeGenericReader;
import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.factory.KDMModelReaderFactory;

public class KDMActionElementReaderImpl implements KDMCodeGenericReader<ActionElement>{

	private boolean hasNoFilter = false;
	private boolean hasFilterType = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	private KDMActionElementsType filterActionElementType = null;

	public KDMActionElementReaderImpl() {
		super();
	}

	public KDMActionElementReaderImpl(KDMActionElementsType actionElementType) {
		this.hasNoFilter = false;
		this.hasFilterType  = true;
		this.hasFilterName = false;
		this.filterName = "";
		this.filterActionElementType = actionElementType;
	}
	public KDMActionElementReaderImpl(String actionElementName) {
		this.hasNoFilter = false;
		this.hasFilterType  = false;
		this.hasFilterName  = true;
		this.filterName = actionElementName;
		this.filterActionElementType = null;
	}

	@Override
	public Map<String, List<ActionElement>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				if (kdmModel instanceof CodeModel) {

					allActionElementsUnit.put(kdmModel.getName(), this.getAllActionElementsFrom((CodeModel) kdmModel));

				}
			}
		}

		return allActionElementsUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromModel(CodeModel codeModelToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(codeModelToSearch.getName(), this.getAllActionElementsFrom(codeModelToSearch));

		return allActionElementsUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromPackage(Package packageToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(packageToSearch.getName(), this.getAllActionElementsFrom(packageToSearch));

		return allActionElementsUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(classUnitToSearch.getName(), this.getAllActionElementsFrom(classUnitToSearch));

		return allActionElementsUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(interfaceUnitToSearch.getName(), this.getAllActionElementsFrom(interfaceUnitToSearch));

		return allActionElementsUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(enumeratedTypeToSearch.getName(), this.getAllActionElementsFrom(enumeratedTypeToSearch));

		return allActionElementsUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnitFromMethodUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnitFromMethodUnit.put(methodUnitToSearch.getName(), this.getAllActionElementsFrom(methodUnitToSearch));

		return allActionElementsUnitFromMethodUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnitFromBlockUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnitFromBlockUnit.put("BlockUnit: " + blockUnitToSearch.getName(), this.getAllActionElementsFrom(blockUnitToSearch));

		return allActionElementsUnitFromBlockUnit;
	}
	
	@Override
	public Map<String, List<ActionElement>> getAllFromActionElement(ActionElement actionElementToSearch) {
		Map<String, List<ActionElement>> allActionElementsUnitFromBlockUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnitFromBlockUnit.put("ActionElement: " + actionElementToSearch.getName(), this.getAllActionElementsFrom(actionElementToSearch));

		return allActionElementsUnitFromBlockUnit;
	}
	
	@Override
	@Deprecated
	public Map<String, List<ActionElement>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}
	
	@Override
	@Deprecated
	public Map<String, List<ActionElement>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}
	
	@Override
	@Deprecated
	public Map<String, List<ActionElement>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	private List<ActionElement> getAllActionElementsFrom(CodeModel codeModelToSearch) {
		List<ActionElement> modelActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){
				
				modelActionElements.addAll(this.getAllActionElementsFrom((ClassUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				modelActionElements.addAll(this.getAllActionElementsFrom((InterfaceUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof EnumeratedType){

				modelActionElements.addAll(this.getAllActionElementsFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				modelActionElements.addAll(this.getAllActionElementsFrom((Package)abstractCodeElement));

			}

		}

		return modelActionElements;
	}

	private List<ActionElement> getAllActionElementsFrom(Package packageToSearch) {
		List<ActionElement> packageActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				packageActionElements.addAll(this.getAllActionElementsFrom((ClassUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				packageActionElements.addAll(this.getAllActionElementsFrom((InterfaceUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof EnumeratedType){

				packageActionElements.addAll(this.getAllActionElementsFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				packageActionElements.addAll(this.getAllActionElementsFrom((Package)abstractCodeElement));

			}

		}

		return packageActionElements;
	}
	
	private List<ActionElement> getAllActionElementsFrom(ClassUnit classToSearch) {
		List<ActionElement> classActionElements = new ArrayList<ActionElement>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof ActionElement){
				
				if(validateFilter((ActionElement)codeItem)){
					classActionElements.add((ActionElement)codeItem);
				}
				
			}else if(codeItem instanceof ClassUnit){
				
				classActionElements.addAll(this.getAllActionElementsFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				classActionElements.addAll(this.getAllActionElementsFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				classActionElements.addAll(this.getAllActionElementsFrom((EnumeratedType)codeItem)); 
				
			}else if (codeItem instanceof MethodUnit){
				
				classActionElements.addAll(this.getAllActionElementsFrom((MethodUnit)codeItem));
				
			}
			
		}
		
		return classActionElements;
	}
	
	private List<ActionElement> getAllActionElementsFrom(InterfaceUnit interfaceToSearch) {
		List<ActionElement> interfaceActionElements = new ArrayList<ActionElement>();
		
		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {
			
			if(codeItem instanceof ActionElement){
				
				if(validateFilter((ActionElement)codeItem)){
					interfaceActionElements.add((ActionElement)codeItem);
				}
				
			}else if(codeItem instanceof ClassUnit){
				
				interfaceActionElements.addAll(this.getAllActionElementsFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				interfaceActionElements.addAll(this.getAllActionElementsFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				interfaceActionElements.addAll(this.getAllActionElementsFrom((EnumeratedType)codeItem)); 
				
			}else if (codeItem instanceof MethodUnit){
				
				interfaceActionElements.addAll(this.getAllActionElementsFrom((MethodUnit)codeItem));
				
			}
			
		}
		
		return interfaceActionElements;
	}

	private List<ActionElement> getAllActionElementsFrom(EnumeratedType enumeratedTypeToSearch) {
		List<ActionElement> enumeratedTypeActionElements = new ArrayList<ActionElement>();

		for (CodeItem codeItem : enumeratedTypeToSearch.getCodeElement()) {

			if(codeItem instanceof ActionElement){

				if(validateFilter((ActionElement)codeItem)){
					enumeratedTypeActionElements.add((ActionElement)codeItem);
				}

			}else if(codeItem instanceof ClassUnit){

				enumeratedTypeActionElements.addAll(this.getAllActionElementsFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				enumeratedTypeActionElements.addAll(this.getAllActionElementsFrom((InterfaceUnit)codeItem));

			}else if(codeItem instanceof EnumeratedType){

				enumeratedTypeActionElements.addAll(this.getAllActionElementsFrom((EnumeratedType)codeItem)); 
				
			}else if (codeItem instanceof MethodUnit){

				enumeratedTypeActionElements.addAll(this.getAllActionElementsFrom((MethodUnit)codeItem));

			}

		}

		return enumeratedTypeActionElements;
	}

	private List<ActionElement> getAllActionElementsFrom(MethodUnit methodToSearch) {
		List<ActionElement> methodActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof BlockUnit){

				methodActionElements.addAll(this.getAllActionElementsFrom((BlockUnit) abstractCodeElement));

			}
		}

		return methodActionElements;
	}

	private List<ActionElement> getAllActionElementsFrom(BlockUnit blockToSearch) {

		List<ActionElement> blockActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : blockToSearch.getCodeElement()) {

			if (abstractCodeElement instanceof ActionElement) {

				if(validateFilter((ActionElement) abstractCodeElement)){
					blockActionElements.add((ActionElement) abstractCodeElement);
				}
				blockActionElements.addAll(this.getAllActionElementsFrom((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				blockActionElements.addAll(this.getAllActionElementsFrom((BlockUnit)abstractCodeElement));

			}

		}

		return blockActionElements;
	}

	private List<ActionElement> getAllActionElementsFrom(ActionElement actionToSearch) {
		List<ActionElement> actionActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : actionToSearch.getCodeElement()) {

			if (abstractCodeElement instanceof ActionElement) {

				if(validateFilter((ActionElement) abstractCodeElement)){
					actionActionElements.add((ActionElement) abstractCodeElement);
				}
				actionActionElements.addAll(this.getAllActionElementsFrom((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				actionActionElements.addAll(this.getAllActionElementsFrom((BlockUnit)abstractCodeElement));

			}

		}

		return actionActionElements;
	}

	private boolean validateFilter(ActionElement actionElementToValidate) {
		if(this.hasNoFilter){
			return true;
		}else if(this.hasFilterName){
			if(actionElementToValidate.getName().equalsIgnoreCase(this.filterName)){
				return true;
			}
		}else if(this.hasFilterType){
			if(actionElementToValidate.getName().toString().equalsIgnoreCase(this.filterActionElementType.getName().toString())
					|| 
					actionElementToValidate.getKind().equalsIgnoreCase(this.filterActionElementType.getKind())){
				return true;
			}
		}
		return false;
	}
}
