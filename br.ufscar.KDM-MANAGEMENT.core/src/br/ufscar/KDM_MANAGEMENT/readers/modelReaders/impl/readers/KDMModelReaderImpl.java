package br.ufscar.KDM_MANAGEMENT.readers.modelReaders.impl.readers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.omg.kdm.build.BuildModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.conceptual.ConceptualModel;
import org.eclipse.gmt.modisco.omg.kdm.data.DataModel;
import org.eclipse.gmt.modisco.omg.kdm.event.EventModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformModel;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructureModel;
import org.eclipse.gmt.modisco.omg.kdm.ui.UIModel;

import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.enums.KDMModelType;
import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.interfaces.KDMModelGenericReader;

public class KDMModelReaderImpl implements KDMModelGenericReader<KDMModel> {

	private Map<String, Map<String, List<KDMModel>>> models = null; 

	public Map<String, Map<String, List<KDMModel>>> getAllFromSegment(Segment segmentToSearch) {

		models = new HashMap<String, Map<String, List<KDMModel>>>();
		
		models.put(KDMModelType.BUILD_MODEL.getTypeModel(), null);
		models.put(KDMModelType.CODE_MODEL.getTypeModel(), null);
		models.put(KDMModelType.CONCEPTUAL_MODEL.getTypeModel(), null);
		models.put(KDMModelType.DATA_MODEL.getTypeModel(), null);
		models.put(KDMModelType.EVENT_MODEL.getTypeModel(), null);
		models.put(KDMModelType.INVENTORY_MODEL.getTypeModel(), null);
		models.put(KDMModelType.PLATFORM_MODEL.getTypeModel(), null);
		models.put(KDMModelType.STRUCTURE_MODEL.getTypeModel(), null);
		models.put(KDMModelType.UI_MODEL.getTypeModel(), null);

		for (KDMModel kdmModel : segmentToSearch.getModel()) {

			if(kdmModel instanceof BuildModel){

				includeModelInMap(kdmModel, KDMModelType.BUILD_MODEL);

			}else if(kdmModel instanceof CodeModel){

				includeModelInMap(kdmModel, KDMModelType.CODE_MODEL);

			}else if(kdmModel instanceof ConceptualModel){

				includeModelInMap(kdmModel, KDMModelType.CONCEPTUAL_MODEL);

			}else if(kdmModel instanceof DataModel){

				includeModelInMap(kdmModel, KDMModelType.DATA_MODEL);

			}else if(kdmModel instanceof EventModel){

				includeModelInMap(kdmModel, KDMModelType.EVENT_MODEL);

			}else if(kdmModel instanceof InventoryModel){

				includeModelInMap(kdmModel, KDMModelType.INVENTORY_MODEL);

			}else if(kdmModel instanceof PlatformModel){

				includeModelInMap(kdmModel, KDMModelType.PLATFORM_MODEL);

			}else if (kdmModel instanceof StructureModel) {

				includeModelInMap(kdmModel, KDMModelType.STRUCTURE_MODEL);

			}else if(kdmModel instanceof UIModel){

				includeModelInMap(kdmModel, KDMModelType.UI_MODEL);
				
			}
		}

		return models;
	}

	private void includeModelInMap(KDMModel kdmModel, KDMModelType modelType) {
		if(models.get(modelType.getTypeModel()) == null){

			models.put(modelType.getTypeModel(), new HashMap<String, List<KDMModel>>());
			models.get(modelType.getTypeModel()).put(kdmModel.getName(), new ArrayList<KDMModel>(){
				private static final long serialVersionUID = 1L;
				{ add(modelType.getModelByType(kdmModel));}
			});

		}else{
			if(models.get(modelType.getTypeModel()).get(kdmModel.getName()) == null){

				models.get(modelType.getTypeModel()).put(kdmModel.getName(), new ArrayList<KDMModel>(){
					private static final long serialVersionUID = 1L;
					{ add(modelType.getModelByType(kdmModel));}
				});

			}else {

				models.get(modelType.getTypeModel()).get(kdmModel.getName()).add(modelType.getModelByType(kdmModel));
			}
		}
	}
	
	public Map<String, List<KDMModel>> getAllStructureModelFromSegment(Segment segmentToSearch){
		this.models = null;
		this.getAllFromSegment(segmentToSearch);

		return models.get(KDMModelType.STRUCTURE_MODEL.getTypeModel());
	}
	
	public Map<String, List<KDMModel>> getAllCodeModelFromSegment(Segment segmentToSearch){
		this.models = null;
		this.getAllFromSegment(segmentToSearch);
		
		return models.get(KDMModelType.CODE_MODEL.getTypeModel());
	}
	
	public List<KDMModel> getStructureModelFromSegmentByName(Segment segmentToSearch, String name){
		this.models = null;
		this.getAllFromSegment(segmentToSearch);
		
		return models.get(KDMModelType.STRUCTURE_MODEL.getTypeModel()).get(name);
	}
	
	public List<KDMModel> getCodeModelFromSegmentByName(Segment segmentToSearch, String name){
		this.models = null;
		this.getAllFromSegment(segmentToSearch);
		
		return models.get(KDMModelType.CODE_MODEL.getTypeModel()).get(name);
	}
	
	public List<KDMModel> getModelFromSegmentByName(Segment segmentToSearch, String name) {
		List<KDMModel> modelsNamed = new ArrayList<KDMModel>();
		
		EList<KDMModel> kdmModelASIS = segmentToSearch.getModel();
		
		for (KDMModel kdmModel : kdmModelASIS) {
			
			if(name.equalsIgnoreCase(kdmModel.getName())){
				modelsNamed.add(kdmModel);
			}
			
		}
		return modelsNamed;
	}
	
	
}