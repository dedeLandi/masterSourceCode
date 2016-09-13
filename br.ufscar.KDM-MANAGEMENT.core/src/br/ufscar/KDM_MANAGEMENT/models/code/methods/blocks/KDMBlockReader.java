package br.ufscar.KDM_MANAGEMENT.models.code.methods.blocks;

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
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;

public class KDMBlockReader {
	private Segment segmentMain = null;
	private CodeModel modelMain = null;
	private Package packageMain = null;
	private ClassUnit classMain = null;
	private BlockUnit blockMain = null;
	private MethodUnit methodMain = null;
	
	
	public KDMBlockReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMBlockReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMBlockReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}
	public KDMBlockReader(ClassUnit kdmClassUnit) {
		this.classMain = kdmClassUnit;
	}
	public KDMBlockReader(BlockUnit kdmBlockUnit) {
		this.blockMain = kdmBlockUnit;
	}
	public KDMBlockReader(MethodUnit kdmMethodUnit) {
		this.methodMain = kdmMethodUnit;
	}

	public Map<String, List<BlockUnit>> getAllBlocks() {
		if(this.segmentMain != null){
			return getAllBlocksSegment();
		}else if(this.modelMain != null){
			return getAllBlocksModel();
		}else if(this.packageMain != null){
			return getAllBlocksPackage();
		}else if(this.classMain != null){
			return getAllBlocksClassUnit();
		}else if(this.methodMain != null){
			return getAllBlocksMethodUnit();
		}else if(this.blockMain != null){
			return getAllBlocksBlockUnit();
		}else{
			return null;
		}

	}
	
	//criar os maps
	private Map<String, List<BlockUnit>> getAllBlocksSegment() {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();
		
		for (KDMModel kdmModel : this.segmentMain.getModel()) {
			
			if (kdmModel instanceof CodeModel) {
				
				allBlocksUnit.put(kdmModel.getName(), this.getAllBlocksModel((CodeModel) kdmModel));
				
			}
			
		}
		
		return allBlocksUnit;
	}
	
	private Map<String, List<BlockUnit>> getAllBlocksModel() {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();
		
		allBlocksUnit.put(this.modelMain.getName(), this.getAllBlocksModel(this.modelMain));
		
		return allBlocksUnit;
	}
	
	private Map<String, List<BlockUnit>> getAllBlocksPackage() {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();
		
		allBlocksUnit.put(this.packageMain.getName(), this.getAllBlocksPackage(this.packageMain));
		
		return allBlocksUnit;
	}
	
	private Map<String, List<BlockUnit>> getAllBlocksClassUnit() {
		Map<String, List<BlockUnit>> allBlocksUnit = new HashMap<String, List<BlockUnit>>();
		
		allBlocksUnit.put(this.classMain.getName(), this.getAllBlocksClassUnit(this.classMain));
		
		return allBlocksUnit;
	}
	
	private Map<String, List<BlockUnit>> getAllBlocksMethodUnit() {
		Map<String, List<BlockUnit>> allBlocksUnitFromMethodUnit = new HashMap<String, List<BlockUnit>>();
		
		allBlocksUnitFromMethodUnit.put(this.methodMain.getName(), this.getAllBlocksMethodUnit(this.methodMain));
		
		return allBlocksUnitFromMethodUnit;
	}

	private Map<String, List<BlockUnit>> getAllBlocksBlockUnit() {

		Map<String, List<BlockUnit>> allBlocksUnitFromBlockUnit = new HashMap<String, List<BlockUnit>>();
		
		allBlocksUnitFromBlockUnit.put("BlockUnit", this.getAllBlocksBlockUnit(this.blockMain));
		
		return allBlocksUnitFromBlockUnit;
	}
	
	//fazer as pesquisas	
	private List<BlockUnit> getAllBlocksModel(CodeModel codeModelToSearch) {
		List<BlockUnit> modelBlocks = new ArrayList<BlockUnit>();
		
		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				modelBlocks.addAll(this.getAllBlocksClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				modelBlocks.addAll(this.getAllBlocksPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return modelBlocks;
	}
	
	private List<BlockUnit> getAllBlocksPackage(Package packageToSearch) {
		List<BlockUnit> packageBlocks = new ArrayList<BlockUnit>();
		
		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				packageBlocks.addAll(this.getAllBlocksClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				packageBlocks.addAll(this.getAllBlocksPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return packageBlocks;
	}
	
	private List<BlockUnit> getAllBlocksClassUnit(ClassUnit classToSearch) {
		List<BlockUnit> classBlocks = new ArrayList<BlockUnit>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof BlockUnit){
			
				classBlocks.add((BlockUnit)codeItem);
			
			}else if (codeItem instanceof MethodUnit){
				
				classBlocks.addAll(this.getAllBlocksMethodUnit((MethodUnit)codeItem));
			
			}
			
		}
		
		return classBlocks;
	}
	
	private List<BlockUnit> getAllBlocksMethodUnit(MethodUnit methodToSearch) {
		List<BlockUnit> methodBlocks = new ArrayList<BlockUnit>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof BlockUnit){
				
				methodBlocks.add((BlockUnit) abstractCodeElement);
				methodBlocks.addAll(getAllBlocksBlockUnit((BlockUnit) abstractCodeElement));
				 
			}
		}
		
		return methodBlocks;
	}
	
	private List<BlockUnit> getAllBlocksBlockUnit(BlockUnit blockToSearch) {
		
		List<BlockUnit> blockBlocks = new ArrayList<BlockUnit>();
		
		for (AbstractCodeElement abstractCodeElement : blockToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ActionElement ){
				
				blockBlocks.addAll(searchBlockUnitsInActionElemet((ActionElement)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof BlockUnit ){
				
				blockBlocks.add((BlockUnit) abstractCodeElement);
				blockBlocks.addAll(getAllBlocksBlockUnit((BlockUnit)abstractCodeElement));
				
			}
					
		}
		
		return blockBlocks;
	}
	
	//metodos auxiliares
	private List<BlockUnit> searchBlockUnitsInActionElemet(ActionElement actionToSearch) {
		List<BlockUnit> actionBlocks = new ArrayList<BlockUnit>();
		
		for (AbstractCodeElement abstractCodeElement : actionToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ActionElement ){
				
				actionBlocks.addAll(searchBlockUnitsInActionElemet((ActionElement)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof BlockUnit ){
				
				actionBlocks.add((BlockUnit) abstractCodeElement);
				actionBlocks.addAll(getAllBlocksBlockUnit((BlockUnit)abstractCodeElement));
				
			}
					
		}
		
		return actionBlocks;
	}
}
