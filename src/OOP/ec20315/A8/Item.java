package OOP.ec20315.A8;

class Item {

    final String name;

    Item(String nameOfItem) {
        name = nameOfItem;
    }

    public boolean equals(Item x) {
        return name.equals(x.name);
    }

    public String toString() {return name + "("+this.hashCode()+")";}
}