package org.sourcepit.cargo4j.model;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Metadata {
//	  packages: Vec<Package>,
	private List<Package> packages;
//	    workspace_members: Vec<PackageId>,
	@JsonProperty("workspace_members")
	private List<String> workspaceMembers;
//	    resolve: Option<MetadataResolve>,
	private Optional<Resolve> resolve;
//	    target_directory: String,
	@JsonProperty("target_directory")
	private String targetDirectory;
//	    version: u32,
	private long version;
//	  workspace_root: String,
	@JsonProperty("workspace_root")
	private String workspaceRoot;

}
