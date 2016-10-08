package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.methods.signatures;

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

public class KDMSignatureReaderImpl implements KDMCodeGenericReader<Signature>{
	
	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMSignatureReaderImpl() {
		super();
	}

	public KDMSignatureReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(Signature elementToValidate) {
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
	public Map<String, List<Signature>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				if (kdmModel instanceof CodeModel) {

					allSignaturesUnit.put(kdmModel.getName(), this.getAllSignaturesFrom((CodeModel) kdmModel));

				}

			}
		}

		return allSignaturesUnit;
	}

	@Override
	public Map<String, List<Signature>> getAllFromModel(CodeModel codeModelToSearch) {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();

		allSignaturesUnit.put(codeModelToSearch.getName(), this.getAllSignaturesFrom(codeModelToSearch));

		return allSignaturesUnit;
	}

	@Override
	public Map<String, List<Signature>> getAllFromPackage(Package packageToSearch) {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();

		allSignaturesUnit.put(packageToSearch.getName(), this.getAllSignaturesFrom(packageToSearch));

		return allSignaturesUnit;
	}

	@Override
	public Map<String, List<Signature>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();

		allSignaturesUnit.put(classUnitToSearch.getName(), this.getAllSignaturesFrom(classUnitToSearch));

		return allSignaturesUnit;
	}

	@Override
	public Map<String, List<Signature>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();

		allSignaturesUnit.put(interfaceUnitToSearch.getName(), this.getAllSignaturesFrom(interfaceUnitToSearch));

		return allSignaturesUnit;
	}

	@Override
	public Map<String, List<Signature>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {
		Map<String, List<Signature>> allSignaturesUnit = new HashMap<String, List<Signature>>();

		allSignaturesUnit.put(enumeratedTypeToSearch.getName(), this.getAllSignaturesFrom(enumeratedTypeToSearch));

		return allSignaturesUnit;
	}

	@Override
	public Map<String, List<Signature>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		Map<String, List<Signature>> allSignaturesUnitFromMethodUnit = new HashMap<String, List<Signature>>();

		allSignaturesUnitFromMethodUnit.put(methodUnitToSearch.getName(), this.getAllSignaturesFrom(methodUnitToSearch));

		return allSignaturesUnitFromMethodUnit;
	}
	
	@Override
	public Map<String, List<Signature>> getAllFromSignature(Signature signatureUnitToSearch) {
		Map<String, List<Signature>> allMethodsUnit = new HashMap<String, List<Signature>>();

		List<Signature> methodListed = new ArrayList<Signature>();
		methodListed.add(signatureUnitToSearch);
		allMethodsUnit.put("Signature: " + signatureUnitToSearch.getName(), methodListed );

		return allMethodsUnit;
	}

	@Override
	@Deprecated
	public Map<String, List<Signature>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Signature>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Signature>> getAllFromActionElement(ActionElement actionElementToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Signature>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	private List<Signature> getAllSignaturesFrom(CodeModel codeModelToSearch) {
		List<Signature> modelSignatures = new ArrayList<Signature>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				modelSignatures.addAll(this.getAllSignaturesFrom((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				modelSignatures.addAll(this.getAllSignaturesFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				modelSignatures.addAll(this.getAllSignaturesFrom((EnumeratedType)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof Package){

				modelSignatures.addAll(this.getAllSignaturesFrom((Package)abstractCodeElement));

			}

		}

		return modelSignatures;
	}

	private List<Signature> getAllSignaturesFrom(Package packageToSearch) {
		List<Signature> packageSignatures = new ArrayList<Signature>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				packageSignatures.addAll(this.getAllSignaturesFrom((ClassUnit)abstractCodeElement));

			}else if(abstractCodeElement instanceof InterfaceUnit){
				
				packageSignatures.addAll(this.getAllSignaturesFrom((InterfaceUnit)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof EnumeratedType){
				
				packageSignatures.addAll(this.getAllSignaturesFrom((EnumeratedType)abstractCodeElement));
				
			}else if(abstractCodeElement instanceof Package){

				packageSignatures.addAll(this.getAllSignaturesFrom((Package)abstractCodeElement));

			}

		}

		return packageSignatures;
	}

	private List<Signature> getAllSignaturesFrom(ClassUnit classToSearch) {
		List<Signature> classSignatures = new ArrayList<Signature>();

		for (CodeItem codeItem : classToSearch.getCodeElement()) {

			if(codeItem instanceof ClassUnit){

				classSignatures.addAll(this.getAllSignaturesFrom((ClassUnit)codeItem));

			}else if(codeItem instanceof InterfaceUnit){
				
				classSignatures.addAll(this.getAllSignaturesFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				classSignatures.addAll(this.getAllSignaturesFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){

				classSignatures.addAll(this.getAllSignaturesFrom((MethodUnit)codeItem));

			}

		}

		return classSignatures;
	}

	private List<Signature> getAllSignaturesFrom(InterfaceUnit interfaceToSearch) {
		List<Signature> interfaceSignatures = new ArrayList<Signature>();
		
		for (CodeItem codeItem : interfaceToSearch.getCodeElement()) {
			
			if(codeItem instanceof ClassUnit){
				
				interfaceSignatures.addAll(this.getAllSignaturesFrom((ClassUnit)codeItem));
				
			}else if(codeItem instanceof InterfaceUnit){
				
				interfaceSignatures.addAll(this.getAllSignaturesFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				interfaceSignatures.addAll(this.getAllSignaturesFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){
				
				interfaceSignatures.addAll(this.getAllSignaturesFrom((MethodUnit)codeItem));
				
			}
			
		}
		
		return interfaceSignatures;
	}
	
	private List<Signature> getAllSignaturesFrom(EnumeratedType enumeratedTypeToSearch) {
		List<Signature> enumeratedTypeSignatures = new ArrayList<Signature>();

		for (CodeItem codeItem : enumeratedTypeToSearch.getCodeElement()) {

			if(codeItem instanceof ClassUnit){

				enumeratedTypeSignatures.addAll(this.getAllSignaturesFrom((ClassUnit)codeItem));

			}else if(codeItem instanceof InterfaceUnit){
				
				enumeratedTypeSignatures.addAll(this.getAllSignaturesFrom((InterfaceUnit)codeItem));
				
			}else if(codeItem instanceof EnumeratedType){
				
				enumeratedTypeSignatures.addAll(this.getAllSignaturesFrom((EnumeratedType)codeItem));
				
			}else if (codeItem instanceof MethodUnit){

				enumeratedTypeSignatures.addAll(this.getAllSignaturesFrom((MethodUnit)codeItem));

			}

		}

		return enumeratedTypeSignatures;
	}

	private List<Signature> getAllSignaturesFrom(MethodUnit methodToSearch) {
		List<Signature> methodSignatures = new ArrayList<Signature>();

		for (AbstractCodeElement abstractCodeElement : methodToSearch.getCodeElement()) {

			if(abstractCodeElement instanceof Signature){

				if(validateFilter((Signature)abstractCodeElement)){
					methodSignatures.add(((Signature)abstractCodeElement));
				}

			}
		}

		return methodSignatures;
	}

}
