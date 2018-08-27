package org.sourcepit.cargo4j.model.metadata;

import java.util.List;

import lombok.Data;

@Data
public class Node {
//    id: &'a PackageId,
	private String id;
//    dependencies: Vec<&'a PackageId>,
	private List<String> dependencies;
//    features: Vec<&'a str>,
	private List<String> features;
}
