package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces;

import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMRelationship;

public interface RecoverRelations {

	
	
	public List<KDMRelationship> getRelationOf(ClassUnit classToAvaliate);
	
	public List<KDMRelationship> getRelationOf(Package packageToAvaliate);

	public List<KDMRelationship> getRelationOf(BlockUnit classToAvaliate);
	
	public List<KDMRelationship> getRelationOf(StorableUnit packageToAvaliate);
	
	public List<KDMRelationship> getRelationOf(MethodUnit classToAvaliate);
	
	public List<KDMRelationship> getRelationOf(InterfaceUnit packageToAvaliate);
	
	
	
	
	
}
