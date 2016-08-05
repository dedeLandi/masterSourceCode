package br.ufscar.KDM_MANAGEMENT.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.load.KDMFileReader;
import br.ufscar.KDM_MANAGEMENT.models.KDMModelReader;

public class TestesComKDM {

	public static void main(String[] args) {
		String KDMpathDrifts = "C:\\TestsPlug-in\\labSystemKDMComdesvios.xmi";
		
		Object KDMFile = new KDMFileReader(KDMpathDrifts, KDMFileReader.READ_KDM_TO_SEGMENT).getKdmRead();
		
		List<ArchitecturalDrift> drifts = getKDMDriftsRead(KDMFile);
		
		for (ArchitecturalDrift architecturalDrift : drifts) {
			System.out.println(architecturalDrift.toString());
		}

	}

	private static List<ArchitecturalDrift> getKDMDriftsRead(Object kDMFile) {
		
		List<ArchitecturalDrift> driftsRead = new ArrayList<>();
		Segment KDMTree = (Segment) kDMFile;
		Map<String, Map<String, List<KDMModel>>> allModels = new KDMModelReader(KDMTree).getAllModels();
		for (String key : allModels.keySet()) {
			Map<String, List<KDMModel>> map = allModels.get(key);
			if(map != null){
				String temp = "";
				for (String key2 : map.keySet()) {
					
					if(map.get(key2) == null){
						temp = temp.concat(key2 + "(null), ");
					}else{
						temp = temp.concat(key2 + "(" + map.get(key2).size() + "), ");
					}
				}
				System.out.println(key + " - " + temp);
			}else{
				System.out.println(key + " - null");
			}
		}
		
		return driftsRead;
	}

}
