package br.ufscar.KDM_MANAGEMENT.packages;

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

	private Map<String, List<Package>> packagesPerModel = null;

	public KDMPackageReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
		this.modelMain = null;
	}

	public KDMPackageReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.segmentMain = null;
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

		this.packagesPerModel = new HashMap<String, List<Package>>();

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

				this.packagesPerModel.put(nameCodeModel, allPackages);
			}

		}

		return this.packagesPerModel;
	}

	private Map<String, List<Package>> getAllPackagesModel() {

		this.packagesPerModel = new HashMap<String, List<Package>>();

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

		this.packagesPerModel.put(this.modelMain.getName(), allPackages);

		return this.packagesPerModel;
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

}
