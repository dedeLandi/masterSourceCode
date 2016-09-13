package br.ufscar.KDM_MANAGEMENT.models.code.storables;

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
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;

public class KDMStorableReader {

	private Segment segmentMain = null;
	private CodeModel modelMain = null;
	private Package packageMain = null;
	private ClassUnit classMain = null;
	private BlockUnit blockMain = null;
	private MethodUnit methodMain = null;
	
	
	public KDMStorableReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMStorableReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMStorableReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}
	public KDMStorableReader(ClassUnit kdmClassUnit) {
		this.classMain = kdmClassUnit;
	}
	public KDMStorableReader(BlockUnit kdmBlockUnit) {
		this.blockMain = kdmBlockUnit;
	}
	public KDMStorableReader(MethodUnit kdmMethodUnit) {
		this.methodMain = kdmMethodUnit;
	}

	public Map<String, List<StorableUnit>> getAllStorables() {
		if(this.segmentMain != null){
			return getAllStorablesSegment();
		}else if(this.modelMain != null){
			return getAllStorablesModel();
		}else if(this.packageMain != null){
			return getAllStorablesPackage();
		}else if(this.classMain != null){
			return getAllStorablesClassUnit();
		}else if(this.methodMain != null){
			return getAllStorablesMethodUnit();
		}else if(this.blockMain != null){
			return getAllStorablesBlockUnit();
		}else{
			return null;
		}

	}
	
	//criar os maps
	private Map<String, List<StorableUnit>> getAllStorablesSegment() {
		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();
		
		for (KDMModel kdmModel : this.segmentMain.getModel()) {
			
			if (kdmModel instanceof CodeModel) {
				
				allStorablesUnit.put(kdmModel.getName(), this.getAllStorablesModel((CodeModel) kdmModel));
				
			}
			
		}
		
		return allStorablesUnit;
	}
	
	private Map<String, List<StorableUnit>> getAllStorablesModel() {
		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();
		
		allStorablesUnit.put(this.modelMain.getName(), this.getAllStorablesModel(this.modelMain));
		
		return allStorablesUnit;
	}
	
	private Map<String, List<StorableUnit>> getAllStorablesPackage() {
		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();
		
		allStorablesUnit.put(this.packageMain.getName(), this.getAllStorablesPackage(this.packageMain));
		
		return allStorablesUnit;
	}
	
	private Map<String, List<StorableUnit>> getAllStorablesClassUnit() {
		Map<String, List<StorableUnit>> allStorablesUnit = new HashMap<String, List<StorableUnit>>();
		
		allStorablesUnit.put(this.classMain.getName(), this.getAllStorablesClassUnit(this.classMain));
		
		return allStorablesUnit;
	}
	
	private Map<String, List<StorableUnit>> getAllStorablesMethodUnit() {
		Map<String, List<StorableUnit>> allStorablesUnitFromMethodUnit = new HashMap<String, List<StorableUnit>>();
		
		allStorablesUnitFromMethodUnit.put(this.methodMain.getName(), this.getAllStorablesMethodUnit(this.methodMain));
		
		return allStorablesUnitFromMethodUnit;
	}

	private Map<String, List<StorableUnit>> getAllStorablesBlockUnit() {

		Map<String, List<StorableUnit>> allStorablesUnitFromBlockUnit = new HashMap<String, List<StorableUnit>>();
		
		allStorablesUnitFromBlockUnit.put("BlockUnit", this.getAllStorablesBlockUnit(this.blockMain));
		
		return allStorablesUnitFromBlockUnit;
	}
	
	//fazer as pesquisas	
	private List<StorableUnit> getAllStorablesModel(CodeModel codeModelToSearch) {
		List<StorableUnit> modelStorables = new ArrayList<StorableUnit>();
		
		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				modelStorables.addAll(this.getAllStorablesClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				modelStorables.addAll(this.getAllStorablesPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return modelStorables;
	}
	
	private List<StorableUnit> getAllStorablesPackage(Package packageToSearch) {
		List<StorableUnit> packageStorables = new ArrayList<StorableUnit>();
		
		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
			
				packageStorables.addAll(this.getAllStorablesClassUnit((ClassUnit)abstractCodeElement));
			
			}else if(abstractCodeElement instanceof Package){
				
				packageStorables.addAll(this.getAllStorablesPackage((Package)abstractCodeElement));
				
			}
			
		}
		
		return packageStorables;
	}
	
	private List<StorableUnit> getAllStorablesClassUnit(ClassUnit classToSearch) {
		List<StorableUnit> classStorables = new ArrayList<StorableUnit>();
		
		for (CodeItem codeItem : classToSearch.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
			
				classStorables.add((StorableUnit)codeItem);
			
			}else if (codeItem instanceof MethodUnit){
				
				classStorables.addAll(this.getAllStorablesMethodUnit((MethodUnit)codeItem));
			
			}
			
		}
		
		return classStorables;
	}
	
	private List<StorableUnit> getAllStorablesMethodUnit(MethodUnit methodToSearch) {
		List<StorableUnit> methodStorables = new ArrayList<StorableUnit>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof BlockUnit){
				
				methodStorables.addAll(getAllStorablesBlockUnit((BlockUnit) abstractCodeElement));
				 
			}
		}
		
		return methodStorables;
	}
	
	private List<StorableUnit> getAllStorablesBlockUnit(BlockUnit blockToSearch) {
		
		List<StorableUnit> blockStorables = new ArrayList<StorableUnit>();
		
		for (AbstractCodeElement abstractCodeElement : blockToSearch.getCodeElement()) {
			
			if (abstractCodeElement instanceof StorableUnit) {
			
				blockStorables.add((StorableUnit) abstractCodeElement);
			
			}else if(abstractCodeElement instanceof ActionElement ){
				
				blockStorables.addAll(searchStorableUnitsInActionElemet((ActionElement)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof BlockUnit ){
				
				blockStorables.addAll(getAllStorablesBlockUnit((BlockUnit)abstractCodeElement));
				
			}
					
		}
		
		return blockStorables;
	}
	
	
	
	
	
	
	//metodos auxiliares
	private List<StorableUnit> searchStorableUnitsInActionElemet(ActionElement actionToSearch) {
		List<StorableUnit> actionStorables = new ArrayList<StorableUnit>();
		
		for (AbstractCodeElement abstractCodeElement : actionToSearch.getCodeElement()) {
			
			if (abstractCodeElement instanceof StorableUnit) {
			
				actionStorables.add((StorableUnit) abstractCodeElement);
			
			}else if(abstractCodeElement instanceof ActionElement ){
				
				actionStorables.addAll(searchStorableUnitsInActionElemet((ActionElement)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof BlockUnit ){
				
				actionStorables.addAll(getAllStorablesBlockUnit((BlockUnit)abstractCodeElement));
				
			}
					
		}
		
		return actionStorables;
	}
	
	
}
