package br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.interfaces;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;

public interface RecoverHierarchy {

	public static final String SEPARATOR_TYPE = ".";
	
	public String getHierarchyOf(ClassUnit classToAvaliate);
	
	public String getHierarchyOf(Package packageToAvaliate);

	public String getHierarchyOf(BlockUnit blockToAvaliate);
	
	public String getHierarchyOf(ActionElement actionElementToAvaliate);

	public String getHierarchyOf(Signature signatureToAvaliate);
	
	public String getHierarchyOf(ParameterUnit parameterToAvaliate);
	
	public String getHierarchyOf(StorableUnit storableToAvaliate);
	
	public String getHierarchyOf(MethodUnit methodToAvaliate);
	
	public String getHierarchyOf(InterfaceUnit interfaceToAvaliate);

	public String getHierarchyOf(EnumeratedType enumeratedTypeToAvaliate);
	
}
