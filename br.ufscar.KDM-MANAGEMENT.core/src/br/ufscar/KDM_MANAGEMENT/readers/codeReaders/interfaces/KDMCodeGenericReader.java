package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.interfaces;

import java.util.List;
import java.util.Map;

import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Signature;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Segment;

public interface  KDMCodeGenericReader<T> {

	public Map<String, List<T>> getAllFromSegment(Segment segmentToSearch);
	
	public Map<String, List<T>> getAllFromModel(CodeModel codeModelToSearch);
	
	public Map<String, List<T>> getAllFromPackage(Package packageToSearch);
	
	public Map<String, List<T>> getAllFromClassUnit(ClassUnit classUnitToSearch);
	
	public Map<String, List<T>> getAllFromInterfaceUnit(InterfaceUnit interfaceUnitToSearch);
	
	public Map<String, List<T>> getAllFromEnumeratedType(EnumeratedType enumeratedTypeToSearch);
	
	public Map<String, List<T>> getAllFromMethodUnit(MethodUnit methodUnitToSearch);
	
	public Map<String, List<T>> getAllFromStorableUnit(StorableUnit storableUnitToSearch);
	
	public Map<String, List<T>> getAllFromBlockUnit(BlockUnit blockUnitToSearch);
	
	public Map<String, List<T>> getAllFromSignature(Signature signatureUnitToSearch);
    
	public Map<String, List<T>> getAllFromActionElement(ActionElement actionElementToSearch);
	
	public Map<String, List<T>> getAllFromParameterUnit(ParameterUnit parameterUnitUnitToSearch);
	
}
