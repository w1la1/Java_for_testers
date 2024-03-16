package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {
  @Test
  public void canRemoveGroup() {
    if (app.hbm().getGroupsCountHbm() == 0) {
      app.hbm().createGroupHbm(new GroupData("", "java_for_testers", "header", "footer"));
    }
    var oldGroups = app.hbm().getGroupsListHbm();
    //var oldGroups = app.groups().getGroupsList();
    var random = new Random();
    var index = random.nextInt(oldGroups.size());
    app.groups().removeGroup(oldGroups.get(index));
    var newGroups = app.hbm().getGroupsListHbm();
    //var newGroups = app.groups().getGroupsList();
    var expectedList = new ArrayList<>(oldGroups);
    expectedList.remove(index);
    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  void canRemoveAllGroupsAtOnce() {
    if (app.hbm().getGroupsCountHbm() == 0) {
      app.hbm().createGroupHbm(new GroupData("", "java_for_testers", "header", "footer"));
//        if (app.groups().getGroupsCount() == 0) {
//            app.groups().createGroup(new GroupData("", "java_for_testers", "header", "footer"));
    }
    app.groups().removeAllGroups();
    Assertions.assertEquals(0, app.hbm().getGroupsCountHbm());
  }
}

