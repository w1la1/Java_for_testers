package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {
    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupsCountHbm() == 0) {
            app.hbm().createGroupHbm(new GroupData("", "java_for_testers", "header", "footer"));
        }
        var oldGroups = app.hbm().getGroupsListHbm();
        var random = new Random();
        var index = random.nextInt(oldGroups.size());
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupsListHbm();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));

        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

}
