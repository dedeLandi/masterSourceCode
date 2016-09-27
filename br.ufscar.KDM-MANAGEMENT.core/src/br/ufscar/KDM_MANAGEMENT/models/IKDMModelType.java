package br.ufscar.KDM_MANAGEMENT.models;

import org.eclipse.gmt.modisco.omg.kdm.kdm.KDMModel;

public interface IKDMModelType<T> {

	abstract T getModelByType(KDMModel kdmModel);
	
}
