package org.sourcepit.cargo4j.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Package {
//    name: &'a str,
	private String name;
//    version: &'a str,
	private String version;
//    id: &'a PackageId,
	private String id;
//    license: Option<&'a str>,
	private Optional<String> license;
//    license_file: Option<&'a str>,
	private Optional<String> license_file;
//    description: Option<&'a str>,
	private Optional<String> description;
//    source: &'a SourceId,
	private String source;
//    dependencies: &'a [Dependency],
	private List<Dependency> dependencies;
//    targets: &'a [Target],
	private List<Target> targets;
//    features: &'a FeatureMap,
	private Map<String, List<String>> features;
//    manifest_path: &'a str,
	@JsonProperty("manifest_path")
	private String manifestPath;
//    metadata: Option<&'a toml::Value>,
	private Optional<Map<String, Object>> metadata;
//    authors: &'a [String],
	private List<String> authors;
//    categories: &'a [String],
	private List<String> categories;
//    keywords: &'a [String],
	private List<String> keywords;
//    readme: Option<&'a str>,
	private Optional<String> readme;
//    repository: Option<&'a str>,
	private Optional<String> repository;

	

}
