import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CircusPeopleTower {

  public static class CircusPerson {
    public int h;
    public int w;
    public CircusPerson() {
      this.h = 0;
      this.w = 0;
    }

    public CircusPerson(int h, int w) {
      this.h = h;
      this.w = w;
    }

    public boolean canBeAbove(CircusPerson p) {
      if (p == null) return true;
      return (this.h < p.h && this.w < p.w);
    }

    public void printPerson() {
      System.out.println("Current Person: (" + this.h +", " + this.w + ")");
    }
  }

  /* ----------------------------------- */
  /* ----- Dynamic Programming Way ----- */
  /* ----------------------------------- */
  public ArrayList<CircusPerson> highestTowerDP(CircusPerson[] circusPeople) {
    return highestTowerDP(circusPeople, null, new HashMap<CircusPerson,ArrayList<CircusPerson>>());
  }

  private ArrayList<CircusPerson> highestTowerDP(CircusPerson[] circusPeople,
                                                  CircusPerson bottom,
                                                  HashMap<CircusPerson,ArrayList<CircusPerson>> maxTowers) {
    if (bottom != null && maxTowers.containsKey(bottom) ) {
      return maxTowers.get(bottom);
    }

    int max_height = 0;
    ArrayList<CircusPerson> max_tower = null;
    int new_height;
    ArrayList<CircusPerson> new_tower = null;

    for (CircusPerson p : circusPeople) {
      if (p.canBeAbove(bottom)) {
        // Put p on it and try to find the new highest tower with [bottom,p,.....]
        new_tower = highestTowerDP(circusPeople, p, maxTowers);
        new_height = new_tower.size();

        if (new_height > max_height) {
          max_height = new_height;
          max_tower = new_tower;
        }
      }
    }

    if (max_tower == null) max_tower = new ArrayList<CircusPerson>();
    if (bottom != null) max_tower.add(0,bottom);
    maxTowers.put(bottom, max_tower);

    printCircusPersonArray(max_tower);

    return (ArrayList<CircusPerson>)max_tower.clone();
  }


  /* ----------------------------------- */
  /* ------ Sorting Searching Way ------ */
  /* ----------------------------------- */
  public ArrayList<CircusPerson> highestTowerSortSearch(CircusPerson[] circusPeople) {
    return null;
  }

  /* ----------------------------------- */
  /* -------------- Other -------------- */
  /* ----------------------------------- */

  public static void printCircusPersonArray(ArrayList<CircusPerson> a) {
    Iterator<CircusPerson> iterator = a.iterator();
    while(iterator.hasNext()) {
      CircusPerson p = iterator.next();
      System.out.print("(" + p.h + ", " + p.w + ") ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    CircusPerson[] circusPeople = { new CircusPeopleTower.CircusPerson(65, 100),
                                    new CircusPeopleTower.CircusPerson(70, 150),
                                    new CircusPeopleTower.CircusPerson(56, 90),
                                    new CircusPeopleTower.CircusPerson(75, 190),
                                    new CircusPeopleTower.CircusPerson(60, 95),
                                    new CircusPeopleTower.CircusPerson(68, 110) };
    CircusPeopleTower cpt = new CircusPeopleTower();
    ArrayList<CircusPerson> r = cpt.highestTowerDP(circusPeople);

    CircusPeopleTower.printCircusPersonArray(r);
  }
}
