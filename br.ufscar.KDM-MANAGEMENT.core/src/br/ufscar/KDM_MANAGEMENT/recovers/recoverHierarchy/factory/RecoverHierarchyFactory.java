package br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.factory;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.interfaces.RecoverHierarchy;

public interface RecoverHierarchyFactory {

	RecoverHierarchyFactory eINSTANCE = br.ufscar.KDM_MANAGEMENT.recovers.recoverHierarchy.impl.factory.RecoverHierarchyFactoryImpl.init();
	
	RecoverHierarchy createRecoverHierarchyComplete();
	
	RecoverHierarchy createRecoverHierarchyUntilFirstEntity();
	
	RecoverHierarchy createRecoverHierarchyUntilFirstPackage();
	
}
