package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase {
    @Test
    void canModifyGroup() {
        if (app.groups().getGroupsCount() == 0) {
            app.groups().createGroup(new GroupData("", "java_for_testers", "header", "footer"));
        }
        var oldGroups = app.groups().getGroupsList();
        var random = new Random();
        var index = random.nextInt(oldGroups.size());
        var testData = new GroupData().withName("modified name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.groups().getGroupsList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        //newGroups.sort(Comparator.comparingInt(o -> Integer.parseInt(o.id())));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

}
