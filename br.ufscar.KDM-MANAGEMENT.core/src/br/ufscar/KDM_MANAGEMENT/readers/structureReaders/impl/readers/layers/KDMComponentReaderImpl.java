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

public class KDMComponentReaderImpl implements KDMStructureGenericReader<Component>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMComponentReaderImpl() {
		super();
	}

	public KDMComponentReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(Component elementToValidate) {
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
	public Map<String, List<Component>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<Component>> layersPerModel = new HashMap<String, List<Component>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllStructureModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				layersPerModel.put(kdmModel.getName(), this.getAllComponentFrom((StructureModel) kdmModel));
				
			}
		}

		return layersPerModel;
	}

	@Override
	public Map<String, List<Component>> getAllFromModel(StructureModel structureModelToSearch) {
		Map<String, List<Component>> allFromModel = new HashMap<String, List<Component>>();
		
		allFromModel.put(structureModelToSearch.getName(), this.getAllComponentFrom(structureModelToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Component>> getAllFromSoftwareSystem(SoftwareSystem softwareSystemToSearch) {
		Map<String, List<Component>> allFromModel = new HashMap<String, List<Component>>();
		
		allFromModel.put(softwareSystemToSearch.getName(), this.getAllComponentFrom(softwareSystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Component>> getAllFromSubsytem(Subsystem subsystemToSearch) {
		Map<String, List<Component>> allFromModel = new HashMap<String, List<Component>>();
		
		allFromModel.put(subsystemToSearch.getName(), this.getAllComponentFrom(subsystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Component>> getAllFromArchitectureView(ArchitectureView architectureViewToSearch) {
		Map<String, List<Component>> allFromModel = new HashMap<String, List<Component>>();
		
		allFromModel.put(architectureViewToSearch.getName(), this.getAllComponentFrom(architectureViewToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Component>> getAllFromLayer(Layer layerToSearch) {
		Map<String, List<Component>> allFromModel = new HashMap<String, List<Component>>();
		
		allFromModel.put(layerToSearch.getName(), this.getAllComponentFrom(layerToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Component>> getAllFromComponents(Component componentToSearch) {
		Map<String, List<Component>> allFromModel = new HashMap<String, List<Component>>();
		
		allFromModel.put(componentToSearch.getName(), this.getAllComponentFrom(componentToSearch));
		
		return allFromModel;
	}	

	private List<Component> getAllComponentFrom(StructureModel structureModelToSearch) {
		List<Component> allComponent = new ArrayList<Component>();
		
		for (AbstractStructureElement abstractStructureElement : structureModelToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allComponent.addAll(this.getAllComponentFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				if (validateFilter((Component) abstractStructureElement)) {
					allComponent.add((Component) abstractStructureElement);
				}
				allComponent.addAll(this.getAllComponentFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allComponent.addAll(this.getAllComponentFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allComponent.addAll(this.getAllComponentFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allComponent.addAll(this.getAllComponentFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allComponent;
	}
	
	private List<Component> getAllComponentFrom(Layer layerToSearch) {
		List<Component> allComponent = new ArrayList<Component>();
		
		for (AbstractStructureElement abstractStructureElement : layerToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allComponent.addAll(this.getAllComponentFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				if (validateFilter((Component) abstractStructureElement)) {
					allComponent.add((Component) abstractStructureElement);
				}
				allComponent.addAll(this.getAllComponentFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allComponent.addAll(this.getAllComponentFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allComponent.addAll(this.getAllComponentFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allComponent.addAll(this.getAllComponentFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allComponent;
	}
	
	private List<Component> getAllComponentFrom(Component componentToSearch) {
		List<Component> allComponent = new ArrayList<Component>();
		
		for (AbstractStructureElement abstractStructureElement : componentToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allComponent.addAll(this.getAllComponentFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				if (validateFilter((Component) abstractStructureElement)) {
					allComponent.add((Component) abstractStructureElement);
				}
				allComponent.addAll(this.getAllComponentFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allComponent.addAll(this.getAllComponentFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allComponent.addAll(this.getAllComponentFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allComponent.addAll(this.getAllComponentFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allComponent;
	}
	
	private List<Component> getAllComponentFrom(Subsystem subsystemToSearch) {
		List<Component> allComponent = new ArrayList<Component>();
		
		for (AbstractStructureElement abstractStructureElement : subsystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allComponent.addAll(this.getAllComponentFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				if (validateFilter((Component) abstractStructureElement)) {
					allComponent.add((Component) abstractStructureElement);
				}
				allComponent.addAll(this.getAllComponentFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allComponent.addAll(this.getAllComponentFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allComponent.addAll(this.getAllComponentFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allComponent.addAll(this.getAllComponentFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allComponent;
	}
	
	private List<Component> getAllComponentFrom(ArchitectureView architectureViewToSearch) {
		List<Component> allComponent = new ArrayList<Component>();
		
		for (AbstractStructureElement abstractStructureElement : architectureViewToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allComponent.addAll(this.getAllComponentFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				if (validateFilter((Component) abstractStructureElement)) {
					allComponent.add((Component) abstractStructureElement);
				}
				allComponent.addAll(this.getAllComponentFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allComponent.addAll(this.getAllComponentFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allComponent.addAll(this.getAllComponentFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allComponent.addAll(this.getAllComponentFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allComponent;
	}
	
	private List<Component> getAllComponentFrom(SoftwareSystem softwareSystemToSearch) {
		List<Component> allComponent = new ArrayList<Component>();

		for (AbstractStructureElement abstractStructureElement : softwareSystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allComponent.addAll(this.getAllComponentFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				if (validateFilter((Component) abstractStructureElement)) {
					allComponent.add((Component) abstractStructureElement);
				}
				allComponent.addAll(this.getAllComponentFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allComponent.addAll(this.getAllComponentFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allComponent.addAll(this.getAllComponentFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allComponent.addAll(this.getAllComponentFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allComponent;
	}
}
