package br.ufscar.KDM_MANAGEMENT.tests;

import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.classes.KDMClassesReader;
import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.load.KDMFileReader;
import br.ufscar.KDM_MANAGEMENT.models.KDMModelReader;
import br.ufscar.KDM_MANAGEMENT.packages.KDMPackageReader;

public class TestesComKDM {

	public static void main(String[] args) {
		try {
			String KDMpathDrifts = "C:\\TestsPlug-in\\labSystemKDMComdesvios.xmi";

			System.out.println("-----------------------------Read KDM--------------------------------");

			Object KDMFile = new KDMFileReader(KDMpathDrifts, KDMFileReader.READ_KDM_TO_SEGMENT).getKdmRead();

			System.out.println("-----------------------------All Models--------------------------------");

			getKDMModels(KDMFile);

			System.out.println("-----------------------------All Packages--------------------------------");

			getKDMPackages(KDMFile);

			System.out.println("-----------------------------Model Packages--------------------------------");

			getKDMPackagesByModel(KDMFile);

			System.out.println("-----------------------------All Classes--------------------------------");

			getKDMClasses(KDMFile);
			
			System.out.println("-----------------------------Model Classes--------------------------------");
			
			getKDMClassesByModel(KDMFile);

			System.out.println("-----------------------------Package Classes--------------------------------");

			getKDMClassesByPackage(KDMFile);


		} catch (KDMModelTypeException e) {
			e.printStackTrace();
		}


	}

	private static void getKDMClassesByPackage(Object kDMFile) throws KDMModelTypeException {
		
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReader(KDMTree).getAllCodeModel();
		
		Map<String, List<Package>> allPackages = new KDMPackageReader(allCodeModels.get("LabSys").get(0)).getAllPackages();

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassesReader(allPackages.get("LabSys").get(0)).getAllClasses();
		
		
		for (String nameCodeModel : classesPerCodeModel.keySet()) {

			List<ClassUnit> classes = classesPerCodeModel.get(nameCodeModel);

			String classesString = "";

			for (ClassUnit cls : classes) {
				classesString = classesString.concat(cls.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Classes: " + classesString);
		}
	}

	private static void getKDMClassesByModel(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReader(KDMTree).getAllCodeModel();

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassesReader(allCodeModels.get("LabSys").get(0)).getAllClasses();

		for (String nameCodeModel : classesPerCodeModel.keySet()) {

			List<ClassUnit> classes = classesPerCodeModel.get(nameCodeModel);

			String classesString = "";

			for (ClassUnit cls : classes) {
				classesString = classesString.concat(cls.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Classes: " + classesString);
		}
	}

	private static void getKDMClasses(Object kDMFile) {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassesReader(KDMTree).getAllClasses();

		for (String nameCodeModel : classesPerCodeModel.keySet()) {

			List<ClassUnit> classes = classesPerCodeModel.get(nameCodeModel);

			String classesString = "";

			for (ClassUnit cls : classes) {
				classesString = classesString.concat(cls.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Classes: " + classesString);
		}

	}

	private static void getKDMPackagesByModel(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReader(KDMTree).getAllCodeModel();

		Map<String, List<Package>> packagesPerCodeModel = new KDMPackageReader(allCodeModels.get("LabSys").get(0)).getAllPackages();

		for (String nameCodeModel : packagesPerCodeModel.keySet()) {

			List<Package> packages = packagesPerCodeModel.get(nameCodeModel);

			String packagesString = "";

			for (Package pack : packages) {
				packagesString = packagesString.concat(pack.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Packages: " + packagesString);
		}
	}

	private static void getKDMPackages(Object kDMFile) {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<Package>> packagesPerCodeModel = new KDMPackageReader(KDMTree).getAllPackages();

		for (String nameCodeModel : packagesPerCodeModel.keySet()) {

			List<Package> packages = packagesPerCodeModel.get(nameCodeModel);

			String packagesString = "";

			for (Package pack : packages) {
				packagesString = packagesString.concat(pack.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Packages: " + packagesString);
		}


	}

	private static void getKDMModels(Object kDMFile) {
		Segment KDMTree = (Segment) kDMFile;
		Map<String, Map<String, List<KDMModel>>> allModels = new KDMModelReader(KDMTree).getAllModels();
		for (String key : allModels.keySet()) {
			Map<String, List<KDMModel>> map = allModels.get(key);
			if(map != null){
				String temp = "";
				for (String key2 : map.keySet()) {

					if(map.get(key2) == null){
						temp = temp.concat(key2 + "(null), ");
					}else{
						temp = temp.concat(key2 + "(" + map.get(key2).size() + "), ");
					}
				}
				System.out.println(key + " - " + temp);
			}else{
				System.out.println(key + " - null");
			}
		}
	}

}
