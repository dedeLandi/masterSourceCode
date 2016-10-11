package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.Extends;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsExtendsImpl implements RecoverRelations<Extends>{

	@Override
	public List<Extends> getRelationOf(ClassUnit classToAvaliate) {
		List<Extends> extendsRecovered = new ArrayList<Extends>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : classToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Extends){
				extendsRecovered.add((Extends) abstractCodeRelationship);
			}
		}
		
		return extendsRecovered;
	}

	@Override
	public List<Extends> getRelationOf(Package packageToAvaliate) {
		
		List<Extends> extendsRecovered = new ArrayList<Extends>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			if(abstractCodeElement instanceof Extends){
				extendsRecovered.add((Extends) abstractCodeElement);
			}else if(abstractCodeElement instanceof InterfaceUnit){
				extendsRecovered.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof ClassUnit){
				extendsRecovered.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}
		}
		
		return extendsRecovered;
	}

	@Override
	@Deprecated
	public List<Extends> getRelationOf(BlockUnit classToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Extends> getRelationOf(StorableUnit packageToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Extends> getRelationOf(MethodUnit classToAvaliate) {
		return null;
	}

	@Override
	public List<Extends> getRelationOf(InterfaceUnit interfaceToAvaliate) {
		
		List<Extends> extendsRecovered = new ArrayList<Extends>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : interfaceToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Extends){
				extendsRecovered.add((Extends) abstractCodeRelationship);
			}
		}
		
		return extendsRecovered;
	}

	@Override
	@Deprecated
	public List<Extends> getRelationOf(ActionElement actionElementToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Extends> getRelationOf(Signature signatureToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Extends> getRelationOf(ParameterUnit parameterToAvaliate) {
		return null;
	}

	@Override
	public List<Extends> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<Extends> extendsRecovered = new ArrayList<Extends>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : enumeratedTypeToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Extends){
				extendsRecovered.add((Extends) abstractCodeRelationship);
			}
		}
		
		return extendsRecovered;
	}

}
