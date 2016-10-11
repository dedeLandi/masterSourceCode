package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.HasValue;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsHasValueImpl implements RecoverRelations<HasValue> {

	@Override
	public List<HasValue> getRelationOf(ClassUnit classToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (CodeItem codeItem : classToAvaliate.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				hasValueRelations.addAll(this.getRelationOf((StorableUnit) codeItem));

			}else if(codeItem instanceof MethodUnit){

				hasValueRelations.addAll(this.getRelationOf((MethodUnit) codeItem));

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(Package packageToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof ClassUnit){

				hasValueRelations.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				hasValueRelations.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){

				hasValueRelations.addAll(this.getRelationOf((Package) abstractCodeElement));

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(BlockUnit blockToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (AbstractCodeElement abstractCodeElement : blockToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				hasValueRelations.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				hasValueRelations.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				hasValueRelations.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (AbstractCodeElement abstractCodeElement : actionElementToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				hasValueRelations.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				hasValueRelations.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				hasValueRelations.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(Signature signatureToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (ParameterUnit parameterUnit : signatureToAvaliate.getParameterUnit()) {
			hasValueRelations.addAll(this.getRelationOf(parameterUnit));
		}


		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(ParameterUnit parameterToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (AbstractCodeRelationship abstractCodeRelationship : parameterToAvaliate.getCodeRelation()) {

			if(abstractCodeRelationship instanceof HasValue){

				hasValueRelations.add((HasValue) abstractCodeRelationship);

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(StorableUnit storableToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (AbstractCodeRelationship abstractCodeRelationship : storableToAvaliate.getCodeRelation()) {

			if(abstractCodeRelationship instanceof HasValue){

				hasValueRelations.add((HasValue) abstractCodeRelationship);

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(MethodUnit methodToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (AbstractCodeElement abstractCodeElement : methodToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof Signature){

				hasValueRelations.addAll(this.getRelationOf((Signature) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				hasValueRelations.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				hasValueRelations.addAll(this.getRelationOf((StorableUnit) codeItem));

			}else if(codeItem instanceof MethodUnit){

				hasValueRelations.addAll(this.getRelationOf((MethodUnit) codeItem));

			}

		}

		return hasValueRelations;
	}

	@Override
	public List<HasValue> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<HasValue> hasValueRelations = new ArrayList<HasValue>();

		for (CodeItem codeItem : enumeratedTypeToAvaliate.getCodeElement()) {

			if(codeItem instanceof StorableUnit){

				hasValueRelations.addAll(this.getRelationOf((StorableUnit) codeItem));

			}else if(codeItem instanceof MethodUnit){

				hasValueRelations.addAll(this.getRelationOf((MethodUnit) codeItem));

			}

		}

		return hasValueRelations;
	}
	
}
