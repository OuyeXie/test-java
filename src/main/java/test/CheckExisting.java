package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by ouyexie on 10/10/16.
 */
public class CheckExisting {
    public static void main(String[] args) {
        try {
            File fileA = new File("resources/groups");
            BufferedReader brA = new BufferedReader(new FileReader(fileA));
            String currLine = null;
            String groups = null;
            if ((currLine = brA.readLine()) != null) {
                groups = currLine;
            }
            System.out.println("groups: " + groups);

            int total = 0;
            int notMatch = 0;
            int strictNotMatch = 0;
            Set<String> names = new HashSet<>();
            List<String> duplicatedNames = new LinkedList<>();
            File fileB = new File("resources/names");
            BufferedReader brB = new BufferedReader(new FileReader(fileB));
            while ((currLine = brB.readLine()) != null) {
                if (currLine.equals("==============")) {
                    System.out.println("==============first part finish==============");
                    continue;
                }
                total++;
                String name = currLine.split("\\(")[0];
                if (names.contains(name)) {
                    duplicatedNames.add(name);
                }
                names.add(name);
                if (!groups.contains(name)) {
                    notMatch++;
                    System.out.println(String.format("not match: [%s]", name));
                    name = name.substring(0, 2);
                    if (!groups.contains(name)) {
                        strictNotMatch++;
                        System.out.println(String.format("strict not match: [%s]", name));
                    }
                }
            }
            System.out.println("==============result==============");
            for (String duplicatedName : duplicatedNames) {
                System.out.println(String.format("duplicated name: [%s]", duplicatedName));
            }
            System.out.println(String.format("total num: [%d]", total));
            System.out.println(String.format("distinct total num: [%d]", names.size()));
            System.out.println(String.format("not match num: [%d]", notMatch));
            System.out.println(String.format("strict not match num: [%d]", strictNotMatch));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
