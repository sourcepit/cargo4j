package org.sourcepit.cargo4j.model.metadata;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Dependency {

//    name: &'a str,
	private String name;
//    source: &'a SourceId,
	private String source;
//    req: String,
	private String req;
//    kind: Kind,
	private Optional<DependencyKind> kind;
//    rename: Option<&'a str>,
	private Optional<String> rename;
//
//    optional: bool,
	private boolean optional;
//    uses_default_features: bool,
	@JsonProperty("uses_default_features")
	private boolean usesDefaultFeatures;
//    features: &'a [String],
	private List<String> features;
//    target: Option<&'a Platform>,
	private Optional<String> target;

}
