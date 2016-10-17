package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.Implements;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsImplementsImpl implements RecoverRelations<Implements>{

	@Override
	public List<Implements> getRelationOf(ClassUnit classToAvaliate) {
		List<Implements> implementsRecovered = new ArrayList<Implements>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : classToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Implements){
				implementsRecovered.add((Implements) abstractCodeRelationship);
			}
		}
		
		return implementsRecovered;
	}

	@Override
	public List<Implements> getRelationOf(Package packageToAvaliate) {
		
		List<Implements> implementsRecovered = new ArrayList<Implements>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			if(abstractCodeElement instanceof Implements){
				implementsRecovered.add((Implements) abstractCodeElement);
			}else if(abstractCodeElement instanceof ClassUnit){
				implementsRecovered.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				implementsRecovered.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){
				implementsRecovered.addAll(this.getRelationOf((Package) abstractCodeElement));
			}
		}
		
		return implementsRecovered;
	}

	@Override
	@Deprecated
	public List<Implements> getRelationOf(BlockUnit classToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Implements> getRelationOf(StorableUnit packageToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Implements> getRelationOf(MethodUnit classToAvaliate) {
		return null;
	}

	@Override
	public List<Implements> getRelationOf(InterfaceUnit interfaceToAvaliate) {
		
		List<Implements> implementsRecovered = new ArrayList<Implements>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : interfaceToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Implements){
				implementsRecovered.add((Implements) abstractCodeRelationship);
			}
		}
		
		return implementsRecovered;
	}

	@Override
	@Deprecated
	public List<Implements> getRelationOf(ActionElement actionElementToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Implements> getRelationOf(Signature signatureToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Implements> getRelationOf(ParameterUnit parameterToAvaliate) {
		return null;
	}

	@Override
	public List<Implements> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<Implements> implementsRecovered = new ArrayList<Implements>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : enumeratedTypeToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Implements){
				implementsRecovered.add((Implements) abstractCodeRelationship);
			}
		}
		
		return implementsRecovered;
	}

	@Override
	public List<Implements> getRelationOf(Segment segmentToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Implements> getRelationOf(CodeModel codeModelToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}

}
