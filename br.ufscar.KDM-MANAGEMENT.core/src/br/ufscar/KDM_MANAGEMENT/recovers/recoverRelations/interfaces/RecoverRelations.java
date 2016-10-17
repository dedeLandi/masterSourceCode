package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces;

import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

public interface RecoverRelations<T> {

	public List<T> getRelationOf(Segment segmentToAvaliate);
	
	public List<T> getRelationOf(CodeModel codeModelToAvaliate);
	
	public List<T> getRelationOf(Package packageToAvaliate);

	public List<T> getRelationOf(ClassUnit classToAvaliate);
	
	public List<T> getRelationOf(InterfaceUnit interfaceToAvaliate);

	public List<T> getRelationOf(EnumeratedType enumeratedTypeToAvaliate);
	
	public List<T> getRelationOf(StorableUnit storableToAvaliate);

	public List<T> getRelationOf(MethodUnit methodToAvaliate);
	
	public List<T> getRelationOf(Signature signatureToAvaliate);

	public List<T> getRelationOf(ParameterUnit parameterToAvaliate);
	
	public List<T> getRelationOf(BlockUnit blockToAvaliate);
	
	public List<T> getRelationOf(ActionElement actionElementToAvaliate);
	
}
