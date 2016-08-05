package br.ufscar.KDM_MANAGEMENT.models;

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

public class KDMModelReader {

	public static final String BUILD_MODEL = "BuildModel";
	public static final String CODE_MODEL = "CodeModel";
	public static final String CONCEPTUAL_MODEL = "ConceptualModel";
	public static final String DATA_MODEL = "DataModel";
	public static final String EVENT_MODEL = "EventModel";
	public static final String INVENTORY_MODEL = "InventoryModel";
	public static final String PLATFORM_MODEL = "PlatformModel";
	public static final String STRUCTURE_MODEL = "StructureModel";
	public static final String UI_MODEL = "UIModel";

	private Segment segmentMain;
	private static Map<String, Map<String, List<KDMModel>>> models = null; 

	public KDMModelReader(Segment segmentMain) {
		this.segmentMain = segmentMain;
	}

	public Map<String, Map<String, List<KDMModel>>> getAllModels() {

		models = new HashMap<String, Map<String, List<KDMModel>>>();
		
		EList<KDMModel> kdmModelASIS = segmentMain.getModel();

		models.put(BUILD_MODEL, null);
		models.put(CODE_MODEL, null);
		models.put(CONCEPTUAL_MODEL, null);
		models.put(DATA_MODEL, null);
		models.put(EVENT_MODEL, null);
		models.put(INVENTORY_MODEL, null);
		models.put(PLATFORM_MODEL, null);
		models.put(STRUCTURE_MODEL, null);
		models.put(UI_MODEL, null);

		for (KDMModel kdmModel : kdmModelASIS) {

			if(kdmModel instanceof BuildModel){

				includeModelInMap(kdmModel, BUILD_MODEL);

			}else if(kdmModel instanceof CodeModel){

				includeModelInMap(kdmModel, CODE_MODEL);

			}else if(kdmModel instanceof ConceptualModel){

				includeModelInMap(kdmModel, CONCEPTUAL_MODEL);

			}else if(kdmModel instanceof DataModel){

				includeModelInMap(kdmModel, DATA_MODEL);

			}else if(kdmModel instanceof EventModel){

				includeModelInMap(kdmModel, EVENT_MODEL);

			}else if(kdmModel instanceof InventoryModel){

				includeModelInMap(kdmModel, INVENTORY_MODEL);

			}else if(kdmModel instanceof PlatformModel){

				includeModelInMap(kdmModel, PLATFORM_MODEL);

			}else if (kdmModel instanceof StructureModel) {

				includeModelInMap(kdmModel, STRUCTURE_MODEL);

			}else if(kdmModel instanceof UIModel){

				includeModelInMap(kdmModel, UI_MODEL);
				
			}
		}

		return models;
	}

	@SuppressWarnings("serial")
	private void includeModelInMap(KDMModel kdmModel, String modelType) {
		if(models.get(modelType) == null){

			models.put(modelType, new HashMap<String, List<KDMModel>>());
			models.get(modelType).put(kdmModel.getName(), new ArrayList<KDMModel>(){{ add(kdmModel);}});

		}else{
			if(models.get(modelType).get(kdmModel.getName()) == null){

				models.get(modelType).put(kdmModel.getName(), new ArrayList<KDMModel>(){{ add(kdmModel);}});

			}else {

				models.get(modelType).get(kdmModel.getName()).add(kdmModel);
			}
		}
	}
	
	public Map<String, List<KDMModel>> getAllStructureModel(){
		if(models == null){
			getAllModels();
		}
		return models.get(STRUCTURE_MODEL);
	}
	
	public Map<String, List<KDMModel>> getAllCodeModel(){
		if(models == null){
			getAllModels();
		}
		return models.get(CODE_MODEL);
	}
	
	public List<KDMModel> getStructureModel(String name){
		if(models == null){
			getAllModels();
		}
		return models.get(STRUCTURE_MODEL).get(name);
	}
	
	public List<KDMModel> getCodeModel(String name){
		if(models == null){
			getAllModels();
		}
		return models.get(CODE_MODEL).get(name);
	}
	
}
