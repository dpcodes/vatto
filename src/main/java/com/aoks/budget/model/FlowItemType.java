package com.aoks.budget.model;

/**
 * 
 * @author Diego Pereira
 * @site www.diegopereira.com.br
 *
 */
public enum FlowItemType {

	RECURRENT {
		
		@Override
		public TransactionAlgorithm getAlgorithm() {
			return new RecurrentAlgorithm();
		}

		@Override
		public FlowItemInfo getInfo() {
			return new RecurrentInfo();
		}
	}, 
	
	INSTALMENTS {
		@Override
		public TransactionAlgorithm getAlgorithm() {
			return new InstalmentsAlgorithm();
		}

		@Override
		public FlowItemInfo getInfo() {
			return new InstalmentsInfo();
		}
	},
	
	TRANSFER {

		@Override
		public TransactionAlgorithm getAlgorithm() {
			return new TransferAlgorithm();
		}

		@Override
		public FlowItemInfo getInfo() {
			return new TransferInfo();
		}};
	
	public abstract TransactionAlgorithm getAlgorithm();
	public abstract FlowItemInfo getInfo();
	
}
