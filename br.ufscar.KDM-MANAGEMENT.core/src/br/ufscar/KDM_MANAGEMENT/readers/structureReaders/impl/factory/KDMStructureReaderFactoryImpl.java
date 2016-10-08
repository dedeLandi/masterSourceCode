package br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.factory;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.gmt.modisco.omg.kdm.structure.ArchitectureView;
import org.eclipse.gmt.modisco.omg.kdm.structure.Component;
import org.eclipse.gmt.modisco.omg.kdm.structure.Layer;
import org.eclipse.gmt.modisco.omg.kdm.structure.SoftwareSystem;
import org.eclipse.gmt.modisco.omg.kdm.structure.Subsystem;

import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.factory.KDMStructureReaderFactory;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.readers.layers.KDMArchitectureViewReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.readers.layers.KDMComponentReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.readers.layers.KDMLayerReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.readers.layers.KDMSoftwareSystemReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.readers.layers.KDMSubsystemReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.interfaces.KDMStructureGenericReader;

public class KDMStructureReaderFactoryImpl extends EFactoryImpl implements KDMStructureReaderFactory {

	public static KDMStructureReaderFactory init() {
		try {
			KDMStructureReaderFactory theKDMCodeReaderFactory = (KDMStructureReaderFactory)EPackage.Registry.INSTANCE.getEFactory("br.ufscar.KDM_MANAGEMENT.readers.structureReaders.factory.KDMStructureReaderFactory"); //$NON-NLS-1$ 
			if (theKDMCodeReaderFactory != null) {
				return theKDMCodeReaderFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KDMStructureReaderFactoryImpl();
	}
	
	public KDMStructureReaderFactoryImpl() {
		super();
	}

	@Override
	public KDMStructureGenericReader<Layer> createKDMLayerReader() {
		return new KDMLayerReaderImpl();
	}

	@Override
	public KDMStructureGenericReader<Layer> createKDMLayerReaderWithFilter(String nameElement) {
		return new KDMLayerReaderImpl(nameElement);
	}

	@Override
	public KDMStructureGenericReader<Component> createKDMComponentReader() {
		return new KDMComponentReaderImpl();
	}

	@Override
	public KDMStructureGenericReader<Component> createKDMComponentReaderWithFilter(String nameElement) {
		return new KDMComponentReaderImpl(nameElement);
	}

	@Override
	public KDMStructureGenericReader<Subsystem> createKDMSubsystemReader() {
		return new KDMSubsystemReaderImpl();
	}

	@Override
	public KDMStructureGenericReader<Subsystem> createKDMSubsystemReaderWithFilter(String nameElement) {
		return new KDMSubsystemReaderImpl(nameElement);
	}

	@Override
	public KDMStructureGenericReader<ArchitectureView> createKDMArchitectureViewReader() {
		return new KDMArchitectureViewReaderImpl();
	}

	@Override
	public KDMStructureGenericReader<ArchitectureView> createKDMArchitectureViewReaderWithFilter(String nameElement) {
		return new KDMArchitectureViewReaderImpl(nameElement);
	}

	@Override
	public KDMStructureGenericReader<SoftwareSystem> createKDMSoftwareSystemReader() {
		return new KDMSoftwareSystemReaderImpl();
	}

	@Override
	public KDMStructureGenericReader<SoftwareSystem> createKDMSoftwareSystemReaderWithFilter(String nameElement) {
		return new KDMSoftwareSystemReaderImpl(nameElement);
	}

}
