package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 26.03.16.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c",description = "Count Contact")
    public int count;

    @Parameter(names = "-f",description = "Write File")
    public String file;


    @Parameter(names = "-d",description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator =  new ContactDataGenerator();
        JCommander jCommander =  new JCommander(generator);

        try {
            jCommander.parse(args);}
        catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);

        if (format.equals("csv")) {
            saveAsCvs(contacts, new File(file));
        }

        else  if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        }

        else  if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        }

        else {
            System.out.println("Unricognized format: " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file))
        {writer.write(json);}
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml =  xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file))
        {writer.write(xml);}

    }


    private  void saveAsCvs(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file))

        { for (ContactData contact: contacts){
            writer.write(String.format("%s; %s; %s; %s; %s\n", contact.getFirstname(),contact.getLastname(),contact.getCompany(),contact.getEmail(),
                    contact.getHomeadress()));
        }}
    }




    private  List<ContactData> generateContact(int count) {
        List <ContactData> contacts =  new ArrayList<ContactData>();

        for (int i = 0; i<count; i++){
            contacts.add(new ContactData().withFirstname(String.format("tester %s", i)).withLastname(String.format("testerov %s", i))
                    .withCompany(String.format("tesvovaya %s", i)).withEmail(String.format("test@mail.ru %s",i))
                    .withHomeadress(String.format("testovaya street 11-35  %s",i)));
        }

        return contacts;
    }
}
