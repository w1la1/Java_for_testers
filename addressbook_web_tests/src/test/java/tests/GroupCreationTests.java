package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  public static List<GroupData> groupProvider() throws IOException {
    var result = new ArrayList<GroupData>(List.of());
//        for (var name : List.of("", "group name")) {
//            for (var header : List.of("", "group header")) {
//                for (var footer : List.of("", "group footer")) {
//                    result.add(new GroupData()
//                            .withName(name)
//                            .withHeader(header)
//                            .withFooter(footer));
//                }
//            }
//        }
//    var json = "";  //читает файл построчно
//    try (var reader = new FileReader("groups.json");
//         var breader = new BufferedReader(reader)) {
//      var line = breader.readLine();
//      while (line != null) {
//        json = json + line;
//        line = breader.readLine();
//      }
//    }
    var json = Files.readString(Paths.get("groups.json")); //читает файл целиком json
    // var xml = Files.readString(Paths.get("groups.xml"));
    var mapper = new ObjectMapper(); //new XmlMapper();
    var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {
    });
    // var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {});
    result.addAll(value);
    return result;
  }

  public static List<GroupData> negativeGroupProvider() {
    var result = new ArrayList<GroupData>(List.of(new GroupData("", "group name'", "", "")));
    return result;
  }

  public static List<GroupData> singleRandomGroup() {
    return List.of(new GroupData()
        .withName(CommonFunctions.randomString(10))
        .withHeader(CommonFunctions.randomString(15))
        .withFooter(CommonFunctions.randomString(20)));
  }

  @ParameterizedTest
  @MethodSource("singleRandomGroup")
  public void canCreateGroup(GroupData group) {
   // var oldGroups = app.groups().getGroupsList();
    var oldGroups = app.hbm().getGroupsListHbm();
    //int groupCount = app.groups().getGroupsCount();
   // app.hbm().createGroupHbm(group);
    app.groups().createGroup(group);
    //int newGroupCount = app.groups().getGroupsCount();
    //Assertions.assertEquals(groupCount + 1, newGroupCount);
   // var newGroups = app.groups().getGroupsList();
    var newGroups = app.hbm().getGroupsListHbm();
    Comparator<GroupData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newGroups.sort(compareById);
    var maxID = newGroups.get(newGroups.size() - 1).id();

    var expectedList = new ArrayList<>(oldGroups);
    expectedList.add(group.withId(maxID));
    expectedList.sort(compareById);
    Assertions.assertEquals(newGroups, expectedList);

  //  var newUiGroups = app.groups().getGroupsList();
    //var newGroups = app.jdbc().getGroupsListJdbc(); сравнить по ID и nameGroup
  }

  @ParameterizedTest
  @MethodSource("negativeGroupProvider")
  public void cannotCreateGroup(GroupData group) {
    var oldGroups = app.groups().getGroupsList();
    app.groups().createGroup(group);
    var newGroups = app.groups().getGroupsList();
    Assertions.assertEquals(newGroups, oldGroups);
  }
}
