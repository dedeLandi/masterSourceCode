package br.ufscar.KDM_MANAGEMENT.readers.structureReaders.factory;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.gmt.modisco.omg.kdm.structure.ArchitectureView;
import org.eclipse.gmt.modisco.omg.kdm.structure.Component;
import org.eclipse.gmt.modisco.omg.kdm.structure.Layer;
import org.eclipse.gmt.modisco.omg.kdm.structure.SoftwareSystem;
import org.eclipse.gmt.modisco.omg.kdm.structure.Subsystem;

import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.interfaces.KDMStructureGenericReader;


public interface KDMStructureReaderFactory extends EFactory {

	KDMStructureReaderFactory eINSTANCE = br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.factory.KDMStructureReaderFactoryImpl.init();
	
	KDMStructureGenericReader<Layer> createKDMLayerReader();
	
	KDMStructureGenericReader<Layer> createKDMLayerReaderWithFilter(String nameElement);
	
	KDMStructureGenericReader<Component> createKDMComponentReader();
	
	KDMStructureGenericReader<Component> createKDMComponentReaderWithFilter(String nameElement);
	
	KDMStructureGenericReader<Subsystem> createKDMSubsystemReader();
	
	KDMStructureGenericReader<Subsystem> createKDMSubsystemReaderWithFilter(String nameElement);
	
	KDMStructureGenericReader<ArchitectureView> createKDMArchitectureViewReader();
	
	KDMStructureGenericReader<ArchitectureView> createKDMArchitectureViewReaderWithFilter(String nameElement);
	
	KDMStructureGenericReader<SoftwareSystem> createKDMSoftwareSystemReader();
	
	KDMStructureGenericReader<SoftwareSystem> createKDMSoftwareSystemReaderWithFilter(String nameElement);
	
}
