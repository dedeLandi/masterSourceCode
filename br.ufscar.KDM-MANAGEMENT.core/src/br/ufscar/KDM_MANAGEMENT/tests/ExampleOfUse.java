package br.ufscar.KDM_MANAGEMENT.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.modisco.infra.discovery.core.exception.DiscoveryException;

import br.ufscar.KDM_MANAGEMENT.discoverer.KDMModelDiscover;
import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.models.code.interfaces.KDMInterfaceReader;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.classes.KDMClassReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.packages.KDMPackageReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.impl.readers.KDMModelReaderImpl;

public class ExampleOfUse {

	public static void main(String[] args) {

		try {

			KDMModelDiscover kdmModelDiscover = new KDMModelDiscover();
			kdmModelDiscover.createKDMModel();
			kdmModelDiscover.creatKDMModel2();
		} catch (DiscoveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//		try {
		//			String KDMpathDrifts = "C:\\TestsPlug-in\\labSystemKDMComdesvios.xmi";
		//
		//			System.out.println("\n-----------------------------Read KDM--------------------------------");
		//
		//			Object KDMFile = new KDMFileReader(KDMpathDrifts, KDMFileReader.READ_KDM_TO_SEGMENT).getKdmRead();
		//
		//			System.err.println("\n-----------------------------All Models--------------------------------");
		//
		//			getKDMModels(KDMFile);
		//
		//			System.err.println("\n-----------------------------All Packages--------------------------------");
		//
		//			getKDMPackages(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Model Packages--------------------------------");
		//			
		//			getKDMPackagesByModel(KDMFile);
		//			
		//			System.err.println("\n-----------------------------All Packages Name--------------------------------");
		//			
		//			getKDMPackagesName(KDMFile);
		//
		//			System.err.println("\n-----------------------------Model Packages Name--------------------------------");
		//
		//			getKDMPackagesByModelName(KDMFile);
		//			
		//			System.err.println("\n-----------------------------All Classes--------------------------------");
		//			
		//			getKDMClasses(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Model Classes--------------------------------");
		//			
		//			getKDMClassesByModel(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Package Classes--------------------------------");
		//			
		//			getKDMClassesByPackage(KDMFile);
		//			
		//			System.err.println("\n-----------------------------All Classes Name--------------------------------");
		//			
		//			getKDMClassesName(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Model Classes Name--------------------------------");
		//			
		//			getKDMClassesByModelName(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Package Classes Name--------------------------------");
		//			
		//			getKDMClassesByPackageName(KDMFile);
		//			
		//			System.err.println("\n-----------------------------All Interfaces--------------------------------");
		//			
		//			getKDMInterfaces(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Model Interfaces--------------------------------");
		//			
		//			getKDMInterfacesByModel(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Package Interfaces--------------------------------");
		//			
		//			getKDMInterfacesByPackage(KDMFile);
		//
		//			System.err.println("\n-----------------------------All Interfaces Name--------------------------------");
		//
		//			getKDMInterfacesName(KDMFile);
		//			
		//			System.err.println("\n-----------------------------Model Interfaces Name--------------------------------");
		//			
		//			getKDMInterfacesByModelName(KDMFile);
		//
		//			System.err.println("\n-----------------------------Package Interfaces Name--------------------------------");
		//
		//			getKDMInterfacesByPackageName(KDMFile);
		//
		//
		//		} catch (KDMModelTypeException e) {
		//			e.printStackTrace();
		//		}
	}

	private static void getKDMInterfacesByPackageName(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<Package>> allPackages = new KDMPackageReaderImpl(allCodeModels.get("LabSys").get(0)).getAllPackages();

		Map<String, List<InterfaceUnit>> interfacesPerCodeModel = new KDMInterfaceReaderImpl(allPackages.get("LabSys").get(0)).getInterfaceByName("RRSolicitation");


		for (String nameCodeModel : interfacesPerCodeModel.keySet()) {

			List<InterfaceUnit> interfaces = interfacesPerCodeModel.get(nameCodeModel);

			String interfacesString = "";

			for (InterfaceUnit ifc : interfaces) {
				interfacesString = interfacesString.concat(ifc.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Interfaces: " + interfacesString);
		}

	}

	private static void getKDMInterfacesByModelName(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<InterfaceUnit>> interfacesPerCodeModel = new KDMInterfaceReaderImpl(allCodeModels.get("LabSys").get(0)).getInterfaceByName("URLEntity");

		for (String nameCodeModel : interfacesPerCodeModel.keySet()) {

			List<InterfaceUnit> interfaces = interfacesPerCodeModel.get(nameCodeModel);

			String interfacesString = "";

			for (InterfaceUnit ifc : interfaces) {
				interfacesString = interfacesString.concat(ifc.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Interfaces: " + interfacesString);
		}
	}

	private static void getKDMInterfacesName(Object kDMFile) {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<InterfaceUnit>> interfacesPerCodeModel = new KDMInterfaceReaderImpl(KDMTree).getInterfaceByName("RandomAccess");

		for (String nameCodeModel : interfacesPerCodeModel.keySet()) {

			List<InterfaceUnit> interfaces = interfacesPerCodeModel.get(nameCodeModel);

			String interfacesString = "";

			for (InterfaceUnit ifc : interfaces) {
				interfacesString = interfacesString.concat(ifc.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Interfaces: " + interfacesString);
		}
	}

	private static void getKDMClassesByPackageName(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<Package>> allPackages = new KDMPackageReaderImpl(allCodeModels.get("LabSys").get(0)).getAllPackages();

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassReaderImpl(allPackages.get("LabSys").get(0)).getClassByName("PatrimonyRepository");


		for (String nameCodeModel : classesPerCodeModel.keySet()) {

			List<ClassUnit> classes = classesPerCodeModel.get(nameCodeModel);

			String classesString = "";

			for (ClassUnit cls : classes) {
				classesString = classesString.concat(cls.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Classes: " + classesString);
		}

	}

	private static void getKDMClassesByModelName(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassReaderImpl(allCodeModels.get("LabSys").get(0)).getClassByName("Token");

		for (String nameCodeModel : classesPerCodeModel.keySet()) {

			List<ClassUnit> classes = classesPerCodeModel.get(nameCodeModel);

			String classesString = "";

			for (ClassUnit cls : classes) {
				classesString = classesString.concat(cls.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Classes: " + classesString);
		}
	}

	private static void getKDMClassesName(Object kDMFile) {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassReaderImpl(KDMTree).getClassByName("ChartBean");

		for (String nameCodeModel : classesPerCodeModel.keySet()) {

			List<ClassUnit> classes = classesPerCodeModel.get(nameCodeModel);

			String classesString = "";

			for (ClassUnit cls : classes) {
				classesString = classesString.concat(cls.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Classes: " + classesString);
		}
	}

	private static void getKDMPackagesByModelName(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<Package>> packagesPerCodeModel = new KDMPackageReaderImpl(allCodeModels.get("LabSys").get(0)).getPackageByName("nonExist");

		for (String nameCodeModel : packagesPerCodeModel.keySet()) {

			List<Package> packages = packagesPerCodeModel.get(nameCodeModel);

			String packagesString = "";

			for (Package pack : packages) {
				packagesString = packagesString.concat(pack.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Packages: " + packagesString);
		}
	}

	private static void getKDMPackagesName(Object kDMFile) {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<Package>> packagesPerCodeModel = new KDMPackageReaderImpl(KDMTree).getPackageByName("security");

		for (String nameCodeModel : packagesPerCodeModel.keySet()) {

			List<Package> packages = packagesPerCodeModel.get(nameCodeModel);

			String packagesString = "";

			for (Package pack : packages) {
				packagesString = packagesString.concat(pack.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Packages: " + packagesString);
		}
	}

	private static void getKDMInterfacesByPackage(Object kDMFile) throws KDMModelTypeException {

		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<Package>> allPackages = new KDMPackageReaderImpl(allCodeModels.get("LabSys").get(0)).getAllPackages();

		Map<String, List<InterfaceUnit>> interfacesPerCodeModel = new KDMInterfaceReaderImpl(allPackages.get("LabSys").get(0)).getAllInterfaces();


		for (String nameCodeModel : interfacesPerCodeModel.keySet()) {

			List<InterfaceUnit> interfaces = interfacesPerCodeModel.get(nameCodeModel);

			String interfacesString = "";

			for (InterfaceUnit ifc : interfaces) {
				interfacesString = interfacesString.concat(ifc.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Interfaces: " + interfacesString);
		}
	}

	private static void getKDMInterfacesByModel(Object kDMFile) throws KDMModelTypeException {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<InterfaceUnit>> interfacesPerCodeModel = new KDMInterfaceReaderImpl(allCodeModels.get("LabSys").get(0)).getAllInterfaces();

		for (String nameCodeModel : interfacesPerCodeModel.keySet()) {

			List<InterfaceUnit> interfaces = interfacesPerCodeModel.get(nameCodeModel);

			String interfacesString = "";

			for (InterfaceUnit ifc : interfaces) {
				interfacesString = interfacesString.concat(ifc.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Interfaces: " + interfacesString);
		}
	}

	private static void getKDMInterfaces(Object kDMFile) {
		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<InterfaceUnit>> interfacesPerCodeModel = new KDMInterfaceReaderImpl(KDMTree).getAllInterfaces();

		for (String nameCodeModel : interfacesPerCodeModel.keySet()) {

			List<InterfaceUnit> interfaces = interfacesPerCodeModel.get(nameCodeModel);

			String interfacesString = "";

			for (InterfaceUnit ifc : interfaces) {
				interfacesString = interfacesString.concat(ifc.getName() + ";");
			}

			System.out.println("Code Model:" + nameCodeModel + " - Interfaces: " + interfacesString);
		}

	}

	private static void getKDMClassesByPackage(Object kDMFile) throws KDMModelTypeException {

		Segment KDMTree = (Segment) kDMFile;

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<Package>> allPackages = new KDMPackageReaderImpl(allCodeModels.get("LabSys").get(0)).getAllPackages();

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassReaderImpl(allPackages.get("LabSys").get(0)).getAllClasses();


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

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassReaderImpl(allCodeModels.get("LabSys").get(0)).getAllClasses();

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

		Map<String, List<ClassUnit>> classesPerCodeModel = new KDMClassReaderImpl(KDMTree).getAllClasses();

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

		Map<String, List<KDMModel>> allCodeModels = new KDMModelReaderImpl(KDMTree).getAllCodeModel();

		Map<String, List<Package>> packagesPerCodeModel = new KDMPackageReaderImpl(allCodeModels.get("LabSys").get(0)).getAllPackages();

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

		Map<String, List<Package>> packagesPerCodeModel = new KDMPackageReaderImpl(KDMTree).getAllPackages();

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
		Map<String, Map<String, List<KDMModel>>> allModels = new KDMModelReaderImpl(KDMTree).getAllModels();
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
