package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.factory;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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

import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.factory.RecoverRelationsFactory;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations.RecoverRelationsCallsImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations.RecoverRelationsCreatesImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations.RecoverRelationsReadsImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations.RecoverRelationsUsesTypeImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.actionRelations.RecoverRelationsWritesImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations.RecoverRelationsExtendsImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations.RecoverRelationsHasTypeImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations.RecoverRelationsHasValueImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations.RecoverRelationsImplementsImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.recovers.codeRelations.RecoverRelationsImportsImpl;
import br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.interfaces.RecoverRelations;

public class RecoverRelationsFactoryImpl extends EFactoryImpl implements RecoverRelationsFactory {

	
	public static RecoverRelationsFactory init() {
		try {
			RecoverRelationsFactory theRecoverRelationsFactoryFactory = (RecoverRelationsFactory)EPackage.Registry.INSTANCE.getEFactory("br.ufscar.REFARCH_KDM.recoverRelations.factory.RecoverRelationsFactory"); //$NON-NLS-1$ 
			if (theRecoverRelationsFactoryFactory != null) {
				return theRecoverRelationsFactoryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RecoverRelationsFactoryImpl();
	}
	
	public RecoverRelationsFactoryImpl() {
		super();
	}

	@Override
	public RecoverRelations<InstanceOf> createInstanceOfRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<ParameterTo> createParameterToRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Implements> createImplementsRecover() {
		return new RecoverRelationsImplementsImpl();
	}

	@Override
	public RecoverRelations<ImplementationOf> createImplementationOfRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<HasType> createHasTypeRecover() {
		return new RecoverRelationsHasTypeImpl();
	}

	@Override
	public RecoverRelations<HasValue> createHasValueRecover() {
		return new RecoverRelationsHasValueImpl();
	}

	@Override
	public RecoverRelations<Extends> createExtendsRecover() {
		return new RecoverRelationsExtendsImpl();
	}

	@Override
	public RecoverRelations<GeneratedFrom> createGeneratedFromRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Includes> createIncludesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<VariantTo> createVariantToRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Redefines> createRedefinesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<VisibleIn> createVisibleInRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Imports> createImportsRecover() {
		return new RecoverRelationsImportsImpl();
	}

	@Override
	public RecoverRelations<ControlFlow> createControlFlowRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<EntryFlow> createEntryFlowRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Calls> createCallsRecover() {
		return new RecoverRelationsCallsImpl();
	}

	@Override
	public RecoverRelations<Dispatches> createDispatchesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Reads> createReadsRecover() {
		return new RecoverRelationsReadsImpl();
	}

	@Override
	public RecoverRelations<Writes> createWritesRecover() {
		return new RecoverRelationsWritesImpl();
	}

	@Override
	public RecoverRelations<Addresses> createAddressesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Creates> createCreatesRecover() {
		return new RecoverRelationsCreatesImpl();
	}

	@Override
	public RecoverRelations<ExitFlow> createExitFlowRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<Throws> createThrowsRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations<UsesType> createUsesTypeRecover() {
		return new RecoverRelationsUsesTypeImpl();
	}
	
	
}
