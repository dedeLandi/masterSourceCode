package br.ufscar.REFARCH_KDM.wizardsPage;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Page02SelectFileWithDrift extends WizardPage {

	private Combo cbAlgorithm;
	private Text tPathFileDrifts;

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
		
		Label lblSelectionOfThe = new Label(container, SWT.NONE);
		lblSelectionOfThe.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 3, 1));
		lblSelectionOfThe.setText("Selection of the algorithm to process the file containing the architectural drifts. ");

		Label lAlgorithmForExtractDrift = new Label(container, SWT.NONE);
		lAlgorithmForExtractDrift.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lAlgorithmForExtractDrift.setText("Algorithm for extract drifts");

		cbAlgorithm = new Combo(container, SWT.READ_ONLY);
		cbAlgorithm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		cbAlgorithm.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				getWizard().getContainer().updateButtons();
			}

		});

		Label lFile = new Label(container, SWT.NONE);
		lFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lFile.setText("File containing the drifts");

		tPathFileDrifts = new Text(container, SWT.BORDER);
		tPathFileDrifts.setEditable(false);
		tPathFileDrifts.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button bSearch = new Button(container, SWT.NONE);
		bSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				chooseFile();
			}
		});
		bSearch.setText("Search");

		fillCbAlgorithm(cbAlgorithm);
	}

	protected void chooseFile() {
		FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
        fd.setText("Open");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.xmi" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open();
        if(selected == null){
        	tPathFileDrifts.setText("");
        }else{
        	System.out.println(selected);
        	tPathFileDrifts.setText(selected);
        }
	}

	private void fillCbAlgorithm(Combo combo) {
		combo.add("ARCH-KDM Algorihm");
	}
	
	private boolean validateCbAlgorithm() {
		if(cbAlgorithm.getSelectionIndex() == -1) {  
			setErrorMessage("Select one type of algorithm to continue.");
			return false;
		}else{
			setErrorMessage(null); // clear error message. 
			return true;
		}
	}
	
	private boolean validateTPathFileDrifts() {
		if("".equalsIgnoreCase(tPathFileDrifts.getText())) { 
			setErrorMessage("Select one file to continue.");
			return false;
		}else{
			setErrorMessage(null); // clear error message. 
			return true;
		}
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return validateCbAlgorithm() && validateTPathFileDrifts();
	}

}
