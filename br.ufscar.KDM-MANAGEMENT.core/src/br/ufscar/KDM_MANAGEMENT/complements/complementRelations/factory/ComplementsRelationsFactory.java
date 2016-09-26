package br.ufscar.KDM_MANAGEMENT.complements.complementRelations.factory;

import org.eclipse.emf.ecore.EFactory;

import br.ufscar.KDM_MANAGEMENT.complements.complementRelations.interfaces.ComplementsRelations;


public interface ComplementsRelationsFactory extends EFactory {

	ComplementsRelationsFactory eINSTANCE = br.ufscar.KDM_MANAGEMENT.complements.complementRelations.impl.factory.ComplementsRelationsFactoryImpl.init();
	
	//CodeRelations
	ComplementsRelations createInstanceOfComplements();
	
	ComplementsRelations createParameterToComplements();
	
	ComplementsRelations createImplementsComplements();
	
	ComplementsRelations createImplementationOfComplements();
	
	ComplementsRelations createHasTypeComplements();
	
	ComplementsRelations createHasValueComplements();

	ComplementsRelations createExtendsComplements();
	
	ComplementsRelations createGeneratedFromComplements();
	
	ComplementsRelations createIncludesComplements();
	
	ComplementsRelations createVariantToComplements();
	
	ComplementsRelations createRedefinesComplements();

	ComplementsRelations createVisibleInComplements();
	
	ComplementsRelations createImportsComplements();
	
	//ActionRelations
	
	ComplementsRelations createControlFlowComplements();
	
	ComplementsRelations createEntryFlowComplements();
	
	ComplementsRelations createCallsComplements();
	
	ComplementsRelations createDispatchesComplements();
	
	ComplementsRelations createReadsComplements();
	
	ComplementsRelations createWritesComplements();
	
	ComplementsRelations createAddressesComplements();
	
	ComplementsRelations createCreatesComplements();
	
	ComplementsRelations createExitFlowComplements();
	
	ComplementsRelations createThrowsComplements();
	
	ComplementsRelations createUsesTypeComplements();
	
}
