package org.sourcepit.cargo4j.model.toolchain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Target {

	private final static Set<String> VENDORS = new HashSet<>(Arrays.asList("unknown", "pc", "none"));

	private final String arch;
	private final Optional<String> vendor;
	private final String os;

	public static Target parse(String string) {
		String[] segments = string.split("-");

		String arch = segments[0];

		Optional<String> vendor;
		if (VENDORS.contains(segments[1])) {
			vendor = Optional.of(segments[1]);
		} else {
			vendor = Optional.empty();
		}

		StringBuilder os = new StringBuilder();
		for (int i = vendor.isPresent() ? 2 : 1; i < segments.length; i++) {
			if (os.length() > 0) {
				os.append('-');
			}
			os.append(segments[i]);
		}

		return new Target(arch, vendor, os.toString());
	}

	private Target(String arch, Optional<String> vendor, String os) {
		this.arch = arch;
		this.vendor = vendor;
		this.os = os;
	}

	public String getArch() {
		return arch;
	}

	public Optional<String> getVendor() {
		return vendor;
	}

	public String getOs() {
		return os;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arch == null) ? 0 : arch.hashCode());
		result = prime * result + ((os == null) ? 0 : os.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Target other = (Target) obj;
		if (arch == null) {
			if (other.arch != null) {
				return false;
			}
		} else if (!arch.equals(other.arch)) {
			return false;
		}
		if (os == null) {
			if (other.os != null) {
				return false;
			}
		} else if (!os.equals(other.os)) {
			return false;
		}
		if (vendor == null) {
			if (other.vendor != null) {
				return false;
			}
		} else if (!vendor.equals(other.vendor)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		str.append(arch);
		if (vendor.isPresent()) {
			str.append('-');
			str.append(vendor.get());
		}
		str.append('-');
		str.append(os);
		return str.toString();
	}

}
