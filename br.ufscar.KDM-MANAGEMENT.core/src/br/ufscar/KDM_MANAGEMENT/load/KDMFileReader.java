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

	private int typeReadChoose = 0;
	private Segment kdmSegment = null;

	public KDMFileReader(String KDMModelFullPath, int typeRead) {

		try{

			typeReadChoose = typeRead;
			
			switch (typeReadChoose) {
			case 1:
				readToSegment(KDMModelFullPath);
				break;

			default:
				throw new KDMFileException("Wrong type of read");
			}

		} catch (KDMFileException e) {
			e.printStackTrace();
		}

	}

	private void readToSegment(String KDMModelFullPath) throws KDMFileException {
		System.err.println("KDM Path: " + KDMModelFullPath);



		if("".equalsIgnoreCase(KDMModelFullPath)){
			throw new KDMFileException();
		}
		KdmPackage.eINSTANCE.eClass();//get the KDMPackage instance

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("fer", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		// Get the resource
		Resource resource = resSet.getResource(URI.createURI(KDMModelFullPath),
				true);

		if(resource == null){
			throw new KDMFileException("Wrong path of KDM File");
		}else{
			setKdmSegment((Segment) resource.getContents().get(0)); //pega o primeiro elemento, que e o Segment
		}

	}

	public Object getKdm() {
		try{

			switch (typeReadChoose) {
			case 1:
				return kdmSegment;

			default:
				throw new KDMFileException("Wrong type of read");
			}

		} catch (KDMFileException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void setKdmSegment(Segment kdmSegment) {
		this.kdmSegment = kdmSegment;
	}
}
