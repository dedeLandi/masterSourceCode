package br.ufscar.REFARCH_KDM.readDrifts;

public enum ReadDriftsAlgorithm {
    ALGORITHM_KDM_ARCH_KDM(1, "ARCH-KDM Algorihm");

	private int value;
    private String description;

    private ReadDriftsAlgorithm(int value, String description) {
    	this.value = value;
        this.description = description;
    }

    public int getValue() {
    	return value;
    }
    
    public String getDescription() {
        return description;
    }
}
