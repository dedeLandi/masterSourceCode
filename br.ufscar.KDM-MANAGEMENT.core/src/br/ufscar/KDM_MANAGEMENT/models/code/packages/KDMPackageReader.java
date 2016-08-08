package br.ufscar.KDM_MANAGEMENT.models.code.packages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.models.KDMModelReader;


public class KDMPackageReader {

	private Segment segmentMain = null;
	private CodeModel modelMain = null;

	public KDMPackageReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}

	public KDMPackageReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}

	}

	public Map<String, List<Package>> getAllPackages() {
		if(this.segmentMain != null){
			return getAllPackagesSegment();
		}else if(this.modelMain != null){
			return getAllPackagesModel();
		}else{
			return null;
		}

	}

	private Map<String, List<Package>> getAllPackagesSegment() {

		Map<String, List<Package>> packagesPerModel = new HashMap<String, List<Package>>();

		Map<String, List<KDMModel>> models = new KDMModelReader(segmentMain).getAllCodeModel(); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				List<Package> allPackages = new ArrayList<Package>();

				CodeModel codeModel = (CodeModel) kdmModel;

				EList<AbstractCodeElement> elements = codeModel.getCodeElement();

				for (int i = 0; i < elements.size() ; i++) {

					if (elements.get(i) instanceof Package) {

						Package packageKDM = (Package) elements.get(i);

						allPackages = (List<Package>) this.getAllPackages(
								packageKDM, allPackages);

					}

				}

				packagesPerModel.put(nameCodeModel, allPackages);
			}

		}

		return packagesPerModel;
	}

	private Map<String, List<Package>> getAllPackagesModel() {

		Map<String, List<Package>> packagesPerModel = new HashMap<String, List<Package>>();

		List<Package> allPackages = new ArrayList<Package>();

		CodeModel codeModel = (CodeModel) this.modelMain;

		EList<AbstractCodeElement> elements = codeModel.getCodeElement();

		for (int i = 0; i < elements.size() ; i++) {

			if (elements.get(i) instanceof Package) {

				Package packageKDM = (Package) elements.get(i);

				allPackages = (List<Package>) this.getAllPackages(
						packageKDM, allPackages);

			}

		}

		packagesPerModel.put(this.modelMain.getName(), allPackages);

		return packagesPerModel;
	}

	/**
	 * Recursive package search
	 * @param packageToGet
	 * @param allPackages
	 * @return
	 */
	private List<Package> getAllPackages(Package packageToGet,
			List<Package> allPackages) {

		EList<AbstractCodeElement> elements = packageToGet.getCodeElement();

		for (AbstractCodeElement abstractCodeElement : elements) {
			if (abstractCodeElement instanceof Package){
				allPackages = getAllPackages((Package) abstractCodeElement,
						allPackages);
			}else {
				allPackages.add(packageToGet);
				return allPackages;
			}

		}
		allPackages.add(packageToGet);
		return allPackages;
	}

	public Map<String, List<Package>> getPackageByName(String namePackage){

		if(segmentMain != null){
			return getPackageByNameSegment(namePackage);
		}else if (modelMain != null){
			return getPackageByNameModel(namePackage);
		}else{
			return null;
		}
	}

	private Map<String, List<Package>> getPackageByNameModel(String namePackage) {
		Map<String, List<Package>> packagesPerModel = new HashMap<String, List<Package>>();

		List<Package> allPackages = new ArrayList<Package>();

		CodeModel codeModel = (CodeModel) this.modelMain;

		EList<AbstractCodeElement> elements = codeModel.getCodeElement();

		for (int i = 0; i < elements.size() ; i++) {

			if (elements.get(i) instanceof Package) {

				Package packageKDM = (Package) elements.get(i);

				allPackages = (List<Package>) this.getAllPackagesNamed(namePackage, packageKDM, allPackages);

			}

		}

		packagesPerModel.put(this.modelMain.getName(), allPackages);

		return packagesPerModel;
	}

	private Map<String, List<Package>> getPackageByNameSegment(String namePackage) {
		Map<String, List<Package>> packageNamed = new HashMap<String, List<Package>>();

		Map<String, List<KDMModel>> models = new KDMModelReader(segmentMain).getAllCodeModel(); 

		for (String nameCodeModel : models.keySet()) {

			List<Package> packagesFound = new ArrayList<Package>();

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				CodeModel codeModel = (CodeModel) kdmModel;

				EList<AbstractCodeElement> elements = codeModel.getCodeElement();

				for (int i = 0; i < elements.size() ; i++) {

					if (elements.get(i) instanceof Package) {

						Package packageKDM = (Package) elements.get(i);

						packagesFound = (List<Package>) this.getAllPackagesNamed(namePackage, packageKDM, packagesFound);

					}
				}
			}
			
			packageNamed.put(nameCodeModel, packagesFound);
		}

		return packageNamed;
	}

	/**
	 * Recursive search for packages named 
	 * @param namePackage
	 * @param packageToGet
	 * @param packagesFound
	 * @return
	 */
	private List<Package> getAllPackagesNamed(String namePackage, Package packageToGet, List<Package> packagesFound) {
		EList<AbstractCodeElement> elements = packageToGet.getCodeElement();

		for (AbstractCodeElement abstractCodeElement : elements) {

			if (abstractCodeElement instanceof Package){
				packagesFound = getAllPackagesNamed(namePackage, (Package) abstractCodeElement,
						packagesFound);
			}else {
				if(namePackage.equalsIgnoreCase(packageToGet.getName())){
					packagesFound.add(packageToGet);
				}
				return packagesFound;
			}

		}
		if(namePackage.equalsIgnoreCase(packageToGet.getName())){
			packagesFound.add(packageToGet);
		}
		return packagesFound;
	}
}
