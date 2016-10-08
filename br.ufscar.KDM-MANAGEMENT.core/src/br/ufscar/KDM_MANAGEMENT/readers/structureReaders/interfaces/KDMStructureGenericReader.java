package br.ufscar.KDM_MANAGEMENT.readers.structureReaders.interfaces;

import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.structure.ArchitectureView;
import org.eclipse.gmt.modisco.omg.kdm.structure.Component;
import org.eclipse.gmt.modisco.omg.kdm.structure.Layer;
import org.eclipse.gmt.modisco.omg.kdm.structure.SoftwareSystem;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructureModel;
import org.eclipse.gmt.modisco.omg.kdm.structure.Subsystem;

public interface  KDMStructureGenericReader<T> {

	public Map<String, List<T>> getAllFromSegment(Segment segmentToSearch);
	
	public Map<String, List<T>> getAllFromModel(StructureModel structureModelToSearch);
	
	public Map<String, List<T>> getAllFromSoftwareSystem(SoftwareSystem softwareSystemToSearch);
	
	public Map<String, List<T>> getAllFromSubsytem(Subsystem subsystemToSearch);
	
	public Map<String, List<T>> getAllFromArchitectureView(ArchitectureView architectureViewToSearch);
	
	public Map<String, List<T>> getAllFromLayer(Layer layerToSearch);

	public Map<String, List<T>> getAllFromComponents(Component componentToSearch);
	
}
