import java.util.LinkedList;

public class AnimalShelter {
  LinkedList<Animal> dogs;
  LinkedList<Animal> cats;

  public void enqueue(Animal animal) {
    switch ( animal.getType() ) {
      case DOG:
        dogs.push(animal);
        break;
      case CAT:
        cats.push(animal);
        break;
      default:
        break;
    }
  }

  public Animal dequeueDog() {
    return dogs.pollLast();
  }

  public Animal dequeueCat() {
    return cats.pollLast();
  }

  public Animal dequeue() {
    if (dogs.size() == 0) {
      return dequeueCat();
    }

    if (cats.size() == 0) {
      return dequeueDog();
    }

    if (dogs.peekLast().getArrivalTime() < cats.peekLast().getArrivalTime() ) {
      return dequeueDog();
    }
    else {
      return dequeueCat();
    }
  }

  public AnimalShelter()
  {
    dogs = new LinkedList<Animal>();
    cats = new LinkedList<Animal>();
  }

  public enum AnimalType {
    DOG, CAT
  }

  public class Animal {
    private double arrivalTime;
    private AnimalType type;

    public Animal(double arrivalTime, AnimalType type) {
      this.arrivalTime = arrivalTime;
      this.type = type;
    }

    public double getArrivalTime() {
      return this.arrivalTime;
    }

    public AnimalType getType() {
      return this.type;
    }
  }

  public static void main(String[] args) {

  }
}
