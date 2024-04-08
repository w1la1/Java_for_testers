package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;

public class IssueCreationTests extends TestBase {
  @Test
  void canCreateIssue() {
    app.rest().createIssue(new IssueData()
        .withSummary(CommonFunctions.randomString(10))
        .withDescription(CommonFunctions.randomString(50))
        .withProject(1L));
  }


}
