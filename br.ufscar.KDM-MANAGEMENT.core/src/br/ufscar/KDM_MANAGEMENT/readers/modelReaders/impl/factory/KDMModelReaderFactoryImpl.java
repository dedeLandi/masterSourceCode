package br.ufscar.KDM_MANAGEMENT.readers.modelReaders.impl.factory;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;

import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.factory.KDMModelReaderFactory;
import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.impl.readers.KDMModelReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.interfaces.KDMModelGenericReader;

public class KDMModelReaderFactoryImpl extends EFactoryImpl implements KDMModelReaderFactory {

	
	public static KDMModelReaderFactory init() {
		try {
			KDMModelReaderFactory theKDMModelReaderFactory = (KDMModelReaderFactory)EPackage.Registry.INSTANCE.getEFactory("br.ufscar.KDM_MANAGEMENT.readers.modelReaders.factory.RecoverRelationsFactory"); //$NON-NLS-1$ 
			if (theKDMModelReaderFactory != null) {
				return theKDMModelReaderFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KDMModelReaderFactoryImpl();
	}
	
	public KDMModelReaderFactoryImpl() {
		super();
	}

	@Override
	public KDMModelGenericReader<KDMModel> createKDMModelReader() {
		return new KDMModelReaderImpl();
	}

}
