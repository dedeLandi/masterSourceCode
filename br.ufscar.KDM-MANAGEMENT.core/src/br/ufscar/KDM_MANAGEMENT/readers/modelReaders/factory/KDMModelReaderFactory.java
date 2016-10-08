package br.ufscar.KDM_MANAGEMENT.readers.modelReaders.factory;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;

import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.interfaces.KDMModelGenericReader;


public interface KDMModelReaderFactory extends EFactory {

	KDMModelReaderFactory eINSTANCE = br.ufscar.KDM_MANAGEMENT.readers.modelReaders.impl.factory.KDMModelReaderFactoryImpl.init();

	KDMModelGenericReader<KDMModel> createKDMModelReader();
	
}
