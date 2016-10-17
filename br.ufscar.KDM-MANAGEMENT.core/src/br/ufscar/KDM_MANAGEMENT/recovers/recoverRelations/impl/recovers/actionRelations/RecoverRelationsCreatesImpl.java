package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.Creates;
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

public class RecoverRelationsCreatesImpl implements RecoverRelations<Creates> {

	@Override
	public List<Creates> getRelationOf(ClassUnit classToAvaliate) {

		List<Creates> createsRecovered = new ArrayList<Creates>();
		
		for (CodeItem codeItem : classToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				createsRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				createsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return createsRecovered;
	}

	@Override
	public List<Creates> getRelationOf(Package packageToAvaliate) {

		List<Creates> createsRecovered = new ArrayList<Creates>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				createsRecovered.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				createsRecovered.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){
				createsRecovered.addAll(this.getRelationOf((Package) abstractCodeElement));
			}
			
		}
		
		return createsRecovered;
	}

	@Override
	public List<Creates> getRelationOf(BlockUnit blockToAvaliate) {
		
		List<Creates> createsRecovered = new ArrayList<Creates>();

		for (AbstractCodeElement abstractCodeElement : blockToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				createsRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				createsRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				createsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return createsRecovered;
	}

	@Override
	public List<Creates> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<Creates> createsRecovered = new ArrayList<Creates>();

		for (AbstractCodeRelationship abstractCodeRelationship : actionElementToAvaliate.getCodeRelation()) {
			
			if(abstractCodeRelationship instanceof Creates){
				createsRecovered.add((Creates) abstractCodeRelationship);
			}
			
		}
		
		for (AbstractCodeElement abstractCodeElement : actionElementToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				createsRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				createsRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				createsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}
		}
		
		return createsRecovered;
	}

	@Override
	@Deprecated
	public List<Creates> getRelationOf(Signature signatureToAvaliate) {
		return new ArrayList<Creates>();
	}

	@Override
	@Deprecated
	public List<Creates> getRelationOf(ParameterUnit parameterToAvaliate) {
		return new ArrayList<Creates>();
	}

	@Override
	@Deprecated
	public List<Creates> getRelationOf(StorableUnit storableToAvaliate) {
		return new ArrayList<Creates>();
	}

	@Override
	public List<Creates> getRelationOf(MethodUnit methodToAvaliate) {

		List<Creates> createsRecovered = new ArrayList<Creates>();
		
		for (AbstractCodeElement abstractCodeElement : methodToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof Signature){
				createsRecovered.addAll(this.getRelationOf((Signature) abstractCodeElement));
			}else if(abstractCodeElement instanceof BlockUnit){
				createsRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));
			}
			
		}
		
		return createsRecovered;
	}

	@Override
	public List<Creates> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<Creates> createsRecovered = new ArrayList<Creates>();
		
		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof MethodUnit){
				createsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return createsRecovered;
	}

	@Override
	public List<Creates> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<Creates> createsRecovered = new ArrayList<Creates>();
		
		for (CodeItem codeItem : enumeratedTypeToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				createsRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				createsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return createsRecovered;
	}

	@Override
	public List<Creates> getRelationOf(Segment segmentToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Creates> getRelationOf(CodeModel codeModelToAvaliate) {
		// TODO Auto-generated method stub
		return null;
	}




}
