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
import org.eclipse.gmt.modisco.omg.kdm.core.KDMRelationship;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsReadsImpl implements RecoverRelations {

	@Override
	public List<KDMRelationship> getRelationOf(ClassUnit classToAvaliate) {

		List<KDMRelationship> readsRecovered = new ArrayList<KDMRelationship>();
		
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
	public List<KDMRelationship> getRelationOf(Package packageToAvaliate) {

		List<KDMRelationship> readsRecovered = new ArrayList<KDMRelationship>();
		
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
	public List<KDMRelationship> getRelationOf(BlockUnit blockToAvaliate) {
		
		List<KDMRelationship> readsRecovered = new ArrayList<KDMRelationship>();

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
	public List<KDMRelationship> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<KDMRelationship> readsRecovered = new ArrayList<KDMRelationship>();

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

		List<KDMRelationship> readsRecovered = new ArrayList<KDMRelationship>();
		
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
	public List<KDMRelationship> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<KDMRelationship> readsRecovered = new ArrayList<KDMRelationship>();
		
		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof MethodUnit){
				readsRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return readsRecovered;
	}

	@Override
	public List<KDMRelationship> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<KDMRelationship> readsRecovered = new ArrayList<KDMRelationship>();
		
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
