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

public class KDMArchitectureViewReaderImpl implements KDMStructureGenericReader<ArchitectureView>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMArchitectureViewReaderImpl() {
		super();
	}

	public KDMArchitectureViewReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(ArchitectureView elementToValidate) {
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
	public Map<String, List<ArchitectureView>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<ArchitectureView>> layersPerModel = new HashMap<String, List<ArchitectureView>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllStructureModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				layersPerModel.put(kdmModel.getName(), this.getAllArchitectureViewFrom((StructureModel) kdmModel));
				
			}
		}

		return layersPerModel;
	}

	@Override
	public Map<String, List<ArchitectureView>> getAllFromModel(StructureModel structureModelToSearch) {
		Map<String, List<ArchitectureView>> allFromModel = new HashMap<String, List<ArchitectureView>>();
		
		allFromModel.put(structureModelToSearch.getName(), this.getAllArchitectureViewFrom(structureModelToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<ArchitectureView>> getAllFromSoftwareSystem(SoftwareSystem softwareSystemToSearch) {
		Map<String, List<ArchitectureView>> allFromModel = new HashMap<String, List<ArchitectureView>>();
		
		allFromModel.put(softwareSystemToSearch.getName(), this.getAllArchitectureViewFrom(softwareSystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<ArchitectureView>> getAllFromSubsytem(Subsystem subsystemToSearch) {
		Map<String, List<ArchitectureView>> allFromModel = new HashMap<String, List<ArchitectureView>>();
		
		allFromModel.put(subsystemToSearch.getName(), this.getAllArchitectureViewFrom(subsystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<ArchitectureView>> getAllFromArchitectureView(ArchitectureView architectureViewToSearch) {
		Map<String, List<ArchitectureView>> allFromModel = new HashMap<String, List<ArchitectureView>>();
		
		allFromModel.put(architectureViewToSearch.getName(), this.getAllArchitectureViewFrom(architectureViewToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<ArchitectureView>> getAllFromLayer(Layer layerToSearch) {
		Map<String, List<ArchitectureView>> allFromModel = new HashMap<String, List<ArchitectureView>>();
		
		allFromModel.put(layerToSearch.getName(), this.getAllArchitectureViewFrom(layerToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<ArchitectureView>> getAllFromComponents(Component componentToSearch) {
		Map<String, List<ArchitectureView>> allFromModel = new HashMap<String, List<ArchitectureView>>();
		
		allFromModel.put(componentToSearch.getName(), this.getAllArchitectureViewFrom(componentToSearch));
		
		return allFromModel;
	}	

	private List<ArchitectureView> getAllArchitectureViewFrom(StructureModel structureModelToSearch) {
		List<ArchitectureView> allArchitectureView = new ArrayList<ArchitectureView>();
		
		for (AbstractStructureElement abstractStructureElement : structureModelToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				if (validateFilter((ArchitectureView) abstractStructureElement)) {
					allArchitectureView.add((ArchitectureView) abstractStructureElement);
				}
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allArchitectureView;
	}
	
	private List<ArchitectureView> getAllArchitectureViewFrom(Layer layerToSearch) {
		List<ArchitectureView> allArchitectureView = new ArrayList<ArchitectureView>();
		
		for (AbstractStructureElement abstractStructureElement : layerToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				if (validateFilter((ArchitectureView) abstractStructureElement)) {
					allArchitectureView.add((ArchitectureView) abstractStructureElement);
				}
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allArchitectureView;
	}
	
	private List<ArchitectureView> getAllArchitectureViewFrom(Component componentToSearch) {
		List<ArchitectureView> allArchitectureView = new ArrayList<ArchitectureView>();
		
		for (AbstractStructureElement abstractStructureElement : componentToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				if (validateFilter((ArchitectureView) abstractStructureElement)) {
					allArchitectureView.add((ArchitectureView) abstractStructureElement);
				}
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allArchitectureView;
	}
	
	private List<ArchitectureView> getAllArchitectureViewFrom(Subsystem subsystemToSearch) {
		List<ArchitectureView> allArchitectureView = new ArrayList<ArchitectureView>();
		
		for (AbstractStructureElement abstractStructureElement : subsystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				if (validateFilter((ArchitectureView) abstractStructureElement)) {
					allArchitectureView.add((ArchitectureView) abstractStructureElement);
				}
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allArchitectureView;
	}
	
	private List<ArchitectureView> getAllArchitectureViewFrom(ArchitectureView architectureViewToSearch) {
		List<ArchitectureView> allArchitectureView = new ArrayList<ArchitectureView>();
		
		for (AbstractStructureElement abstractStructureElement : architectureViewToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				if (validateFilter((ArchitectureView) abstractStructureElement)) {
					allArchitectureView.add((ArchitectureView) abstractStructureElement);
				}
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allArchitectureView;
	}
	
	private List<ArchitectureView> getAllArchitectureViewFrom(SoftwareSystem softwareSystemToSearch) {
		List<ArchitectureView> allArchitectureView = new ArrayList<ArchitectureView>();

		for (AbstractStructureElement abstractStructureElement : softwareSystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				if (validateFilter((ArchitectureView) abstractStructureElement)) {
					allArchitectureView.add((ArchitectureView) abstractStructureElement);
				}
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allArchitectureView.addAll(this.getAllArchitectureViewFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allArchitectureView;
	}
}
