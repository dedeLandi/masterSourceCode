package br.ufscar.REFARCH_KDM.wizards;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import br.ufscar.REFARCH_KDM.wizardsPage.Page01Introduction;
import br.ufscar.REFARCH_KDM.wizardsPage.Page02SelectFileWithDrift;
import br.ufscar.REFARCH_KDM.wizardsPage.Page03SelectDrift;
import br.ufscar.REFARCH_KDM.wizardsPage.Page04ProcessAnalisis;
import br.ufscar.REFARCH_KDM.wizardsPage.Page05FillRefactoringCatalog;
import br.ufscar.REFARCH_KDM.wizardsPage.Page05ProcessFillRefactoringCatalog;
import br.ufscar.REFARCH_KDM.wizardsPage.Page06SelectRefactoringToDo;
import br.ufscar.REFARCH_KDM.wizardsPage.Page07ProcessEffectRefactor;
import br.ufscar.REFARCH_KDM.wizardsPage.Page08SaveAndFinish;

public class ArchitecturalRefactoringWizard extends Wizard {
	
	
	private Page01Introduction page1 = new Page01Introduction();
	private Page02SelectFileWithDrift page2 = new Page02SelectFileWithDrift();
	private Page03SelectDrift page3 = new Page03SelectDrift();
	private Page04ProcessAnalisis page4 = new Page04ProcessAnalisis();
	private Page06SelectRefactoringToDo page6 = new Page06SelectRefactoringToDo();
	private Page07ProcessEffectRefactor page7 = new Page07ProcessEffectRefactor();
	private Page08SaveAndFinish page8 = new Page08SaveAndFinish();
	
	private Page05ProcessFillRefactoringCatalog page5_1 = new Page05ProcessFillRefactoringCatalog();
	private Page05FillRefactoringCatalog page5_2 = new Page05FillRefactoringCatalog();

	public ArchitecturalRefactoringWizard() {
		setWindowTitle("Architectural Refactoring Wizard");
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		addPage(page1);
		addPage(page2);
		addPage(page3);
		addPage(page4);
		addPage(page5_1);
		addPage(page5_2);
		addPage(page6);
		addPage(page7);
		addPage(page8);
	}

	@Override
	public boolean canFinish() {
		 if(getContainer().getCurrentPage() == page8){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	@Override
	public boolean performFinish() {
		//usar o mensage dialog
		MessageDialog.open(MessageDialog.INFORMATION, getShell(), "Wizzard finished", "Wizzard finished", MessageDialog.INFORMATION);
		return true;
	}

	public boolean performCancel() {
		boolean ans = MessageDialog.openConfirm(getShell(), "Confirmation", "Are you sure to cancel the wizard?");
		if(ans)
			return true;
		else
			return false;
	}  

}
