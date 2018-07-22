package org.sourcepit.cargo4j.model;

import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
public class Resolve {
//	resolve: Resolve,
	private List<Node> nodes;
//	root: Option<PackageId>,
	private Optional<String> root;
}
