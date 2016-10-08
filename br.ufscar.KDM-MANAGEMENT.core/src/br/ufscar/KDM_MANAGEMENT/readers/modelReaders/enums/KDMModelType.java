package br.ufscar.KDM_MANAGEMENT.readers.modelReaders.enums;

import org.eclipse.gmt.modisco.omg.kdm.build.BuildModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.conceptual.ConceptualModel;
import org.eclipse.gmt.modisco.omg.kdm.data.DataModel;
import org.eclipse.gmt.modisco.omg.kdm.event.EventModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.platform.PlatformModel;
import org.eclipse.gmt.modisco.omg.kdm.source.InventoryModel;
import org.eclipse.gmt.modisco.omg.kdm.structure.StructureModel;
import org.eclipse.gmt.modisco.omg.kdm.ui.UIModel;

import br.ufscar.KDM_MANAGEMENT.readers.modelReaders.interfaces.IKDMModelType;

public enum KDMModelType implements IKDMModelType<KDMModel>{

	BUILD_MODEL ("BuildModel"){
		public BuildModel getModelByType(KDMModel kdmModel) {
			return (BuildModel) kdmModel;
		}
	},
	
	CODE_MODEL ("CodeModel"){
		public CodeModel getModelByType(KDMModel kdmModel) {
			return (CodeModel) kdmModel;
		}
	},
	
	CONCEPTUAL_MODEL ("ConceptualModel"){
		public ConceptualModel getModelByType(KDMModel kdmModel) {
			return (ConceptualModel) kdmModel;
		}
	},
	
	DATA_MODEL ("DataModel"){
		public DataModel getModelByType(KDMModel kdmModel) {
			return (DataModel) kdmModel;
		}
	},
	
	EVENT_MODEL ("EventModel"){
		public EventModel getModelByType(KDMModel kdmModel) {
			return (EventModel) kdmModel;
		}
	},
	
	INVENTORY_MODEL ("InventoryModel"){
		public InventoryModel getModelByType(KDMModel kdmModel) {
			return (InventoryModel) kdmModel;
		}
	},
	
	PLATFORM_MODEL ("PlatformModel"){
		public PlatformModel getModelByType(KDMModel kdmModel) {
			return (PlatformModel) kdmModel;
		}
	},
	
	STRUCTURE_MODEL ("StructureModel"){
		public StructureModel getModelByType(KDMModel kdmModel) {
			return (StructureModel) kdmModel;
		}
	},
	
	UI_MODEL ("UIModel"){
		public UIModel getModelByType(KDMModel kdmModel) {
			return (UIModel) kdmModel;
		}
	};
	
	private String typeModel;
	
	private KDMModelType(String typeModel) {
		this.typeModel = typeModel;
	}
	
	public String getTypeModel() {
		return typeModel;
	}
	
}
