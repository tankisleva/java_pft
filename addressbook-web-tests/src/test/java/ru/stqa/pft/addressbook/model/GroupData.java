package ru.stqa.pft.addressbook.model;

public class GroupData {
    private final String name;
    private final String header;
    private final String footer;
    private  int id;

    public GroupData(String name, String header, String footer, int id) {
        this.name = name;
        this.header = header;
        this.footer = footer;
//        this.id = id;
        this.id =  Integer.MAX_VALUE;
    }



    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
        this.id = 0;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return name != null ? name.equals(groupData.name) : groupData.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}
