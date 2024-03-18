package manager;


import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {
  private SessionFactory sessionFactory;

  public HibernateHelper(ApplicationManager manager) {
    super(manager);

    sessionFactory = new Configuration()
        .addAnnotatedClass(ContactRecord.class)
        .addAnnotatedClass(GroupRecord.class)
        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
        .setProperty(AvailableSettings.USER, "root")
        .setProperty(AvailableSettings.PASS, "")
        .buildSessionFactory();
  }


  static List<ContactData> convertListContacts(List<ContactRecord> records) {
    List<ContactData> result = new ArrayList<>();
    for (var record : records) {
      result.add(convertContacts(record));
    }
    return result;
  }

  private static ContactData convertContacts(ContactRecord record) {
    return new ContactData("" + record.id, record.lastname, record.firstname, record.address, record.email, record.phone);
  }


  private static ContactRecord convertContacts(ContactData data) {
    var id = data.id();
    if ("".equals(id)) {
      id = "0";
    }
    return new ContactRecord(Integer.parseInt(id), data.lastName(), data.firstName(), data.address(), data.eMail(), data.phone());
  }

  public List<ContactData> getContactsListHbm() {
    return convertListContacts(sessionFactory.fromSession(session -> {
      return session.createQuery("from ContactRecord", ContactRecord.class).list();
    }));
  }

  public long getContactsCountHbm() {
    return sessionFactory.fromSession(session -> {
      return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
    });
  }

  public void createContactsHbm(ContactData contactData) {
    sessionFactory.inSession(session -> {
      session.getTransaction().begin();
      session.persist(convertContacts(contactData));
      session.getTransaction().commit();
    });
  }

  static List<GroupData> convertListGroups(List<GroupRecord> records) {
    List<GroupData> result = new ArrayList<>();
    for (var record : records) {
      result.add(convertGroups(record));
    }
    return result;
  }

  private static GroupData convertGroups(GroupRecord record) {
    return new GroupData("" + record.id, record.name, record.header, record.footer);
  }

  private static GroupRecord convertGroups(GroupData data) {
    var id = data.id();
    if ("".equals(id)) {
      id = "0";
    }
    return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
  }

  public List<GroupData> getGroupsListHbm() {
    return convertListGroups(sessionFactory.fromSession(session -> {
      return session.createQuery("from GroupRecord", GroupRecord.class).list();
    }));
  }

  public long getGroupsCountHbm() {
    return sessionFactory.fromSession(session -> {
      return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
    });
  }

  public void createGroupHbm(GroupData groupData) {
    sessionFactory.inSession(session -> {
      session.getTransaction().begin();
      session.persist(convertGroups(groupData));
      session.getTransaction().commit();
    });
  }
}
