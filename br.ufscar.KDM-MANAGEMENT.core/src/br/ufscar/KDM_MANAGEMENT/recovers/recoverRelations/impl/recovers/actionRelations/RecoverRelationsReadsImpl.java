package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.Reads;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsReadsImpl implements RecoverRelations<Reads> {

	@Override
	public List<Reads> getRelationOf(ClassUnit classToAvaliate) {

		List<Reads> readsRecovered = new ArrayList<Reads>();
		
		for (CodeItem codeItem : classToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				readsRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				readsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return readsRecovered;
	}

	@Override
	public List<Reads> getRelationOf(Package packageToAvaliate) {

		List<Reads> readsRecovered = new ArrayList<Reads>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				readsRecovered.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				readsRecovered.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){
				readsRecovered.addAll(this.getRelationOf((Package) abstractCodeElement));
			}
			
		}
		
		return readsRecovered;
	}

	@Override
	public List<Reads> getRelationOf(BlockUnit blockToAvaliate) {
		
		List<Reads> readsRecovered = new ArrayList<Reads>();

		for (AbstractCodeElement abstractCodeElement : blockToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				readsRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				readsRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				readsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return readsRecovered;
	}

	@Override
	public List<Reads> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<Reads> readsRecovered = new ArrayList<Reads>();

		for (AbstractCodeRelationship abstractCodeRelationship : actionElementToAvaliate.getCodeRelation()) {
			
			if(abstractCodeRelationship instanceof Reads){
				readsRecovered.add((Reads) abstractCodeRelationship);
			}
			
		}
		
		for (AbstractCodeElement abstractCodeElement : actionElementToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				readsRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				readsRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				readsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}
		}
		
		return readsRecovered;
	}

	@Override
	@Deprecated
	public List<Reads> getRelationOf(Signature signatureToAvaliate) {
		return new ArrayList<Reads>();
	}

	@Override
	@Deprecated
	public List<Reads> getRelationOf(ParameterUnit parameterToAvaliate) {
		return new ArrayList<Reads>();
	}

	@Override
	@Deprecated
	public List<Reads> getRelationOf(StorableUnit storableToAvaliate) {
		return new ArrayList<Reads>();
	}

	@Override
	public List<Reads> getRelationOf(MethodUnit methodToAvaliate) {

		List<Reads> readsRecovered = new ArrayList<Reads>();
		
		for (AbstractCodeElement abstractCodeElement : methodToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof Signature){
				readsRecovered.addAll(this.getRelationOf((Signature) abstractCodeElement));
			}else if(abstractCodeElement instanceof BlockUnit){
				readsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));
			}
			
		}
		
		return readsRecovered;
	}

	@Override
	public List<Reads> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<Reads> readsRecovered = new ArrayList<Reads>();
		
		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof MethodUnit){
				readsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return readsRecovered;
	}

	@Override
	public List<Reads> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<Reads> readsRecovered = new ArrayList<Reads>();
		
		for (CodeItem codeItem : enumeratedTypeToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				readsRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				readsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return readsRecovered;
	}




}
