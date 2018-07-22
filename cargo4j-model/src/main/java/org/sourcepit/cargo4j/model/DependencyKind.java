package org.sourcepit.cargo4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DependencyKind {
//    #[serde(rename = "normal")]
//    /// The 'normal' kind
//    Normal,
	@JsonProperty("normal")
	NORMAL,
//    #[serde(rename = "dev")]
//    /// Those used in tests only
//    Development,
	@JsonProperty("dev")
	DEVELOPMENT,
//    #[serde(rename = "build")]
//    /// Those used in build scripts only
//    Build,
	@JsonProperty("build")
	BUILD,
//    #[doc(hidden)] DoNotMatchExhaustively,
}
