package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.Calls;
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
import org.eclipse.gmt.modisco.omg.kdm.core.KDMRelationship;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsCallsImpl implements RecoverRelations {

	@Override
	public List<KDMRelationship> getRelationOf(ClassUnit classToAvaliate) {

		List<KDMRelationship> callsRecovered = new ArrayList<KDMRelationship>();
		
		for (CodeItem codeItem : classToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				callsRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				callsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return callsRecovered;
	}

	@Override
	public List<KDMRelationship> getRelationOf(Package packageToAvaliate) {

		List<KDMRelationship> callsRecovered = new ArrayList<KDMRelationship>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				callsRecovered.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				callsRecovered.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){
				callsRecovered.addAll(this.getRelationOf((Package) abstractCodeElement));
			}
			
		}
		
		return callsRecovered;
	}

	@Override
	public List<KDMRelationship> getRelationOf(BlockUnit blockToAvaliate) {
		
		List<KDMRelationship> callsRecovered = new ArrayList<KDMRelationship>();

		for (AbstractCodeElement abstractCodeElement : blockToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				callsRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				callsRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				callsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return callsRecovered;
	}

	@Override
	public List<KDMRelationship> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<KDMRelationship> callsRecovered = new ArrayList<KDMRelationship>();

		for (AbstractCodeRelationship abstractCodeRelationship : actionElementToAvaliate.getCodeRelation()) {
			
			if(abstractCodeRelationship instanceof Calls){
				callsRecovered.add((Calls) abstractCodeRelationship);
			}
			
		}
		
		for (AbstractCodeElement abstractCodeElement : actionElementToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				callsRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				callsRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				callsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}
		}
		
		return callsRecovered;
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(Signature signatureToAvaliate) {
		return new ArrayList<KDMRelationship>();
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(ParameterUnit parameterToAvaliate) {
		return new ArrayList<KDMRelationship>();
	}

	@Override
	@Deprecated
	public List<KDMRelationship> getRelationOf(StorableUnit storableToAvaliate) {
		return new ArrayList<KDMRelationship>();
	}

	@Override
	public List<KDMRelationship> getRelationOf(MethodUnit methodToAvaliate) {

		List<KDMRelationship> callsRecovered = new ArrayList<KDMRelationship>();
		
		for (AbstractCodeElement abstractCodeElement : methodToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof Signature){
				callsRecovered.addAll(this.getRelationOf((Signature) abstractCodeElement));
			}else if(abstractCodeElement instanceof BlockUnit){
				callsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));
			}
			
		}
		
		return callsRecovered;
	}

	@Override
	public List<KDMRelationship> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<KDMRelationship> callsRecovered = new ArrayList<KDMRelationship>();
		
		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof MethodUnit){
				callsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return callsRecovered;
	}

	@Override
	public List<KDMRelationship> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<KDMRelationship> callsRecovered = new ArrayList<KDMRelationship>();
		
		for (CodeItem codeItem : enumeratedTypeToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				callsRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				callsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return callsRecovered;
	}




}
