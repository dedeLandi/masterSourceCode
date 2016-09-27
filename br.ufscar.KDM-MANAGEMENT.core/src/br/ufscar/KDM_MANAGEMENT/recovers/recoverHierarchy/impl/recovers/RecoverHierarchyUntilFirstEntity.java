package br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.impl.recovers;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.interfaces.RecoverHierarchy;

public class RecoverHierarchyUntilFirstEntity implements RecoverHierarchy {

	@Override
	public String getHierarchyOf(ClassUnit classToAvaliate) {
		String completePath = "";
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(classToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(Package packageToAvaliate) {
		String completePath = "";
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(packageToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(BlockUnit blockToAvaliate) {
		String completePath = "";
		
		if(blockToAvaliate.eContainer() instanceof MethodUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((MethodUnit) blockToAvaliate.eContainer()));
			
		}else if(blockToAvaliate.eContainer() instanceof ActionElement){
			
			completePath = completePath.concat(this.getHierarchyOf((ActionElement) blockToAvaliate.eContainer()));
			
		}
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(blockToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(ActionElement actionElementToAvaliate) {
		String completePath = "";
		
		if(actionElementToAvaliate.eContainer() instanceof BlockUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((BlockUnit) actionElementToAvaliate.eContainer()));
			
		}else if(actionElementToAvaliate.eContainer() instanceof ActionElement){
			
			completePath = completePath.concat(this.getHierarchyOf((ActionElement) actionElementToAvaliate.eContainer()));
			
		}
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(actionElementToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(Signature signatureToAvaliate) {
		String completePath = "";
		
		if(signatureToAvaliate.eContainer() instanceof MethodUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((MethodUnit) signatureToAvaliate.eContainer()));
			
		}
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(signatureToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(ParameterUnit parameterToAvaliate) {
		String completePath = "";
		
		if(parameterToAvaliate.eContainer() instanceof MethodUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((MethodUnit) parameterToAvaliate.eContainer()));
			
		}else if(parameterToAvaliate.eContainer() instanceof Signature){
			
			completePath = completePath.concat(this.getHierarchyOf((Signature) parameterToAvaliate.eContainer()));
			
		}
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(parameterToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(StorableUnit storableToAvaliate) {
		String completePath = "";
		
		if(storableToAvaliate.eContainer() instanceof MethodUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((MethodUnit) storableToAvaliate.eContainer()));
			
		}else if(storableToAvaliate.eContainer() instanceof ClassUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((ClassUnit) storableToAvaliate.eContainer()));
			
		}else if(storableToAvaliate.eContainer() instanceof BlockUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((BlockUnit) storableToAvaliate.eContainer()));
			
		}else if(storableToAvaliate.eContainer() instanceof ActionElement){
			
			completePath = completePath.concat(this.getHierarchyOf((ActionElement) storableToAvaliate.eContainer()));
			
		}
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(storableToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(MethodUnit methodToAvaliate) {
		String completePath = "";
		
		if(methodToAvaliate.eContainer() instanceof ClassUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((ClassUnit) methodToAvaliate.eContainer()));
			
		}else if(methodToAvaliate.eContainer() instanceof InterfaceUnit){
			
			completePath = completePath.concat(this.getHierarchyOf((InterfaceUnit) methodToAvaliate.eContainer()));
			
		}
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(methodToAvaliate.getName());
		
		return completePath;
	}

	@Override
	public String getHierarchyOf(InterfaceUnit interfaceToAvaliate) {
		String completePath = "";
		
		completePath = (completePath.concat(SEPARATOR_TYPE)).concat(interfaceToAvaliate.getName());
		
		return completePath;
	}

}
