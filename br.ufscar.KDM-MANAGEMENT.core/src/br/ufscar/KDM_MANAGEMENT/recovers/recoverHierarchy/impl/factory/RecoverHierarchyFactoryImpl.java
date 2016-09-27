package br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.impl.factory;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.factory.RecoverHierarchyFactory;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.impl.recovers.RecoverHierarchyComplete;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.impl.recovers.RecoverHierarchyUntilFirstEntity;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.impl.recovers.RecoverHierarchyUntilFirstPackage;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.interfaces.RecoverHierarchy;

public class RecoverHierarchyFactoryImpl implements RecoverHierarchyFactory{

	public static RecoverHierarchyFactory init() {
		try {
			RecoverHierarchyFactoryImpl theRecoverHierarchyFactoryFactory = (RecoverHierarchyFactoryImpl)EPackage.Registry.INSTANCE.getEFactory("br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.factory.RecoverHierarchyFactory"); //$NON-NLS-1$ 
			if (theRecoverHierarchyFactoryFactory != null) {
				return theRecoverHierarchyFactoryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RecoverHierarchyFactoryImpl();
	}
	
	public RecoverHierarchyFactoryImpl() {
		super();
	}

	@Override
	public RecoverHierarchy createRecoverHierarchyComplete() {
		return new RecoverHierarchyComplete();
	}

	@Override
	public RecoverHierarchy createRecoverHierarchyUntilFirstEntity() {
		return new RecoverHierarchyUntilFirstEntity();
	}

	@Override
	public RecoverHierarchy createRecoverHierarchyUntilFirstPackage() {
		return new RecoverHierarchyUntilFirstPackage();
	}
	
}
