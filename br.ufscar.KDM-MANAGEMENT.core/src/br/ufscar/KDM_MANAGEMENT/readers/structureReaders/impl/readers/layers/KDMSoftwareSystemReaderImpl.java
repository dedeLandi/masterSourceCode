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

public class KDMSoftwareSystemReaderImpl implements KDMStructureGenericReader<SoftwareSystem>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMSoftwareSystemReaderImpl() {
		super();
	}

	public KDMSoftwareSystemReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(SoftwareSystem elementToValidate) {
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
	public Map<String, List<SoftwareSystem>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<SoftwareSystem>> layersPerModel = new HashMap<String, List<SoftwareSystem>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllStructureModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				layersPerModel.put(kdmModel.getName(), this.getAllSoftwareSystemFrom((StructureModel) kdmModel));
				
			}
		}

		return layersPerModel;
	}

	@Override
	public Map<String, List<SoftwareSystem>> getAllFromModel(StructureModel structureModelToSearch) {
		Map<String, List<SoftwareSystem>> allFromModel = new HashMap<String, List<SoftwareSystem>>();
		
		allFromModel.put(structureModelToSearch.getName(), this.getAllSoftwareSystemFrom(structureModelToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<SoftwareSystem>> getAllFromSoftwareSystem(SoftwareSystem softwareSystemToSearch) {
		Map<String, List<SoftwareSystem>> allFromModel = new HashMap<String, List<SoftwareSystem>>();
		
		allFromModel.put(softwareSystemToSearch.getName(), this.getAllSoftwareSystemFrom(softwareSystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<SoftwareSystem>> getAllFromSubsytem(Subsystem subsystemToSearch) {
		Map<String, List<SoftwareSystem>> allFromModel = new HashMap<String, List<SoftwareSystem>>();
		
		allFromModel.put(subsystemToSearch.getName(), this.getAllSoftwareSystemFrom(subsystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<SoftwareSystem>> getAllFromArchitectureView(ArchitectureView architectureViewToSearch) {
		Map<String, List<SoftwareSystem>> allFromModel = new HashMap<String, List<SoftwareSystem>>();
		
		allFromModel.put(architectureViewToSearch.getName(), this.getAllSoftwareSystemFrom(architectureViewToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<SoftwareSystem>> getAllFromLayer(Layer layerToSearch) {
		Map<String, List<SoftwareSystem>> allFromModel = new HashMap<String, List<SoftwareSystem>>();
		
		allFromModel.put(layerToSearch.getName(), this.getAllSoftwareSystemFrom(layerToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<SoftwareSystem>> getAllFromComponents(Component componentToSearch) {
		Map<String, List<SoftwareSystem>> allFromModel = new HashMap<String, List<SoftwareSystem>>();
		
		allFromModel.put(componentToSearch.getName(), this.getAllSoftwareSystemFrom(componentToSearch));
		
		return allFromModel;
	}	

	private List<SoftwareSystem> getAllSoftwareSystemFrom(StructureModel structureModelToSearch) {
		List<SoftwareSystem> allSoftwareSystem = new ArrayList<SoftwareSystem>();
		
		for (AbstractStructureElement abstractStructureElement : structureModelToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				if (validateFilter((SoftwareSystem) abstractStructureElement)) {
					allSoftwareSystem.add((SoftwareSystem) abstractStructureElement);
				}
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSoftwareSystem;
	}
	
	private List<SoftwareSystem> getAllSoftwareSystemFrom(Layer layerToSearch) {
		List<SoftwareSystem> allSoftwareSystem = new ArrayList<SoftwareSystem>();
		
		for (AbstractStructureElement abstractStructureElement : layerToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				if (validateFilter((SoftwareSystem) abstractStructureElement)) {
					allSoftwareSystem.add((SoftwareSystem) abstractStructureElement);
				}
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSoftwareSystem;
	}
	
	private List<SoftwareSystem> getAllSoftwareSystemFrom(Component componentToSearch) {
		List<SoftwareSystem> allSoftwareSystem = new ArrayList<SoftwareSystem>();
		
		for (AbstractStructureElement abstractStructureElement : componentToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				if (validateFilter((SoftwareSystem) abstractStructureElement)) {
					allSoftwareSystem.add((SoftwareSystem) abstractStructureElement);
				}
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSoftwareSystem;
	}
	
	private List<SoftwareSystem> getAllSoftwareSystemFrom(Subsystem subsystemToSearch) {
		List<SoftwareSystem> allSoftwareSystem = new ArrayList<SoftwareSystem>();
		
		for (AbstractStructureElement abstractStructureElement : subsystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				if (validateFilter((SoftwareSystem) abstractStructureElement)) {
					allSoftwareSystem.add((SoftwareSystem) abstractStructureElement);
				}
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSoftwareSystem;
	}
	
	private List<SoftwareSystem> getAllSoftwareSystemFrom(ArchitectureView architectureViewToSearch) {
		List<SoftwareSystem> allSoftwareSystem = new ArrayList<SoftwareSystem>();
		
		for (AbstractStructureElement abstractStructureElement : architectureViewToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				if (validateFilter((SoftwareSystem) abstractStructureElement)) {
					allSoftwareSystem.add((SoftwareSystem) abstractStructureElement);
				}
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSoftwareSystem;
	}
	
	private List<SoftwareSystem> getAllSoftwareSystemFrom(SoftwareSystem softwareSystemToSearch) {
		List<SoftwareSystem> allSoftwareSystem = new ArrayList<SoftwareSystem>();

		for (AbstractStructureElement abstractStructureElement : softwareSystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				if (validateFilter((SoftwareSystem) abstractStructureElement)) {
					allSoftwareSystem.add((SoftwareSystem) abstractStructureElement);
				}
				allSoftwareSystem.addAll(this.getAllSoftwareSystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSoftwareSystem;
	}
}
