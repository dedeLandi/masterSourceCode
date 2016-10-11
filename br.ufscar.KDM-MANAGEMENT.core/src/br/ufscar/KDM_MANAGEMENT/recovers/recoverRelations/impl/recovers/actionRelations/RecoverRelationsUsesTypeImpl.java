package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.UsesType;
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

public class RecoverRelationsUsesTypeImpl implements RecoverRelations<UsesType> {

	@Override
	public List<UsesType> getRelationOf(ClassUnit classToAvaliate) {

		List<UsesType> usesTypeRecovered = new ArrayList<UsesType>();
		
		for (CodeItem codeItem : classToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				usesTypeRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				usesTypeRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return usesTypeRecovered;
	}

	@Override
	public List<UsesType> getRelationOf(Package packageToAvaliate) {

		List<UsesType> usesTypeRecovered = new ArrayList<UsesType>();
		
		for (AbstractCodeElement abstractCodeElement : packageToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof ClassUnit){
				usesTypeRecovered.addAll(this.getRelationOf((ClassUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof InterfaceUnit){
				usesTypeRecovered.addAll(this.getRelationOf((InterfaceUnit) abstractCodeElement));
			}else if(abstractCodeElement instanceof Package){
				usesTypeRecovered.addAll(this.getRelationOf((Package) abstractCodeElement));
			}
			
		}
		
		return usesTypeRecovered;
	}

	@Override
	public List<UsesType> getRelationOf(BlockUnit blockToAvaliate) {
		
		List<UsesType> usesTypeRecovered = new ArrayList<UsesType>();

		for (AbstractCodeElement abstractCodeElement : blockToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				usesTypeRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				usesTypeRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				usesTypeRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}

		}

		return usesTypeRecovered;
	}

	@Override
	public List<UsesType> getRelationOf(ActionElement actionElementToAvaliate) {
		
		List<UsesType> usesTypeRecovered = new ArrayList<UsesType>();

		for (AbstractCodeRelationship abstractCodeRelationship : actionElementToAvaliate.getCodeRelation()) {
			
			if(abstractCodeRelationship instanceof UsesType){
				usesTypeRecovered.add((UsesType) abstractCodeRelationship);
			}
			
		}
		
		for (AbstractCodeElement abstractCodeElement : actionElementToAvaliate.getCodeElement()) {

			if(abstractCodeElement instanceof StorableUnit){

				usesTypeRecovered.addAll(this.getRelationOf((StorableUnit) abstractCodeElement));
				
			}else if(abstractCodeElement instanceof ActionElement){

				usesTypeRecovered.addAll(this.getRelationOf((ActionElement) abstractCodeElement));

			}else if(abstractCodeElement instanceof BlockUnit){

				usesTypeRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));

			}
		}
		
		return usesTypeRecovered;
	}

	@Override
	@Deprecated
	public List<UsesType> getRelationOf(Signature signatureToAvaliate) {
		return new ArrayList<UsesType>();
	}

	@Override
	@Deprecated
	public List<UsesType> getRelationOf(ParameterUnit parameterToAvaliate) {
		return new ArrayList<UsesType>();
	}

	@Override
	@Deprecated
	public List<UsesType> getRelationOf(StorableUnit storableToAvaliate) {
		return new ArrayList<UsesType>();
	}

	@Override
	public List<UsesType> getRelationOf(MethodUnit methodToAvaliate) {

		List<UsesType> usesTypeRecovered = new ArrayList<UsesType>();
		
		for (AbstractCodeElement abstractCodeElement : methodToAvaliate.getCodeElement()) {
			
			if(abstractCodeElement instanceof Signature){
				usesTypeRecovered.addAll(this.getRelationOf((Signature) abstractCodeElement));
			}else if(abstractCodeElement instanceof BlockUnit){
				usesTypeRecovered.addAll(this.getRelationOf((BlockUnit) abstractCodeElement));
			}
			
		}
		
		return usesTypeRecovered;
	}

	@Override
	public List<UsesType> getRelationOf(InterfaceUnit interfaceToAvaliate) {

		List<UsesType> usesTypeRecovered = new ArrayList<UsesType>();
		
		for (CodeItem codeItem : interfaceToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof MethodUnit){
				usesTypeRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return usesTypeRecovered;
	}

	@Override
	public List<UsesType> getRelationOf(EnumeratedType enumeratedTypeToAvaliate) {
		List<UsesType> usesTypeRecovered = new ArrayList<UsesType>();
		
		for (CodeItem codeItem : enumeratedTypeToAvaliate.getCodeElement()) {
			
			if(codeItem instanceof StorableUnit){
				usesTypeRecovered.addAll(this.getRelationOf((StorableUnit) codeItem));
			}else if(codeItem instanceof MethodUnit){
				usesTypeRecovered.addAll(this.getRelationOf((MethodUnit) codeItem));
			}
			
		}
		
		return usesTypeRecovered;
	}




}
