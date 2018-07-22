package org.sourcepit.cargo4j.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Target {
//    /// Is this a `--bin bin`, `--lib`, `--example ex`?
//    /// Serialized as a list of strings for historical reasons.
//    kind: &'a TargetKind,
	private List<String> kind;
//    /// Corresponds to `--crate-type` compiler attribute.
//    /// See https://doc.rust-lang.org/reference/linkage.html
//    crate_types: Vec<&'a str>,
	@JsonProperty("crate_types")
	private List<String> crateTypes;
//    name: &'a str,
	private String name;
//    src_path: &'a PathBuf,
	@JsonProperty("src_path")
	private String srcPath;

}
