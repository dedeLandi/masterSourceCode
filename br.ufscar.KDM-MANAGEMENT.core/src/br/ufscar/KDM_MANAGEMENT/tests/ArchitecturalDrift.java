package br.ufscar.KDM_MANAGEMENT.tests;

public class ArchitecturalDrift {

	private String description = "";

	
	
	
	
	
	
	
	@Override
	public String toString() {
		String string = "";
		
		string.concat("Description: " + description);
		
		return string;
	}
	
	
	public String getDescription() {
		return description;
	}

	public ArchitecturalDrift setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
	
	
	
	
	
}
