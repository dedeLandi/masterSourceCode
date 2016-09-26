package br.ufscar.KDM_MANAGEMENT.recovers.recoverRelations.impl.factory;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
	public RecoverRelations createInstanceOfRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createParameterToRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createImplementsRecover() {
		return new RecoverRelationsImplementsImpl();
	}

	@Override
	public RecoverRelations createImplementationOfRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createHasTypeRecover() {
		return new RecoverRelationsHasTypeImpl();
	}

	@Override
	public RecoverRelations createHasValueRecover() {
		return new RecoverRelationsHasValueImpl();
	}

	@Override
	public RecoverRelations createExtendsRecover() {
		return new RecoverRelationsExtendsImpl();
	}

	@Override
	public RecoverRelations createGeneratedFromRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createIncludesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createVariantToRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createRedefinesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createVisibleInRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createImportsRecover() {
		return new RecoverRelationsImportsImpl();
	}

	@Override
	public RecoverRelations createControlFlowRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createEntryFlowRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createCallsRecover() {
		return new RecoverRelationsCallsImpl();
	}

	@Override
	public RecoverRelations createDispatchesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createReadsRecover() {
		return new RecoverRelationsReadsImpl();
	}

	@Override
	public RecoverRelations createWritesRecover() {
		return new RecoverRelationsWritesImpl();
	}

	@Override
	public RecoverRelations createAddressesRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createCreatesRecover() {
		return new RecoverRelationsCreatesImpl();
	}

	@Override
	public RecoverRelations createExitFlowRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createThrowsRecover() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecoverRelations createUsesTypeRecover() {
		return new RecoverRelationsUsesTypeImpl();
	}
	
	
}
