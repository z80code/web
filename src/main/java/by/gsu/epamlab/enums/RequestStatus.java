package by.gsu.epamlab.enums;

public enum RequestStatus {
	OK,
	ERROR;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
