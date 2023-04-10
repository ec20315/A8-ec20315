package OOP.ec20315.A8;
class Room_ec20315 extends Room {

    private boolean lightsOn = false;
    public Direction visit(Visitor visitor, Direction arrivesFrom){
        visitor.tell("\nYou have entered the room from " + arrivesFrom);
        if (lightsOn) {
            visitor.tell("\nThe light is on. You can see around the room. ");
        }
        else {
            visitor.tell("\nThe light is off. You can't see anything. ");
        }

        boolean exit = false;
        while (!exit) {
            if(lightsOn==true){
                visitor.tell("\nThere is a bookshelf\n");
            }
            char choice = 'a';
            if(lightsOn==true){
                choice = visitor.getChoice("What would you like to do?\n" +
                        "a. Turn on the lights\n" +
                        "b. Turn off the lights\n" +
                        "c. Take a book off the shelf\n" +
                        "d. Leave the room\n", new char[]{'a', 'b', 'c', 'd'});
            }else if(lightsOn==false){
                choice = visitor.getChoice("What would you like to do?\n" +
                        "a. Turn on the lights\n" +
                        "b. Turn off the lights\n" +
                        "d. Leave the room\n", new char[]{'a', 'b', 'c', 'd'});
            }


            if (choice == 'a') {
                if(lightsOn==true){
                    visitor.tell("\nThe light was already on ");
                }else if(lightsOn==false){
                    lightsOn = true;
                    visitor.tell("\nYou turn on the lights. It's flickering. ");
                }
            } else if (choice == 'b') {
                if(lightsOn==false){
                    visitor.tell("\nThe light was already off. ");
                }else if(lightsOn==true){
                    lightsOn = false;
                    visitor.tell("\nYou turn off the lights. It's dark. ");
                }
            }  else if (choice == 'c') {
                visitor.tell("You inspect the bookshelf...\n ");
                if(!(visitor.hasEqualItem(new Item("Book")))){
                    Item book = new Item("Book");
                    visitor.giveItem(book);
                }else{
                    visitor.tell("You already took the book.");
                }
            } else if (choice == 'd') {
                if(!(visitor.hasEqualItem(new Item("Book")))){
                    visitor.tell("You attempt to leave the room but the door is locked?");
                }else{
                    visitor.tell("You leave the room.");
                    exit = true;
                }
            } else {
                visitor.tell("Invalid");
            }
        }

        return Direction.opposite(arrivesFrom);
    }
}

