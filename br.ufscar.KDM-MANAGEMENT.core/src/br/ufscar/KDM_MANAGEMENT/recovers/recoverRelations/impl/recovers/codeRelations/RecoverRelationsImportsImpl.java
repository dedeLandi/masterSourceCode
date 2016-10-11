package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.Imports;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsImportsImpl implements RecoverRelations<Imports>{

	@Override
	public List<Imports> getRelationOf(ClassUnit classToAvaliate) {
		List<Imports> importsRecovered = new ArrayList<Imports>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : classToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Imports){
				importsRecovered.add((Imports) abstractCodeRelationship);
			}
		}
		
		return importsRecovered;
	}

	@Override
	public List<Imports> getRelationOf(Package packageToAvaliate) {
		
		List<Imports> importsRecovered = new ArrayList<Imports>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			if(abstractCodeElement instanceof Imports){
				importsRecovered.add((Imports) abstractCodeElement);
			}else if(abstractCodeElement instanceof ClassUnit){
				importsRecovered.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				importsRecovered.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){
				importsRecovered.addAll(this.getRelationOf((Package) abstractCodeElement));
			}
		}
		
		return importsRecovered;
	}

	@Override
	@Deprecated
	public List<Imports> getRelationOf(BlockUnit classToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Imports> getRelationOf(StorableUnit packageToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Imports> getRelationOf(MethodUnit classToAvaliate) {
		return null;
	}

	@Override
	public List<Imports> getRelationOf(InterfaceUnit interfaceToAvaliate) {
		List<Imports> importsRecovered = new ArrayList<Imports>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : interfaceToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Imports){
				importsRecovered.add((Imports) abstractCodeRelationship);
			}
		}
		
		return importsRecovered;
	}

	@Override
	@Deprecated
	public List<Imports> getRelationOf(ActionElement actionElementToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Imports> getRelationOf(Signature signatureToAvaliate) {
		return null;
	}

	@Override
	@Deprecated
	public List<Imports> getRelationOf(ParameterUnit parameterToAvaliate) {
		return null;
	}

	@Override
	public List<Imports> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<Imports> importsRecovered = new ArrayList<Imports>();
		
		for (AbstractCodeRelationship abstractCodeRelationship : enumeratedTypeToAvaliate.getCodeRelation()) {
			if(abstractCodeRelationship instanceof Imports){
				importsRecovered.add((Imports) abstractCodeRelationship);
			}
		}
		
		return importsRecovered;
	}

}
