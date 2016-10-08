package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.Implements;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMRelationship;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsImplementsImpl implements RecoverRelations{

	@Override
	public List<KDMRelationship> getRelationOf(ClassUnit classToAvaliate) {
		List<KDMRelationship> implementsRecovered = new ArrayList<KDMRelationship>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : classToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Implements){
				implementsRecovered.add((Implements) abstractCodeRelationship);
			}
		}
		
		return implementsRecovered;
	}

	@Override
	public List<KDMRelationship> getRelationOf(Package packageToAvaliate) {
		
		List<KDMRelationship> implementsRecovered = new ArrayList<KDMRelationship>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			if(abstractCodeElement instanceof Implements){
				implementsRecovered.add((KDMRelationship) abstractCodeElement);
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
	public List<KDMRelationship> getRelationOf(BlockUnit classToAvaliate) {
		// não aplicavel
		return null;
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(StorableUnit packageToAvaliate) {
		// não aplicavel
		return null;
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(MethodUnit classToAvaliate) {
		// não aplicavel
		return null;
	}

	@Override
	public List<KDMRelationship> getRelationOf(InterfaceUnit interfaceToAvaliate) {
		
		List<KDMRelationship> implementsRecovered = new ArrayList<KDMRelationship>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : interfaceToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Implements){
				implementsRecovered.add((Implements) abstractCodeRelationship);
			}
		}
		
		return implementsRecovered;
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(ActionElement actionElementToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(Signature signatureToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(ParameterUnit parameterToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KDMRelationship> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<KDMRelationship> implementsRecovered = new ArrayList<KDMRelationship>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : enumeratedTypeToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Implements){
				implementsRecovered.add((Implements) abstractCodeRelationship);
			}
		}
		
		return implementsRecovered;
	}

}
