//Leaky Bucket Algorithm
import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter The Number of Packets : ");
        int n = scanner.nextInt();
        System.out.print("Enter The Bucket Size : ");
        int bsize = scanner.nextInt();
        System.out.print("Enter The Output Rate : ");
        int outrate = scanner.nextInt();
        int[] packets = new int[n];
        System.out.println("Enter The Packet Sizes in Order:");
        for (int i = 0; i < n; i++) {
            packets[i] = scanner.nextInt();
        }
        int i = 0, cycle = 0, remains = 0, sent = 0;
        boolean flag = false;
        System.out.println("Cycle\tPackets\tDropped\tSent\tRemains");
        while (true) {
            cycle++;
            if (packets[i] <= (bsize - remains)) {
                if ((remains + packets[i]) <= outrate) {
                    sent = remains + packets[i];
                    remains = 0;
                } else {
                    remains += (packets[i] - outrate);
                    sent = outrate;
                    }
                    if (!flag)
                    System.out.println(cycle + "\t" + packets[i] + "\t----\t" + sent + "\t" + remains);
                    else
                    System.out.println(cycle + "\t---\t----\t" + sent + "\t" + remains);
                    } else {
                    if (remains >= outrate) {
                    sent = outrate;
                    remains -= outrate;
                    } else {
                        sent = remains;
                        remains = 0;
                        }
                    if (!flag)
                    System.out.println(cycle + "\t" + packets[i] + "\tDrop\t" + sent + "\t" + remains);
                    else
                    System.out.println(cycle + "\t---\t----\t" + sent + "\t" + remains);
                    }
                    if (i == (n - 1)) {
                        flag = true;
                     if (remains == 0)
                        break;
                    } else
                     i++;
                     }
                        scanner.close();
 }
}
