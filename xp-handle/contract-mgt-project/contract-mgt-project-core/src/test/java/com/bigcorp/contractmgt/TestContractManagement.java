package com.bigcorp.contractmgt;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.runtime.RuntimeService;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@Deploy("com.bigcorp.contractmgt.contract-mgt-project-core")
public class TestContractManagement {
	@Inject
	CoreSession coreSession;
	
	@Test
	public void isNuxeoStarted(){
		Assert.assertNotNull("Runtime is not available", Framework.getRuntime());
	}
	
	@Test
	public void isComponentLoaded() {
		RuntimeService runtime = Framework.getRuntime();
		Assert.assertNotNull(runtime.getComponent("com.bigcorp.contractmgt.contract-service"));
	}
	
	@Test
	public void isContractSceAvailable() {
		ContractService contractService = Framework.getService(ContractService.class);
		Assert.assertNotNull(contractService);
		
		Assert.assertEquals("Hello World!", contractService.helloWorld());
	}
	
	@Test
	public void testDocumentCreation() {
		DocumentModel doc = coreSession.createDocumentModel("/", "myFile", "File"); // Create document in memory
		doc = coreSession.createDocument(doc);										// Mark document for write
		coreSession.save();															// Only needed in unit tests, will be called automatically elsewhere
		
		IdRef docIdRef = new IdRef(doc.getId());
		doc = coreSession.getDocument(docIdRef);
		Assert.assertNotNull(doc);
		
		PathRef docPathRef = new PathRef(doc.getPathAsString());
		doc = coreSession.getDocument(docPathRef);
		Assert.assertNotNull(doc);
		
		String query = "SELECT * FROM File";
		DocumentModelList queryResults = coreSession.query(query);
		Assert.assertEquals(1, queryResults.size());
	}
}
