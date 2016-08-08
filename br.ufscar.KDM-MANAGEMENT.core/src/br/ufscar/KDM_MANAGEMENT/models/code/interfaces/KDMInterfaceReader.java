package br.ufscar.KDM_MANAGEMENT.models.code.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.models.KDMModelReader;

public class KDMInterfaceReader {

	private Segment segmentMain = null;
	private CodeModel modelMain = null;
	private Package packageMain = null;

	public KDMInterfaceReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMInterfaceReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof CodeModel){
			this.modelMain = (CodeModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}
	public KDMInterfaceReader(Package kdmPackage) {
		this.packageMain = kdmPackage;
	}

	public Map<String, List<InterfaceUnit>> getAllInterfaces() {
		if(this.segmentMain != null){
			return getAllInterfacesSegment();
		}else if(this.modelMain != null){
			return getAllInterfacesModel();
		}else if(this.packageMain != null){
			return getAllInterfacesPackage();
		}else{
			return null;
		}

	}

	private Map<String, List<InterfaceUnit>> getAllInterfacesSegment() {

		Map<String, List<InterfaceUnit>> interfacesPerModel = new HashMap<String, List<InterfaceUnit>>();

		Map<String, List<KDMModel>> models = new KDMModelReader(this.segmentMain).getAllCodeModel(); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				List<InterfaceUnit> allInterfaces = new ArrayList<InterfaceUnit>();

				CodeModel codeModel = (CodeModel) kdmModel;

				EList<AbstractCodeElement> elements = codeModel.getCodeElement();

				for (int i = 0; i < elements.size() ; i++) {

					if (elements.get(i) instanceof Package) {

						Package packageKDM = (Package) elements.get(i);

						this.getInterfaces(packageKDM.getCodeElement(), allInterfaces);
					}
				}

				interfacesPerModel.put(nameCodeModel, allInterfaces);
			}
		}

		return interfacesPerModel;

	}

	private Map<String, List<InterfaceUnit>> getAllInterfacesModel() {

		Map<String, List<InterfaceUnit>> interfacesPerModel = new HashMap<String, List<InterfaceUnit>>();

		List<InterfaceUnit> allInterfaces = new ArrayList<InterfaceUnit>();

		CodeModel codeModel = (CodeModel) this.modelMain;

		EList<AbstractCodeElement> elements = codeModel.getCodeElement();

		for (int i = 0; i < elements.size() ; i++) {

			if (elements.get(i) instanceof Package) {

				Package packageKDM = (Package) elements.get(i);

				this.getInterfaces(packageKDM.getCodeElement(), allInterfaces);

			}

		}

		interfacesPerModel.put(this.modelMain.getName(), allInterfaces);

		return interfacesPerModel;

	}

	private Map<String, List<InterfaceUnit>> getAllInterfacesPackage() {

		Map<String, List<InterfaceUnit>> interfacesPerModel = new HashMap<String, List<InterfaceUnit>>();

		List<InterfaceUnit> allInterfaces = new ArrayList<InterfaceUnit>();

		EList<AbstractCodeElement> elements = this.packageMain.getCodeElement();

		for (int i = 0; i < elements.size() ; i++) {

			if (elements.get(i) instanceof Package) {

				Package packageKDM = (Package) elements.get(i);

				this.getInterfaces(packageKDM.getCodeElement(), allInterfaces);

			}else if (elements.get(i) instanceof InterfaceUnit) {

				allInterfaces.add((InterfaceUnit) elements.get(i));

			}

		}

		interfacesPerModel.put(this.packageMain.getName(), allInterfaces);

		return interfacesPerModel;

	}

	/**
	 * Recursive Interface search 
	 * @param elements
	 * @param allClasses
	 */
	private void getInterfaces(EList<AbstractCodeElement> elements, List<InterfaceUnit> allInterfaces) {

		for (AbstractCodeElement abstractCodeElement : elements) {

			if (abstractCodeElement instanceof InterfaceUnit) {

				allInterfaces.add((InterfaceUnit) abstractCodeElement);

			} else if (abstractCodeElement instanceof Package) {

				Package packageToPass = (Package) abstractCodeElement;

				getInterfaces(packageToPass.getCodeElement(), allInterfaces);

			}

		}

	}

	public Map<String, List<InterfaceUnit>> getInterfaceByName(String nameInterface) {

		if(this.segmentMain != null){
			return getAllInterfacesSegmentName(nameInterface);
		}else if(this.modelMain != null){
			return getAllInterfacesModelName(nameInterface);
		}else if(this.packageMain != null){
			return getAllInterfacesPackageName(nameInterface);
		}else{
			return null;
		}

	}

	private Map<String, List<InterfaceUnit>> getAllInterfacesPackageName(String nameInterface) {
		Map<String, List<InterfaceUnit>> interfacesPerModel = new HashMap<String, List<InterfaceUnit>>();

		List<InterfaceUnit> allInterfaces = new ArrayList<InterfaceUnit>();

		EList<AbstractCodeElement> elements = this.packageMain.getCodeElement();

		for (int i = 0; i < elements.size() ; i++) {

			if (elements.get(i) instanceof Package) {

				Package packageKDM = (Package) elements.get(i);

				this.getInterfacesByName(nameInterface, packageKDM.getCodeElement(), allInterfaces);

			}else if (elements.get(i) instanceof InterfaceUnit) {
				InterfaceUnit ifc = (InterfaceUnit) elements.get(i);
				if(nameInterface.equalsIgnoreCase(ifc.getName())){
					allInterfaces.add(ifc);
				}

			}

		}

		interfacesPerModel.put(this.packageMain.getName(), allInterfaces);

		return interfacesPerModel;
	}
	
	private Map<String, List<InterfaceUnit>> getAllInterfacesModelName(String nameInterface) {
		Map<String, List<InterfaceUnit>> interfacesPerModel = new HashMap<String, List<InterfaceUnit>>();

		List<InterfaceUnit> allInterfaces = new ArrayList<InterfaceUnit>();

		CodeModel codeModel = (CodeModel) this.modelMain;

		EList<AbstractCodeElement> elements = codeModel.getCodeElement();

		for (int i = 0; i < elements.size() ; i++) {

			if (elements.get(i) instanceof Package) {

				Package packageKDM = (Package) elements.get(i);

				this.getInterfacesByName(nameInterface, packageKDM.getCodeElement(), allInterfaces);

			}

		}

		interfacesPerModel.put(this.modelMain.getName(), allInterfaces);

		return interfacesPerModel;
	}

	private Map<String, List<InterfaceUnit>> getAllInterfacesSegmentName(String nameInterface) {
		Map<String, List<InterfaceUnit>> interfacesPerModel = new HashMap<String, List<InterfaceUnit>>();

		Map<String, List<KDMModel>> models = new KDMModelReader(this.segmentMain).getAllCodeModel(); 

		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				List<InterfaceUnit> allInterfaces = new ArrayList<InterfaceUnit>();

				CodeModel codeModel = (CodeModel) kdmModel;

				EList<AbstractCodeElement> elements = codeModel.getCodeElement();

				for (int i = 0; i < elements.size() ; i++) {

					if (elements.get(i) instanceof Package) {

						Package packageKDM = (Package) elements.get(i);

						this.getInterfacesByName(nameInterface, packageKDM.getCodeElement(), allInterfaces);
					}
				}

				interfacesPerModel.put(nameCodeModel, allInterfaces);
			}
		}

		return interfacesPerModel;
	}

	/**
	 * Recursive search for Interface named
	 * @param elements
	 * @param allClasses
	 */
	private void getInterfacesByName(String nameInterface, EList<AbstractCodeElement> elements, List<InterfaceUnit> namedInterfaces) {

		for (AbstractCodeElement abstractCodeElement : elements) {

			if (abstractCodeElement instanceof InterfaceUnit) {
				InterfaceUnit ifc = (InterfaceUnit) abstractCodeElement;
				if(nameInterface.equalsIgnoreCase(ifc.getName())){
					namedInterfaces.add(ifc);
				}

			} else if (abstractCodeElement instanceof Package) {

				Package packageToPass = (Package) abstractCodeElement;

				getInterfacesByName(nameInterface, packageToPass.getCodeElement(), namedInterfaces);

			}

		}

	}

	
}
