package de.unordentlich.streamchatplus.core.utils.support.objects;

public class DebugEntry {

  String event;
  long timestamp = System.currentTimeMillis();
  DebugPriority type = DebugPriority.INFO;
  DebugActionExecuter executer = DebugActionExecuter.ADDON;

  Class executedClass;

  public DebugEntry(String event) {
    this.event = event;
  }

  public DebugEntry(String event, DebugPriority type) {
    this.event = event;
    this.type = type;
  }

  public DebugEntry(String event, Class executedClass) {
    this.event = event;
    this.executedClass = executedClass;
  }

  public DebugEntry(String event, DebugPriority type, Class executedClass) {
    this.event = event;
    this.type = type;
    this.executedClass = executedClass;
  }

  public DebugActionExecuter getExecuter() {
    return executer;
  }

  public DebugEntry executer(DebugActionExecuter executer) {
    this.executer = executer;
    return this;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public DebugPriority getType() {
    return type;
  }

  public void setType(DebugPriority type) {
    this.type = type;
  }

  public Class getExecutedClass() {
    return executedClass;
  }

  public void setExecutedClass(Class executedClass) {
    this.executedClass = executedClass;
  }
}
