package com.bigcorp.contractmgt;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("RetentionPolicy")
public class RetentionPolicyDescriptor {
	@XNode("@id")
	public String retentionPolicyId;

	@XNode("archiving")
	public Integer archiving;

	@XNode(value = "deletion@number")
	public Integer deletion;

	@XNodeList(value = "industry@id", type = String[].class, componentType = String.class)
	public String[] industries;
}
