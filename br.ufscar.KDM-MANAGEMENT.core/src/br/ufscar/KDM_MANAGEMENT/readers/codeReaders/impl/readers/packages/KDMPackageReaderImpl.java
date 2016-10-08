package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.packages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
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


public class KDMPackageReaderImpl implements KDMCodeGenericReader<Package> {

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMPackageReaderImpl() {
		super();
	}

	public KDMPackageReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(Package elementToValidate) {
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
	public Map<String, List<Package>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<Package>> packagesPerModel = new HashMap<String, List<Package>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllCodeModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				packagesPerModel.put(kdmModel.getName(), this.getAllPackagesFrom((CodeModel) kdmModel));
				
			}
		}

		return packagesPerModel;
	}
	
	@Override
	public Map<String, List<Package>> getAllFromModel(CodeModel codeModelToSearch) {

		Map<String, List<Package>> allPackagesFromModel = new HashMap<String, List<Package>>();
		
		allPackagesFromModel.put(codeModelToSearch.getName(), this.getAllPackagesFrom(codeModelToSearch));
		
		return allPackagesFromModel;
	}

	@Override
	public Map<String, List<Package>> getAllFromPackage(Package packageToSearch) {
		
		Map<String, List<Package>> allPackagesFromPackage = new HashMap<String, List<Package>>();
		
		allPackagesFromPackage.put(packageToSearch.getName(), this.getAllPackagesFrom(packageToSearch));
		
		return allPackagesFromPackage;
	}
	
	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromClassUnit(ClassUnit classUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromMethodUnit(MethodUnit methodUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromStorableUnit(StorableUnit storableUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromBlockUnit(BlockUnit blockUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromSignature(Signature signatureUnitToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromActionElement(ActionElement actionElementToSearch) {
		return null;
	}

	@Override
	@Deprecated
	public Map<String, List<Package>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch) {
		return null;
	}
	
	private List<Package> getAllPackagesFrom(CodeModel codeModelToSearch) {
		List<Package> allPackages = new ArrayList<Package>();

		for (AbstractCodeElement abstractCodeElement : codeModelToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof Package){
				
				if(validateFilter((Package) abstractCodeElement)){
					allPackages.add((Package) abstractCodeElement);
				}
				allPackages.addAll(this.getAllPackagesFrom((Package)abstractCodeElement));
				
			}
			
		}
		
		return allPackages ;
	}

	private List<Package> getAllPackagesFrom(Package packageToSearch) {
		List<Package> allPackages = new ArrayList<Package>();

		for (AbstractCodeElement abstractCodeElement : packageToSearch.getCodeElement()) {
			
			if(abstractCodeElement instanceof Package){
				
				if(validateFilter((Package) abstractCodeElement)){
					allPackages.add((Package) abstractCodeElement);
				}
				allPackages.addAll(this.getAllPackagesFrom((Package)abstractCodeElement));
				
			}
			
		}
		
		return allPackages ;
	}
	
}
