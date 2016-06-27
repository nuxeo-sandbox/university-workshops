package com.bigcorp.contractmgt;

import org.nuxeo.runtime.model.DefaultComponent;

public class ContractServiceImpl extends DefaultComponent implements ContractService {

	@Override
	public String helloWorld() {
		return "Hello World!";
	}

}
