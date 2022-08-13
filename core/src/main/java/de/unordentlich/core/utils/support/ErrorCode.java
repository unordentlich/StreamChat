package de.unordentlich.core.utils.support;

public enum ErrorCode {
  SCP01("IOException"),
  SCP02("IRCException"),
  SCP03("TokenFormatException"),
  SCP04("UsernameFormatException"),
  SCP05("MissingRequiredConfigValuesException");

  public final String ExceptionName;

  private ErrorCode(String label) {
    this.ExceptionName = label;
  }
}
