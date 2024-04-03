package ru.stqa.mantis.manager;

import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

public class JamesClickHelper extends HelperBase {
  public JamesClickHelper(ApplicationManager manager) {
    super(manager);
  }

  public void addUser(String email, String password) {
    CommandLine cmd = new CommandLine("java"
        , "-cp"
        , "\"james-server-jpa-app.lib/*\""
        , "org.apache.james.cli.ServerCmd"
        , "AddUser", email, password);
    cmd.setWorkingDirectory(manager.property("james.workingDir"));
    var out = new CircularOutputStream();
    cmd.copyOutputTo(out);
    cmd.execute();
    cmd.waitFor();
    System.out.println(out);
  }
}
