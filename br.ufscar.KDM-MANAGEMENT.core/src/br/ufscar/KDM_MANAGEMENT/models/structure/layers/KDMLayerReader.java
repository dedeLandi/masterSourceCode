package br.ufscar.KDM_MANAGEMENT.models.structure.layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;
import org.eclipse.gmt.modisco.omg.kdm.structure.AbstractStructureElement;
import org.eclipse.gmt.modisco.omg.kdm.structure.Layer;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructureModel;

import br.ufscar.KDM_MANAGEMENT.exception.KDMModelTypeException;
import br.ufscar.KDM_MANAGEMENT.models.KDMModelReader;

public class KDMLayerReader {

	private Segment segmentMain = null;
	private StructureModel modelMain = null;

	public KDMLayerReader(Segment KDMTree) {
		this.segmentMain = KDMTree;
	}
	public KDMLayerReader(KDMModel kdmModel) throws KDMModelTypeException {
		if(kdmModel instanceof StructureModel){
			this.modelMain = (StructureModel) kdmModel;
		}else{
			throw new KDMModelTypeException();
		}
	}


	public Map<String, List<Layer>> getAllLayers() {
		if(this.segmentMain != null){
			return getAllLayersSegment();
		}else if(this.modelMain != null){
			return getAllLayersModel();
		}else{
			return null;
		}

	}

	private Map<String, List<Layer>> getAllLayersSegment() {

		Map<String, List<Layer>> layersPerModel = new HashMap<String, List<Layer>>();

		Map<String, List<KDMModel>> models = new KDMModelReader(this.segmentMain).getAllStructureModel(); 

		for (String nameStructureModel : models.keySet()) {

			for (KDMModel kdmModel : models.get(nameStructureModel)) {

				List<Layer> allLayers = new ArrayList<Layer>();

				StructureModel structureModel = (StructureModel) kdmModel;

				EList<AbstractStructureElement> elements = structureModel.getStructureElement();

				for (int i = 0; i < elements.size() ; i++) {

					this.getLayers(elements.get(i), allLayers);

				}

				layersPerModel.put(nameStructureModel, allLayers);
			}
		}

		return layersPerModel;

	}

	private void getLayers(AbstractStructureElement structureElement, List<Layer> allLayers) {

		if (structureElement instanceof Layer) {

			allLayers.add((Layer) structureElement);

		} 
		
		for (AbstractStructureElement abstractStructureElement : structureElement.getStructureElement()) {

			if (abstractStructureElement instanceof Layer) {

				allLayers.add((Layer) abstractStructureElement);

			} 

			getLayers(abstractStructureElement, allLayers);

		}

	}


	private Map<String, List<Layer>> getAllLayersModel() {

		Map<String, List<Layer>> layersPerModel = new HashMap<String, List<Layer>>();

		List<Layer> allLayers = new ArrayList<Layer>();

		EList<AbstractStructureElement> elements = this.modelMain.getStructureElement();

		for (int i = 0; i < elements.size() ; i++) {

			this.getLayers(elements.get(i), allLayers);

		}

		layersPerModel.put(this.modelMain.getName(), allLayers);

		return layersPerModel;

	}

}
