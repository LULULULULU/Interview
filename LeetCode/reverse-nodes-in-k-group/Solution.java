// https://oj.leetcode.com/problems/reverse-nodes-in-k-group/
public class Solution {


  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public static class Result {
    ListNode head;
    ListNode tail;
    Result(ListNode h, ListNode t) {
      head = h;
      tail = t;
    }
  }

  // Before run store the next groups head
  public Result reverseGroup(ListNode head, int k) {
    ListNode reversed = null;
    ListNode current = head;
    ListNode temp;
    int count = 0;

    while(count < k) {
      temp = current.next;
      current.next = reversed;
      reversed = current;
      current = temp;
      count++;
    }

    temp = reversed;
    while(count > 1) {
      temp = temp.next;
      count--;
    }

    return new Result(reversed, temp);
  }

  public ListNode reverseKGroup(ListNode head, int k) {

    if (k == 0 || k == 1) {
      return head;
    }

    ListNode newHead = null;
    ListNode thisGroupHead = head;
    ListNode tempTail = null;
    ListNode nextGroupHead = head;
    int count = 0;

    // If link list length less than k return head
    while(nextGroupHead != null) {
      nextGroupHead = nextGroupHead.next;
      count++;
      if (count >= k + 1) {
        break;
      }
    }
    if (count < k) {
      return head;
    }

    count = 1;
    nextGroupHead = head;

    while(true) {
      // From count 1 to count k, get the first k items
      // after this loop, nextGroupHead is on the current's tail
      while(count < k && nextGroupHead != null) {
        nextGroupHead = nextGroupHead.next;
        count++;
      }

      // If has less than k item left, append them to the tail and return
      if (count < k || nextGroupHead == null) {
        tempTail.next = thisGroupHead;
        return newHead;
      }

      nextGroupHead = nextGroupHead.next;

      Result r = reverseGroup(thisGroupHead,k);

      // If this is the first time
      if (newHead == null) {
        newHead = r.head;
        tempTail = r.tail;
      }
      else {
        tempTail.next = r.head;
        tempTail = r.tail;
      }

      // Update this group head and count
      thisGroupHead = nextGroupHead;
      count = 1;
    }
  }

  /*
   */

  public static void main(String[] args) {
    ListNode l01 = new ListNode(1);
    ListNode l02 = new ListNode(2);
    ListNode l03 = new ListNode(3);
    ListNode l04 = new ListNode(4);
    ListNode l05 = new ListNode(5);
    ListNode l06 = new ListNode(6);
    ListNode l07 = new ListNode(7);
    ListNode l08 = new ListNode(8);
    ListNode l09 = new ListNode(9);
    ListNode l10 = new ListNode(10);
    ListNode l11 = new ListNode(11);
    ListNode l12 = new ListNode(12);
    ListNode l13 = new ListNode(13);
    ListNode l14 = new ListNode(14);
    l01.next = l02;
    l02.next = l03;
    l03.next = l04;
    l04.next = l05;
    l05.next = l06;
    l06.next = l07;
    l07.next = l08;
    l08.next = l09;
    l09.next = l10;
    l10.next = l11;
    l11.next = l12;
    l12.next = l13;
    l13.next = l14;

    printLink(l01);
    Solution a = new Solution();
    printLink(a.reverseKGroup(l01,3));
    printLink(a.reverseKGroup(l14,2));
  }


  public static void printLink(ListNode ln) {
    System.out.print("Start: | ");
    while (ln != null) {
      System.out.print(ln.val + " -> ");
      ln = ln.next;
    }
    System.out.println("null |");
  }

}
