package OOP.ec20315.A8;

interface Visitable {

    Direction visit( // Returns direction the visitor leaves towards.
                     Visitor visitor,
                     Direction directionVistorArrivesFrom);
}