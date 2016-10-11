package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.factory;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;

import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.enums.KDMActionElementsType;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.interfaces.KDMCodeGenericReader;


public interface KDMCodeReaderFactory extends EFactory {

	KDMCodeReaderFactory eINSTANCE = br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.factory.KDMCodeReaderFactoryImpl.init();
	
	KDMCodeGenericReader<Package> createKDMPackageReader();
	
	KDMCodeGenericReader<Package> createKDMPackageReaderWithFilter(String nameToSearch);
	
	KDMCodeGenericReader<ClassUnit> createKDMClassReader();
	
	KDMCodeGenericReader<ClassUnit> createKDMClassReaderWithFilter(String nameToSearch);
	
	KDMCodeGenericReader<InterfaceUnit> createKDMInterfaceReader();
	
	KDMCodeGenericReader<InterfaceUnit> createKDMInterfaceReaderWithFilter(String nameToSearch);
	
	KDMCodeGenericReader<EnumeratedType> createKDMEnumeratedTypeReader();
	
	KDMCodeGenericReader<EnumeratedType> createKDMEnumeratedTypeReaderWithFilter(String nameToSearch);
	
	KDMCodeGenericReader<MethodUnit> createKDMMethodReader();
	
	KDMCodeGenericReader<MethodUnit> createKDMMethodReaderWithFilter(String nameToSearch);
	
	KDMCodeGenericReader<StorableUnit> createKDMStorableReader();

	KDMCodeGenericReader<StorableUnit> createKDMStorableReaderWithFilter(String nameToSearch);
	
	KDMCodeGenericReader<ActionElement> createKDMActionReader();

	KDMCodeGenericReader<ActionElement> createKDMActionReaderWithFilter(String nameToSearch);

	KDMCodeGenericReader<ActionElement> createKDMActionReaderWithFilter(KDMActionElementsType kdmActionElementsType);
	
}
