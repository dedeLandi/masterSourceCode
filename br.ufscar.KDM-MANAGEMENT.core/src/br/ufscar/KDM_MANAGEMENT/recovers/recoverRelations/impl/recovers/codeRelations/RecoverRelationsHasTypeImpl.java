package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.HasType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.core.KDMRelationship;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsHasTypeImpl implements RecoverRelations {

	@Override
	public List<KDMRelationship> getRelationOf(ClassUnit classToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (CodeItem codeItem : classToAvaliate.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				hasTypeRelations.addAll(this.getRelationOf((StorableUnit) codeItem));

			}else if(codeItem instanceof MethodUnit){

				hasTypeRelations.addAll(this.getRelationOf((MethodUnit) codeItem));

			}

		}

		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(Package packageToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				hasTypeRelations.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				hasTypeRelations.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){

				hasTypeRelations.addAll(this.getRelationOf((Package) abstractCodeElement));

			}

		}

		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(BlockUnit blockToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (AbstractCodeElement abstractCodeElement : blockToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				hasTypeRelations.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				hasTypeRelations.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				hasTypeRelations.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (AbstractCodeElement abstractCodeElement : actionElementToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				hasTypeRelations.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				hasTypeRelations.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				hasTypeRelations.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(Signature signatureToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (ParameterUnit parameterUnit : signatureToAvaliate.getParameterUnit()) {
			hasTypeRelations.addAll(this.getRelationOf(parameterUnit));
		}


		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(ParameterUnit parameterToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (AbstractCodeRelationship abstractCodeRelationship : parameterToAvaliate.getCodeRelation()) {

			if(abstractCodeRelationship instanceof HasType){

				hasTypeRelations.add((HasType) abstractCodeRelationship);

			}

		}

		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(StorableUnit storableToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (AbstractCodeRelationship abstractCodeRelationship : storableToAvaliate.getCodeRelation()) {

			if(abstractCodeRelationship instanceof HasType){

				hasTypeRelations.add((HasType) abstractCodeRelationship);

			}

		}

		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(MethodUnit methodToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (AbstractCodeElement abstractCodeElement : methodToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof Signature){

				hasTypeRelations.addAll(this.getRelationOf((Signature) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				hasTypeRelations.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return hasTypeRelations;
	}

	@Override
	public List<KDMRelationship> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<KDMRelationship> hasTypeRelations = new ArrayList<KDMRelationship>();

		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				hasTypeRelations.addAll(this.getRelationOf((StorableUnit) codeItem));

			}else if(codeItem instanceof MethodUnit){

				hasTypeRelations.addAll(this.getRelationOf((MethodUnit) codeItem));

			}

		}

		return hasTypeRelations;
	}

}
