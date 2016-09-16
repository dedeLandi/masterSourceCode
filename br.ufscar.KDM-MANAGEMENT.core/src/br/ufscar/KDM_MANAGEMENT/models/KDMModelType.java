package br.ufscar.KDM_MANAGEMENT.models;

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

public enum KDMModelType {

	BUILD_MODEL ("BuildModel"){
		@SuppressWarnings("unused")
		public BuildModel getModelByType(KDMModel kdmModel) {
			return (BuildModel) kdmModel;
		}
	},
	
	CODE_MODEL ("CodeModel"){
		@SuppressWarnings("unused")
		public CodeModel getModelByType(KDMModel kdmModel) {
			return (CodeModel) kdmModel;
		}
	},
	
	CONCEPTUAL_MODEL ("ConceptualModel"){
		@SuppressWarnings("unused")
		public ConceptualModel getModelByType(KDMModel kdmModel) {
			return (ConceptualModel) kdmModel;
		}
	},
	
	DATA_MODEL ("DataModel"){
		@SuppressWarnings("unused")
		public DataModel getModelByType(KDMModel kdmModel) {
			return (DataModel) kdmModel;
		}
	},
	
	EVENT_MODEL ("EventModel"){
		@SuppressWarnings("unused")
		public EventModel getModelByType(KDMModel kdmModel) {
			return (EventModel) kdmModel;
		}
	},
	
	INVENTORY_MODEL ("InventoryModel"){
		@SuppressWarnings("unused")
		public InventoryModel getModelByType(KDMModel kdmModel) {
			return (InventoryModel) kdmModel;
		}
	},
	
	PLATFORM_MODEL ("PlatformModel"){
		@SuppressWarnings("unused")
		public PlatformModel getModelByType(KDMModel kdmModel) {
			return (PlatformModel) kdmModel;
		}
	},
	
	STRUCTURE_MODEL ("StructureModel"){
		@SuppressWarnings("unused")
		public StructureModel getModelByType(KDMModel kdmModel) {
			return (StructureModel) kdmModel;
		}
	},
	
	UI_MODEL ("UIModel"){
		@SuppressWarnings("unused")
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
