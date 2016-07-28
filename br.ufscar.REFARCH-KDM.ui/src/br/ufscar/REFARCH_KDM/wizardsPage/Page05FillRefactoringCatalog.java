package br.ufscar.REFARCH_KDM.wizardsPage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class Page05FillRefactoringCatalog extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public Page05FillRefactoringCatalog() {
		super("wizardPage");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Wizard Page description");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
	}

}
