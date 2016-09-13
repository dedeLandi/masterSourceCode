package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.factory;

import org.eclipse.emf.ecore.EFactory;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;


public interface RecoverRelationsFactory extends EFactory {

	RecoverRelationsFactory eINSTANCE = br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.factory.RecoverRelationsFactoryImpl.init();
	
	//CodeRelations
	RecoverRelations createInstanceOfRecover();
	
	RecoverRelations createParameterToRecover();
	
	RecoverRelations createImplementsRecover();
	
	RecoverRelations createImplementationOfRecover();
	
	RecoverRelations createHasTypeRecover();
	
	RecoverRelations createHasValueRecover();

	RecoverRelations createExtendsRecover();
	
	RecoverRelations createGeneratedFromRecover();
	
	RecoverRelations createIncludesRecover();
	
	RecoverRelations createVariantToRecover();
	
	RecoverRelations createRedefinesRecover();

	RecoverRelations createVisibleInRecover();
	
	RecoverRelations createImportsRecover();
	
	//ActionRelations
	
	RecoverRelations createControlFlowRecover();
	
	RecoverRelations createEntryFlowRecover();
	
	RecoverRelations createCallsRecover();
	
	RecoverRelations createDispatchesRecover();
	
	RecoverRelations createReadsRecover();
	
	RecoverRelations createWritesRecover();
	
	RecoverRelations createAddressesRecover();
	
	RecoverRelations createCreatesRecover();
	
	RecoverRelations createExitFlowRecover();
	
	RecoverRelations createThrowsRecover();
	
	RecoverRelations createUsesTypeRecover();
	
}
