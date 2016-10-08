package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces;

import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMRelationship;

public interface RecoverRelations {

	public List<KDMRelationship> getRelationOf(ClassUnit classToAvaliate);
	
	public List<KDMRelationship> getRelationOf(Package packageToAvaliate);

	public List<KDMRelationship> getRelationOf(BlockUnit blockToAvaliate);
	
	public List<KDMRelationship> getRelationOf(ActionElement actionElementToAvaliate);

	public List<KDMRelationship> getRelationOf(Signature signatureToAvaliate);
	
	public List<KDMRelationship> getRelationOf(ParameterUnit parameterToAvaliate);
	
	public List<KDMRelationship> getRelationOf(StorableUnit storableToAvaliate);
	
	public List<KDMRelationship> getRelationOf(MethodUnit methodToAvaliate);
	
	public List<KDMRelationship> getRelationOf(InterfaceUnit interfaceToAvaliate);

	public List<KDMRelationship> getRelationOf(EnumeratedType enumeratedTypeToAvaliate);
	
}
