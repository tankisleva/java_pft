package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

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
        save(groups,new File(file));
    }

    private  void save(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);

        for (GroupData group: groups){
            writer.write(String.format("%s; %s; %s\n", group.getName(),group.getHeader(),group.getFooter()));
        }
         writer.close();
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
