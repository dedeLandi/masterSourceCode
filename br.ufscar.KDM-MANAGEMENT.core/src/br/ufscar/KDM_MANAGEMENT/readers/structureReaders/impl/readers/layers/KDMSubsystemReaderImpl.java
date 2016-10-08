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

public class KDMSubsystemReaderImpl implements KDMStructureGenericReader<Subsystem>{

	private boolean hasNoFilter = false;
	private boolean hasFilterName = false;

	private String filterName = "";

	public KDMSubsystemReaderImpl() {
		super();
	}

	public KDMSubsystemReaderImpl(String elementName) {
		this.hasNoFilter = false;
		this.hasFilterName  = true;
		this.filterName = elementName;
	}
	
	private boolean validateFilter(Subsystem elementToValidate) {
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
	public Map<String, List<Subsystem>> getAllFromSegment(Segment segmentToSearch) {
		Map<String, List<Subsystem>> layersPerModel = new HashMap<String, List<Subsystem>>();

		Map<String, List<KDMModel>> models = KDMModelReaderFactory.eINSTANCE.createKDMModelReader().getAllStructureModelFromSegment(segmentToSearch); 
				
		for (String nameCodeModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameCodeModel)) {

				layersPerModel.put(kdmModel.getName(), this.getAllSubsystemFrom((StructureModel) kdmModel));
				
			}
		}

		return layersPerModel;
	}

	@Override
	public Map<String, List<Subsystem>> getAllFromModel(StructureModel structureModelToSearch) {
		Map<String, List<Subsystem>> allFromModel = new HashMap<String, List<Subsystem>>();
		
		allFromModel.put(structureModelToSearch.getName(), this.getAllSubsystemFrom(structureModelToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Subsystem>> getAllFromSoftwareSystem(SoftwareSystem softwareSystemToSearch) {
		Map<String, List<Subsystem>> allFromModel = new HashMap<String, List<Subsystem>>();
		
		allFromModel.put(softwareSystemToSearch.getName(), this.getAllSubsystemFrom(softwareSystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Subsystem>> getAllFromSubsytem(Subsystem subsystemToSearch) {
		Map<String, List<Subsystem>> allFromModel = new HashMap<String, List<Subsystem>>();
		
		allFromModel.put(subsystemToSearch.getName(), this.getAllSubsystemFrom(subsystemToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Subsystem>> getAllFromArchitectureView(ArchitectureView architectureViewToSearch) {
		Map<String, List<Subsystem>> allFromModel = new HashMap<String, List<Subsystem>>();
		
		allFromModel.put(architectureViewToSearch.getName(), this.getAllSubsystemFrom(architectureViewToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Subsystem>> getAllFromLayer(Layer layerToSearch) {
		Map<String, List<Subsystem>> allFromModel = new HashMap<String, List<Subsystem>>();
		
		allFromModel.put(layerToSearch.getName(), this.getAllSubsystemFrom(layerToSearch));
		
		return allFromModel;
	}

	@Override
	public Map<String, List<Subsystem>> getAllFromComponents(Component componentToSearch) {
		Map<String, List<Subsystem>> allFromModel = new HashMap<String, List<Subsystem>>();
		
		allFromModel.put(componentToSearch.getName(), this.getAllSubsystemFrom(componentToSearch));
		
		return allFromModel;
	}	

	private List<Subsystem> getAllSubsystemFrom(StructureModel structureModelToSearch) {
		List<Subsystem> allSubsystem = new ArrayList<Subsystem>();
		
		for (AbstractStructureElement abstractStructureElement : structureModelToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				if (validateFilter((Subsystem) abstractStructureElement)) {
					allSubsystem.add((Subsystem) abstractStructureElement);
				}
				allSubsystem.addAll(this.getAllSubsystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSubsystem;
	}
	
	private List<Subsystem> getAllSubsystemFrom(Layer layerToSearch) {
		List<Subsystem> allSubsystem = new ArrayList<Subsystem>();
		
		for (AbstractStructureElement abstractStructureElement : layerToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				if (validateFilter((Subsystem) abstractStructureElement)) {
					allSubsystem.add((Subsystem) abstractStructureElement);
				}
				allSubsystem.addAll(this.getAllSubsystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSubsystem;
	}
	
	private List<Subsystem> getAllSubsystemFrom(Component componentToSearch) {
		List<Subsystem> allSubsystem = new ArrayList<Subsystem>();
		
		for (AbstractStructureElement abstractStructureElement : componentToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				if (validateFilter((Subsystem) abstractStructureElement)) {
					allSubsystem.add((Subsystem) abstractStructureElement);
				}
				allSubsystem.addAll(this.getAllSubsystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSubsystem;
	}
	
	private List<Subsystem> getAllSubsystemFrom(Subsystem subsystemToSearch) {
		List<Subsystem> allSubsystem = new ArrayList<Subsystem>();
		
		for (AbstractStructureElement abstractStructureElement : subsystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				if (validateFilter((Subsystem) abstractStructureElement)) {
					allSubsystem.add((Subsystem) abstractStructureElement);
				}
				allSubsystem.addAll(this.getAllSubsystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSubsystem;
	}
	
	private List<Subsystem> getAllSubsystemFrom(ArchitectureView architectureViewToSearch) {
		List<Subsystem> allSubsystem = new ArrayList<Subsystem>();
		
		for (AbstractStructureElement abstractStructureElement : architectureViewToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				if (validateFilter((Subsystem) abstractStructureElement)) {
					allSubsystem.add((Subsystem) abstractStructureElement);
				}
				allSubsystem.addAll(this.getAllSubsystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSubsystem;
	}
	
	private List<Subsystem> getAllSubsystemFrom(SoftwareSystem softwareSystemToSearch) {
		List<Subsystem> allSubsystem = new ArrayList<Subsystem>();

		for (AbstractStructureElement abstractStructureElement : softwareSystemToSearch.getStructureElement()) {
			
			if(abstractStructureElement instanceof Layer){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Layer) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Component){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((Component) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof Subsystem){
				
				if (validateFilter((Subsystem) abstractStructureElement)) {
					allSubsystem.add((Subsystem) abstractStructureElement);
				}
				allSubsystem.addAll(this.getAllSubsystemFrom((Subsystem) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof ArchitectureView){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((ArchitectureView) abstractStructureElement));
				
			}else if(abstractStructureElement instanceof SoftwareSystem){
				
				allSubsystem.addAll(this.getAllSubsystemFrom((SoftwareSystem) abstractStructureElement));
				
			}
		}
		
		return allSubsystem;
	}
}
