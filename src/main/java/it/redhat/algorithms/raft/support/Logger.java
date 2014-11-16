package it.redhat.algorithms.raft.support;

public class Logger {

  private Class clazz;

  public Logger(Class clazz) {
    this.clazz = clazz;
  }

  public void debug(String message) {
    System.out.println(String.format("DEBUG %s - %s", clazz.toString(), message));
  }

  public void info(String message) {
    System.out.println(String.format("INFO %s - %s", clazz.toString(), message));
  }

  public static Logger getLogger(Class clazz) {
    return new Logger(clazz);
  }

  public void warning(String message) {
    System.out.println(String.format("WARN %s - %s", clazz.toString(), message));
  }
}
