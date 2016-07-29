package br.ufscar.REFARCH_KDM.wizardsPage;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Page05ProcessFillRefactoringCatalog extends WizardPage {

	private boolean canFlip = false;
	
	/**
	 * Create the wizard.
	 */
	public Page05ProcessFillRefactoringCatalog() {
		super("page05_1");
		setTitle("Architectural Refactoring Wizard");
		setDescription("Fill the recommendation catalog.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);

		Label lInformation = new Label(container, SWT.NONE);
		lInformation.setAlignment(SWT.CENTER);
		lInformation.setBounds(10, 10, 554, 44);
		lInformation.setText("For process this algorithm, possibly take a long time.\r\nIf you want to continue, click in the button below.");

		Button btnInitiateProcessing = new Button(container, SWT.NONE);
		btnInitiateProcessing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				initiateFillCatalog();
			}
		});
		btnInitiateProcessing.setBounds(240, 71, 108, 25);
		btnInitiateProcessing.setText("Generate Recommendation");

		Label lWait = new Label(container, SWT.NONE);
		lWait.setAlignment(SWT.CENTER);
		lWait.setBounds(10, 128, 554, 59);
		lWait.setText("Please, wait while is generated a recommendation for drift X.");
	}
	
	private void initiateFillCatalog() {
		try {
			// puts the data into a database ...
			getContainer().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Generating recommendation", 100);
					monitor.worked(0);


					//make the processing initiate here
					for (int i = 0; i < 10; i++) {
						Thread.sleep(500);
						monitor.worked(i*10);
					}

					monitor.done();

					setCanFlip(true);
					
				}
			});
			
			getWizard().getContainer().updateButtons();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return isCanFlip();
	}

	public boolean isCanFlip() {
		return canFlip;
	}

	public void setCanFlip(boolean canFlip) {
		this.canFlip = canFlip;
	}

}
