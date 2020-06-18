import java.util.Scanner;

class Main {
  private static int count = 0;

  public static void main(String[] args) {
    // put your code here
    boolean isOrdered = false;
    int current = 0;
    int previous;
    ORDERING_TYPE orderingType = ORDERING_TYPE.UNKNOWN;
    Scanner scanner = new Scanner(System.in);
    final String input = scanner.nextLine();
    scanner = new Scanner(input);
    while (scanner.hasNextInt()) {
      final int i = scanner.nextInt();
      if (!scanner.hasNextInt()) {
        // Last number which has to be zero
        assert i == 0;
        isOrdered = true;
        break;
      }
      count++;
      previous = current;
      current = i;
      if (count == 1) continue;
      orderingType = verifyOrdering(previous, current, orderingType);
      if (orderingType == ORDERING_TYPE.UNORDERED) {
        break;
      }
    }
    System.out.println(isOrdered);
  }

  private static ORDERING_TYPE verifyOrdering(
      int previous, int current, ORDERING_TYPE orderingType) {
    switch (orderingType) {
      case UNKNOWN:
        return findOrder(previous, current);
      case ASC:
        if (previous <= current) {
          return ORDERING_TYPE.ASC;
        } else return ORDERING_TYPE.UNORDERED;
      case DESC:
        if (previous >= current) {
          return ORDERING_TYPE.DESC;
        } else return ORDERING_TYPE.UNORDERED;
    }
    return ORDERING_TYPE.UNORDERED;
  }

  private static ORDERING_TYPE findOrder(int previous, int current) {
    if (previous == current) return ORDERING_TYPE.UNKNOWN;
    if (previous > current) return ORDERING_TYPE.DESC;
    else return ORDERING_TYPE.ASC;
  }

  private enum ORDERING_TYPE {
    ASC,
    DESC,
    UNKNOWN,
    UNORDERED
  }
}
