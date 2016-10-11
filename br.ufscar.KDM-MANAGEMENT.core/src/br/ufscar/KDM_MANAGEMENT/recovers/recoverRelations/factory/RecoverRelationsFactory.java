package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.factory;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.gmt.modisco.omg.kdm.action.Addresses;
import org.eclipse.gmt.modisco.omg.kdm.action.Calls;
import org.eclipse.gmt.modisco.omg.kdm.action.ControlFlow;
import org.eclipse.gmt.modisco.omg.kdm.action.Creates;
import org.eclipse.gmt.modisco.omg.kdm.action.Dispatches;
import org.eclipse.gmt.modisco.omg.kdm.action.EntryFlow;
import org.eclipse.gmt.modisco.omg.kdm.action.ExitFlow;
import org.eclipse.gmt.modisco.omg.kdm.action.Reads;
import org.eclipse.gmt.modisco.omg.kdm.action.Throws;
import org.eclipse.gmt.modisco.omg.kdm.action.UsesType;
import org.eclipse.gmt.modisco.omg.kdm.action.Writes;
import org.eclipse.gmt.modisco.omg.kdm.code.Extends;
import org.eclipse.gmt.modisco.omg.kdm.code.GeneratedFrom;
import org.eclipse.gmt.modisco.omg.kdm.code.HasType;
import org.eclipse.gmt.modisco.omg.kdm.code.HasValue;
import org.eclipse.gmt.modisco.omg.kdm.code.ImplementationOf;
import org.eclipse.gmt.modisco.omg.kdm.code.Implements;
import org.eclipse.gmt.modisco.omg.kdm.code.Imports;
import org.eclipse.gmt.modisco.omg.kdm.code.Includes;
import org.eclipse.gmt.modisco.omg.kdm.code.InstanceOf;
import org.eclipse.gmt.modisco.omg.kdm.code.ParameterTo;
import org.eclipse.gmt.modisco.omg.kdm.code.Redefines;
import org.eclipse.gmt.modisco.omg.kdm.code.VariantTo;
import org.eclipse.gmt.modisco.omg.kdm.code.VisibleIn;

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;


public interface RecoverRelationsFactory extends EFactory {

	RecoverRelationsFactory eINSTANCE = br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.factory.RecoverRelationsFactoryImpl.init();
	
	//CodeRelations
	RecoverRelations<InstanceOf> createInstanceOfRecover();
	
	RecoverRelations<ParameterTo> createParameterToRecover();
	
	RecoverRelations<Implements> createImplementsRecover();
	
	RecoverRelations<ImplementationOf> createImplementationOfRecover();
	
	RecoverRelations<HasType> createHasTypeRecover();
	
	RecoverRelations<HasValue> createHasValueRecover();

	RecoverRelations<Extends> createExtendsRecover();
	
	RecoverRelations<GeneratedFrom> createGeneratedFromRecover();
	
	RecoverRelations<Includes> createIncludesRecover();
	
	RecoverRelations<VariantTo> createVariantToRecover();
	
	RecoverRelations<Redefines> createRedefinesRecover();

	RecoverRelations<VisibleIn> createVisibleInRecover();
	
	RecoverRelations<Imports> createImportsRecover();
	
	//ActionRelations
	
	RecoverRelations<ControlFlow> createControlFlowRecover();
	
	RecoverRelations<EntryFlow> createEntryFlowRecover();
	
	RecoverRelations<Calls> createCallsRecover();
	
	RecoverRelations<Dispatches> createDispatchesRecover();
	
	RecoverRelations<Reads> createReadsRecover();
	
	RecoverRelations<Writes> createWritesRecover();
	
	RecoverRelations<Addresses> createAddressesRecover();
	
	RecoverRelations<Creates> createCreatesRecover();
	
	RecoverRelations<ExitFlow> createExitFlowRecover();
	
	RecoverRelations<Throws> createThrowsRecover();
	
	RecoverRelations<UsesType> createUsesTypeRecover();
	
}
