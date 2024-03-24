package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
  @Parameter(names = {"--type", "-t"})
  String type;
  @Parameter(names = {"--output", "-o"})
  String output;
  @Parameter(names = {"--format", "-f"})
  String format;
  @Parameter(names = {"--count", "-c"})
  int count;

  public static void main(String[] args) throws IOException {
    var generator = new Generator();
    JCommander.newBuilder()
        .addObject(generator)
        .build()
        .parse(args);
    generator.run();
  }

  private void run() throws IOException {
    var data = generate();
    save(data);
  }

  private Object generate() {
    if ("groups".equals(type)) {
      return generateGroups();
    } else if ("contacts".equals(type)) {
      return generateContacts();
    } else {
      throw new IllegalArgumentException("Неизвестный тип данных" + type);
    }
  }

  private Object generateData(Supplier<Object> dataSupplier) {
  return   Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
//    var result = new ArrayList<Object>();
//    for (int i = 0; i < count; i++) {
//      result.add(dataSupplier.get());
//    }
//    return result;
  }

  private Object generateGroups() {
    return generateData(() -> new GroupData().withName(CommonFunctions.randomString( 10))
        .withHeader(CommonFunctions.randomString(10))
        .withFooter(CommonFunctions.randomString(10)));
//    var result = new ArrayList<GroupData>(List.of());
//    for (int i = 0; i < count; i++) {
//      result.add(new GroupData()
//          .withName(CommonFunctions.randomString(i * 10))
//          .withHeader(CommonFunctions.randomString(i * 10))
//          .withFooter(CommonFunctions.randomString(i * 10)));
  }

  private Object generateContacts() {
    return generateData(() -> new ContactData()
        .withLastName(CommonFunctions.randomString(10))
        .withFirstName(CommonFunctions.randomString(10))
        .withAddress(CommonFunctions.randomString(10))
        .withEmail(CommonFunctions.randomString(10))
        .withHome(CommonFunctions.randomString(10)));
//    var result = new ArrayList<ContactData>(List.of());
//    for (int i = 0; i < count; i++) {
//      result.add(new ContactData()
//          .withLastName(CommonFunctions.randomString(i * 10))
//          .withFirstName(CommonFunctions.randomString(i * 10))
//          .withAddress(CommonFunctions.randomString(i * 10))
//          .withEmail(CommonFunctions.randomString(i * 10))
//          .withPhone(CommonFunctions.randomString(i * 10))
//          .withPhoto(randomFile("src/test/resources/images")));
  }


  private void save(Object data) throws IOException {
    if ("json".equals(format)) {
      var mapper = new ObjectMapper();
      mapper.writeValue(new File(output), data);
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      var json = mapper.writeValueAsString(data);

      try (var writer = new FileWriter(output);) {
        writer.write(json);
      }
    } else if ("yaml".equals(format)) {
      var mapper = new YAMLMapper();
      mapper.writeValue(new File(output), data);
    } else if ("xml".equals(format)) {
      var mapper = new XmlMapper();
      mapper.writeValue(new File(output), data);
    } else {
      throw new IllegalArgumentException("Неизвестный формат данных" + format);
    }
  }
}
