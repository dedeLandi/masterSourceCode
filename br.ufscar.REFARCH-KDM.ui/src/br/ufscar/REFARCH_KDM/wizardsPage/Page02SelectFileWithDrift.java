package br.ufscar.REFARCH_KDM.wizardsPage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class Page02SelectFileWithDrift extends WizardPage {

	/**
	 * Create the wizard.
	 */
	public Page02SelectFileWithDrift() {
		super("page02");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Select the file containing the architectural drifts.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));
		
		Label lAlgorithmForExtractDrift = new Label(container, SWT.NONE);
		lAlgorithmForExtractDrift.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lAlgorithmForExtractDrift.setText("Algorithm for extract drifts");
		
		Combo cbAlgorithm = new Combo(container, SWT.READ_ONLY);
		cbAlgorithm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		fillCbAlgorithm(cbAlgorithm);
		
		Label lFile = new Label(container, SWT.NONE);
		lFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lFile.setText("File");
		
		Text tPathFileDrifts = new Text(container, SWT.BORDER);
		tPathFileDrifts.setEditable(false);
		tPathFileDrifts.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button bSearch = new Button(container, SWT.NONE);
		bSearch.setText("Search");
	}

	private void fillCbAlgorithm(Combo combo) {
		combo.add("ARCH-KDM Algorihm");
	}
}
