package br.ufscar.KDM_MANAGEMENT.readers.structureReaders.impl.readers.layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.structure.AbstractStructureElement;
import org.eclipse.gmt.modisco.omg.kdm.structure.ArchitectureView;
import org.eclipse.gmt.modisco.omg.kdm.structure.Component;
import org.eclipse.gmt.modisco.omg.kdm.structure.Layer;
import org.eclipse.gmt.modisco.omg.kdm.structure.SoftwareSystem;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructureModel;
import org.eclipse.gmt.modisco.omg.kdm.structure.Subsystem;

import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.factory.KDMModelReaderFactory;
import br.ufscar.KDM_MANAGEMENT.readers.structureReaders.interfaces.KDMStructureGenericReader;

public class KDMLayerReaderImpl implements KDMStructureGenericReader<Layer>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMLayerReaderImpl() {
		super();
	}

	public KDMLayerReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(Layer elementToValidate) {
		if(this.hasNoFilter){
			return true;
		}else if(this.hasFilterName){
			if(elementToValidate.getName().equalsIgnoreCase(this.filterName)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<String, List<Layer>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<Layer>> layersPerModel = new HashMap<String, List<Layer>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllStructureModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				layersPerModel.put(kdmModel.getName(), this.getAllLayersFrom((StructureModel) kdmModel));
				
			}
		}

		return layersPerModel;
	}

	@Override
	public Map<String, List<Layer>> getAllFromModel(StructureModel structureModelToSearch) {
		Map<String, List<Layer>> allFromModel = new HashMap<String, List<Layer>>();
		
		allFromModel.put(structureModelToSearch.getName(), this.getAllLayersFrom(structureModelToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Layer>> getAllFromSoftwareSystem(SoftwareSystem softwareSystemToSearch) {
		Map<String, List<Layer>> allFromModel = new HashMap<String, List<Layer>>();
		
		allFromModel.put(softwareSystemToSearch.getName(), this.getAllLayersFrom(softwareSystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Layer>> getAllFromSubsytem(Subsystem subsystemToSearch) {
		Map<String, List<Layer>> allFromModel = new HashMap<String, List<Layer>>();
		
		allFromModel.put(subsystemToSearch.getName(), this.getAllLayersFrom(subsystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Layer>> getAllFromArchitectureView(ArchitectureView architectureViewToSearch) {
		Map<String, List<Layer>> allFromModel = new HashMap<String, List<Layer>>();
		
		allFromModel.put(architectureViewToSearch.getName(), this.getAllLayersFrom(architectureViewToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Layer>> getAllFromLayer(Layer layerToSearch) {
		Map<String, List<Layer>> allFromModel = new HashMap<String, List<Layer>>();
		
		allFromModel.put(layerToSearch.getName(), this.getAllLayersFrom(layerToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Layer>> getAllFromComponents(Component componentToSearch) {
		Map<String, List<Layer>> allFromModel = new HashMap<String, List<Layer>>();
		
		allFromModel.put(componentToSearch.getName(), this.getAllLayersFrom(componentToSearch));
		
		return allFromModel;
	}	

	private List<Layer> getAllLayersFrom(StructureModel structureModelToSearch) {
		List<Layer> allLayers = new ArrayList<Layer>();
		
		for (AbstractStructureElement abstractStructureElement : structureModelToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				if (validateFilter((Layer) abstractStructureElement)) {
					allLayers.add((Layer) abstractStructureElement);
				}
				allLayers.addAll(this.getAllLayersFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allLayers.addAll(this.getAllLayersFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allLayers.addAll(this.getAllLayersFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allLayers.addAll(this.getAllLayersFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allLayers.addAll(this.getAllLayersFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allLayers;
	}
	
	private List<Layer> getAllLayersFrom(Layer layerToSearch) {
		List<Layer> allLayers = new ArrayList<Layer>();
		
		for (AbstractStructureElement abstractStructureElement : layerToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				if (validateFilter((Layer) abstractStructureElement)) {
					allLayers.add((Layer) abstractStructureElement);
				}
				allLayers.addAll(this.getAllLayersFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allLayers.addAll(this.getAllLayersFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allLayers.addAll(this.getAllLayersFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allLayers.addAll(this.getAllLayersFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allLayers.addAll(this.getAllLayersFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allLayers;
	}
	
	private List<Layer> getAllLayersFrom(Component componentToSearch) {
		List<Layer> allLayers = new ArrayList<Layer>();
		
		for (AbstractStructureElement abstractStructureElement : componentToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				if (validateFilter((Layer) abstractStructureElement)) {
					allLayers.add((Layer) abstractStructureElement);
				}
				allLayers.addAll(this.getAllLayersFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allLayers.addAll(this.getAllLayersFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allLayers.addAll(this.getAllLayersFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allLayers.addAll(this.getAllLayersFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allLayers.addAll(this.getAllLayersFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allLayers;
	}
	
	private List<Layer> getAllLayersFrom(Subsystem subsystemToSearch) {
		List<Layer> allLayers = new ArrayList<Layer>();
		
		for (AbstractStructureElement abstractStructureElement : subsystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				if (validateFilter((Layer) abstractStructureElement)) {
					allLayers.add((Layer) abstractStructureElement);
				}
				allLayers.addAll(this.getAllLayersFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allLayers.addAll(this.getAllLayersFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allLayers.addAll(this.getAllLayersFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allLayers.addAll(this.getAllLayersFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allLayers.addAll(this.getAllLayersFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allLayers;
	}
	
	private List<Layer> getAllLayersFrom(ArchitectureView architectureViewToSearch) {
		List<Layer> allLayers = new ArrayList<Layer>();
		
		for (AbstractStructureElement abstractStructureElement : architectureViewToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				if (validateFilter((Layer) abstractStructureElement)) {
					allLayers.add((Layer) abstractStructureElement);
				}
				allLayers.addAll(this.getAllLayersFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allLayers.addAll(this.getAllLayersFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allLayers.addAll(this.getAllLayersFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allLayers.addAll(this.getAllLayersFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allLayers.addAll(this.getAllLayersFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allLayers;
	}
	
	private List<Layer> getAllLayersFrom(SoftwareSystem softwareSystemToSearch) {
		List<Layer> allLayers = new ArrayList<Layer>();

		for (AbstractStructureElement abstractStructureElement : softwareSystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				if (validateFilter((Layer) abstractStructureElement)) {
					allLayers.add((Layer) abstractStructureElement);
				}
				allLayers.addAll(this.getAllLayersFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allLayers.addAll(this.getAllLayersFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allLayers.addAll(this.getAllLayersFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allLayers.addAll(this.getAllLayersFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allLayers.addAll(this.getAllLayersFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allLayers;
	}
}
