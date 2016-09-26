package br.ufscar.KDM_MANAGEMENT.complements.complementRelations.impl.factory;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import br.ufscar.KDM_MANAGEMENT.complements.complementRelations.factory.ComplementsRelationsFactory;
import br.ufscar.KDM_MANAGEMENT.complements.complementRelations.impl.complements.ComplementsRelationHasTypeImpl;
import br.ufscar.KDM_MANAGEMENT.complements.complementRelations.interfaces.ComplementsRelations;

public class ComplementsRelationsFactoryImpl extends EFactoryImpl implements ComplementsRelationsFactory {

	
	public static ComplementsRelationsFactory init() {
		try {
			ComplementsRelationsFactory theComplementsRelationsFactoryFactory = (ComplementsRelationsFactory)EPackage.Registry.INSTANCE.getEFactory("br.ufscar.KDM_MANAGEMENT.complements.complementRelations.factory.ComplementsRelationsFactory"); //$NON-NLS-1$ 
			if (theComplementsRelationsFactoryFactory != null) {
				return theComplementsRelationsFactoryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComplementsRelationsFactoryImpl();
	}
	
	public ComplementsRelationsFactoryImpl() {
		super();
	}

	@Override
	public ComplementsRelations createInstanceOfComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createParameterToComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createImplementsComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createImplementationOfComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createHasTypeComplements() {
		return new ComplementsRelationHasTypeImpl();
	}

	@Override
	public ComplementsRelations createHasValueComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createExtendsComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createGeneratedFromComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createIncludesComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createVariantToComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createRedefinesComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createVisibleInComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createImportsComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createControlFlowComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createEntryFlowComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createCallsComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createDispatchesComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createReadsComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createWritesComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createAddressesComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createCreatesComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createExitFlowComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createThrowsComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplementsRelations createUsesTypeComplements() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
