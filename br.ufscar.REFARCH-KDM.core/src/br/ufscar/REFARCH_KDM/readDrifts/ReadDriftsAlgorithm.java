package br.ufscar.REFARCH_KDM.readDrifts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

public enum ReadDriftsAlgorithm implements ReadDriftsAlgorithmStrategy{
	
	
	ALGORITHM_ARCH_KDM_FROM_SEGMENT(1, "ARCH-KDM Algorihm (using Segment)", new String[]{"*.xmi"}){
		@Override
		public List<ArchitecturalDrift> readDrifts(Object KDMFile) {
			List<ArchitecturalDrift> driftsRead = new ArrayList<>();
			Segment KDMTree = (Segment) KDMFile;
			
			
			
			return driftsRead;
		}
	},
	ALGORITHM_ARCH_KDM_FROM_XPATH(1, "ARCH-KDM Algorihm (using XPath) - not implemented", new String[]{"*.xmi"}){
		@Override
		public List<ArchitecturalDrift> readDrifts(Object KDMFile) {
			//TODO Implement the Algorithm
			return null;
		}
	};

	private int value;
	private String description;
	private String[] extensions;

	private ReadDriftsAlgorithm(int value, String description, String[] extensions) {
		this.value = value;
		this.description = description;
		this.extensions = extensions;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public String[] getExtensions() {
		return extensions;
	}


}
