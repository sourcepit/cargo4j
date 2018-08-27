package org.sourcepit.cargo4j.model.toolchain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Toolchain {

	private final String channel;
	private final Optional<LocalDate> date;
	private final Optional<Target> target;

	public static Toolchain parse(String string) {

		final String[] segments = string.split("-");

		final String channel = segments[0];
		if (channel.length() == 0) {
			throw new IllegalArgumentException("Not a valid toolchain triple");
		}

		final Optional<LocalDate> date;
		if (segments.length > 3) {
			LocalDate value = null;
			try {
				value = LocalDate.parse(segments[1] + "-" + segments[2] + "-" + segments[3]);
			} catch (DateTimeParseException e) {
				value = null;
			}
			date = Optional.ofNullable(value);
		} else {
			date = Optional.empty();
		}

		final int targetStart = date.isPresent() ? 4 : 1;

		final Optional<Target> target;
		if (segments.length > targetStart) {
			StringBuilder sb = new StringBuilder();
			for (int i = targetStart; i < segments.length; i++) {
				if (sb.length() > 0) {
					sb.append('-');
				}
				sb.append(segments[i]);
			}
			target = Optional.of(Target.parse(sb.toString()));
		} else {
			target = Optional.empty();
		}

		return new Toolchain(channel, date, target);
	}

	private Toolchain(String channel, Optional<LocalDate> date, Optional<Target> target) {
		this.channel = channel;
		this.date = date;
		this.target = target;
	}

	public String getChannel() {
		return channel;
	}

	public Optional<LocalDate> getDate() {
		return date;
	}

	public Optional<Target> getTarget() {
		return target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Toolchain other = (Toolchain) obj;
		if (channel == null) {
			if (other.channel != null) {
				return false;
			}
		} else if (!channel.equals(other.channel)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		str.append(channel);
		if (date.isPresent()) {
			str.append('-');
			str.append(date.get().toString());
		}
		if (target.isPresent()) {
			str.append('-');
			str.append(target.get().toString());
		}
		return str.toString();
	}

}
