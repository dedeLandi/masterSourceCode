package br.ufscar.KDM_MANAGEMENT.readers.modelReaders.interfaces;

import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

public interface KDMModelGenericReader<T> {
	
	public Map<String, Map<String, List<T>>> getAllFromSegment(Segment segmentToSearch);
	
	public Map<String, List<T>> getAllStructureModelFromSegment(Segment segmentToSearch);
	
	public Map<String, List<T>> getAllCodeModelFromSegment(Segment segmentToSearch);
	
	public List<T> getStructureModelFromSegmentByName(Segment segmentToSearch, String name);
	
	public List<T> getCodeModelFromSegmentByName(Segment segmentToSearch, String name);
	
	public List<T> getModelFromSegmentByName(Segment segmentToSearch, String name);

}
