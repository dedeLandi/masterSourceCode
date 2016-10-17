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
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsCallsImpl implements RecoverRelations<Calls> {

	@Override
	public List<Calls> getRelationOf(ClassUnit classToAvaliate) {

		List<Calls> callsRecovered = new ArrayList<Calls>();
		
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
	public List<Calls> getRelationOf(Package packageToAvaliate) {

		List<Calls> callsRecovered = new ArrayList<Calls>();
		
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
	public List<Calls> getRelationOf(BlockUnit blockToAvaliate) {
		
		List<Calls> callsRecovered = new ArrayList<Calls>();

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
	public List<Calls> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<Calls> callsRecovered = new ArrayList<Calls>();

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
	public List<Calls> getRelationOf(Signature signatureToAvaliate) {
		return new ArrayList<Calls>();
	}

	@Override
	@Deprecated
	public List<Calls> getRelationOf(ParameterUnit parameterToAvaliate) {
		return new ArrayList<Calls>();
	}

	@Override
	@Deprecated
	public List<Calls> getRelationOf(StorableUnit storableToAvaliate) {
		return new ArrayList<Calls>();
	}

	@Override
	public List<Calls> getRelationOf(MethodUnit methodToAvaliate) {

		List<Calls> callsRecovered = new ArrayList<Calls>();
		
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
	public List<Calls> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<Calls> callsRecovered = new ArrayList<Calls>();
		
		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof MethodUnit){
				callsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return callsRecovered;
	}

	@Override
	public List<Calls> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<Calls> callsRecovered = new ArrayList<Calls>();
		
		for (CodeItem codeItem : enumeratedTypeToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				callsRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				callsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return callsRecovered;
	}

	@Override
	public List<Calls> getRelationOf(Segment segmentToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Calls> getRelationOf(CodeModel codeModelToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}




}
