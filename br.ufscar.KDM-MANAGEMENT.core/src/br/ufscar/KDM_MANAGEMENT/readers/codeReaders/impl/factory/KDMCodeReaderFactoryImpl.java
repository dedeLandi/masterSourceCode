package br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.factory;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.gmt.modisco.omg.kdm.action.ActionElement;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.EnumeratedType;
import org.eclipse.gmt.modisco.omg.kdm.code.InterfaceUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Package;
import org.eclipse.gmt.modisco.omg.kdm.code.StorableUnit;

import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.enums.KDMActionElementsType;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.factory.KDMCodeReaderFactory;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.actionElements.KDMActionElementReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.classes.KDMClassReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.enumeretedTypes.KDMEnumeratedTypeReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.interfaces.KDMInterfaceReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.methods.KDMMethodReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.packages.KDMPackageReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.impl.readers.storables.KDMStorableReaderImpl;
import br.ufscar.KDM_MANAGEMENT.readers.codeReaders.interfaces.KDMCodeGenericReader;

public class KDMCodeReaderFactoryImpl extends EFactoryImpl implements KDMCodeReaderFactory {

	
	public static KDMCodeReaderFactory init() {
		try {
			KDMCodeReaderFactory theKDMCodeReaderFactory = (KDMCodeReaderFactory)EPackage.Registry.INSTANCE.getEFactory("br.ufscar.KDM_MANAGEMENT.readers.codeReaders.factory.KDMCodeReaderFactory"); //$NON-NLS-1$ 
			if (theKDMCodeReaderFactory != null) {
				return theKDMCodeReaderFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KDMCodeReaderFactoryImpl();
	}
	
	public KDMCodeReaderFactoryImpl() {
		super();
	}
	
	@Override
	public KDMCodeGenericReader<Package> createKDMPackageReader() {
		return new KDMPackageReaderImpl();
	}

	@Override
	public KDMCodeGenericReader<Package> createKDMPackageReaderWithFilter(String nameToSearch) {
		return new KDMPackageReaderImpl(nameToSearch);
	}
	
	@Override
	public KDMCodeGenericReader<ClassUnit> createKDMClassReader() {
		return new KDMClassReaderImpl();
	}

	@Override
	public KDMCodeGenericReader<ClassUnit> createKDMClassReaderWithFilter(String nameToSearch) {
		return new KDMClassReaderImpl(nameToSearch);
	}
	
	@Override
	public KDMCodeGenericReader<InterfaceUnit> createKDMInterfaceReader() {
		return new KDMInterfaceReaderImpl();
	}

	@Override
	public KDMCodeGenericReader<InterfaceUnit> createKDMInterfaceReaderWithFilter(String nameToSearch) {
		return new KDMInterfaceReaderImpl(nameToSearch);
	}
	
	@Override
	public KDMCodeGenericReader<EnumeratedType> createKDMEnumeratedTypeReader() {
		return new KDMEnumeratedTypeReaderImpl();
	}

	@Override
	public KDMCodeGenericReader<EnumeratedType> createKDMEnumeratedTypeReaderWithFilter(String nameToSearch) {
		return new KDMEnumeratedTypeReaderImpl(nameToSearch);
	}
	
	@Override
	public KDMCodeGenericReader<StorableUnit> createKDMStorableReader() {
		return new KDMStorableReaderImpl();
	}

	@Override
	public KDMCodeGenericReader<StorableUnit> createKDMStorableReaderWithFilter(String nameToSearch) {
		return new KDMStorableReaderImpl(nameToSearch);
	}

	@Override
	public KDMCodeGenericReader<ActionElement> createKDMActionReader() {
		return new KDMActionElementReaderImpl();
	}

	@Override
	public KDMCodeGenericReader<ActionElement> createKDMActionReaderWithFilter(String nameToSearch) {
		return new KDMActionElementReaderImpl(nameToSearch);
	}

	@Override
	public KDMCodeGenericReader<ActionElement> createKDMActionReaderWithFilter(KDMActionElementsType kdmActionElementsType) {
		return new KDMActionElementReaderImpl(kdmActionElementsType);
	}

	@Override
	public KDMCodeGenericReader<MethodUnit> createKDMMethodReader() {
		return new KDMMethodReaderImpl();
	}

	@Override
	public KDMCodeGenericReader<MethodUnit> createKDMMethodReaderWithFilter(String nameToSearch) {
		return new KDMMethodReaderImpl(nameToSearch);
	}

}
