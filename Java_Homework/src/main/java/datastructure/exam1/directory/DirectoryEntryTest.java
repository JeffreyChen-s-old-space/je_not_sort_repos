package datastructure.exam1.directory;

import java.util.ArrayList;
import java.util.Objects;

public class DirectoryEntryTest {

    public static void main(String[] argv) {
        ArrayList<DirectoryEntry> directoryEntryArrayList = new ArrayList<>();
        directoryEntryArrayList.add(new DirectoryEntry("lwchen", "1234"));
        directoryEntryArrayList.add(new DirectoryEntry("chen", "123"));
        DirectoryEntry test1 = new DirectoryEntry("chen", "");
        DirectoryEntry test2 = new DirectoryEntry("", "123");
        DirectoryEntry test3 = new DirectoryEntry("chen", "123");
        System.out.println(directoryEntryArrayList.indexOf(test1));
        System.out.println(directoryEntryArrayList.indexOf(test2));
        System.out.println(directoryEntryArrayList.indexOf(test3));
    }

    static class DirectoryEntry {
        String name;
        String number;

        public DirectoryEntry(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DirectoryEntry that = (DirectoryEntry) o;
            if (this.number.equals(""))
                return Objects.equals(name, that.name);
            if (this.name.equals(""))
                return Objects.equals(number, that.number);
            return Objects.equals(name, that.name) &&
                    Objects.equals(number, that.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, number);
        }
    }

}
