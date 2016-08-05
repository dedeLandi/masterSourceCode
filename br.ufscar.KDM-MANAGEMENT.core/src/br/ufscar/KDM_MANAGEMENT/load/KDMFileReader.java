package br.ufscar.KDM_MANAGEMENT.load;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KdmPackage;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.exception.KDMFileException;

public class KDMFileReader {

	public static final int READ_KDM_TO_SEGMENT = 1;
	public static final int READ_KDM_TO_XPATH = 2;

	private String KDMModelFullPath = "";
	private int typeReadChoose = 0;

	public KDMFileReader(String KDMModelFullPath, int typeRead) {
		this.KDMModelFullPath = KDMModelFullPath;
		this.typeReadChoose = typeRead;
	}

	public Object getKdmRead() {
		try{

			switch (typeReadChoose) {
			
			case READ_KDM_TO_SEGMENT:
				return readToSegment(KDMModelFullPath);
			case READ_KDM_TO_XPATH:
				return readToXPath(KDMModelFullPath);

			default:
				throw new KDMFileException("Wrong type of read");
				
			}

		} catch (KDMFileException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Segment readToSegment(String KDMModelFullPath) throws KDMFileException {

		KDMModelFullPath = "file:///" + KDMModelFullPath;
		System.err.println("KDM Path: " + KDMModelFullPath);

		if("".equalsIgnoreCase(KDMModelFullPath)){
			throw new KDMFileException();
		}else{
			KdmPackage.eINSTANCE.eClass();//get the KDMPackage instance

			Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
			Map<String, Object> m = reg.getExtensionToFactoryMap();
			m.put("xmi", new XMIResourceFactoryImpl());

			// Obtain a new resource set
			ResourceSet resSet = new ResourceSetImpl();
			// Get the resource
			Resource resource = resSet.getResource(URI.createURI(KDMModelFullPath),
					true);

			if(resource == null){
				throw new KDMFileException("Wrong path of KDM File");
			}else{
				return (Segment) resource.getContents().get(0); //get the first element, that is the Segment
			}
		}

	}

	private Object readToXPath(String kDMModelFullPath2) {
		// TODO Auto-generated method stub
		return null;
	}

}
