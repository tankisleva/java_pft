package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 26.03.16.
 */
public class GroupDataGenerator {

    @Parameter(names = "-c",description = "Count Group")
    public int count;

    @Parameter(names = "-f",description = "Write File")
    public String file;


    @Parameter(names = "-d",description = "Data format")
    public String format;

public static void main(String[] args) throws IOException {
    GroupDataGenerator generator =  new GroupDataGenerator();
    JCommander jCommander =  new JCommander(generator);

    try {
    jCommander.parse(args);}
    catch (ParameterException ex) {
        jCommander.usage();
        return;
    }
    generator.run();

//    int count = Integer.parseInt(args[0]);
//    File file = new File(args[1]);

}

    private void run() throws IOException {
        List <GroupData> groups = generateGroup(count);
        
        if (format.equals("cvs")) {
            saveAsCvs(groups, new File(file));
        }
        
        else  if (format.equals("xml")){
            saveAsXml(groups, new File(file));
        }

        else  if (format.equals("json")){
            saveAsJson(groups, new File(file));
        }

        else {
            System.out.println("Unricognized format: " + format);
        }
    }

    private void saveAsJson(List<GroupData> groups, File file) throws IOException {
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file))
        {writer.write(json);}
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml =  xstream.toXML(groups);
        try (Writer writer = new FileWriter(file))
        { writer.write(xml);}
    }


    private  void saveAsCvs(List<GroupData> groups, File file) throws IOException {
       try ( Writer writer = new FileWriter(file))

       {
           for (GroupData group : groups) {
               writer.write(String.format("%s; %s; %s\n", group.getName(), group.getHeader(), group.getFooter()));
           }
       }

    }

    
    
    
    private  List<GroupData> generateGroup(int count) {
        List <GroupData> groups =  new ArrayList<GroupData>();

        for (int i = 0; i<count; i++){
            groups.add(new GroupData().withName(String.format("test %s", i)).withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }

        return groups;
    }

}
