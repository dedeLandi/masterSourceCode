package br.ufscar.KDM_MANAGEMENT.models.code.actionElements;

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

public class KDMActionElementReader {

	private Segment segmentMain = null;
	private CodeModel modelMain = null;
	private Package packageMain = null;
	private ClassUnit classMain = null;
	private MethodUnit methodMain = null;
	private BlockUnit blockMain = null;
	private ActionElement actionMain = null;
	
	private boolean hasNoFilter = false;
	private boolean hasFilterType = false;
	private boolean hasFilterName = false;
	private String filterName = "";
	private KDMActionElementsType filterActionElementType = null;

	public KDMActionElementReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMActionElementReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMActionElementReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}
	public KDMActionElementReader(ClassUnit kdmClassUnit) {
		this.classMain = kdmClassUnit;
	}
	public KDMActionElementReader(MethodUnit kdmMethodUnit) {
		this.methodMain = kdmMethodUnit;
	}
	public KDMActionElementReader(BlockUnit kdmBlockUnit) {
		this.blockMain = kdmBlockUnit;
	}
	public KDMActionElementReader(ActionElement kdmActionElement) {
		this.actionMain = kdmActionElement;
	}

	public Map<String, List<ActionElement>> getAllActionElements() {
		this.hasNoFilter = true;
		this.hasFilterType = false;
		this.hasFilterName = false;
		this.filterName = "";
		this.filterActionElementType = null;

		if(this.segmentMain != null){
			return getAllActionElementsSegment();
		}else if(this.modelMain != null){
			return getAllActionElementsModel();
		}else if(this.packageMain != null){
			return getAllActionElementsPackage();
		}else if(this.classMain != null){
			return getAllActionElementsClassUnit();
		}else if(this.methodMain != null){
			return getAllActionElementsMethodUnit();
		}else if(this.blockMain != null){
			return getAllActionElementsBlockUnit();
		}else if(this.actionMain != null){
			return getAllActionElementsActionElement();
		}else{
			return null;
		}

	}

	public Map<String, List<ActionElement>> getAllActionElements(KDMActionElementsType actionElementType) {
		this.hasNoFilter = false;
		this.hasFilterType  = true;
		this.hasFilterName = false;
		this.filterName = "";
		this.filterActionElementType = actionElementType;

		if(this.segmentMain != null){
			return getAllActionElementsSegment();
		}else if(this.modelMain != null){
			return getAllActionElementsModel();
		}else if(this.packageMain != null){
			return getAllActionElementsPackage();
		}else if(this.classMain != null){
			return getAllActionElementsClassUnit();
		}else if(this.methodMain != null){
			return getAllActionElementsMethodUnit();
		}else if(this.blockMain != null){
			return getAllActionElementsBlockUnit();
		}else if(this.actionMain != null){
			return getAllActionElementsActionElement();
		}else{
			return null;
		}
	}

	public Map<String, List<ActionElement>> getAllActionElementsByName(String actionElementName) {
		this.hasNoFilter = false;
		this.hasFilterType  = false;
		this.hasFilterName  = true;
		this.filterName = actionElementName;
		this.filterActionElementType = null;

		if(this.segmentMain != null){
			return getAllActionElementsSegment();
		}else if(this.modelMain != null){
			return getAllActionElementsModel();
		}else if(this.packageMain != null){
			return getAllActionElementsPackage();
		}else if(this.classMain != null){
			return getAllActionElementsClassUnit();
		}else if(this.methodMain != null){
			return getAllActionElementsMethodUnit();
		}else if(this.blockMain != null){
			return getAllActionElementsBlockUnit();
		}else if(this.actionMain != null){
			return getAllActionElementsActionElement();
		}else{
			return null;
		}
	}

	//criar os maps
	private Map<String, List<ActionElement>> getAllActionElementsSegment() {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		for (KDMModel kdmModel : this.segmentMain.getModel()) {

			if (kdmModel instanceof CodeModel) {

				allActionElementsUnit.put(kdmModel.getName(), this.getAllActionElementsModel((CodeModel) kdmModel));

			}

		}

		return allActionElementsUnit;
	}

	private Map<String, List<ActionElement>> getAllActionElementsModel() {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(this.modelMain.getName(), this.getAllActionElementsModel(this.modelMain));

		return allActionElementsUnit;
	}

	private Map<String, List<ActionElement>> getAllActionElementsPackage() {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(this.packageMain.getName(), this.getAllActionElementsPackage(this.packageMain));

		return allActionElementsUnit;
	}

	private Map<String, List<ActionElement>> getAllActionElementsClassUnit() {
		Map<String, List<ActionElement>> allActionElementsUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnit.put(this.classMain.getName(), this.getAllActionElementsClassUnit(this.classMain));

		return allActionElementsUnit;
	}

	private Map<String, List<ActionElement>> getAllActionElementsMethodUnit() {
		Map<String, List<ActionElement>> allActionElementsUnitFromMethodUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnitFromMethodUnit.put(this.methodMain.getName(), this.getAllActionElementsMethodUnit(this.methodMain));

		return allActionElementsUnitFromMethodUnit;
	}

	private Map<String, List<ActionElement>> getAllActionElementsBlockUnit() {

		Map<String, List<ActionElement>> allActionElementsUnitFromBlockUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnitFromBlockUnit.put("BlockUnit", this.getAllActionElementsBlockUnit(this.blockMain));

		return allActionElementsUnitFromBlockUnit;
	}

	private Map<String, List<ActionElement>> getAllActionElementsActionElement() {

		Map<String, List<ActionElement>> allActionElementsUnitFromBlockUnit = new HashMap<String, List<ActionElement>>();

		allActionElementsUnitFromBlockUnit.put(this.actionMain.getName(), this.searchActionElementsInActionElement(this.actionMain));

		return allActionElementsUnitFromBlockUnit;
	}

	//fazer as pesquisas
	private List<ActionElement> getAllActionElementsModel(CodeModel codeModelToSearch) {
		List<ActionElement> modelActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				modelActionElements.addAll(this.getAllActionElementsClassUnit((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				modelActionElements.addAll(this.getAllActionElementsPackage((Package)abstractCodeElement));

			}

		}

		return modelActionElements;
	}

	private List<ActionElement> getAllActionElementsPackage(Package packageToSearch) {
		List<ActionElement> packageActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				packageActionElements.addAll(this.getAllActionElementsClassUnit((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof Package){

				packageActionElements.addAll(this.getAllActionElementsPackage((Package)abstractCodeElement));

			}

		}

		return packageActionElements;
	}

	private List<ActionElement> getAllActionElementsClassUnit(ClassUnit classToSearch) {
		List<ActionElement> classActionElements = new ArrayList<ActionElement>();

		for (CodeItem codeItem : classToSearch.getCodeElement()) {

			if(codeItem instanceof ActionElement){

				if(validateFilter((ActionElement)codeItem)){
					classActionElements.add((ActionElement)codeItem);
				}

			}else if (codeItem instanceof MethodUnit){

				classActionElements.addAll(this.getAllActionElementsMethodUnit((MethodUnit)codeItem));

			}

		}

		return classActionElements;
	}

	private List<ActionElement> getAllActionElementsMethodUnit(MethodUnit methodToSearch) {
		List<ActionElement> methodActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof BlockUnit){

				methodActionElements.addAll(getAllActionElementsBlockUnit((BlockUnit) abstractCodeElement));

			}
		}

		return methodActionElements;
	}

	private List<ActionElement> getAllActionElementsBlockUnit(BlockUnit blockToSearch) {

		List<ActionElement> blockActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : blockToSearch.getCodeElement()) {

			if (abstractCodeElement instanceof ActionElement) {

				if(validateFilter((ActionElement) abstractCodeElement)){
					blockActionElements.add((ActionElement) abstractCodeElement);
				}
				blockActionElements.addAll(searchActionElementsInActionElement((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				blockActionElements.addAll(getAllActionElementsBlockUnit((BlockUnit)abstractCodeElement));

			}

		}

		return blockActionElements;
	}

	//metodos auxiliares
	private List<ActionElement> searchActionElementsInActionElement(ActionElement actionToSearch) {
		List<ActionElement> actionActionElements = new ArrayList<ActionElement>();

		for (AbstractCodeElement abstractCodeElement : actionToSearch.getCodeElement()) {

			if (abstractCodeElement instanceof ActionElement) {

				if(validateFilter((ActionElement) abstractCodeElement)){
					actionActionElements.add((ActionElement) abstractCodeElement);
				}
				actionActionElements.addAll(searchActionElementsInActionElement((ActionElement)abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit ){

				actionActionElements.addAll(getAllActionElementsBlockUnit((BlockUnit)abstractCodeElement));

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
