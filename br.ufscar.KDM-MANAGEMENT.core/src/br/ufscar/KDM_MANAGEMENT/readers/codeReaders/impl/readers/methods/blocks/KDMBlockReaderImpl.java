package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.methods.blocks;

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

public class KDMBlockReaderImpl implements KDMCodeGenericReader<BlockUnit>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMBlockReaderImpl() {
		super();
	}

	public KDMBlockReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(BlockUnit elementToValidate) {
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
	public Map<String, List<BlockUnit>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				if (kdmModel instanceof CodeModel) {

					allBlocksUnit.put(kdmModel.getName(), this.getAllBlocksFrom((CodeModel) kdmModel));

				}
			}
		}

		return allBlocksUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromModel(CodeModel codeModelToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnit.put(codeModelToSearch.getName(), this.getAllBlocksFrom(codeModelToSearch));

		return allBlocksUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromPackage(Package packageToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnit.put(packageToSearch.getName(), this.getAllBlocksFrom(packageToSearch));

		return allBlocksUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnit.put(classUnitToSearch.getName(), this.getAllBlocksFrom(classUnitToSearch));

		return allBlocksUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnit.put(interfaceUnitToSearch.getName(), this.getAllBlocksFrom(interfaceUnitToSearch));

		return allBlocksUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnit.put(enumeratedTypeToSearch.getName(), this.getAllBlocksFrom(enumeratedTypeToSearch));

		return allBlocksUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnitFromMethodUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnitFromMethodUnit.put(methodUnitToSearch.getName(), this.getAllBlocksFrom(methodUnitToSearch));

		return allBlocksUnitFromMethodUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnitFromBlockUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnitFromBlockUnit.put("BlockUnit: " + blockUnitToSearch.getName(), this.getAllBlocksFrom(blockUnitToSearch));

		return allBlocksUnitFromBlockUnit;
	}

	@Override
	public Map<String, List<BlockUnit>> getAllFromActionElement(ActionElement actionElementToSearch) {
		Map<String, List<BlockUnit>> allBlocksUnitFromBlockUnit = new HashMap<String, List<BlockUnit>>();

		allBlocksUnitFromBlockUnit.put("ActionElement: " + actionElementToSearch.getName(), this.getAllBlocksFrom(actionElementToSearch));

		return allBlocksUnitFromBlockUnit;
	}
	
	@Override
	@Deprecated
	public Map<String, List<BlockUnit>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}
	
	@Override
	@Deprecated
	public Map<String, List<BlockUnit>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<BlockUnit>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	private List<BlockUnit> getAllBlocksFrom(CodeModel codeModelToSearch) {
		List<BlockUnit> modelBlocks = new ArrayList<BlockUnit>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				modelBlocks.addAll(this.getAllBlocksFrom((ClassUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				modelBlocks.addAll(this.getAllBlocksFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				modelBlocks.addAll(this.getAllBlocksFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				modelBlocks.addAll(this.getAllBlocksFrom((Package)abstractCodeElement));

			}

		}

		return modelBlocks;
	}

	private List<BlockUnit> getAllBlocksFrom(Package packageToSearch) {
		List<BlockUnit> packageBlocks = new ArrayList<BlockUnit>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				packageBlocks.addAll(this.getAllBlocksFrom((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				packageBlocks.addAll(this.getAllBlocksFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				packageBlocks.addAll(this.getAllBlocksFrom((EnumeratedType)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				packageBlocks.addAll(this.getAllBlocksFrom((Package)abstractCodeElement));

			}

		}

		return packageBlocks;
	}

	private List<BlockUnit> getAllBlocksFrom(ClassUnit classToSearch) {
		List<BlockUnit> classBlocks = new ArrayList<BlockUnit>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				classBlocks.addAll(this.getAllBlocksFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				classBlocks.addAll(this.getAllBlocksFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				classBlocks.addAll(this.getAllBlocksFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){
				
				classBlocks.addAll(this.getAllBlocksFrom((MethodUnit)codeItem));
				
			}
			
		}
		
		return classBlocks;
	}
	
	private List<BlockUnit> getAllBlocksFrom(InterfaceUnit interfaceToSearch) {
		List<BlockUnit> interfaceBlocks = new ArrayList<BlockUnit>();
		
		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				interfaceBlocks.addAll(this.getAllBlocksFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				interfaceBlocks.addAll(this.getAllBlocksFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				interfaceBlocks.addAll(this.getAllBlocksFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){
				
				interfaceBlocks.addAll(this.getAllBlocksFrom((MethodUnit)codeItem));
				
			}
			
		}
		
		return interfaceBlocks;
	}
	
	private List<BlockUnit> getAllBlocksFrom(EnumeratedType enumeratedTypeToSearch) {
		List<BlockUnit> enumeratedTypeBlocks = new ArrayList<BlockUnit>();

		for (CodeItem codeItem : enumeratedTypeToSearch.getCodeElement()) {

			if(codeItem instanceof ClassUnit){

				enumeratedTypeBlocks.addAll(this.getAllBlocksFrom((ClassUnit)codeItem));

			}else if(codeItem instanceof InterfaceUnit){
				
				enumeratedTypeBlocks.addAll(this.getAllBlocksFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				enumeratedTypeBlocks.addAll(this.getAllBlocksFrom((EnumeratedType)codeItem));

			}else if (codeItem instanceof MethodUnit){

				enumeratedTypeBlocks.addAll(this.getAllBlocksFrom((MethodUnit)codeItem));

			}

		}

		return enumeratedTypeBlocks;
	}

	private List<BlockUnit> getAllBlocksFrom(MethodUnit methodToSearch) {
		List<BlockUnit> methodBlocks = new ArrayList<BlockUnit>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof BlockUnit){

				if(validateFilter((BlockUnit) abstractCodeElement)){
					methodBlocks.add((BlockUnit) abstractCodeElement);
				}
				methodBlocks.addAll(this.getAllBlocksFrom((BlockUnit) abstractCodeElement));

			}
		}

		return methodBlocks;
	}

	private List<BlockUnit> getAllBlocksFrom(BlockUnit blockToSearch) {

		List<BlockUnit> blockBlocks = new ArrayList<BlockUnit>();

		for (AbstractCodeElement abstractCodeElement : blockToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ActionElement ){

				blockBlocks.addAll(this.getAllBlocksFrom((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				if(validateFilter((BlockUnit) abstractCodeElement)){
					blockBlocks.add((BlockUnit) abstractCodeElement);
				}
				blockBlocks.addAll(this.getAllBlocksFrom((BlockUnit)abstractCodeElement));

			}

		}

		return blockBlocks;
	}

	private List<BlockUnit> getAllBlocksFrom(ActionElement actionToSearch) {
		List<BlockUnit> actionBlocks = new ArrayList<BlockUnit>();

		for (AbstractCodeElement abstractCodeElement : actionToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ActionElement ){

				actionBlocks.addAll(this.getAllBlocksFrom((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				if(validateFilter((BlockUnit) abstractCodeElement)){
					actionBlocks.add((BlockUnit) abstractCodeElement);
				}
				actionBlocks.addAll(this.getAllBlocksFrom((BlockUnit)abstractCodeElement));

			}

		}

		return actionBlocks;
	}
}
