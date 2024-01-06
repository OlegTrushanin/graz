package oleg.trushanin.graz;

import java.util.ArrayList;

public class DataVisualPair {

    @Override
    public String toString() {
        return "DataVisualPair{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public DataVisualPair(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    int id;
    String name;

    String value;
    public String getStringOfSpecification(ArrayList<DataVisualPair> list, int position){

        return list.get(position).getId()+" "+list.get(position).getName()+ " "
                + list.get(position).getValue();
    }

    public String getStringOfSpecification(DataVisualPair dataVisualPair){

        return dataVisualPair.getId()+" "+dataVisualPair.getName()+ " "
                + dataVisualPair.getValue();
    }

  }



